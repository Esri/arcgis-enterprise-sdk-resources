const { Pool } = require("pg");
const https = require("https");

// Single pool instance (one Lakebase source per provider)
let pool = null;
let tokenExpiry = null;
let refreshTimer = null;
let currentConfig = null;

// Refresh 5 minutes before token expires
const TOKEN_BUFFER_MS = 5 * 60 * 1000;

/**
 * HTTPS request to the Databricks workspace REST API.
 */
function databricksApiRequest(method, path, body, config) {
	return new Promise((resolve, reject) => {
		const bodyStr = body ? JSON.stringify(body) : null;
		const options = {
			hostname: config.DATABRICKS_SERVER_HOSTNAME,
			port: 443,
			path,
			method,
			headers: {
				Authorization: `Bearer ${config.DATABRICKS_TOKEN}`,
				"Content-Type": "application/json",
			},
		};
		if (bodyStr) {
			options.headers["Content-Length"] = Buffer.byteLength(bodyStr);
		}

		const req = https.request(options, (res) => {
			let data = "";
			res.on("data", (chunk) => {
				data += chunk;
			});
			res.on("end", () => {
				if (res.statusCode >= 200 && res.statusCode < 300) {
					try {
						resolve(JSON.parse(data));
					} catch (e) {
						reject(new Error(`Failed to parse API response: ${e.message}`));
					}
				} else {
					reject(
						new Error(
							`Databricks API ${path} returned ${res.statusCode}: ${data.substring(0, 300)}`
						)
					);
				}
			});
		});

		req.on("error", (err) => {
			reject(new Error(`Databricks API request failed: ${err.message}`));
		});

		if (bodyStr) req.write(bodyStr);
		req.end();
	});
}

/**
 * Generate a short-lived PG password via the Databricks credentials API.
 *
 * Lakebase Autoscaling uses: POST /api/2.0/postgres/credentials
 * with an "endpoint" param like "projects/{id}/branches/{id}/endpoints/{id}"
 *
 * The endpoint path is set via LAKEBASE_ENDPOINT in config.
 */
async function generateCredential(config) {
	const endpoint = config.LAKEBASE_ENDPOINT;
	if (!endpoint) {
		throw new Error(
			"LAKEBASE_ENDPOINT is required in config (e.g. 'projects/{project-id}/branches/{branch-id}/endpoints/{endpoint-id}'). " +
			"Find this in the Lakebase App → Connect dialog."
		);
	}

	console.log(`[LakebasePool] Generating credential for endpoint "${endpoint}"...`);

	const result = await databricksApiRequest(
		"POST",
		"/api/2.0/postgres/credentials",
		{ endpoint },
		config
	);

	if (!result.token) {
		throw new Error(
			`No token in credential response: ${JSON.stringify(result).substring(0, 200)}`
		);
	}

	const expiry = result.expire_time
		? new Date(result.expire_time).getTime()
		: Date.now() + 55 * 60 * 1000;

	console.log(
		`[LakebasePool] Token generated, expires: ${result.expire_time || "~55min"}`
	);
	return { password: result.token, expiry };
}

/**
 * Get or create a pg.Pool for the configured Lakebase source.
 * Automatically refreshes expired tokens by recreating the pool.
 */
async function getLakebasePool(sourceConfig) {
	// Return existing pool if token is still valid
	if (pool && (!tokenExpiry || Date.now() < tokenExpiry - TOKEN_BUFFER_MS)) {
		return pool;
	}

	currentConfig = sourceConfig;
	await refreshPool(sourceConfig);
	return pool;
}

/**
 * Generate a new credential, tear down the old pool, and create a fresh one.
 * Called both on-demand and proactively by the interval timer.
 */
async function refreshPool(sourceConfig) {
	const { password, expiry } = await generateCredential(sourceConfig);

	// Drain old pool — existing in-flight queries finish, then connections close
	const oldPool = pool;
	if (oldPool) {
		oldPool.end().catch((err) => {
			console.error("[LakebasePool] Error closing old pool:", err.message);
		});
	}

	tokenExpiry = expiry;

	pool = new Pool({
		host: sourceConfig.LAKEBASE_HOST,
		port: sourceConfig.LAKEBASE_PORT || 5432,
		database: sourceConfig.LAKEBASE_DATABASE,
		user: sourceConfig.LAKEBASE_USER || "databricks",
		password,
		ssl: { rejectUnauthorized: false },
		min: 2,
		max: 10,
		idleTimeoutMillis: 60000,
		connectionTimeoutMillis: 30000,
	});

	pool.on("error", (err) => {
		console.error("[LakebasePool] Idle client error:", err.message);
		if (err.message && err.message.includes("authorization")) {
			console.log("[LakebasePool] Auth error, invalidating pool");
			pool = null;
			tokenExpiry = null;
		}
	});

	console.log(
		`[LakebasePool] Pool created for ${sourceConfig.LAKEBASE_HOST}:${sourceConfig.LAKEBASE_PORT || 5432}/${sourceConfig.LAKEBASE_DATABASE}`
	);

	// Schedule proactive refresh before token expires
	scheduleRefresh(sourceConfig, expiry);
}

/**
 * Schedule a timer to proactively refresh the pool before the token expires.
 */
function scheduleRefresh(sourceConfig, expiry) {
	if (refreshTimer) {
		clearTimeout(refreshTimer);
	}

	if (!expiry) return;

	// Refresh 5 min before expiry
	const delay = Math.max((expiry - TOKEN_BUFFER_MS) - Date.now(), 30000);
	console.log(
		`[LakebasePool] Next token refresh in ${Math.round(delay / 1000)}s`
	);

	refreshTimer = setTimeout(async () => {
		try {
			console.log("[LakebasePool] Proactive token refresh...");
			await refreshPool(sourceConfig);
		} catch (err) {
			console.error("[LakebasePool] Proactive refresh failed:", err.message);
			// Next request will trigger a retry via getLakebasePool
			pool = null;
			tokenExpiry = null;
		}
	}, delay);

	// Don't let the timer keep the process alive
	if (refreshTimer.unref) {
		refreshTimer.unref();
	}
}

/**
 * Shut down the Lakebase pool gracefully.
 */
async function shutdownPool() {
	if (refreshTimer) {
		clearTimeout(refreshTimer);
		refreshTimer = null;
	}
	if (pool) {
		try {
			await pool.end();
			console.log("[LakebasePool] Pool closed");
		} catch (err) {
			console.error("[LakebasePool] Error closing pool:", err.message);
		}
		pool = null;
		tokenExpiry = null;
	}
}

module.exports = {
	getLakebasePool,
	shutdownPool,
};

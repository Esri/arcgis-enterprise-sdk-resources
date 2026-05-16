const koopConfig = require("./config/default.json");
const { getLakebasePool, shutdownPool } = require("./modules/lakebase-pool");
const {
	translateToGeoJSON,
	buildSqlQuery,
	generateFiltersApplied,
	getExtentFromGeoJson,
} = require("./modules");
const { insertRows, updateRows, deleteRows } = require("./helpers");

class Model {
	constructor(koop) {
		const sourceConfig = koopConfig.databricks.sources.lakebase;
		if (
			!sourceConfig.DATABRICKS_TOKEN ||
			!sourceConfig.DATABRICKS_SERVER_HOSTNAME ||
			!sourceConfig.LAKEBASE_HOST ||
			!sourceConfig.LAKEBASE_DATABASE ||
			!sourceConfig.LAKEBASE_ENDPOINT
		) {
			throw new Error(
				"Missing required Lakebase config: DATABRICKS_TOKEN, DATABRICKS_SERVER_HOSTNAME, LAKEBASE_HOST, LAKEBASE_DATABASE, or LAKEBASE_ENDPOINT."
			);
		}

		// Graceful shutdown
		process.on("SIGTERM", async () => {
			await shutdownPool();
			process.exit(0);
		});
		process.on("SIGINT", async () => {
			await shutdownPool();
			process.exit(0);
		});
	}

	async getData(req) {
		try {
		// Convert boolean strings to actual booleans
		Object.keys(req.query).forEach((key) => {
			if (req.query[key] + "".toLowerCase() === "true") req.query[key] = true;
			else if (req.query[key] + "".toLowerCase() === "false")
				req.query[key] = false;
		});

		const { query: geoserviceParams } = req;
		const { resultRecordCount, returnCountOnly } = geoserviceParams;
		const config = koopConfig["databricks"];
		const sourceId = req.params.id || "lakebase";
		const sourceConfig = config.sources[sourceId];

		if (!sourceConfig) {
			throw new Error(`No source config found for id: "${sourceId}"`);
		}
		
		// Metadata-only request detection
		const isMetadataRequest =
			(Object.keys(geoserviceParams).length == 1 &&
				geoserviceParams.hasOwnProperty("f")) ||
			Object.keys(geoserviceParams).length == 0;
		const fetchSize = isMetadataRequest
			? 1
			: resultRecordCount || sourceConfig.maxRecordCountPerPage;

		// Get or create Lakebase connection pool
		const pool = await getLakebasePool(sourceConfig);

		const { sql, params } = buildSqlQuery(
			geoserviceParams,
			sourceConfig.idField,
			sourceConfig.geomOutColumn,
			sourceConfig.LAKEBASE_SCHEMA || "public",
			sourceConfig.LAKEBASE_TABLE || sourceConfig.properties.name,
			sourceConfig.dbWKID,
			fetchSize
		);
		console.log(`[${new Date().toISOString()}] getData query: ${sql}`, params);

		// Calculate extent for metadata requests
		let dbExtent = null;
		if (isMetadataRequest) {
			try {
				const extentSql = `SELECT ST_AsGeoJSON(ST_Extent(${sourceConfig.geomOutColumn})) AS extent FROM ${sourceConfig.LAKEBASE_SCHEMA || "public"}.${sourceConfig.LAKEBASE_TABLE || sourceConfig.properties.name}`;
				const extentResult = await pool.query(extentSql);
				if (extentResult.rows.length > 0 && extentResult.rows[0].extent) {
					dbExtent = getExtentFromGeoJson(
						JSON.parse(extentResult.rows[0].extent),
						sourceConfig.dbWKID
					);
				}
			} catch (extentErr) {
				console.warn(`[${new Date().toISOString()}] Failed to calculate extent:`, extentErr.message);
			}
		}

		const result = await pool.query(sql, params);
		const rows = result.rows;
		
		let geojson = { type: "FeatureCollection", features: [] };
		if (!rows || rows.length === 0) {
			return geojson;
		}

		if (returnCountOnly) {
			geojson.count = Number(rows[0].count);
		} else {
			geojson = translateToGeoJSON(rows, sourceConfig);
		}

		geojson.filtersApplied = generateFiltersApplied(
			geoserviceParams,
			sourceConfig.idField,
			sourceConfig.geomOutColumn
		);
		geojson.metadata = {
			...sourceConfig.properties,
			maxRecordCount: sourceConfig.maxRecordCountPerPage,
			idField: sourceConfig.idField,
			capabilities: "Query,Create,Update,Delete",
			...(dbExtent && { extent: dbExtent }),
		};
		geojson.crs = {
			type: `${sourceConfig.dbWKID}`,
			properties: {
				name: `urn:ogc:def:crs:EPSG::${sourceConfig.dbWKID}`,
			},
		};
		console.log(`[${new Date().toISOString()}] getData response: ${geojson.features?.length || 0} features`);
		return geojson;
		} catch (error) {
			console.error(`[${new Date().toISOString()}] getData error:`, error.message);
			throw error;
		}
	}

	async editData(req) {
		const config = koopConfig["databricks"];
		const sourceId = req.params.id || "lakebase";
		const sourceConfig = config.sources[sourceId];

		if (!sourceConfig) {
			throw new Error(`No source config found for id: "${sourceId}"`);
		}

		const pool = await getLakebasePool(sourceConfig);

		// Normalize the request body
		const { edits, editLevel } = this.#normalizeRequestedEdits(req.body);

		const editCounts = edits.map(e => `adds=${e.adds?.length || 0}, updates=${e.updates?.length || 0}, deletes=${e.deletes?.length || 0}`);
		console.log(`[${new Date().toISOString()}] editData request (${editLevel}-level): ${editCounts.join(" | ")}`);

		let allResults = [];

		for (const editSet of edits) {
			let applyEditsResponse = {
				addResults: [],
				updateResults: [],
				deleteResults: [],
			};

			if (editSet.adds) {
				applyEditsResponse.addResults = await insertRows(editSet.adds, pool, sourceConfig);
			}
			if (editSet.updates) {
				applyEditsResponse.updateResults = await updateRows(editSet.updates, pool, sourceConfig);
			}
			if (editSet.deletes) {
				applyEditsResponse.deleteResults = await deleteRows(editSet.deletes, pool, sourceConfig);
			}

			if (editLevel === "service") {
				allResults.push({ id: editSet.id, ...applyEditsResponse });
			} else {
				allResults.push(applyEditsResponse);
			}
		}

		const response = editLevel === "service" ? allResults : allResults[0];
		console.log(`[${new Date().toISOString()}] editData complete`);
		return response;
	}

	#normalizeRequestedEdits(body) {
		if (body.edits) {
			const edits = typeof body.edits === "string" ? JSON.parse(body.edits) : body.edits;
			return { edits, editLevel: "service" };
		}

		const editSet = {};
		if (body.adds) {
			editSet.adds = typeof body.adds === "string" ? JSON.parse(body.adds) : body.adds;
		}
		if (body.updates) {
			editSet.updates = typeof body.updates === "string" ? JSON.parse(body.updates) : body.updates;
		}
		if (body.deletes) {
			const deletes = typeof body.deletes === "string" ? body.deletes : String(body.deletes);
			editSet.deletes = Array.isArray(body.deletes)
				? body.deletes
				: deletes.split(",").map((item) => Number(item.trim()));
		}
		return { edits: [editSet], editLevel: "layer" };
	}
}

module.exports = Model;

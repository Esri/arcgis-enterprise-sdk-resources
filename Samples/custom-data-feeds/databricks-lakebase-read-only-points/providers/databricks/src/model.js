const koopConfig = require("config");
const { getLakebasePool, shutdownPool } = require("./modules/lakebase-pool");
const {
	translateToGeoJSON,
	buildSqlQuery,
	generateFiltersApplied,
	getExtentFromGeoJson,
} = require("./modules");

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

	async getData(req, callback) {
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
			const sourceId = req.params.id;
			const sourceConfig = config.sources[sourceId];

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
			console.log(sql, params);

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
					console.warn("Failed to calculate extent:", extentErr.message);
				}
			}

			const result = await pool.query(sql, params);
			const rows = result.rows;

			let geojson = { type: "FeatureCollection", features: [] };
			if (!rows || rows.length === 0) {
				return callback(null, geojson);
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
				...(dbExtent && { extent: dbExtent }),
			};
			geojson.crs = {
				type: `${sourceConfig.dbWKID}`,
				properties: {
					name: `urn:ogc:def:crs:EPSG::${sourceConfig.dbWKID}`,
				},
			};
			callback(null, geojson);
		} catch (error) {
			console.error(error);
			callback(null, { type: "FeatureCollection", features: [] });
		}
	}
}

module.exports = Model;

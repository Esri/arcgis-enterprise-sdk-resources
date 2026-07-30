const cdfConfig = require("./config/default.json");
const { getLakebasePool, shutdownPool } = require("./modules/lakebase-pool");
const {
	translateToGeoJSON,
	buildSqlQuery,
	generateFiltersApplied,
	getExtentFromGeoJson,
} = require("./modules");
const { insertRows, updateRows, deleteRows } = require("./helpers");

class Model {
	constructor() {
		const sourceConfig = cdfConfig.databricks.sources.lakebase;
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

	async getMetadata(req) {
		const config = cdfConfig["databricks"];
		const sourceId = req?.params?.id || "lakebase";
		const sourceConfig = config.sources[sourceId];

		if (!sourceConfig) {
			throw new Error(`No source config found for id: "${sourceId}"`);
		}

		return {
			idField: sourceConfig.idField,
			inputCrs: sourceConfig.dbWKID
		};
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
		const { resultRecordCount, returnCountOnly, returnDistinctValues } = geoserviceParams;
		const config = cdfConfig["databricks"];
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

			// DISTINCT queries don't include the idField, but the framework's
			// winnow hashes properties[idField] via fnv-plus which crashes on
			// undefined. Add a synthetic id so the hash gets a valid value.
			if (returnDistinctValues) {
				const idField = sourceConfig.idField;
				geojson.features.forEach((f, i) => {
					f.properties[idField] = i + 1;
				});
			}
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

	

	async editData(req, editData) {
		const config = cdfConfig["databricks"];
		const sourceId = req.params.id || "lakebase";
		const sourceConfig = config.sources[sourceId];

		if (!sourceConfig) {
			throw new Error(`No source config found for id: "${sourceId}"`);
		}

		const pool = await getLakebasePool(sourceConfig);

		// `editData` is provided by the CDF framework already normalized to GeoJSON
		// and reprojected into the layer's native CRS (see getMetadata `inputCrs`).
		const { adds, updates, deletes } = editData;

		console.log(
			`[${new Date().toISOString()}] editData request: adds=${adds?.length || 0}, updates=${updates?.length || 0}, deletes=${deletes?.length || 0}`
		);

		const applyEditsResponse = {
			addResults: [],
			updateResults: [],
			deleteResults: [],
		};

		if (adds) {
			applyEditsResponse.addResults = await insertRows(adds, pool, sourceConfig);
		}
		if (updates) {
			applyEditsResponse.updateResults = await updateRows(updates, pool, sourceConfig);
		}
		if (deletes) {
			applyEditsResponse.deleteResults = await deleteRows(deletes, pool, sourceConfig);
		}

		console.log(`[${new Date().toISOString()}] editData complete`);
		return applyEditsResponse;
	}
}

module.exports = Model;

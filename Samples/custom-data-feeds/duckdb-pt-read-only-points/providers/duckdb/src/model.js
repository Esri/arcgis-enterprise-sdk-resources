/* Copyright 2025 Esri

Licensed under the Apache License Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

const koopConfig = require("config");
const duckdb = require("duckdb");
const fs = require("fs");
const {
	translateToGeoJSON,
	validateConfig,
	buildSqlQuery,
	generateFiltersApplied,
	getExtentFromGeoJson,
} = require("./modules");

class Model {
	constructor(koop) {
		try {
			validateConfig(koopConfig);
		} catch (error) {
			throw error;
		}
		this.db = new duckdb.Database(":memory:");
		const deltaConfig = koopConfig.duckdb.sources.deltaTable;
		const minioConfig = koopConfig.duckdb.sources.minio;
		const localParquetConfig = koopConfig.duckdb.sources.localParquet;

		var localParquetCreateClause = ``;
		if (localParquetConfig) {
			localParquetCreateClause = `CREATE TABLE ${localParquetConfig.properties.name} AS 
						SELECT * EXCLUDE ${localParquetConfig.WKBColumn}, 
						ST_GeomFromWKB(CAST(${localParquetConfig.WKBColumn} AS BLOB)) AS ${localParquetConfig.geomOutColumn}, 
						CAST(row_number() OVER () AS INTEGER) AS ${localParquetConfig.idField}
						FROM read_parquet('${localParquetConfig.path}/*.parquet', hive_partitioning = true);`;
		}

		var minioCreateClause = ``;
		if (minioConfig) {
			var secretClause = `INSTALL 'httpfs';
								LOAD 'httpfs';
								SET s3_region='${minioConfig.s3Region}';
								SET s3_url_style='path';
								SET s3_endpoint='${minioConfig.s3Url}';
								SET s3_access_key_id='${minioConfig.s3AccessKeyId}';
								SET s3_secret_access_key='${minioConfig.s3Secret}';
								SET s3_use_ssl = false;`;
			minioCreateClause = `${secretClause}
						CREATE TABLE ${minioConfig.properties.name} AS 
						SELECT * EXCLUDE ${minioConfig.WKBColumn}, 
						ST_GeomFromWKB(CAST(${minioConfig.WKBColumn} AS BLOB)) AS ${minioConfig.geomOutColumn}, 
						CAST(row_number() OVER () AS INTEGER) AS ${minioConfig.idField}
						FROM read_parquet('s3://${minioConfig.s3BucketName}/${minioConfig.properties.name}.parquet/*.parquet', hive_partitioning = true);`;
		}

		var deltaCreateClause = ``;
		if (deltaConfig) {
			var secretClause = `INSTALL delta;LOAD delta;
								INSTALL azure;LOAD azure;
								CREATE SECRET deltatableconn (TYPE AZURE, CONNECTION_STRING 'abfss://${deltaConfig.azureStorageConnStr}');`;
			deltaCreateClause = `${secretClause}
						CREATE TABLE ${deltaConfig.properties.name} AS 
						SELECT * EXCLUDE ${deltaConfig.WKBColumn}, 
						ST_GeomFromWKB(CAST(${deltaConfig.WKBColumn} AS BLOB)) AS ${deltaConfig.geomOutColumn}, 
						CAST(row_number() OVER () AS INTEGER) AS ${deltaConfig.idField}
						FROM delta_scan('${deltaConfig.deltaUrl}');`;
		}

		const initQuery = `INSTALL spatial; LOAD spatial; 
						${deltaCreateClause}
						${minioCreateClause}
						${localParquetCreateClause}`;
		this.db.all(initQuery, function (err, res) {
			if (err) {
				console.error(err);
			}
			console.log(`🦆 DuckDB initialized with ${res[0].Count} rows 🦆`);
		});
	}

	getData(req, callback) {
		try {
			// convert bools from strings
			Object.keys(req.query).forEach((key) => {
				if (req.query[key] + "".toLowerCase() === "true") req.query[key] = true;
				else if (req.query[key] + "".toLowerCase() === "false")
					req.query[key] = false;
			});
			const { query: geoserviceParams } = req;
			// TODO: speed up returnIdsOnly with large datasets
			const { resultRecordCount, returnCountOnly } = geoserviceParams;
			const config = koopConfig["duckdb"];
			const sourceId = req.params.id;
			const sourceConfig = config.sources[sourceId];
			// only return back one row for metadata purposes
			const isMetadataRequest =
				(Object.keys(geoserviceParams).length == 1 &&
					geoserviceParams.hasOwnProperty("f")) ||
				Object.keys(geoserviceParams).length == 0;
			const fetchSize = isMetadataRequest
				? 1
				: resultRecordCount || sourceConfig.maxRecordCountPerPage;

			const sqlQuery = buildSqlQuery(
				geoserviceParams,
				sourceConfig.idField,
				sourceConfig.geomOutColumn,
				sourceConfig.properties.name,
				sourceConfig.dbWKID,
				fetchSize
			);

			var dbExtent = null;
			if (isMetadataRequest) {
				const extentQuery = `SELECT ST_AsGeoJSON(ST_Envelope_Agg(${sourceConfig.geomOutColumn})) AS extent FROM ${sourceConfig.properties.name}`;
				this.db.all(extentQuery, (err, rows) => {
					if (err) {
						console.error(err);
						return;
					}
					dbExtent = getExtentFromGeoJson(JSON.parse(rows[0]["extent"]), sourceConfig.dbWKID);
				});
			}

			this.db.all(sqlQuery, (err, rows) => {
				let geojson = { type: "FeatureCollection", features: [] };
				if (err) {
					console.error(err);
					callback(null, geojson);
				}
				if (rows.length == 0) {
					return callback(null, geojson);
				}
				if (returnCountOnly) {
					geojson.count = Number(rows[0]["count(1)"]);
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
			});
		} catch (error) {
			console.error(error);
			callback(null, { type: "FeatureCollection", features: [] });
		}
	}
}

module.exports = Model;

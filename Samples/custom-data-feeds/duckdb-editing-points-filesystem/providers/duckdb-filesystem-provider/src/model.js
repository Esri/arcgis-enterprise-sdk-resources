const localConfig = require("config");
const duckdb = require("duckdb");
const path = require("path");
const {
	translateToGeoJSON,
	validateConfig,
	buildSqlQuery,
	generateFiltersApplied,
	getExtentFromGeoJson,
} = require("./modules");
const { 
	normalizeRequestedEdits, 
	syncWALandDB, 
	insertRows, 
	updateRows, 
	deleteRows
} = require('./helpers');

class Model {
	constructor({logger}) {
    this.logger = logger;
		try {
			validateConfig(localConfig);
		} catch (error) {
			throw error;
		}
    this.localParquetConfig = localConfig.duckdbfs.sources.localParquet;
    const currentDir = __dirname;
		const targetFilePath = path.join(currentDir, this.localParquetConfig.dbPath);
		this.dbPath = path.normalize(targetFilePath);
		this.db = null;
		this.openDB();
	}

	// Open database connection safely
	async openDB() {
		if (!this.db) {
			this.db = new duckdb.Database(this.dbPath);
			await new Promise((resolve, reject) => {
				this.db.run("INSTALL spatial; LOAD spatial;", (err) => {
					if (err) {
						this.logger.severe("Error initializing DuckDB:", err);
						this.db = null;
						reject(err);
					} else {
						resolve();
					}
				});
			});
		}
	}

	async editData(req) {

		const rollbackOnFailure = req.body.rollbackOnFailure !== undefined ? 
		(typeof req.body.rollbackOnFailure === 'string' ? JSON.parse(req.body.rollbackOnFailure) : req.body.rollbackOnFailure) : false;
		
		const collection = normalizeRequestedEdits(req.body);
		let applyEditsResponse = {
			addResults: [],
			updateResults: [],
			deleteResults: []
		};
	
		if (rollbackOnFailure === true) {
			try {
				
				await startTransaction(this.db); // Start the transaction
				// this.logger.info('Starting transaction.')

				for (const layer of collection.edits) {
					if (layer.id !== undefined && layer.id !== null) applyEditsResponse.id = layer.id;
					if (layer.adds) applyEditsResponse.addResults = await insertRows(layer.adds, this.db, this.localParquetConfig, true);
					if (layer.updates) applyEditsResponse.updateResults = await updateRows(layer.updates, this.db, this.localParquetConfig, true);
					if (layer.deletes) applyEditsResponse.deleteResults = await deleteRows(layer.deletes, this.db, this.localParquetConfig, true);
				}

				const hasFailedEdit = hasSuccessFalse([applyEditsResponse]);
				if (hasFailedEdit) {
					await rollbackTransaction(this.db); // Rollback the transaction on error
					throw new Error("An error occurred: There is at least one operation that failed.");
				}
				else {
					// an error above will not trigger the commit
					await commitTransaction(this.db); // Commit the transaction
					this.logger.info('Committing transaction')

				}
			} catch (error) {
				this.logger.info(`${error} Transaction rolled back.`)

				// if rolling back, we will transform the appropriate response
				let transformedResponse = {}
				for (const category of ['addResults', 'updateResults', 'deleteResults']) {
					
					if (applyEditsResponse[category]) {
						transformedResponse[category] = applyEditsResponse[category].map(item => ({
							success: false,
							objectId: item.objectId,
							error: {
								code: 1003,
								description: "Operation rolled back."
							}
						}));
					}
				}
				applyEditsResponse = transformedResponse;
			}
	
		} else {
			try {
				for (const layer of collection.edits) {
					if (layer.id !== undefined && layer.id !== null) applyEditsResponse.id = layer.id;
					if (layer.adds) applyEditsResponse.addResults = await insertRows(layer.adds, this.db, this.localParquetConfig, false);
					if (layer.updates) applyEditsResponse.updateResults = await updateRows(layer.updates, this.db, this.localParquetConfig, false);
					if (layer.deletes) applyEditsResponse.deleteResults = await deleteRows(layer.deletes, this.db, this.localParquetConfig, false);
				}
				await syncWALandDB(this.db);
			} catch (error) {
				this.logger.debug('Failed to complete edits.')
			}
		}
	
	
		if (collection.editLevel === 'service') {
			return [applyEditsResponse];
		}
	
		return applyEditsResponse;
	}

	async getData(req, callback) {
		try {
			
			//Merge the latest changes in DB
			await syncWALandDB(this.db);

			// convert bools from strings
			Object.keys(req.query).forEach((key) => {
				if (req.query[key] + "".toLowerCase() === "true") req.query[key] = true;
				else if (req.query[key] + "".toLowerCase() === "false")
					req.query[key] = false;
			});

			const { query: geoserviceParams } = req;
			const { resultRecordCount, returnCountOnly } = geoserviceParams;
			const config = localConfig["duckdbfs"];
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
						this.logger.debug(err);
						return;
					}
					dbExtent = getExtentFromGeoJson(JSON.parse(rows[0]["extent"]), sourceConfig.dbWKID);
				});
			}

			this.db.all(sqlQuery, (err, rows) => {
				let geojson = { type: "FeatureCollection", features: [] };
				if (err) {
					this.logger.debug(err);
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
          			templates: [
						{
							"name": "Edit Template",
							"description": "Template for editing features",
							"drawingTool": "esriFeatureEditToolPoint",
							"prototype": {
								"attributes": {
									"VendorID":"1",
									"tpep_pickup_datetime":"2/10/2025, 1:28 AM",
									"tpep_dropoff_datetime":"2/10/2025, 1:39 AM",
									"passenger_count":"2",
									"trip_distance":".70",
									"pickup_longitude":"-73.986862182617188",
									"pickup_latitude":"40.721054077148438",
									"RatecodeID":"1",
									"store_and_fwd_flag":"N",
									"dropoff_longitude":"-73.99725341796875",
									"dropoff_latitude":"40.725048065185547",
									"payment_type":"1",
									"fare_amount":"7",
									"extra":"0.5",
									"mta_tax":"0.5",
									"tip_amount":"1.2",
									"tolls_amount":"0",
									"improvement_surcharge":"0.3",
									"total_amount":"9.5"
								}
							}
						}
					],
					fields: [
						{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "1",
							"editable": true,
							"domain": null,
							"name": "VendorID",
							"length": 128,
							"alias": "VendorID",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "2/10/2025, 1:28 AM",
							"editable": true,
							"domain": null,
							"name": "tpep_pickup_datetime",
							"length": 128,
							"alias": "Trip_Pickup_DateTime",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "2/10/2025, 1:39 AM",
							"editable": true,
							"domain": null,
							"name": "tpep_dropoff_datetime",
							"length": 128,
							"alias": "Trip_Dropoff_DateTime",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "2",
							"editable": true,
							"domain": null,
							"name": "passenger_count",
							"length": 128,
							"alias": "Passenger_Count",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "0.70",
							"editable": true,
							"domain": null,
							"name": "trip_distance",
							"length": 128,
							"alias": "Trip_Distance",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "-73.986862182617188",
							"editable": true,
							"domain": null,
							"name": "pickup_longitude",
							"length": 128,
							"alias": "Pickup_Longitude",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "40.721054077148438",
							"editable": true,
							"domain": null,
							"name": "pickup_latitude",
							"length": 128,
							"alias": "Pickup_Latitude",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "1",
							"editable": true,
							"domain": null,
							"name": "RatecodeID",
							"length": 128,
							"alias": "RatecodeID",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "N",
							"editable": true,
							"domain": null,
							"name": "store_and_fwd_flag",
							"length": 128,
							"alias": "Store_and_FWD_Flag",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "-73.99725341796875",
							"editable": true,
							"domain": null,
							"name": "dropoff_longitude",
							"length": 128,
							"alias": "Dropoff_Longitude",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "40.725048065185547",
							"editable": true,
							"domain": null,
							"name": "dropoff_latitude",
							"length": 128,
							"alias": "Dropoff_Latitude",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "1",
							"editable": true,
							"domain": null,
							"name": "payment_type",
							"length": 128,
							"alias": "Payment_Type",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "7",
							"editable": true,
							"domain": null,
							"name": "fare_amount",
							"length": 128,
							"alias": "Fare_Amount",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "0.5",
							"editable": true,
							"domain": null,
							"name": "extra",
							"length": 128,
							"alias": "Extra",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "0.5",
							"editable": true,
							"domain": null,
							"name": "mta_tax",
							"length": 128,
							"alias": "MTA_Tax",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "1.2",
							"editable": true,
							"domain": null,
							"name": "tip_amount",
							"length": 128,
							"alias": "Tip_Amount",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "0",
							"editable": true,
							"domain": null,
							"name": "tolls_amount",
							"length": 128,
							"alias": "Tolls_Amount",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "0.3",
							"editable": true,
							"domain": null,
							"name": "improvement_surcharge",
							"length": 128,
							"alias": "Improvement_Surcharge",
							"type": "esriFieldTypeString"
									},
									{
							"sqlType": "sqlTypeNVarchar",
							"nullable": true,
							"defaultValue": "9.5",
							"editable": true,
							"domain": null,
							"name": "total_amount",
							"length": 128,
							"alias": "Total_Amount",
							"type": "esriFieldTypeString"
									}
					]
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
			this.logger.debug(error);
			callback(null, { type: "FeatureCollection", features: [] });
		}
	}
}

// Properly rollback the DB transaction
async function rollbackTransaction(dbConn) {
	if (!dbConn) return;
	try {
		await new Promise((resolve, reject) => {
			dbConn.run("ROLLBACK;", (err) => {
				if (err) {
					this.logger.info("Failed to start transaction:", err);
					reject(err);
				} else {
					resolve();
				}
			});
		});
	} catch (error) {
		this.logger.info("Error during starting DB transaction:", error);
	}
}

// Properly start the database transaction
async function startTransaction(dbConn) {
	if (!dbConn) {
		return;
	}
	try {
		await new Promise((resolve, reject) => {
			dbConn.run("BEGIN TRANSACTION;", (err) => {
				if (err) {
					console.error("Failed to start transaction:", err);
					reject(err);
				} else {
					resolve();
				}
			});
		});
	} catch (error) {
		console.error("Error during starting DB transaction:", error);
	}
}


// 
async function commitTransaction(dbConn) {
	if (!dbConn) return;
	try {
		await new Promise((resolve, reject) => {
			dbConn.run("COMMIT;", (err) => {
				if (err) {
					console.error("Failed to commit transaction:", err);
					reject(err);
				} else {
					resolve();
				}
			});
		});
	} catch (error) {
		console.error("Error during starting DB transaction:", error);
	}
}


function hasSuccessFalse(results) {
    for (const result of results) {
        for (const category of ['addResults', 'updateResults', 'deleteResults']) {
            for (const item of result[category]) {
                if (item.success === false) {
                    return true; // Found a false success
                }
            }
        }
    }
    return false; // No false success found
}
module.exports = Model;

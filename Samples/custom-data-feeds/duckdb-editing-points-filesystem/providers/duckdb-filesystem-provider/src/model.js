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
const { normalizeRequestedEdits, insertRows, updateRows, deleteRows } = require('./helpers');

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
    // this.conn = this.db.connect();
	}

	// Open database connection safely
	async openDB() {
		if (!this.db) {
			this.db = new duckdb.Database(this.dbPath);
			console.log("DuckDB connection opened.");
			await new Promise((resolve, reject) => {
				this.db.run("INSTALL spatial; LOAD spatial;", (err) => {
					if (err) {
						console.error("Error initializing DuckDB:", err);
						this.db = null;
						reject(err);
					} else {
						resolve();
					}
				});
			});
		}
	}

	// Properly close the database connection (Not applicable for file system)
	async closeDB() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("checkpoint;", (err) => {
					if (err) {
						console.error("Error running CHECKPOINT before read:", err);
						reject(err);
					} else {
						console.log("CHECKPOINT executed. Ensuring latest data is visible.");
						resolve();
					}
				});
			});

		} catch (error) {
			console.error("Error during closeDB:", error);
		}
	}

	// Properly merge the database changes
	async syncDB() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("checkpoint;", (err) => {
					if (err) {
						console.error("Error running CHECKPOINT before read:", err);
						reject(err);
					} else {
						//console.log("CHECKPOINT executed. Ensuring latest data is visible.");
						resolve();
					}
				});
			});
		} catch (error) {
			console.error("Error during running CHECKPOINT:", error);
		}
	}

	// Properly start the database transaction
	async startTransaction() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("BEGIN TRANSACTION;", (err) => {
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

	// Properly start the DB transaction
	async commitTransaction() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("COMMIT;", (err) => {
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

	// Properly rollback the DB transaction
	async rollbackTransaction() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("ROLLBACK;", (err) => {
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

	// Properly merge the database changes
	async syncDB() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("checkpoint;", (err) => {
					if (err) {
						console.error("Error running CHECKPOINT before read:", err);
						reject(err);
					} else {
						//console.log("CHECKPOINT executed. Ensuring latest data is visible.");
						resolve();
					}
				});
			});
		} catch (error) {
			console.error("Error during running CHECKPOINT:", error);
		}
	}

	// Properly start the database transaction
	async startTransaction() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("BEGIN TRANSACTION;", (err) => {
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

	// Properly start the DB transaction
	async commitTransaction() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("COMMIT;", (err) => {
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

	// Properly rollback the DB transaction
	async rollbackTransaction() {
		if (!this.db) return;
		try {
			await new Promise((resolve, reject) => {
				this.db.run("ROLLBACK;", (err) => {
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

	async editData(req) {

		// quick way to enure they are bools and not undefined; defaults to false if they are 
		const rollbackOnFailure = req.query.rollbackOnFailure !== undefined ? 
    		(typeof req.query.rollbackOnFailure === 'string' ? JSON.parse(req.query.rollbackOnFailure) : req.query.rollbackOnFailure) : false;

		const returnEditResults = req.query.returnEditResults !== undefined ? 
    		(typeof req.query.returnEditResults === 'string' ? JSON.parse(req.query.returnEditResults) : req.query.returnEditResults) : false;


		// add logic to normalize layer-level or service-level requests
		const collection = normalizeRequestedEdits(req.body);

    	//Create an empty response object
		let applyEditsResponse = {
			addResults: [],
			updateResults: [],
			deleteResults: []
		};

		if (rollbackOnFailure === true) {

			try {
	
				  await this.startTransaction();
	
				for (const layer of collection.edits) {
					if (layer.id !== undefined && layer.id !== null) applyEditsResponse.id = layer.id
					  
					if (layer.adds) applyEditsResponse.addResults = await insertRows(layer.adds, this.db, this.localParquetConfig, true);
	
					if (layer.updates) applyEditsResponse.updateResults = await updateRows(layer.updates, this.db, this.localParquetConfig, true);
	
					if (layer.deletes) applyEditsResponse.deleteResults = await deleteRows(layer.deletes, this.db, this.localParquetConfig, true);
				
					if (layer.id) applyEditsResponse.id = layer.id
					
				}
	
				await this.commitTransaction();
			} catch (error) {

				this.logger.debug(`Transaction failed: ${error.message}`);
				
				await this.rollbackTransaction();

				if (returnEditResults === false) return applyEditsResponse = {"success": false};
			}

			if (returnEditResults === false) return applyEditsResponse = {"success": true};

		} else {

			try {
  
			  for (const layer of collection.edits) {
					if (layer.id !== undefined && layer.id !== null) applyEditsResponse.id = layer.id
					
					if (layer.adds) applyEditsResponse.addResults = await insertRows(layer.adds, this.db, this.localParquetConfig, false);
	
					if (layer.updates) applyEditsResponse.updateResults = await updateRows(layer.updates, this.db, this.localParquetConfig, false);
	
					if (layer.deletes) applyEditsResponse.deleteResults = await deleteRows(layer.deletes, this.db, this.localParquetConfig, false);
				
			  }
  
		  } catch (error) {

			  this.logger.debug(`edits failed: ${error.message}`);

		  }

		}
		
    	await this.syncDB();
		
		if (collection.editLevel === 'service') {
			return [applyEditsResponse];
		  }
    
		return applyEditsResponse;
	}

	async getData(req, callback) {
		try {
			
			//Merge the latest changes in DB
			await this.syncDB();

			// convert bools from strings
			Object.keys(req.query).forEach((key) => {
				if (req.query[key] + "".toLowerCase() === "true") req.query[key] = true;
				else if (req.query[key] + "".toLowerCase() === "false")
					req.query[key] = false;
			});
			const { query: geoserviceParams } = req;
			// TODO: speed up returnIdsOnly with large datasets
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
					"fields": [
					  {
              "sqlType": "sqlTypeBigInt",
              "nullable": true,
              "defaultValue": null,
              "editable": false,
              "domain": null,
              "name": "OBJECTID",
              "alias": "OBJECTID",
              "type": "esriFieldTypeOID"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "VendorID",
              "length": 128,
              "alias": "Vendor ID",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "tpep_pickup_datetime",
              "length": 128,
              "alias": "Trip Pickup DateTime",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "tpep_dropoff_datetime",
              "length": 128,
              "alias": "Trip Dropoff DateTime",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "passenger_count",
              "length": 128,
              "alias": "Passenger Count",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "trip_distance",
              "length": 128,
              "alias": "Trip Distance",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "pickup_longitude",
              "length": 128,
              "alias": "Pickup Longitude",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "pickup_latitude",
              "length": 128,
              "alias": "Pickup Latitude",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "RatecodeID",
              "length": 128,
              "alias": "Ratecode ID",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "store_and_fwd_flag",
              "length": 128,
              "alias": "Store and FWD Flag",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "dropoff_longitude",
              "length": 128,
              "alias": "Dropoff Longitude",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "dropoff_latitude",
              "length": 128,
              "alias": "Dropoff Latitude",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "payment_type",
              "length": 128,
              "alias": "Payment Type",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "fare_amount",
              "length": 128,
              "alias": "Fare Amount",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
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
              "editable": true,
              "domain": null,
              "name": "mta_tax",
              "length": 128,
              "alias": "MTA Tax",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "tip_amount",
              "length": 128,
              "alias": "Tip Amount",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "tolls_amount",
              "length": 128,
              "alias": "Tolls Amount",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "improvement_surcharge",
              "length": 128,
              "alias": "Improvement Surcharge",
              "type": "esriFieldTypeString"
					  },
					  {
              "sqlType": "sqlTypeNVarchar",
              "nullable": true,
              "editable": true,
              "domain": null,
              "name": "total_amount",
              "length": 128,
              "alias": "Total Amount",
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
			console.error(error);
			callback(null, { type: "FeatureCollection", features: [] });
		}
	}
}

module.exports = Model;

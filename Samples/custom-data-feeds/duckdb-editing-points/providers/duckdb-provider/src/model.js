fs = require("fs");
const path = require("path");
const duckdb = require("duckdb");
const localConfig = require("config");
const { 
  normalizeRequestedEdits,
  insertRows,
  updateRows,
  deleteRows,
  syncWALandDB

} = require('./helpers');
const {
	translateToGeoJSON,
	validateConfig,
	buildSqlQuery,
	generateFiltersApplied,
	getExtentFromGeoJson,
} = require("./modules");

class Model {
  constructor({logger}) {
    this.logger = logger;
		try {
			validateConfig(localConfig);
		} catch (error) {
			throw error;
		}
		try {
			this.DFSConfig = localConfig.duckdb.sources.localParquet;
		} catch (error) {
			logger.error("Error reading config file:", error);
      throw error;
		}
    this.parquetPath = path.join(this.DFSConfig.parquetPath);

    // initilize database and create a new connection
		this.db = new duckdb.Database(":memory:"); // Use in-memory DuckDB
    this.conn = this.db.connect();
		logger.info("DuckDB initialized in memory.");
		// Load data from Parquet file into memory
		this.loadParquetData();
	}
  
  // Open a new connection per transaction
	async getDBConnection() {
		return new Promise((resolve, reject) => {
			const conn = this.db.connect();
			if (conn) {
				resolve(conn);
			} else {
				reject(new Error("Failed to create a new DuckDB connection"));
			}
		});
	}

  async loadParquetData() {
    
    try {
      await new Promise((resolve, reject) => {
        const localParquetCreateClause = `CREATE TABLE ${this.DFSConfig.properties.name} AS 
            SELECT * EXCLUDE ${this.DFSConfig.WKBColumn}, 
            ST_GeomFromWKB(CAST(${this.DFSConfig.WKBColumn} AS BLOB)) AS ${this.DFSConfig.geomOutColumn}, 
            CAST(row_number() OVER () AS INTEGER) AS ${this.DFSConfig.idField}
            FROM read_parquet('${this.parquetPath}/*.parquet', hive_partitioning = true);`;

        const initQuery = `INSTALL spatial; LOAD spatial; 
            ${localParquetCreateClause}`;

        this.conn.run(initQuery, (err) => {
          if (err) {
            this.logger.error("Error loading Parquet file into memory:", err);
            reject(err);
          } else {
            this.logger.info("Parquet file successfully loaded into memory.");
            resolve();
          }
        });
      });
    } catch (error) {
      this.logger.error("Unexpected error loading Parquet data:", error);
    }
  }

  async editData(req) {

		// add logic to normalize layer-level or service-level requests
		const collection = normalizeRequestedEdits(req.body);

		//Create an empty response object
		let applyEditsResponse = {
			addResults: [],
			updateResults: [],
			deleteResults: []
		};

		try {
      
      for (const layer of collection.edits) {
        if (layer.id !== undefined && layer.id !== null) {
          applyEditsResponse.id = layer.id
        }
        if (layer.adds) {

          // call an insert function and add the results to the final response object
          applyEditsResponse.addResults = await insertRows(layer.adds, this.conn);
          
        }
        if (layer.updates) {

          // call the update function and add the results to the final response object
          applyEditsResponse.updateResults = await updateRows(layer.updates, this.conn);
          
        }
        if (layer.deletes) {

          // call the delete function and add teh results to the final response object
          applyEditsResponse.deleteResults = await deleteRows(layer.deletes, this.conn);
          
        }
      }
		} catch (error) {
			this.logger.debug(`Feature editing failure: ${error.message}`);
		}

    // function ensures that the write along log is synced with database
    await syncWALandDB(this.conn);

		if (collection.editLevel === 'service') {
      return [applyEditsResponse];
    }
		return applyEditsResponse;
	}


  async getData(req, callback) {

		try {
    
      // function ensures that the write along log is synced with database
      await syncWALandDB(this.conn);

			// convert bools from strings
			Object.keys(req.query).forEach((key) => {
				if (req.query[key] + "".toLowerCase() === "true") req.query[key] = true;
				else if (req.query[key] + "".toLowerCase() === "false")
					req.query[key] = false;
			});
			const { query: geoserviceParams } = req;
			// TODO: speed up returnIdsOnly with large datasets
			const { resultRecordCount, returnCountOnly } = geoserviceParams;

			// only return back one row for metadata purposes
			const isMetadataRequest =
				(Object.keys(geoserviceParams).length == 1 &&
					geoserviceParams.hasOwnProperty("f")) ||
				Object.keys(geoserviceParams).length == 0;
			const fetchSize = isMetadataRequest
				? 1
				: resultRecordCount || this.DFSConfig.maxRecordCountPerPage;

			const sqlQuery = buildSqlQuery(
				geoserviceParams,
				this.DFSConfig.idField,
				this.DFSConfig.geomOutColumn,
				this.DFSConfig.properties.name,
				this.DFSConfig.dbWKID,
				fetchSize
			);

			var dbExtent = null;
      // logic for returning extent and database spatial reference
			if (isMetadataRequest) {
				const extentQuery = `SELECT ST_AsGeoJSON(ST_Envelope_Agg(${this.DFSConfig.geomOutColumn})) AS extent FROM ${this.DFSConfig.properties.name}`;
				
        this.conn.all(extentQuery, (err, rows) => {
					if (err) {
						this.logger.error(err);
						return;
					}
					dbExtent = getExtentFromGeoJson(JSON.parse(rows[0]["extent"]), this.DFSConfig.dbWKID);
				});
			}

      // "all" method invokes callback for the entire set of rows returned in the query
			this.conn.all(sqlQuery, (err, rows) => {
        this.logger.info('SQLQUERY' + sqlQuery);
				let geojson = { type: "FeatureCollection", features: [] };
				// if error, return an empty set of features
        if (err) {
					this.logger.error(err);
					callback(null, geojson);
				}

        // if no rows were found, return an empty set of features
				if (rows.length == 0) {
					return callback(null, geojson);
				}

        // if query contains 'returnCountOnly' only return the number of rows
				if (returnCountOnly) {
					geojson.count = Number(rows[0]["count(1)"]);
				} else {
					geojson = translateToGeoJSON(rows, this.DFSConfig);
				}

				geojson.filtersApplied = generateFiltersApplied(
					geoserviceParams,
					this.DFSConfig.idField,
					this.DFSConfig.geomOutColumn
				);
				geojson.metadata = {
					...this.DFSConfig.properties,
					maxRecordCount: this.DFSConfig.maxRecordCountPerPage,
					idField: this.DFSConfig.idField,
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
					type: `${this.DFSConfig.dbWKID}`,
					properties: {
						name: `urn:ogc:def:crs:EPSG::${this.DFSConfig.dbWKID}`,
					},
				};
				callback(null, geojson);
			});
		} catch (error) {
			this.logger.error(error);
			callback(null, { type: "FeatureCollection", features: [] });
		} 
	}
}

module.exports = Model

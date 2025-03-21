const localConfig = require("config");
const duckdb = require("duckdb");
const os = require("os");
const fs = require("fs");

const {
  translateToGeoJSON,
  buildSqlQuery,
  generateFiltersApplied,
  getExtentFromGeoJson,
} = require("./modules");

class Model {
  constructor(koop) {
    // This uses in memory instance of a DuckDB
    this.db = new duckdb.Database(":memory:");

    //Comment out abolve line and uncomment line of code below if you want to use file based instance of DuckDB
    // const dbPath = `${os.tmpdir()}/addressess.duckdb`;
    // if (fs.existsSync(dbPath)) {
    // 	// Delete the file
    // 	try {
    // 		fs.unlinkSync(dbPath); // Synchronous removal
    // 		console.log("DuckDB database file deleted successfully.");
    // 	} catch (error) {
    // 		console.error("Error deleting DuckDB database file:", error);
    // 	}
    // }  else {
    // 	console.log("Database file not found!");
    // }
    // this.db = new duckdb.Database(dbPath);

    const s3Config = localConfig.addressess.sources.awss3;

    //Only install if it's not already installed (prevents conflicts)
    const httpfsQuery = `
      SELECT COUNT(*) FROM duckdb_extensions() WHERE extension_name='httpfs' and installed=true;
    `;
    this.db.all(httpfsQuery, (err, res) => {
      if (err) {
        console.error("Error checking httpfs extension:", err);
        return;
      }
      if (res[0]["COUNT(*)"] === 0) {
        console.log("Installing httpfs Extension...");
        this.db.all(`INSTALL httpfs; LOAD httpfs`, function (err) {
          if (err) {
            console.error("Error installing httpfs extension:", err);
          } else {
            console.log("httpfs extension installed.");
          }
        });
      } else {
        console.log("httpfs extension already installed.");
      }
    });

    //Only install if it's not already installed (prevents conflicts)
    const initQuery = `
      SELECT COUNT(*) FROM duckdb_extensions() WHERE extension_name='spatial' and installed=true;
    `;

    this.db.all(initQuery, (err, res) => {
      if (err) {
        console.error("Error checking spatial extension:", err);
        return;
      }

      if (res[0]["COUNT(*)"] === 0) {
        console.log("Installing Spatial Extension...");
        this.db.all(`INSTALL spatial;`, function (err) {
          if (err) {
            console.error("Error installing spatial extension:", err);
          } else {
            console.log("Spatial extension installed.");
          }
        });
      } else {
        console.log("Spatial extension already installed.");
      }
    });
    
    var s3CreateClause = ``;
    if (s3Config) {
      var secretClause = `LOAD spatial;`;
        s3CreateClause =  `${secretClause}
          DROP TABLE IF EXISTS ${s3Config.properties.name};
            CREATE TABLE ${s3Config.properties.name} AS 
            SELECT
              CAST(row_number() OVER () AS INTEGER) AS OBJECTID,
              country,
              postcode,
              street,
              number,
              unit,
              CAST(address_levels[1].value AS VARCHAR(256)) AS address_level,
              postal_city,
              geometry
            FROM read_parquet('${s3Config.s3Url}', 
              filename=true, hive_partitioning=1)
            WHERE 
              bbox.xmin > ${s3Config.xmin} 
                AND bbox.xmax < ${s3Config.xmax}
              AND bbox.ymin > ${s3Config.ymin} 
                AND bbox.ymax < ${s3Config.ymax};`;
    }

    this.db.all(s3CreateClause, function (err, res) {
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

      // Retrieve geoservices parameters
      const { resultRecordCount, returnCountOnly } = geoserviceParams;
      const sourceConfig = localConfig.addressess.sources.awss3;

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
      
    // Handle metadata requests
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

    // Handle Query requests
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

const localConfig = require("config");
const proj4 = require('proj4');
const codes = require('@esri/proj-codes');
const objectidFieldName = localConfig.duckdb.sources.localParquet.idField;
const geometryColumnName = localConfig.duckdb.sources.localParquet.geomOutColumn;
const tableName = localConfig.duckdb.sources.localParquet.properties.name;

async function insertRows(adds, dbConn) {
    
    let addResults =[];

    for (const feature of adds) {
        const attributes = feature.attributes;
        const geometry = feature.geometry;
        
        // Ensure "OBJECTID" and "geometry" are not duplicated
        let columns = Object.keys(attributes).filter(col => col !== `${objectidFieldName}` && col !== `${geometryColumnName}`);
        columns.push(`${objectidFieldName}`, `${geometryColumnName}`); // Ensure correct order

        // Fetch next available OBJECTID
        const objectId = await new Promise((resolve, reject) => {
            const maxsql = `SELECT COALESCE(MAX(${objectidFieldName}), 0) + 1 AS next_id FROM ${tableName};`;
            dbConn.all(maxsql, (err, row) => {
                if (err || !row || !row[0]["next_id"]) {
                    this.logger.error(`Failed to fetch ${objectidFieldName}, using default value 1`);
                    resolve(1);
                } else {
                    resolve(row[0]["next_id"]);
                }
            });
        });

        // Validate and prepare geometry
        let geomValue = null;
        if (geometry && geometry.x !== undefined && geometry.y !== undefined) {
        if (geometry.spatialReference && geometry.spatialReference.wkid !== '4326') {
            // look up the code
            const crs = codes.lookup(geometry.spatialReference.wkid);
            // convert coordinates from what is currently in client to our data source crs
            const convertedCoordinates = proj4(crs.wkt,'EPSG:4326', [geometry.x, geometry.y]);
            // push the converted coordinates into the array 
            geometry.x = convertedCoordinates[0];
            geometry.y = convertedCoordinates[1];
        }
        geomValue = `SRID=4326;POINT(${geometry.x} ${geometry.y})`;
        } else {
            this.logger.warn("Missing or invalid geometry data. Skipping geometry insert.");
        }

        // Extract values in the correct order as columns
        const values = columns.map(col => {
            if (col === `${objectidFieldName}`) return objectId;
            if (col === `${geometryColumnName}`) return `ST_GeomFromText('${geomValue}')`; // Correct syntax
            return typeof attributes[col] === "string" ? `'${attributes[col].replace(/'/g, "''")}'` : attributes[col]; // Escaping quotes in strings
        });

        // Prepare direct SQL query (NO placeholders)
        const insertsql = `INSERT INTO ${tableName} (${columns.join(", ")}) VALUES (${values.join(", ")})`;
        
        // Execute insert operation with error handling
        await new Promise((resolve, reject) => {
            dbConn.run(insertsql, (err) => {
                if (err) {
                this.logger.error(`Failed to insert record: ${err.message}`);
                const errorresponse = {
                    "success": false,
                    "error": {
                    "code": 1017,
                    "description": "Internal error during object insert."
                    }
                }
                    applyEditsResponse.addResults.push(errorresponse)
                    reject(err);
                } else {
                    resolve();
                }
            });
        });
        const outputresponse = {
            "success": true,
            "OBJECTID": objectId
        }
        addResults.push(outputresponse)
    }

    return addResults;

}

module.exports = {
    insertRows
}
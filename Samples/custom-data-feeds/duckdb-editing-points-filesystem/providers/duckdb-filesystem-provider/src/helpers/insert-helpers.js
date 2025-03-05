const proj4 = require('proj4');
const codes = require('@esri/proj-codes');

async function insertRows(adds, dbConn, config, rollbackOnFailure) {
    const objectidFieldName = config.idField;
    const geometryColumnName = config.geomOutColumn;
    const tableName = config.properties.name;

    let addResults = [];

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
                const crs = codes.lookup(geometry.spatialReference.wkid);
                const convertedCoordinates = proj4(crs.wkt, 'EPSG:4326', [geometry.x, geometry.y]);
                geometry.x = convertedCoordinates[0];
                geometry.y = convertedCoordinates[1];
            }
            geomValue = `SRID=4326;POINT(${geometry.x} ${geometry.y})`;
        }

        // Extract values in the correct order as columns
        const values = columns.map(col => {
            if (col === `${objectidFieldName}`) return objectId;
            if (col === `${geometryColumnName}`) return `ST_GeomFromText('${geomValue}')`;
            return typeof attributes[col] === "string" ? `'${attributes[col].replace(/'/g, "''")}'` : attributes[col];
        });

        // Prepare direct SQL query (NO placeholders)
        const insertsql = `INSERT INTO ${tableName} (${columns.join(", ")}) VALUES (${values.join(", ")})`;

        // Execute insert operation with error handling
        try {
            await new Promise((resolve, reject) => {
                dbConn.run(insertsql, (err) => {
                    if (err) {
                        const errorresponse = {
                            "success": false,
                            "error": {
                                "code": 1017,
                                "description": "Internal error during object insert."
                            }
                        };
                        addResults.push(errorresponse);
                        if (rollbackOnFailure) {
                            reject(new Error("Insert operation failed")); // Throw error to trigger rollback
                        } else {
                            resolve(); // Continue without throwing an error
                        }
                    } else {
                        resolve();
                    }
                });
            });
        } catch (error) {
            throw error; // Rethrow the error to be caught in editData
        }

        const outputresponse = {
            "success": true,
            "OBJECTID": objectId
        };
        addResults.push(outputresponse);
    }

    return addResults;
}

module.exports = {
    insertRows
}
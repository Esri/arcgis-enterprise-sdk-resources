const proj4 = require('proj4');
const codes = require('@esri/proj-codes');

async function updateRows(updates, dbConn, config, rollbackOnFailure) {
    const objectidFieldName = config.idField;
    const geometryColumnName = config.geomOutColumn;
    const tableName = config.properties.name;

    let updateResults = [];

    for (const feature of updates) {
        const attributes = feature.attributes;
        const geometry = feature.geometry;

        // Ensure "OBJECTID" is not duplicated
        let columns = Object.keys(attributes).filter(col => col !== `${objectidFieldName}`);
        const objectId = attributes.OBJECTID;

        // Validate and prepare geometry
        let geomValue;
        if (geometry && geometry.x !== undefined && geometry.y !== undefined) {
            if (geometry.spatialReference && geometry.spatialReference.wkid !== '4326') {
                const crs = codes.lookup(geometry.spatialReference.wkid);
                const convertedCoordinates = proj4(crs.wkt, 'EPSG:4326', [geometry.x, geometry.y]);
                geometry.x = convertedCoordinates[0];
                geometry.y = convertedCoordinates[1];
            }
            geomValue = `SRID=4326;POINT(${geometry.x} ${geometry.y})`;
            columns.push(`${geometryColumnName}`); // Include geometry column if geometry is present
        }

        // Extract values in the correct order as columns
        const values = columns.map(col => {
            if (col === `${geometryColumnName}` && geomValue) {
                return `ST_GeomFromText('${geomValue}')`; // Include geometry value
            }
            return typeof attributes[col] === "string" ? `'${attributes[col].replace(/'/g, "''")}'` : attributes[col]; // Escaping quotes in strings
        });

        // Construct SET clause for UPDATE
        let updateSet = columns
            .map((col, index) => `${col} = ${values[index]}`) // Match column with its value
            .join(", ");

        // Prepare UPDATE SQL
        const updateSql = `UPDATE ${tableName} SET ${updateSet} WHERE ${objectidFieldName} = ${objectId}`;

        // Execute insert operation with error handling

        let outputResponse;
        await new Promise((resolve, reject) => {
            dbConn.run(updateSql, (err) => {
                if (err) {
                    const errorResponse = {
                        "success": false,
                        "objectId": objectId,
                        "error": {
                            "code": 1019,
                            "description": "Internal error during object update."
                        }
                    };
                    updateResults.push(errorResponse);
                    resolve(err); // Continue without throwing an error so we can see all the results; reject would stop process

                } else {
                    outputResponse = {
                        "success": true,
                        "objectId": objectId
                    };
                    updateResults.push(outputResponse);
                    resolve();
                }
            });
        });
    }

    return updateResults;
}

module.exports = {
    updateRows
};
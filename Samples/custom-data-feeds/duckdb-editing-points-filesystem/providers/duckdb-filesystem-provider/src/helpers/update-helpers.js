
async function updateRows (updates, dbConn, config, rollbackOnFailure) {
    const objectidFieldName = config.idField;
    const geometryColumnName = config.geomOutColumn;
    const tableName = config.properties.name;

    let updateResults = [];

    for (const feature of updates) {
        const attributes = feature.attributes;
        const geometry = feature.geometry;
        console.log(attributes)
        console.log(geometry)


        // Ensure "OBJECTID" and "geometry" are not duplicated
        let columns = Object.keys(attributes).filter(col => col !== `${objectidFieldName}` && col !== `${geometryColumnName}`);
        columns.push(`${objectidFieldName}`, `${geometryColumnName}`); // Ensure correct order

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
            console.warn("Missing or invalid geometry data. Skipping geometry insert.");
        }

        let objectId = attributes.OBJECTID

        // Extract values in the correct order as columns
        const values = columns.map(col => {
            if (col === `${geometryColumnName}`) return `ST_GeomFromText('${geomValue}')`; // Correct syntax
            return typeof attributes[col] === "string" ? `'${attributes[col].replace(/'/g, "''")}'` : attributes[col]; // Escaping quotes in strings
        });

        // Debugging logs to verify correctness
        console.log(`Columns: ${columns.length} -> ${columns}`);
        console.log(`Values: ${values.length} -> ${values}`);

        // Construct SET clause for UPDATE
        let updateSet = columns
            .filter(col => col !== `${objectidFieldName}`) // Don't update OBJECTID itself
            .map(col => `${col} = ${values[columns.indexOf(col)]}`) // Match column with its value
            .join(", ");

        // Prepare UPDATE SQL
        const updateSql = `UPDATE ${tableName} SET ${updateSet} WHERE ${objectidFieldName} = ${objectId}`;

        // Execute insert operation with error handling
        try {
            await new Promise((resolve, reject) => {
                dbConn.run(updateSql, (err) => {
                    if (err) {
                        const errorresponse = {
                            "success": false,
                            "error": {
                                "OBJECTID": objectId,
                                "code": 1019,
                                "description": "Internal error during object update."
                            }
                        };
                        updateResults.push(errorresponse);
                        reject(err); // Reject if problem occurs but still continue to next feature
                    } else {
                        resolve();
                    }
                });
            });
        } catch (error) {
            continue; // Continue to the next feature
        }
    
        const outputresponse = {
            "success": true,
            "objectid": objectId
        }
        updateResults.push(outputresponse)
    }

    return updateResults;

}

module.exports = {
    updateRows
}
async function deleteRows(deletes, dbConn, config, rollbackOnFailure) {
    const objectidFieldName = config.idField;
    const tableName = config.properties.name;

    let deleteResults = [];

    for (const objectId of deletes) {
        const deleteQuery = `DELETE FROM ${tableName} WHERE ${objectidFieldName} = ${objectId}`;
        // Execute update operation with error handling
        try {
            await new Promise((resolve, reject) => {
                dbConn.run(deleteQuery, (err) => {
                    if (err) {
                        const errorresponse = {
                            "success": false,
                            "error": {
                                "OBJECTID": objectId,
                                "code": 1018,
                                "description": "Internal error during object delete."
                            }
                        }
                        deleteResults.push(errorresponse)
                        reject(err); // If problem occurs, only reject the deletion of this single feature
                    } else {
                        resolve();
                    }
                });
            });
        } catch (error) {
            continue; // Continue to next feature that should be deleted
        }

        const outputresponse = {
          "success": true,
          "OBJECTID": objectId
        };
        deleteResults.push(outputresponse)
    }

    return deleteResults;
}

module.exports = {
    deleteRows
}
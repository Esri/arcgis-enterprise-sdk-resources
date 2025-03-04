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
                        addResults.push(errorresponse);
                        if (rollbackOnFailure) {
                            reject(new Error("Delete operation failed. Rolling back.")); // Throw error to trigger rollback
                        } else {
                            console.warn("Delete operation failed, continuing without rollback.");
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
        deleteResults.push(outputresponse)
    }

    return deleteResults;
}

module.exports = {
    deleteRows
}
async function deleteRows(deletes, dbConn, config, rollbackOnFailure) {
    const objectidFieldName = config.idField;
    const tableName = config.properties.name;

    let deleteResults = [];

    for (const objectId of deletes) {
        const deleteQuery = `DELETE FROM ${tableName} WHERE ${objectidFieldName} = ${objectId}`;
        // Execute update operation with error handling

            await new Promise((resolve, reject) => {
                dbConn.run(deleteQuery, (err) => {
                    if (err) {
                        const errorresponse = {
                            "success": false,
                            "objectId": objectId,
                            "error": {
                                "code": 1018,
                                "description": "Internal error during object delete."
                            }
                        }
                        addResults.push(errorresponse);
                        resolve(err);
                    } else {
                        const outputresponse = {
                            "success": true,
                            "objectId": objectId
                        };
                        deleteResults.push(outputresponse)
                        resolve();
                    }
                });
            });


    }

    return deleteResults;
}

module.exports = {
    deleteRows
}
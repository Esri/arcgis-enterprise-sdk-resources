async function syncWALandDB(dbConn) {

    // Sync the Write Along Log with the DB if any commits are still outstanding with "CHECKPOINT"
    await new Promise((resolve, reject) => {
        dbConn.run("CHECKPOINT;", (err) => {
            if (err) {
                reject(err);
            } else {
                resolve();
            }
        });
    });
}

module.exports = {
    syncWALandDB, 
}
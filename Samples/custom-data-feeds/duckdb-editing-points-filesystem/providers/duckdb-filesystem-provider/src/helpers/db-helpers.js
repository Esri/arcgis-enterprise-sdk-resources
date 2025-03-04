
    // // Open database connection safely
    // async initializDB() {
    //     if (!this.db) {
    //         this.db = new duckdb.Database(this.dbPath);
    //         console.log("DuckDB file system initialized");
    //         await new Promise((resolve, reject) => {
    //             this.db.run("INSTALL spatial; LOAD spatial;", (err) => {
    //                 if (err) {
    //                     console.error("Error initializing DuckDB:", err);
    //                     this.db = null;
    //                     reject(err);
    //                 } else {
    //                     resolve();
    //                 }
    //             });
    //         });
    //     }
    // }

    // // Open a new connection per transaction
    // async getDBConnection() {
    //     return new Promise((resolve, reject) => {
    //         const conn = this.db.connect();
    //         if (conn) {
    //             resolve(conn);
    //         } else {
    //             reject(new Error("Failed to create a new DuckDB connection"));
    //         }
    //     });
    // }

    // // Properly close the database connection (Not applicable for file system)
    // async closeDB() {
    //     if (!this.db) return;
    //     try {
    //         await new Promise((resolve, reject) => {
    //             this.db.run("checkpoint;", (err) => {
    //                 if (err) {
    //                     console.error("Error running CHECKPOINT before read:", err);
    //                     reject(err);
    //                 } else {
    //                     console.log("CHECKPOINT executed. Ensuring latest data is visible.");
    //                     resolve();
    //                 }
    //             });
    //         });

    //         await new Promise((resolve, reject) => {
    //             this.db.close((err) => {
    //                 setImmediate(() => {
    //                     if (err) {
    //                         console.error("Error closing DuckDB:", err);
    //                         reject(err);
    //                     } else {
    //                         console.log("DuckDB connection closed.");
    //                         this.db = null;
    //                         resolve();
    //                     }
    //                 });
    //             });
    //         });
    //     } catch (error) {
    //         console.error("Error during closeDB:", error);
    //     }
    // }

    // // Properly merge the database changes
    // async syncDB() {
    //     if (!this.db) return;
    //     try {
    //         await new Promise((resolve, reject) => {
    //             this.db.run("checkpoint;", (err) => {
    //                 if (err) {
    //                     console.error("Error running CHECKPOINT before read:", err);
    //                     reject(err);
    //                 } else {
    //                     //console.log("CHECKPOINT executed. Ensuring latest data is visible.");
    //                     resolve();
    //                 }
    //             });
    //         });
    //     } catch (error) {
    //         console.error("Error during running CHECKPOINT:", error);
    //     }
    // }

    // // Properly start the database transaction
    // async startTransaction() {
    //     if (!this.db) return;
    //     try {
    //         await new Promise((resolve, reject) => {
    //             this.db.run("BEGIN TRANSACTION;", (err) => {
    //                 if (err) {
    //                     console.error("Failed to start transaction:", err);
    //                     reject(err);
    //                 } else {
    //                     resolve();
    //                 }
    //             });
    //         });
    //     } catch (error) {
    //         console.error("Error during starting DB transaction:", error);
    //     }
    // }

    // // Properly start the DB transaction
    // async commitTransaction() {
    //     if (!this.db) return;
    //     try {
    //         await new Promise((resolve, reject) => {
    //             this.db.run("COMMIT;", (err) => {
    //                 if (err) {
    //                     console.error("Failed to commit transaction:", err);
    //                     reject(err);
    //                 } else {
    //                     resolve();
    //                 }
    //             });
    //         });
    //     } catch (error) {
    //         console.error("Error during starting DB transaction:", error);
    //     }
    // }

    // // Properly rollback the DB transaction
    // async rollbackTransaction() {
    //     if (!this.db) return;
    //     try {
    //         await new Promise((resolve, reject) => {
    //             this.db.run("ROLLBACK;", (err) => {
    //                 if (err) {
    //                     console.error("Failed to start transaction:", err);
    //                     reject(err);
    //                 } else {
    //                     resolve();
    //                 }
    //             });
    //         });
    //     } catch (error) {
    //         console.error("Error during starting DB transaction:", error);
    //     }
    // }


async function syncWALandDB(dbConn) {

    // Sync the Write Along Log with the DB if any commits are still outstanding with "CHECKPOINT"
    await new Promise((resolve, reject) => {
        dbConn.run("CHECKPOINT;", (err) => {
            if (err) {
                this.logger.error("Error running CHECKPOINT before read:", err);
                reject(err);
            } else {
                resolve();
            }
        });
    });
}

module.exports = {
    syncWALandDB
}
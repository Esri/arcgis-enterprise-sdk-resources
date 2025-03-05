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

async function loadParquetData(dbConn, logger, DFSConfig, parquetPath) {
    
    try {
      await new Promise((resolve, reject) => {
        const localParquetCreateClause = `CREATE TABLE ${DFSConfig.properties.name} AS 
            SELECT * EXCLUDE ${DFSConfig.WKBColumn}, 
            ST_GeomFromWKB(CAST(${DFSConfig.WKBColumn} AS BLOB)) AS ${DFSConfig.geomOutColumn}, 
            CAST(row_number() OVER () AS INTEGER) AS ${DFSConfig.idField}
            FROM read_parquet('${parquetPath}/*.parquet', hive_partitioning = true);`;

        const initQuery = `INSTALL spatial; LOAD spatial; 
            ${localParquetCreateClause}`;

            dbConn.run(initQuery, (err) => {
          if (err) {
            this.logger.error("Error loading Parquet file into memory:", err);
            reject(err);
          } else {
            this.logger.info("Parquet file successfully loaded into memory.");
            resolve();
          }
        });
      });
    } catch (error) {
      logger.error("Unexpected error loading Parquet data:", error);
    }
}

async function getDBConnection(db) {
    return new Promise((resolve, reject) => {
        const conn = db.connect();
        if (conn) {
            resolve(conn);
        } else {
            reject(new Error("Failed to create a new DuckDB connection"));
        }
    });
}

module.exports = {
    syncWALandDB,
    loadParquetData,
    getDBConnection
}
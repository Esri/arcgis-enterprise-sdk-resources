# DuckDB 
This is a CDF using duckdb as the spatially enabled database engine. It has a single dependency (duckDB) and uses its spatial extension. This cdf is considered a pass through data provider where subsets of data are fetched each request (not the entire dataset at once like a full fetch data provider). 

## Adding your own data source: 
- To add another data source copy one of the examples in the `config/default.json` or `config/olderexamples.json` file
- Then, write SQL query code in `src/model.js` to create a table with the format you desire, example: 
```javascript
deltaPointsCreateClause = `${secretClause}
CREATE TABLE ${deltaPointsConfig.properties.name} AS 
SELECT * EXCLUDE ${deltaPointsConfig.WKBColumn}, 
ST_GeomFromWKB(CAST(${deltaPointsConfig.WKBColumn} AS BLOB)) AS ${deltaPointsConfig.geomOutColumn}, 
CAST(row_number() OVER () AS INTEGER) AS ${deltaPointsConfig.idField}
FROM delta_scan('${deltaPointsConfig.deltaUrl}');`;
```
- add this SQL query into the `Model` constructor (inside of `src/model.js`) where duckdb is initalized to create a new table 
- whatever you named the topmost key in `config/default.json`, you will be access it as a layer in the feature server by doing `http://127.0.0.1:8080/duckdb/rest/services/{yourkeyhere}/FeatureServer/0`

## Important Notes: 
- To see full schema of required args see `src/modules/validate.js`
- `idfield` should **always** be set to OBJECTID, duckdb will create a row number and set it to `OBJECTID` since koop requires an object id field
- other id fields in your data columns can be accessed with sql where queries
- for very large datasets, best performance is achieved with loading in a .duckdb file instead of using a memory duckdb db.  
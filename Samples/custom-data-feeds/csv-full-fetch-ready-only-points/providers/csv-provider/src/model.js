const CSVconfig = require("config");
const fs        = require("fs").promises; 
const Papa      = require("papaparse");
const path      = require("path");
const validUrl  = require("valid-url");
const translate = require("./utils/translate-csv");

function Model() {}
Model.prototype.getData = async function (req) {
 const config     = CSVconfig["csv-provider"];
 const sourceId   = req.params.id;
 const sourceCfg  = config.sources[sourceId];
 const sourcePath = sourceCfg.url;

 let csvText;

 if (validUrl.isUri(sourcePath)) {
   // Network URL: fetch it and get the text
   const res = await fetch(sourcePath);
   if (!res.ok) {
     throw new Error(`Failed to fetch CSV: ${res.status} ${res.statusText}`);
   }

   csvText = await res.text();

 } else if (sourcePath.toLowerCase().endsWith(".csv")) {
   // Local file path: read it from disk
   const fullPath = path.join(__dirname, sourcePath);
   csvText = await fs.readFile(fullPath, "utf8");
 } else {
   throw new Error(`Unrecognized CSV source ${sourcePath}`);
 }
 // Now parse the entire CSV string
 const result = Papa.parse(csvText, {
   header: true,
   dynamicTyping: true,
   skipEmptyLines: true
 });

 if (result.errors && result.errors.length) {
   throw new Error(result.errors[0].message);
 }
 // Translate to GeoJSON
 const geojson = translate(result.data, sourceCfg);
 geojson.metadata = { name: 'points_csv', idField: 'Id' };
 return geojson;

};

module.exports = Model;


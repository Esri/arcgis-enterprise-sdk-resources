/*
  model.js

  This file is required. It must export a class with at least one public function called `getData`

  Documentation: http://koopjs.github.io/docs/specs/provider/
*/

const koopConfig = require("config");
const fs = require("fs");
const Papa = require("papaparse");
const path = require("path"); 
var validUrl = require('valid-url');
const translate = require("./utils/translate-csv");

function Model(koop) {}

// Public function to return data from the
// Return: GeoJSON FeatureCollection
Model.prototype.getData = async function (req, callback) {
  const config = koopConfig["csv-provider"];
  const sourceId = req.params.id;
  const sourceConfig = config.sources[sourceId];
  const csvFilePath = path.join(__dirname, sourceConfig.url);

  const csv = [];
  let readStream;

  if (validUrl.isUri(sourceConfig.url)) {
    // this is a network URL
    const res = await fetch(sourceConfig.url);
    readStream = res.body;
} else if (csvFilePath.toLowerCase().endsWith(".csv")) {
    // this is a file path
    readStream = fs.createReadStream(csvFilePath, "utf8");
    console.log("CSV File Path:", csvFilePath);
} else {
    callback(new Error(`Unrecognized CSV source ${sourceConfig.url}`));
    return;
}

  Papa.parse(readStream, {
    header: true,
    dynamicTyping: true,
    complete: function (result) {
      if (result.errors.length > 0) {
        callback(new Error(result.errors[0].message));
      }
      try {
        const geojson = translate(result.data, sourceConfig);
        geojson.metadata = { name: 'points_csv', idField: 'Id' }
        callback(null, geojson);
      } catch (e) {
        callback(e);
      }
    },
    error: callback,
  });
};

module.exports = Model;


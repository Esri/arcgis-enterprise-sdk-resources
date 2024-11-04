const koopConfig = require("config");
const fs = require("fs");
const Papa = require("papaparse");
const path = require("path");
const validUrl = require('valid-url');
const translate = require("./utils/translate-csv");

function Model(koop) {}

// Public function to return data from the
// Return: GeoJSON FeatureCollection
Model.prototype.getData = async function (req) {
  const config = koopConfig["csv-provider"];
  const sourceId = req.params.id;
  const sourceConfig = config.sources[sourceId];
  const csvFilePath = path.join(__dirname, sourceConfig.url);

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
    throw new Error(`Unrecognized CSV source ${sourceConfig.url}`);
  }

  return new Promise((resolve, reject) => {
    Papa.parse(readStream, {
      header: true,
      dynamicTyping: true,
      complete: (result) => {
        if (result.errors.length > 0) {
          return reject(new Error(result.errors[0].message));
        }
        try {
          const geojson = translate(result.data, sourceConfig);
          geojson.metadata = { name: 'points_csv', idField: 'Id' };
          resolve(geojson);
        } catch (e) {
          reject(e);
        }
      },
      error: reject,
    });
  });
};

module.exports = Model;


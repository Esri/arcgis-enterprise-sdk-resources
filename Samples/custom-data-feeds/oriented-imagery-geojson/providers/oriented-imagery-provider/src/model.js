const fs = require('fs-extra');
const path = require('path');
const loggingPrefix = 'File GeoJSON provider: ';
const config = require("../config/default.json");

class Model {
  #dataDir;
  #dataDirPath;
  #ttl;
  #logger;

  constructor(koop = {}) {
    // constructor (koop = {}, options = {}) {

    this.#logger = koop.logger;
    // this.#dataDir = options.dataDir || koop.dataDir || process.env.DATA_DIR || './data';
    this.#dataDir = config.dataDirectory;
    // this.#dataDirPath = path.join(process.cwd(), this.#dataDir);
    this.#dataDirPath = path.join(__dirname, this.#dataDir);

    console.log('dataDirPath ' + this.#dataDirPath);
    // this.#ttl = options.ttl || 0;
    this.#verifyDataDirectoryExists(this.#dataDirPath);
  }

  async getData(req, callback) {
    const filePath = await this.#getFileName(req.params.id);
    const filename = path.basename(filePath);

    try {
      const dataStringFromFile = await readGeoJsonFile(filePath);
      const { metadata = {}, ...geojsonFromFile } = parseGeoJson(dataStringFromFile, filename);
      const geojson = normalizeAsFeatureCollection(geojsonFromFile);
      geojson.ttl = this.#ttl;
      metadata.geometryType = geojson?.features[0]?.geometry?.type;
      metadata.title = metadata.title || 'Koop File GeoJSON';
      metadata.name = metadata.name || filename;
      metadata.description = metadata.description || `GeoJSON from ${filename}`;
      geojson.metadata = metadata;
      return callback(null, geojson);
    } catch (err) {
      err.message = `${loggingPrefix}${err.message}`;
      callback(err);
    }
  }

  async #getFileName(fileId) {
    const pathWithNoExt = path.join(this.#dataDirPath, fileId);
    console.log('path with no ext ' + pathWithNoExt);
    const geojsonExt = await fs.pathExists(`${pathWithNoExt}.geojson`);
    return geojsonExt ? `${pathWithNoExt}.geojson` : `${pathWithNoExt}.json`;
  }

  #verifyDataDirectoryExists(dataDirPath) {
    console.log('here is the path' + dataDirPath);
    const result = fs.existsSync(dataDirPath);
    if (!result) {
      throw new Error(`${loggingPrefix}data directory "${this.#dataDir}" not found.`);
    }

    this.#logger.info(`${loggingPrefix}will read data from ${this.#dataDir}`);
  }
}

async function readGeoJsonFile(filePath) {
  try {
    const dataBuffer = await fs.readFile(filePath);
    return dataBuffer.toString();
  } catch (err) {
    if (err.errno === -2) {
      err.code = 404;
      err.message = `${path.basename(filePath)} not found`;
    } else {
      err.code = 500;
    }
    throw err;
  }
}

function parseGeoJson(dataString, filename) {
  try {
    return JSON.parse(dataString);
  } catch (error) {
    throw new Error(`unparsable JSON in ${filename}`);
  }
}

function normalizeAsFeatureCollection(input) {
  // If input type is Feature, wrap in Feature Collection
  if (input.type === 'Feature') {
    return {
      type: 'FeatureCollection',
      features: [input],
      metadata: {
        geometryType: input.geometry.type
      }
    };
  }

  // If it's neither a Feature or a FeatureCollection its a geometry.  Wrap in a Feature Collection
  if (input.type !== 'FeatureCollection') {
    return {
      type: 'FeatureCollection',
      features: [{
        type: 'Feature',
        geometry: input,
        properties: {}
      }],
      metadata: {
        geometryType: input.type
      }
    };
  }

  return input;
}

module.exports = Model;

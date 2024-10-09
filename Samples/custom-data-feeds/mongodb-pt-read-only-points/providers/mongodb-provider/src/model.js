const { MongoClient, ServerApiVersion } = require('mongodb');
const config = require('config');
const { convertGeoserviceParamsToDbParams } = require('./helpers');

const filtersApplied = {
  where: true,
  objectIds: true,
  geometry: true,
  resultRecordCount: true,
  resultOffset: true,
};

class Model {
  #client;
  #databaseLookup;
  #definedCollectionsOnly;
  #logger;

  constructor(
    { logger },
    { connectString, databases, definedCollectionsOnly } = {},
  ) {
    this.#logger = logger;
    const databaseUri = connectString || config?.mongodb_provider?.connectString;
    this.#client = new MongoClient(databaseUri, {
      serverApi: {
        version: ServerApiVersion.v1,
        strict: true,
        deprecationErrors: true,
      },
    });
    this.#databaseLookup = databases || config?.mongodb_provider?.databases || {};
    this.#definedCollectionsOnly =
      definedCollectionsOnly ||
      config?.mongodb_provider?.definedCollectionsOnly ||
      false;
  }

  async getData(req, callback) {
    // Get  the "host" and "id" parameters from route-path
    const databaseName = req.params.host;
    const collectionName = req.params.id;

    // Get request query/body params
    const geoserviceParams = this.#parseParams(req.query);

    try {
      // Access specific database collection
      const collection = this.#client
        .db(databaseName)
        .collection(collectionName);

      // Fetch collection metadata
      const {
        geometryField,
        idField = 'alternateID',
        cacheTtl = 0,
        crs = 4326,
        maxRecordCount = 2000,
      } = this.#getMetadata(databaseName, collectionName);

      // Convert Geoservice params to MongoDB query equivalents
      const dbParams = convertGeoserviceParamsToDbParams({
        ...geoserviceParams,
        geometryField,
        idField,
        crs,
        resultRecordCount: maxRecordCount,
      });

      // for aggregate requests, aggregate directly with MongoDB
      if (geoserviceParams.returnCountOnly || geoserviceParams.returnExtentOnly) {
        const result = await aggregateDocs(collection, dbParams.pipeline);
        return callback(null, result);
      }

      // Fetch docs from Mongo
      const {features, exceededTransferLimit } = await fetchDocs({
        dbParams,
        collection,
        outFields: geoserviceParams.outFields,
        idField,
        geometryField,
      });

      // Convert docs to geojson
      const geojson = convertDocsToGeoJSON(features, geometryField);

      callback(null, {
        ...geojson,
        metadata: { idField, maxRecordCount, exceededTransferLimit },
        filtersApplied,
        ttl: cacheTtl,
        crs,
      });

    } catch (error) {
      this.#logger.error(`MongoDB Provider: ${JSON.stringify(error)}`);
      return callback(error);
    }
  }

  #parseParams(params) {
    const { returnCountOnly, returnExtentOnly } = params;

    return {
      ...params,
      returnCountOnly: typeof returnCountOnly === 'boolean' ? returnCountOnly : returnCountOnly === 'true',
      returnExtentOnly: typeof returnExtentOnly === 'boolean' ? returnExtentOnly : returnExtentOnly === 'true'
    };
  }

  #getMetadata(databaseName, collectionName) {
    // Lookup collection config
    const collectionConfig =
      this.#databaseLookup[databaseName]?.[collectionName];

    // If no defined collection-config and definedCollectionsOnly, reject with 404
    if (!collectionConfig && this.#definedCollectionsOnly) {
      const error = new Error('Not Found');
      error.code = 404;
      throw error;
    }

    // Get collection specific config data
    return this.#databaseLookup[databaseName]?.[collectionName] || {};
  }
}

async function aggregateDocs(collection, aggregatePipeline) {
  const cursor = await collection.aggregate(aggregatePipeline);
  const result = (await cursor.toArray())[0];
  await cursor.close();
  return result;
}

async function fetchDocs({
  dbParams,
  collection,
  outFields,
  idField,
  geometryField,
}) {
  const { query = {}, limit, skip = 0, sort = {} } = dbParams;
  const projection = buildProjection(outFields, idField, geometryField);

  const features = await collection
    .find(query)
    .limit(limit)
    .skip(skip)
    .sort(sort)
    .project(projection)
    .toArray();

  const totalCount = await collection.count(query);
  return { features, exceededTransferLimit: totalCount - skip > features.length };

}

function buildProjection(outFields = '*', idField, geometryField) {
  if (outFields === '*') {
    return {};
  }

  const fields = outFields.split(',');

  const projection = fields.reduce((acc, cur) => {
    acc[cur] = 1;
    return acc;
  }, {});

  if (geometryField) {
    projection[geometryField] = 1;
  }

  if (!fields.includes(idField)) {
    projection[idField] = 0;
  }

  return projection;
}

function convertDocsToGeoJSON(docs, geometryField) {
  const features = docs.map((record) => {
    const { [geometryField]: geometry, ...properties } = record;
    return { geometry, properties };
  });

  return {
    type: 'FeatureCollection',
    features,
  };
}

module.exports = Model;

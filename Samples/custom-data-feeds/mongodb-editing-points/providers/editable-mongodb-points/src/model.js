const { MongoClient, ServerApiVersion } = require('mongodb');
const config = require('config');
const { performEdits } = require('./perform-edits');

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
    const databaseUri = connectString || config?.editable_mongodb_points?.connectString;
    this.#client = new MongoClient(databaseUri, {
      serverApi: {
        version: ServerApiVersion.v1,
        strict: true,
        deprecationErrors: true,
      },
    });
    this.#databaseLookup = databases || config?.editable_mongodb_points?.databases || {};
    this.#definedCollectionsOnly =
      definedCollectionsOnly ||
      config?.editable_mongodb_points?.definedCollectionsOnly ||
      false;
  }

  async editData(req) {

    // assign database and collection name from path parameters
    const databaseName = req.params.host;
    const collectionName = req.params.id;

    // add logic to normalize layer-level or service-level requests
    const extractedEdits = normalizeRequestedEdits(req.body);

    const database  = this.#client.db(databaseName);
    const collection = database.collection(collectionName);

    let applyEditsResponse = {};  // initialize the response object

    // call the necessary functions to handled the edits
    applyEditsResponse = await performEdits(collection, extractedEdits.edits);

    // check if the response should be an object(layer-level) or an array(service-level)
    if (extractedEdits.editLevel === 'service') {
      applyEditsResponse.id = extractedEdits.layer;
      return [applyEditsResponse];

    }

    return applyEditsResponse;

  }

  // this a very basic getData function that operates as full-fetch
  async getData(req) {

    const databaseName = req.params.host;
    const collectionName = req.params.id;

    try {

      const database  = this.#client.db(databaseName);
      const collection = database.collection(collectionName);

      const results = await fetchDocs(collection);
     
      const geojson = convertDocsToGeoJSON(results, 'location');

      geojson.metadata

      return {...geojson, metadata: { 
        idField: 'alternateID', 
        name: 'Fires',
        templates: [
          {
           "name": "Edit MongoDB Fires",
           "description": "Template for editing fire data features",
           "drawingTool": "esriFeatureEditToolPoint",
           "prototype": {
            "attributes": {}
           }
          }
         ],
        fields: [
          {
            "name": "_id",
            "type": "string",
            "alias": "mongoID",
            "length": 128,
            "editable": false
          },
          {
            "name": "alternateID",
            "type": "bigInteger",
            "alias": "alternateID",
            "editable": false
          },
          {
              "name": "fireId",
              "type": "string",
              "alias": "fireId",
              "length": 128,
              "editable": true
          },
          {
              "name": "fireName",
              "type": "string",
              "alias": "fireName",
              "length": 128,
              "editable": true
          },
          {
              "name": "fireType",
              "type": "string",
              "alias": "fireType",
              "length": 128,
              "editable": true
          },
          {
              "name": "acres",
              "type": "string",
              "alias": "acres",
              "length": 128,
              "editable": true
          }
        ]
      }};

    } catch (error) {
      this.#logger.warning(error)
      return error
    }
    
  }
}

async function fetchDocs(items) {
  const results = await items.find({}).toArray();
  return results
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

function normalizeRequestedEdits(body) {
  let edits = {};
  // if it service-level, just return the object
  if(body.edits) { 
  
    return {
      edits: body.edits[0],
      editLevel : 'service',
      layer: body.edits[0].id
    }
  // handle the layer level request
  } else {
    if(body.adds) edits.adds = body.adds;
    if(body.updates) edits.updates = body.updates;
    if(body.deletes) {
      let deletesArray = [body.deletes];
      edits.deletes = deletesArray;
    }
    return {
      edits: edits,
      editLevel: 'layer'
    }
  }
}

module.exports = Model;
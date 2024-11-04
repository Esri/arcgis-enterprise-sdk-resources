const { MongoClient, ServerApiVersion } = require('mongodb');
const config = require('config');
const { normalizeRequestedEdits, performEdits, fetchDocs, convertDocsToGeoJSON } = require('./helpers');

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
    const databaseUri = connectString || config?.editable_mongodb_polygon_provider?.connectString;
    this.#client = new MongoClient(databaseUri, {
      serverApi: {
        version: ServerApiVersion.v1,
        strict: true,
        deprecationErrors: true,
      },
    });
    this.#databaseLookup = databases || config?.editable_mongodb_polygon_provider?.databases || {};
    this.#definedCollectionsOnly =
      definedCollectionsOnly ||
      config?.editable_mongodb_polygon_provider?.definedCollectionsOnly ||
      false;
  }

  async editData(pathParams, body) {

    const databaseName = pathParams.host;
    const collectionName = pathParams.id;

    // add logic to normalize layer-level or service-level requests
    const extractedEdits = normalizeRequestedEdits(body);

    const database  = this.#client.db(databaseName);
    const collection = database.collection(collectionName);

    let applyEditsResponse = {}

    applyEditsResponse = await performEdits(collection, extractedEdits.edits);

    // check if the response should be object(layer-level) or array(service-level)
    if (extractedEdits.editLevel === 'service') {
      applyEditsResponse.id = extractedEdits.layer;
      return [applyEditsResponse];

    }

    return applyEditsResponse;

  }

  // this a bare bones getData function not intended to be a full example
  async getData(req) {

    const databaseName = req.params.host;
    const collectionName = req.params.id;

    try {

      const database  = this.#client.db(databaseName);
      const collection = database.collection(collectionName);

      const results = await fetchDocs(collection);
     
      const geojson = convertDocsToGeoJSON(results, 'location');

      return {...geojson, metadata: { 
        idField: 'alternateID', 
        name: 'Fires',
        templates: [
          {
           "name": "Edit MongoDB Fires",
           "description": "Template for editing features",
           "drawingTool": "esriFeatureEditToolPoint",
           "prototype": {
            "attributes": {}
           }
          }
         ],
        fields: [
          {
            "name": "alternateID",
            "type": "esriFieldTypeOID",
            "alias": "alternateID",
            "sqlType": "sqlTypeInteger",
            "domain": null,
            "defaultValue": null,
            "editable": false
          },
          {
              "name": "_id",
              "type": "esriFieldTypeString",
              "alias": "_id",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": false
          },
          {
              "name": "fireId",
              "type": "esriFieldTypeString",
              "alias": "fireId",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true
          },
          {
              "name": "fireName",
              "type": "esriFieldTypeString",
              "alias": "fireName",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true
          },
          {
              "name": "fireType",
              "type": "esriFieldTypeString",
              "alias": "fireType",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true
          },
          {
              "name": "acres",
              "type": "esriFieldTypeInteger",
              "alias": "acres",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "editable": true
          }
        ]
      }};

    } catch (error) {
      console.log(error)
      return error
    }
    
  }
}

module.exports = Model;
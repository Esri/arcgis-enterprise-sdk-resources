const { GoogleSpreadsheet } = require('google-spreadsheet');
const { JWT } = require('google-auth-library');
const { editSheet } = require("./helpers/editSheet");
const config = require('config');
const inputCrs = 4326;

class Model {
  #serviceAccount;
  #logger;

  constructor(
    { logger }
  ) {
    this.#serviceAccount = new JWT({
      email: config?.google_sheets_provider?.client_email,
      key: config?.google_sheets_provider?.private_key,
      scopes: ['https://www.googleapis.com/auth/spreadsheets'],
    })
   
  }

  async editData(pathParams, body) {

    const documentID = pathParams.host;
    const sheetID = pathParams.id;

    // add logic for whether the request is at the layer-level or service-level
    const extractedEdits = normalizeRequestedEdits(body);
    
    // initialize the spreadsheet and load the information for it
    const doc = new GoogleSpreadsheet(documentID, this.#serviceAccount);
    await doc.loadInfo();
    
    // get the sheet requested along with the sheet headers
    const sheet = doc.sheetsByTitle[sheetID];

    let applyEditsResponse = {};

    applyEditsResponse = await editSheet(sheet, extractedEdits.edits);

    // check if the response should be object(layer-level) or array(service-level)
    if (extractedEdits.editLevel === 'service') {
      applyEditsResponse.id = extractedEdits.layer;
      return [applyEditsResponse];

    }

    return applyEditsResponse;
    
  }

  // this a simple getData function that follows a full-fetch pattern
  async getData(req) {

    // use path params to get the Google Document and Sheets IDs
    const documentID = req.params.host;
    const sheetID = req.params.id;

    // initialize the spreadsheet and load the information for it
    const doc = new GoogleSpreadsheet(documentID, this.#serviceAccount);
    await doc.loadInfo();

    // get the sheet requested along with the sheet headers
    const sheet = doc.sheetsByTitle[sheetID];
    await sheet.loadHeaderRow();
    const headers = sheet.headerValues;

    // initialize GeoJSON
    let geoJSON = {
      type: 'FeatureCollection',
      features: []
    }

    try {

      // get all the rows from the Google Sheet
      const rows = await sheet.getRows();
          
      rows.forEach(row => {
        let feature = {
          type: 'feature',
          geometry: {
            type: 'Point',
            coordinates: [parseFloat(row._rawData[6]), parseFloat(row._rawData[5])]
          },
          properties: {}
        };

        // use the array of headers to create the feature properties
        headers.forEach((header, index) => {
          if (header !== 'Latitude' && header !== 'Longitude') {
            feature.properties[header] = row._rawData[index];
          }
        });

        // in code add on an ID that can be used as objectId
        feature.properties.RowID = row._rowNumber;

        // add the feature to the geoJSON
        geoJSON.features.push(feature);

      });
      // the fields metadata property is required for configuring each attribute to be editable or not
      geoJSON.metadata;
      return {
        ...geoJSON, 
        metadata: { 
          idField: 'RowID', 
          name: 'Shoe Stores',
          templates: [
            {
             "name": "Google Sheets Edit Template",
             "description": "Template for editing Google Sheets features",
             "drawingTool": "esriFeatureEditToolPoint",
             "prototype": {
              "attributes": {}
             }
            }
          ], 
          fields: [
            {
              "name": "RowID",
              "type": "esriFieldTypeOID",
              "alias": "RowID",
              "sqlType": "sqlTypeInteger",
              "domain": null,
              "defaultValue": null,
              "editable": false,
              "nullable": false
            },
            {
              "name": "StoreName",
              "type": "esriFieldTypeString",
              "alias": "StoreName",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true,
              "nullable": false
            },
            {
              "name": "Street",
              "type": "esriFieldTypeString",
              "alias": "Street",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true,
              "nullable": false
            },
            {
              "name": "City",
              "type": "esriFieldTypeString",
              "alias": "City",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true,
              "nullable": false
            },
            {
              "name": "State",
              "type": "esriFieldTypeString",
              "alias": "State",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true,
              "nullable": false
            },
            {
              "name": "ZIP",
              "type": "esriFieldTypeString",
              "alias": "ZIP",
              "sqlType": "sqlTypeOther",
              "domain": null,
              "defaultValue": null,
              "length": 128,
              "editable": true,
              "nullable": false
            },
          ],
        inputCrs,
        },
      };
      
    } catch (error) {
      console.log(error)
      return error
    } 
  }
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

module.exports = Model

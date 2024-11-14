const constructGeoJSON = require('./constructGeoJSON');
const filtersApplied = {
  where: true,
  objectIds: true,
  geometry: true,
  resultRecordCount: true,
  resultOffset: true,
};

const maxRecordCount = 300;
const idField = 'gbifID';
const crs = 4326;
const hasZ = true;
const cacheTtl = 600; // seconds
let exceededTransferLimit = true;

class Model {
  #logger;

  constructor( {logger}) {
    this.#logger = logger;
  }

  async getData (req) {
    
    const familyKey = req.params.host;
    const country = req.params.id;
    let clientQuery;

    if (req.method === 'GET') {
        clientQuery = req.query;
    } else if (req.method === 'POST') {
        clientQuery = req.body;
    } else {
        clientQuery = {};
    }

    let baseURL = `https://api.gbif.org/v1/occurrence/search?familyKey=${familyKey}&country=${country}`;

    //intercept the first client requests
    if (req.query.returnCountOnly === 'true') {

      const responseCountResults = await fetch(`https://api.gbif.org/v1/occurrence/search?familyKey=${familyKey}&country=${country}&limit=1`);

      const jsonCountResults = await responseCountResults.json();
      let resultRecordCount = Math.min(jsonCountResults.count, 100000);

      return {
        "count": resultRecordCount,
        "extent": {
            "xmin": -10852633,
            "ymin": -134244,
            "xmax": -4957809,
            "ymax": 3280350,
            "spatialReference": {
                "wkid": 102100
            }
        }
      }
    }
    else {
      // subsequent requests that aren't returnCountOnly
      try {
        
        function urlQuery(clientQuery) {
          const {
            where,
            objectIds,
            resultOffset,
          } = clientQuery;

          let cleanedWhere;
          
          if (where) cleanedWhere = where.replace(/\sAND\s/g, '&').replace(/[\(\)]/g, '').replace(/'/g, '').replace(/\s/g, "");
          

          // write new logic for handling multiple objectIds
          if (objectIds) {
            const array = objectIds.split(",").map(Number);
            
            // Cap the number of objectIds
            const cappedArray = array.slice(0, 300); // when refactoring, add extra logic for making multiple by id requests

            const newArray = cappedArray.map(element => 'gbifId=' + element + '&');
            const objectIdString = newArray.join("").slice(0, -1);
            
            baseURL = `https://api.gbif.org/v1/occurrence/search?${objectIdString}`;

          }
          
          const whereClause = cleanedWhere ? `&${cleanedWhere}` : '';
          const limitClause = `&limit=${maxRecordCount}`;
          const offsetClause = `&offset=${resultOffset}`;
          
          return `${whereClause}${limitClause}${offsetClause}`;
          
        }
        
        const query = urlQuery(clientQuery);
        const finalURL = `${baseURL}${query}`;
        this.#logger.debug(finalURL);

        const response = await fetch(finalURL);
        
        const jsonResults = await response.json();

        const offset = Number(clientQuery.resultOffset);

        if (offset >= jsonResults.count) exceededTransferLimit = false;
        
        if (!response.ok) {
          const status = response.status;
          const statusText = response.statusText;
          throw new Error(`Request to ${finalURL} failed; ${status}, ${statusText}.`);
        }
        
        let geojson;

        // check for single item or array
        if (jsonResults.results === undefined) {
          geojson = constructGeoJSON(jsonResults);
          
        } else {
          geojson = constructGeoJSON(jsonResults.results);

        }
        
        return {
          ...geojson,
          metadata: { 
            idField, 
            maxRecordCount,
            hasZ,
            supportedQueryFormats: 'JSON',
            exceededTransferLimit,
            description: `This feature layer uses data from GBIF to map the distribution of GBIF Family Key ${familyKey} in ${country}`,
            name: `Geographic distribution of GBIF Family Key:${familyKey} in Country:${country}`, 
            label: 'year',
            fields: [
                { name: 'gbifID',         type: 'bigint' },
                { name: 'scientificName', type: 'string', length: 128 },
                { name: 'genus',          type: 'string', length: 128 },
                { name: 'species',        type: 'string', length: 128 },
                { name: 'basisOfRecord',  type: 'string', length: 256 },
                { name: 'continent',      type: 'string', length: 128 },
                { name: 'country',        type: 'string', length: 128 },
                { name: 'stateProvince',  type: 'string', length: 128 },
                { name: 'locality',       type: 'string', length: 512 },
                { name: 'elevation',      type: 'integer' },
                { name: 'eventDate',      type: 'date' },
                { name: 'year',           type: 'integer' },
                { name: 'month',          type: 'integer' },
                { name: 'day',            type: 'integer' },
                { name: 'image',          type: 'string', length: 256 },

            ],
            inputCrs: crs
          },
          filtersApplied,
          ttl: cacheTtl,
        }
        
      } catch (error) {
        return error;
      }
      
    }
  }

}

module.exports = Model

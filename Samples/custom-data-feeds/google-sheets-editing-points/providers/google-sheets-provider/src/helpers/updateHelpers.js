const codes = require('@esri/proj-codes');
const proj4 = require('proj4');
const { DatabaseSuccess, DatabaseFailure } = require('../classes/response-classes');
const ERROR_MSG = require('../constants/error-strings.json');
const CONSTANTS = require('../constants/constants.json');

async function updateRows(sheet, updates) {
    let updatesArray = [];

    const rows = await sheet.getRows();
    for (const item of updates) {
        try {
          
            // check if the requested OBJECTID is greater than the number of rows or less than 2
            // we want to return custom message for not found since our Sheets API wrapper won't
            if ( item.attributes.RowID < CONSTANTS.INDEX_OFFSET || item.attributes.RowID > rows[rows.length -1]._rowNumber) {
                const addedElement = new DatabaseFailure(item.attributes.OBJECTID, 1019, ERROR_MSG.NOT_UPDATED_OR_DOESNT_EXIST);
                updatesArray.push(addedElement);

            } else {
                // applyEdits spec allows for partial updates to features, so we just update what is requested, not a whole feature
                Object.entries(item.attributes).forEach(([key, value]) => {
                    if (key !== 'RowID') {
                      rows[item.attributes.RowID - CONSTANTS.INDEX_OFFSET].set(key, value);
                    }
                });

                // check separately if geometry is being updated for the feature
                if (item.geometry) {
                  const featureSR = item.geometry.spatialReference.wkid;
                  //check if incoming SR is same as the data SR
                  if (featureSR !== CONSTANTS.SOURCE_CRS_WKID) {
                    // look up the code
                    const crs = codes.lookup(featureSR)
                    // convert the coordinates to the data source CRS
                    const convertedCoordinates = proj4(crs.wkt,`EPSG:${CONSTANTS.SOURCE_CRS_WKID}`, [item.geometry.x, item.geometry.y])
                    // update rows with the converted coordinates
                    rows[item.attributes.RowID - CONSTANTS.INDEX_OFFSET].set('Longitude', convertedCoordinates[0]);
                    rows[item.attributes.RowID - CONSTANTS.INDEX_OFFSET].set('Latitude', convertedCoordinates[1]);

                  } else {
                    // use the actual incoming coordinates if the coordinate systems are the same
                    rows[item.attributes.RowID - CONSTANTS.INDEX_OFFSET].set('Longitude', item.geometry.x);
                    rows[item.attributes.RowID - CONSTANTS.INDEX_OFFSET].set('Latitude', item.geometry.y);
                  }
                }
                // save the commited change and update the response object  
                await rows[item.attributes.RowID - CONSTANTS.INDEX_OFFSET].save();
                const addedElement = new DatabaseSuccess(item.attributes.RowID);
                updatesArray.push(addedElement);
            }
            
        } catch (error) {
            const failedElement = new DatabaseFailure(item.attributes.OBJECTID, 1019, error.message);
            updatesArray.push(failedElement); 
        }
      }

      return updatesArray;
}

module.exports = {
    updateRows
}
const codes = require('@esri/proj-codes');
const proj4 = require('proj4');
const { DatabaseSuccess, InsertDatabaseFailure } = require('../classes/response-classes');
const CONSTANTS = require('../constants/constants.json');

async function insertRows(sheet, adds) {
    let addsArray = [];
    for (const item of adds) {

        try {

            let newRow;
            const featureSR = item.geometry.spatialReference.wkid;
            //check if incoming SR is same as the data SR, if not, then we need to convert
            if (featureSR !== CONSTANTS.SOURCE_CRS_WKID) {
                // look up the code
                const crs = codes.lookup(featureSR);
                // convert coordinates from what is currently in the client to our data source crs
                const convertedCoordinates = proj4(crs.wkt,`EPSG:${CONSTANTS.SOURCE_CRS_WKID}`, [item.geometry.x, item.geometry.y]);

                // insert a new row if crs was different
                newRow = await sheet.addRow({
                    ...item.attributes,
                    Longitude: convertedCoordinates[0],
                    Latitude: convertedCoordinates[1]
                })

            } else {
                newRow = await sheet.addRow({
                    ...item.attributes,
                    Longitude: item.geometry.x,
                    Latitude: item.geometry.y
                })
            }

            // the new row number is added to the response onject
            if (newRow._rowNumber) {
                const addedElement = new DatabaseSuccess(newRow._rowNumber);
                addsArray.push(addedElement);
            }
            
        } catch (error) {
            const failedElement = new InsertDatabaseFailure(1017, error.message);
            addsArray.push(failedElement);
        }
    }
      
      return addsArray;
}

module.exports = {
    insertRows
}
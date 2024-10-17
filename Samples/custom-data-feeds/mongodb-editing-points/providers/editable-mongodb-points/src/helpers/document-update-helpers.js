const codes = require('@esri/proj-codes');
const proj4 = require('proj4');
const { DatabaseSuccess, DatabaseFailure } = require('../classes/response-classes');
const CONSTANTS = require('../constants/constants.json');


async function updateMongoDocs(collection, updates) {
    // Use map to create an array of promises for each update operation
    const updatePromises = updates.map(async record => {
        let filter = { alternateID: record.attributes.alternateID };
        let updateDoc = { $set: {} };

        // Tack on any non OBJECTID updated attributes
        for (let key in record.attributes) {
            if (key !== '_id') {
                updateDoc.$set[key] = record.attributes[key];
            }
        }

        console.log(updateDoc);

        // If there is geometry, tack on the geometry updates
        if (record.geometry) {

            let coordinatesArray = [];
            
            // check if the incorming SR is the same as the data SR
            const featureSR = record.geometry.spatialReference.wkid;

            if (featureSR !== CONSTANTS.SOURCE_CRS_WKID) {

                // look up the code
                const crs = codes.lookup(featureSR);
                // convert coordinates from what is currently in client to our data source crs
                const convertedCoordinates = proj4(crs.wkt,`EPSG:${CONSTANTS.SOURCE_CRS_WKID}`, [record.geometry.x, record.geometry.y]);
                // push the converted coordinates into the array 
                coordinatesArray.push(convertedCoordinates[0]);
                coordinatesArray.push(convertedCoordinates[1]);

            } else {
                // SR is the same, so we just push on the incorming coordinates as is
                coordinatesArray.push(record.geometry.x);
                coordinatesArray.push(record.geometry.y);
            }

            if (!updateDoc.$set.location) {
                updateDoc.$set.location = {}; // Initialize location object if not already present
            }

            // Build the final update query with coordinates
            updateDoc.$set.location.type = 'Point';
            updateDoc.$set.location.coordinates = coordinatesArray;
        }

        // Perform the update
        const result = await collection.updateOne(filter, updateDoc);
        console.log(result);

        // Add the success or failure object to updates array based on the result
        if (result.modifiedCount === 1 || (result.matchedCount === 1 && result.modifiedCount === 0)) {
            return new DatabaseSuccess(record.attributes.alternateID);
        } else {
            return new DatabaseFailure(1019, "Internal error during object update.");
        }
    });

    // Wait for all update operations to complete
    const verifiedUpdatesArray = await Promise.all(updatePromises);

    return verifiedUpdatesArray;
}

module.exports = { updateMongoDocs };
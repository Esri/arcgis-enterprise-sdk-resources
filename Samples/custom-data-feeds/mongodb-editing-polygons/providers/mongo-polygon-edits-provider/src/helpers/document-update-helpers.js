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
            if (key !== 'OBJECTID') {
                updateDoc.$set[key] = record.attributes[key];
            }
        }

        // If there is geometry, tack on the geometry updates
        if (record.geometry) {
            let coordinatesArray = [];

            // Check if the incoming SR is the same as the data SR
            const featureSR = record.geometry.spatialReference.wkid;

            if (featureSR !== CONSTANTS.SOURCE_CRS_WKID) {
                // Look up the code
                const crs = codes.lookup(featureSR);
                // Convert coordinates from what is currently in client to our data source crs
                const convertedCoordinates = record.geometry.rings.map(ring => 
                    ring.map(coord => proj4(crs.wkt, `EPSG:${CONSTANTS.SOURCE_CRS_WKID}`, coord))
                );
                // Push the converted coordinates into the array
                coordinatesArray = convertedCoordinates;
            } else {
                // SR is the same, so we just push on the incoming coordinates as is
                coordinatesArray = record.geometry.rings;
            }

            if (!updateDoc.$set.location) {
                updateDoc.$set.location = {}; // Initialize location object if not already present
            }

            // Build the final update query with coordinates
            updateDoc.$set.location.type = 'Polygon';
            updateDoc.$set.location.coordinates = coordinatesArray;
        }

        // Perform the update
        const result = await collection.updateOne(filter, updateDoc);

        // Add the success or failure object to updates array based on the result
        if (result.modifiedCount === 1) {
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
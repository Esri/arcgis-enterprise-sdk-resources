const crypto = require('crypto');
const { ObjectId } = require('mongodb');

async function performEdits(collection, editsBody) {

    let applyEditsResponse = {
        addResults: [],
        updateResults: [],
        deleteResults: []
    };
    
    if (editsBody.adds) {
        const insertResult = await insertMongoDocs(collection, editsBody.adds);

        // append the 'addResults' array to the final response object
        applyEditsResponse.addResults = insertResult;
        
      } 
      
      // handle the updates portion if it exists
      if (editsBody.updates) {
        const updateResult = await updateMongoDocs(collection, editsBody.updates);
        // append the 'updateResults' array to the final response object
        applyEditsResponse.updateResults = updateResult;
  
      }
      
      // handle the deletes portion if it exists
      if (editsBody.deletes) {
        const deleteResult = await deleteMongoDocs(collection, editsBody.deletes);
        // append the 'deleteResults' to the final response object
        applyEditsResponse.deleteResults = deleteResult;
  
      } 
      
      return applyEditsResponse;
}

async function insertMongoDocs(collection, adds) {
  let addsArray =[]
  adds.forEach(record => {
    addsArray.push(transformToDatasourceJson(record));
  });

  const result = await collection.insertMany(addsArray);

  return addsArray.map((record, index) => {
    if (result.acknowledged && result.insertedIds[index] === record._id) {
      return new DatabaseSuccess([record._id]);
    }
    return new DatabaseFailure([record._id], 1017, "Internal error during object insert.");
  });
}

function transformToDatasourceJson(record) {

  const transformedJson = new DataBaseObject(
    record.properties.fireId.toString(),
    record.properties.fireName.toString(),
    record.properties.fireType,
    record.properties.acres,
    record.geometry.x,
    record.geometry.y
  );

  return transformedJson;

}

async function updateMongoDocs(collection, updates) {
  // Use map to create an array of promises for each update operation
  const updatePromises = updates.map(async record => {
      const id = record.properties._id;
      if (typeof id !== 'string' || id.length === 0) {
          return new DatabaseFailure([], 1019, "A string _id is required for updates.");
      }
      let filter = { _id: ObjectId.isValid(id) ? new ObjectId(id) : id };
      let updateDoc = { $set: {} };

      // MongoDB's _id is immutable; alternateID is no longer an identifier.
      for (let key in record.properties) {
          if (key !== '_id' && key !== 'alternateID') {
              updateDoc.$set[key] = record.properties[key];
          }
      }

      // If there is geometry, tack on the geometry updates
      if (record.geometry) {

        let coordinatesArray = [];
        coordinatesArray.push(record.geometry.x);
        coordinatesArray.push(record.geometry.y);

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
          return new DatabaseSuccess([id]);
      } else {
          return new DatabaseFailure([id], 1019, "Internal error during object update.");
      }
  });

  // Wait for all update operations to complete
  const verifiedUpdatesArray = await Promise.all(updatePromises);

  return verifiedUpdatesArray;
}

async function deleteMongoDocs(collection, deletes) {
  let verifiedDeletesArray = await Promise.all(deletes.map(async element => {
      if (typeof element !== 'string' || element.length === 0) {
          return new DatabaseFailure([], 1018, "A string _id is required for deletes.");
      }
      const query = { _id: ObjectId.isValid(element) ? new ObjectId(element) : element };
      const result = await collection.deleteOne(query);

      if (result.deletedCount === 1) {
          return new DatabaseSuccess([element]);
      } else {
          return new DatabaseFailure([element], 1018, "Internal error during object delete.");
      }
  }));

  return verifiedDeletesArray;
}

class DataBaseObject {
  constructor(fireId, fireName, fireType, acres, x, y) {
      this.fireId = fireId,
      this.fireName = fireName,
      this.fireType = fireType,
      this.acres = acres,
      this.location = {
          type: "Point",
          coordinates: [x,y]
      }
  }
}

class DatabaseSuccess {
  constructor(uniqueIds) {
      this.uniqueIds = uniqueIds;
      this.success = true
  }
}

class DatabaseFailure {
  constructor(uniqueIds, code, description) {
      if (uniqueIds.length > 0) {
          this.uniqueIds = uniqueIds;
      }
      this.success = false,
      this.error = {
          code: code,
          description: description
      }
  }
}


module.exports = { performEdits };
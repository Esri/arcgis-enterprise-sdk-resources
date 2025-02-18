async function performEdits(collection, editsBody) {

    let applyEditsResponse = {
        addResults: [],
        updateResults: [],
        deleteResults: []
    };
    
    if (editsBody.adds) {
        const insertResult = await insertMongoDocs(collection, editsBody.adds);

        // add our own idField (altnerateId) and verify the documents
        const verifiedDocuments = await updateIdFieldAndVerify(collection, insertResult, editsBody.adds);

        // append the 'addResults' array to the final response object
        applyEditsResponse.addResults = verifiedDocuments;
        
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

  return result;
}

function transformToDatasourceJson(record) {

  // check if the incoming SR is the same as the data SR; if differnt, convert and modify the record
  const featureSR = record.geometry.spatialReference.wkid;
  if (featureSR !== CONSTANTS.SOURCE_CRS_WKID) {

    // look up the code
    const crs = codes.lookup(featureSR);
    // convert coordinates from what is currently in client to our data source crs
    const convertedCoordinates = proj4(crs.wkt,`EPSG:${CONSTANTS.SOURCE_CRS_WKID}`, [record.geometry.x, record.geometry.y]);
    // push the converted coordinates into the array 
    record.geometry.x = convertedCoordinates[0];
    record.geometry.y = convertedCoordinates[1];

  }

  const transformedJson = new DataBaseObject(
    record.attributes.fireId.toString(),
    record.attributes.fireName.toString(),
    record.attributes.fireType,
    record.attributes.acres,
    record.geometry.x,
    record.geometry.y
  );

  return transformedJson;

}

async function updateIdFieldAndVerify(collection, insertResults, adds) {

  // Extract and convert each ObjectId to its string representation
  const ids = Object.keys(insertResults.insertedIds).map(key => insertResults.insertedIds[key].toString());

  // create alternateIDs from the Mongo OIDs
  const hashedIDs = hashMongoOID(ids);
  
  // do an update of the new documents with the new alternateIDs
  // iterate over the new hashed IDs and match them to the Mongo equivalents so we can update
  let updatesArray = [];
  let updateObj;

  for (let index = 0; index < hashedIDs.length; index++) {
      
      const originalOID = ids[index];
      // create the update queries and load into an array
      updateObj = { _id: originalOID, update: { $set: { alternateID: hashedIDs[index]}}}
      updatesArray.push(updateObj);
  }

  // perform the update of the new docs
  updatesArray.forEach(async update => {
    const result = await collection.updateOne({ _id: new ObjectId(update._id)}, update.update);
      
  })

  let verifiedDocs;
  const maxRetries = 3;
  let attempts = 0;

  while (attempts < maxRetries) {
    verifiedDocs = await returnDocsByAlternateId(collection, hashedIDs);
    if (verifiedDocs.length === adds.length) {
      break;
    }
    attempts++;
  }

  // add the alternateIDs (ie OBJECTID) to the final response object
  // loop for the number of objects user intended to insertedIds
  // compare the intended insertions to the number that actually got inserted
  let verifiedAddsArray = [];
  for (let index = 0; index < adds.length; index++) {

      if(typeof verifiedDocs[index] !== 'undefined') {
      const addedElement = new DatabaseSuccess(verifiedDocs[index].alternateID);
      verifiedAddsArray.push(addedElement);
      } else {
      const failedElement = new DatabaseFailure(1017, "Internal error during object insert.");
      verifiedAddsArray.push(failedElement)
      }
  }

  return verifiedAddsArray;
}

async function returnDocsByAlternateId(collection, ids) {

  const verifiedDocs = await collection.find(
    { alternateID: { $in: ids } },
    { alternateID: 1 }
  ).toArray()

  return verifiedDocs;
}

function hashMongoOID(oids) {
  const hashedOIds = oids.map(oid => {
    // Create a SHA-256 hash of the input string
    const hash = crypto.createHash('sha256').update(oid).digest('hex');
    
    // Take the first 8 characters (32 bits) of the hash
    const hash32Bit = hash.substring(0, 8);
    
    // Convert the 32-bit hex string to a regular number
    const integer32Bit = parseInt(hash32Bit, 16);
    
    // Ensure the number is within a desired range to keep it under 9 digits
    // Using 999,999,999 as the base for modulo operation to ensure the result is always fewer than 9 digits
    const safeInteger = integer32Bit % 999999999;
    
    return safeInteger;
    
  });
  return hashedOIds;
}

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

async function deleteMongoDocs(collection, deletes) {
  let verifiedDeletesArray = await Promise.all(deletes.map(async element => {
      const query = { alternateID: element };
      const result = await collection.deleteOne(query);

      if (result.deletedCount === 1) {
          return new DatabaseSuccess(element);
      } else {
          return new DatabaseFailure(1018, "Internal error during object delete.");
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
      },
      this.alternateID = null
  }
}

class DatabaseSuccess {
  constructor(objectId) {
      this.objectId = objectId;
      this.success = true
  }
}

class DatabaseFailure {
  constructor(objectId, code, description) {
      this.objectId = objectId
      this.success = false,
      this.error = {
          code: code,
          desicription: description
      }
  }
}


module.exports = { performEdits };
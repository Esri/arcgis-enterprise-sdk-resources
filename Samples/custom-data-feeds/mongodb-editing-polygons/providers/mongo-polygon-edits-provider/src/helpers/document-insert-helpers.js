const crypto = require('crypto');
const { ObjectId } = require('mongodb');
const codes = require('@esri/proj-codes');
const proj4 = require('proj4');
const { DatabaseSuccess, DatabaseFailure } = require('../classes/response-classes');
const CONSTANTS = require('../constants/constants.json');


async function insertMongoDocs(collection, adds) {
    let addsArray =[]
    adds.forEach(record => {
      addsArray.push(transformToDatasourceJson(record));
    });
  
    const result = await collection.insertMany(addsArray);
  
    return result;
}

function transformToDatasourceJson(record) {
  // check if the incoming SR is the same as the data SR; if different, convert and modify the record
  const featureSR = record.geometry.spatialReference.wkid;
  if (featureSR !== CONSTANTS.SOURCE_CRS_WKID) {
      // look up the code
      const crs = codes.lookup(featureSR);
      // convert coordinates from what is currently in client to our data source crs
      const convertedCoordinates = record.geometry.rings.map(ring => 
          ring.map(coord => proj4(crs.wkt, `EPSG:${CONSTANTS.SOURCE_CRS_WKID}`, coord))
      );
      // push the converted coordinates into the array 
      record.geometry.rings = convertedCoordinates;
  }

  const transformedJson = {
      fireId: record.attributes.fireId.toString(),
      fireName: record.attributes.fireName,
      fireType: record.attributes.fireType,
      acres: record.attributes.acres,
      location: {
          type: "Polygon",
          coordinates: record.geometry.rings
      },
      alternateID: null
  };

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

    // perform the updates for each feature
    updatesArray.forEach(async update => {
      const result = await collection.updateOne({ _id: new ObjectId(update._id)}, update.update);
      console.log(result);
    })

    let verifiedDocs;
    const maxRetries = 3;
    let attempts = 0;

    while (attempts < maxRetries) {
      verifiedDocs = await returnDocsByAlternateId(collection, hashedIDs);
      if (verifiedDocs.lenght === adds.length) {
        break;
      }
      attempts++;
    }

    // const verifiedDocs = await returnDocsByAlternateId(collection, hashedIDs);
  
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

module.exports = {
    insertMongoDocs,
    updateIdFieldAndVerify
}
const { insertMongoDocs, updateIdFieldAndVerify } = require('./document-insert-helpers');
const { updateMongoDocs } = require('./document-update-helpers');
const { deleteMongoDocs } = require('./document-delete-helpers');

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

module.exports = { performEdits };
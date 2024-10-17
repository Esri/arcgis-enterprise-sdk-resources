const { insertRows } = require('./insertHelpers');
const { updateRows } = require('./updateHelpers');
const { deleteRows } = require('./deleteHelpers');

async function editSheet(sheet, editsBody) {

    // build our initial response object
    let applyEditsResponse = {
        addResults: [],
        updateResults: [],
        deleteResults: []
    };
    // Handle Inserts
    if (editsBody.adds) {
        const insertResult = await insertRows(sheet, editsBody.adds);
        applyEditsResponse.addResults = insertResult;
    }
  
    // Handle Updates
    if (editsBody.updates) {
        const updateResult = await updateRows(sheet, editsBody.updates);
        applyEditsResponse.updateResults = updateResult;
    }
  
    // Handle Deletes
    if (editsBody.deletes) {
        const deleteResult = await deleteRows(sheet, editsBody.deletes)
        applyEditsResponse.deleteResults = deleteResult;
    }
  
    return applyEditsResponse;
}

module.exports = {
    editSheet
}
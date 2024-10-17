const { DatabaseSuccess, DatabaseFailure } = require('../classes/response-classes');
const ERROR_MSG = require('../constants/error-strings.json');
const CONSTANTS = require('../constants/constants.json');

async function deleteRows(sheet, deletes) {
    let deletesArray = [];
    const rows = await sheet.getRows();

    for (const item of deletes) {
        try {

            if ( item > rows[rows.length -1]._rowNumber ) {

                const addedElement = new DatabaseSuccess(item);
                deletesArray.push(addedElement);

            // make sure the requested OBJECTID is not less than 2
            // this is the only invalid OBJECTID that is possible
            } else if ( item < CONSTANTS.INDEX_OFFSET) {

                const failedElement = new DatabaseFailure(item, 1000, ERROR_MSG.ROW_ID_LESS_THAN_MIN);
                deletesArray.push(failedElement);

            } else {

                await rows[item - CONSTANTS.INDEX_OFFSET].delete();
                const addedElement = new DatabaseSuccess(item);
                deletesArray.push(addedElement);
            }

        } catch (error) {
            const failedElement = new DatabaseFailure(item, 500, error.message);
            deletesArray.push(failedElement);
        } 

      }

      return deletesArray;
}

module.exports = {
    deleteRows
}
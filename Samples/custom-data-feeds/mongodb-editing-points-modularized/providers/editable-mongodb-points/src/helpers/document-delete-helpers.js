const { DatabaseSuccess, DatabaseFailure } = require('../classes/response-classes');

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

module.exports = { deleteMongoDocs };
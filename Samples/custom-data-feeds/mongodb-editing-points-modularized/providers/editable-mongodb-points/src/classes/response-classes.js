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

class InsertDatabaseFailure {
    constructor(code, description) {
        this.success = false,
        this.error = {
            code: code,
            desicription: description
        }
    }
}

class RollBackResponse {
    constructor(code, extendedCode, message, details) {
        this.code = code,
        this.extendedCode = extendedCode,
        this.message = message,
        this.details = details
    }
}

class InvalidParameter {
    constructor(code, message, details) {
        this.error = {
            code : code,
            message: message,
            details: [details]
        }
    }
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

module.exports = {
    DatabaseSuccess,
    DatabaseFailure,
    InsertDatabaseFailure,
    RollBackResponse,
    InvalidParameter,
    DataBaseObject
}
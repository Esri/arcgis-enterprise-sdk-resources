module.exports = [
    {
        path: `/editable-mongodb-points/rest/services/:host/:id/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/editable-mongodb-points/rest/services/:host/:id/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
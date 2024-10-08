module.exports = [
    {
        path: `/editable-mongodb-provider/rest/services/:host/:id/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/editable-mongodb-provider/rest/services/:host/:id/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
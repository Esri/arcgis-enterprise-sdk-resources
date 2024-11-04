module.exports = [
    {
        path: `/mongo-polygon-edits-provider/rest/services/:host/:id/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/mongo-polygon-edits-provider/rest/services/:host/:id/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
module.exports = [
    {
        path: `/databricks/rest/services/:id/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/databricks/rest/services/:id/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]

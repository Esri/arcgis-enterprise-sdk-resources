module.exports = [
    {
        path: `/duckdb-filesystem-provider/rest/services/:id/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/duckdb-filesystem-provider/rest/services/:id/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
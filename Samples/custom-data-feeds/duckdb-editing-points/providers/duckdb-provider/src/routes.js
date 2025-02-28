module.exports = [
    {
        path: `/duckdb-provider/rest/services/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/duckdb-provider/rest/services/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
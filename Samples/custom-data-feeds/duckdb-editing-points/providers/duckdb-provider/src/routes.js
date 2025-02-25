module.exports = [
    {
        path: `/duckdb/rest/services/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/duckdb/rest/services/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
module.exports = [
    {
        path: `/google-sheets-provider/rest/services/:host/:id/FeatureServer/:layer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    },
    {
        path: `/google-sheets-provider/rest/services/:host/:id/FeatureServer/applyEdits`,
        methods: ['POST'],
        handler: 'editData'
    }
]
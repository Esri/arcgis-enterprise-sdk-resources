async function fetchDocs(items) {
    const results = await items.find({}).toArray();
    return results
}
  
function convertDocsToGeoJSON(docs, geometryField) {
    const features = docs.map((record) => {
      const { [geometryField]: geometry, ...properties } = record;
      return { geometry, properties };
    });
  
    return {
      type: 'FeatureCollection',
      features,
    };
}

module.exports = {
    fetchDocs,
    convertDocsToGeoJSON
}

async function fetchDocs(items) {
    const results = await items.find({}).toArray();
    return results
}
  
function convertDocsToGeoJSON(docs, geometryField) {
  const features = docs.map((record) => {
      const { [geometryField]: geometry, ...properties } = record;
      return {
          type: 'Feature',
          geometry: {
              type: geometry.type,
              coordinates: geometry.coordinates
          },
          properties
      };
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

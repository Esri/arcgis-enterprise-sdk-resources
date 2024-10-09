module.exports = body => {
    return {
      type: "FeatureCollection",
      features: body.businesses.map(business => {
        return {
          type: "Feature",
          properties: {
            yelp_id: business.id,
            name: business.name,
            category: business.categories[0].title
          },
          geometry: {
            type: "Point",
            coordinates: [business.coordinates.longitude, business.coordinates.latitude]
          }
        };
      })
    };
};
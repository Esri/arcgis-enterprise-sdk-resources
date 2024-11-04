function addGeoFilterToQuery(query, geometryFilter) {
  if (!query) {
    return geometryFilter;
  }

  if (!query.$and) {
    return {
      $and: [ geometryFilter, query]
    };
  }
  query.$and.push(geometryFilter);
  return query;
}

module.exports = { addGeoFilterToQuery };

const _ = require('lodash');

function addGeoFilterToPipeline(pipeline, geoFilter) {
  // Find any existing match stage in aggregate pipeline array
  const matchStageIndex = pipeline?.findIndex((stage) => stage.$match);

  // If no pre-existing match stage, add one with a geo-filter
  if (matchStageIndex === -1) {
    _.set(pipeline.unshift({ $match: geoFilter }));
    return pipeline;
  }

  // When match stage exists, but doesn't include a top-level "and" array, add one with a geo-filter
  if (!pipeline[matchStageIndex].$match.$and) {
    const currentMatch = pipeline[matchStageIndex].$match;
    pipeline[matchStageIndex].$match = {
      $and: [currentMatch, geoFilter],
    };
    return pipeline;
  }

  pipeline[matchStageIndex].$match.$and.push(geoFilter);
  return pipeline;
}

module.exports = { addGeoFilterToPipeline };

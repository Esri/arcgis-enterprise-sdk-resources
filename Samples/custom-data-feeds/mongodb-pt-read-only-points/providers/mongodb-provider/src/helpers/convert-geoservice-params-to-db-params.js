const SQLParser = require('@synatic/noql');
const {
  standardizeGeometryFilter,
  combineObjectIdsAndWhere,
} = require('@koopjs/geoservice-utils');
const { addGeoFilterToPipeline } = require('./add-geo-filter-to-pipeline');
const { addGeoFilterToQuery } = require('./add-geo-filter-to-query');
const { extentCalculatorStage } = require('./extent-calculator-stage');

const relationLookup = {
  esriSpatialRelIntersects: '$geoIntersects',
  esriSpatialRelWithin: '$geoWithin',
};

function convertGeoserviceParamsToDbParams(params) {
  const {
    geometry,
    returnExtentOnly,
    geometryField,
    idField,
    inSR,
    spatialRel,
    dataCRS,
  } = params;

  // Convert geoservice where, objectIds, resultRecordCount, result offset to SQL equivalent
  const sql = buildSqlQuery(params);

  // Convert SQL to MongoDB "find" parameters
  const dbParams = SQLParser.parseSQL(sql);

  // If geo-extent requested, add pipeline stage to calculate it
  if (returnExtentOnly) {
    dbParams.pipeline = addExtentAggregation(dbParams.pipeline, geometryField, idField);
  }

  if (!geometry) {
    return dbParams;
  }

  // if geometry filter, add to the MongoDB "find" parameterization
  const geometryFilter = standardizeGeometryFilter({
    geometry,
    inSR,
    spatialRel,
    reprojectionSR: dataCRS,
  });

  const { query, pipeline } = addGeometryFilter(
    dbParams,
    geometryFilter,
    geometryField,
  );

  return {
    ...dbParams,
    query,
    pipeline,
  };
}

function addGeometryFilter(dbParams, geometryFilter, geometryField) {
  const { type, pipeline, query } = dbParams;
  const geoFilter = {
    [geometryField]: {
      [relationLookup[geometryFilter.relation]]: {
        $geometry: geometryFilter.geometry,
      },
    },
  };

  if (type === 'aggregate') {
    return {
      pipeline: addGeoFilterToPipeline(pipeline, geoFilter),
    };
  }

  return {
    query: addGeoFilterToQuery(query, geoFilter),
  };
}

function addExtentAggregation(pipeline, geometryField, idField) {
  const groupIndex = pipeline.findIndex((stage) => stage.$group);
  pipeline.slice(0, groupIndex);
  pipeline[groupIndex].$group = extentCalculatorStage(geometryField);
  pipeline[groupIndex + 1] = {
    $project: {
      [idField]: 0,
      count: '$result.count',
      extent: '$result.extent',
    },
  };

  return pipeline;
}

function buildSqlQuery(params) {
  const {
    where,
    orderByFields,
    objectIds,
    resultRecordCount,
    resultOffset,
    idField,
    returnCountOnly,
    returnExtentOnly,
  } = params;

  const limitClause = ` LIMIT ${resultRecordCount}`;

  const orderByClause = orderByFields ? ` ORDER BY ${orderByFields}` : '';

  const offsetClause = resultOffset ? ` OFFSET ${resultOffset}` : '';

  // combine the "where" and "objectIds"
  const combinedWhere = combineObjectIdsAndWhere({ where, objectIds, idField });
  const whereClause = combinedWhere ? ` WHERE ${combinedWhere}` : '';

  if (returnCountOnly || returnExtentOnly) {
    return `SELECT COUNT(*) AS count FROM collection${whereClause}`;
  }

  return `SELECT * FROM collection${whereClause}${orderByClause}${limitClause}${offsetClause}`;
}

module.exports = {
  convertGeoserviceParamsToDbParams,
};

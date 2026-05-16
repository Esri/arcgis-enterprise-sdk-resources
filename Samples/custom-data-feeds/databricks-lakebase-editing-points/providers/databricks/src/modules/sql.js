const { getGeometryFilter } = require("./geometry");

/**
 * Build a parameterized PostGIS SELECT query for Lakebase.
 * Returns { sql, params } for use with pg.pool.query(sql, params).
 */
function buildSqlQuery(
	geoParams,
	idField,
	geometryField,
	schema,
	tableName,
	dbWKID,
	fetchSize
) {
	const {
		where,
		outFields = "*",
		orderByFields,
		objectIds,
		geometry,
		inSR,
		resultOffset,
		spatialRel,
		returnIdsOnly,
		returnCountOnly,
		returnDistinctValues,
		returnGeometry,
	} = geoParams;

	const params = [];
	let paramIndex = 1;

	// SELECT clause - use ST_AsGeoJSON to convert PostGIS geometry to GeoJSON
	var selectClause = "";
	if (returnCountOnly) {
		selectClause = "COUNT(*) AS count";
	} else if (returnIdsOnly) {
		selectClause = `${idField}`;
	} else if (returnDistinctValues && !returnGeometry) {
		selectClause = `${outFields}`;
	} else if (outFields === "*") {
		selectClause = `*, ST_AsGeoJSON(${geometryField}) AS ${geometryField}_geojson`;
	} else {
		var outputFields = outFields;
		if (!outFields.includes(idField)) {
			outputFields = outFields.concat(`, ${idField}`);
		}
		selectClause = `${outputFields}, ST_AsGeoJSON(${geometryField}) AS ${geometryField}_geojson`;
	}

	const from = ` FROM ${schema}.${tableName}`;

	const { whereClause, params: whereParams, nextParamIndex } = buildSqlWhere({
		where,
		objectIds,
		idField,
		geometry,
		geometryField,
		inSR,
		spatialRel,
		dbWKID,
		paramIndex,
	});
	params.push(...whereParams);
	paramIndex = nextParamIndex;

	const orderByClause = orderByFields ? ` ORDER BY ${orderByFields}` : "";

	const distinctClause = returnDistinctValues ? `DISTINCT ` : "";

	const limitClause =
		fetchSize && !returnIdsOnly && !returnDistinctValues
			? ` LIMIT ${fetchSize}`
			: "";

	const offsetClause =
		resultOffset && !returnIdsOnly ? ` OFFSET ${resultOffset}` : "";

	const sql = `SELECT ${distinctClause}${selectClause}${from}${whereClause}${orderByClause}${limitClause}${offsetClause}`;
	return { sql, params };
}

function buildSqlWhere({
	where,
	objectIds,
	idField,
	geometry,
	geometryField,
	inSR,
	spatialRel,
	dbWKID,
	paramIndex,
}) {
	const sqlWhereComponents = [];
	const params = [];

	if (!where && objectIds === undefined && !geometry) {
		return { whereClause: "", params, nextParamIndex: paramIndex };
	}

	if (where) {
		sqlWhereComponents.push(where);
	}

	if (idField && objectIds) {
		// Use parameterized queries for object IDs
		const ids = String(objectIds)
			.split(",")
			.map((id) => Number(id.trim()))
			.filter((n) => Number.isFinite(n) && Number.isInteger(n));

		if (ids.length > 0) {
			const placeholders = ids.map((id) => {
				params.push(id);
				return `$${paramIndex++}`;
			});
			sqlWhereComponents.push(`${idField} IN (${placeholders.join(", ")})`);
		} else {
			sqlWhereComponents.push("1 = 0");
		}
	}

	if (geometry && geometryField) {
		const geomResult = getGeometryFilter(
			geometry,
			geometryField,
			inSR,
			spatialRel,
			dbWKID,
			paramIndex
		);
		if (geomResult) {
			sqlWhereComponents.push(geomResult.clause);
			params.push(...geomResult.params);
			paramIndex = geomResult.nextParamIndex;
		}
	}

	const whereClause =
		sqlWhereComponents.length > 0
			? " WHERE " + sqlWhereComponents.join(" AND ")
			: "";

	return { whereClause, params, nextParamIndex: paramIndex };
}

module.exports = {
	buildSqlQuery,
};

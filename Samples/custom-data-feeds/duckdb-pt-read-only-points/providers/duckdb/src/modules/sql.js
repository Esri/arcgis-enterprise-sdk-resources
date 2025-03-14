/* Copyright 2025 Esri

Licensed under the Apache License Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

const { getGeometryQuery } = require("./geometry");

// TODO: support datetime queries?
function buildSqlQuery(
	geoParams,
	idField,
	geometryField,
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

	var selectClause = "";
	if (returnCountOnly) {
		selectClause = "COUNT(1)";
	} else if (returnIdsOnly) {
		selectClause = `${idField}`;
	} else if (returnDistinctValues && !returnGeometry) {
		selectClause = `${outFields}`;
	} else if (outFields === "*") {
		selectClause = `${outFields} EXCLUDE ${geometryField}, ST_AsGeoJSON(${geometryField}) AS ${geometryField}`;
	} else {
		var outputFields = outFields;
		if (!outFields.includes(idField)) {
			// Koop needs OBJECTID field in geojson
			outputFields = outFields.concat(`, ${idField}`);
		}
		selectClause = `${outputFields}, ST_AsGeoJSON(${geometryField}) AS ${geometryField}`;
	}

	const from = ` FROM ${tableName}`;

	const whereClause = buildSqlWhere({
		where,
		objectIds,
		idField,
		geometry,
		geometryField,
		inSR,
		spatialRel,
		dbWKID,
	});

	const orderByClause = orderByFields ? ` ORDER BY ${orderByFields}` : "";

	const distinctClause = returnDistinctValues ? `DISTINCT ` : "";

	const limitClause =
		fetchSize && !returnIdsOnly && !returnDistinctValues
			? ` LIMIT ${fetchSize}`
			: "";

	const offsetClause =
		resultOffset && !returnIdsOnly ? ` OFFSET ${resultOffset}` : "";

	return `SELECT ${distinctClause}${selectClause}${from}${whereClause}${orderByClause}${limitClause}${offsetClause}`;
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
}) {
	const sqlWhereComponents = [];

	if (!where && objectIds === undefined) {
		return "";
	}

	if (where) {
		sqlWhereComponents.push(where);
	}

	if (idField && objectIds) {
		const objectIdsComponent = objectIds
			.split(",")
			.map((val) => {
				return isNaN(val) ? `'${val}'` : val;
			})
			.join(",")
			.replace(/^/, `${idField} IN (`)
			.replace(/$/, ")");

		sqlWhereComponents.push(objectIdsComponent);
	}

	if (geometry && geometryField) {
		var geomComponent = getGeometryQuery(
			geometry,
			geometryField,
			inSR,
			spatialRel,
			dbWKID
		);
		sqlWhereComponents.push(geomComponent);
	}

	return " WHERE " + sqlWhereComponents.join(" AND ");
}

module.exports = {
	buildSqlQuery,
};

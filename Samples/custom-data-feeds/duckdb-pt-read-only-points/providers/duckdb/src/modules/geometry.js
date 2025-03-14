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

const { arcgisToGeoJSON } = require("./terraformer");

function getGeometryQuery(
	geometry,
	geometryField,
	inSR,
	spatialRel = "esriSpatialRelIntersects",
	dbSR = 4326
) {
	// reference https://developers.arcgis.com/rest/services-reference/enterprise/query-feature-service-layer/ 
	// geometry can be comma delimited or a couple different types of json. toGeoJsonString helps simplify this and uses terraformer
	var rawGeomFilter = "";
	try {
		rawGeomFilter = JSON.parse(geometry);
	} catch (error) {
		rawGeomFilter = geometry.split(",").map((item) => Number(item.trim()));
	}
	var geometryFilter = `ST_GeomFromGeoJSON('${toGeoJsonString(
		rawGeomFilter
	)}')`;

	// reference above link - inSR can be null, a wkid string, a wkt string, or nested within the geometry json
	inSR = getSpatialReference(rawGeomFilter, inSR, dbSR);
	if (inSR != dbSR) {
		geometryFilter = `ST_TRANSFORM(${geometryFilter},'EPSG:${inSR}','EPSG:${dbSR}',TRUE)`;
	}

	var geomComponent = "";
	switch (spatialRel) {
		case "esriSpatialRelIntersects":
			geomComponent = `ST_Intersects_Extent(${geometryField}, ${geometryFilter})`;
			break;
		case "esriSpatialRelContains":
			geomComponent = `ST_Contains(${geometryField}, ${geometryFilter})`;
			break;
		case "esriSpatialRelWithin":
			geomComponent = `ST_Within(${geometryField}, ${geometryFilter})`;
			break;
		case "esriSpatialRelCrosses":
			geomComponent = `ST_Crosses(${geometryField}, ${geometryFilter})`;
			break;
		case "esriSpatialRelOverlaps":
			geomComponent = `ST_Overlaps(${geometryField}, ${geometryFilter})`;
			break;
		case "esriSpatialRelTouches":
			geomComponent = `ST_Touches(${geometryField}, ${geometryFilter})`;
			break;
		default:
			throw new Error(`Unsupported spatial relation: ${spatialRel}`);
	}
	return geomComponent;
}

function getSpatialReference(rawGeomFilter, inSR, dbSR) {
	if (inSR) {
		if (typeof inSR === "string") {
			try {
				const parsed = JSON.parse(inSR);
				return parsed.spatialReference?.wkid || parseInt(inSR);
			} catch {
				return parseInt(inSR);
			}
		}
		return inSR;
	}

	if (!rawGeomFilter) return dbSR;

	const { spatialReference } = rawGeomFilter || {};
	if (spatialReference) {
		if ("wkid" in spatialReference) {
			const { wkid, latestWkid } = spatialReference;
			return latestWkid === dbSR ? latestWkid : wkid;
		} else if ("wkt" in spatialReference) {
			// TODO: implement wkt parsing with something like https://github.com/proj4js/wkt-parser
			throw new Error("WKT string parsing not supported");
		}
	}
	return dbSR;
}

function toGeoJsonString(filter) {
	var geojson = {};
	if (isSinglePointArray(filter)) {
		geojson = {
			type: "Point",
			coordinates: filter.map(Number),
		};
	} else if (isEnvelopeArray(filter)) {
		geojson = {
			type: "Polygon",
			coordinates: [
				[
					[filter[0], filter[1]], // Bottom-left corner
					[filter[2], filter[1]], // Bottom-right corner
					[filter[2], filter[3]], // Top-right corner
					[filter[0], filter[3]], // Top-left corner
					[filter[0], filter[1]], // Closing the polygon
				],
			],
		};
	} else {
		// terraformer conversion function
		geojson = arcgisToGeoJSON(filter);
	}
	return JSON.stringify(geojson);
}

function isSinglePointArray(pointArray) {
	if (!Array.isArray(pointArray)) {
		return false;
	}
	if (pointArray.length !== 2) {
		return false;
	}
	return pointArray.every((item) => typeof item === "number");
}

function isEnvelopeArray(envelopeArray) {
	if (!Array.isArray(envelopeArray)) {
		return false;
	}
	if (envelopeArray.length !== 4) {
		return false;
	}
	return envelopeArray.every((item) => typeof item === "number");
}

function getExtentFromGeoJson(geoJsonPolygon, dbWKID) {
	const coordinates = geoJsonPolygon.coordinates[0];
	let minX = Infinity,
		minY = Infinity,
		maxX = -Infinity,
		maxY = -Infinity;
	coordinates.forEach(([longitude, latitude]) => {
		if (longitude < minX) minX = longitude;
		if (longitude > maxX) maxX = longitude;
		if (latitude < minY) minY = latitude;
		if (latitude > maxY) maxY = latitude;
	});
	return {
		xmin: minX,
		ymin: minY,
		xmax: maxX,
		ymax: maxY,
		spatialReference: {
			wkid: dbWKID,
		},
	};
}

module.exports = {
	getGeometryQuery,
	getExtentFromGeoJson,
};

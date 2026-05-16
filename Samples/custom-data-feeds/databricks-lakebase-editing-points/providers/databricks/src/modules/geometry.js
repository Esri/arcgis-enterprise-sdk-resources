const { arcgisToGeoJSON } = require("./terraformer");

/**
 * Build a parameterized PostGIS spatial filter clause.
 * Returns { clause, params, nextParamIndex } or null if geometry can't be parsed.
 */
function getGeometryFilter(
	geometry,
	geometryField,
	inSR,
	spatialRel = "esriSpatialRelIntersects",
	dbSR = 4326,
	paramIndex = 1
) {
	const geoJson = parseGeometryToGeoJson(geometry);
	if (!geoJson) return null;

	const params = [JSON.stringify(geoJson)];

	// Build the PostGIS geometry expression with SRID
	let geomExpr = `ST_SetSRID(ST_GeomFromGeoJSON($${paramIndex}), ${Number(dbSR)})`;

	// If inSR differs from target SRID, transform
	const sourceSR = parseSpatialReference(geometry, inSR, dbSR);
	if (sourceSR && sourceSR !== Number(dbSR)) {
		geomExpr = `ST_Transform(ST_SetSRID(ST_GeomFromGeoJSON($${paramIndex}), ${sourceSR}), ${Number(dbSR)})`;
	}

	const clause = getSpatialPredicate(spatialRel, geometryField, geomExpr);
	return { clause, params, nextParamIndex: paramIndex + 1 };
}

/**
 * Map ArcGIS spatialRel to native PostGIS predicate.
 */
function getSpatialPredicate(spatialRel, geomColumn, geomParam) {
	switch (spatialRel) {
		case "esriSpatialRelIntersects":
			return `ST_Intersects(${geomColumn}, ${geomParam})`;
		case "esriSpatialRelContains":
			return `ST_Contains(${geomColumn}, ${geomParam})`;
		case "esriSpatialRelWithin":
			return `ST_Within(${geomColumn}, ${geomParam})`;
		case "esriSpatialRelCrosses":
			return `ST_Crosses(${geomColumn}, ${geomParam})`;
		case "esriSpatialRelOverlaps":
			return `ST_Overlaps(${geomColumn}, ${geomParam})`;
		case "esriSpatialRelTouches":
			return `ST_Touches(${geomColumn}, ${geomParam})`;
		default:
			return `ST_Intersects(${geomColumn}, ${geomParam})`;
	}
}

/**
 * Parse ArcGIS geometry input into GeoJSON.
 * Handles: comma-delimited envelope, JSON envelope, JSON point/polygon/polyline, raw GeoJSON.
 */
function parseGeometryToGeoJson(geometry) {
	var raw = "";
	try {
		raw = JSON.parse(geometry);
	} catch (error) {
		// Try comma-delimited envelope: xmin,ymin,xmax,ymax
		const parts = String(geometry)
			.split(",")
			.map((item) => Number(item.trim()));
		if (parts.length === 4 && parts.every((n) => !isNaN(n))) {
			return {
				type: "Polygon",
				coordinates: [
					[
						[parts[0], parts[1]],
						[parts[2], parts[1]],
						[parts[2], parts[3]],
						[parts[0], parts[3]],
						[parts[0], parts[1]],
					],
				],
			};
		}
		if (parts.length === 2 && parts.every((n) => !isNaN(n))) {
			return { type: "Point", coordinates: parts };
		}
		return null;
	}

	// Esri envelope object
	if (raw.xmin !== undefined) {
		return {
			type: "Polygon",
			coordinates: [
				[
					[raw.xmin, raw.ymin],
					[raw.xmax, raw.ymin],
					[raw.xmax, raw.ymax],
					[raw.xmin, raw.ymax],
					[raw.xmin, raw.ymin],
				],
			],
		};
	}

	// Esri point
	if (raw.x !== undefined && raw.y !== undefined) {
		return { type: "Point", coordinates: [raw.x, raw.y] };
	}

	// Esri polygon/polyline - use terraformer
	if (raw.rings || raw.paths) {
		return arcgisToGeoJSON(raw);
	}

	// Already GeoJSON
	if (raw.type && raw.coordinates) {
		return raw;
	}

	return null;
}

// Esri-proprietary WKIDs that PostGIS doesn't recognize -> EPSG equivalents
const ESRI_TO_EPSG = {
	102100: 3857,
	102113: 3785,
};

function toEpsg(wkid) {
	return ESRI_TO_EPSG[wkid] || wkid;
}

/**
 * Parse spatial reference from geometry or inSR parameter.
 * Always returns an EPSG-compatible SRID (maps Esri-proprietary WKIDs).
 */
function parseSpatialReference(geometry, inSR, dbSR) {
	if (inSR) {
		if (typeof inSR === "number") return toEpsg(inSR);
		if (typeof inSR === "string") {
			try {
				const parsed = JSON.parse(inSR);
				const wkid = parsed.spatialReference?.latestWkid || parsed.spatialReference?.wkid || parsed.latestWkid || parsed.wkid || parseInt(inSR);
				return toEpsg(wkid);
			} catch {
				return toEpsg(parseInt(inSR));
			}
		}
		if (typeof inSR === "object") {
			const wkid = inSR.spatialReference?.latestWkid || inSR.spatialReference?.wkid || inSR.latestWkid || inSR.wkid || null;
			return wkid ? toEpsg(wkid) : null;
		}
		return toEpsg(inSR);
	}

	// Check if geometry JSON contains spatialReference
	var raw = null;
	try {
		raw = typeof geometry === "string" ? JSON.parse(geometry) : geometry;
	} catch {
		return dbSR;
	}
	if (!raw) return dbSR;

	const { spatialReference } = raw || {};
	if (spatialReference) {
		if (spatialReference.latestWkid) return toEpsg(spatialReference.latestWkid);
		if (spatialReference.wkid) return toEpsg(spatialReference.wkid);
	}
	return dbSR;
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
	getGeometryFilter,
	getExtentFromGeoJson,
};

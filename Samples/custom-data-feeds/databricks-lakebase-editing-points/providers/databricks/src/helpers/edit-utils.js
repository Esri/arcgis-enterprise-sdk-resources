const IDENTIFIER_PATTERN = /^[a-zA-Z0-9_]+$/;

function validateIdentifier(name) {
	if (!name || typeof name !== "string") {
		throw new Error("Invalid identifier: must be a non-empty string.");
	}
	const trimmed = name.trim();
	if (!IDENTIFIER_PATTERN.test(trimmed)) {
		throw new Error(
			`Invalid identifier: "${trimmed}". Only alphanumeric characters and underscores are allowed.`
		);
	}
	return trimmed;
}

/**
 * Validate that geometry coordinates are valid numbers (not NaN or Infinity).
 */
function isValidGeometry(geom) {
	if (!geom || !geom.coordinates) return false;
	const checkCoord = (coord) => {
		if (typeof coord === "number") {
			return Number.isFinite(coord);
		}
		if (Array.isArray(coord)) {
			return coord.every(checkCoord);
		}
		return false;
	};
	return checkCoord(geom.coordinates);
}

/**
 * Convert Esri JSON or GeoJSON geometry to GeoJSON for PostGIS ST_GeomFromGeoJSON.
 * Handles: GeoJSON (passthrough), Esri Point, Polygon, Polyline, MultiPoint.
 */
function toGeoJSON(geom) {
	if (!geom) return null;
	// Already GeoJSON
	if (geom.type && geom.coordinates) return geom;
	// Esri point
	if (geom.x !== undefined && geom.y !== undefined) {
		return { type: "Point", coordinates: [geom.x, geom.y] };
	}
	// Esri multipoint
	if (geom.points) {
		return { type: "MultiPoint", coordinates: geom.points };
	}
	// Esri polygon
	if (geom.rings) {
		return { type: "Polygon", coordinates: geom.rings };
	}
	// Esri polyline
	if (geom.paths) {
		return geom.paths.length === 1
			? { type: "LineString", coordinates: geom.paths[0] }
			: { type: "MultiLineString", coordinates: geom.paths };
	}
	return null;
}

module.exports = { validateIdentifier, toGeoJSON, isValidGeometry };

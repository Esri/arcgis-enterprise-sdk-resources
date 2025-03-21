// From terraformer https://github.com/terraformer-js/terraformer/blob/main/packages/arcgis/src/arcgis.js
// TODO: Consider removing this code and including terraformer as a dependency

const isNumber = (n) => !isNaN(parseFloat(n)) && isFinite(n);

const edgeIntersectsEdge = (a1, a2, b1, b2) => {
	var uaT =
		(b2[0] - b1[0]) * (a1[1] - b1[1]) - (b2[1] - b1[1]) * (a1[0] - b1[0]);
	var ubT =
		(a2[0] - a1[0]) * (a1[1] - b1[1]) - (a2[1] - a1[1]) * (a1[0] - b1[0]);
	var uB =
		(b2[1] - b1[1]) * (a2[0] - a1[0]) - (b2[0] - b1[0]) * (a2[1] - a1[1]);

	if (uB !== 0) {
		var ua = uaT / uB;
		var ub = ubT / uB;

		if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
			return true;
		}
	}

	return false;
};

const arraysIntersectArrays = (a, b) => {
	if (isNumber(a[0][0])) {
		if (isNumber(b[0][0])) {
			for (var i = 0; i < a.length - 1; i++) {
				for (var j = 0; j < b.length - 1; j++) {
					if (edgeIntersectsEdge(a[i], a[i + 1], b[j], b[j + 1])) {
						return true;
					}
				}
			}
		} else {
			for (var k = 0; k < b.length; k++) {
				if (arraysIntersectArrays(a, b[k])) {
					return true;
				}
			}
		}
	} else {
		for (var l = 0; l < a.length; l++) {
			if (arraysIntersectArrays(a[l], b)) {
				return true;
			}
		}
	}
	return false;
};

const coordinatesContainPoint = (coordinates, point) => {
	let contains = false;
	for (let i = -1, l = coordinates.length, j = l - 1; ++i < l; j = i) {
		if (
			((coordinates[i][1] <= point[1] && point[1] < coordinates[j][1]) ||
				(coordinates[j][1] <= point[1] && point[1] < coordinates[i][1])) &&
			point[0] <
				((coordinates[j][0] - coordinates[i][0]) *
					(point[1] - coordinates[i][1])) /
					(coordinates[j][1] - coordinates[i][1]) +
					coordinates[i][0]
		) {
			contains = !contains;
		}
	}
	return contains;
};

const pointsEqual = (a, b) => {
	for (let i = 0; i < a.length; i++) {
		if (a[i] !== b[i]) {
			return false;
		}
	}

	return true;
};

const arrayIntersectsArray = (a, b) => {
	for (let i = 0; i < a.length - 1; i++) {
		for (let j = 0; j < b.length - 1; j++) {
			if (edgeIntersectsEdge(a[i], a[i + 1], b[j], b[j + 1])) {
				return true;
			}
		}
	}
	return false;
};

// checks if the first and last points of a ring are equal and closes the ring
const closeRing = (coordinates) => {
	if (!pointsEqual(coordinates[0], coordinates[coordinates.length - 1])) {
		coordinates.push(coordinates[0]);
	}
	return coordinates;
};

// determine if polygon ring coordinates are clockwise. clockwise signifies outer ring, counter-clockwise an inner ring
// or hole. this logic was found at http://stackoverflow.com/questions/1165647/how-to-determine-if-a-list-of-polygon-
// points-are-in-clockwise-order
const ringIsClockwise = (ringToTest) => {
	var total = 0;
	var i = 0;
	var rLength = ringToTest.length;
	var pt1 = ringToTest[i];
	var pt2;
	for (i; i < rLength - 1; i++) {
		pt2 = ringToTest[i + 1];
		total += (pt2[0] - pt1[0]) * (pt2[1] + pt1[1]);
		pt1 = pt2;
	}
	return total >= 0;
};

// shallow object clone for feature properties and attributes
// from http://jsperf.com/cloning-an-object/2
const shallowClone = (obj) => {
	var target = {};
	for (var i in obj) {
		// both arcgis attributes and geojson props are just hardcoded keys
		if (obj.hasOwnProperty(i)) {
			// eslint-disable-line no-prototype-builtins
			target[i] = obj[i];
		}
	}
	return target;
};

const coordinatesContainCoordinates = (outer, inner) => {
	var intersects = arrayIntersectsArray(outer, inner);
	var contains = coordinatesContainPoint(outer, inner[0]);
	if (!intersects && contains) {
		return true;
	}
	return false;
};

// used for checking for holes in arcgis rings
const convertRingsToGeoJSON = (rings) => {
	var outerRings = [];
	var holes = [];
	var x; // iterator
	var outerRing; // current outer ring being evaluated
	var hole; // current hole being evaluated

	// for each ring
	for (var r = 0; r < rings.length; r++) {
		var ring = closeRing(rings[r].slice(0));
		if (ring.length < 4) {
			continue;
		}
		// is this ring an outer ring? is it clockwise?
		if (ringIsClockwise(ring)) {
			var polygon = [ring.slice().reverse()]; // wind outer rings counterclockwise for RFC 7946 compliance
			outerRings.push(polygon); // push to outer rings
		} else {
			holes.push(ring.slice().reverse()); // wind inner rings clockwise for RFC 7946 compliance
		}
	}

	var uncontainedHoles = [];

	// while there are holes left...
	while (holes.length) {
		// pop a hole off out stack
		hole = holes.pop();

		// loop over all outer rings and see if they contain our hole.
		var contained = false;
		for (x = outerRings.length - 1; x >= 0; x--) {
			outerRing = outerRings[x][0];
			if (coordinatesContainCoordinates(outerRing, hole)) {
				// the hole is contained push it into our polygon
				outerRings[x].push(hole);
				contained = true;
				break;
			}
		}

		// ring is not contained in any outer ring
		// sometimes this happens https://github.com/Esri/esri-leaflet/issues/320
		if (!contained) {
			uncontainedHoles.push(hole);
		}
	}

	// if we couldn't match any holes using contains we can try intersects...
	while (uncontainedHoles.length) {
		// pop a hole off out stack
		hole = uncontainedHoles.pop();

		// loop over all outer rings and see if any intersect our hole.
		var intersects = false;

		for (x = outerRings.length - 1; x >= 0; x--) {
			outerRing = outerRings[x][0];
			if (arrayIntersectsArray(outerRing, hole)) {
				// the hole is contained push it into our polygon
				outerRings[x].push(hole);
				intersects = true;
				break;
			}
		}

		if (!intersects) {
			outerRings.push([hole.reverse()]);
		}
	}

	if (outerRings.length === 1) {
		return {
			type: "Polygon",
			coordinates: outerRings[0],
		};
	} else {
		return {
			type: "MultiPolygon",
			coordinates: outerRings,
		};
	}
};

const getId = (attributes, idAttribute) => {
	var keys = idAttribute
		? [idAttribute, "OBJECTID", "FID"]
		: ["OBJECTID", "FID"];
	for (var i = 0; i < keys.length; i++) {
		var key = keys[i];
		if (
			key in attributes &&
			(typeof attributes[key] === "string" ||
				typeof attributes[key] === "number")
		) {
			return attributes[key];
		}
	}
	throw Error("No valid id attribute found");
};

function arcgisToGeoJSON(arcgis, idAttribute) {
	var geojson = {};

	if (arcgis.features) {
		geojson.type = "FeatureCollection";
		geojson.features = [];
		for (var i = 0; i < arcgis.features.length; i++) {
			geojson.features.push(arcgisToGeoJSON(arcgis.features[i], idAttribute));
		}
	}

	if (typeof arcgis.x === "number" && typeof arcgis.y === "number") {
		geojson.type = "Point";
		geojson.coordinates = [arcgis.x, arcgis.y];
		if (typeof arcgis.z === "number") {
			geojson.coordinates.push(arcgis.z);
		}
	}

	if (arcgis.points) {
		geojson.type = "MultiPoint";
		geojson.coordinates = arcgis.points.slice(0);
	}

	if (arcgis.paths) {
		if (arcgis.paths.length === 1) {
			geojson.type = "LineString";
			geojson.coordinates = arcgis.paths[0].slice(0);
		} else {
			geojson.type = "MultiLineString";
			geojson.coordinates = arcgis.paths.slice(0);
		}
	}

	if (arcgis.rings) {
		geojson = convertRingsToGeoJSON(arcgis.rings.slice(0));
	}

	if (
		typeof arcgis.xmin === "number" &&
		typeof arcgis.ymin === "number" &&
		typeof arcgis.xmax === "number" &&
		typeof arcgis.ymax === "number"
	) {
		geojson.type = "Polygon";
		geojson.coordinates = [
			[
				[arcgis.xmax, arcgis.ymax],
				[arcgis.xmin, arcgis.ymax],
				[arcgis.xmin, arcgis.ymin],
				[arcgis.xmax, arcgis.ymin],
				[arcgis.xmax, arcgis.ymax],
			],
		];
	}

	if (arcgis.geometry || arcgis.attributes) {
		geojson.type = "Feature";
		geojson.geometry = arcgis.geometry
			? arcgisToGeoJSON(arcgis.geometry)
			: null;
		geojson.properties = arcgis.attributes
			? shallowClone(arcgis.attributes)
			: null;
		if (arcgis.attributes) {
			try {
				geojson.id = getId(arcgis.attributes, idAttribute);
			} catch (err) {
				// don't set an id
			}
		}
	}

	// if no valid geometry was encountered
	if (JSON.stringify(geojson.geometry) === JSON.stringify({})) {
		geojson.geometry = null;
	}

	return geojson;
}

module.exports = {
	arcgisToGeoJSON,
};

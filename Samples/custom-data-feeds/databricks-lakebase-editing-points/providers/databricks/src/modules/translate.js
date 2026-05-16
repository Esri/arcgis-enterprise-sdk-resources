function translateToGeoJSON(data, config) {
	const columns = Object.keys(data[0]);
	const geojsonColumn = `${config.geomOutColumn}_geojson`;
	return {
		type: "FeatureCollection",
		features: data.map((row) =>
			formatFeature(row, columns, config.idField, config.geomOutColumn, geojsonColumn, config.excludedFields || [])
		),
	};
}

function formatFeature(values, columns, idField, geometryField, geojsonColumn, excludedFields) {
	let feature = {
		type: "Feature",
		properties: {},
		geometry: {},
	};

	for (let i = 0; i < columns.length; i++) {
		const value = values[columns[i]];

		if (excludedFields.includes(columns[i])) {
			continue;
		}

		// The geojson alias column holds the ST_AsGeoJSON output
		if (columns[i] === geojsonColumn) {
			feature.geometry = typeof value === "string" ? JSON.parse(value) : value;
		} else if (columns[i] === geometryField) {
			// Skip the raw geometry column (binary/hex from PostGIS)
			continue;
		} else {
			if (columns[i] === idField) {
				if (!isValidId(value)) {
					console.warn(`Invalid ID value: ${value}`);
				}
			}
			feature.properties[columns[i]] = value;
		}
	}
	return feature;
}

// Max ID value supported by feature server:
// https://koopjs.github.io/docs/usage/provider#setting-provider-metadata-in-getdata
function isValidId(value) {
	const parsedValue = parseInt(value);
	return 0 <= parsedValue && parsedValue <= 2147483647;
}

module.exports = {
	translateToGeoJSON,
};

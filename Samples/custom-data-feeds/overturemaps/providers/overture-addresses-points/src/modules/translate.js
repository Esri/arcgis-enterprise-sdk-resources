function translateToGeoJSON(data, config) {
	const columns = Object.keys(data[0]);
	return {
		type: "FeatureCollection",
		features: data.map((row) =>
			formatFeature(row, columns, config.idField, config.geomOutColumn)
		),
	};
}

function formatFeature(values, columns, idField, geometryField) {
	let feature = {
		type: "Feature",
		properties: {},
		geometry: {},
	};

	for (let i = 0; i < columns.length; i++) {
		const value = values[columns[i]];

		if (columns[i] == geometryField) {
			feature.geometry = JSON.parse(value);
		} else {
			if (columns[i] == idField) {
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

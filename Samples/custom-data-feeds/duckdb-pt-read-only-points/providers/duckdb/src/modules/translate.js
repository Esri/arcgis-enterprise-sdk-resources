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

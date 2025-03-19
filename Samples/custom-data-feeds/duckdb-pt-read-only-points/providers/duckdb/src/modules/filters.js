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

function generateFiltersApplied(geoParams, idField, geometryField) {
	const {
		where,
		objectIds,
		orderByFields,
		resultOffset,
		geometry,
		resultRecordCount,
		returnDistinctValues,
	} = geoParams;

	const filtersApplied = {};

	// don't apply filters if asking for unique values of a column for symbology
	if (returnDistinctValues) {
		return filtersApplied;
	}

	if (where) {
		filtersApplied.where = true;
	}

	if (objectIds && idField) {
		filtersApplied.objectIds = true;
	}

	if (resultOffset) {
		filtersApplied.offset = true;
	}

	if (orderByFields) {
		filtersApplied.orderByFields = true;
	}

	if (geometry && geometryField) {
		filtersApplied.geometry = true;
	}

	if (resultRecordCount) {
		filtersApplied.limit = true;
	}

	return filtersApplied;
}

module.exports = {
	generateFiltersApplied,
};

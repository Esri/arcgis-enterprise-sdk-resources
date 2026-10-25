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

	if (returnDistinctValues) {
		filtersApplied.returnDistinctValues = true;
	}

	if (where) {
		filtersApplied.where = true;
	}

	if (objectIds && idField) {
		filtersApplied.objectIds = true;
	}

	if (resultOffset) {
		filtersApplied.resultOffset = true;
	}

	if (orderByFields) {
		filtersApplied.orderByFields = true;
	}

	if (geometry && geometryField) {
		filtersApplied.geometry = true;
	}

	if (resultRecordCount) {
		filtersApplied.resultRecordCount = true;
	}

	return filtersApplied;
}

module.exports = {
	generateFiltersApplied,
};

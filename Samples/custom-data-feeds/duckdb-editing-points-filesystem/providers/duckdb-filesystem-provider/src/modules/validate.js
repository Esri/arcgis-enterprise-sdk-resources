function validateConfig(config) {
	if (config.duckdbfs.sources.deltaTable) {
		var deltaRequiredFields = [
			"deltaUrl",
			"azureStorageConnStr",
			"WKBColumn",
			"geomOutColumn",
			"idField",
			"maxRecordCountPerPage",
			"properties",
			"dbWKID",
		];
		const allKeysTruthy = deltaRequiredFields.every(
			(key) => config.duckdbfs.sources.deltaRequiredFields[key]
		);
		if (!allKeysTruthy) {
			throw new Error("Error with required key in default.json file");
		}
		return;
	}

	if (config.duckdbfs.sources.minio) {
		var minioRequiredFields = [
			"s3Url",
			"s3Region",
			"s3AccessKeyId",
			"s3Secret",
			"s3BucketName",
			"WKBColumn",
			"geomOutColumn",
			"idField",
			"maxRecordCountPerPage",
			"properties",
			"dbWKID",
		];
		const allKeysTruthy = minioRequiredFields.every(
			(key) => config.duckdbfs.sources.minio[key]
		);
		if (!allKeysTruthy) {
			throw new Error("Error with required key in default.json file");
		}
		return;
	}

	return;
}

module.exports = {
	validateConfig,
};

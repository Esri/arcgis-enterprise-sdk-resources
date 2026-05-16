function validateConfig(config) {
	if (config.databricks && config.databricks.sources && config.databricks.sources.lakebase) {
		var lakebaseRequiredFields = [
			"DATABRICKS_TOKEN",
			"DATABRICKS_SERVER_HOSTNAME",
			"LAKEBASE_HOST",
			"LAKEBASE_DATABASE",
			"geomOutColumn",
			"idField",
			"maxRecordCountPerPage",
			"properties",
			"dbWKID",
		];
		const allKeysTruthy = lakebaseRequiredFields.every(
			(key) => config.databricks.sources.lakebase[key]
		);
		if (!allKeysTruthy) {
			throw new Error("Error with required key in default.json file");
		}
		return;
	}

	if (config.duckdb.sources.minio) {
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
			(key) => config.duckdb.sources.minio[key]
		);
		if (!allKeysTruthy) {
			throw new Error("Error with required key in default.json file");
		}
		return;
	}

	if (config.duckdb.sources.localParquet) {
		var localParquetRequiredFields = ["dfsConfigPath"];
		const allKeysTruthy = localParquetRequiredFields.every(
			(key) => config.duckdb.sources.localParquet[key]
		);
		if (!allKeysTruthy) {
			throw new Error("Error with required key in default.json file");
		}
		return;
	}

	console.warn("Please set a datasource in the default.json file");
	return;
}

module.exports = {
	validateConfig,
};

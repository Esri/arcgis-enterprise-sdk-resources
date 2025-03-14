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

function validateConfig(config) {
	if (config.duckdb.sources.deltaTable) {
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
			(key) => config.duckdb.sources.deltaRequiredFields[key]
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
		var localParquetRequiredFields = [
			"path",
			"WKBColumn",
			"geomOutColumn",
			"idField",
			"maxRecordCountPerPage",
			"properties",
			"dbWKID",
		];
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

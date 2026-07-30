const { validateIdentifier } = require("./edit-utils");

async function deleteRows(deletes, pool, config) {
	const idField = config.idField;
	const schema = config.LAKEBASE_SCHEMA || "public";
	const table = config.LAKEBASE_TABLE || config.properties.name;

	// Validate config-driven identifiers once, not per-row
	const safeSchema = validateIdentifier(schema);
	const safeTable = validateIdentifier(table);
	const safeIdField = validateIdentifier(idField);

	// SQL is the same for every row — build once
	const sql = `DELETE FROM ${safeSchema}.${safeTable} WHERE ${safeIdField} = $1`;

	const deleteResults = [];

	for (const objectId of deletes) {
		try {
			const res = await pool.query(sql, [objectId]);

			if (res.rowCount > 0) {
				console.log(`[${new Date().toISOString()}] DELETE success, ${idField}=${objectId}`);
				deleteResults.push({ success: true, objectId });
			} else {
				console.warn(`[${new Date().toISOString()}] DELETE no matching row, ${idField}=${objectId}`);
				deleteResults.push({
					success: false,
					objectId,
					error: { code: 1018, description: "Object not found for delete." },
				});
			}
		} catch (error) {
			console.error(`[${new Date().toISOString()}] DELETE failed, ${idField}=${objectId}:`, error.message);
			deleteResults.push({
				success: false,
				objectId,
				error: { code: 1018, description: "Internal error during object delete." },
			});
		}
	}

	return deleteResults;
}

module.exports = { deleteRows };

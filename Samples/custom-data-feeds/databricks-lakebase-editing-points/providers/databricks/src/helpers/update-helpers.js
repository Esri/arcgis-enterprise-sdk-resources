const { validateIdentifier, toGeoJSON } = require("./edit-utils");

async function updateRows(updates, pool, config) {
	const idField = config.idField;
	const geomCol = config.geomOutColumn;
	const schema = config.LAKEBASE_SCHEMA || "public";
	const table = config.LAKEBASE_TABLE || config.properties.name;
	const srid = config.dbWKID || 4326;

	// Validate config-driven identifiers once, not per-feature
	const safeSchema = validateIdentifier(schema);
	const safeTable = validateIdentifier(table);
	const safeIdField = validateIdentifier(idField);
	const safeGeomCol = validateIdentifier(geomCol);

	const updateResults = [];

	for (const feature of updates) {
		try {
			const attributes = feature.attributes || feature.properties || {};
			const geometry = toGeoJSON(feature.geometry);

			const objectId = attributes[idField];
			if (objectId === undefined || objectId === null) {
				throw new Error(`Update feature missing identifier attribute: ${idField}`);
			}

			// Build SET clauses for attributes (skip the idField)
			const setClauses = [];
			const params = [];
			let paramIndex = 1;

			for (const [col, val] of Object.entries(attributes)) {
				if (col === idField) continue;
				validateIdentifier(col);
				setClauses.push(`${col} = $${paramIndex}`);
				params.push(val);
				paramIndex++;
			}

			// Only include geometry in SET if geometry is present 
			if (geometry && geometry.coordinates) {
				setClauses.push(`${safeGeomCol} = ST_SetSRID(ST_GeomFromGeoJSON($${paramIndex}), ${Number(srid)})`);
				params.push(JSON.stringify(geometry));
				paramIndex++;
			}

			if (setClauses.length === 0) {
				throw new Error("UPDATE requires at least one column to update");
			}

			// WHERE clause
			params.push(objectId);
			const sql = `UPDATE ${safeSchema}.${safeTable} SET ${setClauses.join(", ")} WHERE ${safeIdField} = $${paramIndex}`;

			const res = await pool.query(sql, params);

			if (res.rowCount === 0) {
				updateResults.push({
					success: false,
					objectId,
					error: { code: 1019, description: "Object not found for update." },
				});
			} else {
				console.log(`[${new Date().toISOString()}] UPDATE success, ${idField}=${objectId}`);
				updateResults.push({ success: true, objectId });
			}
		} catch (error) {
			console.error(`[${new Date().toISOString()}] UPDATE failed:`, error.message);
			updateResults.push({
				success: false,
				objectId: feature.attributes?.[idField] || feature.properties?.[idField],
				error: { code: 1019, description: "Internal error during object update." },
			});
		}
	}

	return updateResults;
}

module.exports = { updateRows };

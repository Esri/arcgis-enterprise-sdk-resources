const { validateIdentifier, toGeoJSON } = require("./edit-utils");

async function insertRows(adds, pool, config) {
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

	const addResults = [];

	for (const feature of adds) {
		try {
			const attributes = feature.attributes || feature.properties || {};
			const geometry = toGeoJSON(feature.geometry);

			// Filter out idField and geometry column from attributes
			const attrColumns = Object.keys(attributes).filter(
				(col) => col !== idField && col !== geomCol
			);

			// Fetch next available objectId
			// Note: if your table uses SERIAL/IDENTITY, replace this with RETURNING
			const maxRes = await pool.query(
				`SELECT COALESCE(MAX(${safeIdField}), 0) + 1 AS next_id FROM ${safeSchema}.${safeTable}`
			);
			const objectId = maxRes.rows[0]?.next_id || 1;

			const columns = [safeIdField];
			const placeholders = ["$1"];
			const params = [objectId];
			let paramIndex = 2;

			// Add attribute columns
			for (const col of attrColumns) {
				validateIdentifier(col);
				columns.push(col);
				placeholders.push(`$${paramIndex}`);
				params.push(attributes[col]);
				paramIndex++;
			}

			// Add geometry if present and valid
			if (geometry && geometry.coordinates) {
				columns.push(safeGeomCol);
				placeholders.push(
					`ST_SetSRID(ST_GeomFromGeoJSON($${paramIndex}), ${Number(srid)})`
				);
				params.push(JSON.stringify(geometry));
				paramIndex++;
			}

			const sql = `INSERT INTO ${safeSchema}.${safeTable} (${columns.join(", ")}) VALUES (${placeholders.join(", ")})`;
			await pool.query(sql, params);

			console.log(`[${new Date().toISOString()}] INSERT success, ${idField}=${objectId}`);
			addResults.push({ success: true, objectId });
		} catch (error) {
			console.error(`[${new Date().toISOString()}] INSERT failed:`, error.message);
			addResults.push({
				success: false,
				error: { code: 1017, description: "Internal error during object insert." },
			});
		}
	}

	return addResults;
}

module.exports = { insertRows };

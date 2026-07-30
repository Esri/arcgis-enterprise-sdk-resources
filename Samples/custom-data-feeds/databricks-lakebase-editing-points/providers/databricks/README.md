# Databricks Lakebase Provider

ArcGIS Custom Data Feed (CDF) provider that connects to [Databricks Lakebase](https://www.databricks.com/product/lakebase) (serverless PostgreSQL + PostGIS) and serves data as an ArcGIS Feature Service.

## Prerequisites

- **ArcGIS Enterprise SDK** with Custom Data Feeds
- **Node.js with npm** 
- A **Databricks workspace** with a Lakebase instance (Autoscaling)
- A **Databricks Personal Access Token (PAT)**
- **PostGIS** extension enabled on your Lakebase database

## Creating a Lakebase Instance

1. In your Databricks workspace, navigate to **SQL** → **Lakebase** in the left sidebar.
2. Click **Create project**. Give it a name (e.g. `myproject`). This creates a project with a default `production` branch and `primary` endpoint.
3. Once created, click into the project to find your connection details:
   - **Host** — the PostgreSQL hostname (e.g. `ep-xxxx.database.westus2.azuredatabricks.net`)
   - **Port** — `5432`
   - **Database** — `databricks_postgres` (default)
4. Generate a **Personal Access Token (PAT)** if you don't already have one:
   - Click your username (top-right) → **Settings** → **Developer** → **Access tokens** → **Generate new token**
5. Note the resource path for your endpoint — you'll need it for the config:  
   `projects/<project-name>/branches/production/endpoints/primary`

## Lakebase Database Setup

Connect to your Lakebase instance with the Databricks SQL web editor and ensure PostGIS is enabled:

```sql
CREATE EXTENSION IF NOT EXISTS postgis;
```

Your table must have:
- A **geometry column** (e.g. `geometry`) with a valid PostGIS geometry type and SRID
- An **integer ID column** (e.g. `objectid`) with unique values between 0 and 2,147,483,647

Example table creation:

```sql
CREATE TABLE public.taxi (
  objectid SERIAL PRIMARY KEY,
  geometry geometry(Point, 4326),
  -- additional columns...
);
```

## Configuration

1. Copy the example config:

   ```
   cp config/default.example.json config/default.json
   ```

2. Fill in your values in `config/default.json`:

   | Field | Description |
   |-------|-------------|
   | `DATABRICKS_TOKEN` | Your Databricks Personal Access Token |
   | `DATABRICKS_SERVER_HOSTNAME` | Workspace hostname (e.g. `adb-xxxx.14.azuredatabricks.net`) |
   | `LAKEBASE_HOST` | Lakebase connection endpoint (e.g. `ep-xxxx.database.westus2.azuredatabricks.net`) |
   | `LAKEBASE_PORT` | PostgreSQL port (default: `5432`) |
   | `LAKEBASE_DATABASE` | Database name |
   | `LAKEBASE_ENDPOINT` | Lakebase resource path: `projects/<project>/branches/<branch>/endpoints/<endpoint>` |
   | `LAKEBASE_SCHEMA` | Schema name (default: `public`) |
   | `LAKEBASE_TABLE` | Table name |
   | `LAKEBASE_USER` | Your Databricks account email |
   | `geomOutColumn` | Name of the PostGIS geometry column in your table |
   | `idField` | Name of the integer ID column (must be lowercase) |
   | `dbWKID` | SRID of the geometry column (e.g. `4326`) |
   | `maxRecordCountPerPage` | Max features per request |

> **Note:** `config/default.json` is gitignored. Do not commit it — it contains secrets.

## Finding Lakebase Connection Details

You can find your Lakebase endpoint and host in the Databricks workspace UI, or use the REST API:

```bash
# List projects
curl -H "Authorization: Bearer $TOKEN" \
  https://<workspace>/api/2.0/postgres/projects

# List branches for a project
curl -H "Authorization: Bearer $TOKEN" \
  https://<workspace>/api/2.0/postgres/projects/<project>/branches

# List endpoints for a branch
curl -H "Authorization: Bearer $TOKEN" \
  https://<workspace>/api/2.0/postgres/projects/<project>/branches/<branch>/endpoints
```

The Lakebase host is shown in the endpoint details response.

## Authentication

This provider uses **OAuth token rotation** for Lakebase connections:

1. Your PAT authenticates against the Databricks REST API
2. A short-lived JWT credential is generated via `POST /api/2.0/postgres/credentials`
3. The JWT is used as the PostgreSQL password
4. Tokens are automatically refreshed 5 minutes before expiry

No manual token management is needed after initial configuration.

## Running

```bash
npm install
npm start
```

The CDF server starts on `http://localhost:8080`. The FeatureServer endpoint is available at:

```
http://localhost:8080/databricks/rest/services/lakebase/FeatureServer/0/query
```

## Spatial Queries

The provider supports standard ArcGIS spatial relationship parameters via PostGIS:

| `spatialRel` | PostGIS Function |
|---|---|
| `esriSpatialRelIntersects` | `ST_Intersects` |
| `esriSpatialRelContains` | `ST_Contains` |
| `esriSpatialRelWithin` | `ST_Within` |
| `esriSpatialRelCrosses` | `ST_Crosses` |
| `esriSpatialRelOverlaps` | `ST_Overlaps` |
| `esriSpatialRelTouches` | `ST_Touches` |

Input geometries in Esri spatial references (e.g. Web Mercator 102100) are automatically transformed to the database SRID via `ST_Transform`.

## Project Structure

```
src/
  index.js              # CDF provider registration and server startup
  model.js              # getData() — builds query, executes, returns GeoJSON
  modules/
    sql.js              # Parameterized PostGIS SQL query builder
    geometry.js         # Spatial filter construction (ST_Intersects, etc.)
    translate.js        # PostgreSQL rows → GeoJSON FeatureCollection
    lakebase-pool.js    # pg.Pool management with OAuth token refresh
    validate.js         # Config validation
    terraformer.js      # ArcGIS geometry → GeoJSON conversion
    index.js            # Module exports
config/
  default.json          # Connection config (gitignored)
  default.example.json  # Template with placeholders
```
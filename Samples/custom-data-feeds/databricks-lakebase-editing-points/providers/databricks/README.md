# Editing-enabled Databricks Lakebase provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting point features served from a [Databricks Lakebase](https://www.databricks.com/product/lakebase) instance (serverless PostgreSQL + PostGIS). Your Lakebase table must have a PostGIS geometry column and an integer ID column. The provider uses OAuth token rotation to authenticate against Lakebase and supports standard ArcGIS spatial queries via PostGIS.

## See it in action

https://github.com/user-attachments/assets/3fb3d4ad-ade9-4c37-9dce-0a938063eb58

## Prerequisites

- **ArcGIS Enterprise SDK** with Custom Data Feeds (requires 12.0+)
- **Node.js with npm**
- A **Databricks workspace** with a Lakebase instance (Autoscaling)
- A **Databricks Personal Access Token (PAT)**
- **PostGIS** extension enabled on your Lakebase database

## Provider Quickstart

1.  In a command prompt, run the `cdf createapp databricks-lakebase-editing-points` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **databricks-lakebase-editing-points** directory or an existing custom data app directory,
    run the `cdf createprovider databricks` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/databricks/src**
    directory.
4.  Copy the example config file to create your local config:

    ```
    cp src/config/default.example.json src/config/default.json
    ```

    Then fill in your values in **providers/databricks/src/config/default.json** (see [Configure the Provider](#configure-the-provider) below).
5.  In a command prompt, run the command `npm install pg` to install the needed modules from the **providers/databricks/** directory.
6.  Navigate back to the **databricks-lakebase-editing-points** app-level directory and run `npm run start` to start the custom data app.

### Creating a Lakebase Instance

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

### Lakebase Database Setup

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

## Configure the Provider

1.  In the **providers/databricks/cdconfig.json** file, set `"editingEnabled": true`.

2.  In the **providers/databricks/src/config/default.json** file, configure your Lakebase
    connection.

    ```json
    {
        "databricks": {
            "sources": {
                "lakebase": {
                    "DATABRICKS_TOKEN": "<your-databricks-pat>",
                    "DATABRICKS_SERVER_HOSTNAME": "<your-workspace>.azuredatabricks.net",
                    "LAKEBASE_HOST": "<your-lakebase-instance>.database.cloud.databricks.com",
                    "LAKEBASE_PORT": 5432,
                    "LAKEBASE_DATABASE": "<your_database>",
                    "LAKEBASE_ENDPOINT": "projects/<project-id>/branches/<branch-id>/endpoints/<endpoint-id>",
                    "LAKEBASE_SCHEMA": "public",
                    "LAKEBASE_TABLE": "<your_table>",
                    "LAKEBASE_USER": "<your-databricks-email>",
                    "geomOutColumn": "geometry",
                    "idField": "objectid",
                    "maxRecordCountPerPage": 10000,
                    "dbWKID": 4326,
                    "properties": {
                        "name": "<layer_name>",
                        "description": "<layer_description>"
                    },
                    "excludedFields": []
                }
            }
        }
    }
    ```

> **Note:** `config/default.json` is gitignored. Do not commit it — it contains secrets.

### Finding Lakebase Connection Details

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

### Authentication

This provider uses **OAuth token rotation** for Lakebase connections:

1. Your PAT authenticates against the Databricks REST API
2. A short-lived JWT credential is generated via `POST /api/2.0/postgres/credentials`
3. The JWT is used as the PostgreSQL password
4. Tokens are automatically refreshed 5 minutes before expiry

No manual token management is needed after initial configuration.

## Test the Provider

1.  Navigate to the **databricks-lakebase-editing-points** app-level directory in a command prompt and
    run the `npm start` command to start the custom data app.
2.  Send a GET request to
    http://localhost:8080/databricks/rest/services/lakebase/FeatureServer/0/query
    and verify that the Lakebase provider returns a feature.
3. Send a POST request to http://localhost:8080/databricks/rest/services/lakebase/FeatureServer/applyEdits with a properly formatted payload. Sample POST request:

    ```curl
        curl --location 'http://localhost:8080/databricks/rest/services/lakebase/FeatureServer/applyEdits' \
        --header 'Content-Type: application/x-www-form-urlencoded' \
        --data-urlencode 'f=json' \
        --data-urlencode 'rollbackOnFailure=true' \
        --data-urlencode 'async=false' \
        --data-urlencode 'returnServiceEditsOption=originalAndCurrentFeatures' \
        --data-urlencode 'useGlobalIds=false' \
        --data-urlencode 'edits=[{"id":0,"adds":[{"geometry":{"spatialReference":{"latestWkid":4326,"wkid":4326},"x":-73.986862,"y":40.721054},"attributes":{"pickup_longitude":"-73.986862182617188","fare_amount":"7","passenger_count":"1","trip_distance":".70","payment_type":"1","total_amount":"9.5","pickup_latitude":"40.721054077148438"}}],"updates":null,"deletes":null,"attachments":null,"assetMaps":null}]'

    ```

> **Tip:** A simple frontend test page is included at **frontend/index.html**. It uses the ArcGIS Maps SDK for JavaScript to render a web map pointed at your local CDF FeatureServer URL. This is useful for visually verifying queries and edits during development. You can also use ArcGIS Pro by adding the `127.0.0.1` FeatureServer URL as a data path, though you won't have access to browser dev tools for inspecting requests.

## Build and Deploy the Custom Data Provider Package File

1.  Stop the custom data app if it is running.
2.  Open a command prompt and navigate to the custom data app directory.
3.  Run the `cdf export databricks` command. This will generate a **databricks.cdpk** file.
4.  In a web browser, navigate to ArcGIS Server Manager (e.g. **https://\<yourserverurl\>.esri.com:6443/arcgis/manager/index.html**).
5.  Click the **Site** tab at the top and navigate to **Custom Datafeeds**.
6.  Click **Add Custom Data Provider** and upload your **databricks.cdpk** file.
7.  After the upload completes successfully, navigate back to **Services** at the top.
8.  Click **Publish Service** in the top right, then select **From a registered custom data provider**.
9.  Select your custom data provider from the dropdown. In the data source field, enter the name of your data source as it appears in your localhost FeatureServer URL (e.g. `lakebase`).
10. Set a name for your feature service and click **Publish**.

## Consume Feature Service

If everything worked, you should be able to navigate to your Portal content page (e.g. **https://\<yourserverurl\>.esri.com/portal/home/content.html**) and see your feature service listed there. Add it to an ArcGIS web app and verify that data populates correctly. 

## Project Structure

```
src/
  index.js              # Koop provider registration and server startup
  model.js              # getData() — builds query, executes, returns GeoJSON
  controllers.js        # Request routing and controller logic
  routes.js             # Route definitions
  helpers/              # Helper utilities
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

# Editing-enabled DuckDB provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting polygon features served from a DuckDB on the machine local filesystem. The database you need for this sample is available here: **data/ny.duckdb**.

## How the Provider Works

This sample provider will read from and write to a DuckDB instance that is populated
with rows of fictional data for New York taxi cabs.

## Set up the Provider

1.  In a command prompt, run the `cdf createapp duckdb-editing-points-filesystem` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **duckdb-editing-points-filesystem** directory or an existing custom data app directory, 
    run the `cdf createprovider duckdb-filesystem-provider` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/duckdb-filesystem-provider/src**
    directory.
4.  Copy the conetns of **data** and **config** folder in the provided source code into the **providers/duckdb-filesystem-provider**
    directory.
5.  Navigate to the **providers/duckdb-filesystem-provider** directory in a
    command prompt, and run the command `npm install config duckdb proj4 @esri/proj-codes` to install the needed modules. _`proj4` and `@esri/proj-codes` are tools that are used for coordinate system conversion._

## Configure the Provider

1.  In the **providers/duckdb-filesystem-provider/cdconfig.json** file, set the value of the
    `properties.hosts` field to `true` and
    `properties.disableIdParam` field to `false`.

2.  In the **providers/duckdb-filesystem-provider/config** directory, configure your DuckDB
    connection in the **default.json** file.

````json
{
	"duckdbfs": {
		"sources": {
			"localParquet": {
				"dbPath": "../data/ny.duckdb",
				"WKBColumn": "WKB",
				"geomOutColumn": "geometry",
				"idField": "OBJECTID",
				"maxRecordCountPerPage": 10000,
				"dbWKID": 4326,
				"properties": {
					"name": "testTaxi",
					"description": "Taxi location data for New York City."
				}
			}
		}
	}
}
````

## Test the Provider

1.  Navigate to the **duckdb-editing-points-filesystem** app-level directory in a command prompt and
    run the `npm start` command to start the custom data app.
2.  In a web browser, navigate to
    http://localhost:8080/duckdb-filesystem-provider/rest/services/localParquet/FeatureServer/0/query
    and verify that the DuckDB provider returns a feature.

## Build and Deploy the Custom Data Provider Package File

1.  Stop the custom data app if it is running.
2.  Open a command prompt and navigate to the custom data app directory.
3.  Run the `cdf export duckdb-filesystem-provider` command.
4.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
5.  Click **uploads \> upload**.
6.  On the **Upload Item** page, click **Choose File** and select the
    **duckdb-filesystem-provider.cdpk** file. Optionally, provide a
    description in the **Description** text box.
7.  Click **Upload**. Once the file is uploaded, you will be directed to
    a page with the following header: **Uploaded item - \<item_id\>** .
    Copy the item id.
8. Browse back to the root of the Administrator Directory and then
    click **services \> types \> customdataproviders**.
9. On the **Registered Customdata Providers** page, click register and
    paste the item id into the **Id of uploaded item** field.
10. Click **Register**.

## Create Feature Service

1.  Browse back to the root of the Administrator Directory and click
    **services \> createService**.

2.  On the **Create Service** page, copy and paste the following JSON
    into the **Service (in JSON format)** text box.

    ```json
    {
        "serviceName": "duckdb-taxis",
        "type": "FeatureServer",
        "description": "",
        "capabilities": "Query",
        "provider": "CUSTOMDATA",
        "clusterName": "default",
        "minInstancesPerNode": 0,
        "maxInstancesPerNode": 0,
        "instancesPerContainer": 1,
        "maxWaitTime": 60,
        "maxStartupTime": 300,
        "maxIdleTime": 1800,
        "maxUsageTime": 600,
        "loadBalancing": "ROUND_ROBIN",
        "isolationLevel": "HIGH",
        "configuredState": "STARTED",
        "recycleInterval": 24,
        "recycleStartTime": "00:00",
        "keepAliveInterval": 1800,
        "private": false,
        "isDefault": false,
        "maxUploadFileSize": 0,
        "allowedUploadFileTypes": "",
        "properties": {
            "disableCaching": "true"
        },
        "jsonProperties": {
            "customDataProviderInfo": {
                "dataProviderName": "duckdb-filesystem-provider",
                "dataProviderHost": "localParquet",
                "dataProviderId": ""
            }
        },
        "extensions": [],
        "frameworkProperties": {},
        "datasets": []
    }

    ```

3.  Click **Create.**

4. In ArcGIS Server Administrator Directory, navigate to **Home > services > duckdb-taxis > edit**

5. Change the value of `capabilities` to `Query,Editing`, and click the **Save Edits** button.

## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g. **https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/duckdb-taxis/FeatureServer**).
You can use this URL to consume data from DuckDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
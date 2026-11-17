# Editing-enabled DuckDB provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting polygon features served from a DuckDB on the machine local filesystem. The database you need for this sample is available here: **data/ny.duckdb**. Furthermore, this samples shows how it is possible to make use of transaction logic with the `rollbackOnFailure` parameter.

## Supported ArcGIS Enterprise SDK Versions
**12.2**

Looking for prior versions of this sample?
[11.4](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.4.0/Samples/custom-data-feeds/duckdb-editing-points-filesystem),
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/duckdb-editing-points-filesystem),
[12.0](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.0.0/Samples/custom-data-feeds/duckdb-editing-points-filesystem),
[12.1](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.1.0/Samples/custom-data-feeds/duckdb-editing-points-filesystem)


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
4.  Copy the contents of **data** and folder in the provided source code into a **data** folder in the **providers/duckdb-filesystem-provider/**
    directory.
5.  Create a file called **duckdb-config.json** inside the **providers/duckdb-filesystem-provider/src** directory.

6.  In a command prompt, and run the command `npm install duckdb` to install the needed modules from the **providers/duckdb-filesystem-provider/** directory. 

## Configure the Provider

1.  In the **providers/duckdb-filesystem-provider/cdconfig.json** file, set `"editingEnabled": true`.

2.  In the **providers/duckdb-filesystem-provider/duckdb-config.json** file, configure your DuckDB
    connection.

    ```json
    {
        "duckdbfs": {
            "sources": {
                "localParquet": {
                    "dbPath": "../data/ny.duckdb",
                    "WKBColumn": "WKB",
                    "geomOutColumn": "geometry",
                    "idField": "OBJECTID",
                    "maxRecordCountPerPage": 2000,
                    "dbWKID": 4326,
                    "properties": {
                        "name": "testTaxi",
                        "description": "Taxi location data for New York City."
                    }
                }
            }
        }
    }
    ```

## Test the Provider

1.  Navigate to the **duckdb-editing-points-filesystem** app-level directory in a command prompt and
    run the `npm start` command to start the custom data app.
2.  Send a GET request to 
    http://localhost:8080/duckdb-filesystem-provider/rest/services/FeatureServer/0/query
    and verify that the DuckDB provider returns a feature.
3. Send a POST request to http://localhost:8080/duckdb-filesystem-provider/rest/services/FeatureServer/applyEdits with a properly formatted payload. Sample POST request:

    ```curl
        curl --location 'https://localhost:8080/duckdb-filesystem-provider/rest/services/FeatureServer/applyEdits' \
        --header 'Content-Type: application/x-www-form-urlencoded' \
        --data-urlencode 'f=json' \
        --data-urlencode 'rollbackOnFailure=true' \
        --data-urlencode 'async=false' \
        --data-urlencode 'returnServiceEditsOption=oringalAndCurrentFeatures' \
        --data-urlencode 'useGlobalIds=false' \
        --data-urlencode 'edits=[{"id":0,"adds":[{"geometry":{"spatialReference":{"latestWkid":3857,"wkid":102100},"x":-3536494.0462012663,"y":6044195.426078061},"attributes":{"pickup_longitude":"-73.986862182617188","RatecodeID":"1","fare_amount":"7","tpep_dropoff_datetime":"2/10/2025, 1:39 AM","VendorID":"1","passenger_count":"20","tolls_amount":"0","dropoff_latitude":"40.725048065185547","improvement_surcharge":"0.3","trip_distance":".70","store_and_fwd_flag":"N","dropoff_longitude":"-73.99725341796875","payment_type":"1","total_amount":"9.5","pickup_latitude":"40.721054077148438","extra":"0.5","tip_amount":"1.2","mta_tax":"0.5","tpep_pickup_datetime":"2/10/2025, 1:28 AM"}}],"updates":null,"deletes":null,"attachments":null,"assetMaps":null}]'

    ```

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
                "serviceParameters": []
            },
            "customDataServiceInfo": {
              "dedicated": false,
              "cache": {
                "expiration": 0,
                "enabled": false
              }
           }
        },
        "extensions": [],
        "frameworkProperties": {},
        "datasets": []
    }

    ```

3.  Click **Create.** _Alternatively, you can create the feature service in ArcGIS Server Manager or in the Portal for ArcGIS Home Application._


4. In ArcGIS Server Administrator Directory, navigate to **Home > services > duckdb-taxis > edit**

5. Change the value of `capabilities` to `Query,Editing`, and click the **Save Edits** button.

## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g. **https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/duckdb-taxis/FeatureServer**).
You can use this URL to consume data from DuckDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
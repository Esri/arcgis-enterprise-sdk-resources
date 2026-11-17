# MongoDB provider

This sample provider demonstrates a ["Pass-through data loading pattern"](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/create-a-custom-data-feed-provider/)  that fetches 
wildfire location data from a MongoDB instance and exposes a feature service 
that displays point data. This sample implements geometry filtering, pagination, and aggregation operations, including
`returnCountOnly` and `returnExtentOnly`.
_Before you begin, you will need to setup your own MongoDB instance and populate it 
with the provided dataset_. The data are found in **data/fires.json**.

## Supported ArcGIS Enterprise SDK Versions
**12.2**

Looking for prior versions of this sample?
[11.4](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.4.0/Samples/custom-data-feeds/mongodb-pt-read-only-points),
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/mongodb-pt-read-only-points),
[12.0](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.0.0/Samples/custom-data-feeds/mongodb-pt-read-only-points),
[12.1](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.1.0/Samples/custom-data-feeds/mongodb-pt-read-only-points)



## How the Provider Works

This sample provider will read from a MongoDB instance that is populated
with documents in the format below.

```json
    {
      "_id": "eca3c261-6f3a-4313-b9e9-4a23f58d4e85",
      "fireId": "1",
      "fireName": "PUMP HOUSE",
      "fireType": "Human",
      "acres": 0.1,
      "location": {
        "type": "Point",
        "coordinates": [
          -117.09982,
          32.58459
        ]
      },
      "alternateID": 1
    }   
```

## Set up the Provider

1.  In a command prompt, run the `cdf createapp mongodb-app` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **mongodb-app** directory or an existing custom data app directory, 
    run the `cdf createprovider mongodb-provider` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/mongodb-provider/src**
    directory.
4.  Navigate to the **providers/mongodb-provider** directory in a
    command prompt, and run the command `npm install @koopjs/geoservice-utils @synatic/noql mongodb` to install the needed modules.

## Configure the Provider

1.    In the **providers/mongodb-provider/cdconfig.json** file, add the following object to the `serviceParameters` array:
```json
        {
            "key": "dataBaseName",
            "label": "Database Name",
            "description": "Name of the MongoDB database."
        },
        {
            "key": "collectionName",
            "label": "Collection Name",
            "description": "Name of the MongoDB collection."
        }
```

2.  In the **providers/mongodb-provider/src/mongodb-provider-config.json** file, configure your MongoDB
    connection details. This sample assumes a locally running instance of MongoDB. It will look similar to this:

```json
{
    "mongodb_provider": {
        "connectString": "mongodb://127.0.0.1:27017",
        "databases": {
          "sample-data": {
            "fires": {
              "geometryField": "location",
              "idField": "_id",
              "cacheTtl": 0,
              "crs": 4326,
              "maxRecordCount": 2000
            }
          }
        }
    }
}
```

## Test the Provider

1.  Navigate to the **mongodb-app** directory in a command prompt, and
    run the `npm start` command to start the custom data app.
2.  Send a GET request
    to: http://localhost:8080/monogodb-provider/rest/services/FeatureServer/0/query with the header `x-esri-cdf-service-params` and value `{"dataBaseName": "sample-data", "collectionName": "fires"}`.
    Verify that the provider is returning data points.

## Build and Deploy the Custom Data Provider Package File

3.  Stop the custom data app if it is running.
4.  Open a command prompt and navigate to the custom data app directory.
5.  Run the `cdf export mongodb-provider` command.
6.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
7.  Click **uploads \> upload**.
8.  On the **Upload Item** page, click **Choose File** and select the
    **mongodb-provider.cdpk** file. Optionally, provide a
    description in the **Description** text box.
9.  Click **Upload**. Once the file is uploaded, you will be directed to
    a page with the following header: **Uploaded item - \<item_id\>** .
    Copy the item id.
10. Browse back to the root of the Administrator Directory and then
    click **services \> types \> customdataproviders**.
11. On the **Registered Customdata Providers** page, click register and
    paste the item id into the **Id of uploaded item** field.
12. Click **Register**.

## Create Feature Service

1.  Browse back to the root of the Administrator Directory and click
    **services \> createService**.

2.  On the **Create Service** page, copy and paste the following JSON
    into the **Service (in JSON format)** text box.

    ```json
    {
        "serviceName": "mongoReadOnlyFires",
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
                "dataProviderName": "mongodb-provider",
                "serviceParameters": {
                    "dataBaseName": "sample-data",
                    "collectionName": "fires"
                }
            },
            "customDataServiceInfo": {
                "dedicated": "false",
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

3.  Click **Create.**

Alternatively, you can create the feature service in ArcGIS Server Manager or in the Portal for ArcGIS Home Application. Use the service parameter values listed above when configuring the service.

_Keep in mind that the provider code we used above assumes a database named
**sample-data** and a collection named **fires**. If you used different names
in your MongoDB instance, update the values of_ `dataBaseName` _and_ 
`collectionName` _accordingly._

## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g.,
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/mongoReadOnlyFires/FeatureServer**).
You can use this URL to consume data from MongoDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
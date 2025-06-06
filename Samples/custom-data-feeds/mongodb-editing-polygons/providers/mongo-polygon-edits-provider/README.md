# Editing-enabled MongoDB provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting polygon features served from a MongoDB instance. 
_Before you begin, you will need to setup your own MongoDB instance and populate it 
with the provided dataset_. The data are found in **data/polygon.json**.

## How the Provider Works

This sample provider will read and write to a MongoDB instance that is populated
with documents in the format below.

````json
{
  "_id": "1150d48a-4283-409f-95dc-d97487b8ad84",
  "fireId": "AK6448214467319840607",
  "fireName": "GLACIER CREEK",
  "fireType": "Wildfire",
  "acres": 1897,
  "location": {
    "type": "Polygon",
    "coordinates": [
      [
        [
          -104.05,
          48.99
        ],
        [
          -97.22,
          48.98
        ],
        [
          -96.58,
          45.94
        ],
        [
          -104.03,
          45.94
        ],
        [
          -104.05,
          48.99
        ]
      ]
    ]
  },
  "alternateID": 12345678
}
````
Notice in the document above the attribute `alternateID`. ArcGIS does not currently support strings as ObjectIDs, so the `_id` field generated by MongoDB cannot be used. If you inspect the sample code, there is a function that hashes the `_id` and saves the hashed integer as the `alternateID` in the document. This `alternateID` is used as the ObjectID. See the [API Reference](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/custom-data-provider-api-reference/) for details on designating a feature attribute as the ObjectID with the `idField` metadata property.

## Set up the Provider

1.  In a command prompt, run the `cdf createapp mongodb-app` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **mongodb-app** directory or an existing custom data app directory, 
    run the `cdf createprovider mongo-polygon-edits-provider` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/mongo-polygon-edits-provider/src**
    directory.
4.  Navigate to the **providers/mongo-polygon-edits-provider** directory in a
    command prompt, and run the command `npm install config mongodb proj4 @esri/proj-codes` to install the needed modules. _`proj4` and `@esri/proj-codes` are tools that are used for coordinate system conversion._

## Configure the Provider

1.  In the **providers/mongo-polygon-edits-provider/cdconfig.json** file, set the value of the
    `properties.hosts` field to `true` and
    `properties.disableIdParam` field to `false`.

2.  In the **provider/mongo-polygon-edits-provider/config** directory, configure your MongoDB
    connection in the **default.json** file. The `connectString` in this sample assumes a locally running instance of MongoDB. It will look similar to this:

````json
{
    "editable_mongodb_polygon_provider": {
        "connectString": "mongodb://127.0.0.1:27017",
        "definedCollectionsOnly": true,
        "databases": {
            "edit-polygon-db": {
                "polygons": {
                    "geometryField": "location",
                    "idField": "alternateID",
                    "cacheTtl": 0,
                    "crs": 4326,
                    "maxRecordCount": 2000
                }
            }
        }
    }
}
````

## Test the Provider

1.  Navigate to the **mongodb-app** directory in a command prompt, and
    run the `npm start` command to start the custom data app.
2.  In a web browser, navigate to
    http://localhost:8080/mongo-polygon-edits-provider/rest/services/edit-polygon-db/polygons/FeatureServer/0/query,
    and verify that the MongoDB provider is returning data points.

## Build and Deploy the Custom Data Provider Package File

3.  Stop the custom data app if it is running.
4.  Open a command prompt and navigate to the custom data app directory.
5.  Run the `cdf export mongo-polygon-edits-provider` command.
6.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
7.  Click **uploads \> upload**.
8.  On the **Upload Item** page, click **Choose File** and select the
    **mongo-polygon-edits-provider.cdpk** file. Optionally, provide a
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
        "serviceName": "mongoEditPolygonFires",
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
                "dataProviderName": "mongo-polygon-edits-provider",
                "dataProviderHost": "edit-polygon-db",
                "dataProviderId": "polygons"
            }
        },
        "extensions": [],
        "frameworkProperties": {},
        "datasets": []
    }

    ```

3.  Click **Create.**

4. In ArcGIS Server Administrator Directory, navigate to **Home > services > mongoEditPolygonFires > edit**

5. Change the value of `capabilities` to `Query,Editing`, and click the **Save Edits** button.

![](./images/editable-polygon-fires-admin-edit.png)

_Keep in mind that the provider code we used above assumes a database named
**edit-polygon-db** and a collection named **polygons**. If you used different names
in your MongoDB instance, update the values of_ `dataProviderHost` _and_ 
`dataProviderId` _accordingly._

## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g. **https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/mongoEditPolygonFires/FeatureServer**).
You can use this URL to consume data from MongoDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
# Editing-enabled MongoDB provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting polygon features served from a MongoDB instance. 
_Before you begin, you will need to setup your own MongoDB instance and populate it 
with the provided dataset_. The data are found in **data/polygon.json**.

## Supported ArcGIS Enterprise SDK Versions
**12.2**

Looking for prior versions of this sample?
[11.4](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.4.0/Samples/custom-data-feeds/mongodb-editing-polygons),
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/mongodb-editing-polygons),
[12.0](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.0.0/Samples/custom-data-feeds/mongodb-editing-polygons),
[12.1](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.1.0/Samples/custom-data-feeds/mongodb-editing-polygons)


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

## Set up the Provider

1.  In a command prompt, run the `cdf createapp mongodb-app` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **mongodb-app** directory or an existing custom data app directory, 
    run the `cdf createprovider mongo-polygon-edits-provider` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/mongo-polygon-edits-provider/src**
    directory.
4.  Navigate to the **providers/mongo-polygon-edits-provider** directory in a
    command prompt, and run the command `npm install  mongodb` to install the needed modules.

## Configure the Provider

1.  In the **providers/mongo-polygon-edits-provider/cdconfig.json** file, add the following to thed `serviceParameters` array:

    ```json
    {
        "key": "db",
        "label": "database",
        "description": "Name of Mongo database"
      },
      {
        "key": "collection",
        "label": "collection",
        "description": "Name of Mongo collection"
      }
    ```
    
    Set `"editingEnabled": true`.

2.  Create a file called **mongo-config.json** in the **providers/mongo-polygon-edits-provider/src** directory. Configure your MongoDB connection. The `connectString` in this sample assumes a locally running instance of MongoDB. **mongo-config.json** will look similar to this:

    ```json
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
    ```

## Test the Provider

1.  Navigate to the **mongodb-app** directory in a command prompt, and
    run the `npm start` command to start the custom data app.
2.  Send a GET request
    to: http://localhost:8080/editable_mongodb_polygon_provider/rest/services/FeatureServer/0/query with the header `x-esri-cdf-service-params` and value `{"db": "edit-polygon-db", "collection": "polygons"}`.
    Verify that the provider is returning data points.
3.  Send a properly formatted POST request
    to: http://localhost:8080/editable_mongodb_polygon_provider/rest/services/FeatureServer/applyEdits with the header `x-esri-cdf-service-params` and value `{"db": "edit-polygon-db", "collection": "polygons"}`. Sample POST request:

    ```curl
        curl --location 'https://localhost:8080/mongo-polygon-edits-provider/rest/services/FeatureServer/applyEdits' \
            --header 'x-esri-cdf-service-params: {"collection": "fires","db": "editable-sample-fires"}' \
            --header 'Content-Type: application/x-www-form-urlencoded' \
            --data-urlencode 'f=json' \
            --data-urlencode 'rollbackOnFailure=true' \
            --data-urlencode 'useGlobalIds=false' \
            --data-urlencode 'async=false' \
            --data-urlencode 'edits=[{"id":0,"adds":[{"geometry":{"spatialReference":{"latestWkid":3857,"wkid":102100},"rings":[[[-13031222.578554569,4057320.728596478],[-13030992.398840608,4057381.530738308],[-13030883.823410304,4057220.8392385044],[-13031061.886836078,4057103.5779545605],[-13031300.752549473,4057103.5779545605],[-13031313.781694422,4057216.496238788],[-13031222.578554569,4057320.728596478]]]},"attributes":{"fireId":"aaa","fireName":"aaaa","fireType":"aaaa","acres":3}}],"updates":null,"deletes":null,"attachments":null,"assetMaps":null}]' \
            --data-urlencode 'returnServiceEditOptions=originalAndCurrentFeatures'
    ```
    Check for correct response.

## Build and Deploy the Custom Data Provider Package File

1.  Stop the custom data app if it is running.
2.  Open a command prompt and navigate to the custom data app directory.
3.  Run the `cdf export mongo-polygon-edits-provider` command.
4.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
5.  Click **uploads \> upload**.
6.  On the **Upload Item** page, click **Choose File** and select the
    **mongo-polygon-edits-provider.cdpk** file. Optionally, provide a
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
                "serviceParameters":
                    {
                        "db": "edit-polygon-db", 
                        "collection": "polygons"
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

4. In ArcGIS Server Administrator Directory, navigate to **Home > services > mongoEditPolygonFires > edit**

5. Change the value of `capabilities` to `Query,Editing`, and click the **Save Edits** button.

![](./images/editable-polygon-fires-admin-edit.png)

_Keep in mind that the provider code we used above assumes a database named
**edit-polygon-db** and a collection named **polygons**. If you used different names
in your MongoDB instance, update the values of the `serviceParameters` accordingly._

Alternatively, you can create the feature service in ArcGIS Server Manager or in the Portal for ArcGIS Home Application. Use the service parameter values listed above when configuring the service.

## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g. **https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/mongoEditPolygonFires/FeatureServer**).
You can use this URL to consume data from MongoDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
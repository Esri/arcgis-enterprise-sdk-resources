# Editing-enabled MongoDB provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting point data features served from a MongoDB instance. There is a full walkthrough version of this sample available [here](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/walkthrough-create-mongodb-editing-provider/).  

_Before you begin, you will need to setup your own MongoDB instance and populate it 
with the provided dataset_. The data are found in **data/wildfires-sample.json**.

## Supported ArcGIS Enterprise SDK Versions
**12.2**

Looking for prior versions of this sample?
[11.4](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.4.0/Samples/custom-data-feeds/mongodb-editing-points),
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/mongodb-editing-points),
[12.0](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.0.0/Samples/custom-data-feeds/mongodb-editing-points),
[12.1](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.1.0/Samples/custom-data-feeds/mongodb-editing-points)


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
    "type": "Point",
    "coordinates": [
      -144.67327160022626,
      64.48231108796996
    ]
  },
  "alternateID": 212345
}
````
This sample has been updated to make use of `uniqueIds`. Support for `uniqueIds` was introduced in custom data feeds with the release of ArcGIS Enterprise 12.2. See the [API Reference](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/custom-data-provider-api-reference/) for details on the `idField` metadata property.

## Set up the Provider

1.  In a command prompt, run the `cdf createapp mongodb-app` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **mongodb-app** directory or an existing custom data app directory, 
    run the `cdf createprovider editable-mongodb-points` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/editable-mongodb-points/src**
    directory.
4.  Navigate to the **providers/editable-mongodb-points** directory in a
    command prompt, and run the command `npm install mongodb`.

## Configure the Provider

1.  In the **providers/editable-mongodb-points/cdconfig.json** file, add the following to the `serviceParameters` array:

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


2.  Create a file the **providers/editable-mongodb-points/src** directory called **mongo-config.json** with the following code:

    ```json
        {
            "editable_mongodb_points": {
                "connectString": "mongodb://127.0.0.1:27017",
                "definedCollectionsOnly": true,
                "databases": {
                    "editable-sample-fires": {
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

1.  Navigate to the **mongodb-app** directory in a command prompt and
    run the `npm start` command to start the custom data app.
2.  Send a GET request
    to: http://localhost:8080/editable-monogodb-points/rest/services/FeatureServer/0/query with the header `x-esri-cdf-service-params` and value `{"database": "editable-sample-fires", "collection": "fires"}`.
    Verify that the provider is returning data points.
3. Send a properly formatted POST request to: http://localhost:8080/editable-monogodb-points/rest/services/FeatureServer/applyEdits with the header `x-esri-cdf-service-params` and value `{"database": "editable-sample-fires", "collection": "fires"}`. Sample POST request:

    ```curl
        curl --location 'https://localhost:8080/editable-mongodb-points/rest/services/FeatureServer/applyEdits' \
        --header 'x-esri-cdf-service-params: {"collection": "fires","db": "editable-sample-fires"}' \
        --header 'Content-Type: application/x-www-form-urlencoded' \
        --data-urlencode 'f=json' \
        --data-urlencode 'rollbackOnFailure=true' \
        --data-urlencode 'useGlobalIds=false' \
        --data-urlencode 'async=false' \
        --data-urlencode 'edits=[{"id":0,"adds":[{"geometry":{"spatialReference":{"latestWkid":3857,"wkid":102100},"x":-11580849.34814877,"y":5772969.114195114},"attributes":{"fireId":"44444","fireName":"Big One","fireType":"Wild","acres":"3434"}}],"updates":null,"deletes":null,"attachments":null,"assetMaps":null}]' \
        --data-urlencode 'returnServiceEditOptions=originalAndCurrentFeatures'

    ```

## Build and Deploy the Custom Data Provider Package File

3.  Stop the custom data app if it is running.
4.  Open a command prompt and navigate to the custom data app directory.
5.  Run the `cdf export editable-mongodb-points` command.
6.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
7.  Click **uploads \> upload**.
8.  On the **Upload Item** page, click **Choose File** and select the
    **editable-mongodb-points.cdpk** file. Optionally, provide a
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
        "serviceName": "mongoEditPoints",
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
                "dataProviderName": "editable-mongodb-points",
                "serviceParameters" :
                {
                    "database": "editable-sample-fires",
                    "collection": "fires"
                }
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

3.  Click **Create.**

4. In ArcGIS Server Administrator Directory, navigate to **Home > services > mongoEditPoints > edit**

5. Change the value of `capabilities` to `Query,Editing`, and click the **Save Edits** button.

![](./images/cdf-editable-mongodb-points.png)

_Keep in mind that the provider code we used above assumes a database named
**editable-sample-fires** and a collection named **fires**.

Alternatively, you can create the feature service in ArcGIS Server Manager or in the Portal for ArcGIS Home Application. Use the service parameter values listed above when configuring the service.

## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g.,
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/mongoEditPoints/FeatureServer**).
You can use this URL to consume data from MongoDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
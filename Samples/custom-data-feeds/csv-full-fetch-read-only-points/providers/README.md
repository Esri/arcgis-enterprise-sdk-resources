# CSV Provider

This sample provider interfaces with a CSV file published on the web or with a local file and
integrates the data with ArcGIS Enterprise.

## Supported ArcGIS Enterprise SDK Versions
**12.2**

Looking for prior versions of this sample?
[11.4](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.4.0/Samples/custom-data-feeds/csv-full-fetch-ready-only-points),
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/csv-full-fetch-ready-only-points),
[12.0](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.0.0/Samples/custom-data-feeds/csv-full-fetch-read-only-points),
[12.1](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.1.0/Samples/custom-data-feeds/csv-full-fetch-read-only-points)


## Set up the Provider

1.  Run the `cdf createapp csv-app` command to create a new custom data
    app, or use an existing custom data app.
2.  Run the `cdf createprovider csv-provider` command to create a custom
    data provider.
3.  Navigate to the **providers/csv-provider** directory in a command
    prompt and run the `npm i valid-url papaparse` command.
4.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/csv-provider/src** directory.
5.  Put the **RunningShoeStores.csv** file on a web server so that it is available
    through a URL. You may also put the file in a **data** folder in **/providers/csv-provider**. The file is located in the **data** folder inside the **csv-provider** sample directory.

## Configure the Provider

1.  In the **providers/csv-provider/** directory, create a file named **csv-provider-config.json**. Set the **url** field to the URL where
    the **RunningShoeStores.csv** file is hosted. Here's how it should look, but your URL
    value may be different.

    ```json
      {
          "dataDir":"data",
          "delimiter": ",",
          "metadata": {
              "idField": "Id",
              "dataCrs": 4326
          }
      }
    ```

2.  In the **providers/csv-provider/cdconfig.json** file, add the following to the `serviceParameters` array:

    ```json
      {
        "key": "file_name",
        "label": "File Name",
        "description": "Name of the CSV file."
      },
      {
        "key": "lat_column",
        "label": "Latitude column",
        "description": "Name of CSV column that specifies latitude."
      },
      {
        "key": "long_column",
        "label": "Longitude column",
        "description": "Name of CSV column that specifies longitude."
      }
    ```

## Test the Provider

1.  Navigate to the **csv-app** directory in a command prompt, and run
    the `npm start` command to start the custom data app.
2.  Send a GET request
    to: http://localhost:8080/csv-provider/rest/services/FeatureServer/0/query with the header `x-esri-cdf-service-params` and value `{"file_name": "RunningShoeStores.csv", "lat_column": "latitude","long_column": "longitude"}`.
    Verify that the provider is returning data points.

## Build and Deploy the Custom Data Provider Package File

1.  Stop the custom data app, if it is currently running.
2.  Open a command prompt, and navigate to the custom data app directory.
3.  Run the `cdf export csv-provider` command.
4.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory, and sign in as an administrator. Alternatively, you can use the [ArcGIS Server Manager](https://enterprise.arcgis.com/en/server/latest/develop/linux/administer-custom-data-providers-using-server-manager.htm) workflow.
5.  Click **uploads \> upload**.
6.  On the **Upload Item** page, click **Choose File**, and select the
    **csv-provider.cdpk** file. Optionally, provide a description in the
    **Description** text box.
7.  Click **Upload**. Once the file is uploaded, you will be directed to
    a page with the following header: **Uploaded item - \<item_id\>** .
    Copy the item id.
8.  Browse back to the root of the Administrator Directory and then
    click **services \> types \> customdataproviders**.
9.  On the **Registered Customdata Providers** page, click the **register** link and 
    then paste the item id into the **Id of uploaded item** field.
10. Click the **Register** button.

## Create Feature Service

1.  Browse back to the root of the Administrator Directory and click
    **services \> createService**.

2.  On the **Create Service** page, copy and paste the following JSON
    into the **Service (in JSON format)** text box.

    ```json
    {
      "serviceName": "csvShoeStores",
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
          "dataProviderName": "csv-provider",
          "serviceParameters": {
            "file_name": "RunningShoeStores.csv", 
            "lat_column": "latitude",
            "long_column": "longitude"
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

Alternatively, you can create the feature service in ArcGIS Server Manager or in the Portal for ArcGIS Home Application. Use the service parameter values listed above when configuring the service.

## Consume Feature Service

To access the csv feature service that you created in the previous
section, use the appropriate URL (e.g.,
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/csvShoeStores/FeatureServer**).
You can use this URL to consume data from your CSV file in ArcGIS
clients like ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
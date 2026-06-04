# Oriented Imagery Provider

This sample provider interfaces with a local geojson file and
integrates the Oriented Imagery feature data with ArcGIS Enterprise.

## Supported ArcGIS Enterprise SDK Versions

**12.0**

Looking for 11.x versions of this sample?
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/oriented-imagery-geojson)

## Set up the Provider

1.  Run the `cdf createapp oriented-imagery-app` command to create a new custom data
    app, or use an existing custom data app.
2.  Run the `cdf createprovider oriented-imagery-provider` command to create a custom
    data provider.
3.  Navigate to the **providers/oriented-imagery-provider**
4.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/oriented-imagery-provider/src** directory.
5.  Add the geojson file **esriBuildingE.geojson** containing the oriented imagery features in a **data** folder in **/providers/oriented-imagery-provider**. The file is located in the **data** folder inside the **oriented-imagery-provider** sample directory.

## Configure the Provider

1.  Create a file called **oriented-imagery-provider.json** in the directory **providers/oriented-imagery-provider/src/** if it is not already there and set the `dataDirectory` path where
    the **esriBuildingE.geojson** file is located.

    ```json
    {
      "dataDirectory": "../data"
    }
    ```

2.  In the **providers/oriented-imagery-provider/cdconfig.json** file, add the following to the `serviceParameters` array:

    ```json
      {
        "key": "fileName",
        "label": "File Name",
        "description": "Name of the GeoJSON file without file extension."
      }
    ```

## Test the Provider

1.  Navigate to the **oriented-imagery-app** directory in a command prompt, and run
    the `npm start` command to start the custom data app.
2.  In a client, send a request to
    \>http://localhost:8080/oriented-imagery-provider/rest/services/FeatureServer/0/query,
    with a header called `x-esri-cdf-service-params` with a value of `{"fileName": "esriBuildingE"}`and verify that the provider is returning data points. This header is required when testing locally. You do not need to set this yourself when you deploy the provider and service.

## Build and Deploy the Custom Data Provider Package File

1.  Stop the custom data app, if it is currently running.
2.  Open a command prompt, and navigate to the custom data app directory.
3.  Run the `cdf export oriented-imagery-provider` command.
4.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory, and sign in as an administrator.
5.  Click **uploads \> upload**.
6.  On the **Upload Item** page, click **Choose File**, and select the
    **oriented-imagery-provider.cdpk** file. Optionally, provide a description in the
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
      "serviceName": "orientedImagerySample",
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
          "dataProviderName": "oriented-imagery-provider",
          "serviceParameters": {"fileName": "esriBuildingE"}
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
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/orientedImagerySample/FeatureServer**).
You can use this URL to consume data from your CSV file in ArcGIS
clients like ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.

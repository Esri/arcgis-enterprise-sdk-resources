# Yelp Provider

This sample provider interfaces with the Yelp Fusion API to find local
entities such as restaurants and parks using a valid postal code or city
name. This provider also demonstrates how to change the services default 
labeling and feature styling.

## Supported ArcGIS Enterprise SDK Versions
**12.2**

Looking for prior versions of this sample?
[11.4](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.4.0/Samples/custom-data-feeds/yelp-full-fetch-ready-only-points),
[11.5](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v11.5.0/Samples/custom-data-feeds/yelp-full-fetch-ready-only-points),
[12.0](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.0.0/Samples/custom-data-feeds/yelp-full-fetch-read-only-points),
[12.1](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/release-v12.1.0/Samples/custom-data-feeds/yelp-full-fetch-read-only-points)


## Set up the Provider

1.  Run the `cdf createapp yelp-app` command to create a new custom data
    app or use an existing custom data app.
2.  Run the `cdf createprovider yelp-data-provider` command to create a
    custom data provider.
3.  Navigate to the **providers/yelp-data-provider** directory in a
    command prompt, and run the `npm i yelp-fusion` command.
4.  Copy the contents of the src folder in the provided source code into
    the src folder inside your **providers/yelp-data-provider/src**
    directory.

## Obtain an API Key

1.  Follow the instructions in Yelp Fusion's developer documentation to
    obtain a private API key.


## Configure the Provider

2.  Once you have an API key, create a file named **yelp-provider-config.json** in the
    **providers/yelp-data-provider/src** direcotry. Assign as the value for
    the **yelp.apiKey** field as shown in this file.

    ```json
    {
      "yelp": {
        "apiKey": "<generated API key here>"
      }
    }
    ```

2.  In the **providers/yelp-data-provider/cdconfig.json** file, add the following to the `serviceParameters` array.

    ```json
        {
            "key": "category",
            "label": "Category",
            "description": "Valid 'category' in the Yelp Places API."
        },
                {
            "key": "city",
            "label": "City",
            "description": "The name of the city."
        }

    ```

## Test the Provider

1.  Navigate to the **yelp-app** directory in a command prompt and run
    the `npm start` command to start the custom data app
2.  Send a GET request to
    http://localhost:8080/yelp-data-provider/rest/services/FeatureServer/0/query with the header `x-esri-cdf-service-params` and value `{"category": "restaurants", "city": "Redlands"}`.
    Verify that the Yelp provider is returning data points.

## Build and Deploy a Custom Data Provider Package File

1.  Stop the custom data app if it's running.
2.  Open a command prompt and navigate to the custom data app directory.
3.  Run the `cdf export yelp-data-provider` command.
4.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator. Alternatively, you can use the [ArcGIS Server Manager](https://enterprise.arcgis.com/en/server/latest/develop/linux/administer-custom-data-providers-using-server-manager.htm) workflow.
5.  Click **uploads \> upload**.
6.  On the **Upload Item** page, click **Choose File** and select the
    **yelp-data-provider.cdpk** file. Optionally, provide a description
    in the **Description** text box.
7.  Click **Upload**. Once the file is uploaded, you will be directed to
    a page with the following header: **Uploaded item - \<item_id\>** .
    Copy the item id.
8.  Browse back to the root of the Administrator Directory and then
    click **services \> types \> customdataproviders**.
9.  On the **Registered Customdata Providers** page, click register and
    paste the item id into the **Id of uploaded item** field.
10. Click **Register**.

## Create Feature Service

1.  Browse back to the root of the Administrator Directory and click
    **services > createService**.

2.  On the **Create Service** page, copy and paste the following JSON
    into the **Service (in JSON format)** text box.

    ```json
    {
      "serviceName": "yelpRedlands",
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
          "dataProviderName": "yelp-data-provider",
          "serviceParameters": {
            "category": "restaurants",
            "city": "Redlands"
          }
        },
        "customDataServiceInfo": {
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

## Consume Feature Service

To access the yelp feature service that you created in the previous
section, use the appropriate URL (e.g.,
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/yelpRedlands/FeatureServer**).
You can use this URL to consume data from Yelp in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
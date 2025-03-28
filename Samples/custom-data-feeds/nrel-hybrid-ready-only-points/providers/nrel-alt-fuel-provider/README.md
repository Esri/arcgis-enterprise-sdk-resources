# NREL provider

This sample provider fetches data from the [National Renewable Energy Laboratory database](https://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/) via their developer API to return the locations of alternative fuel stations in the United States.

## Supported ArcGIS Enterprise SDK Versions
**11.1+**

## About the Provider

Generally, providers can be defined as either ['full-fetch'](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/create-a-custom-data-feed-provider/) or ['pass-through'](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/create-a-custom-data-feed-provider/). Occassionally, providers can't fully use one of these two patterns due to limitations in connecting to the data source. For example, the API for NREL data on alternative fuel stations does not provide a way to paginate results. A pass-through provider cannot be used to fetch the data in manageable chunks by requesting the next page of data until all are retrieved. The dataset is also too large return with a single request. Furthermore, the API rate limit is quite strict, limiting the number of requests to 1000 per hour.

Building a provider that uses this API requires researching the dataset and the API specification for ways to overcome these constraints. By examining the [request parameters in the alternative fuel stations API](https://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/all/#request-parameters), we see that we can request data by `state`. Through experimenting, we find that payload size is not too large if we query multiple times by state, and we can combine the data from all the requests into a single custom data provider response. The ArcGIS clients will send multiple requests in paginated fashion to retrieve our combined data payload. Furthermore, we will implement a local cache of the combined reponse and set a time to live of 24 hours.

A portion of the `model.js` file is shown below demonstrating using multiple requests to fetch all the data and storing it in a local, provider cache.

```js

    // *code snippet*

    const config = require('config');
    class Model {
    
        constructor() {
            this.lastFetchTime = 0; // Initialize last fetch time to 0
            this.allTheData = [];
        }

        async getData() {
            const states = config.altFuelStations.states;
            const currentTime = Date.now();
            const timeElapsed = currentTime - this.lastFetchTime;
            const shouldFetch = timeElapsed > 86400000;
        
            if (shouldFetch) {
                this.allTheData = [];
        
                // Use Promise.all with map to make requests asynchronously
                await Promise.all(states.map(async (state) => {
                    try {
                        const response = await fetch(`${config.altFuelStations.afsURL}/v1.json?api_key=${config.altFuelStations.apiKey}&state=${state}`);
        
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
        
                        const data = await response.json();
                        this.allTheData.push(...data.fuel_stations);
                    } catch (error) {
                        console.log(`Failed to fetch data for state ${state}:`, error);
                    }
                }));
        
                this.lastFetchTime = Date.now();
            } else {
                console.log("Using cached data.");
            }
        
            let geojson = convertToGeoJSON(this.allTheData);
        
            return {
                ...geojson,
                metadata: {
                    idField: "id",
                    maxRecordCount: 5000
                }
            };
        }
    }

   // *code snippet*

```

## Set up the Provider

1.  Run the `cdf createapp nrel-app` command to create a new custom
    data app or use an existing custom data app.
2.  Run the `cdf createprovider nrel-alt-fuel-provider` command to create a
    custom data provider.
3.  Navigate to the **providers/nrel-alt-fuel-provider** directory in a
    command prompt and run the `npm i config` command.
4.  Copy the contents of the src folder in the provided source code into
    the src folder inside your **providers/nrel-alt-fuel-provider/src**
    directory.

## Obtain an API Key

1.  Follow the instructions in [NREL's developer documentation](https://developer.nrel.gov/signup/) to
    obtain a private API key.

2.  Once you have an API key, specify it in the
    **providers/nrel-alt-fuel-provider/config/default.json** file as the value for
    the **altFuelStations.apiKey** field as shown.

    ```json
    {
      "altFuelStations": {
        "apiKey": "<generated API key here>",
        "afsURL": "https://developer.nrel.gov/api/alt-fuel-stations",
        "states": ["AL","AK","AZ","AR","CA"] // add as many two-letter abbreviated US states as you wish here
      }
    }
    ```

## Configure Provider

1.  In the **providers/nrel-alt-fuel-provider/cdconfig.json** file, set the value of the
    `properties.hosts` field to `false` and
    `properties.disableIdParam` field to `true`.

## Test the Provider

1.  Navigate to the **nrel-app** directory in a command prompt and
    run the `npm start` command to start the custom data app
2.  In a web browser, navigate to
    http://localhost:8080/nrel-alt-fuel-provider/rest/services/FeatureServer/0/query
    and verify that the NREL alternative fuel stations provider is returning data points.

## Build and Deploy the Custom Data Provider Package File

1.  Stop the custom data app if it's running.
2.  Open a command prompt and navigate to the custom data app directory.
3.  Run the `cdf export nrel-alt-fuel-provider` command.
4.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
5.  Click **uploads \> upload**.
6.  On the **Upload Item** page, click **Choose File** and select the
    **nrel-alt-fuel-provider.cdpk** file. Optionally, provide a description in
    the **Description** text box.
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
    **services \> createService**.

2.  On the **Create Service** page, copy and paste the following JSON
    into the **Service (in JSON format)** text box.

    ```json
    {
      "serviceName": "NRELfuelStations",
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
          "dataProviderName": "nrel-alt-fuel-provider",
          "dataProviderHost": "",
          "dataProviderId": ""
        }
      },
      "extensions": [],
      "frameworkProperties": {},
      "datasets": []
    }
    ```

3.  Click **Create.**

## Consume Feature Service

To access the socrata feature service that you created in the previous
section, use the appropriate URL (e.g.,
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/NRELfuelStations/FeatureServer**).
You can use this URL to consume data from NREL in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise.
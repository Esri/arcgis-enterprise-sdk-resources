# Editing-enabled Google Sheets provider

This sample demonstrates how to create an [editing-enabled custom data provider](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/editable-custom-data-provider/) that allows for creating, reading, updating, and deleting point features in a Google Sheet using a Node.js wrapper for the Google Sheets API.
_Before you begin, you will need to setup your Google Sheet and obtain Google developer credentials if you don't already have them._

## How the Provider Works

### Google Sheets Data

Follow these basic steps for setting up a Google Sheet and obtaining developer credentials. For the most accurate, up-to-date, and detailed information, see the official documentation for [Google Cloud](https://cloud.google.com/?hl=en) and the [Google Sheets API](https://console.cloud.google.com/marketplace/product/google/sheets.googleapis.com).

1.  Create a Google Sheets document while signed in using your Google Account.

2.  Add the provided [data](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples/custom-data-feeds/google-sheets-editing-points/providers/google-sheets-provider) to the Google Sheet.

3.  Rename the default sheet name from `Sheet1` to `Shoes`.

4.  Take note of the Google Document ID because it will be needed below. The Document ID is a unique string that can be found in the URL when viewing your Google Sheet. In this sample URL,
    ```https://docs.google.com/spreadsheets/d/1P1xw1dHaXtX4EktOc8DuB7IP4Lrc4q9-pzYovqBtEfc/edit?gid=0#gid=0```, **1P1xw1dHaXtX4EktOc8DuB7IP4Lrc4q9-pzYovqBtEfc** is the Document ID.

### Google Sheets API

1.  In a browser, go to [Google Cloud Console](https://console.cloud.google.com) and sign in.

2.  If you do not have an existing project you wish to use, create a New Project and give it a name.

3.  Under "Quick Access", go to "APIs & Services". Click the "+ ENABLE APIS AND SERVICES" tab.

4.  Click the "Credentials" button on the left side menu.

5.  On the Credentials page, click on the "Create Credentials" button and select "Service Account". Fill in the required fields and click "Done."

6.  Navigate to the Service Account and click "KEYS". From the "ADD KEY" dropdown, select "Create new key". Choose the "JSON" option to download the key file.

7.  On the webpage displaying your Google Sheets, click the "Share" button and share the Google Sheets file with the Service Account email used in the steps above. The Service Account email address will have the format `<Name>@<random generated text>.iam.gserviceaccount.com`.

## Set up the Provider

1.  In a command prompt, run the `cdf createapp google-sheets-app` command to create a new custom
    data app, or navigate to an existing custom data app.
2.  Inside either the newly-created **google-sheets-app** directory or an existing custom data app directory, 
    run the `cdf createprovider google-sheets-provider` command to create a custom data provider.
3.  Copy the contents of the **src** folder in the provided source code into
    the **src** folder inside your **providers/google-sheets-provider/src**
    directory.
4.  Navigate to the **providers/google-sheets-provider** directory in a
    command prompt, and run the command `npm install config google-auth-library google-spreadsheet proj4 @esri/proj-codes` to install the needed modules. _`proj4` and `@esri/proj-codes` are tools that are used for coordinate system conversion._

## Configure the Provider

1.  In the **providers/google-sheets-provider/cdconfig.json** file, set the value of the
    `properties.hosts` field to `true` and
    `properties.disableIdParam` field to `false`.

2.  In the **providers/google-sheets-provider/config** directory, configure the Google Developer credentials in the **default.json** file.

````json
{
    "google_sheets_provider": {
        "client_email": "<service account email>",
        "private_key": "<your private key>"
    }
}
````

## Test the Provider

1.  Navigate to the **google-sheets-app** directory in a command prompt, and
    run the `npm start` command to start the custom data app.
2.  In a web browser, navigate to
    http://localhost:8080/google-sheets-provider/rest/services/googleDocumentId/sheetName/FeatureServer/0/query,
    and verify that the Google Sheets provider is returning data points.

## Build and Deploy the Custom Data Provider Package File

3.  Stop the custom data app if it is running.
4.  Open a command prompt and navigate to the custom data app directory.
5.  Run the `cdf export google-sheets-provider` command.
6.  In a web browser, navigate to the ArcGIS Server Administrator
    Directory and sign in as an administrator.
7.  Click **uploads \> upload**.
8.  On the **Upload Item** page, click **Choose File** and select the
    **google-sheets-provider.cdpk** file. Optionally, provide a
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
        "serviceName": "googleSheetsShoeStores",
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
                "dataProviderName": "google-sheets-provider",
                "dataProviderHost": "<google document id>",
                "dataProviderId": "<sheet name>"
            }
        },
        "extensions": [],
        "frameworkProperties": {},
        "datasets": []
    }

    ```

3.  Click **Create**.

4. In ArcGIS Server Administrator Directory, navigate to **Home > services > googleSheetsShoeStores > edit**.

5. Change the value of `capabilities` to `Query,Editing`, and click the **Save Edits** button.

![](./images/cdf-google-sheets-service-json.png)


## Consume Feature Service

To access the MongoDB feature service that you created in the
previous section, use the appropriate URL (e.g.,
**https://\<domain_or_machine_name\>/\<webadaptor_name\>/rest/services/googleSheetsShoeStores/FeatureServer**).
You can use this URL to consume data from MongoDB in ArcGIS clients like
ArcGIS Pro, ArcGIS Online, and ArcGIS Enterprise. 

For this sample, we recommend using ArcGIS Pro for consuming the service due to the smaller number of service calls ArcGIS Pro makes compared to clients such as Map Viewer. If you are using a free Google Developer account, you may run into API rate limiting issues. Check the custom data feeds logs or the network traffic if you suspect you've hit your rate limit.
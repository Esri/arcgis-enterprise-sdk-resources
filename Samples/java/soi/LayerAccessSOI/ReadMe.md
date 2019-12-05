---
order: 10
---

# Java layer access SOI

This sample illustrates the following areas:
* **How to handle REST, SOAP binary, and SOAP string requests.**
   Typically, ArcObjects-based clients, such as ArcMap, send SOAP binary requests and the `HandleBinaryRequest()` method has to be implemented, whereas non-ArcObjects-based clients send SOAP XML requests, which requires the `HandleStringRequest()` method to be implemented.
* **How to implement fine-grained layer level controls based on the groups that the logged-in user belongs to.**
   This SOI is able to grant different users the access to certain layers of a map service based on the rules defined in an external permission file ([permission.json](permission.json)).


Deploying the SOI from the .soe file (`../LayerAccessSOI/lib/LayerAccessSOI_ent.soe`) does not require you to open the IDE. However, you can load the project (`../LayerAccessSOI`) in the IDE to debug, modify, and rebuild the SOI project.

> **Note:** This sample is not comprehensive in what operations are intercepted. Developers should use this as the basis for learning how to implement security-related functionality, but all production code should be carefully vetted to ensure that all appropriate operations are handled and no information is inadvertently leaked or made accessible.

## Features

* Handle REST requests and responses in SOIs
* Get logged-in user information
* [disableCaching](#set-up-testing-environment)

## Sample data

Any dynamic map service published from ArcGIS Pro. This instruction uses the [USA map service](../../../ReadMe.md#1-usa-service) as the sample service to test with the SOI.


## Instructions

### Set up testing environment

1. Make sure you have published the USA map service using ArcGIS Pro. If not, refer to [USA map service](../../../ReadMe.md#1-usa-service).
2. Set the service's ***disableCaching*** property to true.\
As this sample SOI restricts user access to certain layers, it also alters the service's directory page content, which by default is cached. Therefore, you must disable service caching to view the changes on the service's directory page. This setting is only necessary when the service directory page content changes dynamically.
   1. Open the ArcGIS Server Administrator Directory (`http://<serverdomain>/<webadaptorname>/admin`) and sign in.
   2. Click ***services*** and click the ***USA*** map service.
       This leads to `http://<serverdomain>/<webadaptorname>/admin/services/USA.MapServer`
   3. Click ***edit***.
   4. In the properties section of the service JSON, add the property ***"disableCaching": "true"*** and click ***Save Edits***:

      ```
      "properties": {
        "disableCaching": "true",
        "maxDomainCodeCount": "25000",
        //... more properties
      },
      ```
      > **Note:** This step is very important. If ***disableCaching*** is not set to true, the service directory page content may not be displayed properly.
3. Grant proper user access to the USA map service.

#### For a stand-alone ArcGIS Server
This environment is configured with [users and roles from the built-in store](https://enterprise.arcgis.com/en/server/latest/administer/windows/securing-services-with-users-and-roles-specific-to-arcgis-server.htm#GUID-9D46D38D-DA48-47BE-A776-DD84C1CD0F4B).

1. Browse to **ArcGIS Server Manager** > **Security** tab.
2. Click the **Users** tab and the **New User** button to create new users with a username of **silver123**, **gold123** and **platinum123**.
3. Click the **Roles** tab to create a role which contains the users defined in the last step.

   ![](../../../../images/javasp/JavaOp1.png "Java Layer Access SOI Sample")
4. Browse to ***ArcGIS Server Manager*** > ***Services*** tab.
5. Right-click the ***Sharing Properties*** button next to the map service to secure it for logged-in users.
6. Check the ***Allow access to all users who are logged in*** checkbox.

    ![](../../../../images/javasp/JavaOp2.png "Java Layer Access SOI Sample")
7. Find the [permission.json](permission.json) file and edit it.

   It should reflect the service name, user roles with their authorized layers, for example:

    ```json
    {
        "permissions": [
          {
            "fqsn": "USA.MapServer",
            "permission": [
              {
                "role": "silver123",
                "authorizedLayers": "0"
              },
              {
                "role": "gold123",
                "authorizedLayers": "1"
              },
              {
                "role": "platinum123",
                "authorizedLayers": "2"
              }
            ]
          }
        ]
    }
    ```
8. Copy the permission.json file to the ArcGIS Server folder (default location C:\arcgisserver).

### Deploy the SOI

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***LayerAccessSOI_ent.soe*** file (`../LayerAccessSOI/lib/LayerAccessSOI_ent.soe` or the SOI's export path).
5. Click ***Add***.

### Enable the SOI on a map service

1. Log in to ArcGIS Server Manager and click the ***Services*** tab. Select USA map service and select ***Capabilities***.
2. In the ***Interceptors*** section, select ***Layer Access SOI*** in the ***Available Interceptors*** box and click the right arrow button to move it to ***Enabled Interceptors***.
3. Click the ***Save and Restart*** button to restart the service.

### Test the SOI

1. Open a browser and navigate to the REST services page (URL: `http://<serverdomain>/<webadaptorname>/rest/services`).
2. Click ***Login*** at the upper right corner to log in as a user created in the previous steps.
3. Click the ***USA*** map service.
4. Now you will see that the layer showing up on the directory page changes based on the logged-in user:

    ![](../../../../images/javasp/JavaOp3.png "Java Layer Access SOI Sample")
5. Test with [***Export Map***](https://developers.arcgis.com/rest/services-reference/export-map.htm), [***Find***](https://developers.arcgis.com/rest/services-reference/find.htm) or other operations, and observe the different results when different users perform the operation.

## Troubleshooting tips

1. Ensure that the ***disableCaching*** property is set to ***true***. Otherwise, the map service directory page content may not display properly. To check how to set this property, see [set up testing environment](#set-up-testing-environment).
2. When you test this SOI, make sure you access the service as a logged-in user. Anonymous access to the map service’s resources will not result in a valid response.

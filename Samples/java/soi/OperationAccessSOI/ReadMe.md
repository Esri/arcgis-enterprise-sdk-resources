---
order: 11
---

# Java operation access SOI

This sample illustrates how to add an extra layer of security control for individual operations that users are allowed to access. This SOI only allows three operations - [**Find**](https://developers.arcgis.com/rest/services-reference/find.htm), [**Identify**](https://developers.arcgis.com/rest/services-reference/identify-map-service-.htm), and [**Export Map**](https://developers.arcgis.com/rest/services-reference/export-map.htm) to certain users that are defined in the code, and blocks unauthorized users to access those operations. It also blocks all other service operations.

Deploying the SOI from the .soe file (`../OperationAccessSOI/lib/OperationAccessSOI_ent.soe`) does not require you to open the IDE. However, you can load the project (`../OperationAccessSOI`) in the IDE to debug, modify, and rebuild the SOI project.


## Features

* Get logged-in user information
* Block REST requests


## Sample data

Any dynamic map service published from ArcGIS Pro. This instruction uses the [USA map service](../../../ReadMe.md#1-usa-service) as the sample service to test with the SOI.

## Instructions

### Set up testing environment

1. Make sure you have published the USA map service using ArcGIS Pro. If not, refer to [USA map service](../../../ReadMe.md#1-usa-service).
2. Grant proper user access to the USA map service.

#### For a stand-alone ArcGIS Server
This environment is configured with [users and roles from the built-in store](https://enterprise.arcgis.com/en/server/latest/administer/windows/securing-services-with-users-and-roles-specific-to-arcgis-server.htm#GUID-9D46D38D-DA48-47BE-A776-DD84C1CD0F4B).

1. Browse to ***ArcGIS Server Manager*** > ***Security*** tab.
2. Click the ***Users*** tab and the ***New User*** button to create new users with a username of ***gold123*** and ***silver123***.
3. Click the ***Roles*** tab to create a role which contains the users defined in the last step.

   ![](../../../../images/javasp/JavaOp1.png "Java Operation Access SOI Sample")
4. Browse to ***ArcGIS Server Manager*** > ***Services*** tab.
5. Right-click the ***Sharing Properties*** button next to the map service to secure it for logged-in users.
6. Check the ***Allow access to all users who are logged in*** checkbox.

    ![](../../../../images/javasp/JavaOp2.png "Java Operation Access SOI Sample")

### Deploy the SOI

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***OperationAccessSOI_ent.soe*** file (`../OperationAccessSOI/lib/OperationAccessSOI_ent.soe` or the SOI's export path).
5. Click ***Add***.

### Enable the SOI on a map service

1. Log in to ArcGIS Server Manager and click the ***Services*** tab. Select USA map service and select ***Capabilities***.
2. In the ***Interceptors*** section, select ***Operation Access SOI*** in the ***Available Interceptors*** box and click the right arrow button to move it to ***Enabled Interceptors***.
3. Click the ***Save and Restart*** button to restart the service.

### Test the SOI

1. Open a browser and navigate to the REST services page (URL: `http://<serverdomain>/<webadaptorname>/rest/services`).
2. Click ***Login*** at the upper right corner to log in as user ***gold123***.
3. Click the ***USA*** map service and ***Export Map*** in ***Supported Extensions***.

   This leads you to the following URL and the ***Export Map*** operation generates a valid result:

   ```
   http://<serverdomain>/<webadaptorname>/rest/services/USA/MapServer/export?bbox=-178.85719640187426,13.522152002873426,-56.484036397641795,81.72479317856566
   ```
4. Click ***Logout*** at the upper right corner and clear the browser cache. 
5. Log in as another user ***silver123***.
6. Repeat step 1 to step 3. Now no image is exported and the operation returns a "404 Not Found" error if the ***Format*** parameter is set to JSON.
7. Test with other service operations under different user accounts, and see how this SOI blocks requests and grants access.

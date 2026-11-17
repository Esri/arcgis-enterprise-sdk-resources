---
order: 2
---

# Chaining Interceptors Sample

This sample demonstrates how multiple service interceptors interact with an editable hosted feature service. Each interceptor performs a specific task in a chain, keeping the logic separate and easier to manage. The sample uses three interceptors:

1. **Geocoding** — Retrieves address information from an incident's location.
2. **System Integration** — Enriches incident information using logic that represents integration with an external system.
3. **Response Enrichment** — Adds static values to selected fields in responses to query or edit operations.

The example uses the `Hosted/incidents` feature service.

> **Version note:** The linked online sample documentation currently lists ArcGIS Enterprise SDK 12.1 on Windows and Linux. Confirm compatibility and test the interceptor packages against the target SDK and ArcGIS Enterprise build before publishing them as 12.2 samples.

## Prerequisites

- An ArcGIS Enterprise environment with Portal for ArcGIS and hosting capabilities.
- Administrator access to the ArcGIS Server Administrator Directory.
- An editable `incidents` hosted feature service (instructions below).
- The [Chaining Interceptors sample](../Dataset/incidents.gdb.zip).
- An appropriate ArcGIS authentication token for the reverse-geocoding request used by the Geocoding interceptor.

## Configure the hosted feature service

1. Download the [Chaining Interceptors sample](../Dataset/incidents.gdb.zip).
2. Sign in to Portal for ArcGIS and go to **Content > My Content**.
3. Click **New item** and upload `incidents.gdb.zip`.
4. For **File type**, select **File geodatabase**. Under **How would you like to add this file?**, select **Add incidents.gdb.zip and create a hosted feature layer**, then click **Next**.
5. Set the title to `incidents` and click **Save**.
6. Open the `incidents` hosted feature layer item, go to **Settings**, and enable editing for the hosted feature layer.
7. Verify that the service is published and available for editing.

You can also publish an editing-enabled hosted feature service from ArcGIS Pro.

## Download and configure the sample code

1. Download the [Chaining Interceptors sample](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples/service-interceptors/Chaining) and extract it locally.
2. In the Geocoding project, locate:

   ```text
   Chaining/Geocoding/src/main/java/com/esri/interceptor/helpers/ReverseGeocodeAPI.java
   ```

3. Update the reverse-geocoding configuration for your environment. The official sample guide shows these declarations:

   ```java
   private static final String ACCESS_TOKEN = "YOUR_ACCESS_TOKEN_HERE";
   private static final String API_URL = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode" +
       "?f=pjson&";
   ```

   Replace `YOUR_ACCESS_TOKEN_HERE` with an appropriate token for testing and confirm that the endpoint is accessible. **Do not commit a real token to source control.**

4. Locate the compiled `.interceptor` packages in the `target` directories of the three sample projects. If the packages are not available, build the sample projects before deployment.

## Deploy the interceptors

Deployment has two steps for each interceptor: upload and register.

### Upload and register

Repeat these steps for **Geocoding**, **System Integration**, and **Response Enrichment**.

1. Sign in to the **ArcGIS Server Administrator Directory** as an administrator.
2. Navigate to **Home > uploads > upload**.
3. Choose the relevant `.interceptor` file from the sample project's `target` directory.
4. Click **Upload** and copy the **Item ID** from the uploaded item page.
5. Navigate to **Home > system > interceptors**:

   ```text
   https://<server-domain>/<server-web-adaptor>/admin/system/interceptors
   ```

6. Click **register**, enter the uploaded **Item ID**, and click **Register**.
7. Repeat until all three interceptors are registered.

### Configure the chaining order

1. In the ArcGIS Server Administrator Directory, navigate to:

   ```text
   https://<server-domain>/<server-web-adaptor>/admin/system/interceptors/chainingorder
   ```

2. In **Chaining Order**, enter the sample configuration:

   ```json
   [
     {
       "interceptorName": "Geocoding",
       "chainingOrder": 1,
       "urlPatterns": ["Hosted/incidents/FeatureServer"]
     },
     {
       "interceptorName": "SystemIntegration",
       "chainingOrder": 2,
       "urlPatterns": ["Hosted/incidents/FeatureServer"]
     },
     {
       "interceptorName": "ResponseEnrichment.interceptor",
       "chainingOrder": 3,
       "urlPatterns": ["Hosted/incidents/FeatureServer"]
     }
   ]
   ```

3. Apply the configuration, then confirm the chaining order is saved.

> **Important:** The JSON above preserves the interceptor names shown in the official sample guide. Verify that each `interceptorName` matches the name displayed for the interceptor actually registered in your environment. In particular, confirm whether the third registered name includes the `.interceptor` suffix. Replace `Hosted/incidents/FeatureServer` if you published the service under a different name.

## Verify the results

### Establish the baseline

1. In **Map Viewer**, add the `incidents` hosted feature layer to a new map.
2. Inspect an existing incident. Before registering the interceptors, confirm that `agency`, `agency_name`, `resolution_description`, and `last_action_date` are empty, as described in the sample guide.
3. Open the **Edit** tool and create a new point incident. Choose a **complaint type** and **location type** and leave the other fields blank.
4. Select the new incident and inspect its attributes to establish the behavior before interception.

### Test the chained interceptors

1. Upload and register all three interceptors, then configure the chaining order as described above. If you performed the baseline after deployment, use a clean environment or remove the relevant registrations before comparing behavior.
2. Refresh the map in Map Viewer.
3. Add another point incident, selecting a **complaint type** and **location type** while leaving the remaining inputs blank.
4. Exit edit mode and select the new incident to open its pop-up.
5. Verify that the response includes the expected fields:

   - Address information: `address`, `street_name`, `city`, `zipcode`
   - Incident/system information: `incident_id`, `agency`, `agency_name`, `resolution_description`, `last_action_date`

6. Select existing incidents and verify the response-enriched fields (`incident_id`, `agency`, `agency_name`, `resolution_description`, and `last_action_date`) are available as described in the sample guide.

### Expected behavior

| Interceptor | Expected behavior |
| --- | --- |
| Geocoding | Populates `address`, `street_name`, `city`, and `zipcode` from the incident location. |
| System Integration | Adds values for `resolution_description` and `last_action_date` to illustrate external-system integration. |
| Response Enrichment | Adds static values for fields such as `incident_id`, `agency`, and `agency_name`. |

The values returned depend on the sample logic, the published data, and access to the reverse-geocoding service. Inspect service responses to distinguish response enrichment from changes persisted to the hosted feature layer.

## Additional resources

- [Chaining Interceptors Sample documentation](https://developers.arcgis.com/enterprise-sdk/guide/service-interceptors/service-interceptor-chaining-sample/)
- [ArcGIS Enterprise SDK resources repository](https://github.com/Esri/arcgis-enterprise-sdk-resources)



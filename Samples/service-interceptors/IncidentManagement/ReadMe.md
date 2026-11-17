---
order: 3
---

# Incident Management Interceptor Sample

This sample demonstrates how a service interceptor modifies outgoing responses from a hosted feature service. The Incident Management Interceptor handles responses to add and query operations, enriches designated fields, and forwards the modified response to the client. The sample documentation describes support for both JSON and PBF response formats.

The example uses the editable `Hosted/incidents` feature service.

> **Version note:** The linked online sample documentation currently lists ArcGIS Enterprise SDK 12.1 on Windows and Linux. Confirm compatibility and test the interceptor packages against the target SDK and ArcGIS Enterprise build before publishing them as 12.2 samples.

## Prerequisites

- An ArcGIS Enterprise environment with Portal for ArcGIS and hosting capabilities.
- Administrator access to the ArcGIS Server Administrator Directory.
- The [Incident Management Interceptor sample](../Dataset/incidents.gdb.zip).
- The `incidents` sample dataset and an editing-enabled hosted feature layer created from it.

## Configure the hosted feature service

1. Download the [Incident Management Interceptor sample](../Dataset/incidents.gdb.zip).
2. Sign in to Portal for ArcGIS and navigate to **Content > My Content**.
3. Click **New item** and upload `incidents.gdb.zip`.
4. For **File type**, select **File geodatabase**. Under **How would you like to add this file?**, select **Add incidents.gdb.zip and create a hosted feature layer**, then click **Next**.
5. Set the title to `incidents` and click **Save**.
6. Return to **Content > My Content** and verify that the `incidents` hosted feature layer was created.
7. Open the hosted feature layer item and enable editing in its settings.

Alternatively, you can create an editing-enabled hosted feature layer using ArcGIS Pro.

## Download the sample code

Download the [Incident Management Interceptor sample](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples/service-interceptors/IncidentManagement) and extract it locally.

The sample documentation uses this compiled package:

```text
IncidentManagement/target/IncidentManagement.interceptor
```

If the package is not included with the sample, build the sample project before deployment.

## Deploy the interceptor

Deployment involves uploading the `.interceptor` package and registering it with ArcGIS Server.

### Upload and register

1. Sign in to the **ArcGIS Server Administrator Directory** as an administrator.
2. Navigate to **Home > uploads > upload**.
3. Click **Choose File** and select `IncidentManagement.interceptor` from the `IncidentManagement/target` directory.
4. Click **Upload** and copy the **Item ID** from the uploaded item page.
5. Navigate to **Home > system > interceptors**:

   ```text
   https://<server-domain>/<server-web-adaptor>/admin/system/interceptors
   ```

6. Click **register** and enter the uploaded **Item ID**.
7. Click **Register** to complete registration.

According to the sample documentation, the interceptor begins processing matching responses immediately after registration.

## Verify the results

### Verify response enrichment on an existing incident

1. In **Map Viewer**, add the `incidents` hosted feature layer to a map.
2. Select an existing incident on the map to open its information pop-up.
3. Verify that the response contains the enriched field values described in the sample guide:

   | Field | Example value |
   | --- | --- |
   | `agency` | `NYPD` |
   | `agency_name` | `New York City Police Department` |
   | `status` | `In Progress` |
   | `resolution_description` | `This is a test resolution description` |
   | `last_action_date` | Current timestamp at the time the sample runs |

   The timestamp changes when the response is processed; do not compare it with a fixed date from an example screenshot.

### Verify response enrichment when adding an incident

1. Open the **Edit** tool in Map Viewer.
2. Add a new point incident.
3. Select an appropriate **complaint type** and **location type**, leaving the other attributes blank.
4. Finish the edit and select the new incident.
5. Verify that its response includes the enrichment fields described in the sample guide:

   - `address`
   - `street_name`
   - `city`
   - `zipcode`
   - `incident_id`
   - `agency`
   - `agency_name`
   - `resolution_description`
   - `last_action_date`

> **Note:** This sample demonstrates **response enrichment**. Values visible in the returned response or Map Viewer pop-up are not, by themselves, proof that the same values were permanently saved in the feature service's underlying data. Verify persistence separately if your use case requires it.

## Additional resources

- [Incident Management Interceptor Sample documentation](https://developers.arcgis.com/enterprise-sdk/guide/service-interceptors/service-interceptor-incident-management-sample/)
- [ArcGIS Enterprise SDK resources repository](https://github.com/Esri/arcgis-enterprise-sdk-resources)


---
order: 4
---

# Access Control Interceptor Sample

This sample demonstrates fine-grained, per-user access control for a hosted feature service. The interceptor identifies the requesting user and returns only the features assigned to that user. The sample uses a U.S. states dataset and hard-coded usernames for demonstration purposes. For example, `james12` can access only the California and Oregon features when querying the `Hosted/States` feature service.

## Prerequisites

- An ArcGIS Enterprise deployment with Portal for ArcGIS and a hosted feature service.
- An administrator account for Portal for ArcGIS and the ArcGIS Server Administrator Directory.
- The [Access Control Interceptor sample](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples/service-interceptors/AccessControlInterceptor), including the `AccessControl.interceptor` package, or a locally built package.
- The [States sample dataset](../Dataset/States.gdb.zip).

> **Note:** This sample uses hard-coded usernames and state assignments. It is intended to demonstrate interceptor behavior, not to provide a production-ready authorization policy.

## Configure the hosted feature service

Follow these steps to publish the `States` hosted feature layer in Portal for ArcGIS:

1. Download the [States sample dataset](../Dataset/States.gdb.zip).
2. Sign in to Portal for ArcGIS and navigate to **Content > My Content**.
3. Click **New item** and upload `States.gdb.zip`.
4. Under **File type**, select **File geodatabase**. Under **How would you like to add this file?**, select **Add States.gdb.zip and create a hosted feature layer**, and click **Next**.
5. Set the title to `States` and click **Save**.
6. Return to **Content > My Content** and verify that the `States` hosted feature layer was created.

Alternatively, you can publish the hosted feature layer from ArcGIS Pro.

## Set up the testing environment

### Create test users

Create three Portal for ArcGIS users to test the hard-coded access rules:

1. Sign in to Portal for ArcGIS as an administrator.
2. Navigate to **Organization > Members > Add members**.
3. Select **New member** and click **Next**.
4. Create `james12` using the information below. Follow your organization's password and account policies.
5. Complete the remaining steps in the member creation workflow, keeping the default settings where appropriate.
6. Repeat the process for `sam259` and `tony64`.

| Username | First name | Last name | Role | Example email address |
| --- | --- | --- | --- | --- |
| `james12` | James | Smith | Viewer | `james12@example.com` |
| `sam259` | Sam | Johnson | Viewer | `sam259@example.com` |
| `tony64` | Tony | Brown | Viewer | `tony64@example.com` |

Replace the example email addresses with valid addresses if required by your organization.

### Create a group and share the hosted feature layer

1. In Portal for ArcGIS, navigate to **Groups** and click **Create group**.
2. Set the group name to `ViewMapGroup`, keep the remaining settings at their defaults, and click **Save**.
3. Select **Invite members** and add `james12`, `sam259`, and `tony64` to the group.
4. Select **Add items to group**, choose the `States` hosted feature layer, and click **Add items**.
5. Verify that all three users are members of `ViewMapGroup` and that the `States` hosted feature layer is available to the group.

## Download the sample code

Download the [Access Control Interceptor sample](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples/service-interceptors/AccessControlInterceptor) from the ArcGIS Enterprise SDK resources repository.

The interceptor package used in the following instructions is named `AccessControl.interceptor` and is located in the sample project's `target` directory. If it is not included in your download, build the sample project to produce the package before proceeding.

## Deploy the interceptor

Deploying the interceptor involves two steps: uploading the package and registering it with ArcGIS Server.

### Upload and register the interceptor

1. Sign in to the **ArcGIS Server Administrator Directory** as an administrator.
2. Navigate to **uploads > upload**.
3. Click **Choose File** and select `AccessControl.interceptor` from the `AccessControlInterceptor/target` directory.
4. Click **Upload**. On the uploaded item page, copy the **Item ID**.
5. Navigate to **Home > system > interceptors**. The corresponding endpoint is:

   ```text
   https://<server-domain>/<server-web-adaptor>/admin/system/interceptors
   ```

6. Click **register**.
7. Paste the uploaded **Item ID** into the registration input field.
8. Click **Register** to complete registration.

According to the sample documentation, the interceptor begins functioning after it is registered.

## Verify the results

### Test with the configured users

1. Sign in to Portal for ArcGIS as `james12`.
2. Navigate to **Groups > ViewMapGroup > Content**.
3. Select the `States` hosted feature layer and click **Open in Map Viewer**.
4. Verify that the map shows only **California** and **Oregon**.
5. Sign out and repeat the test with `sam259` and `tony64`.

Expected results:

| Username | Accessible states |
| --- | --- |
| `james12` | California, Oregon |
| `sam259` | Washington, New York |
| `tony64` | Texas, Oklahoma |

### Test with a user not listed in the sample

1. Sign in as an administrator or another user who has access to the `States` hosted feature layer and whose username is not hard-coded in the interceptor.
2. Open the same layer in Map Viewer.
3. Verify that all states are visible.

This all-states behavior is specific to the sample's demonstration logic. Do not use the sample's hard-coded access rules as a production security configuration.

## Additional resources

- [Access Control Interceptor Sample documentation](https://developers.arcgis.com/enterprise-sdk/guide/service-interceptors/service-interceptor-access-control-sample/)
- [ArcGIS Enterprise SDK resources repository](https://github.com/Esri/arcgis-enterprise-sdk-resources)



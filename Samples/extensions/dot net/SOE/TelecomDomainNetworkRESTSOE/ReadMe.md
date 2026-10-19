---
order: 16
tags:
  - Samples
  - net
---

# .NET Telecom Domain Network REST SOE

This sample demonstrates how to use the telecom domain network API. The REST server object extension (SOE) has one operation called **TelecomDomainNetworkOperation**. It opens the geodatabase, reads telecom domain network properties, creates and verifies a sectioned circuit, performs equipment unit identifier operations, and reads color scheme information. 

Deploying the SOE from the **TelecomDomainNetworkRESTSOE_ent.soe** file does not require you to open Visual Studio. However, to debug, modify, or recompile the code, open `TelecomDomainNetworkRESTSOE.csproj` in Visual Studio with the ArcGIS Enterprise SDK installed. The project targets .NET 10 and ArcGIS Server 12.2. Building the **Debug** configuration for **x64** generates the SOE in `bin\x64\Debug\net10.0\win-x64\TelecomDomainNetworkRESTSOE_ent.soe`, relative to this sample folder.

## Features

* Opening a mobile geodatabase (`SqliteWorkspaceFactoryClass`, `IWorkspaceFactory2`)
* Opening a utility network from a feature dataset (`IFeatureDatasetExtensionContainer`, `IDatasetContainer3`)
* Reading the utility network schema version and telecom domain network properties (`IDEBaseNetwork`, `IDEUtilityNetwork`, `ITelecomDomainNetwork`)
* Creating, querying, and verifying circuits (`IUtilityNetworkCircuit`, `IUNCircuitManager`, `ICircuit`, `ICircuitSection`)
* Querying, reserving, resizing, and resetting equipment unit identifiers (`IUtilityNetworkEquipmentIdentifiers`)
* Reading color scheme labels and codes (`IUtilityNetworkColorScheme`)

## Sample data

Use the **TelecomUtilityNetwork.zip** sample archive provided with this sample. Extract it to **C:\TelecomUtilityNetwork**, so the final mobile geodatabase path is:

```text
C:\TelecomUtilityNetwork\TelecomUtilityNetwork.geodatabase
```

To simplify the sample deployment, this SOE is designed to work without publishing telecom utility network datasets. Ensure that the mobile geodatabase resides at the correct path on the ArcGIS Server machine, not only on the browser host. Assign both read and write permissions for the mobile geodatabase and its folder to the ArcGIS Server account. The SOE connects directly to the mobile geodatabase and does not access the utility network through map service layers. 

The sample expects utility network schema version **8 or later**, a telecom domain network named **Telco**, and the sample's network sources and records. The implementation opens the first feature dataset and its first utility network. Circuit locations, equipment GlobalIDs, source names (`TelcoJunctionObject` and `TelcoDevice`), and color scheme identifiers are specific to the supplied data. To use a different database, update these values in `TelecomDomainNetworkRESTSOE.cs` and rebuild the SOE.

> **Note:** This operation is not read-only. It saves circuit and equipment edits to the mobile geodatabase. Use a disposable copy of the sample data, not a production database. Before repeating the operation, restore a clean copy to avoid conflicts with the circuit named `MySectionedCircuit` created by a previous request.

## Instructions

### Deploy the SOE

1. Log in to ArcGIS Server Manager and click the **Site** tab.
2. Click **Extensions**.
3. Click **Add Extension**.
4. Click **Choose File** and choose **TelecomDomainNetworkRESTSOE_ent.soe** from the build output folder, for example `bin\x64\Debug\net10.0\win-x64\TelecomDomainNetworkRESTSOE_ent.soe`.
5. Click **Add**.

### Enable the SOE on a map service

1. Make sure you have a map service published from ArcGIS Pro. Any dynamic map service can host this SOE because the mobile geodatabase is opened directly from its file path.
2. Log in to ArcGIS Server Manager and click the **Services** tab. Select the map service.
3. On the **Pooling** page, select the **dedicated instance** pool. This SOE does not support shared instances.
4. Select **Capabilities**. Find **Telecom Domain Network REST SOE** and check the box to enable it.
5. Click **Save and Restart** to restart the service.

### Test the SOE in the ArcGIS Server Services Directory

1. Open a browser and navigate to the map service's REST endpoint:

   ```text
   https://<serverdomain>/<webadaptorname>/rest/services/<servicename>/MapServer
   ```

2. Scroll to **Supported Extensions** and click **TelecomDomainNetworkRESTSOE**. This opens the SOE's root resource:

   ```text
   https://<serverdomain>/<webadaptorname>/rest/services/<servicename>/MapServer/exts/TelecomDomainNetworkRESTSOE
   ```

   The root resource returns `UtilityNetworkDomain` with the value `Telecom Domain Network`.

3. Click **TelecomDomainNetworkOperation** under **Supported Operations**.
4. In the **MobileGeodatabasePath** input box, enter:

   ```text
   C:\TelecomUtilityNetwork\TelecomUtilityNetwork.geodatabase
   ```

   Alternatively, leave the input empty to use the same default path defined by `gdbPath` in the source code. To use another location, enter its full path on the server; this parameter is not a file upload.

5. Choose **json** as the output format and click **TelecomDomainNetworkOperation (POST)**.
6. Inspect the JSON response. It contains the following properties:

   | Property | Description |
   |----------|-------------|
   | `MobileGeodatabasePath` | The mobile geodatabase path used by the operation. |
   | `DatasetName` | The utility network dataset name. |
   | `SchemaVersion` | The utility network schema version. |
   | `DomainNetworkName` | The telecom domain network name, `Telco`. |
   | `CircuitsInfo` | Queried circuit properties and circuit verification results. |
   | `EquipmentInfo` | The equipment container source ID and GlobalID, unit gaps, and contents queried before the equipment edits. |

   Domain network properties and color scheme information are accessed by the sample code but are not included in the JSON response.
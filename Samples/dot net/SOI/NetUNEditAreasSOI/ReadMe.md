---
order: 16
---

# .NET utility network edit areas collaboration REST SOI

This sample demonstrates how to communicate the network areas being edited by levereging the utility network's dirty areas table. The SOI intercepts the call to the [Validate Network Topology](https://developers.arcgis.com/rest/services-reference/validatenetworktopology-utiility-network-server-.htm) REST operation and processes the dirty areas to create a new polygon in a common non-versioned feature class. This feature class is shared, as a feature layer, in the same ArcGIS Pro project as the utility network layers.

In order to use this SOI, the administrator must first create a new non-versioned feature class with the following properties in the same Enterprise Geodatabase as the Utility Network dataset and under the same owning user.

Feature class name: EDITAREAS

Fields (Types):
- OBJECTID (Object Id)
- CREATIONDATE (Date)
- LASTUPDATE (Date)
- EDITCOUNT (Long)
- VERSIONNAME (Text)
- CREATOR (Text)
- SHAPE (Shape)

The administrator that deploys this SOI must enable it on the map service that also has the Utility Network capability enabled.

Deploying the SOE from the .soe file (`..\NetUNEditAreasSOI\bin\Release\NetUNEditAreasSOI_ent.soe`) does not require you to open Visual Studio. However, you can load the project (`..\NetUNEditAreasSOI\NetUNEditAreasSOI.csproj`) in Visual Studio to debug, modify, and recompile the SOE code.

The Utility Network owner must make sure that the network topology has been enabled.

## Features
  * Opening a Utility Network dataset (`IFeatureDataset`, `IDataset`)
  * Getting network properties and dirty areas table (`IBaseNetwork`, `IDEBaseNetwork`)
  

## Sample data
This sample does not require a specific utility network dataset. For example, you can load the [Electric Utility Network Foundation](https://solutions.arcgis.com/electric/help/electric-utility-network-foundation/) Naperville demo data in an Enterprise Geodatabase and publish it as a service on ArcGIS Enterprise. Please follow the instructions provided in the above link to download, configure and publish the data.


## Instructions

### Deploy the SOI

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***NetUNEditAreasSOI_ent.soe*** file (`..\NetUNEditAreasSOI
\bin\Release\NetUNEditAreasSOI_ent.soe` or `..\NetUNEditAreasSOI\bin\Debug\NetUNEditAreasSOI_ent.soe`).
5. Click ***Add***.

### Publish both services

1. Using ArcGIS Pro, follow the steps listed in [Publish and consume services with a utility network](https://pro.arcgis.com/en/pro-app/latest/help/data/utility-network/publishing-and-consuming-services-with-the-utility-network.htm) to publish the utility network layers.
2. Still using ArcGIS Pro, connect to the Enterprise Geodatabase as the utility network owner and publish the edit areas feature layer as a service (non-versioned).

### Enable the SOI on a map service

1. Make sure you have published the utility network service whose data source is from an Enterprise Geodatabase using ArcGIS Pro. If not, refer to [Electric Utility Network Foundation](https://solutions.arcgis.com/electric/help/electric-utility-network-foundation/).
2. Log in to ArcGIS Server Manager and click the ***Services*** tab. Select the utility network map service and select ***Capabilities***.
3. Under ***Interceptors***, select ***NetUNEditAreasSOI*** and click on the right arrow to add it to the list of enabled interceptors.
4. Click the ***Save and Restart*** button to restart the service.

### Create an ArcGIS Pro project
1. In ArcGIS Pro, create a new project and connect to the Portal on which both services have been published.
2. Add both the utility network feature service and the edit areas feature service on a new map.
3. Save the project.

### Test the SOI in ArcGIS Pro

1. In ArcGIS Pro, open the project created in the previous step.
2. On the Utility Network layer, create a branch version and change to it.
3. Edit one or more features that participate in the network, such as devices. Make sure to edit an attribute that will generate a dirty area (e.g. asset type, asset group, lifecycle status). 
4. Save the edits made to the version.
5. Execute the Validate Network Topology tool. This should remove the dirty areas and create a new edit area in the edit areas layer. 
6. Delete the branch version. This should remove the edit area from the edit areas layer.
     
   

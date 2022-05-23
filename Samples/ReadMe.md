## Overview

The SOE samples and SOI samples in this repository are also included with the installation of ArcGIS Enterprise SDK. It's located at `<ArcGIS_Enterprise_SDK_Installation_Path>\Samples`, by default, `C:\Program Files\ArcGIS\EnterpriseSDK\Samples`.

> Note: This repository contains SOE and SOI samples built with **10.9.1**, the latest version of ArcGIS Enterprise SDK. If you want to download the SOE and SOI samples built with 10.9 or previous versions of ArcGIS Enterprise SDK, refer to [Releases](https://github.com/Esri/arcgis-enterprise-sdk-resources/releases).

The sample data used to publish the testing services can be found at `<ArcGIS_Enterprise_SDK_Installation_Path>\Samples\EnterpriseSDKSampleData.zip`, by default, `C:\Program Files\ArcGIS\EnterpriseSDK\Samples\EnterpriseSDKSampleData.zip`.

The following three services are used in the samples:
1. **USA map service**
    1. Open an ArcGIS Pro map.
    2. Add the Cities, Highways, Counties, and State feature classes from usa.gdb (`...\EnterpriseSDKSampleData\data\usa\usa.gdb`) to the map.
    3. Share the map as a web layer. Make sure the Map Image option is checked for Layer Type.
    4. Publish the service

2. **Vegetation map service**
    1. Open an ArcGIS Pro map.
    2. Add the veg feature class from veg.gdb (`...\EnterpriseSDKSampleData\Yellowstone\veg.gdb`) to the map.
    3. Share the map as a web layer. Make sure the Map Image option is checked for Layer Type.
    4. Publish the service
    
3. **Naperville service**

    This service is used to test with the [utility network tracing REST SOE](dot%20net/SOE/UNTracingRESTSOE), which is available since 10.8.1. You can follow the instructions at [Electric Utility Network Foundation](https://solutions.arcgis.com/electric/help/electric-utility-network-foundation/) to load the Naperville demo data in an Enterprise Geodatabase and publish it as the NapervilleElectricFoundation service on ArcGIS Enterprise.
   
   
## Samples
### For .NET
* **.NET SOE Samples**
  * [Simple REST SOE](dot%20net/SOE/SimpleRESTSOE)
  * [Simple REST SOE With Capabilities](dot%20net/SOE/SimpleRESTSOEWithCapabilities)
  * [Simple REST SOE With Properties](dot%20net/SOE/SimpleRESTSOEWithProperties)
  * [Spatial Query REST SOE](dot%20net/SOE/SpatialQueryRESTSOE)
  * [Find Nearby Features REST SOE](dot%20net/SOE/FindNearFeaturesRESTSOE)
  * [Edit Features REST SOE](dot%20net/SOE/EditFeaturesRESTSOE)
  * [Download File REST SOE](dot%20net/SOE/DownloadFileRESTSOE)
  * [Simple SOAP SOE](dot%20net/SOE/SimpleSOAPSOE)
  * [Find Nearby Features SOAP SOE](dot%20net/SOE/FindNearFeaturesSOAPSOE)
  * [Utility Network Tracing REST SOE](dot%20net/SOE/UNTracingRESTSOE) (From 10.8.1)
  * [Query Network Dataset REST SOE](dot%20net/SOE/QueryNetworkDatasetRESTSOE) (From 10.9.1) 🆕
  * [Topology REST SOE](dot%20net/SOE/TopologyRESTSOE) (From 10.9.1) 🆕

* **.NET SOI Samples**
  * [Simple Logger SOI](dot%20net/SOI/NetSimpleLoggerSOI)
  * [Apply Watermark SOI](dot%20net/SOI/NetApplyWatermarkSOI)
  * [Layer Access SOI](dot%20net/SOI/NetLayerAccessSOI)
  * [Operation Access SOI](dot%20net/SOI/NetOperationAccessSOI)
  * [Spatial Restriction SOI](dot%20net/SOI/NetSpatialRestrictionSOI)
  * [Utility Network Edit Areas Collaboration REST SOI](dot%20net/SOI/NetUNEditAreasSOI) (From 10.8.1)

* **.NET SOAP Client Samples**
  * [Overview of building .NET SOAP client application](dot%20net/.NET%20SOAP%20Client)
  
### For Java
* **Java SOE Samples**
  * [Simple REST SOE](java/soe/simplerestsoe)
  * [Simple REST SOE With Capabilities](java/soe/simplerestsoewithcapabilities)
  * [Simple REST SOE With Properties](java/soe/simplerestsoewithproperties)
  * [Find Nearby Features REST SOE](java/soe/findnearbyfeaturesrestsoe)
  * [Download File REST SOE](java/soe/downloadfilerestsoe)
  * [Simple SOAP SOE](java/soe/simplesoapsoe)
  * [Find Nearby Features SOAP SOE](java/soe/findnearbyfeaturessoapsoe)
  * [Utility Network Tracing REST SOE](java/soe/untracingrestsoe) (From 10.8.1)

* **Java SOI Samples**
  * [Simple SOI](java/soi/SimpleSOI)
  * [Apply Watermark SOI](java/soi/ApplyWatermarkSOI)
  * [Layer Access SOI](java/soi/LayerAccessSOI)
  * [Operation Access SOI](java/soi/OperationAccessSOI)  
  * [Spatial Restriction SOI](java/soi/SpatialRestrictionSOI)
  
* **Java SOAP Client Samples**
  * [Overview of building Java SOAP client application](java/Java%20SOAP%20Client)

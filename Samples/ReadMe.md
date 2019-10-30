## Overview

The samples in this repository are also included with the installation of ArcGIS Enterprise SDK. It's located at `<ArcGIS_Enterprise_SDK_Installation_Path>\Samples`, by default, `C:\Program Files\ArcGIS\EnterpriseSDK\Samples`.

The sample data used to publish the testing services can be found at `<ArcGIS_Enterprise_SDK_Installation_Path>\Samples\EnterpriseSDKSampleData.zip`, by default, `C:\Program Files\ArcGIS\EnterpriseSDK\Samples\EnterpriseSDKSampleData.zip`.

The following two services are used in the samples:
1. **USA service**
    1. Open an ArcGIS Pro map.
    2. Add the Cities, Highways, Counties, and State feature classes from usa.gdb (`...\EnterpriseSDKSampleData\data\usa\usa.gdb`) to the map.
    3. Share the map as a web layer. Make sure the Map Image option is checked for Layer Type.
    4. Publish the service

2. **Veg service**
    1. Open an ArcGIS Pro map.
    2. Add the veg feature class from veg.gdb (`...\EnterpriseSDKSampleData\Yellowstone\veg.gdb`) to the map.
    3. Share the map as a web layer. Make sure the Map Image option is checked for Layer Type.
    4. Publish the service
   
   
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

* **.NET SOI Samples**
  * [Simple Logger SOI](dot%20net/SOI/SimpleLoggerSOI)
  * [Apply Watermark SOI](dot%20net/SOI/NetApplyWatermarkSOI)
  * [Layer Access SOI](dot%20net/SOI/NetLayerAccessSOI)
  * [Operation Access SOI](dot%20net/SOI/NetOperationAccessSOI)
  * [Spatial Restriction SOI](dot%20net/SOI/NetSpatialRestrictionSOI)

### For Java
* **Java SOE Samples**
  * [Simple REST SOE](java/soe/simplerestsoe)
  * [Simple REST SOE With Capabilities](java/soe/simplerestsoewithcapabilities)
  * [Simple REST SOE With Properties](java/soe/simplerestsoewithproperties)
  * [Find Nearby Features REST SOE](java/soe/findnearbyfeaturesrestsoe)
  * [Download File REST SOE](java/soe/downloadfilerestsoe)
  * [Simple SOAP SOE](java/soe/simplesoapsoe)
  * [Find Nearby Features SOAP SOE](java/soe/findnearbyfeaturessoapsoe)

* **Java SOI Samples**
  * [Simple SOI](java/soi/SimpleSOI)
  * [Apply Watermark SOI](java/soi/ApplyWatermarkSOI)
  * [Layer Access SOI](java/soi/LayerAccessSOI)
  * [Operation Access SOI](java/soi/OperationAccessSOI)  
  * [Spatial Restriction SOI](java/soi/SpatialRestrictionSOI)

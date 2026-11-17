## Overview

This repository provides sample code for developing and testing custom extensions, custom data feeds, and service interceptors with **ArcGIS Enterprise SDK 12.2**.

The SDK supports the following extensibility patterns:

- **Server Object Extensions (SOEs):** Add custom REST or SOAP capabilities to map and image services.
- **Server Object Interceptors (SOIs):** Intercept requests and responses for map and image services.
- **Custom Data Feeds (CDFs):** Integrate external data sources with ArcGIS Enterprise through custom data providers.
- **Service Interceptors:** Inspect and modify HTTP requests and responses for REST-based ArcGIS Server services. Service interceptors are developed in Java and are distinct from SOIs.

SOE, SOI, and service interceptor sample code is also provided with the ArcGIS Enterprise SDK installation. On Windows, the default installation location is:

`C:\Program Files\ArcGIS\EnterpriseSDK\Samples`

Common sample-code locations on Windows include:

- **.NET SOEs and SOIs:** `C:\Program Files\ArcGIS\EnterpriseSDK\Samples\DotNet`
- **Java SOEs:** `C:\Program Files\ArcGIS\EnterpriseSDK\Samples\Java\serverobjectextensions`
- **Java SOIs:** `C:\Program Files\ArcGIS\EnterpriseSDK\Samples\Java\serverobjectinterceptors`
- **Service Interceptors:** `C:\Program Files\ArcGIS\EnterpriseSDK\Samples\Java\serviceinterceptors`

## Download the samples

1. Clone the [ArcGIS Enterprise SDK resources repository](https://github.com/Esri/arcgis-enterprise-sdk-resources):

   ```bash
   git clone https://github.com/Esri/arcgis-enterprise-sdk-resources.git
   ```

2. Open the `Samples` directory in the cloned repository.
3. Select a sample and follow its README instructions to build, deploy, and test it.

## Sample data

Sample data for publishing services used to test SOE and SOI samples is included with the SDK installation as `EnterpriseSDKSampleData.zip`.

Default Windows location:

`C:\Program Files\ArcGIS\EnterpriseSDK\Samples\EnterpriseSDKSampleData.zip`

1. Locate `EnterpriseSDKSampleData.zip` in the SDK `Samples` directory.
2. Extract the ZIP file to a directory of your choice.
3. Open the extracted `EnterpriseSDKSampleData/data` directory. It contains the following folders:
   - `AirportAndGolf`
   - `California`
   - `proxygen`
   - `SanFrancisco`
   - `toxic`
   - `usa`
   - `world`
   - `Yellowstone`
4. Use the dataset required by your chosen sample. For the USA map service, navigate to `EnterpriseSDKSampleData/data/usa`.

For example, if you extract the archive to `C:\EnterpriseSDKSampleData`, the USA dataset is located at:

`C:\EnterpriseSDKSampleData\data\usa`

> **Note:** The datasets and map-service publication instructions in this section are primarily for SOE and SOI samples. CDF samples connect to their respective data providers, and service interceptor samples may use different target services. Consult the README for each sample.

## Publish sample services

### 1. USA map service

The USA map service is used by several SOE and SOI samples.

1. Open a map in ArcGIS Pro.
2. Add the `Cities`, `Highways`, `Counties`, and `State` feature classes from `EnterpriseSDKSampleData/data/usa/usa.gdb`.
3. Share the map as a web layer, and select **Map Image** as the layer type.
4. Publish the map image layer to a federated ArcGIS Server to create the map service.

### 2. Vegetation map service

1. Open a map in ArcGIS Pro.
2. Add the `veg` feature class from `EnterpriseSDKSampleData/data/Yellowstone/veg.gdb`.
3. Share the map as a web layer, and select **Map Image** as the layer type.
4. Publish the map image layer to create the map service.

### 3. Naperville service

This service is used to test the [Utility Network Tracing REST SOE](extensions/dot%20net/SOE/UNTracingRESTSOE), available since ArcGIS Enterprise SDK 10.8.1.

Follow the [Electric Utility Network Foundation](https://solutions.arcgis.com/electric/help/electric-utility-network-foundation/) instructions to load the Naperville demonstration data into an enterprise geodatabase and publish the `NapervilleElectricFoundation` service to ArcGIS Enterprise.

### 4. Annotation map service

This service is used to test the [.NET Edit Annotation REST SOE](extensions/dot%20net/SOE/EditAnnotationRESTSOE/), available since ArcGIS Enterprise SDK 12.0.

1. Open a map in ArcGIS Pro.
2. Add the `citiesAnno` annotation feature class from the [AnnotationData geodatabase](extensions/dot%20net/SOE/EditAnnotationRESTSOE/AnnotationData/AnnotationData.gdb/).
3. Use an enterprise geodatabase registered with ArcGIS Enterprise as the map service's data source.
4. Publish an editable map service, following the sample-specific requirements.

## Samples

### .NET extensions

#### .NET SOE samples

- [Simple REST SOE](extensions/dot%20net/SOE/SimpleRESTSOE)
- [Simple REST SOE With Capabilities](extensions/dot%20net/SOE/SimpleRESTSOEWithCapabilities)
- [Simple REST SOE With Properties](extensions/dot%20net/SOE/SimpleRESTSOEWithProperties)
- [Spatial Query REST SOE](extensions/dot%20net/SOE/SpatialQueryRESTSOE)
- [Find Nearby Features REST SOE](extensions/dot%20net/SOE/FindNearFeaturesRESTSOE)
- [Edit Features REST SOE](extensions/dot%20net/SOE/EditFeaturesRESTSOE)
- [Download File REST SOE](extensions/dot%20net/SOE/DownloadFileRESTSOE)
- [Simple SOAP SOE](extensions/dot%20net/SOE/SimpleSOAPSOE)
- [Find Nearby Features SOAP SOE](extensions/dot%20net/SOE/FindNearFeaturesSOAPSOE)
- [Utility Network Tracing REST SOE](extensions/dot%20net/SOE/UNTracingRESTSOE)
- [Query Network Dataset REST SOE](extensions/dot%20net/SOE/QueryNetworkDatasetRESTSOE)
- [Topology REST SOE](extensions/dot%20net/SOE/TopologyRESTSOE)
- [Edit Annotation REST SOE](extensions/dot%20net/SOE/EditAnnotationRESTSOE/)

#### .NET SOI samples

- [Simple Logger SOI](extensions/dot%20net/SOI/NetSimpleLoggerSOI)
- [Apply Watermark SOI](extensions/dot%20net/SOI/NetApplyWatermarkSOI)
- [Layer Access SOI](extensions/dot%20net/SOI/NetLayerAccessSOI)
- [Operation Access SOI](extensions/dot%20net/SOI/NetOperationAccessSOI)
- [Spatial Restriction SOI](extensions/dot%20net/SOI/NetSpatialRestrictionSOI)
- [Utility Network Edit Areas Collaboration REST SOI](extensions/dot%20net/SOI/NetUNEditAreasSOI)

#### .NET SOAP client samples

- [Overview of building a .NET SOAP client application](extensions/dot%20net/.NET%20SOAP%20Client)

### Java extensions

#### Java SOE samples

- [Simple REST SOE](extensions/java/soe/simplerestsoe)
- [Simple REST SOE With Capabilities](extensions/java/soe/simplerestsoewithcapabilities)
- [Simple REST SOE With Properties](extensions/java/soe/simplerestsoewithproperties)
- [Find Nearby Features REST SOE](extensions/java/soe/findnearbyfeaturesrestsoe)
- [Download File REST SOE](extensions/java/soe/downloadfilerestsoe)
- [Simple SOAP SOE](extensions/java/soe/simplesoapsoe)
- [Find Nearby Features SOAP SOE](extensions/java/soe/findnearbyfeaturessoapsoe)
- [Utility Network Tracing REST SOE](extensions/java/soe/untracingrestsoe)

#### Java SOI samples

- [Simple SOI](extensions/java/soi/SimpleSOI)
- [Apply Watermark SOI](extensions/java/soi/ApplyWatermarkSOI)
- [Layer Access SOI](extensions/java/soi/LayerAccessSOI)
- [Operation Access SOI](extensions/java/soi/OperationAccessSOI)
- [Spatial Restriction SOI](extensions/java/soi/SpatialRestrictionSOI)

#### Java SOAP client samples

- [Overview of building a Java SOAP client application](extensions/java/Java%20SOAP%20Client)


### For Custom Data Feeds

Custom Data Feeds are JavaScript-based providers that enable ArcGIS Enterprise to work with external data sources. Each sample has its own setup instructions and may require access credentials or a running external data source.

* [csv full fetch read only points](custom-data-feeds/csv-full-fetch-read-only-points)
* [duckdb pass through read only points](custom-data-feeds/duckdb-pt-read-only-points)
* [google sheets editing points](custom-data-feeds/google-sheets-editing-points)
* [mongodb editing polygons](custom-data-feeds/mongodb-editing-polygons)
* [mongodb pt read only points](custom-data-feeds/mongodb-pt-read-only-points)
* [nrel hybrid read only points](custom-data-feeds/nrel-hybrid-read-only-points)
* [socrata full fetch read only points](custom-data-feeds/socrata-full-fetch-read-only-points)
* [yelp full fetch read only points](custom-data-feeds/yelp-full-fetch-read-only-points)

### Service Interceptors

Service Interceptors are Java-based components that inspect and modify requests and responses for REST-based ArcGIS Server services. Unlike SOIs, they are not limited to map and image services.

The documented sample scenarios include:

- **Audit Log Interceptor:** Log details about requests to REST-based services.
- **Chaining Interceptors:** Demonstrate the processing order of multiple interceptors.
- **Incident Management:** Enrich hosted feature service responses.
- **Access Control Interceptor:** Filter hosted feature service results according to the requesting user.

See the [Service Interceptor sample directory](service-interceptors/) for sample source code and individual setup instructions. For descriptions, see the [Overview of Service Interceptor Samples](https://developers.arcgis.com/enterprise-sdk/guide/service-interceptors/overview-of-service-interceptor-samples/).

> **Note:** Service Interceptors are separate from Java SOIs. Build them using the Service Interceptor APIs and packaging workflow for your installed SDK version.

## Additional resources

- [ArcGIS Enterprise SDK documentation](https://developers.arcgis.com/enterprise-sdk/)

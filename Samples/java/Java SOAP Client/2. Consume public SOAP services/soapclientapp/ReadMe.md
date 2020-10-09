# SOAP client application

This application contains the following modules:

- [SoapStubGenerator](SoapStubGenerator)
- [FeatureServiceClient](FeatureServiceClient)
- [MapServiceClient](MapServiceClient)

The [SoapStubGenerator](SoapStubGenerator) provides a sample to generate both map service and feature service proxy classes into `com.esri.soap` package. You can run `mvn clean install` at this module to generate the proxy classes, which can be found in the [`target\generated-sources`](SoapStubGenerator/target) folder.

After the proxy classes are generated, you can then run [FeatureServiceClient](FeatureServiceClient) or [MapServiceClient](MapServiceClient). These two modules uses the following two services to demonstrate the workflow:

https://sampleserver6.arcgisonline.com/arcgis/rest/services/USA/MapServer
https://sampleserver6.arcgisonline.com/arcgis/rest/services/Recreation/FeatureServer

> Note: To make the service easier to view here, their REST URLs are provided above. But in these sample applications, their SOAP URLs are used all the time and no REST URL should be used.

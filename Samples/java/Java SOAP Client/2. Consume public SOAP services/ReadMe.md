# Consume public SOAP services

This section includes two sample projects:
 
- [SOAP client application](soapclientapp)
- [SOAP SOE client application](soapsoeclientapp)

The [SOAP client application](soeclientapp) demonstrates generating SOAP proxy classes for map services and feature services, and also consuming a sample feature service [Query](http://resources.arcgis.com/en/help/soap/latest/#/Query/01vp00000047000000/), map service [ExportMapImage](http://resources.arcgis.com/en/help/soap/latest/#/ExportMapImage/01vp0000005q000000/) and other SOAP functions.

The [SOAP SOE client application](soapsoeclientapp) demonstrates generating the SOAP proxy classes based on a SOAP SOE built with [SOAP SOE archetype](https://developers.arcgis.com/enterprise-sdk/guide/java/build-soap-soes-using-intellij/), and building a client application for the SOAP SOE using the generated proxy classes.

If you are using the SOAP SOE approach, it's recommended that you review the [SOAP client application ReadMe](soapclientapp/ReadMe.md) as well, because it introduces some basic concepts for consuming ArcGIS SOAP services, which can help you understand how to consume a SOAP SOE in a Java application better.

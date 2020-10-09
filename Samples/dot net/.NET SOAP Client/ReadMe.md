## Overview

This section illustrates how to consume a SOAP service in a .NET application. In detail, it mainly discusses three workflows: consuming a SOAP service in .NET, consuming a secured SOAP service in .NET, and generating the source code of the SOAP proxy classes. For the SOAP client samples in Java, you can visit [Java SOAP Client](../../java/Java%20SOAP%20Client).

These three workflows can be applied to both SOAP services of built-in ArcGIS service types and SOAP services provided by SOEs. Correspondingly, the following samples are provided to demonstrate each case:

1. [Consume SOAP services in a .NET application](1.%20Consume%20SOAP%20services%20and%20SOAP%20SOE)
    * [Map service ExportMapImage SOAP request sample](1.%20Consume%20SOAP%20services%20and%20SOAP%20SOE/SOAPExportMapImage)
    * [Feature service Query SOAP request sample](1.%20Consume%20SOAP%20services%20and%20SOAP%20SOE/SOAPFeatureServiceQuery)
    * [SOAP SOE .NET client sample](1.%20Consume%20SOAP%20services%20and%20SOAP%20SOE/SimpleSOAPSOEClient)
2. [Generate SOAP proxy libraries](2.%20Generate%20SOAP%20proxy%20libraries)
    * Generate SOAP proxy classes using Visual Studio WSDL tool
    * Consume the SOAP service via the SOAP proxy classes
3. [Consume secured SOAP services in a .NET application](3.%20Consume%20secured%20SOAP%20services)

Note that it's not necessary to explicitly generate the SOAP proxy libraries to consume a SOAP service, as Visual Studio can automatically create the SOAP proxy classes by referencing the SOAP service's WSDL as a web service, which is described in the first workflow, [consuming a SOAP service in a .NET application](1.%20Consume%20SOAP%20services%20and%20SOAP%20SOE). 

Generating your own SOAP proxy libraries is an alternative if your current workflow relies on the provided [.NET proxy libraries](http://resources.arcgis.com/en/help/soap/latest/#/NET/01vp00000090000000/). Instead of using the pre-built [.NET proxy libraries](http://resources.arcgis.com/en/help/soap/latest/#/NET/01vp00000090000000/), you can build your own SOAP proxy libraries and generate the source code of the proxy classes on demand. For example, SOAP proxy libraries are useful when you need to consume a secured SOAP service. In the [secured map service SOAP request sample](3.%20Consume%20secured%20SOAP%20services), you will learn about different ways to consume a secured SOAP service.

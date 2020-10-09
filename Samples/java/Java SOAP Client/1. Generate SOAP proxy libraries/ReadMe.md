# Generate .NET SOAP proxy class

To build a Java SOAP client application, you must first obtain the SOAP proxy libraries. Prior to 10.8, the SOAP proxy libraries are provided with [SOAP SDK](http://resources.arcgis.com/en/help/soap/latest/#/Java/01vp00000044000000/), which includes the pre-built Esri AgsJWS libraries, `arcgis_agsws_stubs.jar` and `arcgis_ws_runtime.jar`. However, starting with 10.8, these pre-built SOAP stubs are deprecated and developers must build their own SOAP proxy libraries (see [Deprecation features plan for ArcGIS 10.8.1](https://support.esri.com/en/technical-article/000017062)):

> The ArcGIS Enterprise 10.7.x release series will be the last to include pre-built Java SOAP stubs with the ArcGIS Server SOAP SDK. While SOAP interfaces and the SOAP SDK will continue to be supported, developers are highly encouraged to use the newer REST APIs or higher-level APIs such as the ArcGIS API for JavaScript or the ArcGIS Runtime SDKs. Developers that continue to use SOAP interfaces, and need SOAP stubs, should built and maintain stubs using the programming language and environment of their choice.

This indicates that if you want to develop a Java SOAP client application for a 10.8 or later version of SOAP services, including built-in service types and SOAP SOE, you must generate your own SOAP proxy libraries. However, the SOAP interfaces are still supported and you can still refer to [SOAP SDK](http://resources.arcgis.com/en/help/soap/latest/#/Overview/01vp0000008q000000/) for API reference. 

There are many ways to generate Java SOAP proxy libraries from the SOAP WSDL. This topic illustrates how to generate your own SOAP proxy libraries using [Apache CXF WSDL2Java tool](http://cxf.apache.org/docs/wsdl-to-java.html) and the [jaxws:wsimport plugin](https://www.mojohaus.org/jaxws-maven-plugin/wsimport-mojo.html). These approaches can be applied to both ArcGIS SOAP services of built-in service types and services provided by SOAP SOEs. After you have the SOAP proxy libraries generated, you can import them to your client application and interact with your SOAP services via the proxy classes, and you can refer to [Reference SOAP services using proxy classes](reference-soap-service-class.md) to view the code usage of the generated SOAP proxy class. For the complete sample project of generating proxy classes and building Java client applications, you can refer to [consume public SOAP services](../2.%20Consume%20public%20SOAP%20services) and [consume secured SOAP services](../3.%20Consume%20secured%20SOAP%20services).

Continue this topic:

- [Use the Apache CXF **wsdl2java** tool](1-use-the-apache-cxf-wsdl2java-tool.md)
- [Use the **jaxws:wsimport** plugin](2-use-the-wsimport-plugin.md)
- [Reference SOAP services using proxy classes](3-reference-soap-service.md)

## Overview of building Java SOAP client applications

This section illustrates how to consume a SOAP service in a Java console application. In detail, it mainly discusses three workflows: generating SOAP proxy classes, consuming a SOAP service using the SOAP proxy class, and consuming a secured SOAP service in Java. For the SOAP client samples in .NET, you can visit [.NET SOAP Client](../../dot%20net/.NET%20SOAP%20Client).

These three workflows can be applied to both SOAP services of built-in ArcGIS service types and SOAP services provided by SOEs. Correspondingly, the following samples are provided to demonstrate each topic:

1. [Generate SOAP proxy libraries](1.%20Generate%20SOAP%20proxy%20libraries)
    * Use the [Apache CXF **WSDL2Java**](http://cxf.apache.org/docs/wsdl-to-java.html)  tool
    * Use the [**jaxws:wsimport**](https://www.mojohaus.org/jaxws-maven-plugin/wsimport-mojo.html) plugin
2. [Consume public SOAP services]()
    * [Map service and feature service SOAP client application](2.%20Consume%20public%20SOAP%20services/soapclientapp)
    * [Simple SOAP SOE client application]()
3. [Consume secured SOAP services]()


Note that since the [pre-built ArcGIS SOAP Java stubs](http://resources.arcgis.com/en/help/soap/latest/#/Java/01vp00000044000000/) are deprecated at 10.8.x (see [Deprecation plans for ArcGIS 10.8.1](https://support.esri.com/en/technical-article/000017062)), you should build your own SOAP stubs or proxy libraries instead of using the pre-built [Esri AgsJWS libraries](http://resources.arcgis.com/en/help/soap/latest/#/Java/01vp00000044000000/). The [ArcGIS SOAP API](http://resources.arcgis.com/en/help/soap/latest/#/Overview/01vp0000009n000000/) and SOAP interfaces continue to be supported, and you can refer to the SOAP SDK documentation.

To write a SOAP Java client application that consumes an Esri SOAP service, you must first build the SOAP proxy libraries, and then use the proxy libraries to consume the public or secured SOAP service in a Java client application. The [SOAP stubs cxf generator]() provides an example of using [Apache CXF](https://cxf.apache.org/) to generate the SOAP proxy libraries. You can also choose your preferred Java tools or approaches to generate the SOAP proxy libraries. Once you have the proxy libraries ready, you can import them as your project references and call the SOAP methods in the Java client application.

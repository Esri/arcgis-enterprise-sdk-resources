# SOAP SOE client application

This application contains the following modules (in the order of project build and dependency):

- [SimpleSoapSoe](SimpleSoapSoe)
- [SoapSoeStubGenerator](SoapSoeStubGenerator)
- [SoapSoeClient](SoapSoeClient)


The [SimpleSoapSoe](SimpleSoapSoe) is a 10.8.1 simple SOAP SOE created from the [SOAP SOE archetype](https://developers.arcgis.com/enterprise-sdk/guide/java/build-soap-soes-using-intellij/). It should be built first to generate the `.soe` file and/or the [`.wsdl` file](SimpleSoapSoe/target/MySoapSoe.wsdl) in its [`target`](SimpleSoapSoe/target) folder.

Once the SOAP SOE is built, you can define the WSDL in the [SoapSoeStubGenerator](SoapSoeStubGenerator)'s [`pom.xml`](SoapSoeStubGenerator/pom.xml) file, and build the [SoapSoeStubGenerator](SoapSoeStubGenerator) module to generate the proxy libraries. In [`pom.xml`](SoapSoeStubGenerator/pom.xml), you can either reference the generated `.wsdl` file from the SOAP SOE's target folder, or you can deploy the SOAP SOE to your ArcGIS Server and obtain the service WSDL URL (you should share the service public in this case). This example uses the former workflow of referencing the [`.wsdl` file](SimpleSoapSoe/target/MySoapSoe.wsdl). But you can also modify the [`pom.xml`](SoapSoeStubGenerator/pom.xml) to use the second approach and rebuild [SoapSoeStubGenerator](SoapSoeStubGenerator) via `mvn clean install`.

Next, you can build and run [SoapSoeClient](SoapSoeClient), which references the output proxy libraries from [SoapSoeStubGenerator](SoapSoeStubGenerator).

The main interface that is used to access the SOAP SOE service is `IMySoapSoe`, which is defined in <port name="MapServerPort"> tag of the service WSDL (see [MySoapSoe.wsdl](SimpleSoapSoe/target/MySoapSoe.wsdl#Ln79)):
  
``` xml
  <service name="MySoapSoeService">
    <port name="IMySoapSoe" binding="soe:IMySoapSoeBinding">
      <soap:address location="#URL#"/>
    </port>
  </service>
```

If you generated the SOAP proxy classes using the service WSDL URL, you can instantiate the proxy class using `MySoapSoeService`, which is defined in the `<service name="">` tag. Note that in this scenario, the `<soap:address>` has a URL `location`, so you can access the main `IMySoapSoe` interface as the following code:

``` java
   IMySoapSoe mySoapSoe = new MySoapSoeService().getIMySoapSoe();
```

If you generated the SOAP proxy classes using the WSDL file, as this sample, you must construct the `Service` object with service name (defined in the WSDL's `<service name="">` tag), and service SOAP URL (without `.wsdl` appended), and the namespace URL (the SOAP SOE's namespace/package name). Then, you can then get the reference of `IMySoapSoe` as the following code:

``` java
  //This is determined by the SOAP SOE's package name
  String namespaceUrl = "http://www.samples.esri.com";

  //This is the service name.
  //Refer to the service WSDL.
  String serviceName = "MySoapSoeService";

  String serviceEndpoint = "https://dev0009736.esri.com/arcgis/services/SampleWorldCities/MapServer/MySoapSoe";

  QName qname = new QName(namespaceUrl, serviceName);
  URL url = new URL(serviceEndpoint);
  Service service = Service.create(url, qname);

  IMySoapSoe mySoapSoe = service.getPort(IMySoapSoe.class);
  int layerCnt = mySoapSoe.getLayerCountByType("feature");
  System.out.println("Layer count is: " + layerCnt);
```

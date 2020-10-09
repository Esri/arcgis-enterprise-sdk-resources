# Soap stub generator sample

This SOAP stub generator sample uses the [Apache CXF WSDL2Java tool](../../../1.%20Generate%20SOAP%20proxy%20libraries/1-use-the-apache-cxf-wsdl2java-tool.md) to generate SOAP proxy libraries. 

This sample contains the generated proxy classes at the [`target/generated-sources`](target) folder. You can directly use them as proxy classes to consume a service, as [FeatureServiceClient](FeatureServiceClient) and [MapServiceClient](MapServiceClient), or you can modify the **WSDL2Java** parameters and generate the proxy libraries using the Maven build (`mvn clean install`).

The following `<configuration>` is used to define how the proxy classes will be generated from the WSDLs.

``` xml
  <configuration>
      <sourceRoot>${project.build.directory}/generated-sources/</sourceRoot>
      <defaultOptions>
          <bindingFiles>
                  <bindingFile>${basedir}/src/main/resources/bindings.xml</bindingFile>
          </bindingFiles>
          <extraargs>
              <extraarg>-p</extraarg>
              <extraarg>com.esri.soap</extraarg>
          </extraargs>
      </defaultOptions>
      <wsdlOptions>
          <wsdlOption>
              <wsdl>https://sampleserver6.arcgisonline.com/arcgis/services/USA/MapServer?wsdl</wsdl>
          </wsdlOption>
          <wsdlOption>
              <wsdl>https://sampleserver6.arcgisonline.com/arcgis/services/Recreation/MapServer/FeatureServer?wsdl</wsdl>
          </wsdlOption>
      </wsdlOptions>
  </configuration>
```

Note that since this sample generates both map service and feature service proxy classes into the same package `com.esri.soap`, a binding file defined in `<bindingFile>` is used. If you only need to generate proxy libraries for a single type of service, then you shouldn't need to use binding files and you can remove the `<bindingFiles>` tags. For a discussion about using binding files, you can refer to [Apache CXF WSDL2Java tool](../../../1.%20Generate%20SOAP%20proxy%20libraries/1-use-the-apache-cxf-wsdl2java-tool.md).
                  

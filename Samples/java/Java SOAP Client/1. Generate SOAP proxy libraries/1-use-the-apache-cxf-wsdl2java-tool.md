# Use the Apache CXF **wsdl2java** tool

[Apache CXF](https://cxf.apache.org/) is an open-source, fully featured Web services framework. It helps you build and develop services using frontend programming APIs, like `JAX-WS` and `JAX-RS`. One of the tools it includes is the **wsdl2java** tool, which can be used to generate Java classes from WSDL. 

You can either [download Apache CXF](https://cxf.apache.org/download.html) to run the **wsdl2java** tool from the command prompt, or you can use **wsdl2java** in Maven plugin (`cxf-codegen-plugin`) to generate the SOAP classes with Maven build. The benefit of using the Maven plugin is that you do not need to download Apache CXF or manually configure the CXF tool running environment. In addition, even though **wsdl2java** in 3.3.x version supports Java 8-11, as multiple Jaxb API modules are removed since Java 8, you may need to manully add these modules to make the **wsdl2java** tool work with JDK 9 or later versions, which is easier in a Maven project than configuring the environment to run the command tool. It's recommended that you use the Maven plugin approach. The following steps demonstrate how to use **wsdl2java** as Maven plugin:

1. Create an empty Maven project first. You don't need any source code in this project.
2. Include the `cxf-codegen-plugin` as the project's plugin in the project's`pom.xml`:

   ``` xml
   <build>
       <plugins>
           <plugin>
               <groupId>org.apache.cxf</groupId>
               <artifactId>cxf-codegen-plugin</artifactId>
               <version>${cxf.version}</version>
               <executions>
                   <execution>
                       <id>generate-sources</id>
                       <phase>generate-sources</phase>
                       <configuration>
                           <sourceRoot>${project.build.directory}/generated-sources/</sourceRoot>
                           <wsdlOptions>
                           </wsdlOptions>
                       </configuration>
                       <goals>
                           <goal>wsdl2java</goal>
                       </goals>
                   </execution>
               </executions>
               <dependencies>
               </dependencies>
           </plugin>
       </plugins>
   </build>
   ```

  3. Specify the `<configuration>` or `<wsdlOptions>` for the WSDL URL and other parameters for **wsdl2java** in the above xml.

     Some commonly-used parameters are:
     
     Parameter | Description
     -- | --
     `wsdlurl`| Either the `.wsdl` file path or the `?wsdl` service url. 
     `-p package-name`| Optional parameter to define the name of the package where the SOAP proxy classes will be generated.
     `defaultOptions`|This defines a group of paramters that are commonly-used for all the `<wsdlOptions>`. See [Maven cxf-codegen-plugin examples](https://cxf.apache.org/docs/maven-cxf-codegen-plugin-wsdl-to-java.html).

     For the full list of supported parameters, see [WSDL to Java doc](http://cxf.apache.org/docs/wsdl-to-java.html).
     
     These parameters can be added either individually in `<configuration>` tag, or `<extraargs>` tag in `<wsdlOption>`. For example, you can define the following `<wsdlOptions>` to generate proxy classes in the `com.esri.soap.mapservice` package based on a map service WSDL url:
     
     ``` xml
     <wsdlOptions>
        <wsdlOption>
            <wsdl>https://sampleserver6.arcgisonline.com/arcgis/services/USA/MapServer?wsdl</wsdl>
            <extraargs>
                <extraarg>-p</extraarg>
                <extraarg>com.esri.soap.mapservice</extraarg>
            </extraargs>
        </wsdlOption>
     </wsdlOptions>
     ```

     Similar, if you want to generate proxy classes based on the WSDL file. You can use the following tag:

     ``` xml
     <wsdlOption>
          <wsdl>C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema\MapServer.wsdl</wsdl>
      </wsdlOption>
     ```
     
     ArcGIS Server provides the WSDL files for all the services which support SOAP endpoint. They are located at server installation. For example, by default, for ArcMap-based service WSDLs, you can find them at `C:\Program Files\ArcGIS\Server\XmlSchema`; for ArcGIS-Pro-based service WSDLs, you can find them at `C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema`.
     
     > Note: If you use this approach to generate proxy classes from the Server WSDL files, you must define the name of the service in the WSDL file within `<service name=#NAME#>` tag, for example `<service name="MapServer">`.
 

     If you want to generate the SOAP proxy classes based on multiple WSDLs from different types of services, then you can define each URL in its own `<wsdlOption>` and include multiple `<wsdlOption>`. 
     
     Note that since there are [shared types/classes](http://resources.arcgis.com/en/help/soap/latest/#/Value_object_availability/01vp0000009m000000/) existing across different types of services, generating proxy classes from mutliple WSDLs can be tricky. 
     
     The simplest solution is to define a unique package name for each WSDL, and in this way, there will be no naming conflicts when the different services have the same SOAP method name. However, the drawback of this solution is that you can not cast the shared classes between different services or packages, and this can cause inconvinence when the same class will be used across different packages. If you don't have classes to share between different packages in your client application and you are ok with using each package's own classes, then you can adopt this approach. 
     
     The other solution is to build all the proxy classes into the same package, which can resolve the shared classes issue. However, if different services contain methods with the same method name, this will cause naming conflicts. For instance, if you build map service and feature service proxy classes into the same package, some SOAP methods will conflict and cause unexpected behaviors. To avoid naming conflicts, you should use custom binding (see the [SoapStubGenerator](../2.%20Consume%20public%20SOAP%20services/soapclientapp/SoapStubGenerator) sample) and you can't use the `autoNameResolution` parameter. Custom binding is an advanced topic and allows lots of customization and configuration. The [SoapStubGenerator](../2.%20Consume%20public%20SOAP%20services/soapclientapp/SoapStubGenerator) sample just demonstrates one potential way to configure custom binding and you can explore more to wirte your own binding. 

  4. Depending on the Java version, you may need to add additional dependencies for the project. For example, since Java 11 removes certain JAXB libraries, you need to include the following dependencies in your Maven project's `pom.xml` file if you are using JDK 11:

     ``` xml
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.2.7</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>2.3.2</version>
        </dependency>
     ```
     
     Normally when you do a `mvn install`, the Maven compiler can tell you if certain dependencies are missing. You can also use it as a way to find out if additional dependencies should be added to your project.

  5. After the plugin is configured in the `pom.xml` file, you can run `mvn clean install` to run the plugin. 

     This will generate the SOAP proxy classes in the `target\generated-sources` folder. If you define a package name, then you should see the package in the `generated-sources` folder. You can then import the generated package into your other Java client applications, and call the SOAP classes there. You can continue the [Reference SOAP services using proxy classes](3-reference-soap-service.md) topic to view some simple code usage of the proxy classes.

     > Note: It's always good to do a clean install as this can fully remove the `target` folder before rebuilding the project, which ensures to generate the SOAP proxy classes that are not affected by the previous build. 


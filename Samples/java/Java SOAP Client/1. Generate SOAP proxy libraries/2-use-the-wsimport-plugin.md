# Use the jaxws:wsimport plugin

[JAX-WS Maven Plugin](https://www.mojohaus.org/jaxws-maven-plugin/) provides the JAX-WS **wsimport** compiler which can parse WSDL and generate Java code needed to access it.

Prior to Java 9, **wsimport** is included with the JDK, so you can obtain the **wsimport** tool from the JDK and run it from the command prompt. As **wsimport** and other JAXB libraries are removed at Java 9 or later versions, it will be easier to run **wsimport** as Maven plugin with the required libraries added as the Maven project dependencies, when JDK 9 or later version is used. This topic demonstrates how to use the **jaxws:wsimport** plugin to generate the SOAP proxy libraries.

1. Create an empty Maven project first. You don't need any source code in this project.
2. Include the `jaxws-maven-plugin` as the project's plugin in the project's`pom.xml`:

   ``` xml
   <plugin>
       <groupId>org.codehaus.mojo</groupId>
       <artifactId>jaxws-maven-plugin</artifactId>
       <version>2.6</version>
       <executions>
           <execution>
               <goals>
                   <goal>wsimport</goal>
               </goals>
               <configuration>
               </configuration>
           </execution>
       </executions>
       <dependencies>
       </dependencies>
   </plugin>
   ```

  3. Specify the `<configuration>` for the WSDL URL and other parameters in the above xml. For the list of supported parameters, see [jaxws:wsimport doc](https://www.mojohaus.org/jaxws-maven-plugin/wsimport-mojo.html).

     Some commonly-used parameters are:
     
     Parameter | Description
     `<wsdlUrls>`| List of WSDL service URLs (the `?wsdl` url). 
     `<wsdlDirectory>`| Directory containing WSDL files.
     `<wsdlFiles>`| List of WSDL files. If not specified, all `.wsdl` files in the `<wsdlDirectory>` will be used.
     `<packageName>`| The name of the package where the SOAP proxy classes will be generated. This defines a group of parameters that are commonly-used for all the `<wsdlOptions>`. See [Maven cxf-codegen-plugin examples](https://cxf.apache.org/docs/maven-cxf-codegen-plugin-wsdl-to-java.html).

     These parameters can be added within `<configuration>` tag. For example, you can define the following `<configuration>` to generate proxy classes in the `com.esri.soap.mapservice` package based on a map service WSDL url:
     
     ``` xml
      <configuration>
          <wsdlUrls>
              <wsdlUrl>https://sampleserver6.arcgisonline.com/arcgis/services/USA/MapServer?wsdl</wsdlUrl>
          </wsdlUrls>
          <packageName>com.esri.soap.mapservice</packageName>
      </configuration>
     ```

     If you want to generate proxy classes based on the WSDL file. You can use the following tag:

     ``` xml
      <configuration>
          <wsdlFiles>
              <wsdlFile>C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema\MapServer.wsdl</wsdlFile>
          </wsdlFiles>
          <packageName>com.esri.soap.mapservice</packageName>
      </configuration>
     ```
     ArcGIS Server provides the WSDL files for all the services which support SOAP endpoint. They are located at server installation by default. For example, for ArcMap-based service WSDLs, you can find them at `C:\Program Files\ArcGIS\Server\XmlSchema`; for ArcGIS-Pro-based service WSDLs, you can find them at `C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema`.
     
     > Note: If you use this approach to generate proxy classes from the Server WSDL files, you must define the name of the service in the WSDL file within `<service name=#NAME#>` tag, for example `<service name="MapServer">`.

     If you want to generate the SOAP proxy classes based on multiple WSDLs from different types of services, you can define each URL as `<wsdlFile>` and add multiple `<wsdlFile>` to the `<wsdlFiles>` tag. 
     
     Note that since there are [shared types/classes](http://resources.arcgis.com/en/help/soap/latest/#/Value_object_availability/01vp0000009m000000/) existing across different types of services, generating proxy classes from multiple WSDLs can be tricky. 
     
     The simplest solution is to define a unique package name for each WSDL, and in this way, there will be no conflicts when the different services have the same SOAP method name. However, the drawback of this solution is that you can not cast the shared classes between different services, and this can cause inconvenience when the same class will be used across different services. 
     
     The other solution is to build all the proxy classes into the same package, which can resolve the shared classes issue. However, if different services contain methods with the same method name, this will cause naming conflicts. For instance, if you build map service and feature service proxy classes into the same package, some SOAP methods will conflict and can cause unexpected behaviors. To avoid this, you must use custom binding (see the [SoapStubGenerator](../2.%20Consume%20public%20SOAP%20services/soapclientapp/SoapStubGenerator) sample). Custom binding is an advanced topic and enables lots of customization and configuration. The [SoapStubGenerator](../2.%20Consume%20public%20SOAP%20services/soapclientapp/SoapStubGenerator) sample just demonstrates one potential way to configure custom binding using the Apache CXF **wsdl2java** tool but similar concept applies to **wsimport** as well, and you can explore more to write your own binding. 

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
     
     Normally when you do a `mvn clean install`, the Maven compiler can tell you if certain dependencies are missing. You can also use it to find out if additional dependencies should be added to your project.

  5. After the plugin is configured in the `pom.xml` file, you can run `mvn clean install` to run the plugin. 

     This will generate the SOAP proxy classes in `target` folder. If you define a package name, then you should see the package in the `target\generated-sources\wsimport` folder. You can then import the generated package into your other Java client applications and call the SOAP classes. You can continue the [Reference SOAP services using proxy classes](3-reference-soap-service.md) topic to view some simple code usage of the proxy classes.

     > Note: It's always good to do a clean install as this can fully remove the `target` folder before rebuilding the project. You want to make sure to generate SOAP proxy classes that are not affected by the previous build. 


# Secured SOAP client app

This section illustrates how to create a Java application that consumes a secured SOAP map service. In this scenario, the WSDL URL of the secured service cannot be accessed without a token. For example, to access the WSDL of the secured service, you need to add the `token` parameter like this:

`https://localhost/arcgis/services/SampleWorldCities/MapServer?token=C5rJMY1Levmq1LQfBfOQGqTL6issJEa2qYl01g9h9Z8.&wsdl`

In other words, you should programmatically generate the token first, and then consume the SOAP service by setting the service URL with the token parameter appended. This sample takes a secured map service as an example.

## Sample service

ArcGIS Server or ArcGIS Enterprise login: `user1`/`user1`

Secured map service: https://localhost/arcgis/services/SampleWorldCities/MapServer?wsdl

> Note: The above service is a secured map service and you won't be able to access this link directly in a browser because a valid token is required. You can configure the service and user on your own ArcGIS Server or ArcGIS Enterprise.

## Instructions

To programmatically consume a secured SOAP map service, you should follow these steps. 

1. Build the proxy libraries for the catalog service and the map service.

   This is done in the [SoapMapServiceStubGen](SoapMapServiceStubGen) module. In this example, the `ServiceCatalog.wsdl` and `MapServer.wsdl` files are used to generate the proxy libraries, which is defined in the project's [pom.xml](SoapMapServiceStubGen/pom.xml#41):
   
   ``` xml
      <configuration>
          <sourceRoot>${project.build.directory}/generated-sources/</sourceRoot>
          <wsdlOptions>
              <wsdlOption>
                  <wsdl>C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema\MapServer.wsdl</wsdl>
                  <extraargs>
                      <extraarg>-p</extraarg>
                      <extraarg>com.esri.soap.mapservice</extraarg>
                  </extraargs>
              </wsdlOption>
              <wsdlOption>
                  <wsdl>C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema\ServiceCatalog.wsdl</wsdl>
                  <extraargs>
                      <extraarg>-p</extraarg>
                      <extraarg>com.esri.soap.servicecatalog</extraarg>
                  </extraargs>
              </wsdlOption>
          </wsdlOptions>
      </configuration>
   ```
   
   Note that before you run this project, you must define the name of the service in these two WSDL files within their `<service name=#NAME#>` tag. For example, for the `MapServer.wsdl` file, we can use the following tags:
   
   ``` xml
      <service name="MapServer">
         <port name="MapServerPort" binding="e:MapServerBinding">
            <soap:address location="#URL#" />
         </port>
      </service>
   ```
   
   The SOAP proxy libraries are already generated in the [`target/generated-resources`](SoapMapServiceStubGen/target) folder, so you can directly reference it in the client application. Otherwise, you can run `mvn clean install` to build the proxy libraries based on your own server environment or WSDL files.  

2. Once the SOAP proxy libraries are generated in the [SoapMapServiceStubGen](SoapMapServiceStubGen) module, you can reference it as a dependency in the client application, in this case, the [SecuredMapServiceSoapClient](SecuredMapServiceSoapClient) module. 

3. In the SOAP client application, to consume the secured map service, you need to first obtain the token by sending a [Generate Token](https://developers.arcgis.com/rest/services-reference/generate-token.htm) request. 

   Since the token service URL can be different depending on whether the service is from a standalone [ArcGIS Server](https://developers.arcgis.com/rest/services-reference/generate-token.htm) or an [ArcGIS Enterprise](https://developers.arcgis.com/rest/users-groups-and-items/generate-token.htm), you can find the URL from Catalog Service's [GetTokenServiceURL](http://resources.arcgis.com/en/help/soap/latest/#/GetTokenServiceURL/01vp0000008p000000/):
   
   ``` java
      String serviceCatalogURL = https://localhost/arcgis/services
      ServiceCatalog catalog = new ServiceCatalog();
      ServiceCatalogPort myCatalog = catalog.getServiceCatalogPort();
      BindingProvider bp = (BindingProvider)myCatalog;
      bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceCatalogURL);


      String tokenServiceUrl = null;
      if (myCatalog.requiresTokens())
      {
         tokenServiceUrl = myCatalog.getTokenServiceURL();
      }
   ```
   
   The `ServiceCatalog` class is extracted from the SOAP WSDL's service name, which is defined like this in the service WSDL:
   
   ``` xml
      <service name="ServiceCatalog">
         <port name="ServiceCatalogPort" binding="e:ServiceCatalogBinding">
            <soap:address location="#URL#" />
         </port>
      </service>
   ```
   The `ServiceCatalogPort` is the main interface to access the service catalog, which is also defined in its WSDL.
  
   If you already know the token service URL that will be used, you can skip this part and directly define the token service URL in your code.
   
   Once the token service URL is obtained, you can send Generate Token REST request with username and password to the token service URL. This is a REST request, and you can use Java code to programmatically send the request and get the returned token string. There is no SOAP proxy classes involved in this part. 

4. Access the secured SOAP service's proxy class with token

   To access the secured SOAP service using the SOAP proxy class, you need to prepare the URL of the secured map service with the token string appended:
   ``` java
      String token = getToken(serviceCatalogURL, username, password);
      String serviceURLwithToken = securedServiceURL + "?token=" + token;
      MapServer mapservice = new MapServer();
      MapServerPort mapserverport = mapservice.getMapServerPort();
      BindingProvider bp = (BindingProvider)mapserverport;
      bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURLwithToken);
   ```
  
   Now, you can send SOAP request to the secured map service just as how you have tried with a public service before:

   ``` c#
      var defaultMapName = mapserverport.GetDefaultMapName();
      Console.WriteLine("Map name: " + defaultMapName + "\n");
   ```

## More tips

If you want to generate the proxy libraries using the service URL, you can define the `<wsdl>` tags like this:

``` xml
   <configuration>
         <sourceRoot>${project.build.directory}/generated-sources/</sourceRoot>
         <wsdlOptions>
            <wsdlOption>
               <wsdl>https://sampleserver6.arcgisonline.com/arcgis/services/USA/MapServer?wsdl</wsdl>
               <extraargs>
                     <extraarg>-p</extraarg>
                     <extraarg>com.esri.soap.mapservice</extraarg>
               </extraargs>
            </wsdlOption>
            <wsdlOption>
               <wsdl>https://sampleserver6.arcgisonline.com/arcgis/services?wsdl</wsdl>
               <extraargs>
                     <extraarg>-p</extraarg>
                     <extraarg>com.esri.soap.servicecatalog</extraarg>
               </extraargs>
            </wsdlOption>
         </wsdlOptions>
   </configuration>
```

Note that you must use a public service URL to generate the proxy libraries. Once the proxy libraries are generated, you can use it to consume the secured service with the same schema. The catalog service `https://<serverdomain>/<webadaptorname>/services` is always public.

This will also affect how the service proxy class is accessed. Using proxy libraries generated from the service URL allows you to access the service object directly. For example, to access the catalog service object:

``` java
   Catalog catalog = new Catalog();
   ServiceCatalogPort myCatalog = catalog.getServiceCatalogPort();
```

To access the map service object:

``` java
   //"USAMapServer" is from the service name defined in the service WSDL: "<service name="USA_MapServer">"
   USAMapServer mapservice = new USAMapServer(); 
   MapServerPort mapserverport = mapservice.getMapServerPort();
   BindingProvider bp = (BindingProvider)mapserverport;
   bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
```

Remember to assign the secured service URL to `MapServerPort` via `BindingProvider` as the above code, otherwise, the `MapServerPort` still refers to the public service (`USAMapServer`), which is the one used to generate proxy libraries defined in `pom.xml`.
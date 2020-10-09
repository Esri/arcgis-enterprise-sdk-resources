# Generate .NET SOAP proxy class

The .NET SOAP proxy class can be used to consume ArcGIS SOAP services from a .NET client application. The SOAP proxy class workflow is more flexible compared to directly consuming the designated service. Using the SOAP proxy class allows you to dynamically connect to different services and secured SOAP services.

To consume built-in ArcGIS SOAP services using the proxy class workflow, you can download the SOAP proxy library provided with the SOAP SDK ([SOAP SDK - Download .NET SOAP proxy library](http://resources.arcgis.com/en/help/soap/latest/#/NET/01vp00000090000000/)). You can also generate the SOAP proxy class for one or more types of services based on your needs. 

To consume SOAP SOEs using the proxy class workflow, you must generate the SOAP SOE's proxy class on your own, because the classes or methods implemented in the SOAP SOE are not defined in the Esri-provided SOAP proxy libraries.

Visual Studio provides the [WSDL tool](https://docs.microsoft.com/en-us/sql/reporting-services/report-server-web-service/net-framework/creating-the-web-service-proxy?view=sql-server-ver15) (wsdl.exe) which generates the .NET SOAP proxy class on demand. The WSDL tool can be executed from Visual Studio's **Developer Command Prompt**. You can type **Developer Command Prompt for VS** in the Windows search bar to find it.

Some commonly-used parameters for the [WSDL tool](https://docs.microsoft.com/en-us/sql/reporting-services/report-server-web-service/net-framework/creating-the-web-service-proxy?view=sql-server-ver15) are listed as below:

Parameter  | Description
-- | --
`<url/path>` | WSDL url or the WSDL file path
`/language:<language>`| The language for the generated proxy class, such as 'CS', 'VB', etc.
`/sharetypes`|  Turns on type sharing feature. This option should be used when different SOAP service WSDLs are included to generate the proxy class.
`/namespace:<namespace>`| The namespace for the generated proxy or template. The default namespace is the global namespace.
`/out:<fileName/directoryPath>` | The filename or directory path for the generated proxy code. The default filename is derived from the service name.

Typically, the SOAP proxy class can be generated from either the SOAP WSDL url or the WSDL file. 

## Generate proxy class using SOAP service WSDL URL

For example, the following example uses the SOAP service WSDL URL to generate the SOAP proxy class with the default output class `SampleWorldCities_MapServer.cs` for the SampleWorldCities map service:

``` bash
C:\soapdemo>wsdl https://dev0009736.esri.com/arcgis/services/SampleWorldCities/MapServer?wsdl /language:CS
```

You can then add the `SampleWorldCities_MapServer.cs` class to your .NET application and consume the service. If you want to consume a different map service, you can define its URL in `SampleWorldCities_MapServer.Url`:

``` c#
    SampleWorldCities_MapServer mapServer = new SampleWorldCities_MapServer();
    mapServer.Url = "https://dev0009736.esri.com/arcgis/services/USA/MapServer";
    string mapName = mapServer.GetDefaultMapName();
```

> Note: When you add a proxy class to your project manually, you need to add a reference to System.Web.Services.dll.

## Generate proxy class using SOAP WSDL file

Alternatively, you can also generate the SOAP proxy class from the WSDL file. 

For ArcGIS-Pro-based services, you can first find the service WSDL file at Server installation (default location `C:\Program Files\ArcGIS\Server\framework\runtime\ArcGIS\Resources\XmlSchema`). Since these WSDL files don't have the default service name and URL, you need to set them in the WSDL file. Take map service as an example, you can copy the `MapServer.wsdl` file to your directory and edit the following part in notepad:

``` xml
  <service name="#NAME#">
    <port name="MapServerPort" binding="e:MapServerBinding">
      <soap:address location="#URL#" />
    </port>
  </service>
```

Replace `#NAME#` in the above XML with `MapServer` (`<service name="MapServer">`) or your prefered name of the default service. Replace `#URL` with your default SOAP service URL (such as `https://serverdomain/webadaptorname/services/SampleWorldCities/MapServer`). This URL can be set later in the client application.

You can run the following command to generate the map service proxy class now:

``` bash
C:\soapdemo>wsdl "file:\\C:\soapdemo\MapServer.wsdl" /language:CS
```

This will generate a `MapServer.cs` class file. You can either directly consume the service from this class or export the class to a DLL as a reference in your client application. You need to provide the service URL like the following code:

``` c#
    MapServer mapServer = new MapServer();
    mapServer.Url = "https://dev0009736.esri.com/arcgis/services/SampleWorldCities/MapServer";
    string mapName = mapServer.GetDefaultMapName();
```

## Generate proxy class with multiple service types

If you want to generate SOAP proxy class from multiple types of services which share similar input and output type requirements, you should include the `/sharetypes` parameter to turns on type sharing feature. For instance, if you want to build a SOAP client that consumes both a map service and a feature service, then you should use `/sharetypes` and generate the SOAP classes within the same namespace, as the following steps:
1. Copy the `MapServer.wsdl` and `FeatureServer.wsdl` file to your designated directory.
2. Update `#NAME#` and `#URL#` to be your default service name and service URL in both WSDL files.
3. Run the following command:

``` bash
C:\soapdemo>wsdl /sharetypes /language:CS /namespace:ESRI.ArcGIS.SOAP /out:.\ESRI.ArcGIS.SOAP.cs /protocol:SOAP "file:\\C:\soapdemo\MapServer.wsdl" "file:\\C:\soapdemo\FeatureServer.wsdl"
C:\soapdemo>csc /target:library /out:ESRI.ArcGIS.SOAP.dll ESRI.ArcGIS.SOAP.cs    
```

The first command generates the `ESRI.ArcGIS.SOAP.cs` proxy class that can be directly added and used in a .NET application. The second command exports the `ESRI.ArcGIS.SOAP.cs` proxy class to `ESRI.ArcGIS.SOAP.dll` that can be referenced in the application. You can choose either the `.cs`  or the `.dll` form of the SOAP proxy to use in your project.

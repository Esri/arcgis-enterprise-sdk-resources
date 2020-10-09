# Reference SOAP services using proxy classes

After the SOAP proxy libraries are generated, you can import them to a Java client application and call the SOAP methods from there. This section provides some explanations on how these proxy classes are constructed with several samples on different code usages.

For example, using the following parameters in [Apache CXF wsdl2java plugin](1-use-the-apache-cxf-wsdl2java-tool.md) will generate the map service proxy classes in the `com.esri.soap.mapservice` package:

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
        </wsdlOptions>
    </configuration>
```

You can then add the `com.esri.soap.mapservice` package to your Java application and consume the service. 

If you want to consume the same map service that is used in the above `wsdl` parameter, i.e., `https://sampleserver6.arcgisonline.com/arcgis/services/USA/MapServer?wsdl` in this example, you can use the `USAMapServer` class, which is named after `serviceName` and `serviceType`. 

If you want to consume a different service from the one used to generate the proxy classes but with the same SOAP schema (same service type, version, and provider), you should use the `MapServerPort` interface. Both of these two classes are generated based on the service WSDL:

``` xml
<service name="USA_MapServer">
    <port name="MapServerPort" binding="e:MapServerBinding">
        <soap:address location="https://sampleserver6.arcgisonline.com:443/arcgis/services/USA/MapServer"/>
    </port>
</service>
```

The main interface that is used to access the SOAP service is `MapServerPort`, which is defined in `<port name="MapServerPort">` tag of the service WSDL. To consume this USA map service, the `USAMapServer` class, defined from the `<service name="USA_MapServer">` tag in the above WSDL, can be used to access the `MapServerPort` interface and then call its SOAP methods, like this:

``` java
        USAMapServer usaservice = new USAMapServer();
        MapServerPort mapServerPort = usaservice.getMapServerPort();
        String mapName = mapServerPort.getDefaultMapName();
        MapServerInfo serverInfo = mapServerPort.getServerInfo(mapName);
        System.out.println("Map name: " + mapName);
        System.out.println("Service description: " +serverInfo.getDescription());
```

To consume other map services with the same schema, you can use the `MapServerPort` interface, like this:

``` java
import com.esri.soap.mapservice.MapServerInfo;
import com.esri.soap.mapservice.MapServerPort;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Console {

    public static void main(String[] args) throws MalformedURLException {
        System.out.println("test");

        String serviceEndpoint = "https://sampleserver6.arcgisonline.com/arcgis/services/Recreation/MapServer";

        //This is the ArcGIS namespace. For Pro-based services, use "http://www.esri.com/schemas/ArcGIS/2.x"
        //Refer to the service WSDL to find out which ArcGIS namespace URL should be used.
        String namespaceUrl = "http://www.esri.com/schemas/ArcGIS/10.7";

        //This is the service name.
        // For dedicated services or ArcMap-based service, use "ServiceName_ServiceType", e.g.,"Recreation_MapServer";
        // For services using shared instances, the serviceName should be "DynamicMappingHost_MapServer"
        String serviceName = "Recreation_MapServer";

        QName qname = new QName(namespaceUrl, serviceName);
        URL url = new URL(serviceEndpoint);
        Service service = Service.create(url, qname);
        MapServerPort mapServerPort = service.getPort(MapServerPort.class);
        String mapName = mapServerPort.getDefaultMapName();
        MapServerInfo serverInfo = mapServerPort.getServerInfo(mapName);
        System.out.println("Map name: " + mapName);
        System.out.println("Service description: " +serverInfo.getDescription());
    }
}

```

You must use `javax.xml.ws.Service` to create an instance of `MapServerPort`. To define a `Service` object, you should provide the service URL, service name, and ArcGIS namespace URL. 

The service URL is the service's SOAP endpoint without the appending `?wsdl`. 

The service name is the `$serviceName_$serviceType` string, however, for services using shared instances, the service name is `DynamicMappingHost_$serviceName`. If you are not sure what service name should be used, you can refer to the service's WSDL and find out in the `<service name="">` tag.

The ArcGIS namespace URL are different for different service providers. For ArcMap-based services including SOAP SOE, the ArcGIS namespace URL should be `http://www.esri.com/schemas/ArcGIS/10.x`. For ArcGIS-Pro-based services (both dedicated instance and shared instance) including SOAP SOE, the ArcGIS namespace URL should be `http://www.esri.com/schemas/ArcGIS/2.x`. If you are not sure what ArcGIS namespace URL should be used, you can refer to the service's WSDL and find out in the `<port name="">` tag.

Once the `Service` object is constructed, you can obtain the `MapServerPort` object from `Service.getPort()` method. And then you can call the map service's SOAP methods subsequently via the `MapServerPort` object. To view the complete sample project, you can refer to [SOAP Java client application](../2.%20Consume%20public%20SOAP%20services).

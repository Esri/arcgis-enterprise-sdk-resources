
import com.esri.soap.*;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {

        String serviceEndpoint = "https://sampleserver6.arcgisonline.com/arcgis/services/USA/MapServer";

        //This is the ArcGIS namespace. For Pro-based services, use "http://www.esri.com/schemas/ArcGIS/2.x"
        //Refer to the service WSDL for a reference.
        String namespaceUrl = "http://www.esri.com/schemas/ArcGIS/10.7";

        //This is the service name.
        // For dedicated services or ArcMap-based service, use "$serviceName_$serviceType",
        // e.g.,"Recreation_MapServer";
        // For services using shared instances, the serviceName should be "DynamicMappingHost_$serviceType",
        // e.g., "DynamicMappingHost_MapServer"
        String serviceName = "USA_MapServer";

        QName qname = new QName(namespaceUrl, serviceName);
        URL url = new URL(serviceEndpoint);
        Service service = Service.create(url, qname);

        MapServerPort mapserver = service.getPort(MapServerPort.class);
        String mapName = mapserver.getDefaultMapName();
        MapServerInfo serverInfo = mapserver.getServerInfo(mapName);

        System.out.println("Map name: " + mapName);
        System.out.println("Description: " + serverInfo.getDescription());

        MapServerInfo servInfo = mapserver.getServerInfo(mapName);
        System.out.println(mapName);
        ImageDescription imageDescription = new ImageDescription();
        ImageDisplay mapdisp = new ImageDisplay();
        mapdisp.setImageDPI(96);
        mapdisp.setImageHeight(500);
        mapdisp.setImageWidth(500);
        imageDescription.setImageDisplay(mapdisp);
        ImageType imagetp = new ImageType();
        imagetp.setImageFormat(EsriImageFormat.ESRI_IMAGE_JPG);
        imagetp.setImageReturnType(EsriImageReturnType.ESRI_IMAGE_RETURN_URL);

        imageDescription.setImageType(imagetp);
        MapImage exportImage = mapserver.exportMapImage(servInfo.getDefaultMapDescription(), imageDescription);
        String outImageUrl = exportImage.getImageURL();
        System.out.println("Output URL: " + outImageUrl);
    }
}

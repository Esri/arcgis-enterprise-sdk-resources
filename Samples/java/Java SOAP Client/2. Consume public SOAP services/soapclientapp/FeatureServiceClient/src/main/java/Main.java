
import com.esri.soap.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String serviceEndpoint = "https://sampleserver6.arcgisonline.com/arcgis/services/Recreation/MapServer/FeatureServer";

        //This is the ArcGIS namespace.
        // For Pro-based services, use "http://www.esri.com/schemas/ArcGIS/2.x"
        //Refer to the service WSDL.
        String namespaceUrl = "http://www.esri.com/schemas/ArcGIS/10.7";

        //This is the service name.
        // For dedicated services or ArcMap-based service, use "$serviceName_$serviceType",
        // e.g.,"Recreation_MapServer";
        // For services using shared instances, the serviceName should be "DynamicMappingHost_$serviceType",
        // e.g., "DynamicMappingHost_MapServer"
        //Refer to the service WSDL.
        String serviceName = "Recreation_FeatureServer";

        QName qname = new QName(namespaceUrl, serviceName);
        URL url = new URL(serviceEndpoint);
        Service service = Service.create(url, qname);

        FeatureServerPort featureServer =service.getPort(FeatureServerPort.class);
        QueryFilter qf = new QueryFilter();
        qf.setWhereClause("1=1");

        FeatureServerInfo serverInfo = featureServer.getServerInfo();
        System.out.println("Service description: " + serverInfo.getServiceDescription());

        ServiceDataOptions sdo = new ServiceDataOptions();
        sdo.setFormat("NATIVE");
        sdo.setTransportType(EsriTransportType.ESRI_TRANSPORT_TYPE_EMBEDDED);
        ServiceData resp = featureServer.query(0, "1=1", qf, sdo, "", 0.0);
        DataObjects r = (DataObjects)resp.getObject();
        System.out.println("Number of features returned: " + r.getDataObjectArray().getDataObject().size());
    }
}

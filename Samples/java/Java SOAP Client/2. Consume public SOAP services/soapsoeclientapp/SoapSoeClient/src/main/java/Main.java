import com.esri.samples.IMySoapSoe;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {

        // * If you generate the SOAP proxy classes using the service WSDL URL,
        //     you can instantiate the proxy class using MySoapSoeService, i.e.,
        //     IMySoapSoe mySoapSoe = new MySoapSoeService().getIMySoapSoe();

        // * If you generate the SOAP proxy classes using the WSDL file
        //   (see SoapSoeStubGenerator/pom.xml),
        //     you must construct the Service object with service name and service url,
        //     and then get a reference of IMySoapSoe as the following code:

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

    }
}

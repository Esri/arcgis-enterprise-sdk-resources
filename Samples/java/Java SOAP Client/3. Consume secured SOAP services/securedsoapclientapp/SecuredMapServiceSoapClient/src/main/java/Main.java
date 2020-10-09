
import com.esri.soap.mapservice.*;
import com.esri.soap.servicecatalog.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.io.*;
import java.net.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        String username = "user1";
        String password = "user1";
        String securedServiceURL = "https://dev0009736.esri.com/arcgis/services/SampleWorldCities/MapServer";
        String serviceCatalogURL = "https://dev0009736.esri.com/arcgis/services";

        String token = getToken(serviceCatalogURL, username, password);
        String serviceURLwithToken = securedServiceURL + "?token=" + token;
        CallExportMapImage(serviceURLwithToken);

    }

    private static String getToken(String serviceCatalogURL, String username, String password) throws IOException {

        ServiceCatalog catalog = new ServiceCatalog();
        ServiceCatalogPort myCatalog = catalog.getServiceCatalogPort();
        BindingProvider bp = (BindingProvider)myCatalog;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceCatalogURL);


        String tokenServiceUrl = null;
        if (myCatalog.requiresTokens())
        {
            tokenServiceUrl = myCatalog.getTokenServiceURL();
        }
        URL url = new URL(tokenServiceUrl);
        HttpURLConnection connect = (HttpURLConnection)url.openConnection();
        connect.setRequestMethod("POST");
        connect.setDoOutput(true);
        DataOutputStream outStream = new DataOutputStream(connect.getOutputStream());
        String parameters = "username=" + username + "&password=" + password +
                "&client=requestip&expiration=100&f=json";
        outStream.write(parameters.getBytes("UTF-8"));
        outStream.flush();
        outStream.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String str;

        StringBuffer content = new StringBuffer("");
        str = in.readLine();
        while (str != null) {
            content.append(str);
            str = in.readLine();
        }
        in.close();
        String result = content.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> tokenJson = objectMapper.readValue(
                result, new TypeReference<Map<String,Object>>(){});
        if (tokenJson.containsKey("token"))
            return (String)tokenJson.get("token");
        else if (tokenJson.containsKey("error"))
            throw new IOException("Generate token error: " + result);
        else
            throw new IOException("No valid token: " + result);
    }

    private static void CallExportMapImage(String serviceURL) throws MalformedURLException {
        MapServer mapservice = new MapServer();
        MapServerPort mapserverport = mapservice.getMapServerPort();
        BindingProvider bp = (BindingProvider)mapserverport;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);

        String mapName = mapserverport.getDefaultMapName();
        MapServerInfo serverInfo = mapserverport.getServerInfo(mapName);

        System.out.println("Map name: " + mapName);
        System.out.println("Description: " + serverInfo.getDescription());

        MapServerInfo servInfo = mapserverport.getServerInfo(mapName);
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
        MapImage exportImage = mapserverport.exportMapImage(servInfo.getDefaultMapDescription(), imageDescription);
        String outImageUrl = exportImage.getImageURL();
        System.out.println("Output URL: " + outImageUrl);
    }

}

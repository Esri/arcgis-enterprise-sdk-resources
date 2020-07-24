package entsdksamples.soe;


import com.esri.arcgis.carto.IMapLayerInfo;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.system.*;

import java.io.IOException;

public class SOEUtil {

    private ILog logger;

    public static int LOG_LEVEL_SEVERE = 1;
    public static int LOG_LEVEL_WARNING = 2;
    public static int LOG_LEVEL_INFO = 3;
    public static int LOG_LEVEL_FINE = 4;
    public static int LOG_LEVEL_DEBUG = 100;


    public SOEUtil()
    {
        this.logger = ServerUtilities.getServerLogger();
    }

    /**
     * This method returns the Utility Network Layer information object.
     */
    public IMapLayerInfo getUNLayerInfo(IMapServer mapService) throws IOException {
        IMapServerInfo mapServerInfo = mapService.getServerInfo(mapService.getDefaultMapName());
        IMapLayerInfos layerInfos = mapServerInfo.getMapLayerInfos();

        for (int i = 0; i < layerInfos.getCount(); i++)
        {
            IMapLayerInfo layerInfo = layerInfos.getElement(i);
            if (layerInfo.getType().equalsIgnoreCase("Utility Network Layer"))
            {
                return layerInfo;
            }
        }
        return null;
    }

    /**
     * This method returns the layer id of the layer matching the specified name
     */
    public int getLayerIdByName(IMapServer mapService, String name) throws IOException {
        IMapServerInfo msInfo = mapService.getServerInfo(mapService.getDefaultMapName());
        IMapLayerInfos layerInfos = msInfo.getMapLayerInfos();

        for (int i = 0; i < layerInfos.getCount(); i++)
        {
            IMapLayerInfo layerInfo = layerInfos.getElement(i);
            if (layerInfo.getName().equalsIgnoreCase(name))
            {
                return layerInfo.getID();
            }
        }

        return -1;
    }

    /**
     * This method returns true if the current extension is of type 'Utility Network Server'
     */
    public boolean isUNExtension() throws IOException {
        boolean isUNService = false;
        String extensionName = (String)this.getServerProperty("ExtensionName");
        if (extensionName != null && extensionName.equalsIgnoreCase("UtilityNetworkServer"))
        {
            isUNService = true;
        }

        return isUNService;
    }

    /**
     * This method returns a Server property value
     */
    public Object getServerProperty(String propertyName) throws IOException {
        Object propertyValue = null;
        EnvironmentManager envMgr = new EnvironmentManager();
        UID envUID = new UID();
        envUID.setValue("{32d4c328-e473-4615-922c-63c108f55e60}");
        IServerEnvironment serverEnvironment = new IServerEnvironment2Proxy(envMgr.getEnvironment(envUID));
        IPropertySet serverProps = serverEnvironment.getProperties();
        propertyValue = serverProps.getProperty(propertyName);
        Cleaner.release(envMgr);

        return propertyValue;
    }
}

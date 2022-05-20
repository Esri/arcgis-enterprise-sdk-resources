package entsdksamples.soe;


import com.esri.arcgis.carto.IMapLayerInfo;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.geodatabase.*;
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
     * This method returns the NetworkSourceID of the UN feature class usage type
     */
    public Integer GetNetworkSourceIdByFeatureClassUsageType(IDEUtilityNetworkProxy deUtilityNetwork, String domainNetworkName, int esriUNFCUTDevice) throws IOException {
        IArray domainNetworks = deUtilityNetwork.getDomainNetworks();

        for (int i = 0; i < domainNetworks.getCount(); i++)
        {
            IDomainNetwork domainNetwork = (IDomainNetwork) domainNetworks.getElement(i);
            if (domainNetwork != null && domainNetwork.getDomainNetworkName().equals(domainNetworkName))
            {
                IArray networkSources = domainNetwork.getSources();
                for (int j = 0; j < networkSources.getCount(); j++)
                {
                    INetworkSource networkSource = (INetworkSource) networkSources.getElement(j);
                    if (networkSource != null)
                    {
                        int networkSourceID = networkSource.getID();

                        IUtilityNetworkSource utilityNetworkSource = (IUtilityNetworkSource)networkSource;
                        if (utilityNetworkSource != null)
                        {
                            if (utilityNetworkSource.getUtilityNetworkFeatureClassUsageType() == esriUNFCUTDevice)
                            {
                                return networkSourceID;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}

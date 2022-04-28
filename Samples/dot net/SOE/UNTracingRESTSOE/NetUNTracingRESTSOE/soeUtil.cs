using ESRI.ArcGIS.Carto;
using ESRI.Server.SOESupport;
using System;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;

namespace NetUNTracingRESTSOE
{
  class SOEUtil
  {
    private ServerLogger logger;

    public SOEUtil()
    {
      logger = new ServerLogger();
    }

    /**
     * This method returns the Utility Network Layer information object.
    */
    public IMapLayerInfo GetUNLayerInfo(IMapServer mapService)
    {
      IMapServerInfo mapServerInfo = mapService.GetServerInfo(mapService.DefaultMapName);
      IMapLayerInfos layerInfos = mapServerInfo.MapLayerInfos;

      for (int i = 0; i < layerInfos.Count; i++)
      {
        IMapLayerInfo layerInfo = layerInfos.Element[i];
        if (layerInfo.Type.Equals("Utility Network Layer", StringComparison.OrdinalIgnoreCase))
        {
          return layerInfo;
        }
      }

      return null;
    }

    /**
    * This method returns the layer id of the layer matching the specified name
    */
    public int GetLayerIdByName(IMapServer mapService, string name)
    {
      IMapServerInfo msInfo = mapService.GetServerInfo(mapService.DefaultMapName);
      IMapLayerInfos layerInfos = msInfo.MapLayerInfos;

      for (int i = 0; i < layerInfos.Count; i++)
      {
        IMapLayerInfo layerInfo = layerInfos.Element[i];
        if (layerInfo.Name.Equals(name, StringComparison.OrdinalIgnoreCase))
        {
          return layerInfo.ID;
        }
      }

      return -1;
    }

    /**
     * This method returns the NetworkSourceID of the UN feature class usage type
     */
    public int? GetNetworkSourceIdByFeatureClassUsageType(IDEUtilityNetwork deUtilityNetwork, string domainNetworkName, esriUtilityNetworkFeatureClassUsageType usageType)
    {
      IArray domainNetworks = deUtilityNetwork.DomainNetworks as IArray;

      for (int i = 0; i < domainNetworks.Count; i++)
      {
        IDomainNetwork domainNetwork = domainNetworks.Element[i] as IDomainNetwork;
        if (domainNetwork != null && domainNetwork.DomainNetworkName == domainNetworkName)
        {
          IArray networkSources = domainNetwork.Sources;
          for (int j = 0; j < networkSources.Count; j++)
          {
            INetworkSource networkSource = networkSources.Element[j] as INetworkSource;
            if (networkSource != null)
            {
              int networkSourceID = networkSource.ID;
              
              IUtilityNetworkSource utilityNetworkSource = (IUtilityNetworkSource)networkSource;
              if (utilityNetworkSource != null)
              {
                if (utilityNetworkSource.UtilityNetworkFeatureClassUsageType == usageType)
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


    /**
    * This method returns true if the current extension is of type 'Utility Network Server'
    */
    public bool IsUNExtension()
    {
      bool isUNService = false;
      String extensionName = (String)this.GetServerProperty("ExtensionName");
      if (extensionName != null && extensionName.Equals("UtilityNetworkServer", StringComparison.OrdinalIgnoreCase))
      {
        isUNService = true;
      }

      return isUNService;
    }

    /**
    * This method returns a Server property value
    */
    public Object GetServerProperty(string propertyName)
    {
      Object propertyValue = null;
      Type envMgrType = Type.GetTypeFromProgID("esriSystem.EnvironmentManager");
      object envMgrObj = Activator.CreateInstance(envMgrType);
      IEnvironmentManager envMgr = envMgrObj as IEnvironmentManager;
      UID envUID = new UIDClass();
      envUID.Value = "{32D4C328-E473-4615-922C-63C108F55E60}";
      IServerEnvironment2 serverEnvironment = (IServerEnvironment2)envMgr.GetEnvironment(envUID);
      IPropertySet serverProps = serverEnvironment.Properties;
      propertyValue = serverProps.GetProperty(propertyName);

      return propertyValue;
    }

    public JSONObject CreateErrorJSON(int code, string message, int extendedCode = 0, string detailsContent = "")
    {
      JSONObject response = new JSONObject();
      JSONObject error = new JSONObject();

      error.Add("code", code);
      error.Add("message", message);

      if (extendedCode != 0)
        error.Add("extendedCode", extendedCode);

      JSONArray details = new JSONArray();
      details.AddString(detailsContent);
      error.AddJSONArray("details", details);

      response.AddJSONObject("error", error);

      return response;
    }

  }
}

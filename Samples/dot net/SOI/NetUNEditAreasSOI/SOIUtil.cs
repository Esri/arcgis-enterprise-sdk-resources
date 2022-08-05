using ESRI.ArcGIS.Carto;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Server;
using ESRI.Server.SOESupport;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NetUNEditAreasSOI
{
  class SOIUtil
  {
    private IServerEnvironment3 _server;
    private ServerLogger _serverLog;

    public SOIUtil()
    {
      _server = GetServerEnvironment();
      _serverLog = new ServerLogger();
    }

    /**
     * This method returns the byte[] response as JSON object
    */
    public JSONObject ParseResponse(byte[] response)
    {
      JSONObject responseJSON = new JSONObject();

      try
      {
        JSONReader responseReader = new JSONReader();
        string responseStr = Encoding.UTF8.GetString(response, 0, response.Length);
        responseReader.ReadFromString(responseStr);
        responseJSON.ParseJSON(responseReader);
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, "SOIUtil.ParseResponse()",
            200, "Error while parsing response: " + e.ToString());
      }

      return responseJSON;
    }

    /**
     * This method returns the input parameters as JSON object
    */
    public JSONObject ParseParameters(string operationInput)
    {
      JSONObject inputJSON = new JSONObject();

      try
      {
        JSONReader inputReader = new JSONReader();
        inputReader.ReadFromString(operationInput);
        inputJSON.ParseJSON(inputReader);
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, "SOIUtil.ParseParameters()",
            200, "Error while parsing parameters: " + e.ToString());
      }

      return inputJSON;
    }

    /**
     * This method returns the map service's utility network layer information object.
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
     * This method returns the utility network dataset.
    */
    public IDataset GetUNDataset(IServerObject serverObject, string versionName)
    {
      IMapServer mapService = (MapServer)serverObject;
      IMapServerDataAccess mapServerDataAccess = (IMapServerDataAccess)serverObject;

      // Get feature class from any layer
      IFeatureClass featureClass = (IFeatureClass)mapServerDataAccess.GetDataSource(mapService.DefaultMapName, 0);

      // Get the container feature dataset
      IFeatureDataset featureDataset = featureClass.FeatureDataset;

      // Open feature dataset in specified version
      if (!string.IsNullOrEmpty(versionName))
      {
        IWorkspace workspace = featureDataset.Workspace;
        IVersionedWorkspace versionedWorkspace = (IVersionedWorkspace)workspace;
        IVersion childVersion = versionedWorkspace.FindVersion(versionName);
        IFeatureWorkspace featureWorkspace = (IFeatureWorkspace)childVersion;
        string featureDatasetName = featureDataset.Name;   

        IDataset utilityNetworkDataset = featureWorkspace.OpenExtensionDataset(esriDatasetType.esriDTUtilityNetwork, featureDatasetName);
        return utilityNetworkDataset;
      }
      return null;
    }


    /**
    * This method returns true if the current extension is of type 'Utility Network Server'
    */
    public bool IsUNExtension()
    {
      bool isUNService = false;
      String extensionName = null;
      try
      {
        extensionName = (String)this.GetServerProperty("ExtensionName");
      }
      catch (Exception)
      {

      }
      finally
      {
        if (extensionName != null && extensionName.Equals("UtilityNetworkServer", StringComparison.OrdinalIgnoreCase))
        {
          isUNService = true;
        }
      }

      return isUNService;
    }

    public string GetServerExtensionName()
    {
      String extensionName = null;

      try
      {
        extensionName = (String)this.GetServerProperty("ExtensionName");
      }
      catch (Exception)
      {

      }

      return extensionName;
    }

    /**
    * This method returns a Server property value
    */
    public Object GetServerProperty(string propertyName)
    {
      Object propertyValue = null;
      IPropertySet serverProps = _server.Properties;
      propertyValue = serverProps.GetProperty(propertyName);

      return propertyValue;
    }

    public IServerEnvironment3 GetServerEnvironment()
    {
      UID uid = new UIDClass();
      uid.Value = "{32D4C328-E473-4615-922C-63C108F55E60}";

      //use activator to cocreate singleton
      Type t = Type.GetTypeFromProgID("esriSystem.EnvironmentManager");
      System.Object obj = Activator.CreateInstance(t);
      IEnvironmentManager environmentManager = obj as IEnvironmentManager;
      return (environmentManager.GetEnvironment(uid) as IServerEnvironment3);
    }
  }
}

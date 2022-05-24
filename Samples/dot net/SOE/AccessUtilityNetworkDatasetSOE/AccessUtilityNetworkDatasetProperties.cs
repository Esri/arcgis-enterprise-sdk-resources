// Copyright 2018 ESRI
// 
// All rights reserved under the copyright laws of the United States
// and applicable international laws, treaties, and conventions.
// 
// You may freely redistribute and use this sample code, with or
// without modification, provided you include the original copyright
// notice and use restrictions.
// 
// See the use restrictions at <your Enterprise SDK install location>/userestrictions.txt.
// 

using ESRI.ArcGIS.Carto;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Server;
using ESRI.Server.SOESupport;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.Json;

//This is REST SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace AccessUtilityNetworkDataset
{
  [ComVisible(true)]
  [Guid("308e671a-156e-46bd-9381-348c20b9f368")]
  [ClassInterface(ClassInterfaceType.None)]
  [ServerObjectExtension("MapServer",
      AllCapabilities = "",
      DefaultCapabilities = "",
      Description = "",
      DisplayName = "AccessUtilityNetworkDataset",
      Properties = "",
      SupportsREST = true,
      SupportsSOAP = false,
      SupportsSharedInstances = false)]
  public class AccessUtilityNetworkDatasetProperties : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
  {
    private string soe_name;

    private IPropertySet configProps;
    private IServerObjectHelper serverObjectHelper;
    private ServerLogger logger;
    private IRESTRequestHandler reqHandler;
    private IMapServer mapServer;
    private IMapServerDataAccess mapServerDataAccess;
    public AccessUtilityNetworkDatasetProperties()
    {
      soe_name = this.GetType().Name;
      logger = new ServerLogger();
      reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;
    }

    #region IServerObjectExtension Members

    public void Init(IServerObjectHelper pSOH)
    {
      serverObjectHelper = pSOH;
      mapServer = (MapServer)serverObjectHelper.ServerObject;
      mapServerDataAccess = (IMapServerDataAccess)serverObjectHelper.ServerObject;
    }

    public void Shutdown()
    {
    }

    #endregion

    #region IObjectConstruct Members

    public void Construct(IPropertySet props)
    {
      configProps = props;
    }

    #endregion

    #region IRESTRequestHandler Members

    public string GetSchema()
    {
      return reqHandler.GetSchema();
    }

    public byte[] HandleRESTRequest(string Capabilities, string resourceName, string operationName, string operationInput, string outputFormat, string requestProperties, out string responseProperties)
    {
      return reqHandler.HandleRESTRequest(Capabilities, resourceName, operationName, operationInput, outputFormat, requestProperties, out responseProperties);
    }

    #endregion

    private RestResource CreateRestSchema()
    {
      RestResource rootRes = new RestResource(soe_name, false, RootResHandler);

      RestOperation sampleOper = new RestOperation("UtilityNetworkExtensionDataOperation",
                                                new string[] { "versionName" },
                                                new string[] { "json" },
                                                ExtensionDataOperationHandler);

      rootRes.operations.Add(sampleOper);

      return rootRes;
    }

    private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
    {
      responseProperties = null;

      return Encoding.UTF8.GetBytes("UN Properties");
    }

    private byte[] ExtensionDataOperationHandler(NameValueCollection boundVariables,
                                              JsonObject operationInput,
                                                  string outputFormat,
                                                  string requestProperties,
                                              out string responseProperties)
    {
      responseProperties = null;

      string versionName;
      bool found = operationInput.TryGetString("versionName", out versionName);

      if (!found || string.IsNullOrEmpty(versionName))
      {
        versionName = "sde.DEFAULT";
      }

      object unProperties = AccessUNDatasetProperties(mapServer, versionName);      
      string jsonPropertiesAsString = JsonSerializer.Serialize(unProperties);

      return Encoding.UTF8.GetBytes(jsonPropertiesAsString);
    }

    private object AccessUNDatasetProperties(IMapServer mapService, string versionName)
    {
      #region Access UN Dataset properties
      
      // Get dataset from the map service
      IDataset defaultDataset = mapServerDataAccess.GetDataSource(mapService.DefaultMapName, 0 ) as IDataset;
      IWorkspace workspace = defaultDataset.Workspace;
      IVersionedWorkspace versionedWorkspace = (IVersionedWorkspace)workspace;

      // Default Version
      IVersion  defaultVersion = versionedWorkspace.DefaultVersion;
      string defaultVersionName = defaultVersion.VersionName;

      // Iterate over versions
      IEnumVersionInfo allVersions = versionedWorkspace.Versions;
      allVersions.Reset();
      IVersionInfo currentVersion = allVersions.Next();
      int versionCounter = 1;
      while (currentVersion != null)
      {
        string currentVersionName = currentVersion.VersionName;
        logger.LogMessage(ServerLogger.msgType.infoSimple, nameof(AccessUNDatasetProperties),3,$"VersionName from: AccessUNDataset {currentVersionName} - {versionCounter}");

        currentVersion = allVersions.Next(); 
        versionCounter++;
      }
      
      // Get a version specified by the name
      IVersion version = versionedWorkspace.FindVersion(versionName);    
      IFeatureWorkspace featureWorkspace = (IFeatureWorkspace)version;

      // Get the UtilityNetwork Dataset by opening an extension dataset
      string extensionDatasetName = "UtilityNetwork";
      IDataset versionedUNDataset = featureWorkspace.OpenExtensionDataset(esriDatasetType.esriDTUtilityNetwork, extensionDatasetName);
      
      IWorkspace unWorkSpace = versionedUNDataset.Workspace;
      IVersion unCurrentlySelectedVersion = (IVersion)unWorkSpace;
      string currenltySelectedVerionName = unCurrentlySelectedVersion.VersionName;
      
      //Read UN Properties
      IBaseNetwork baseNetwork = (IBaseNetwork)versionedUNDataset;
      IDatasetComponent datasetComponent = (IDatasetComponent)versionedUNDataset;
      IDEDataset deDataSet = datasetComponent.DataElement;

      IDEBaseNetwork deBaseNetwork = (IDEBaseNetwork)deDataSet;
      int schemaVersion = deBaseNetwork.SchemaGeneration;
            
      IBaseNetworkTopology baseNetworkTopology = (IBaseNetworkTopology)baseNetwork;
      bool hasValidateNetworkTopology = baseNetworkTopology.HasValidNetworkTopology();
      #endregion Access UN Dataset properties 

      // Format response as JSON
      object result = new
      {
        UtilityNetworkDatasetName = extensionDatasetName,
        CurrentVersionName = currenltySelectedVerionName,
        DefaultVersionName = defaultVersionName,
        SchemaVersion = schemaVersion,
        NetworkTopology = hasValidateNetworkTopology
      };

      return result;
    }
  }
}

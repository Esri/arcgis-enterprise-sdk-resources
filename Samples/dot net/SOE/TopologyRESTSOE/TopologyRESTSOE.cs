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

using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Server;
using ESRI.Server.SOESupport;
using System;
using System.Collections.Specialized;
using System.Runtime.InteropServices;
using System.Text;

//This is REST SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace TopologyRESTSOE
{
  [ComVisible(true)]
  [Guid("3d374591-c679-4e42-b79f-208e81b52d1c")]
  [ClassInterface(ClassInterfaceType.None)]
  [ServerObjectExtension("MapServer",
      AllCapabilities = "",
      DefaultCapabilities = "",
      Description = "",
      DisplayName = "Topology REST SOE",
      Properties = "",
      SupportsREST = true,
      SupportsSOAP = false,
      SupportsSharedInstances = false)]
  public class TopologyRESTSOE : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
  {
    private string soe_name;

    private IPropertySet configProps;
    private IServerObjectHelper serverObjectHelper;
    private ServerLogger logger;
    private IRESTRequestHandler reqHandler;
    private TopologyOperation topologyOperation;
    private GeodatabaseHelper geodatabaseHelper;

    private readonly string gdbPath = @"C:\Data\CookCounty.gdb";

    public TopologyRESTSOE()
    {
      soe_name = this.GetType().Name;
      logger = new ServerLogger();
      reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;

      topologyOperation = new TopologyOperation();
      geodatabaseHelper = new GeodatabaseHelper();
    }

    #region IServerObjectExtension Members

    public void Init(IServerObjectHelper pSOH)
    {
      serverObjectHelper = pSOH;
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

      RestOperation topologyOperation = new RestOperation("QueryAdjoiningParcels",
                                                new string[] { "ParcelObjectID" },
                                                new string[] { "json" },
                                                TopologyOperationHandler);

      rootRes.operations.Add(topologyOperation);

      return rootRes;
    }

    private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
    {
      responseProperties = null;
      JsonObject result = new JsonObject();
      return Encoding.UTF8.GetBytes(result.ToJson());
    }

    private byte[] TopologyOperationHandler(NameValueCollection boundVariables, JsonObject operationInput, string outputFormat, string requestProperties, out string responseProperties)
    {
      responseProperties = null;

      System.Diagnostics.Debugger.Launch();

      string topologyName = "Cadastre_Topology";
      string featureDatasetName = "Cadastre";
      string parcelFeatureClassName = "TaxParcel";

      IWorkspace workspace = geodatabaseHelper.GetGeodatabaseWorkspace(gdbPath);
      IFeatureDataset featureDataset = geodatabaseHelper.GetFeatureDatasetFromWorkspace(workspace, featureDatasetName);
      
      object parcelIdValue;

      bool isParcelValueFound = operationInput.TryGetObject("ParcelObjectID", out parcelIdValue);
      if (!isParcelValueFound || parcelIdValue == null) return null;

      int parcelObjectId;
      bool isParcelId = int.TryParse(parcelIdValue.ToString(), out parcelObjectId);

      JsonObject result = new JsonObject();

      if (isParcelId)
      {
        result = topologyOperation.TraverseTopologyGraph(featureDataset, topologyName, parcelFeatureClassName, parcelObjectId);
      }

      return Encoding.UTF8.GetBytes(result.ToJson());
    }
  }
}

// Copyright 2021 ESRI
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

using System;
using System.Text;

using System.Collections.Specialized;
using System.Runtime.InteropServices;

using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Server;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Carto;
using ESRI.Server.SOESupport;

//This is REST SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace QueryNetworkDatasetRESTSOE
{
  [ComVisible(true)]
  [Guid("b4153121-0c2b-4a19-b01c-d871f8c931a2")]
  [ClassInterface(ClassInterfaceType.None)]
  [ServerObjectExtension("MapServer",
    AllCapabilities = "",
    DefaultCapabilities = "",
    Description = "",
    DisplayName = "QueryNetworkDatasetRESTSOE",
    Properties = "",
    SupportsREST = true,
    SupportsSOAP = false,
    SupportsSharedInstances = true)]
  public class QueryNetworkDatasetRESTSOE : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
  {
    private string soe_name;

    private IPropertySet configProps;
    private ServerLogger logger;
    private IRESTRequestHandler reqHandler;
    private INetworkDataset networkDataset;
    private IFeatureClass streetFC;
    private int travelTimeAttributeID;
    private int streetsSourceID;
    private const string streetsName = "Streets";
    private const string costAttributeName = "TravelTime";


    public QueryNetworkDatasetRESTSOE()
    {
      soe_name = this.GetType().Name;
      logger = new ServerLogger();
      reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;
    }

    #region IServerObjectExtension Members

    public void Init(IServerObjectHelper pSOH)
    {
      IMapServer mapServer = pSOH.ServerObject as IMapServer;
      IMapServerDataAccess mapServerDataAccess = mapServer as IMapServerDataAccess;
      IMapServerInfo mapServerInfo = mapServer.GetServerInfo(mapServer.DefaultMapName);
      IMapLayerInfos layerInfos = mapServerInfo.MapLayerInfos;
      IMapLayerInfo ndLayerInfo = null;

      // Get the network dataset layer from current service
      for (var i = 0; i < layerInfos.Count; i++)
      {
        IMapLayerInfo layerInfo = layerInfos.Element[i];
        if (layerInfo.Type.Equals("Network Dataset Layer", StringComparison.InvariantCultureIgnoreCase))
        {
          ndLayerInfo = layerInfo;
          break;
        }
      }

      // Get the network dataset
      if (ndLayerInfo != null)
      {
        var dt = mapServerDataAccess.GetDataSource(mapServer.DefaultMapName, ndLayerInfo.ID);
        // Cast the dataset to required network dataset interface
        networkDataset = dt as INetworkDataset;
      }

      if (networkDataset != null)
      {
        // Open the streets feature class
        IDataset dataSet = networkDataset as IDataset;
        IFeatureWorkspace fWorkspace = dataSet.Workspace as IFeatureWorkspace;
        streetFC = fWorkspace.OpenFeatureClass(streetsName);

        // Get the Streets source ID
        INetworkSource streetNetworkSource = networkDataset.SourceByName[streetsName];
        streetsSourceID = streetNetworkSource.ID;

        // Get the TraveTime attribute ID
        INetworkAttribute travelTimeAttribute = networkDataset.AttributeByName[costAttributeName];
        travelTimeAttributeID = travelTimeAttribute.ID;
      }      
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

    public byte[] HandleRESTRequest(string Capabilities, string resourceName, string operationName,
      string operationInput, string outputFormat, string requestProperties, out string responseProperties)
    {
      return reqHandler.HandleRESTRequest(Capabilities, resourceName, operationName, operationInput, outputFormat,
        requestProperties, out responseProperties);
    }

    #endregion

    private RestResource CreateRestSchema()
    {
      RestResource rootRes = new RestResource(soe_name, false, RootResHandler);

      RestOperation queryOp = new RestOperation("Query by extent",
        new string[] { "Extent" },
        new string[] { "json" },
        QueryByExtentHandler);

      rootRes.operations.Add(queryOp);

      return rootRes;
    }

    private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties,
      out string responseProperties)
    {
      responseProperties = null;

      JsonObject json = new JsonObject();
      json.AddString("name", "Query network dataset by extent");
      json.AddString("description",
        "Provide an extent by specifying 'xmin,ymin;xmax,ymax' to query the network elements with the extent.");

      return Encoding.UTF8.GetBytes(json.ToJson());
    }

    private byte[] QueryByExtentHandler(NameValueCollection boundVariables,
        JsonObject operationInput,
        string outputFormat,
        string requestProperties,
        out string responseProperties)
    {
      responseProperties = null;

      if (networkDataset == null)
        throw new NullReferenceException("Could not access the network dataset.");

      if (!operationInput.TryGetString("Extent", out var envelopeString))
        throw new ArgumentNullException("Extent is invalid.");
      var coords = envelopeString.Split(';');
      var minCoords = coords[0].Split(',');
      var maxCoords = coords[1].Split(',');
      double.TryParse(minCoords[0].Trim(), out var minX);
      double.TryParse(minCoords[1].Trim(), out var minY);
      double.TryParse(maxCoords[0].Trim(), out var maxX);
      double.TryParse(maxCoords[0].Trim(), out var maxY);

      // Find features in envelope
      IEnvelope env = new EnvelopeClass();
      env.PutCoords(minX, minY, maxX, maxY);
      ISpatialFilter spatialFilter = new SpatialFilter();
      spatialFilter.Geometry = env;
      spatialFilter.SpatialRel = esriSpatialRelEnum.esriSpatialRelIntersects;

      // Add selected features OID into LongArray
      ILongArray oIDs = new LongArray();
      IFeatureCursor fCursor = streetFC.Search(spatialFilter, true);
      IFeature feature = fCursor.NextFeature();
      while (feature != null)
      {
        oIDs.Add(feature.OID);
        feature = fCursor.NextFeature();
      }

      // Get the network edges corresponding to the streets and write out information about them

      INetworkQuery networkQuery = networkDataset as INetworkQuery;
      INetworkJunction fromJunction =
          networkQuery.CreateNetworkElement(esriNetworkElementType.esriNETJunction) as INetworkJunction;
      INetworkJunction toJunction =
          networkQuery.CreateNetworkElement(esriNetworkElementType.esriNETJunction) as INetworkJunction;

      IEnumNetworkElement networkElements = networkQuery.ElementsByOIDs[streetsSourceID, oIDs];
      INetworkElement networkElement = networkElements.Next();
      JSONObject result = new JSONObject();
      JSONArray elementArray = new JSONArray();
      INetworkEdge networkEdge;
      while (networkElement != null)
      {
        JSONObject jo = new JSONObject();
        networkEdge = networkElement as INetworkEdge;
        networkEdge.QueryJunctions(fromJunction, toJunction);
        double travelTime = (double)networkEdge.AttributeValue[travelTimeAttributeID];
        jo.AddLong("EdgeID", networkEdge.EID);
        jo.AddLong("FromJunctionID", fromJunction.EID);
        jo.AddLong("ToJunctionID", toJunction.EID);
        jo.AddDoubleEx(costAttributeName, travelTime, 4);
        elementArray.AddJSONObject(jo);
        networkElement = networkElements.Next();
      }
      result.AddJSONArray("NetworkElements", elementArray);

      return Encoding.UTF8.GetBytes(result.ToJSONString(null));
    }
  }
}
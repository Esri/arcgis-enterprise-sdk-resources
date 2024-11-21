// Copyright 2023 ESRI
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


namespace NetUNTracingRESTSOE
{
    [ComVisible(true)]
    [Guid("29cfdaea-e587-493f-9108-29807ffb4b9a")]
    [ClassInterface(ClassInterfaceType.None)]
    [ServerObjectExtension("MapServer",
        AllCapabilities = "",
        DefaultCapabilities = "",
        Description = ".Net Utility Network Tracing REST SOE Sample",
        DisplayName = ".Net UN Tracing REST SOE",
        Properties = "",
        SupportsREST = true,
        SupportsSOAP = false,
        SupportsSharedInstances = false)]
    public class NetUNTracingRESTSOE : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
    {
        private string soe_name;

        private IPropertySet configProps;
        private IServerObjectHelper serverObjectHelper;
        private ServerLogger logger;
        private IRESTRequestHandler reqHandler;

        private SOEUtil soeUtil;
        private MapServer mapService;
        private IMapLayerInfo unLayerInfo;
        private IMapServerDataAccess mapServerDataAccess;
        private IDataset unDataset;

        // Naperville Electric utility network specific properties
        // >>> Modify these values to use with a different UN dataset
        // Electric Distribution Domain
        private const string DOMAIN_NETWORK = "ElectricDistribution";
        // Medium Voltage Tier Name and Code
        private const string MV_TIER_NAME = "Medium Voltage Radial";
        private static int MV_TIER_CODE = 0;
        // Electric Distribution Device Layer Name and Source ID
        private const string DEVICE_LAYER_NAME = "Electric Distribution Device";
        private const int DEVICE_SOURCE_ID = 9;
        // Medium Voltage Transformer Asset Group and Load Terminal ID
        private const int MV_XFR_ASSETGROUP = 15;
        private const int MV_XFR_TERMINAL_ID = 8;
        // Service Point Asset Group and Asset Types
        private const int LV_SERVICE_ASSETGROUP = 11;
        public readonly int[] LV_SERVICE_ASSETTYPES = { 0, 401, 402, 403 };

        public NetUNTracingRESTSOE()
        {
            soe_name = this.GetType().Name;
            logger = new ServerLogger();
            reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;
            soeUtil = new SOEUtil();
            mapService = null;
            unLayerInfo = null;
            mapServerDataAccess = null;
            unDataset = null;
        }

        #region IServerObjectExtension Members

        public void Init(IServerObjectHelper pSOH)
        {
            serverObjectHelper = pSOH;
            mapService = (MapServer)serverObjectHelper.ServerObject;
            mapServerDataAccess = (IMapServerDataAccess)serverObjectHelper.ServerObject;

            // Check if this service contains a Utility Network layer
            unLayerInfo = soeUtil.GetUNLayerInfo(mapService);
            if (unLayerInfo != null)
            {
                // Get the Utility Network dataset
                IFeatureClass fc = (IFeatureClass)mapServerDataAccess.GetDataSource(mapService.DefaultMapName, unLayerInfo.SubLayers.Element[0]);

                // Get UN dataset
                IFeatureDataset fd = fc.FeatureDataset;
                IDataset ds = (IDataset)fd;
                IEnumDataset enumSubDS = ds.Subsets;
                IDataset subDS = enumSubDS.Next();
                while (subDS != null)
                {
                    if (subDS.Type == esriDatasetType.esriDTUtilityNetwork)
                    {
                        unDataset = subDS;
                        break;
                    }
                    subDS = enumSubDS.Next();
                }

                logger.LogMessage(ServerLogger.msgType.infoStandard, soe_name + ".init()", 200, "UN dataset found: " + unDataset.Name);
            }

        }

        public void Shutdown()
        {
            logger.LogMessage(ServerLogger.msgType.infoStandard, soe_name + ".shutdown()", 200, "");
            unDataset = null;
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

        #region REST Resources & Operations
        private RestResource CreateRestSchema()
        {
            RestResource rootRes = new RestResource(soe_name, false, RootResHandler);

            RestOperation getTransformers = new RestOperation("getMVTransformerAssetIds",
                                          new string[] { "where" },
                                          new string[] { "json" },
                                          GetTransformersOperHandler);

            rootRes.operations.Add(getTransformers);

            RestOperation getCustomers = new RestOperation("getCustomers",
                                                      new string[] { "transformerAssetId" },
                                                      new string[] { "json" },
                                                      GetCustomersOperHandler);

            rootRes.operations.Add(getCustomers);

            return rootRes;
        }

        /**
        * Returns basic Utility Network properties on the root resource
        */
        private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
        {
            responseProperties = null;

            if (unDataset == null)
                throw new RestErrorException("No Utility Network found");

            JSONObject result = new JSONObject();

            try
            {
                result.AddString("description",
                    "UN Tracing is a REST SOE with 2 operations called \"getMVTransformerAssetIds\" and  \"getCustomers\".");
                result.AddString(
                        "usage",
                        "The \"getMVTransformerAssetIds\" operation returns a list of medium voltage transformer asset IDs.\n"
                                + "These asset IDs can be then used as input in the \"getCustomers\" operation.\n"
                                + "The \"getCustomers\" operation runs a network trace starting at a specified medium voltage transformer\n"
                                + " and returns the list of service points and associated customer information.");
                result.AddString("note",
                    "Make sure that the network topology is enabled. If it is the case, then \"hasValidNetworkTopology\" property should be 'true'.");

                result.AddString("networkName", unLayerInfo.Name);

                // Get UN properties
                IPropertySet unProps = GetUNProperties();

                object[] nameArray = new object[1];
                object[] valueArray = new object[1];
                unProps.GetAllProperties(out nameArray[0], out valueArray[0]);
                object[] names = (object[])nameArray[0];
                object[] values = (object[])valueArray[0];
                JSONObject joUNProps = new JSONObject();

                for (int i = 0; i < unProps.Count; i++)
                {
                    joUNProps.Add((String)names[i], values[i]);
                }
                result.AddJSONObject("networkProperties", joUNProps);
            }
            catch (Exception e)
            {
                logger.LogMessage(ServerLogger.msgType.debug, soe_name + " root resource", 500, "Exception " + e.GetType().Name + " " + e.Message + " " + e.StackTrace);
                JSONObject error = soeUtil.CreateErrorJSON(500, "Unable to complete operation", e.HResult, e.Message);
                return Encoding.UTF8.GetBytes(error.ToJSONString(null));
            }

            return Encoding.UTF8.GetBytes(result.ToJSONString(null));
        }

        /**
        * Returns a list of transformer asset IDs
        */
        private byte[] GetTransformersOperHandler(NameValueCollection boundVariables,
                                                  JsonObject operationInput,
                                                      string outputFormat,
                                                      string requestProperties,
                                                  out string responseProperties)
        {
            responseProperties = null;

            if (unDataset == null)
                throw new RestErrorException("No Utility Network found");

            JSONObject result = new JSONObject();

            try
            {
                string userWhereClause;
                string xfrWhereClause = "ASSETGROUP=" + MV_XFR_ASSETGROUP.ToString() + " AND TIERNAME=" + MV_TIER_CODE.ToString();
                operationInput.TryGetString("where", out userWhereClause);
                if (!string.IsNullOrEmpty(userWhereClause))
                {
                    xfrWhereClause += " AND (" + userWhereClause + ")";
                }

                // Get the transformers asset IDs
                IStringArray xfrOutFields = new StrArrayClass();
                xfrOutFields.Add("ASSETID");
                IPropertySetArray xfrQryResults = QueryDevices(xfrWhereClause, xfrOutFields);

                IJSONArray assetIds = new JSONArray();

                if (xfrQryResults != null)
                {
                    for (int i = 0; i < xfrQryResults.Count; i++)
                    {
                        assetIds.Add((string)xfrQryResults.Element[i].GetProperty("ASSETID"));
                    }
                }
                result.AddJSONArray("assetIds", assetIds);
            }
            catch (Exception e)
            {
                logger.LogMessage(ServerLogger.msgType.debug, soe_name + " getMVTransformerAssetIds", 500, "Exception " + e.GetType().Name + " " + e.Message + " " + e.StackTrace);
                JSONObject error = soeUtil.CreateErrorJSON(500, "Unable to complete operation", e.HResult, e.Message);
                return Encoding.UTF8.GetBytes(error.ToJSONString(null));
            }

            return Encoding.UTF8.GetBytes(result.ToJSONString(null));
        }

        /**
        * Returns a list of customers serviced by the specified transformer
        */
        private byte[] GetCustomersOperHandler(NameValueCollection boundVariables,
                                                  JsonObject operationInput,
                                                      string outputFormat,
                                                      string requestProperties,
                                                  out string responseProperties)
        {
            responseProperties = null;

            if (unDataset == null)
                throw new RestErrorException("No Utility Network found");

            JSONObject result = new JSONObject();
            try
            {
                string xfrAssetID = "";
                operationInput.TryGetString("transformerAssetId", out xfrAssetID);
                if (string.IsNullOrEmpty(xfrAssetID))
                {
                    long? assetNumber = null;
                    operationInput.TryGetAsLong("transformerAssetId", out assetNumber);
                    if (assetNumber == null)
                        throw new ArgumentNullException("transformerAssetId");
                    else
                        xfrAssetID = assetNumber.ToString();
                }

                // Get the transformer feature global ID from the asset ID
                string xfrWhereClause = "ASSETGROUP=" + MV_XFR_ASSETGROUP.ToString() + " AND ASSETID='" + xfrAssetID + "'";
                IStringArray xfrOutFields = new StrArrayClass();
                xfrOutFields.Add("GLOBALID");
                xfrOutFields.Add("ASSETTYPE");
                xfrOutFields.Add("SHAPE");
                IPropertySetArray xfrQryResults = QueryDevices(xfrWhereClause, xfrOutFields);

                if (xfrQryResults == null || xfrQryResults.Count == 0)
                    throw new RestErrorException("No medium voltage transformer found for asset id: " + xfrAssetID);

                string xfrGlobalID = (string)xfrQryResults.Element[0].GetProperty("GLOBALID");
                short xfrAssetType = (short)xfrQryResults.Element[0].GetProperty("ASSETTYPE");

                // Execute a downstream trace to find all customer low voltage service points
                IStringArray lvsGlobalIDs = FindLVServicePoints(xfrGlobalID);

                // Prepare response JSON
                JSONObject xfrInfo = new JSONObject();
                xfrInfo.AddString("ASSETID", xfrAssetID);
                xfrInfo.AddString("GLOBALID", xfrGlobalID);
                xfrInfo.AddLong("ASSETGROUP", MV_XFR_ASSETGROUP);
                xfrInfo.AddLong("ASSETTYPE", xfrAssetType);

                IJSONObject xfrPointJson = new JSONObject();
                IGeometry xfrPoint = (IGeometry)xfrQryResults.Element[0].GetProperty("SHAPE");
                IJSONConverterGeometry geoSerializer = new JSONConverterGeometryClass();
                geoSerializer.QueryJSONGeometry(xfrPoint, false, xfrPointJson);
                xfrInfo.AddJSONObject("geometry", xfrPointJson);
                result.AddJSONObject("transformer", xfrInfo);

                IJSONArray servicePoints = new JSONArrayClass();

                // Get additional service point info if any returned from trace
                if (lvsGlobalIDs != null && lvsGlobalIDs.Count > 0)
                {
                    // Query devices to get more details about service points
                    string lvsGlobalIDList = "";

                    for (int i = 0; i < lvsGlobalIDs.Count; i++)
                    {
                        lvsGlobalIDList += (i == 0) ? "" : ",";
                        lvsGlobalIDList += "'" + lvsGlobalIDs.Element[i] + "'";
                    }

                    string lvsWhereClause = "GLOBALID IN (" + lvsGlobalIDList + ")";
                    IStringArray lvsOutFields = new StrArrayClass();
                    lvsOutFields.Add("GLOBALID");
                    lvsOutFields.Add("ASSETTYPE");
                    lvsOutFields.Add("ASSETGROUP");
                    lvsOutFields.Add("ASSETID");
                    lvsOutFields.Add("SHAPE");
                    IPropertySetArray lvsQryResults = QueryDevices(lvsWhereClause, lvsOutFields);

                    // Get customer details
                    if (lvsQryResults != null && lvsQryResults.Count > 0)
                    {
                        for (int i = 0; i < lvsQryResults.Count; i++)
                        {
                            IPropertySet lvs = lvsQryResults.Element[i];
                            JSONObject servicePoint = new JSONObject();
                            servicePoint.AddString("GLOBALID", (string)lvs.GetProperty("GLOBALID"));
                            servicePoint.AddString("ASSETID", (string)lvs.GetProperty("ASSETID"));
                            servicePoint.AddLong("ASSETGROUP", (int)lvs.GetProperty("ASSETGROUP"));
                            servicePoint.AddLong("ASSETTYPE", (short)lvs.GetProperty("ASSETTYPE"));

                            IGeometry lvsPoint = (IGeometry)lvs.GetProperty("SHAPE");
                            IJSONObject lvsPointJson = new JSONObject();
                            geoSerializer.QueryJSONGeometry(lvsPoint, false, lvsPointJson);
                            servicePoint.AddJSONObject("geometry", lvsPointJson);

                            JSONObject customerInfo = GetCustomerInfoJSON();
                            servicePoint.AddJSONObject("customerInfo", customerInfo);
                            servicePoints.AddJSONObject(servicePoint);
                        }
                    }
                }
                result.AddJSONArray("servicePoints", servicePoints);
            }
            catch (Exception e)
            {
                logger.LogMessage(ServerLogger.msgType.debug, soe_name + " getCustomers", 500, "Exception " + e.GetType().Name + " " + e.Message + " " + e.StackTrace);
                JSONObject error = soeUtil.CreateErrorJSON(500, "Unable to complete operation", e.HResult, e.Message);
                return Encoding.UTF8.GetBytes(error.ToJSONString(null));
            }

            return Encoding.UTF8.GetBytes(result.ToJSONString(null));
        }
        #endregion

        #region UN SOE Methods
        /*
        * This method returns the current utility network properties
        */
        private IPropertySet GetUNProperties()
        {
            IPropertySet unProps = new PropertySet();

            // Get UN
            IBaseNetwork bn = (IBaseNetwork)unDataset;

            // Get data element
            IDatasetComponent dsComponent = (IDatasetComponent)unDataset;
            IDEDataset deDS = dsComponent.DataElement;

            IDEBaseNetwork deBN = (IDEBaseNetwork)deDS;
            unProps.SetProperty("proVersion", deBN.ProVersion);
            unProps.SetProperty("schemaGeneration", deBN.SchemaGeneration);
            unProps.SetProperty("userIdentity", deBN.UserIdentity);
            unProps.SetProperty("creationTime", deBN.CreationTime.ToString());

            // Get topology
            IBaseNetworkTopology bnTopo = (IBaseNetworkTopology)bn;
            unProps.SetProperty("hasValidNetworkTopology", bnTopo.HasValidNetworkTopology());

            return unProps;
        }

        /*
        * This method queries the device layer and returns a set of features
        */
        private IPropertySetArray QueryDevices(string whereClause, IStringArray outFields)
        {
            IPropertySetArray results = new PropertySetArray();

            int deviceLayerId = soeUtil.GetLayerIdByName(mapService, DEVICE_LAYER_NAME);
            IFeatureClass deviceFC = (IFeatureClass)mapServerDataAccess.GetDataSource(mapService.DefaultMapName, deviceLayerId);

            IQueryFilter filter = new QueryFilterClass();
            filter.WhereClause = whereClause;
            IFeatureCursor resultsFeatureCursor = deviceFC.Search(filter, false);
                
            IArray fieldIndex = new ArrayClass();
            for(int i=0;i<outFields.Count;i++)
            {
                fieldIndex.Add(deviceFC.FindField(outFields.Element[i]));
            }
            IFeature resultFeature = null;
            while ((resultFeature = resultsFeatureCursor.NextFeature()) != null)
            {
                IPropertySet values = new PropertySet();
                for(int i=0;i<outFields.Count;i++)
                {
                    values.SetProperty(outFields.Element[i], resultFeature.Value[(int)fieldIndex.Element[i]]);
                }
                results.Add(values);
            }
            Marshal.ReleaseComObject(resultsFeatureCursor);

            return results;
        }

        /*
         * This method executes a utility network trace to find all the low voltage service points
         * serviced by the specified transformer and returns the service points global IDs
         */
        private IStringArray FindLVServicePoints(string xfrGlobalID)
        {
            //Get required Utility Network interfaces
            IBaseNetwork unBaseNetwork = (IBaseNetwork)unDataset;
            IDatasetComponent datasetComponent = (IDatasetComponent)unDataset;
            IDEDataset deDataset = datasetComponent.DataElement;
            IDEBaseNetwork deBaseNetwork = (IDEBaseNetwork)deDataset;
            IDEUtilityNetwork deUtilityNetwork = (IDEUtilityNetwork)deBaseNetwork;
            IDataElement deElement = (IDataElement)deDataset;

            //Create and initialize network tracer
            IUtilityNetworkQuery unQry = unBaseNetwork.CreateQuery();
            ITracer unTracer = unBaseNetwork.CreateTracer();
            unTracer.Initialize(unQry, (IDataElement)deDataset);

            // Add transformer as trace starting point
            IStringArray startGUID = new StrArrayClass();
            startGUID.Add(xfrGlobalID);
            ILongArray startTerm = new LongArrayClass();
            startTerm.Add(MV_XFR_TERMINAL_ID);

            unTracer.AddTraceLocationForJunctionFeatures(esriTraceLocationType.esriTLTStartingPoint, startGUID, startTerm);

            // Configure trace parameters
            UNTraceConfiguration traceConfig = new UNTraceConfiguration();

            traceConfig.IgnoreBarriersAtStartingPoints = true;
            traceConfig.IncludeContainers = false;
            traceConfig.IncludeBarriers = false;
            traceConfig.IncludeContent = true;
            traceConfig.IncludeIsolated = false;
            traceConfig.IncludeStructures = false;
            traceConfig.IncludeUpToFirstSpatialContainer = false;
            traceConfig.DomainNetworkName = DOMAIN_NETWORK;
            traceConfig.TierName = MV_TIER_NAME;
            traceConfig.TargetTierName = MV_TIER_NAME;
            traceConfig.TraversabilityScope = esriTraversabilityScope.esriTSJunctionsAndEdges;
            traceConfig.FilterScope = esriTraversabilityScope.esriTSJunctionsAndEdges;
            traceConfig.ValidateConsistency = false;

            // Add output filter to only return service points
            IArray outFilters = new ArrayClass();
            for (int i=0;i<LV_SERVICE_ASSETTYPES.Length;i++)
            {
                UNOutputFilter outFilter = new UNOutputFilter();
                outFilter.NetworkSourceID = DEVICE_SOURCE_ID;
                outFilter.AssetGroupCode = LV_SERVICE_ASSETGROUP;
                outFilter.AssetTypeCode = LV_SERVICE_ASSETTYPES[i];
                outFilters.Add(outFilter);
            }
            traceConfig.OutputFilters = outFilters;

            unTracer.TraceConfiguration = (ITraceConfiguration)traceConfig;

            // Execute the trace
            long[] jEid = new long[1];
            long[] eEid = new long[1];

            unTracer.Trace(esriUtilityNetworkTraceType.esriUNTTDownstream, out jEid, out eEid);

            // Get features from returned elements
            IUNTraceResults unTraceResults = (IUNTraceResults)unTracer;

            ILongArray junctionNetworkSourceIDs = new LongArrayClass();
            IStringArray junctionGlobalIDs = new StrArrayClass();
            ILongArray junctionObjectIDs = new LongArrayClass();
            ILongArray junctionTerminalIDs = new LongArrayClass();
            ILongArray junctionAssetGroupCodes = new LongArrayClass();
            ILongArray junctionAssetTypeCodes = new LongArrayClass();
            ILongArray edgeNetworkSourceIDs = new LongArrayClass();
            IStringArray edgeGlobalIDs = new StrArrayClass();
            ILongArray edgeObjectIDs = new LongArrayClass();
            ILongArray edgeAssetGroupCodes = new LongArrayClass();
            ILongArray edgeAssetTypeCodes = new LongArrayClass();

            unTraceResults.TraceResultFeatures(out junctionNetworkSourceIDs, out junctionGlobalIDs, out junctionObjectIDs, out junctionTerminalIDs, out junctionAssetGroupCodes, out junctionAssetTypeCodes, out edgeNetworkSourceIDs, out edgeGlobalIDs, out edgeObjectIDs, out edgeAssetGroupCodes, out edgeAssetTypeCodes);

            return junctionGlobalIDs;
        }

        /*
        * This method returns made-up anonymized customer details (mimics querying a customer service database for customer information)
        */
        private JSONObject GetCustomerInfoJSON()
        {
            JSONObject customerInfo = new JSONObject();
            
            customerInfo.AddString("customerID", "XXXXXXXXXX");
            customerInfo.AddString("firstName", "XXXXXXXXXX");
            customerInfo.AddString("lastName", "XXXXXXXXXX");
            customerInfo.AddString("phoneNumber", "XXX-XXX-XXXX");
            customerInfo.AddString("address", "XXXXXXXXXX");
            customerInfo.AddString("city", "XXXXXXXXXX");
            customerInfo.AddString("zipCode", "XXXXX");
            return customerInfo;
        }
        #endregion

    }
}

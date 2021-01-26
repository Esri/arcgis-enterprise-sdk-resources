package entsdksamples.soe;


/*
COPYRIGHT 1995-2012 ESRI
TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
Unpublished material - all rights reserved under the
Copyright Laws of the United States and applicable international
laws, treaties, and conventions.

For additional information, contact:
Environmental Systems Research Institute, Inc.
Attn: Contracts and Legal Services Department
380 New York Street
Redlands, California, 92373
USA

email: contracts@esri.com
*/

import com.esri.arcgis.carto.*;
import com.esri.arcgis.geodatabase.*;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geometry.JSONConverterGeometry;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.SOIHelper;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.system.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java UN Tracing REST SOE",
        description = "Java Sample Utility Network Tracing REST SOE",
        properties = "",
        allSOAPCapabilities = "",
        defaultSOAPCapabilities = "",
        supportsSharedInstances = false)

public class JavaUNTracingRESTSOE implements IServerObjectExtension, IRESTRequestHandler {

    private static final long serialVersionUID = 1L;
    private ILog serverLog;
    private IServerObjectHelper soHelper;
    private IMapLayerInfos layerInfos;
    private IPropertySet pPropertyset;
    private SOIHelper soiHelper;

    private SOEUtil soeUtil;
    private IMapServer mapService;
    private IMapLayerInfo unLayerInfo;
    private IMapServerDataAccess mapServerDataAccess;
    private IDataset unDataset;

    // Naperville Electric utility network properties
    // >>> Modify these values to use with a different UN dataset
    // Electric Distribution Domain
    private static String DOMAIN_NETWORK = "ElectricDistribution";
    // Medium Voltage Tier Name and Code
    private static String MV_TIER_NAME = "Medium Voltage Radial";
    private static Integer MV_TIER_CODE = 0;
    // Electric Distribution Device Layer Name and Source ID
    private static String DEVICE_LAYER_NAME = "Electric Distribution Device";
    private static Integer DEVICE_SOURCE_ID = 9;
    // Medium Voltage Transformer Asset Group and Load Terminal ID
    private static Integer MV_XFR_ASSETGROUP = 15;
    private static Integer MV_XFR_TERMINAL_ID = 8;
    // Service Point Asset Group and Asset Types
    private static Integer LV_SERVICE_ASSETGROUP = 11;
    public static Integer[] LV_SERVICE_ASSETTYPES = {0, 401, 402, 403};

    public JavaUNTracingRESTSOE() throws Exception {
        super();

        this.mapService = null;
        this.unLayerInfo = null;
        this.mapServerDataAccess = null;
        this.unDataset = null;
    }

    /**
     * IServerObjectExtension methods:
     * This is a mandatory interface that must be supported by all SOEs.
     * This interface is used by the Server Object to manage the lifecycle of the SOE and includes
     * two methods: init() and shutdown().
     * The Server Object cocreates the SOE and calls the init() method handing it a back reference
     * to the Server Object via the Server Object Helper argument. The Server Object Helper implements
     * a weak reference on the Server Object. The extension can keep a strong reference on the Server
     * Object Helper (for example, in a member variable) but should not.
     * <p>
     * The log entries are merely informative and completely optional.
     * <p>
     * init() is called once, when the instance of the SOE is created.
     * <p>
     * init() is called once, when the instance of the SOE is created.
     */
    /**
     * init() is called once, when the instance of the SOE is created.
     */
    public void init(IServerObjectHelper soh) throws IOException, AutomationException {
        /*
         * An SOE should retrieve a weak reference to the Server Object from the Server Object Helper in
         * order to make any method calls on the Server Object and release the
         * reference after making the method calls.
         */
        this.soHelper = soh;
        this.serverLog = ServerUtilities.getServerLogger();
        this.soeUtil = new SOEUtil();

        serverLog.addMessage(3, 200, "Beginning init in "
                + this.getClass().getName() + " SOE.");

        this.mapService = (IMapServer) this.soHelper.getServerObject();
        this.mapServerDataAccess = (IMapServerDataAccess) this.soHelper.getServerObject();

        // Check if this service contains a Utility Network layer
        unLayerInfo = soeUtil.getUNLayerInfo(mapService);
        if (unLayerInfo != null) {
            // Get the Utility Network dataset
            FeatureClass fc = new FeatureClass(mapServerDataAccess.getDataSource(mapService.getDefaultMapName(), unLayerInfo.getSubLayers().getElement(0)));

            // Open UN dataset
            IFeatureDataset fd = fc.getFeatureDataset();
            IDataset ds = new IDatasetProxy(fd);
            IEnumDataset enumSubDS = ds.getSubsets();
            IDataset subDS = enumSubDS.next();
            while (subDS != null)
            {
                if (subDS.getType() == esriDatasetType.esriDTUtilityNetwork)
                {
                    this.unDataset = subDS;
                    break;
                }
                subDS = enumSubDS.next();
            }

            this.serverLog.addMessage(3, 200, "UN dataset found: " + this.unDataset.getName());

        }

        this.serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName() + " SOE.");
    }

    /**
     * shutdown() is called once when the Server Object's context is being shut down and is
     * about to go away.
     */
    public void shutdown() throws IOException, AutomationException {
        /*
         * The SOE should release its reference on the Server Object Helper.
         */
        serverLog.addMessage(3, 200, "Shutting down "
                + this.getClass().getName() + " SOE.");
        this.serverLog.addMessage(3, 200, "Shutting down " + this.getClass().getName() + " SOE.");
        this.serverLog = null;
        this.unDataset = null;
    }


    /**
     * Returns JSON representation of specified resource.
     *
     * @return String JSON representation of specified resource.
     */
    private byte[] getResource(String capabilitiesList, String resourceName, String outputFormat,
                               JSONObject requestPropertiesJSON, java.util.Map<String, String> responsePropertiesMap) throws Exception {
        if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
            return getRootResource();
        }
        return null;
    }

    /**
     * Returns JSON representation of the root resource.
     *
     * @return String JSON representation of the root resource.
     */
    private byte[] getRootResource() throws Exception {
        JSONObject json = new JSONObject();

        try {
            json.put(
                    "description",
                    "UN Tracing is a REST SOE with 2 operations called \"getMVTransformerAssetIds\" and  \"getCustomers\".");
            json.put(
                    "usage",
                    "The \"getMVTransformerAssetIds\" operation returns a list of medium voltage transformer asset IDs.\n"
                            + "These asset IDs can be then used as input in the \"getCustomers\" operation.\n"
                            + "The \"getCustomers\" operation runs a network trace starting at a specified medium voltage transformer\n"
                            + " and returns the list of service points and associated customer information.");
            json.put("note",
                    "Make sure that the network topology is enabled. If it is the case, then \"hasValidNetworkTopology\" property should be 'true'.");

            IPropertySet unProps = getUNProperties();
            Object[] nameArray = new Object[1];
            Object[] valueArray = new Object[1];
            unProps.getAllProperties(nameArray, valueArray);
            Object[] names = (Object[]) nameArray[0];
            Object[] values = (Object[]) valueArray[0];
            JSONObject joUNProps = new JSONObject();

            for (int i = 0; i < unProps.getCount(); i++) {
                joUNProps.put((String) names[i], values[i]);
            }
            json.put("networkProperties", joUNProps);
        }
        catch (Exception e) {
            String message = "Exception occurred while handling REST request for SOE " + this.getClass().getName() + ":"
                    + e.getMessage();
            this.serverLog.addMessage(1, 500, message);
            return ServerUtilities.sendError(0, e.getMessage(), null).getBytes("utf-8");
        }

        return json.toString().getBytes("utf-8");
    }


    /**
     * Invokes specified REST operation on specified REST resource
     *
     * @param capabilitiesList
     * @param resourceName
     * @param operationName
     * @param operationInput
     * @param outputFormat
     * @param requestProperties
     * @param responsePropertiesMap
     * @return byte[]
     */
    private byte[] invokeRESTOperation(String capabilitiesList, String resourceName, String operationName,
                                       String operationInput, String outputFormat, JSONObject requestPropertiesJSON,
                                       java.util.Map<String, String> responsePropertiesMap) throws Exception {
        byte[] operationOutput = null;

		JSONObject operationInputAsJSON = new JSONObject(operationInput);

        if (resourceName.isEmpty()) {
            if (operationName.equalsIgnoreCase("getMVTransformerAssetIds")) {
                operationOutput = getTransformers(operationInputAsJSON, outputFormat, requestPropertiesJSON,responsePropertiesMap);
            } else if (operationName.equalsIgnoreCase("getCustomers")) {
                operationOutput = getCustomers(operationInputAsJSON, outputFormat, requestPropertiesJSON,responsePropertiesMap);
            }
        } else
        // if non existent sub-resource specified, report error
        {
            this.serverLog.addMessage(1, 500, "No sub-resource by name "
                    + resourceName + " found.");
            return ServerUtilities.sendError(500,
                    "No sub-resource by name " + resourceName + " found.",
                    new String[]{"No details specified."})
                    .getBytes("utf-8");
        }

        return operationOutput;
    }

    /*************************************************************************************
     * IRESTRequestHandler methods:
     *************************************************************************************/
    /**
     * This method handles REST requests by determining whether an operation or
     * resource has been invoked and then forwards the request to appropriate
     * methods.
     */
    @Override
	public byte[] handleRESTRequest(String capabilities, String resourceName, String operationName,
									String operationInput, String outputFormat, String requestProperties, String[] responseProperties) throws IOException, AutomationException {

        // parse request properties, create a map to hold request properties
        JSONObject requestPropertiesJSON = new JSONObject(requestProperties);

        // create a response properties map to hold properties of response
        java.util.Map<String, String> responsePropertiesMap = new HashMap<String, String>();

        try {
            // if no operationName is specified send description of specified
            // resource
            byte[] response;
            if (operationName.length() == 0) {
                response = getResource(capabilities, resourceName, outputFormat, requestPropertiesJSON,
                        responsePropertiesMap);
            } else
            // invoke REST operation on specified resource
            {
                response = invokeRESTOperation(capabilities, resourceName, operationName, operationInput, outputFormat,
                        requestPropertiesJSON, responsePropertiesMap);
            }

            // handle response properties
            JSONObject responsePropertiesJSON = new JSONObject(responsePropertiesMap);
            responseProperties[0] = responsePropertiesJSON.toString();

            return response;
        } catch (Exception e) {
            String message = "Exception occurred while handling REST request for SOE " + this.getClass().getName() + ":"
                    + e.getMessage();
            this.serverLog.addMessage(1, 500, message);
            return ServerUtilities.sendError(500,
                    "Unable to complete operation",
                    new String[]{e.getMessage()})
                    .getBytes("utf-8");
        }
    }


	/**
     * This method returns the resource hierarchy of a REST based SOE in JSON format.
     */
    public String getSchema() throws IOException, AutomationException {
        try {
            JSONObject untracingrestsoe = ServerUtilities.createResource("UNTracingRESTSOE",
                    "This SOE runs a network trace to return a list of customers serviced by a specified medium voltage transformer.", false, false);

            JSONArray _subResourcesArray = new JSONArray();
            untracingrestsoe.put("resources", _subResourcesArray);

            JSONArray _OpArray = new JSONArray();
            _OpArray.put(ServerUtilities.createOperation("getMVTransformerAssetIds",
                    "where", "json", false));
            _OpArray.put(ServerUtilities.createOperation("getCustomers",
                    "transformerAssetId", "json", false));
            untracingrestsoe.put("operations", _OpArray);

            return untracingrestsoe.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Returns a list of transformer asset IDs
     */
    private byte[] getTransformers(JSONObject operationInput, String outputFormat, JSONObject requestPropertiesJSON,
                                   java.util.Map<String, String> responseProperties) throws Exception {

        responseProperties.put("Content-Type", "application/json");
        JSONObject result = new JSONObject();

        if (this.unDataset == null) {
            String message = "No Utility Network found.";
            this.serverLog.addMessage(1, 500, message);
            return ServerUtilities.sendError(0, message, null).getBytes("utf-8");
        }

        try {
            String xfrWhereClause = "ASSETGROUP=" + MV_XFR_ASSETGROUP.toString() + " AND TIERNAME=" + MV_TIER_CODE.toString();
            String userWhereClause = operationInput.has("where") ? operationInput.getString("where") : "";
            if (userWhereClause != null && userWhereClause.length() > 0) {
                xfrWhereClause += " AND (" + userWhereClause + ")";
            }

            // Get the transformers asset IDs
            IStringArray xfrOutFields = new StrArray();
            xfrOutFields.add("ASSETID");
            IPropertySetArray xfrQryResults = queryDevices(xfrWhereClause, xfrOutFields);

            JSONArray assetIds = new JSONArray();
            if (xfrQryResults != null) {
                for (int i = 0; i < xfrQryResults.getCount(); i++) {
                    assetIds.put((String) xfrQryResults.getElement(i).getProperty("ASSETID"));
                }
            }
            result.put("assetIds", assetIds);
        } catch (Exception e) {
            String message = "Exception occurred while handling REST request for SOE " + this.getClass().getName() + ":"
                    + e.getMessage();
            this.serverLog.addMessage(1, 500, message);
            return ServerUtilities.sendError(500,
                    "Unable to complete operation",
                    new String[]{e.getMessage()})
                    .getBytes("utf-8");
        }

        return result.toString().getBytes("utf-8");
    }

    /**
     * Returns a list of customers serviced by the specified transformer
     */
    private byte[] getCustomers(JSONObject operationInput, String outputFormat, JSONObject requestPropertiesJSON,
                                java.util.Map<String, String> responseProperties) throws Exception {

        responseProperties.put("Content-Type", "application/json");
        JSONObject result = new JSONObject();

        if (this.unDataset == null) {
            String message = "No Utility Network found.";
            this.serverLog.addMessage(1, 500, message);
            return ServerUtilities.sendError(0, message, null).getBytes("utf-8");
        }

        try {
            String xfrAssetID = operationInput.getString("transformerAssetId");
            if (xfrAssetID != null && xfrAssetID.length() > 0)
            {
                Integer assetNumber = operationInput.getInt("transformerAssetId");
                if (assetNumber == null) {
                    String message = "Unable to parse 'transformerAssetId'.";
                    this.serverLog.addMessage(1, 500, message);
                    return ServerUtilities.sendError(500, message, null).getBytes("utf-8");
                }
                else
                    xfrAssetID = assetNumber.toString();
            }

            // Get the transformer feature global ID from the asset ID
            String xfrWhereClause = "ASSETGROUP=" + MV_XFR_ASSETGROUP.toString() + " AND ASSETID='" + xfrAssetID +"'";
            IStringArray xfrOutFields = new StrArray();
            xfrOutFields.add("GLOBALID");
            xfrOutFields.add("ASSETTYPE");
            xfrOutFields.add("SHAPE");

            IPropertySetArray xfrQryResults = queryDevices(xfrWhereClause, xfrOutFields);

            if(xfrQryResults == null || xfrQryResults.getCount() == 0) {
                String message = "No medium voltage transformer found for asset id: " + xfrAssetID;
                this.serverLog.addMessage(1, 500, message);
                return ServerUtilities.sendError(500, message, null).getBytes("utf-8");
            }

            String xfrGlobalID = (String)xfrQryResults.getElement(0).getProperty("GLOBALID");
            short xfrAssetType = (short)xfrQryResults.getElement(0).getProperty("ASSETTYPE");

            // Execute a downstream trace to find all customer low voltage service points
            IStringArray lvsGlobalIDs = findLVServicePoints(xfrGlobalID);

            // Prepare response JSON
            JSONObject xfrInfo = new JSONObject();
            xfrInfo.put("ASSETID", xfrAssetID);
            xfrInfo.put("GLOBALID", xfrGlobalID);
            xfrInfo.put("ASSETGROUP", MV_XFR_ASSETGROUP);
            xfrInfo.put("ASSETTYPE", xfrAssetType);

            com.esri.arcgis.system.JSONObject xfrPointJsonTemp = new com.esri.arcgis.system.JSONObject();
            IGeometry xfrPoint = (IGeometry)xfrQryResults.getElement(0).getProperty("SHAPE");
            JSONConverterGeometry geoSerializer = new JSONConverterGeometry();
            geoSerializer.queryJSONGeometry(xfrPoint, false, (com.esri.arcgis.system.IJSONObject)xfrPointJsonTemp);

            String xfrPointJsonStr = xfrPointJsonTemp.toJSONString(null);
            JSONObject xfrPointJson = new JSONObject(xfrPointJsonStr);

            xfrInfo.put("geometry", xfrPointJson);

            result.put("transformer", xfrInfo);

            JSONArray servicePoints = new JSONArray();

            // Get additional service point info if any returned from trace
            if (lvsGlobalIDs != null && lvsGlobalIDs.getCount() > 0)
            {
                // Query devices to get more details about service points
                String lvsGlobalIDList = "";

                for(int i=0;i<lvsGlobalIDs.getCount();i++)
                {
                    lvsGlobalIDList += (i == 0) ? "" : ",";
                    lvsGlobalIDList += "'" + lvsGlobalIDs.getElement(i) + "'";
                }

                String lvsWhereClause = "GLOBALID IN (" + lvsGlobalIDList + ")";
                IStringArray lvsOutFields = new StrArray();
                lvsOutFields.add("GLOBALID");
                lvsOutFields.add("ASSETTYPE");
                lvsOutFields.add("ASSETGROUP");
                lvsOutFields.add("ASSETID");
                lvsOutFields.add("SHAPE");
                IPropertySetArray lvsQryResults = queryDevices(lvsWhereClause, lvsOutFields);

                // Get customer details
                if (lvsQryResults != null && lvsQryResults.getCount() > 0)
                {
                    for (int i = 0; i < lvsQryResults.getCount(); i++)
                    {
                        IPropertySet lvs = lvsQryResults.getElement(i);
                        JSONObject servicePoint = new JSONObject();
                        servicePoint.put("GLOBALID", (String)lvs.getProperty("GLOBALID"));
                        servicePoint.put("ASSETID", (String)lvs.getProperty("ASSETID"));
                        servicePoint.put("ASSETGROUP", (int)lvs.getProperty("ASSETGROUP"));
                        servicePoint.put("ASSETTYPE", (int)(short)lvs.getProperty("ASSETTYPE"));

                        IGeometry lvsPoint = (IGeometry)lvs.getProperty("SHAPE");
                        com.esri.arcgis.system.JSONObject lvsPointJsonTemp = new com.esri.arcgis.system.JSONObject();
                        geoSerializer.queryJSONGeometry(lvsPoint, false, (com.esri.arcgis.system.IJSONObject)lvsPointJsonTemp);

                        String lvsPointJsonStr = lvsPointJsonTemp.toJSONString(null);
                        JSONObject lvsPointJson = new JSONObject(lvsPointJsonStr);
                        servicePoint.put("geometry",lvsPointJson);

                        JSONObject customerInfo = getCustomerInfoJSON();
                        servicePoint.put("customerInfo", customerInfo);
                        servicePoints.put(servicePoint);
                    }
                }
            }
            result.put("servicePoints",servicePoints);

            return result.toString().getBytes("utf-8");

        } catch (Exception e) {
            String message = "Exception occurred while handling REST request for SOE " + this.getClass().getName() + ":"
                    + e.getMessage();

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String eTrace = sw.toString();

            this.serverLog.addMessage(1, 500, message + " Stack trace: " + eTrace);
            return ServerUtilities.sendError(500,
                    "Unable to complete operation",
                    new String[]{e.getMessage()})
                    .getBytes("utf-8");
        }
    }

    /*************************************************************************************
     * UN Tracing SOE methods:
     *************************************************************************************/
    /*
     * This method returns the current utility network properties
     */
    private IPropertySet getUNProperties() throws IOException {
        IPropertySet unProps = new PropertySet();

        // Open UN
        IBaseNetworkProxy bn = new IBaseNetworkProxy(this.unDataset);

        // Get data element
        IDatasetComponentProxy dsComponent = new IDatasetComponentProxy(this.unDataset);
        IDEDataset deDS = dsComponent.getDataElement();
        IDEBaseNetworkProxy deBN = new IDEBaseNetworkProxy(deDS);
        unProps.setProperty("proVersion", deBN.getProVersion());
        unProps.setProperty("schemaGeneration", deBN.getSchemaGeneration());
        unProps.setProperty("userIdentity", deBN.getUserIdentity());
        unProps.setProperty("creationTime", deBN.getCreationTime().toString());

        // Get topology
        IBaseNetworkTopology bnTopo = new IBaseNetworkTopologyProxy(bn);
        unProps.setProperty("hasValidNetworkTopology", bnTopo.hasValidNetworkTopology());

        return unProps;
    }

    /*
     * This method queries the device layer and returns a set of features
     */
    private IPropertySetArray queryDevices(String whereClause, IStringArray outFields) throws Exception {
        PropertySetArray results = new PropertySetArray();

        int deviceLayerId = this.soeUtil.getLayerIdByName(this.mapService, DEVICE_LAYER_NAME);

        ILayerDescriptions layerDescs = mapService.getServerInfo(mapService.getDefaultMapName()).getDefaultMapDescription().getLayerDescriptions();
        ILayerDescription layerDesc = (ILayerDescription)layerDescs.getElement(deviceLayerId);
        LayerResultOptions layerResOpt = new LayerResultOptions();
        layerResOpt.setIncludeGeometry(true);
        layerResOpt.setReturnFieldNamesInResults(true);

        layerDesc.setLayerResultOptionsByRef(layerResOpt);
        IMapTableDescription tableDesc = (IMapTableDescription)layerDesc;
        IQueryResultOptions resultOptions = new QueryResultOptions();
        resultOptions.setFormat(esriQueryResultFormat.esriQueryResultRecordSetAsObject);
        IQueryFilter filter = new QueryFilter();
        if(whereClause == null || whereClause.length() == 0) {
            whereClause = "1=1";
        }
        filter.setWhereClause(whereClause);
        for(int i=0;i<outFields.getCount();i++)
        {
            filter.addField(outFields.getElement(i));
        }

        IQueryResult qry = mapService.queryData(mapService.getDefaultMapName(), tableDesc, filter, resultOptions);
        RecordSet rs = (RecordSet)qry.getObject();
        ICursor rc = rs.getCursor(false);
        IRow resultFeature;
        while ((resultFeature = rc.nextRow()) != null)
        {
            IPropertySet values = new PropertySet();
            for(int i=0;i<outFields.getCount();i++)
            {

                values.setProperty(outFields.getElement(i), resultFeature.getValue(resultFeature.getFields().findField(outFields.getElement(i))));
            }
            results.add(values);
        }
        Cleaner.release(rc);

        return results;
    }

    /*
     * This method executes a utility network trace to find all the low voltage service points
     * serviced by the specified transformer and returns the service points global IDs
     */
    private IStringArray findLVServicePoints(String xfrGlobalID) throws IOException {
        //Get required Utility Network interfaces
        IBaseNetworkProxy unBaseNetwork = new IBaseNetworkProxy(unDataset);
        IDatasetComponentProxy datasetComponent = new IDatasetComponentProxy(unDataset);
        IDEDataset deDataset = datasetComponent.getDataElement();
        IDEBaseNetworkProxy deBaseNetwork = new IDEBaseNetworkProxy(deDataset);
        IDEUtilityNetworkProxy deUtilityNetwork =  new IDEUtilityNetworkProxy(deBaseNetwork);
        IDataElement deElement = (IDataElement)deDataset;

        //Create and initialize network tracer
        IUtilityNetworkQuery unQry = unBaseNetwork.createQuery();
        ITracer unTracer = unBaseNetwork.createTracer();
        unTracer.initialize(unQry, deElement);

        // Add transformer as trace starting point
        IStringArray startGUID = new StrArray();
        startGUID.add(xfrGlobalID);
        ILongArray startTerm = new LongArray();
        startTerm.add(MV_XFR_TERMINAL_ID);

        unTracer.addTraceLocationForJunctionFeatures(esriTraceLocationType.esriTLTStartingPoint, startGUID, startTerm);

        // Configure trace parameters
        UNTraceConfiguration traceConfig = new UNTraceConfiguration();

        traceConfig.setIgnoreBarriersAtStartingPoints(true);
        traceConfig.setIncludeContainers(false);
        traceConfig.setIncludeBarriers(false);
        traceConfig.setIncludeContent(true);
        traceConfig.setIncludeIsolated(false);
        traceConfig.setIncludeStructures(false);
        traceConfig.setIncludeUpToFirstSpatialContainer(false);
        traceConfig.setDomainNetworkName(DOMAIN_NETWORK);
        traceConfig.setTierName(MV_TIER_NAME);
        traceConfig.setTargetTierName(MV_TIER_NAME);
        traceConfig.setTraversabilityScope(esriTraversabilityScope.esriTSJunctionsAndEdges);
        traceConfig.setFilterScope(esriTraversabilityScope.esriTSJunctionsAndEdges);
        traceConfig.setValidateConsistency(false);


        // Add output filter to only return low voltage service points
        IArray outFilters = new Array();
        for (int i=0;i<LV_SERVICE_ASSETTYPES.length;i++)
        {
            UNOutputFilter outFilter = new UNOutputFilter();
            outFilter.setNetworkSourceID(DEVICE_SOURCE_ID);
            outFilter.setAssetGroupCode(LV_SERVICE_ASSETGROUP);
            outFilter.setAssetTypeCode(LV_SERVICE_ASSETTYPES[i]);
            outFilters.add(outFilter);
        }

        traceConfig.setOutputFiltersByRef(outFilters);

        ITraceConfiguration tc = new ITraceConfigurationProxy(traceConfig);
        //unTracer.setTraceConfigurationByRef(traceConfig);
        unTracer.setTraceConfigurationByRef(tc);

        // Execute the trace
        long[][] jEid = new long[1][1];
        long[][] eEid = new long[1][1];

        unTracer.trace(esriUtilityNetworkTraceType.esriUNTTDownstream, jEid, eEid);

        // Get features from returned elements
        IUNTraceResults unTraceResults = new IUNTraceResultsProxy(unTracer);

        ILongArray[] junctionNetworkSourceIDs = new LongArray[1];
        IStringArray[] junctionGlobalIDs = new StrArray[1];
        ILongArray[] junctionObjectIDs = new LongArray[1];
        ILongArray[] junctionTerminalIDs = new LongArray[1];
        ILongArray[] junctionAssetGroupCodes = new LongArray[1];
        ILongArray[] junctionAssetTypeCodes = new LongArray[1];
        ILongArray[] edgeNetworkSourceIDs = new LongArray[1];
        IStringArray[] edgeGlobalIDs = new StrArray[1];
        ILongArray[] edgeObjectIDs = new LongArray[1];
        ILongArray[] edgeAssetGroupCodes = new LongArray[1];
        ILongArray[] edgeAssetTypeCodes = new LongArray[1];

        unTraceResults.getTraceResultFeatures(junctionNetworkSourceIDs, junctionGlobalIDs, junctionObjectIDs, junctionTerminalIDs, junctionAssetGroupCodes, junctionAssetTypeCodes, edgeNetworkSourceIDs, edgeGlobalIDs, edgeObjectIDs, edgeAssetGroupCodes, edgeAssetTypeCodes);

        return junctionGlobalIDs[0];
    }

    private JSONObject getCustomerInfoJSON()
    {
        JSONObject customerInfo = new JSONObject();

        customerInfo.put("customerID", "XXXXXXXXXX");
        customerInfo.put("firstName", "XXXXXXXXXX");
        customerInfo.put("lastName", "XXXXXXXXXX");
        customerInfo.put("phoneNumber", "XXX-XXX-XXXX");
        customerInfo.put("address", "XXXXXXXXXX");
        customerInfo.put("city", "XXXXXXXXXX");
        customerInfo.put("zipCode", "XXXXX");
        return customerInfo;
    }

}
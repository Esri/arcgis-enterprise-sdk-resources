package entsdksamples.soe;

/*
COPYRIGHT 2018 ESRI
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

import java.io.IOException;
import java.util.HashMap;

import com.esri.arcgis.carto.IMapLayerInfo;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerDataAccess;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.geodatabase.FeatureClass;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.ServerUtilities;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Simple REST SOE With Capabilities", description = "Java Simple REST SOE With 2 Capabilities", defaultSOAPCapabilities = { "BusServices" }, allSOAPCapabilities = {
	"BusServices", "TrainServices" })
public class JavaSimpleRESTSOEWithCapabilities implements
	IServerObjectExtension, IRESTRequestHandler {
    private static final long serialVersionUID = 1L;
    private IServerObjectHelper soHelper;
    private ILog serverLog;
    private IMapServerDataAccess mapServerDataAccess;

    public JavaSimpleRESTSOEWithCapabilities() throws Exception {
	super();
    }

    /****************************************************************************************************************************
     * IServerObjectExtension methods: This is a mandatory interface that must
     * be supported by all SOEs. This interface is used by the Server Object to
     * manage the lifetime of the SOE and includes two methods: init() and
     * shutdown(). The Server Object cocreates the SOE and calls the init()
     * method handing it a back reference to the Server Object via the Server
     * Object Helper argument. The Server Object Helper implements a weak
     * reference on the Server Object. The extension can keep a strong reference
     * on the Server Object Helper (for example, in a member variable) but
     * should not keep a strong reference on the Server Object.
     * 
     * The log entries are merely informative and completely optional.
     ****************************************************************************************************************************/
    /**
     * init() is called once, when the instance of the SOE is created.
     */
    public void init(IServerObjectHelper soh) throws IOException,
	    AutomationException {
	/*
	 * An SOE should get the Server Object from the Server Object Helper in
	 * order to make any method calls on the Server Object and release the
	 * reference after making the method calls.
	 */
	this.soHelper = soh;
	this.serverLog = ServerUtilities.getServerLogger();
	this.mapServerDataAccess = (IMapServerDataAccess) this.soHelper
		.getServerObject();
	serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName()
		+ " SOE.");
    }

    /**
     * shutdown() is called once when the Server Object's context is being shut
     * down and is about to go away.
     */
    public void shutdown() throws IOException, AutomationException {
	/*
	 * The SOE should release its reference on the Server Object Helper.
	 */
	serverLog.addMessage(3, 200, "Shutting down "
		+ this.getClass().getName() + " SOE.");
	this.soHelper = null;
	this.serverLog = null;
	this.mapServerDataAccess = null;
    }

    /**
     * Method for implementing REST operation "findBusStationById"'s
     * functionality.
     * 
     * @param String
     *            operationInput JSON representation of input
     * @return String JSON representation of output
     */
    private byte[] findBusStationById(String capabilitiesList,
	    JSONObject operationInput, String outputFormat,
	    JSONObject requestPropertiesJSON) throws Exception {
	JSONObject json = new JSONObject();
	json.put("stationName",
		"Bus Station " + operationInput.get("busStationId"));
	json.put("caps", capabilitiesList);
	
	return json.toString().getBytes("utf-8");
    }

    /**
     * Method for implementing REST operation "findTrainStationById"'s
     * functionality.
     * 
     * @param String
     *            operationInput JSON representation of input
     * @return String JSON representation of output
     */
    private byte[] findTrainStationById(String capabilitiesList,
	    JSONObject operationInput, String outputFormat,
	    JSONObject requestPropertiesJSON) throws Exception {
	JSONObject json = new JSONObject();
	json.put("stationName",
		"Train Station " + operationInput.get("trainStationId"));
	json.put("caps", capabilitiesList);
	
	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns JSON representation of root resource's description.
     * 
     * @return String JSON representation of root resource's description.
     */
    private byte[] getRootResource(String outputFormat,
	    JSONObject requestPropertiesJSON,
	    java.util.Map<String, String> responsePropertiesMap)
	    throws Exception {
	JSONObject json = new JSONObject();
	json.put("name", "Java Simple REST SOE With Capabilities");
	json.put("layerInfo", getLayerInfo());

	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns layer info for map that hosts this SOE
     * 
     * @return
     * @throws Exception
     */
    private JSONObject getLayerInfo() throws Exception {
	IMapServer ms = (IMapServer) this.soHelper.getServerObject();
	IMapServerInfo mapServerInfo = ms.getServerInfo(ms.getDefaultMapName());
	IMapLayerInfos layerInfos = mapServerInfo.getMapLayerInfos();

	JSONObject json = new JSONObject();
	int count = layerInfos.getCount();
	json.put("count", count);
	JSONArray layerArray = new JSONArray();
	for (int i = 0; i < count; i++) {
	    IMapLayerInfo layerInfo = layerInfos.getElement(i);
	    JSONObject layerJSON = new JSONObject();
	    layerJSON.put("name", layerInfo.getName());
	    layerJSON.put("type", layerInfo.getType());
	    int id = layerInfo.getID();
	    layerJSON.put("id", id);
	    layerJSON.put("description", layerInfo.getDescription());
	    if (layerInfo.isFeatureLayer()) {
		FeatureClass fc = new FeatureClass(
			this.mapServerDataAccess.getDataSource("", id));
		layerJSON.put("featureCount", fc.featureCount(null));
	    }

	    layerArray.put(i, layerJSON);
	}
	json.put("layers", layerArray);

	return json;
    }

    /**
     * Returns JSON representation of NumberOfBusStations resource's
     * description. This resource is not a collection.
     * 
     * @return String JSON representation of NumberOfBusStations resource's
     *         description.
     */
    private byte[] getSubResourceNumberOfBusStations(String capabilitiesList,
	    String outputFormat, JSONObject requestPropertiesJSON)
	    throws Exception {
	JSONObject json = new JSONObject();
	json.put("numberOfBusStations", "100");
	
	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns JSON representation of NumberOfTrainStations resource's
     * description. This resource is not a collection.
     * 
     * @return String JSON representation of NumberOfTrainStations resource's
     *         description.
     */
    private byte[] getSubResourceNumberOfTrainStations(String capabilitiesList,
	    String outputFormat, JSONObject requestPropertiesJSON)
	    throws Exception {
	JSONObject json = new JSONObject();
	json.put("numberOfTrainStations", "100");

	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns JSON representation of specified resource.
     * 
     * @return String JSON representation of specified resource.
     */
    private byte[] getResource(String capabilitiesList, String resourceName,
	    String outputFormat, JSONObject requestPropertiesJSON,
	    java.util.Map<String, String> responsePropertiesMap)
	    throws Exception {
	serverLog
		.addMessage(1, 200, "Caps in getResource: " + capabilitiesList);
	
	
	byte[] response = null;
	if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
	    response = getRootResource(outputFormat, requestPropertiesJSON,
		    responsePropertiesMap);
	} else if (resourceName.equals("NumberOfBusStations")) {
	    if (capabilitiesList.contains("BusServices")) {
		response = getSubResourceNumberOfBusStations(capabilitiesList,
			outputFormat, requestPropertiesJSON);
	    } else {
		response = ServerUtilities
			.sendError(
				406,
				"Unable to access subresource " + resourceName
					+ ". ",
				new String[] { "Please contact the ArcGIS Server admin to enable access to this subresource." })
			.getBytes("utf-8");
	    }
	} else if (resourceName.equals("NumberOfTrainStations")) {
	    if (capabilitiesList.contains("TrainServices")) {
		response = getSubResourceNumberOfTrainStations(capabilitiesList,
			outputFormat, requestPropertiesJSON);
	    } else {
		response = ServerUtilities
			.sendError(
				406,
				"Unable to access subresource " + resourceName
					+ ". ",
				new String[] { "Please contact the ArcGIS Server admin to enable access to this subresource." })
			.getBytes("utf-8");
	    }
	}
	responsePropertiesMap.put("Content-Type", "application/json");

	return response;
    }

    /**
     * Invokes specified REST operation on specified REST resource
     * 
     * @param capabilitiesList
     * @param resourceName
     * @param operationName
     * @param operationInput
     * @param outputFormat
     * @param requestPropertiesMap
     * @param responsePropertiesMap
     * @return byte[]
     */
    private byte[] invokeRESTOperation(String capabilitiesList,
	    String resourceName, String operationName, String operationInput,
	    String outputFormat, JSONObject requestPropertiesJSON,
	    java.util.Map<String, String> responsePropertiesMap)
	    throws Exception {

	serverLog.addMessage(1, 200, "Caps in invokeRESTOperation: "
		+ capabilitiesList);
	JSONObject operationInputAsJSON = new JSONObject(operationInput);
	byte[] operationOutput = null;

	if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
	    if (operationName.equalsIgnoreCase("findBusStationById")) {
		serverLog.addMessage(1, 200, "Caps contains BusServices: "
			+ capabilitiesList.contains("BusServices"));
		if (capabilitiesList.contains("BusServices")) {
		    operationOutput = findBusStationById(capabilitiesList,
			    operationInputAsJSON, outputFormat,
			    requestPropertiesJSON);
		} else {
		    operationOutput = ServerUtilities
			    .sendError(
				    406,
				    "Unable to access operation "
					    + operationName + ". ",
				    new String[] { "Please contact the ArcGIS Server admin to enable access to this operation." })
			    .getBytes("utf-8");
		}
	    } else if (operationName.equalsIgnoreCase("findTrainStationById")) {
		serverLog.addMessage(1, 200, "Caps contains TrainServices: "
			+ capabilitiesList.contains("TrainServices"));
		if (capabilitiesList.contains("TrainServices")) {
		    operationOutput = findTrainStationById(capabilitiesList,
			    operationInputAsJSON, outputFormat,
			    requestPropertiesJSON);
		} else {
		    operationOutput = ServerUtilities
			    .sendError(
				    406,
				    "Unable to access operation "
					    + operationName + ". ",
				    new String[] { "Please contact the ArcGIS Server admin to enable access to this operation." })
			    .getBytes("utf-8");
		}

	    }
	} else
	// if non existent sub-resource specified, report error
	{
	    operationOutput = ServerUtilities.sendError(500,
		    "No sub-resource by name " + resourceName + " found.",
		    new String[] { "No details specified." }).getBytes(
		    "utf-8");
	}
	responsePropertiesMap.put("Content-Type", "application/json");
	
	return operationOutput;
    }

    /**
     * Handles REST request by determining whether an operation or resource has
     * been invoked and then forwards the request to appropriate Java methods,
     * along with request and response properties
     */
    public byte[] handleRESTRequest(String capabilities, String resourceName,
	    String operationName, String operationInput, String outputFormat,
	    String requestProperties, String[] responseProperties)
	    throws IOException, AutomationException {
	serverLog.addMessage(1, 200, "Caps in handleRESTRequest: "
		+ capabilities);
	// parse request properties, create a map to hold request properties
	JSONObject requestPropertiesJSON = new JSONObject(requestProperties);

	// create a response properties map to hold properties of response
	java.util.Map<String, String> responsePropertiesMap = new HashMap<String, String>();

	try {
	    // if no operationName is specified send description of specified
	    // resource
	    byte[] response;
	    if (operationName.length() == 0) {
		response = getResource(capabilities, resourceName,
			outputFormat, requestPropertiesJSON,
			responsePropertiesMap);
	    } else
	    // invoke REST operation on specified resource
	    {
		response = invokeRESTOperation(capabilities, resourceName,
			operationName, operationInput, outputFormat,
			requestPropertiesJSON, responsePropertiesMap);
	    }

	    // handle response properties
	    JSONObject responsePropertiesJSON = new JSONObject(responsePropertiesMap);
	    responseProperties[0] = responsePropertiesJSON.toString();

	    return response;
	} catch (Exception e) {
	    responsePropertiesMap.put("Content-Type", "application/json");
	    return ServerUtilities.sendError(500,
		    "Exception occurred: " + e.getMessage(),
		    new String[] { "No details specified." }).getBytes(
		    "utf-8");
	}
    }

    /**
     * This method returns the resource hierarchy of a REST based SOE in JSON
     * format.
     */
    public String getSchema() throws IOException, AutomationException {
	try {
	    JSONObject _RESTSOEWithCapabilities = ServerUtilities
		    .createResource("JavaSimpleRESTSOEWithCapabilities",
			    "Java Simple REST SOE With Capabilities", false,
			    false);
	    JSONArray _RESTSOEWithCapabilities_OpArray = new JSONArray();
	    _RESTSOEWithCapabilities_OpArray.put(ServerUtilities
		    .createOperation("findBusStationById", "busStationId",
			    "json", false));
	    _RESTSOEWithCapabilities_OpArray.put(ServerUtilities
		    .createOperation("findTrainStationById", "trainStationId",
			    "json", false));
	    _RESTSOEWithCapabilities.put("operations",
		    _RESTSOEWithCapabilities_OpArray);
	    JSONArray _RESTSOEWithCapabilities_SubResourceArray = new JSONArray();
	    JSONObject _NumberOfBusStations = ServerUtilities.createResource(
		    "NumberOfBusStations", "NumberOfBuses description", false,
		    false);
	    _RESTSOEWithCapabilities_SubResourceArray.put(_NumberOfBusStations);
	    JSONObject _NumberOfTrainStations = ServerUtilities.createResource(
		    "NumberOfTrainStations", "NumberOfTrain description",
		    false, false);
	    _RESTSOEWithCapabilities_SubResourceArray
		    .put(_NumberOfTrainStations);
	    _RESTSOEWithCapabilities.put("resources",
		    _RESTSOEWithCapabilities_SubResourceArray);
	    return _RESTSOEWithCapabilities.toString();
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	return null;

    }

}
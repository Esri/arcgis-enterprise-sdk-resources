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
import java.util.Map;

import com.esri.arcgis.carto.IMapLayerInfo;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerDataAccess;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IObjectConstruct;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.ServerUtilities;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Simple REST SOE With Properties", description = "Java Simple REST SOE With Properties", properties = {
	"layerType=feature", "returnFormat=json", "maxNumFeatures=100",
	"isEditable=false" })
public class JavaSimpleRESTSOEWithProperties implements IServerObjectExtension,
	IObjectConstruct, IRESTRequestHandler {
    private static final long serialVersionUID = 1L;
    private IServerObjectHelper soHelper;
    private ILog serverLog;
    private IMapServerDataAccess mapServerDataAccess;
    private IMapLayerInfos layerInfos;

    // properties
    private String layerType, returnFormat;
    private int maxNumFeatures;
    private boolean isEditable;

    public JavaSimpleRESTSOEWithProperties() throws Exception {
	super();
    }

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
	IMapServer ms = (IMapServer) this.mapServerDataAccess;
	IMapServerInfo mapServerInfo = ms.getServerInfo(ms.getDefaultMapName());
	this.layerInfos = mapServerInfo.getMapLayerInfos();

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

    /****************************************************************************************************************************
     * IObjectConstruct: This is an optional interface for SOEs. If your SOE
     * includes configuration properties or requires any additional
     * initialization logic, you need to implement the IObjectConstruct
     * interface.
     * 
     * This interface includes a single method called construct().
     ****************************************************************************************************************************/
    /**
     * construct() is called only once, when the SOE is created, after
     * IServerObjectExtension.init() is called. This method hands back the
     * configuration properties for the SOE as a property set. You should
     * include any expensive initialization logic for your SOE within your
     * implementation of construct().
     */
    public void construct(IPropertySet propertySet) throws IOException {
	String lType = (String) propertySet.getProperty("layerType");
	if (lType.equalsIgnoreCase("feature")
		|| lType.equalsIgnoreCase("raster")
		|| lType.equalsIgnoreCase("all")) {
	    this.layerType = lType;
	} else {
	    this.layerType = "feature";
	}

	String format = (String) propertySet.getProperty("returnFormat");
	if (format.equalsIgnoreCase("json") || format.equalsIgnoreCase("html")
		|| format.equalsIgnoreCase("text")) {
	    this.returnFormat = format;
	} else {
	    this.returnFormat = "json";
	}

	int maxFeatures = Integer.parseInt((String) propertySet
		.getProperty("maxNumFeatures"));
	if (maxFeatures <= 0) {
	    this.maxNumFeatures = 100;
	} else {
	    this.maxNumFeatures = maxFeatures;
	}

	String editableStringValue = (String) propertySet
		.getProperty("isEditable");
	if (editableStringValue.equalsIgnoreCase("true")
		|| editableStringValue.equalsIgnoreCase("false")) {
	    this.isEditable = Boolean.parseBoolean((String) propertySet
		    .getProperty("isEditable"));
	} else {
	    this.isEditable = false;
	}
    }

    /**
     * Returns JSON representation of root resource's description.
     * 
     * @return String JSON representation of root resource's description.
     */
    private byte[] getRootResource() throws Exception {
	JSONObject json = new JSONObject();
	json.put("name", "Java Simple REST SOE With Properties");
	json.put(
		"description",
		"Simple REST SOE with 1 sub-resource called \"layers\" and 1 property called layerType.");
	json.put(
		"usage",
		"The \"layers\" subresource returns all layers of a certain type in the map service.\n"
			+ "The layerType property defines the type of layers are returned by the \"layers\" subresource.");
	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns JSON representation of specified sub-resource.
     * 
     * @return String JSON representation of specified resource.
     */
    private byte[] getSubresourceLayers(Map<String, String> responsePropertiesMap) throws Exception {
	JSONObject json = new JSONObject();
	json.put("layers", new String(getLayersInfo()));
	
	responsePropertiesMap.put("Content-Type", "application/json");
	
	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns properties and their current values
     * 
     * @return String JSON representation of specified resource.
     */
    private byte[] getSubresourceProperties(Map<String, String> responsePropertiesMap) throws Exception {
	JSONObject json = new JSONObject();
	json.put("layerType", this.layerType);
	json.put("returnFormat", this.returnFormat);
	json.put("maxNumFeatures", this.maxNumFeatures);
	json.put("isEditable", this.isEditable);
	
	responsePropertiesMap.put("Content-Type", "application/json");
	
	return json.toString().getBytes("utf-8");
    }

    /**
     * Returns info about layers in associated map service
     * 
     * @return
     * @throws Exception
     */
    public byte[] getLayersInfo() throws Exception {
	String aoLayerType = "";
	if (this.layerType.equalsIgnoreCase("feature")) {
	    aoLayerType = "Feature Layer";
	} else if (this.layerType.equalsIgnoreCase("raster")) {
	    aoLayerType = "Raster Layer";
	} else if (this.layerType.equalsIgnoreCase("dataset")) {
	    aoLayerType = "Network Dataset Layer";
	} else {
	    return ServerUtilities
		    .sendError(
			    1,
			    "Propety layerType has invalid value.",
			    new String[] { "Acceptable values are \"feature\", \"raster\", and \"dataset\"." })
		    .getBytes("utf-8");
	}

	int layerTypeCount = 0;
	JSONObject json = new JSONObject();
	JSONArray layerArray = new JSONArray();
	for (int i = 0; i < layerInfos.getCount(); i++) {
	    IMapLayerInfo layerInfo = layerInfos.getElement(i);
	    String lType = layerInfo.getType();
	    if (lType.equalsIgnoreCase(aoLayerType)) {
		JSONObject layerJSON = new JSONObject();
		layerJSON.put("name", layerInfo.getName());
		layerJSON.put("type", lType);
		layerJSON.put("id", layerInfo.getID());
		layerJSON.put("description", layerInfo.getDescription());
		layerArray.put(layerTypeCount, layerJSON);
		layerTypeCount++;
	    }
	}
	json.put(this.layerType + " layerCount", layerTypeCount);
	json.put("layersInfo", layerArray);

	if (this.returnFormat.equalsIgnoreCase("html")) {
	    return convertJSONToHTML(json).getBytes();
	} else if (this.returnFormat.equalsIgnoreCase("text")) {
	    return convertJSONToText(json).getBytes();
	}

	return json.toString().getBytes();
    }

    /**
     * Converts JSON to plain text
     * 
     * @param json
     * @return String
     */
    private String convertJSONToText(JSONObject json) {
	String text = this.layerType + " layerCount: "
		+ json.getString(this.layerType + " layerCount");
	text += " Layers:";

	JSONArray layersArray = json.getJSONArray("layersInfo");
	for (int i = 0; i < layersArray.length(); i++) {
	    JSONObject layerJSON = layersArray.getJSONObject(i);
	    text += "Name: " + layerJSON.getString("name") + " Type: "
		    + layerJSON.getString("type") + " ID: "
		    + layerJSON.getString("id") + " Description: "
		    + layerJSON.getString("description") + ";";
	}

	return text;
    }

    /**
     * Converts JSON to HTML
     * 
     * @param json
     * @return
     */
    private String convertJSONToHTML(JSONObject json) {
	String html = "<table>";

	html += "<tr>" + "<td>" + this.layerType + " layerCount</td>" + "<td>"
		+ json.getString(this.layerType + " layerCount") + "</td>"
		+ "</tr>";

	html += "<tr><td colspan='2'>Layers</td></tr>";
	JSONArray layersArray = json.getJSONArray("layersInfo");
	for (int i = 0; i < layersArray.length(); i++) {
	    JSONObject layerJSON = layersArray.getJSONObject(i);
	    html += "<tr>" + "<td>" + layerJSON.getString("name") + "</td>"
		    + "<td>" + "<b>Type:</b> " + layerJSON.getString("type")
		    + "<br>" + "<b>ID:</b>" + layerJSON.getString("id")
		    + "<br>" + "<b>Description:</b>"
		    + layerJSON.getString("description") + "</td>" + "</tr>";
	}

	html += "</table>";

	return html;
    }

    /**
     * Returns JSON representation of specified resource.
     * 
     * @return String JSON representation of specified resource.
     */
    private byte[] getResource(String resourceName, Map<String, String> responsePropertiesMap) throws Exception {
	if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
	    return getRootResource();
	} else if (resourceName.equalsIgnoreCase("layers")) {
	    return getSubresourceLayers(responsePropertiesMap);
	} else if (resourceName.equalsIgnoreCase("properties")) {
	    return getSubresourceProperties(responsePropertiesMap);
	}

	return null;
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
    public byte[] handleRESTRequest(String capabilities, String resourceName,
	    String operationName, String operationInput, String outputFormat,
	    String requestProperties, String[] responseProperties)
	    throws IOException, AutomationException {

	Map<String, String> responsePropertiesMap = new HashMap<String, String>();
	try {
	    // if no operationName is specified send description of specified
	    // resource
	    byte[] response = null;	    
	    if (operationName.length() == 0) {
		response = getResource(resourceName, responsePropertiesMap);
	    } else {
		response = ServerUtilities.sendError(1,
			"No operations defined for this SOE",
			new String[] { "No details specified." })
			.getBytes("utf-8");
	    }
	    
	    //collect response properties that may have changed in subresources or operations
	    JSONObject responsePropertiesJSON = new JSONObject(responsePropertiesMap);
	    responseProperties[0] = responsePropertiesJSON.toString();
		
	    return response;
	} catch (Exception e) {
	    this.serverLog.addMessage(1, 500, e.getMessage());
	    return ServerUtilities.sendError(1,
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
	    JSONObject _SimpleRESTSOEWithProperties = ServerUtilities
		    .createResource("JavaSimpleRESTSOEWithProperties",
			    "JavaSimpleRESTSOEWithProperties description",
			    false, false);
	    JSONArray _SimpleRESTSOEWithProperties_SubResourceArray = new JSONArray();
	    JSONObject _layers = ServerUtilities.createResource("layers",
		    "layers description", false, false);
	    JSONObject _properties = ServerUtilities.createResource(
		    "properties", "properties and their current values", false,
		    false);
	    _SimpleRESTSOEWithProperties_SubResourceArray.put(_layers);
	    _SimpleRESTSOEWithProperties_SubResourceArray.put(_properties);
	    _SimpleRESTSOEWithProperties.put("resources",
		    _SimpleRESTSOEWithProperties_SubResourceArray);
	    return _SimpleRESTSOEWithProperties.toString();
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
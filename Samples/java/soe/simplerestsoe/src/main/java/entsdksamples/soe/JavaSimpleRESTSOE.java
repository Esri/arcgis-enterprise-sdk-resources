package entsdksamples.soe;


/*
COPYRIGHT 2020 ESRI
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

import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.ServerUtilities;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.carto.IMapServerDataAccess;
import com.esri.arcgis.carto.IMapLayerInfo;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.system.IObjectActivate;
import com.esri.arcgis.system.IObjectConstruct;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.server.SOIHelper;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.geodatabase.FeatureClass;

import java.util.HashMap;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Simple REST SOE", description = "Java Simple REST SOE",
			 properties = "" ,
			 allSOAPCapabilities = "" ,
			 defaultSOAPCapabilities = "" ,
			supportsSharedInstances = true)

public class JavaSimpleRESTSOE implements IServerObjectExtension, IRESTRequestHandler, IObjectActivate
	 {

	private static final long serialVersionUID = 1L;
	private ILog serverLog;
	private IServerObjectHelper soHelper;
	private IMapLayerInfos layerInfos;
	private IPropertySet pPropertyset;
	private SOIHelper soiHelper;

	public JavaSimpleRESTSOE()throws Exception{
        super();
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
	public void init(IServerObjectHelper soh)throws IOException,AutomationException{
        /*
         * An SOE should retrieve a weak reference to the Server Object from the Server Object Helper in
         * order to make any method calls on the Server Object and release the
         * reference after making the method calls.
         */
        this.soHelper=soh;
        this.serverLog=ServerUtilities.getServerLogger();

		serverLog.addMessage(3, 200, "Beginning init in "
				+ this.getClass().getName() + " SOE.");

        IMapServer ms=(IMapServer)this.soHelper.getServerObject();
        IMapServerInfo mapServerInfo=ms.getServerInfo(ms.getDefaultMapName());
        this.layerInfos=mapServerInfo.getMapLayerInfos();
		this.soiHelper = new SOIHelper();

        this.serverLog.addMessage(3,200,"Initialized "+this.getClass().getName()+" SOE.");
    }

	/**
	 * shutdown() is called once when the Server Object's context is being shut down and is
	 * about to go away.
	 */
	public void shutdown()throws IOException,AutomationException{
        /*
         * The SOE should release its reference on the Server Object Helper.
         */
		serverLog.addMessage(3, 200, "Shutting down "
				+ this.getClass().getName() + " SOE.");
        this.serverLog.addMessage(3,200,"Shutting down "+this.getClass().getName()+" SOE.");
        this.serverLog=null;
    }

	/****************************************************************************************************************************
	 * IObjectActivate:
	 * This is an optional interface for SOEs.
	 * If your SOE requires special logic to run each time a request is made to the SOE and response is returned, you need to 	 * implement IObjectActivate.
	 *
	 * This interface includes 2 methods, activate() and deactivate().
	 ****************************************************************************************************************************/
	/**
	 * activate() is called each time a request is made to the SOE. Any logic implemented in this
	 * method should be trivial if possible, for best performance.
	 */
	public void activate() {
		//TODO: add logic to handle your SOE's activation here
	}

	/**
	 * deactivate() is called each time a response is sent to the client. Any logic implemented in this
	 * method should be trivial if possible, for best performance.
	 */
	public void deactivate() {
		//TODO: add logic to handle your SOE's deactivation here
	}

	
	/**
	 * Returns JSON representation of specified resource.
	 * @return String JSON representation of specified resource.
	 */
	private byte[] getResource(String resourceName, Map<String, String> responsePropertiesMap)throws Exception{
		if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
			return getRootResource();
		} else if (resourceName.equalsIgnoreCase("layers")) {
			return getSubresourceLayers(responsePropertiesMap, "layers");
		}else if (resourceName.equalsIgnoreCase("serviceproperties")) {
			return getSubresourceLayers(responsePropertiesMap, "serviceproperties");
		}
        return null;
    }

	/**
	 * Returns JSON representation of the root resource.
	 * @return String JSON representation of the root resource.
	 */
	private byte[] getRootResource() throws Exception {
        JSONObject json=new JSONObject();
        json.put("name","root resource");
		json.put(
				"description",
				"JavaSimpleRESTSOE SOE with 1 sub-resource called \"layers\" and 1 operation called \"getLayerCountByType\".");
		json.put(
				"usage",
				"The \"layers\" subresource returns all layers in the map service.\n"
						+ "The \"getLayerCountByType\" operation returns a count of layer of specified type. It accepts one of the following values as input: \"feature\", \"raster\", "
						+ "\"dataset\", and \"all\".");

        return json.toString().getBytes("utf-8");
    }

	/**
	 * Returns JSON representation of layers resource.
	 * This resource is not a collection.
	 * @return String JSON representation of layers resource.
	 */
	private byte[] getSubresourceLayers(Map<String, String> responsePropertiesMap, String resourcename) throws Exception {

		JSONObject json = new JSONObject();
		if (resourcename == "layers")
		{
			json.put("layers", getLayersInfoAsJSON());
		}
		else if (resourcename == "serviceproperties")
		{
			json.put("serviceproperties", getPropertiesInfoAsJSON());
		}
		responsePropertiesMap.put("Content-Type", "application/json");
		return json.toString().getBytes("utf-8");
    }

	/**
	 * Returns info about layers in associated map service
	 *
	 * @return
	 * @throws Exception
	 */
	public JSONObject getLayersInfoAsJSON() throws Exception {
		JSONObject json = new JSONObject();

		int count = this.layerInfos.getCount();
		json.put("layerCount", count);
		JSONArray layerArray = new JSONArray();
		for (int i = 0; i < count; i++) {
			IMapLayerInfo layerInfo = layerInfos.getElement(i);
			JSONObject layerJSON = new JSONObject();
			layerJSON.put("name", layerInfo.getName());
			String layerType = layerInfo.getType();
			layerJSON.put("type", layerType);
			int id = layerInfo.getID();
			layerJSON.put("id", id);
			layerJSON.put("description", layerInfo.getDescription());
			if (layerInfo.isFeatureLayer()) {
				IMapServerDataAccess mapServerDataAccess = (IMapServerDataAccess) this.soHelper.getServerObject();
				IMapServer ms = (IMapServer)mapServerDataAccess;
				FeatureClass fc = new FeatureClass(mapServerDataAccess.getDataSource(ms.getDefaultMapName(), id));
				layerJSON.put("featureCount", fc.featureCount(null));
			}

			layerArray.put(i, layerJSON);
		}
		json.put("layersInfo", layerArray);
		return json;
	}

	public JSONObject getPropertiesInfoAsJSON() throws Exception {
		JSONObject json = new JSONObject();
		this.pPropertyset = this.soiHelper.queryConfigurationProperties(this.soHelper.getServerObject().getConfigurationName(), this.soHelper.getServerObject().getTypeName());
		json.put("MaxRecordCount", this.pPropertyset.getProperty("MaxRecordCount").toString());
		json.put("MaxImageHeight", this.pPropertyset.getProperty("MaxImageHeight").toString());
		json.put("MaxImageWidth", this.pPropertyset.getProperty("MaxImageWidth").toString());
		json.put("PhysicalOutputDirectory", this.pPropertyset.getProperty("outputDir").toString());
		json.put("PhysicalCacheDirectory", this.pPropertyset.getProperty("cacheDir").toString());
		return json;
	}

	/**
	 * Returns number of layers in associate map service, based on layer type
	 * provided
	 *
	 * @param operationInput
	 * @return
	 */
	private byte[] getLayerCountByType(JSONObject operationInput, java.util.Map<String, String> responsePropertiesMap)
			throws Exception {
		String type = operationInput.getString("type");

		JSONObject json = new JSONObject();

		int count = 0;
		if (type != null && !type.isEmpty()) {
			String aoType = "";
			if (type.equalsIgnoreCase("all")) {
				count = layerInfos.getCount();
			} else if (type.equalsIgnoreCase("feature")) {
				aoType = "Feature Layer";
			} else if (type.equalsIgnoreCase("raster")) {
				aoType = "Raster Layer";
			} else if (type.equalsIgnoreCase("dataset")) {
				aoType = "Network Dataset Layer";
			}

			for (int i = 0; i < layerInfos.getCount(); i++) {
				if (layerInfos.getElement(i).getType().equalsIgnoreCase(aoType)) {
					count++;
				}
			}

			json.put("count", count);

			responsePropertiesMap.put("Content-Type", "application/json");

			return json.toString().getBytes();
		} else {
			throw new Exception(
					"Invalid layer type provided. Available types are: \"all\", \"feature\", \"raster\", \"dataset\".");
		}
	}

	/**
	 * Invokes specified REST operation on specified REST resource
	 * @param capabilitiesList
	 * @param resourceName
	 * @param operationName
	 * @param operationInput
	 * @param outputFormat
	 * @param requestProperties
	 * @param responsePropertiesMap
	 * @return byte[]
	 */
	private byte[] invokeRESTOperation(String capabilitiesList,
									   String resourceName, String operationName, String operationInput,
									   String outputFormat, String requestProperties,
									   Map<String, String> responsePropertiesMap) throws Exception {
		byte[] operationOutput = null;

		JSONObject operationInputAsJSON = new JSONObject(operationInput);

		if (resourceName.isEmpty()) {
			if (operationName.equalsIgnoreCase("getLayerCountByType")) {
				operationOutput = getLayerCountByType(operationInputAsJSON, responsePropertiesMap);
			}
		} else
		// if non existent sub-resource specified, report error
		{
			this.serverLog.addMessage(1, 500, "No sub-resource by name "
					+ resourceName + " found.");
			return ServerUtilities.sendError(500,
					"No sub-resource by name " + resourceName + " found.",
					new String[] { "No details specified." })
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
	public byte[] handleRESTRequest(String capabilities, String resourceName,
									String operationName, String operationInput, String outputFormat,
									String requestProperties, String[] responseProperties)
			throws IOException, AutomationException {
		/*
		 * This method handles REST requests by determining whether an operation
		 * or resource has been invoked and then forwards the request to
		 * appropriate methods.
		 */

		try {

			Map<String, String> responsePropertiesMap = new HashMap<String, String>();

			// if no operationName is specified send description of specified
			// resource
			byte[] response = null;
			if (operationName.length() == 0) {
				response = getResource(resourceName, responsePropertiesMap);
			} else
			// invoke REST operation on specified resource
			{
				response = invokeRESTOperation(capabilities, resourceName,
						operationName, operationInput, outputFormat,
						requestProperties, responsePropertiesMap);
			}

			//collect response properties that may have changed in subresources or operations
			JSONObject responsePropertiesJSON = new JSONObject(responsePropertiesMap);
			responseProperties[0] = responsePropertiesJSON.toString();

			return response;
		} catch (Exception e) {
			this.serverLog.addMessage(1, 500, e.getMessage());
			return ServerUtilities.sendError(500,
					"Exception occurred: " + e.getMessage(),
					new String[] { "No details specified." }).getBytes("utf-8");
		}
	}

	/**
	 * This method returns the resource hierarchy of a REST based SOE in JSON format.
	 */
	public String getSchema()throws IOException,AutomationException{
        try{
			        	JSONObject javaSimpleRESTSOE = ServerUtilities.createResource("javaSimpleRESTSOE",
				"javaSimpleRESTSOE description",false,false);

			JSONArray _subResourcesArray = new JSONArray();
			_subResourcesArray.put(ServerUtilities.createResource("layers",
					"layers in associated map service", false, false));
			_subResourcesArray.put(ServerUtilities.createResource("serviceproperties",
					"service properties associated map service", false, false));
			javaSimpleRESTSOE.put("resources", _subResourcesArray);

			JSONArray _OpArray = new JSONArray();
			_OpArray.put(ServerUtilities.createOperation("getLayerCountByType",
					"type", "json", false));
			javaSimpleRESTSOE.put("operations", _OpArray);

			return javaSimpleRESTSOE.toString();
        }catch(JSONException e){
	        e.printStackTrace();
        }
        return null;
    }
}
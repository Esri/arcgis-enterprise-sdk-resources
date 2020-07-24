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

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.esri.arcgis.carto.*;
import com.esri.arcgis.geodatabase.*;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polygon;
import com.esri.arcgis.geometry.SpatialReferenceEnvironment;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.ServerUtilities;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.system.IObjectActivate;
import com.esri.arcgis.system.IObjectConstruct;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.server.SOIHelper;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.IRESTRequestHandler;

import java.util.HashMap;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Find Nearby Features REST SOE",
			description = "Java Find Nearby Features REST SOE",
			 properties = "" ,
			 allSOAPCapabilities = "" ,
			 defaultSOAPCapabilities = "" ,
			supportsSharedInstances = true)

public class JavaFindNearbyFeaturesRESTSOE implements IServerObjectExtension, IRESTRequestHandler
	 {

	private static final long serialVersionUID = 1L;
	private ILog serverLog;
	private IServerObjectHelper soHelper;
	private IMapServerDataAccess mapServerDataAccess;

	public JavaFindNearbyFeaturesRESTSOE()throws Exception{
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
		this.mapServerDataAccess = (IMapServerDataAccess)ms;

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
		this.mapServerDataAccess = null;
	}

	 /*************************************************************************************
	  * Handles logic to find nearby features
	  *************************************************************************************/
	 private JSONObject findNearbyFeatures(int layerId, Point location,
										   double distance) throws Exception {
		 IMapServer ms = (IMapServer) this.mapServerDataAccess;
		 IMapServerInfo mapServerInfo = ms.getServerInfo(ms.getDefaultMapName());
		 String mapName = ms.getDefaultMapName();
		 IMapLayerInfos layerInfos = mapServerInfo.getMapLayerInfos();
		 int layerCount = layerInfos.getCount();

		 // check if layer id is within bounds and points to a feature layer
		 if (layerId >= layerCount) {
			 throw new Exception(
					 "Invalid layer id provided. Please provide a layer id between 0 and "
							 + (layerCount - 1)
							 + " as associated map service contains only "
							 + layerCount + " layers.");
		 } else {
			 if (!layerInfos.getElement(layerId).isFeatureLayer()) {
				 throw new Exception(
						 "The layer id provided does not point to a feature layer. Please provide id of a feature layer.");
			 }
		 }

		 FeatureClass fc = new FeatureClass(
				 this.mapServerDataAccess.getDataSource(mapName, layerId));
		 if (fc.getFeatureType() == esriFeatureType.esriFTSimple) {
			 // calculate buffer pg for user specified point
			 Polygon buffer = (Polygon) location.buffer(distance);
			 if (buffer != null) {
				 // create an intersects spatial filter
				 SpatialFilter spatialFilter = new SpatialFilter();
				 spatialFilter.setGeometryByRef(buffer);
				 spatialFilter
						 .setSpatialRel(esriSpatialRelEnum.esriSpatialRelIntersects);

				 // set result properties
				 QueryResultOptions qResultOptions = new QueryResultOptions();
				 qResultOptions
						 .setFormat(esriQueryResultFormat.esriQueryResultRecordSetAsObject);

				 // query for relevant features using spatial filter
				 QueryResult queryResult = (QueryResult) ms.queryData(mapName,
						 getTableDescription(mapServerInfo, layerId),
						 spatialFilter, qResultOptions);
				 RecordSet rs = (RecordSet) queryResult.getObject();

				 // send result back to client
				 JSONArray featuresArray = new JSONArray();
				 FeatureCursor cursor = new FeatureCursor(rs.getCursor(true));
				 IFeature feature = cursor.nextFeature();
				 int resultCount = 0;
				 if (feature != null) {
					 Fields fields = (Fields) feature.getFields();
					 int fieldCount = fields.getFieldCount();

					 while (feature != null) {
						 JSONObject json = new JSONObject();
						 for (int i = 2; i < fieldCount; i++) {
							 Field field = (Field) fields.getField(i);
							 String fieldName = field.getName();
							 json.put(fieldName, feature.getValue(fields
									 .findField(fieldName)));
						 }

						 featuresArray.put(json);
						 feature = cursor.nextFeature();
						 resultCount++;
					 }
				 }

				 JSONObject json = new JSONObject();
				 json.put("resultCount", resultCount);
				 json.put("features", featuresArray);

				 return json;
			 }
		 }

		 return null;
	 }

	 /**
	  * Returns layer info for map that hosts this SOE
	  *
	  * @return
	  * @throws Exception
	  */
	 private JSONObject getLayerInfo() throws Exception {
		 IMapServer ms = (IMapServer) this.mapServerDataAccess;
		 IMapServerInfo mapServerInfo = ms.getServerInfo(ms.getDefaultMapName());
		 IMapLayerInfos layerInfos = mapServerInfo.getMapLayerInfos();

		 JSONObject json = new JSONObject();
		 int count = layerInfos.getCount();
		 json.put("count", count);
		 JSONArray layerArray = new JSONArray();
		 for (int i = 0; i < count; i++) {
			 IMapLayerInfo layerInfo = layerInfos.getElement(i);
			 if (layerInfo.isFeatureLayer()) {
				 JSONObject layerJSON = new JSONObject();
				 layerJSON.put("name", layerInfo.getName());
				 int id = layerInfo.getID();
				 layerJSON.put("id", id);
				 layerJSON.put("description", layerInfo.getDescription());

				 FeatureClass fc = new FeatureClass(
						 this.mapServerDataAccess.getDataSource(ms.getDefaultMapName(), id));
				 layerJSON.put("featureCount", fc.featureCount(null));
				 layerArray.put(i, layerJSON);
			 }
		 }
		 json.put("featureLayers", layerArray);

		 return json;
	 }

	 /**
	  * Method for implementing REST operation "findNearbyFeatures"'s functionality.
	  *
	  * @param operationInput  operationInput JSON representation of input, including layerId,
	  *                        location, and spatialReference
	  * @return byte[] byte array of the output JSON
	  */
	 private byte[] findNearbyFeaturesRESTOp(JSONObject operationInput,
											 String outputFormat, JSONObject requestProperties,
											 Map<String, String> responsePropertiesMap) throws Exception {
		 int layerId = operationInput.getInt("layerId");
		 JSONObject locJSON = operationInput.getJSONObject("location");
		 JSONObject srJSON = locJSON.getJSONObject("spatialReference");

		 Point location = new Point();
		 location.setX(locJSON.getDouble("x"));
		 location.setY(locJSON.getDouble("y"));
		 SpatialReferenceEnvironment sre = new SpatialReferenceEnvironment();
		 location.setSpatialReferenceByRef(sre.createSpatialReference(Integer
				 .parseInt(srJSON.getString("wkid"))));

		 double distance = operationInput.getDouble("distance");

		 responsePropertiesMap.put("Content-Type", "application/json");

		 return findNearbyFeatures(layerId, location, distance).toString()
				 .getBytes();
	 }

	 /**
	  * Returns JSON representation of specified resource.
	  *
	  * @return String JSON representation of specified resource.
	  */
	 private byte[] getResource(String resourceName, Map<String, String> responsePropertiesMap) throws Exception {
		 if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
			 return getRootResource();
		 } else if (resourceName.matches("featureLayers")) {
			 return getLayersSubResource(responsePropertiesMap);
		 }

		 return null;
	 }

	 /**
	  * Returns JSON representation of an element of sr1 collection. This element
	  * is represented by resourceId parameter.
	  *
	  * @return String JSON representation of sr1 resource's description.
	  */
	 private byte[] getLayersSubResource(Map<String, String> responsePropertiesMap) throws Exception {

		 responsePropertiesMap.put("Content-Type", "application/json");
		 return getLayerInfo().toString(4).getBytes("utf-8");
	 }

	 /**
	  * Returns JSON representation of root resource's description.
	  *
	  * @return String JSON representation of root resource's description.
	  */
	 private byte[] getRootResource() throws Exception {
		 JSONObject json = new JSONObject();
		 json.put("name", "Java Find Nearby Features SOE");
		 json.put(
				 "description",
				 "This REST SOE returns features in an area surrounding the specified location. It has 1 sub-resource and 1 operation.");
		 json.put(
				 "usage",
				 "The \"featureLayers\" sub-resource displays all feature layers in associated map service.\n"
						 + "The \"findNearbyFeatures\" operation takes in an integer layerId, location in JSON format (for example: "
						 + "{\"x\":7643274.467,\"y\":682498.886,\"spatialReference\":{\"wkid\":102726}}), and the "
						 + "distance in feet (for example: 500), and returns nearby features.");
		 return json.toString().getBytes("utf-8");

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

		// retrieve request properties as a JSONObject
		JSONObject requestPropertiesJSON = new JSONObject(requestProperties);

		// create a Map to hold response properties
		if (resourceName.isEmpty()) {
			if (operationName.equalsIgnoreCase("findNearbyFeatures")) {
				operationOutput = findNearbyFeaturesRESTOp(
						operationInputAsJSON, outputFormat,
						requestPropertiesJSON, responsePropertiesMap);
			}
		} else
		// if non existent sub-resource specified, report error
		{
			return ServerUtilities.sendError(0,
					"No sub-resource by name " + resourceName + " found.",
					new String[] { "No details specified." }).getBytes("utf-8");
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
			serverLog.addMessage(1, 200,
					"Exception occurred: " + e.getMessage() + " in "
							+ this.getClass().getName() + " SOE.");
			return ServerUtilities.sendError(0,
					"Exception occurred: " + e.getMessage(),
					new String[] { "No details specified." }).getBytes("utf-8");
		}
	}

	/**
	 * This method returns the resource hierarchy of a REST based SOE in JSON format.
	 */
	public String getSchema() throws IOException, AutomationException {
		try {
			JSONObject _OneSRWith1Op = ServerUtilities.createResource(
					"JavaFindNearbyFeaturesSOE",
					"Java FindNearby Features SOE", false, false);
			JSONArray _OneSRWith1Op_SubResourceArray = new JSONArray();
			_OneSRWith1Op_SubResourceArray.put(ServerUtilities.createResource(
					"featureLayers",
					"feature layers is associated map service", false, false));
			_OneSRWith1Op.put("resources", _OneSRWith1Op_SubResourceArray);

			JSONArray _OpArray = new JSONArray();
			_OpArray.put(ServerUtilities.createOperation("findNearbyFeatures",
					"layerId, location, distance", "json", false));
			_OneSRWith1Op.put("operations", _OpArray);

			return _OneSRWith1Op.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	 /*************************************************************************************
	  * SOE Util methods
	  *************************************************************************************/
	 /**
	  *
	  */
	 private LayerDescription getTableDescription(IMapServerInfo mapServerInfo,
												  int layerID) {
		 try {
			 IMapLayerInfos layerInfos = mapServerInfo.getMapLayerInfos();
			 IMapServerInfo mapServerInfo3 = (IMapServerInfo) mapServerInfo;
			 ILayerDescriptions layerDescriptions = mapServerInfo3
					 .getDefaultMapDescription().getLayerDescriptions();
			 int ldCount = layerInfos.getCount();
			 for (int i = 0; i < ldCount; i++) {
				 LayerDescription layerDescription = (LayerDescription) layerDescriptions
						 .getElement(i);

				 if (layerDescription.getID() == layerID) {
					 LayerResultOptions resultOptions = new LayerResultOptions();

					 GeometryResultOptions geomResultOptions = new GeometryResultOptions();
					 geomResultOptions.setDensifyGeometries(true);

					 resultOptions
							 .setGeometryResultOptionsByRef(geomResultOptions);

					 layerDescription.setLayerResultOptionsByRef(resultOptions);

					 return layerDescription;
				 }
			 }
		 } catch (AutomationException e) {
			 e.printStackTrace();
		 } catch (UnknownHostException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }

		 return null;
	 }
}
package entsdksamples.soi;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.esri.arcgis.carto.ILayerDescription;
import com.esri.arcgis.carto.ILayerDescriptions;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.MapDescription;
import com.esri.arcgis.carto.MapServer;
import com.esri.arcgis.carto.MapServerInfo;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObject;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.SOIHelper;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IMessage;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.IRequestHandler;
import com.esri.arcgis.system.IRequestHandler2;
import com.esri.arcgis.system.IWebRequestHandler;
import com.esri.arcgis.system.IXMLSerializeData;
import com.esri.arcgis.system.LongArray;
import com.esri.arcgis.system.Message;
import com.esri.arcgis.system.ServerUtilities;

/*
 * For an SOE to act as in intercepter, it needs to implement all request handler interfaces
 * IRESTRequestHandler, IWebRequestHandler, IRequestHandler2, IRequestHandler now the SOE/SOI can
 * intercept all types of calls to ArcObjects or custom SOEs
 *
 * This example demonstrates the power of SOI's. We can log all requests to ArcObjects or custom
 * SOEs and even control access to different map layers based on user roles. In this example we use
 * the permission.json file (stores authorized layers for each user role). At runtime we use this
 * information to control access to different layers. Map Service operations; export, identify and
 * find are configured to honor layer level access.
 *
 * Note:
 *
 * 1: Only REST handler calls are being intercepted to provide layer level access. All other handler
 * need to be implemented.
 *
 * 2: This example only implements layer level access on Map Service operations. Image Service
 * operations need to be implemented.
 *
 * 3: This example is incomplete and is intended to teach patters to writing SOIs and is not designed
 * to work with custom SOE's or with service capabilities (for e.g. Mobile Data Access etc.) enabled.
 */

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Layer Access SOI",
		description = "SOI to control access to different layers",
		interceptor = true,
		servicetype = "MapService",
		supportsSharedInstances = true)
public class LayerAccessSOI implements IServerObjectExtension, IRESTRequestHandler, IWebRequestHandler,
		IRequestHandler2, IRequestHandler {
	private static final String ARCGISHOME_ENV = "AGSSERVER";
	private static final long serialVersionUID = 1L;
	private ILog serverLog;
	private IServerObject so;
	private SOIHelper soiHelper;

	/*
	 * Map used to store permission information. Permission rules for each service is read form the
	 * permisson.json file.
	 */
	private Map<String, String> servicePermissionMap = null;

	public LayerAccessSOI() throws Exception {
		super();
	}

	/**
	 * init() is called once, when the instance of the SOE/SOI is created.
	 *
	 * @param soh the IServerObjectHelper
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	public void init(IServerObjectHelper soh) throws IOException, AutomationException {
		/*
		 * An SOE should retrieve a weak reference to the Server Object from the Server Object Helper in
		 * order to make any method calls on the Server Object and release the reference after making
		 * the method calls.
		 */
		this.serverLog = ServerUtilities.getServerLogger();
		String arcgisHome = getArcGISHomeDir();
		/* Still null - throw exception */
		if(arcgisHome == null) {
			serverLog.addMessage(1, 200,"Could not get ArcGIS home directory. Check if environment variable " + ARCGISHOME_ENV + " is set.");
			throw new IOException("Could not get ArcGIS home directory. Check if environment variable " + ARCGISHOME_ENV + " is set.");
		}
		if(arcgisHome != null && !arcgisHome.endsWith(File.separator)) {
			arcgisHome += File.separator;
		}
		// Set Log Level to 4 to log the detailed message
		this.serverLog.addMessage(4, 200,"ArcGIS home directory: " + arcgisHome);
		this.so = soh.getServerObject();
		//Load the SOI helper.
		this.soiHelper = new SOIHelper(arcgisHome + "XmlSchema" + File.separator + "MapServer.wsdl");
		getPermissionFromFile(this.so);
		this.serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName() + " SOE.");
	}

	/**
	 * This method is called to handle REST requests.
	 *
	 * SOEs allow the user to extend base functionality for ArvGIS Map Services and Image Services.
	 * For a <b>Map Service</b> the supported REST operations are: find, identify, export. For an
	 * <b>Image Service</b> the supported REST operations are: identify, export, etc.
	 *
	 * In this example we demonstrate layer level access on REST operators for a Map Service.
	 * Authorized layers for each user role is read from the permission.json file. When user queries a
	 * particular operation, the request is manipulated to allow information retrieval only from
	 * authorized layers.
	 *
	 * Note: This example only implements layer level access on Map Service operations. Image Service
	 * operations need to be implemented.
	 *
	 * @param capabilities the capabilities
	 * @param resourceName the resource name
	 * @param operationName the operation name
	 * @param operationInput the operation input
	 * @param outputFormat the output format
	 * @param requestProperties the request properties
	 * @param responseProperties the response properties
	 * @return the response as byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public byte[] handleRESTRequest(String capabilities, String resourceName, String operationName,
									String operationInput, String outputFormat, String requestProperties, String[] responseProperties)
			throws IOException, AutomationException {
		/*
		 * Log message with server.
		 */
		/*
		 * You can use different log codes to set up different log levels.
		 *
		 * For example:
		 * Use log level 1 for Error Messages.
		 * Use log level 2 for Warning Messages.
		 * Use log level 3 for Normal Messages.
		 * Use log level 4 for Detailed Messages.
		 * Use log level 5 for Debug Messages.
		 *
		 * Note: See http://resources.arcgis.com/en/help/arcobjects-java/api/arcobjects/com/esri/arcgis/system/ILog.html for more information.
		 */
		serverLog.addMessage(3, 200, "Request received in Layer Access SOI for handleRESTRequest");
		serverLog.addMessage(3, 200, "capabilities - " + capabilities);
		serverLog.addMessage(3, 200, "resourceName - " + resourceName);
		serverLog.addMessage(3, 200, "operationName - " + operationName);
		serverLog.addMessage(3, 200, "operationInput - " + operationInput);
		serverLog.addMessage(3, 200, "outputFormat - " + outputFormat);
		serverLog.addMessage(3, 200, "requestProperties - " + requestProperties);
		/*
		 * Perform layer level access on REST operations.
		 *
		 * Note: When resourceName, operationName and operationInput are empty its the Map Service
		 * resource (or getInfo) call
		 */
		try {
			/*
			 * Get roles for the user making the request.
			 */
			Set<String> userRoleSet = ServerUtilities.getGroupInfo();
			/*
			 * Generate a set of authorized layers for the user
			 */
			HashSet<String> authorizedLayerSet = null;
			String authorizedLayers = "";
			{
				for (String role : userRoleSet) {
					if (servicePermissionMap.containsKey(so.getConfigurationName() + "." + so.getTypeName() + "." + role)) {
						authorizedLayers +=
								"," + servicePermissionMap.get(so.getConfigurationName() + "." + so.getTypeName() + "." + role);
					}
				}
				// remove spaces
				authorizedLayers = authorizedLayers.replaceAll("\\s+", "");
				// Create a set of authorized layers
				List<String> authorizedLayerList = null;
				try {
					authorizedLayerList = Arrays.asList(authorizedLayers.split(","));
					authorizedLayerSet = new HashSet<String>(authorizedLayerList);
				} catch (Exception ignore) {
				}
				// remove blank layer form set
				authorizedLayerSet.remove("");
				authorizedLayers = authorizedLayerSet.toString().replaceAll("\\[|]|\\s+","");
			}

			/*
			 * Convert input parameters to JSON for easier manipulation.
			 */
			JSONObject operationInputJson = new JSONObject(operationInput);

			switch (RESTOperations.get(operationName)) {
				case EXPORT_MAP: {
					/*
					 * Manipulate request parameters to control access to different layers.
					 *
					 * Syntax to specify which layers appear on the exported map:
					 * "[show | hide | include | exclude]:layerId1,layerId2"
					 *
					 * example: "show:1,2"
					 *
					 * Here we replace user requested layers with layers he has access to achieve layer level
					 * security.
					 */
					if(authorizedLayers.isEmpty()) {
						return new JSONObject().put("error", new JSONObject().put("code", 404).put("message", "Not Found"))
								.toString().getBytes();
					}
					operationInputJson.put("layers", "show:" + authorizedLayers);
				}
				break;
				case GENERATE_KML:
				case FIND: {
					/*
					 * Get the layers value from the input parameters JSON.
					 */
					String requestedLayers = operationInputJson.getString("layers");

					/*
					 * Syntax to specify which layers to find information from:
					 * "layers=<layerId1>,<layerId2>"
					 *
					 * Manipulate the requested layers by matching against authorized layers. Remove layers
					 * from the request user is not authorized to access.
					 */
					operationInputJson.put(
							"layers",
							removeUnauthorizedLayersFromRequestedLayers(requestedLayers.replaceAll("\\s+", ""),
									authorizedLayers));
				}
				break;
				case IDENTIFY: {
					/*
					 * Get the layers value from the input parameters JSON.
					 */
					String requestedLayers = operationInputJson.getString("layers");

					/*
					 * Syntax to specify which layers to query information from:
					 * "layers=[top | visible | all]:layerId1,layerId2"
					 *
					 * Manipulate the requested layers by matching against authorized layers. Remove
					 * layers from the request user is not authorized to access.
					 */
					/*
					 * If the requestedLayers value is "top" or "all" replace with all layers user has
					 * access to.
					 *
					 * If the requestedLayers value is "visible" + requested layers filter this based on
					 * what layers user has access to.
					 */
					if (requestedLayers == null || requestedLayers.length() == 0 || requestedLayers.startsWith("top")
							|| requestedLayers.startsWith("all")) {
						/*
						 * Convert request to only authorized layers.
						 */
						operationInputJson.put("layers", "visible:" + authorizedLayers);
					} else if (requestedLayers.startsWith("visible")) {
						/*
						 * Get all layers user wants to query.
						 */
						String[] requestedLayersInParts = requestedLayers.split(":");
						/*
						 * Verify user is authorized to access requested layers, if not convert request to
						 * only authorized layers.
						 */
						operationInputJson.put(
								"layers",
								"visible:"
										+ removeUnauthorizedLayersFromRequestedLayers(requestedLayersInParts[1].replaceAll("\\s+", ""),
										authorizedLayers));
					} else {
						operationInputJson.put("layers", "visible:" + authorizedLayers);
					}

				}
				break;
				case GENERATE_RENDERER:
				case QUERY_RELATED_RECORDS:
				case QUERY: {
					/*
					 * Get the requested layer from resource name. example: layers/0
					 */
					String[] resourceNameInParts = resourceName.split("/");
					String requestedLayerId = resourceNameInParts[1];

					/*
					 * Check if user has access to the requested layer. If not, throw an error
					 */
					if (!authorizedLayerSet.contains(requestedLayerId)) {
						return new JSONObject().put("error", new JSONObject().put("code", 404).put("message", "Not Found"))
								.toString().getBytes();
					}
				}
				break;
				default: {
					if (resourceName.length() > 0) {
						/*
						 * If operationName and operationInput are empty and we have a resource name than it
						 * could be Feature or All Layer or Legend operation
						 *
						 * Context : ServiceName/MapServer/Layer/FeatureId
						 *
						 * The feature resource represents a single feature in a layer in a map service. For
						 * feature requests the resource name will get a value like 'layers/0/features/1', using
						 * this value we can perform layer level access.
						 *
						 * Feature resource has two child resources:
						 * 1. Attachment Infos (layers/0/features/1/attachments)
						 *  1.1 Attachment (layers/0/features/1/attachments/0)
						 * 2. HTML Popup (layers/0/features/1/htmlPopup)
						 *
						 * Layer resource has these child resources:
						 * 1. Query
						 * 2. Query Related Records
						 */

						/*
						 * Make sure resourceName always starts with '/' for consistency.
						 */
						String tempResourceName = "";
						if(!resourceName.startsWith("/")) {
							tempResourceName = "/" + resourceName;
						} else {
							tempResourceName = resourceName;
						}

						/*
						 * Get the requested layer from resource name.
						 * Example: /layers/0
						 */
						String operationFromResource = "";
						String requestedLayerId = "";
						try {
							String[] resourceNameInParts = tempResourceName.split("/");
							operationFromResource = resourceNameInParts[1];
							requestedLayerId = resourceNameInParts[2];
						} catch (Exception ignore) {
						}

						if (operationFromResource.equalsIgnoreCase("legend") || operationFromResource.equalsIgnoreCase("layers")) {
							/*
							 * Authorize request for a particular layer.
							 * Example: /layers/0
							 */
							if (requestedLayerId.length() > 0) {
								/*
								 * Check if user has access to the requested layer. If not, throw an error.
								 */
								if (!authorizedLayerSet.contains(requestedLayerId)) {
									return new JSONObject()
											.put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
											.getBytes();
								}
							} else {
								/*
								 * Its the GetLegend or GetAllLayerandTables call, perform post processing.
								 *
								 * Find the correct delegate to forward the request to.
								 */
								IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
								if (restRequestHandler != null) {
									/*
									 * Get the response from Map Server SO.
									 */
									byte[] response =
											restRequestHandler.handleRESTRequest(capabilities, resourceName, operationName, operationInput,
													outputFormat, requestProperties, responseProperties);
									String responseStr = new String(response);

									/*
									 * Perform filtering on the response based on access to different layers.
									 */
									responseStr = filterJSONGetLegendandGetAllLayersResponse(new String(response), authorizedLayers, operationFromResource);
									return responseStr.getBytes();
								}
							}
						}
					} else {
						/*
						 * Its the GetInfo call, perform post processing.
						 *
						 * Find the correct delegate to forward the request to.
						 */
						IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
						if (restRequestHandler != null) {
							/*
							 * Get the response from Map Server SO.
							 */
							byte[] response =
									restRequestHandler.handleRESTRequest(capabilities, resourceName, operationName, operationInput,
											outputFormat, requestProperties, responseProperties);
							String responseStr = new String(response);

							/*
							 * Perform filtering on the response based on access to different layers.
							 */
							responseStr = filterJSONGetInfoResponse(new String(response), authorizedLayers);
							serverLog.addMessage(3, 200, "REST getInfo response :: " + responseStr);
							return responseStr.getBytes();
						}
					}
				}
			}

			/*
			 * Replace with formatted input parameters based on user access to different layers.
			 */
			operationInput = operationInputJson.toString();
			serverLog.addMessage(3, 200, "modified operation input :: " + operationInput);
		} catch (Exception e) {
			// Log error with server
			serverLog.addMessage(1, 200, "Exception logged in SOI - " + e.toString());
			/*
			 * Note: Depending on the use case we can still forward the call to Server Object
			 */
			return new JSONObject()
					.put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
					.getBytes();
		}

		/*
		 * Find the correct delegate to forward the request to.
		 */
		IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
		if (restRequestHandler != null) {
			/*
			 * Return the response.
			 */
			byte[] response =
					restRequestHandler.handleRESTRequest(capabilities, resourceName, operationName, operationInput, outputFormat,
							requestProperties, responseProperties);
			serverLog.addMessage(3, 200, "rest response :: " + new String(response));
			return response;
		}

		return new JSONObject()
				.put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
				.getBytes();
	}

	/**
	 * This method is called to handle SOAP requests.
	 *
	 * Note: Layer level access on SOAP handler operation requests is not implemented.
	 *
	 * @param capabilities the capabilities
	 * @param request the request
	 * @return the response as String
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public String handleStringRequest(final String capabilities, final String request) throws IOException,
			AutomationException {
		// Log message with server
		serverLog.addMessage(3, 200, "Request received in Layer Access SOI for handleStringRequest");

		serverLog.addMessage(3, 200, "capabilities :: " + capabilities);
		serverLog.addMessage(3, 200, "request :: " + request);

		// Convert the XML request into a generic IMessage
		// Intercept the request and perform filtering
		// Recreate modified request back to the original request format
		String filteredRequest = filterStringRequest(request, RequestMode.STRING);
		// Forward the request to the appropriate delegate/handler
		IRequestHandler requestHandler = soiHelper.findRequestHandlerDelegate(so);
		if (requestHandler != null) {
			// Get the response
			String response = requestHandler.handleStringRequest(capabilities, filteredRequest);

			// Intercept the response and perform filtering
			String filteredResponse = filterStringRequest(response, RequestMode.STRING);
			if (filteredResponse != null) {
				response = filteredResponse;
			}
			serverLog.addMessage(3, 200, "soap response :: " + response);
			return response;
		}

		return null;
	}

	/**
	 * Apply filter to request parameters
	 *
	 * @param request
	 * @param mode
	 * @return RequestTypes
	 * @throws AutomationException
	 * @throws IOException
	 */
	private String filterStringRequest(String request, RequestMode mode) throws AutomationException, IOException {
		// 1. Get the name of the operation from inRequest
		// 2. For the operation that we care about, parse parameters
		// 3. Manipulate the parameters
		// 4. Re-construct the IMessage based on manipulated parameters
		// 5. Serialize the IMessage to original request format

		IMessage inRequest = SOIHelper.convertStringRequestToMessage(request);

		// Get operation name
		String name = inRequest.getName();

		// Generate a set of authorized layers for the user
		HashSet<String> authorizedLayerSet = null;
		{
			Set<String> userRoleSet = ServerUtilities.getGroupInfo();
			String authorizedLayers = "";
			for (String role : userRoleSet) {
				if (servicePermissionMap.containsKey(so.getConfigurationName() + "." + so.getTypeName() + "." + role)) {
					authorizedLayers +=
							"," + servicePermissionMap.get(so.getConfigurationName() + "." + so.getTypeName() + "." + role);
				}
			}
			// remove spaces
			authorizedLayers = authorizedLayers.replaceAll("\\s+", "");
			// Create a set of authorized layers
			List<String> authorizedLayerList = null;
			try {
				authorizedLayerList = Arrays.asList(authorizedLayers.split(","));
				authorizedLayerSet = new HashSet<String>(authorizedLayerList);
			} catch (Exception ignore) {
			}
		}

		// Apply filter only on those operations we care about
		if ("Find".equalsIgnoreCase(name)) {
			inRequest = filterLayerIds(inRequest, mode, authorizedLayerSet);
		} else if ("ExportMapImage".equalsIgnoreCase(name)) {
			inRequest = filterMapDescription(inRequest, mode, authorizedLayerSet);
		} else if ("Identify".equalsIgnoreCase(name)) {
			inRequest = filterLayerIds(inRequest, mode, authorizedLayerSet);
		} else if ("GetLegendInfo".equalsIgnoreCase(name)) {
			inRequest = filterLayerIds(inRequest, mode, authorizedLayerSet);
		} else if ("GetServerInfoResponse".equalsIgnoreCase(name)) {
			inRequest = filterGetServerInfoResponse(inRequest, mode, authorizedLayerSet);
		}
		else
			return request;

		// Recreate modified request back to the original request format
		RequestType requestout = new RequestType();
		requestout.stringRequest = SOIHelper.convertMessageToStringRequest(inRequest);

		String filteredRequest = requestout.stringRequest;
		if (filteredRequest == null) {
			filteredRequest = request;
		}
		return filteredRequest;
	}

	private byte[] filterBinaryRequest(byte[] request, RequestMode mode) throws AutomationException, IOException {
		// 1. Get the name of the operation from inRequest
		// 2. For the operation that we care about, parse parameters
		// 3. Manipulate the parameters
		// 4. Re-construct the IMessage based on manipulated parameters
		// 5. Serialize the IMessage to original request format

		IMessage inRequest = inRequest = SOIHelper.convertBinaryRequestToMessage(request);

		// Get operation name
		String name = inRequest.getName();

		// Generate a set of authorized layers for the user
		HashSet<String> authorizedLayerSet = null;
		{
			Set<String> userRoleSet = ServerUtilities.getGroupInfo();
			String authorizedLayers = "";
			for (String role : userRoleSet) {
				if (servicePermissionMap.containsKey(so.getConfigurationName() + "." + so.getTypeName() + "." + role)) {
					authorizedLayers +=
							"," + servicePermissionMap.get(so.getConfigurationName() + "." + so.getTypeName() + "." + role);
				}
			}
			// remove spaces
			authorizedLayers = authorizedLayers.replaceAll("\\s+", "");
			// Create a set of authorized layers
			List<String> authorizedLayerList = null;
			try {
				authorizedLayerList = Arrays.asList(authorizedLayers.split(","));
				authorizedLayerSet = new HashSet<String>(authorizedLayerList);
			} catch (Exception ignore) {
			}
		}

		// Apply filter only on those operations we care about
		if ("Find".equalsIgnoreCase(name)) {
			inRequest = filterLayerIds(inRequest, mode, authorizedLayerSet);
		} else if ("ExportMapImage".equalsIgnoreCase(name)) {
			inRequest = filterMapDescription(inRequest, mode, authorizedLayerSet);
		} else if ("Identify".equalsIgnoreCase(name)) {
			inRequest = filterLayerIds(inRequest, mode, authorizedLayerSet);
		} else if ("GetLegendInfo".equalsIgnoreCase(name)) {
			inRequest = filterLayerIds(inRequest, mode, authorizedLayerSet);
		} else if ("GetServerInfoResponse".equalsIgnoreCase(name)) {
			inRequest = filterGetServerInfoResponse(inRequest, mode, authorizedLayerSet);
		}
		else
			return request;

		// Recreate modified request back to the original request format
		RequestType requestout = new RequestType();
		requestout.byteArrayRequest = SOIHelper.convertMessageToBinaryRequest(inRequest);

		byte[] filteredRequest = requestout.byteArrayRequest;

		if (filteredRequest == null) {
			filteredRequest = request;
		}

		return filteredRequest;
	}
	/**
	 * Filter to apply layer level security to MapDescription
	 *
	 * @param inRequest
	 * @param mode
	 * @param authorizedLayerSet
	 * @return
	 * @throws AutomationException
	 * @throws IOException
	 */
	private IMessage filterMapDescription(IMessage inRequest, RequestMode mode, HashSet<String> authorizedLayerSet)
			throws AutomationException, IOException {
		// 1. Find the index of the MapDescription parameter
		// 2. Get the value for the interested parameter
		// 3. Manipulate it
		// 4. Put it back into IMessage

		// Get the parameters out from the request object
		int idx = -1;
		IXMLSerializeData inRequestData = inRequest.getParameters();
		// IXMLSerializeData inRequestData = inRequest.getParameters();
		idx = inRequestData.find("MapDescription");

		MapDescription md =
				(MapDescription) inRequestData.getObject(idx, inRequest.getNamespaceURI(),
						soiHelper.getSoapOperationParameterTypeName(inRequest.getName(), idx));
		// Manipulate the MapDescription to perform layer level security
		ILayerDescriptions lds = md.getLayerDescriptions();
		for (int i = 0; i < lds.getCount(); i++) {
			ILayerDescription ld = lds.getElement(i);
			if (!authorizedLayerSet.contains(Integer.toString(ld.getID()))) {
				ld.setVisible(false);
			}
		}

		// If binary request we dont have to create and copy in a new Message object
		if (mode == RequestMode.BYTE_ARRAY) {
			return inRequest;
		}

		// Create new request message
		IMessage modifiedInRequest = new Message();
		IXMLSerializeData modifiedInRequestData = modifiedInRequest.getParameters();
		// Set the correct namespace for outMessage
		if (inRequest.getNamespaceURI() != null)
			modifiedInRequest.setNamespaceURI(inRequest.getNamespaceURI());
		// Set the correct operation
		modifiedInRequest.setName(inRequest.getName());
		// Put all parameters back in IMessage
		for (int i = 0; i < inRequestData.getCount(); i++) {
			if (soiHelper.getSoapOperationParameterName(inRequest.getName(), i).equalsIgnoreCase("MapDescription")) {
				// Add the modified MapDescription
				modifiedInRequestData.addObject(soiHelper.getSoapOperationParameterName(inRequest.getName(), i), md);
			} else {
				/*
				 * Add other parameters as is. Here we are using the SOI helper to add and get parameters
				 * because we don't care about the type we just want to copy from one IMessage object to
				 * another.
				 */
				modifiedInRequestData.addObject(
						soiHelper.getSoapOperationParameterName(inRequest.getName(), i),
						inRequestData.getObject(i, inRequest.getNamespaceURI(),
								soiHelper.getSoapOperationParameterTypeName(inRequest.getName(), i)));
			}
		}

		return modifiedInRequest;
	}

	/**
	 * Filter to apply layer level security to LayerIds
	 *
	 * @param inRequest
	 * @param mode
	 * @param authorizedLayerSet
	 * @return
	 * @throws AutomationException
	 * @throws IOException
	 */
	private IMessage filterLayerIds(IMessage inRequest, RequestMode mode, HashSet<String> authorizedLayerSet)
			throws AutomationException, IOException {
		// 1. Find the index of the layers parameter
		// 2. Get the value for the interested parameter
		// 3. Manipulate it
		// 4. Put it back into IMessage

		int idx = -1;
		IXMLSerializeData inRequestData = inRequest.getParameters();
		try {
			idx = inRequestData.find("LayerIDs");
		} catch (Exception ignore) {
		}

		LongArray layerIdInLA = null;
		if(idx >= 0) {
			// Get all the requested layers
			layerIdInLA = (LongArray) inRequestData.getObject(idx, inRequest.getNamespaceURI(), "ArrayOfInt");

			// Perform filtering based on access to different layers
			for (int i = layerIdInLA.getCount() - 1; i >= 0; i--) {
				if(!authorizedLayerSet.contains(Integer.toString(layerIdInLA.getElement(i)))) {
					layerIdInLA.remove(i);
				}
			}
		} else {
			// No LayerIDs are specified, add authorized layers
			layerIdInLA = new LongArray();
			for (String layerId : authorizedLayerSet) {
				layerIdInLA.add(Integer.parseInt(layerId));
			}
			inRequestData.addObject("LayerIDs", layerIdInLA);
		}

		// If binary request we dont have to create and copy in a new Message object
		if (mode == RequestMode.BYTE_ARRAY) {
			return inRequest;
		}

		// Create new request message
		IMessage modifiedInRequest = soiHelper.createNewIMessage(inRequest);
		IXMLSerializeData modifiedInRequestData = modifiedInRequest.getParameters();

		// Put all parameters back in IMessage
		for (int i = 0; i < inRequestData.getCount(); i++) {
			if (soiHelper.getSoapOperationParameterName(inRequest.getName(), i).equalsIgnoreCase("LayerIDs")) {
				// Add the modified MapDescription
				soiHelper.addObjectToXMLSerializeData(soiHelper.getSoapOperationParameterName(inRequest.getName(), i),
						layerIdInLA, soiHelper.getSoapOperationParameterTypeName(inRequest.getName(), i), modifiedInRequestData);
			} else {
				/*
				 * Add other parameters as is. Here we are using the SOI helper to add and get parameters
				 * because we don't care about the type we just want to copy from one IMessage object to
				 * another.
				 */
				soiHelper.addObjectToXMLSerializeData(
						soiHelper.getSoapOperationParameterName(inRequest.getName(), i),
						soiHelper.getObjectFromXMLSerializeData(i, inRequest.getNamespaceURI(),
								soiHelper.getSoapOperationParameterTypeName(inRequest.getName(), i), inRequestData),
						soiHelper.getSoapOperationParameterTypeName(inRequest.getName(), i), modifiedInRequestData);
			}
		}

		return modifiedInRequest;
	}

	/**
	 * Filter to apply layer level security to GetServerInfoResponse
	 *
	 * @param inRequest
	 * @param mode
	 * @param authorizedLayerSet
	 * @return
	 * @throws AutomationException
	 * @throws IOException
	 */
	private IMessage filterGetServerInfoResponse(IMessage inRequest, RequestMode mode, HashSet<String> authorizedLayerSet)
			throws AutomationException, IOException {
		// 1. Find the index of the Result parameter
		// 2. Get the value for the MapServerInfo parameter
		// 3. Manipulate it
		// 4. Put it back into IMessage

		int idx = -1;
		IXMLSerializeData inRequestData = inRequest.getParameters();
		idx = inRequestData.find("Result");

		// Get MapServerInfo
		MapServerInfo mapServerInfo = (MapServerInfo) inRequestData.getObject(idx, inRequest.getNamespaceURI(),
				"MapServerInfo");

		// Perform filtering based on access to different layers
		IMapLayerInfos layerInfos = mapServerInfo.getMapLayerInfos();
		for (int i = layerInfos.getCount() - 1; i >= 0; i--) {
			if(!authorizedLayerSet.contains(Integer.toString(layerInfos.getElement(i).getID()))) {
				layerInfos.remove(i);
			}
		}

		// Perform filtering based on access to different layers
		ILayerDescriptions layerDescriptions = mapServerInfo.getDefaultMapDescription().getLayerDescriptions();
		for (int i = layerDescriptions.getCount() - 1; i >= 0; i--) {
			if(!authorizedLayerSet.contains(Integer.toString(layerDescriptions.getElement(i).getID()))) {
				layerDescriptions.remove(i);
			}
		}

		// If binary request we don't have to create and copy in a new Message object
		if (mode == RequestMode.BYTE_ARRAY) {
			return inRequest;
		}

		// Create new request message
		IMessage modifiedInRequest = soiHelper.createNewIMessage(inRequest);
		IXMLSerializeData modifiedInRequestData = modifiedInRequest.getParameters();

		// Put all parameters back in IMessage
		for (int i = 0; i < inRequestData.getCount(); i++) {
			if (soiHelper.getSoapOperationParameterName(inRequest.getName(), i).equalsIgnoreCase("Result")) {
				// Add the modified MapDescription
				modifiedInRequestData.addObject(soiHelper.getSoapOperationParameterName(inRequest.getName(), i), mapServerInfo);
			} else {
				/*
				 * Add other parameters as is. Here we are using the SOI helper to add and get parameters
				 * because we don't care about the type we just want to copy from one IMessage object to
				 * another.
				 */
				modifiedInRequestData.addObject(
						soiHelper.getSoapOperationParameterName(inRequest.getName(), i),
						inRequestData.getObject(i, inRequest.getNamespaceURI(),
								soiHelper.getSoapOperationParameterTypeName(inRequest.getName(), i)));
			}
		}

		return modifiedInRequest;
	}

	/**
	 * This method is called by SOAP handler to handle OGC requests.
	 *
	 * @param httpMethod
	 * @param requestURL the request URL
	 * @param queryString the query string
	 * @param capabilities the capabilities
	 * @param requestData the request data
	 * @param responseContentType the response content type
	 * @param respDataType the response data type
	 * @return the response as byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public byte[] handleStringWebRequest(int httpMethod, String requestURL, String queryString, String capabilities,
										 String requestData, String[] responseContentType, int[] respDataType) throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in Sample Object Interceptor for handleStringWebRequest");

		/*
		 * Add code to manipulate OGC (WMS, WFC, WCS etc) requests here
		 */

		IWebRequestHandler webRequestHandler = soiHelper.findWebRequestHandlerDelegate(so);
		if (webRequestHandler != null) {
			return webRequestHandler.handleStringWebRequest(httpMethod, requestURL, queryString, capabilities, requestData,
					responseContentType, respDataType);
		}

		return null;
	}

	/**
	 * This method is called to handle schema requests for custom SOE's.
	 *
	 * To get schema (or Root resource) for a Map Service, REST handler calls
	 * <code>handleRESTRequest</code> with all arguments as empty.
	 *
	 * @return the schema as String
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public String getSchema() throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in Sample Object Interceptor for getSchema");

		/*
		 * Add code to manipulate schema requests here
		 */

		IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
		if (restRequestHandler != null) {
			return restRequestHandler.getSchema();
		}

		return null;
	}

	/**
	 * This method is called to handle binary requests from desktop.
	 *
	 * @param capabilities the capabilities
	 * @param request
	 * @return the response as byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public byte[] handleBinaryRequest2(String capabilities, byte[] request) throws IOException, AutomationException {
		// Log message with server
		serverLog.addMessage(3, 200, "Request received in Sample Object Interceptor for handleBinaryRequest2");

		/*
		 * Add code to manipulate Binary requests from desktop here
		 */

		IRequestHandler2 requestHandler = soiHelper.findRequestHandler2Delegate(so);
		if (requestHandler != null) {
			return requestHandler.handleBinaryRequest2(capabilities, request);
		}

		return null;
	}

	/**
	 * This method is called to handle binary requests from desktop. It calls the
	 * <code>handleBinaryRequest2</code> method with capabilities equal to null.
	 *
	 * @param request
	 * @return the response as the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public byte[] handleBinaryRequest(byte[] request) throws IOException, AutomationException {
		// Log message with server
		serverLog.addMessage(3, 200, "Request received in Sample Object Interceptor for handleBinaryRequest");

		/*
		 * Add code to manipulate Binary requests from desktop here
		 */

		// Convert the XML request into a generic IMessage
		// Intercept the request and perform filtering
		byte[] filteredRequest = filterBinaryRequest(request, RequestMode.BYTE_ARRAY);

		// Forward the request to the appropriate delegate/handler
		IRequestHandler requestHandler = soiHelper.findRequestHandlerDelegate(so);
		if (requestHandler != null) {
			// Get the response
			byte[] response = requestHandler.handleBinaryRequest(filteredRequest);

			// Perform filtering for GetServerInfoResponse
			byte[] filteredResponse = filterBinaryRequest(response, RequestMode.BYTE_ARRAY);

			if (filteredResponse != null) {
				response = filteredResponse;
			}
			return response;
		}

		return null;
	}

	/**
	 * shutdown() is called once when the Server Object's context is being shut down and is about to
	 * go away.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	public void shutdown() throws IOException, AutomationException {
		/*
		 * The SOE should release its reference on the Server Object Helper.
		 */
		this.serverLog.addMessage(3, 200, "Shutting down " + this.getClass().getName() + " SOI.");
		this.serverLog = null;
		this.so = null;
		this.soiHelper = null;
	}

	/**
	 * Remove unauthorized layers from request.
	 *
	 * @param requestedLayers layer user is requesting information from
	 * @param authorizedLayers layers user is authorized to fetch information from
	 * @return
	 */
	private static String removeUnauthorizedLayersFromRequestedLayers(String requestedLayers, String authorizedLayers) {
		// authorized layers
		List<String> authorizedLayerList = null;
		HashSet<String> authorizedLayerSet = null;
		try {
			authorizedLayerList = Arrays.asList(authorizedLayers.split(","));
			authorizedLayerSet = new HashSet<String>(authorizedLayerList);
		} catch (Exception ignore) {
		}

		// requested layers
		List<String> requestedLayersList = null;
		List<String> requestedLayersArrayList = null;
		try {
			requestedLayersList = Arrays.asList(requestedLayers.split(","));
			requestedLayersArrayList = new ArrayList<String>(requestedLayersList);
		} catch (Exception ignore) {
		}

		if (requestedLayersArrayList != null && authorizedLayerSet != null) {
			// filter out unauthorized layers
			for (String layer : requestedLayersList) {
				if (!authorizedLayerSet.contains(layer)) {
					requestedLayersArrayList.remove(layer);
				}
			}
			// if nothing was common return -1
			if (requestedLayersArrayList.size() == 0)
				return "-1";
			else
				return join(requestedLayersArrayList, ",");
		}

		return "-1";
	}

	/**
	 * Read permission information from disk
	 *
	 * @param fileName path and name of the file to read permissions from
	 * @return
	 */
	private Map<String, String> readPermissionFile(String fileName) {
		// read the permissions file
		BufferedReader reader;
		Map<String, String> permissionMap = new HashMap<String, String>();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			String permissionFileDataString = "";
			while ((line = reader.readLine()) != null) {
				permissionFileDataString += line;
			}
			JSONObject permissionsJSON = new JSONObject(permissionFileDataString);
			// create a map of permissions
			// read the permissions array
			JSONArray permissionsArray = permissionsJSON.getJSONArray("permissions");
			// add to map
			for (int i = 0; i < permissionsArray.length(); i++) {
				// get the fqsn or service name
				String fqsn = permissionsArray.getJSONObject(i).getString("fqsn");
				// read the permission for that service
				JSONArray permissionArray = permissionsArray.getJSONObject(i).getJSONArray("permission");
				for (int j = 0; j < permissionArray.length(); j++) {
					String role = permissionArray.getJSONObject(j).getString("role");
					// read and get all authorized layers
					String authorizedLayers = permissionArray.getJSONObject(j).getString("authorizedLayers");
					permissionMap.put(fqsn + "." + role, authorizedLayers);
				}
			}
			reader.close();
		} catch (Exception ignore) {
		}
		return permissionMap;
	}

	/**
	 * Joins the specified parts separating each from one another with the specified delimiter.
	 *
	 * @param parts the strings to be joined
	 * @param delim the char(s) that should separate the parts in the result
	 * @return a string representing the joined parts.
	 */
	private static String join(List<String> parts, String delim) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < parts.size(); i++) {
			String part = parts.get(i);
			result.append(part);
			if (delim != null && i < parts.size() - 1) {
				result.append(delim);
			}
		}
		return result.toString();
	}

	/**
	 *  Filter REST GetLegend response.
	 *
	 * @param originalJSONRes
	 * @param authorizedLayers
	 * @param operationFromResource
	 * @return
	 */
	private String filterJSONGetLegendandGetAllLayersResponse(
			final String originalJSONRes, final String authorizedLayers, final String operationFromResource) {
		// Decide what tag to use, based on the operation
		String layerIdJSONKey = null;
		if(operationFromResource.equalsIgnoreCase("legend")) {
			layerIdJSONKey = "layerId";
		} else if (operationFromResource.equalsIgnoreCase("layers")) {
			layerIdJSONKey = "id";
		}

		// Authorized layer set
		List<String> authorizedLayerList = null;
		HashSet<String> authorizedLayerSet = null;
		try {
			authorizedLayerList = Arrays.asList(authorizedLayers.split(","));
			authorizedLayerSet = new HashSet<String>(authorizedLayerList);
		} catch (Exception ignore) {
		}

		// Perform JSON filtering
		// Note: The JSON syntax can change and you might have to adjust this piece of code accordingly
		if (authorizedLayerSet != null) {
			JSONObject jsonResObj = new JSONObject(originalJSONRes);
			// Filter for layers' tag
			JSONArray legendLayerJA = jsonResObj.getJSONArray("layers");
			for (int i = legendLayerJA.length() - 1; i >= 0; i--) {
				if (!authorizedLayerSet.contains(Integer.toString(legendLayerJA.getJSONObject(i).getInt(layerIdJSONKey)))) {
					legendLayerJA.remove(i);
				}
			}

			// Return the filter response
			return jsonResObj.toString();
		}

		return null;
	}

	/**
	 * Filter REST GetInfo response.
	 *
	 * @param originalJSONRes
	 * @param authorizedLayers
	 * @return
	 */
	private static String filterJSONGetInfoResponse(final String originalJSONRes, final String authorizedLayers) {
		// Authorized layer set
		List<String> authorizedLayerList = null;
		HashSet<String> authorizedLayerSet = null;
		try {
			authorizedLayerList = Arrays.asList(authorizedLayers.split(","));
			authorizedLayerSet = new HashSet<String>(authorizedLayerList);
		} catch (Exception ignore) {
		}

		// Perform JSON filtering
		// Note: The JSON syntax can change and you might have to adjust this piece of code accordingly
		if (authorizedLayerSet != null) {
			/*
			 * Remove unauthorized layer information from
			 * 1. Under 'contents' tag
			 * 2. Under 'resources' tag
			 *  2.1 'layers'
			 *  2.2 'tables'
			 *  2.3 'legend'
			 */

			JSONObject jsonResObj = new JSONObject(originalJSONRes);

			// Filter for 'contents' tag
			JSONArray layersJA = jsonResObj.getJSONObject("contents").getJSONArray("layers");
			for (int i = layersJA.length() - 1; i >= 0; i--) {
				if (!authorizedLayerSet.contains(Integer.toString(layersJA.getJSONObject(i).getInt("id")))) {
					layersJA.remove(i);
				}
			}

			// Filter for 'resources' tag
			JSONArray resourcesJA = jsonResObj.getJSONArray("resources");

			// Filter for 'resources -> layers -> resources' tag
			JSONArray layerResourceJA = resourcesJA.getJSONObject(0).getJSONArray("resources");
			for (int i = layerResourceJA.length() - 1; i >= 0; i--) {
				if (!authorizedLayerSet.contains(layerResourceJA.getJSONObject(i).getString("name"))) {
					layerResourceJA.remove(i);
				}
			}

			// Filter for 'resources -> tables -> resources' tag
			JSONArray tableResourceJA = resourcesJA.getJSONObject(1).getJSONArray("resources");
			for (int i = tableResourceJA.length() - 1; i >= 0; i--) {
				if (!authorizedLayerSet.contains(tableResourceJA.getJSONObject(i).getString("name"))) {
					tableResourceJA.remove(i);
				}
			}

			// Filter for 'resources -> legend -> layers' tag
			JSONArray legendLayerJA = resourcesJA.getJSONObject(2).getJSONObject("contents").getJSONArray("layers");
			for (int i = legendLayerJA.length() - 1; i >= 0; i--) {
				if (!authorizedLayerSet.contains(Integer.toString(legendLayerJA.getJSONObject(i).getInt("layerId")))) {
					legendLayerJA.remove(i);
				}
			}

			// Return the filter response
			return jsonResObj.toString();
		}

		return null;
	}


	/**
	 * Returns the ArcGIS home directory path.
	 *
	 * @return
	 * @throws Exception
	 */
	private String getArcGISHomeDir() throws IOException {
		String arcgisHome = null;
		/* Not found in env, check system property */
		if (System.getProperty(ARCGISHOME_ENV) != null) {
			arcgisHome = System.getProperty(ARCGISHOME_ENV);
		}

		if(arcgisHome == null) {
			/* To make env lookup case insensitive */
			Map<String, String> envs = System.getenv();
			for (String envName : envs.keySet()) {
				if (envName.equalsIgnoreCase(ARCGISHOME_ENV)) {
					arcgisHome = envs.get(envName);
				}
			}
		}
		if(arcgisHome != null && !arcgisHome.endsWith(File.separator)) {
			arcgisHome += File.separator;
		}
		return arcgisHome;
	}

	/**
	 * Reads a permission file and return the defined permissions.
	 *
	 * @param serverobject
	 * @throws IOException
	 */
	private void getPermissionFromFile(IServerObject serverobject) throws IOException {
		String serverDir = null;
		MapServer mapserver= (MapServer)serverobject;
		String physicalOutputDir= mapserver.getPhysicalOutputDirectory();
		int index = physicalOutputDir.indexOf(File.separator + "directories" + File.separator + "arcgisoutput");
		if(index > 0) {
			serverLog.addMessage(4, 200, "The physical directory for output files: " + physicalOutputDir);
			serverDir = physicalOutputDir.substring(0,index);
		} else {
			serverLog.addMessage(1, 200,"Incorrect physical directory for output files: " + physicalOutputDir);
			throw new IOException("Incorrect physical directory for output files: " + physicalOutputDir);
		}
		/*
		 * Permission are read from this external file. Advantage of an external file is that same SOI can
		 * be used for multiple services and permission for all of these services is read from the
		 * permission.json file.
		 */
		String permssionFilePath = serverDir + File.separator +  "permission.json";
		// Read the permissions file
		if (new File(permssionFilePath).exists()) {
			serverLog.addMessage(4, 200, "The permission file is located at : " + permssionFilePath);
			servicePermissionMap = readPermissionFile(permssionFilePath);
		} else {
			serverLog.addMessage(1, 200,"Cannot find the permission file at " + permssionFilePath);
			throw new IOException("Cannot find the permission file at " + permssionFilePath);
		}
	}

	/**
	 * String or binary request mode
	 */
	private enum RequestMode {
		STRING, BYTE_ARRAY
	}

	/**
	 * String or binary request type
	 */
	private class RequestType {
		public String stringRequest;
		public byte[] byteArrayRequest;
	}

	/**
	 * List of operations supported by REST handler
	 */
	private enum RESTOperations {
		// Supported operations
		FIND("find"), IDENTIFY("identify"), EXPORT_MAP("export"), GENERATE_KML("generateKml"), GENERATE_RENDERER(
				"generateRenderer"), QUERY("query"), QUERY_RELATED_RECORDS("queryRelatedRecords"), DEFAULT("default");

		private String value;
		private static final Map<String, RESTOperations> lookup = new HashMap<String, RESTOperations>();
		static {
			for (RESTOperations operation : RESTOperations.values())
				lookup.put(operation.getValue(), operation);
		}

		private RESTOperations(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		/**
		 * Get RESTOperation from a String representation
		 *
		 * @param value
		 * @return
		 */
		public static RESTOperations get(final String value) {
			if (lookup.get(value) != null)
				return lookup.get(value);
			else
				return RESTOperations.DEFAULT;
		}
	};
}
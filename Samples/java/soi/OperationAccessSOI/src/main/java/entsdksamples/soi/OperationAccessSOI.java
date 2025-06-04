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

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObject;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.SOIHelper;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.IRequestHandler;
import com.esri.arcgis.system.IRequestHandler2;
import com.esri.arcgis.system.IServerUserInfo;
import com.esri.arcgis.system.IWebRequestHandler;
import com.esri.arcgis.system.ServerUtilities;

/*
 * For an SOE to act as in intercepter, it needs to implement all request
 * handler interfaces IRESTRequestHandler, IWebRequestHandler, IRequestHandler2,
 * IRequestHandler now the SOE/SOI can intercept all types of calls to
 * ArcObjects or custom SOEs.
 *
 * In this example we try to control access to different operation based on user
 * roles.
 *
 *
 * Note: Intercepting REST operation calls has been implemented in this example.
 * All other handlers need to be implemented.
 *
 * Note: Limitation with using SOIs in 10.3 is that we cannot intercept the call
 * to ArcSOC, used to create the Service Directory (or Root resource) HTML page,
 * as the call is made once and subsequently REST handler caches this
 * information. We are working on fixing this limitation.
 */

/*
 * For an interceptor you need to set additional properties for @ServerObjectExtProperties the annotation.
 * 1. interceptor = true, is used to identify an SOI
 * 2. servicetype = MapService | ImageService, can be used to assign an interceptor to an Image or Map Service.
 */
@ArcGISExtension
@ServerObjectExtProperties(displayName = "Operation Access SOI",
		description = "SOI to control access to different operations",
		interceptor = true,
		servicetype = "MapService",
		supportsSharedInstances = true)
public class OperationAccessSOI implements IServerObjectExtension, IRESTRequestHandler, IWebRequestHandler,
		IRequestHandler2, IRequestHandler {
	private static final String ARCGISHOME_ENV = "AGSSERVER";
	private static final long serialVersionUID = 1L;
	private ILog serverLog;
	private IServerObject so;
	private SOIHelper soiHelper;
	/*
	 * Load the SOI helper.
	 */
	//private SOIHelper soiHelper = new SOIHelper("C:/Program Files/ArcGIS/Server/XmlSchema/MapServer.wsdl");

	/**
	 * Default constructor.
	 *
	 * @throws Exception
	 */
	public OperationAccessSOI() throws Exception {
		super();
	}

	/**
	 * init() is called once, when the instance of the SOE/SOI is created.
	 *
	 * @param soh
	 *            the IServerObjectHelper
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	public void init(IServerObjectHelper soh) throws IOException, AutomationException {
		/*
		 * An SOE should retrieve a weak reference to the Server Object from the Server Object Helper in
		 * order to make any method calls on the Server Object and release the
		 * reference after making the method calls.
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
		// Load the SOI helper.
		String mapServiceWSDLPath = arcgisHome + "framework#runtime#ArcGIS#Resources#XmlSchema".replace("#", File.separator) + File.separator + "MapServer.wsdl";
		this.soiHelper = new SOIHelper(mapServiceWSDLPath);
		this.serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName() + " SOE.");
	}

	/**
	 * This method is called to handle REST requests.
	 *
	 * SOEs allow the user to extend base functionality for ArvGIS Map Services
	 * and Image Services.
	 * To get schema or root resource for a Map Service the REST handler
	 * calls <code>handleRESTRequest</code> with all arguments as empty.
	 * For a Map Service the supported REST operations are: find, identify, export.
	 * For an Image Service the supported REST operations are: identify, export, etc.
	 *
	 * In this example we demonstrate operation level access. REST operation on
	 * a published Map Service are controlled via the
	 * <code>rolesAuthorizedForFindOperation</code> list. It defines a list of
	 * roles that have access to operations (find, identify, export) on a map
	 * service. For users who do not belong to the authorized roles list we
	 * return a <code>null</code> value.
	 *
	 *
	 * Note: Limitation with using SOIs in 10.3 is that we cannot intercept the call
	 * to ArcSOC, used to create the Service Directory (or Root resource) HTML page,
	 * as the call is made once and subsequently REST handler caches this
	 * information. We are working on fixing this limitation.
	 *
	 * @param capabilities
	 *            the capabilities
	 * @param resourceName
	 *            the resource name
	 * @param operationName
	 *            the operation name
	 * @param operationInput
	 *            the operation input
	 * @param outputFormat
	 *            the output format
	 * @param requestProperties
	 *            the request properties
	 * @param responseProperties
	 *            the response properties
	 * @return the response as byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public byte[] handleRESTRequest(String capabilities, String resourceName,
									String operationName, String operationInput, String outputFormat,
									String requestProperties, String[] responseProperties)
			throws IOException, AutomationException {
		/*
		 * Log message with server.
		 */
		serverLog.addMessage(3, 200, "Request received in Operation Access SOI for handleRESTRequest");
		serverLog.addMessage(3, 200, "capabilities - " + capabilities);
		serverLog.addMessage(3, 200, "resourceName - " + resourceName);
		serverLog.addMessage(3, 200, "operationName - " + operationName);
		serverLog.addMessage(3, 200, "operationInput - " + operationInput);
		serverLog.addMessage(3, 200, "outputFormat - " + outputFormat);
		serverLog.addMessage(3, 200, "requestProperties - " + requestProperties);
		/*
		 * By default, block access for all users.
		 */
		boolean blockAccessToFindOperation = true;
		/*
		 * List of roles that have access.
		 *
		 * Here we have defined a single list to control access for all
		 * operations but depending on the use case we can create per operation
		 * level lists or even read this information from an external file.
		 */
		String[] rolesAuthorizedForFindOperation = {"gold123", "platinum123"};

		/*
		 * Check if the user if authorized to perform the operation.
		 *
		 * Note: Here we are checking for all valid Map Service operations. If
		 * you need to use this SOI for a published Image Service you need to
		 * extend this to cover all Image Service operations.
		 */
		if (operationName.equalsIgnoreCase("find") // find operation
				|| operationName.equalsIgnoreCase("identify") // identify operation
				|| operationName.equalsIgnoreCase("export"))// export operation
		{
			/*
			 * Get all roles the user belongs to.
			 */
			Set<String> userRoleSet = ServerUtilities.getGroupInfo();
			/*
			 * Using 'userRoleSet' and ''rolesAuthorizedForFindOperation' to
			 * decide if user has access to the requested operation.
			 */
			for (int i = 0; i < rolesAuthorizedForFindOperation.length; i++) {
				if(userRoleSet.contains(rolesAuthorizedForFindOperation[i])) {
					blockAccessToFindOperation = false;
				}
			}
			/*
			 * Block access to the operation if user is not authorized
			 */
			if (blockAccessToFindOperation) {
				return new JSONObject()
						.put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
						.getBytes();
			}
		}  else if( !operationName.equalsIgnoreCase("")){
			/*
			 * We support only operations find, identify, export
			 * for all other operations we return a null or no result.
			 */
			return new JSONObject()
					.put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
					.getBytes();
		}

		/*
		 * Find the correct delegate to forward the request to
		 */
		IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
		if(restRequestHandler != null) {
			/*
			 * Return the response.
			 */
			byte[] response =
					restRequestHandler.handleRESTRequest(capabilities, resourceName, operationName, operationInput, outputFormat,
							requestProperties, responseProperties);
			serverLog.addMessage(4, 200, "rest response :: " + new String(response));
			return response;
		}

		return new JSONObject()
				.put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
				.getBytes();
	}

	/**
	 * This method is called to handle SOAP requests.
	 *
	 * Note: Intercepting and authorizing SOAP handler operation requests is not
	 * implemented.
	 *
	 * @param capabilities
	 *            the capabilities
	 * @param request
	 *            the request
	 * @return the response as String
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public String handleStringRequest(String capabilities, String request)
			throws IOException, AutomationException {
		// Log message with server
		serverLog.addMessage(3, 200, "Request received in Server Object Interceptor for handleStringRequest");

		/*
		 * Add code to manipulate SOAP requests here
		 */

		// Find the correct delegate to forward the request too
		IRequestHandler requestHandler = soiHelper.findRequestHandlerDelegate(so);
		if(requestHandler != null)
			// Return the response
			return requestHandler.handleStringRequest(capabilities, request);

		return null;
	}

	/**
	 * This method is called by SOAP handler to handle OGC requests.
	 *
	 * @param httpMethod
	 * @param requestURL
	 *            the request URL
	 * @param queryString
	 *            the query string
	 * @param capabilities
	 *            the capabilities
	 * @param requestData
	 *            the request data
	 * @param responseContentType
	 *            the response content type
	 * @param respDataType
	 *            the response data type
	 * @return the response as byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public byte[] handleStringWebRequest(int httpMethod, String requestURL,
										 String queryString, String capabilities, String requestData,
										 String[] responseContentType, int[] respDataType)
			throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in Server Object Interceptor for handleStringWebRequest");

		/*
		 * Add code to manipulate OGC (WMS, WFC, WCS etc) requests here
		 */

		IWebRequestHandler webRequestHandler = soiHelper.findWebRequestHandlerDelegate(so);
		if(webRequestHandler != null) {
			return webRequestHandler.handleStringWebRequest(
					httpMethod, requestURL, queryString, capabilities, requestData, responseContentType, respDataType);
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
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public String getSchema() throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in Server Object Interceptor for getSchema");

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
	 * @param capabilities
	 *            the capabilities
	 * @param request
	 * @return the response as byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public byte[] handleBinaryRequest2(String capabilities, byte[] request)
			throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in Server Object Interceptor for handleBinaryRequest2");

		/*
		 * Add code to manipulate Binary requests from desktop here
		 */

		IRequestHandler2 requestHandler = soiHelper.findRequestHandler2Delegate(so);
		if(requestHandler != null) {
			return requestHandler.handleBinaryRequest2(capabilities, request);
		}

		return null;
	}

	/**
	 * This method is called to handle binary requests from desktop. It calls
	 * the <code>handleBinaryRequest2</code> method with capabilities equal to
	 * null.
	 *
	 * @param request
	 * @return the response as the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public byte[] handleBinaryRequest(byte[] request) throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in Server Object Interceptor for handleBinaryRequest");

		/*
		 * Add code to manipulate Binary requests from desktop here
		 */

		IRequestHandler requestHandler = soiHelper.findRequestHandlerDelegate(so);
		if(requestHandler != null)
			return requestHandler.handleBinaryRequest(request);

		return null;
	}

	/**
	 * shutdown() is called once when the Server Object's context is being shut
	 * down and is about to go away.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AutomationException
	 *             the automation exception
	 */
	public void shutdown() throws IOException, AutomationException {
		/*
		 * The SOE should release its reference on the Server Object Helper.
		 */
		this.serverLog.addMessage(3, 200, "Shutting down "
				+ this.getClass().getName() + " SOI.");
		this.soiHelper.cleanup(); 
		this.serverLog = null;
		this.so = null;
		this.soiHelper = null;
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


}
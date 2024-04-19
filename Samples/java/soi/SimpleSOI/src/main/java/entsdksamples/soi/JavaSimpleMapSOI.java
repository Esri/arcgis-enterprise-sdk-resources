package entsdksamples.soi;

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

import java.io.File;
import java.io.IOException;
import java.util.Map;

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
import com.esri.arcgis.system.IWebRequestHandler;
import com.esri.arcgis.system.ServerUtilities;

/*
 * For an SOE to act as in interceptor, it needs to implement all request handler interfaces
 * IRESTRequestHandler, IWebRequestHandler, IRequestHandler2, IRequestHandler now the SOE/SOI can
 * intercept all types of calls to ArcObjects or custom SOEs.
 * 
 * This sample SOI can be used as the starting point to writing new SOIs. It is a basic example
 * which implements all request handlers and logs calls to ArcObjects or custom SOEs.
 */

/*
 * For an interceptor you need to set additional properties for @ServerObjectExtProperties the annotation.
 * 1. interceptor = true, is used to identify an SOI
 * 2. servicetype = MapService | ImageService, can be used to assign an interceptor to an Image or Map Service.
 */
@ArcGISExtension

@ServerObjectExtProperties(
        displayName = "Java Simple Map SOI",
        description = "Intercepts and logs all calls to ArcObjects or custom SOEs",
        interceptor = true,
        servicetype =  "MapService" ,
         properties = "" ,
		supportsSharedInstances = true)

public class JavaSimpleMapSOI
		implements IServerObjectExtension, IRESTRequestHandler, IWebRequestHandler, IRequestHandler2, IRequestHandler
     {

	private static final long serialVersionUID = 1L;
	private static final String ARCGISHOME_ENV = "AGSSERVER";
	private ILog serverLog;
	private IServerObject so;
	private SOIHelper soiHelper;

	/**
	 * Default constructor.
	 *
	 * @throws Exception
	 */
	public JavaSimpleMapSOI() throws Exception {
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
		// Get reference to server logger utility
		this.serverLog = ServerUtilities.getServerLogger();
		// Log message with server
		this.serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName() + " SOI.");
		this.so = soh.getServerObject();
		String arcgisHome = getArcGISHomeDir();
		/* If null, throw an exception */
		if (arcgisHome == null) {
			serverLog.addMessage(1, 200, "Could not get ArcGIS home directory. Check if environment variable "
					+ ARCGISHOME_ENV + " is set.");
			throw new IOException("Could not get ArcGIS home directory. Check if environment variable " + ARCGISHOME_ENV
					+ " is set.");
		}
		if (arcgisHome != null && !arcgisHome.endsWith(File.separator))
			arcgisHome += File.separator;
		// Load the SOI helper.    
		String mapServiceWSDLPath = arcgisHome + "framework#runtime#ArcGIS#Resources#XmlSchema".replace("#", File.separator) + File.separator + "MapServer.wsdl";
		this.soiHelper = new SOIHelper(mapServiceWSDLPath);
	}

    
	/**
	 * This method is called to handle REST requests.
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
		 * Log message with server. Here we are logging who made the request,
		 * what operation was invoked and with what input parameters.
		 * 
		 * You can use different log codes to set up different log levels.
		 * 
		 * For example:
		 * Use log code 1 for Severe Messages.
		 * Use log code 2 for Warning Messages.
		 * Use log code 3 for Info Messages.
		 * Use log code 4 for Fine Messages.
		 * Use log code 100 for Debug Messages.
		 * 
		 * Note: You can also use the ILog interface to get more information on log message levels.
		 */
		serverLog.addMessage(3, 200, "Request logged in JavaSimpleMapSOI. User: " + getLoggedInUserName() + ", Operation: "
				+ operationName + ", Operation Input: " + processOperationInput(operationInput));

		/*
		 * Add code to manipulate REST requests here
		 */

		// Find the correct delegate to forward the request too
		IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
		if (restRequestHandler != null) {
			// Return the response
			return restRequestHandler.handleRESTRequest(capabilities, resourceName, operationName, operationInput,
					outputFormat, requestProperties, responseProperties);
		}

		return null;
	}

	/**
	 * This method is called to handle SOAP requests.
	 *
	 * @param capabilities the capabilities
	 * @param request the request
	 * @return the response as String
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public String handleStringRequest(String capabilities, String request) throws IOException, AutomationException {
		// Log message with server
		serverLog.addMessage(3, 200, "Request received in JavaSimpleMapSOI Interceptor for handleStringRequest");

		/*
		 * Add code to manipulate SOAP requests here
		 */

		// Find the correct delegate to forward the request too
		IRequestHandler requestHandler = soiHelper.findRequestHandlerDelegate(so);
		if (requestHandler != null) {
			// Return the response
			return requestHandler.handleStringRequest(capabilities, request);
		}

		return null;
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
			String requestData, String[] responseContentType, int[] respDataType)
			throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in JavaSimpleMapSOI Interceptor for handleStringWebRequest");

		/*
		 * Add code to manipulate OGC (WMS, WFC, WCS etc) requests here
		 */

		IWebRequestHandler webRequestHandler = soiHelper.findWebRequestHandlerDelegate(so);
		if (webRequestHandler != null) {
			return webRequestHandler.handleStringWebRequest(httpMethod, requestURL, queryString, capabilities,
					requestData, responseContentType, respDataType);
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
		serverLog.addMessage(3, 200, "Request received in JavaSimpleMapSOI Interceptor for handleBinaryRequest2");

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
	 * Return the logged in user's user name.
	 * 
	 * @return
	 */
	private String getLoggedInUserName() {
		try {
			/*
			 * Get the user information.
			 */
			String userName = ServerUtilities.getServerUserInfo().getName();

			if (userName.isEmpty()) {
				return new String("Anonymous User");
			}
			return userName;
		} catch (Exception ignore) {
		}

		return new String("Anonymous User");
	}

	/**
	 * Get bbox from operationInput
	 * 
	 * @param operationInput
	 * @return
	 */
	private String processOperationInput(String operationInput) {
		try {
			return "bbox = " + new JSONObject(operationInput).getString("bbox");
		} catch (Exception ignore) {
		}
		return new String("No input parameters");
	}

	/**
	 * This method is called to handle schema requests for custom SOE's.
	 *
	 * @return the schema as String
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AutomationException the automation exception
	 */
	@Override
	public String getSchema() throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Request received in JavaSimpleMapSOI Interceptor for getSchema");

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
		serverLog.addMessage(3, 200, "Request received in JavaSimpleMapSOI Interceptor for handleBinaryRequest");

		/*
		 * Add code to manipulate Binary requests from desktop here
		 */

		IRequestHandler requestHandler = soiHelper.findRequestHandlerDelegate(so);
		if (requestHandler != null) {
			return requestHandler.handleBinaryRequest(request);
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
		if (arcgisHome == null) {
			/* To make env lookup case insensitive */
			Map<String, String> envs = System.getenv();
			for (String envName : envs.keySet()) {
				if (envName.equalsIgnoreCase(ARCGISHOME_ENV)) {
					arcgisHome = envs.get(envName);
				}
			}
		}
		if (arcgisHome != null && !arcgisHome.endsWith(File.separator)) {
			arcgisHome += File.separator;
		}
		return arcgisHome;
	}
}

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
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.EnvironmentManager;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.IServerEnvironment2;
import com.esri.arcgis.system.IServerEnvironment2Proxy;
import com.esri.arcgis.system.OutputStore;
import com.esri.arcgis.system.ServerUtilities;
import com.esri.arcgis.system.UID;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Download File REST SOE",
		description = "Java Sample Download File REST SOE.",
	 	properties = "" ,
		allSOAPCapabilities = "" ,
	 	defaultSOAPCapabilities = "" ,
		servicetype = "MapService",
		supportsSharedInstances = false)

public class JavaDownloadFileRESTSOE implements IServerObjectExtension, IRESTRequestHandler
	 {

		 private static final long serialVersionUID = 1L;
		 private ILog serverLog;

		 private OutputStore outputStore;
		 private String virtualOutputDir;

		 private Exception ex;

	public JavaDownloadFileRESTSOE()throws Exception{
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
		try {
			this.serverLog=ServerUtilities.getServerLogger();
			serverLog.addMessage(3, 200, "Beginning init in "
					+ this.getClass().getName() + " SOE.");
			outputStore = ServerUtilities.getOutputStore(soh.getServerObject());
			virtualOutputDir = outputStore.getServiceVirtualOutputDir();
			this.serverLog.addMessage(3,200,"Initialized "+this.getClass().getName()+" SOE.");
		}
		catch (Exception ex) {
			this.ex = ex;
			String message = "Error during init()";
			try {
				serverLog.addMessage(1, 200, message + "\n\n" + ex);
			} catch (Exception ignore) {}
			throw new RuntimeException(message, ex);
		}
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

	 /**
	  * Method for implementing REST operation "DownloadFile"'s functionality.
	  * @param  operationInput JSON representation of input
	  * @return JSON representation of output
	  */
	 private byte[] DownloadFile(JSONObject operationInput, String outputFormat, JSONObject requestPropertiesJSON,
								 java.util.Map<String, String> responseProperties) throws Exception {
		 try {
			 String fileName = operationInput.optString("fileName");
			 if (fileName == null || fileName.isEmpty()) {
				 String fileId = UUID.randomUUID().toString().substring(0, 7);
				 fileName = "testFile_" + fileId + ".txt";
				 String inputText = "default testing content...";
				 byte[] inputBytes = inputText.getBytes();
				 long fileSize = inputBytes.length;
				 InputStream fileStream = new ByteArrayInputStream(inputBytes);
				 outputStore.write(fileName, fileStream, fileSize);
			 }
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 outputStore.read(fileName, out);
			 responseProperties.put("Content-Type", "application/octet-stream");
			 responseProperties.put("Content-Disposition", "attachment; filename=" + fileName);
			 return out.toByteArray();
		 } catch (Exception ex) {
			 responseProperties.put("Content-Type", "application/json");
			 return new JSONObject().put("error", ex).toString().getBytes(StandardCharsets.UTF_8);
		 }
	 }

	 /**
	  * Method for implementing REST operation "DeleteFile"'s functionality.
	  * @param operationInput JSON representation of input
	  * @return JSON representation of output
	  */
	 private byte[] DeleteFile(JSONObject operationInput, String outputFormat, JSONObject requestPropertiesJSON,
							   java.util.Map<String, String> responseProperties) throws Exception {
		 responseProperties.put("Content-Type", "application/json");
		 String fileName = operationInput.optString("fileName");
		 if (fileName == null || fileName.isEmpty() || !outputStore.exists(fileName)) {
			 return new JSONObject().put("error", "file not found.").toString().getBytes(StandardCharsets.UTF_8);
		 }
		 try {
			 outputStore.delete(fileName);
			 return new JSONObject().put("success", true).toString().getBytes(StandardCharsets.UTF_8);
		 }
		 catch(Exception e) {
			 return new JSONObject().put("error", e.getMessage()).toString().getBytes(StandardCharsets.UTF_8);
		 }
	 }


	 private byte[] getRootResource(String outputFormat, JSONObject requestPropertiesJSON,
									java.util.Map<String, String> responsePropertiesMap) throws Exception {
		 JSONObject json = new JSONObject();
		 json.put("name", "root resource");
		 json.put("description",
				 "This SOE generates a text file on the Server and allows clients to download the file from the Server.\n It also provides REST endpoints to manage those files such as obtaining file names and deleting the files.");
		 return json.toString().getBytes(StandardCharsets.UTF_8);
	 }

	 /**
	  * Returns JSON representation of Files resource.
	  * This resource is not a collection.
	  * @return String JSON representation of Files resource.
	  */
	 private byte[] getSubresourcefiles(JSONObject operationInput,
																			Map<String, String> responsePropertiesMap) throws Exception {

		 String subDir = operationInput.optString("subDir");
		 if (subDir == null || subDir.isEmpty()) {
			 subDir = "";
		 }
		 responsePropertiesMap.put("Content-Type", "application/json");
		 List<String> files = outputStore.listFiles(subDir);
		 JSONArray filesArr = new JSONArray();
		 for(String fileName: files) {
			 filesArr.put(fileName);
		 }
		 JSONObject resultJson = new JSONObject();
		 resultJson.put("files", filesArr);

		 return resultJson.toString().getBytes(StandardCharsets.UTF_8);
	 }

	 /**
	  * Returns JSON representation of specified resource.
	  * @return String JSON representation of specified resource.
	  */
	 private byte[] getResource(String capabilitiesList, String resourceName, String outputFormat,
								JSONObject requestPropertiesJSON, java.util.Map<String, String> responsePropertiesMap) throws Exception {
		 return null;
	 }

	 /**
	  * Invokes specified REST operation on specified REST resource
	  * @param capabilitiesList
	  * @param resourceName
	  * @param operationName
	  * @param operationInput
	  * @param outputFormat
	  * @param requestPropertiesJSON
	  * @param responsePropertiesMap
	  * @return byte[]
	  */
	 private byte[] invokeRESTOperation(String capabilitiesList, String resourceName, String operationName,
										String operationInput, String outputFormat, JSONObject requestPropertiesJSON,
										java.util.Map<String, String> responsePropertiesMap) throws Exception {
		 JSONObject operationInputAsJSON = new JSONObject(operationInput);
		 byte[] operationOutput = null;

		 //permitted capabilities list can be used to allow/block access to operations

		 if (resourceName.isEmpty()) {
			 if (operationName.equalsIgnoreCase("WriteFile")) {
				 operationOutput = WriteFile(operationInputAsJSON, outputFormat, requestPropertiesJSON,
						 responsePropertiesMap);
			 }
			 if (operationName.equalsIgnoreCase("DownloadFile")) {
				 operationOutput = DownloadFile(operationInputAsJSON, outputFormat, requestPropertiesJSON,
						 responsePropertiesMap);
			 }
			 else if (operationName.equalsIgnoreCase("DeleteFile")) {
				 operationOutput = DeleteFile(operationInputAsJSON, outputFormat, requestPropertiesJSON,
						 responsePropertiesMap);
			 }
		 } else //if non existent sub-resource specified, report error
		 {
			 return ServerUtilities
					 .sendError(0, "No sub-resource by name " + resourceName + " found.", new String[] { "" })
					 .getBytes(StandardCharsets.UTF_8);
		 }

		 return operationOutput;
	 }

		 private byte[] WriteFile(JSONObject operationInput,
															String outputFormat,
															JSONObject requestProperties,
															Map<String, String> responseProperties) {
			 if (ex != null) {
				 return ex.toString().getBytes();
			 }
			 JSONObject jsonResult = new JSONObject();
			 try {
				 String fileName = operationInput.optString("fileName");
				 if (fileName == null || fileName.isEmpty()) {
					 String fileId = UUID.randomUUID().toString().substring(0, 7);
					 fileName = "testFile_" + fileId + ".txt";
				 }
				 String inputText = operationInput.optString("inputText");
				 if (inputText == null || inputText.isEmpty()) {
					 inputText = "default testing content...";
				 }
				 byte[] inputBytes = inputText.getBytes();
				 long fileSize = inputBytes.length;
				 InputStream fileStream = new ByteArrayInputStream(inputBytes);
				 outputStore.write(fileName, fileStream, fileSize);

				 responseProperties.put("Content-Type", "application/json");
				 IPropertySet prop = RequestProperties();
				 String requestURL = (String)prop.getProperty("RequestContextURL");
				 String fileVirtualURL = requestURL + virtualOutputDir + "/" + fileName;
				 jsonResult.put("url", fileVirtualURL);
				 jsonResult.put("fileName", fileName);
				 jsonResult.put("fileSizeBytes", fileSize);
			 }
			 catch (Exception ex) {
				 jsonResult.put("error", ex.getMessage());
			 }
			 return jsonResult.toString().getBytes(StandardCharsets.UTF_8);
		 }

	 /**
	  * Handles REST request by determining whether an operation or resource has been invoked and then forwards the
	  * request to appropriate Java methods, along with request and response properties
	  */
	 public byte[] handleRESTRequest(String capabilities, String resourceName, String operationName,
																	 String operationInput, String outputFormat, String requestProperties, String[] responseProperties)
			 throws IOException, AutomationException {
		 // parse request properties, create a map to hold request properties
		 JSONObject requestPropertiesJSON = new JSONObject(requestProperties);

		 // create a response properties map to hold properties of response
		 java.util.Map<String, String> responsePropertiesMap = new HashMap<String, String>();

		 try {
			 // if no operationName is specified send description of specified
			 // resource
			 byte[] response;
			 if (operationName.isEmpty()) {
				 if (resourceName.isEmpty()) {
					 return getRootResource(outputFormat, requestPropertiesJSON, responsePropertiesMap);
				 } else if (resourceName.equals("Files")) {
					 JSONObject input = new JSONObject(operationInput);
					 return getSubresourcefiles(input, responsePropertiesMap);
				 }
				 throw new Exception("No operation specified for resource " + resourceName + ".");
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
			 return ServerUtilities.sendError(0, message, null).getBytes(StandardCharsets.UTF_8);
		 }
	 }

	 /**
		* This method returns the resource hierarchy of a REST based SOE in JSON format.
		*/
	 public String getSchema() throws IOException, AutomationException {
		 try {
			 JSONObject _DownloadFileRESTSOE = ServerUtilities.createResource("DownloadFileRESTSOE",
					 "This SOE generates a text file on the Server and allows clients to download the file from the Server.\n It also provides REST endpoints to manage those files such as obtaining file names and deleting the files.",
					 false, false);
			 JSONArray opArray = new JSONArray();
			 opArray.put(ServerUtilities.createOperation("WriteFile", "fileName, inputText", "json", false));
			 opArray.put(ServerUtilities.createOperation("DownloadFile", "fileName", "file", false));
			 opArray.put(ServerUtilities.createOperation("DeleteFile", "fileName", "json", false));
			 _DownloadFileRESTSOE.put("operations", opArray);
			 JSONArray subResourceArray = new JSONArray();
			 JSONObject _Files = ServerUtilities.createResource("Files", "Files description", false, false);
			 subResourceArray.put(_Files);
			 _DownloadFileRESTSOE.put("resources", subResourceArray);
			 return _DownloadFileRESTSOE.toString();
		 } catch (JSONException e) {
			 e.printStackTrace();
		 }
		 return null;

	 }

	 private IPropertySet RequestProperties() throws AutomationException, IOException {
		 EnvironmentManager envMgr = new EnvironmentManager();
		 UID envUID = new UID();
		 envUID.setValue("{32d4c328-e473-4615-922c-63c108f55e60}");
		 Object envObj = envMgr.getEnvironment(envUID);
		 IServerEnvironment2 serverEnvironment = new IServerEnvironment2Proxy(envObj);
		 IPropertySet reqPropertySet = serverEnvironment.getProperties();
		 return reqPropertySet;
	 }
}
package entsdksamples.soe;

import java.awt.image.ColorConvertOp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
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
import java.nio.file.Files;
import java.nio.file.Paths;

import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.SOIHelper;
import com.esri.arcgis.system.EnvironmentManager;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.ServerUtilities;
import com.esri.arcgis.system.UID;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.carto.IMapServerDataAccess;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.IServerEnvironment2;
import com.esri.arcgis.system.IServerEnvironment2Proxy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Download File REST SOE", description = "Java Sample Download File REST SOE ")

public class JavaDownloadFileRESTSOE implements IServerObjectExtension, IRESTRequestHandler {
	private ILog serverLog;
    private IServerObjectHelper soHelper; 
	private String localFilePath = "";
	private String virtualFilePath = "";
	private String virtualOutputDir = "";
	
	public JavaDownloadFileRESTSOE() throws Exception {
		super();
	}

	public void init(IServerObjectHelper soh) throws IOException, AutomationException {
		this.serverLog = ServerUtilities.getServerLogger();
		this.serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName() + " SOE.");
		this.soHelper = soh;
		String configName = this.soHelper.getServerObject().getConfigurationName();
		String soeName = this.soHelper.getServerObject().getTypeName();
		String outputDir = "";
		try {
			IPropertySet serverProps = new SOIHelper().queryConfigurationProperties(configName, soeName);	
			outputDir = (String)serverProps.getProperty("outputDir");
			virtualOutputDir = (String)serverProps.getProperty("virtualOutputDir");
		}
		catch(Exception ignore){
			if (outputDir.length()<1)
				outputDir = "C:\\arcgisserver\\directories\\arcgisoutput";
			if (virtualOutputDir.length()<1)
				virtualOutputDir = "/rest/directories/arcgisoutput";
		}
		finally {
			localFilePath = outputDir + File.separator + configName.replace("/", File.separator) + "_" + soeName;
			virtualFilePath = configName + "_" + soeName;
		}
	}

	public void shutdown() throws IOException, AutomationException {
		this.serverLog.addMessage(3, 200, "Shutting down " + this.getClass().getName() + " SOE.");
		this.serverLog = null;
	}

	/**
	* Method for implementing REST operation "DownloadFile"'s functionality.
	* @param String operationInput JSON representation of input
	* @return String JSON representation of output
	*/
	private byte[] DownloadFile(JSONObject operationInput, String outputFormat, JSONObject requestPropertiesJSON,
			java.util.Map<String, String> responseProperties) throws Exception {
		
		String fileId = UUID.randomUUID().toString().substring(0, 7);
		String fileName = "testFile_" + fileId + ".txt";
		String file = localFilePath + File.separator + fileName; 
		String inputText = operationInput.getString("inputText");
		if (inputText == null || inputText.isEmpty()) {
			inputText = "default testing content...";
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(inputText);	     
	    writer.close();
		long fileSize = new File(file).length();
	    
		if (outputFormat.equals("json")) {
			responseProperties.put("Content-Type", "application/json");
			IPropertySet prop = RequestProperties();
			String requestURL = (String)prop.getProperty("RequestContextURL");
			String fileVirtualURL = requestURL + virtualOutputDir + "/" + virtualFilePath + "/" + fileName;
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("url", fileVirtualURL);
			jsonResult.put("fileName", fileName);
			jsonResult.put("fileSizeBytes", fileSize);
			return jsonResult.toString().getBytes("utf-8");
		}
		else if (outputFormat.equals("file")) {
			responseProperties.put("Content-Type", "application/octet-stream");
			responseProperties.put("Content-Disposition", "attachment; filename=" + fileName);
			return Files.readAllBytes(Paths.get(file));
		}
		else
			return new JSONObject("Only JSON or File format is supported.").toString().getBytes("utf-8");
	}

	/**
	* Method for implementing REST operation "DeleteFile"'s functionality.
	* @param String operationInput JSON representation of input
	* @return String JSON representation of output
	*/
	private byte[] DeleteFile(JSONObject operationInput, String outputFormat, JSONObject requestPropertiesJSON,
			java.util.Map<String, String> responseProperties) throws Exception {
		
		responseProperties.put("Content-Type", "application/json");
		String fileName = operationInput.getString("fileName");
		String file = localFilePath + File.separator + fileName; 
		if (fileName == null || fileName.isEmpty() || !Files.exists(Paths.get(file))) {
			return new JSONObject().put("error", "file not found.").toString().getBytes("utf-8");
		}
		try {
			Files.delete(Paths.get(file));
			return new JSONObject().put("success", true).toString().getBytes("utf-8");

		}
		catch(Exception e) {
			return new JSONObject().put("error", e.getMessage()).toString().getBytes("utf-8");
			}	
		}


	private byte[] getRootResource(String outputFormat, JSONObject requestPropertiesJSON,
			java.util.Map<String, String> responsePropertiesMap) throws Exception {
		JSONObject json = new JSONObject();
		json.put("name", "root resource");
		json.put("description",
				"This SOE generates a text file on the Server and allows clients to download the file from the Server.\n It also provides REST endpoints to manage those files such as obtaining file names and deleting the files.");
		return json.toString().getBytes("utf-8");
	}

	/**
	* Returns JSON representation of Files resource.
	* This resource is not a collection.
	* @return String JSON representation of Files resource.
	*/
	private byte[] getSubresourcefiles(String capabilitiesList, String outputFormat, JSONObject requestPropertiesJSON,
			java.util.Map<String, String> responsePropertiesMap) throws Exception {
		
		responsePropertiesMap.put("Content-Type", "application/json");
		File[] files = new File(localFilePath).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".txt");
			}
		});
		JSONArray filesArr = new JSONArray();
		for(File f: files) {
			JSONObject fileJson = new JSONObject();
			fileJson.put("filename", f.getName());
			filesArr.put(fileJson);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("files", filesArr);
		
		return resultJson.toString().getBytes("utf-8");
	}

	/**
	* Returns JSON representation of specified resource.
	* @return String JSON representation of specified resource.
	*/
	private byte[] getResource(String capabilitiesList, String resourceName, String outputFormat,
			JSONObject requestPropertiesJSON, java.util.Map<String, String> responsePropertiesMap) throws Exception {
		if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
			return getRootResource(outputFormat, requestPropertiesJSON, responsePropertiesMap);
		} else if (resourceName.equals("Files")) {
			return getSubresourcefiles(capabilitiesList, outputFormat, requestPropertiesJSON, responsePropertiesMap);
		}

		return null;
	}

	/**
	* Invokes specified REST operation on specified REST resource
	* @param capabilitiesList
	* @param resourceName
	* @param operationName
	* @param operationInput
	* @param outputFormat
	* @param requestPropertiesMap
	* @param responsePropertiesMap
	* @return byte[]
	*/
	private byte[] invokeRESTOperation(String capabilitiesList, String resourceName, String operationName,
			String operationInput, String outputFormat, JSONObject requestPropertiesJSON,
			java.util.Map<String, String> responsePropertiesMap) throws Exception {
		JSONObject operationInputAsJSON = new JSONObject(operationInput);
		byte[] operationOutput = null;

		//permitted capabilities list can be used to allow/block access to operations

		if (resourceName.equalsIgnoreCase("") || resourceName.length() == 0) {
			if (operationName.equalsIgnoreCase("DownloadFile")) {
				operationOutput = DownloadFile(operationInputAsJSON, outputFormat, requestPropertiesJSON,
						responsePropertiesMap);
			} else if (operationName.equalsIgnoreCase("DeleteFile")) {
				operationOutput = DeleteFile(operationInputAsJSON, outputFormat, requestPropertiesJSON,
						responsePropertiesMap);
			}
		} else //if non existent sub-resource specified, report error
		{
			return ServerUtilities
					.sendError(0, "No sub-resource by name " + resourceName + " found.", new String[] { "" })
					.getBytes("utf-8");
		}

		return operationOutput;
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
			return ServerUtilities.sendError(0, message, null).getBytes("utf-8");
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
			JSONArray _DownloadFileRESTSOE_OpArray = new JSONArray();
			_DownloadFileRESTSOE_OpArray
					.put(ServerUtilities.createOperation("DownloadFile", "inputText", "json, file", false));
			_DownloadFileRESTSOE_OpArray
					.put(ServerUtilities.createOperation("DeleteFile", "fileName", "json", false));
			_DownloadFileRESTSOE.put("operations", _DownloadFileRESTSOE_OpArray);
			JSONArray _DownloadFileRESTSOE_SubResourceArray = new JSONArray();
			JSONObject _Files = ServerUtilities.createResource("Files", "Files description", false, false);
			_DownloadFileRESTSOE_SubResourceArray.put(_Files);
			_DownloadFileRESTSOE.put("resources", _DownloadFileRESTSOE_SubResourceArray);
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
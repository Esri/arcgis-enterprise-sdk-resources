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

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import com.esri.arcgis.system.IWebRequestHandlerProxy;
import com.esri.arcgis.system.ServerUtilities;

import javax.imageio.ImageIO;

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
        displayName = "Java Watermark SOI",
        description = "Overlays a watermark on the export image operation",
        interceptor = true,
        servicetype =  "MapService" ,
		properties = "" ,
		supportsSharedInstances = true)

public class ApplyWatermarkSOI
		implements IServerObjectExtension, IRESTRequestHandler, IWebRequestHandler, IRequestHandler2, IRequestHandler
     {

	private static final long serialVersionUID = 1L;
	private static final String ARCGISHOME_ENV = "AGSSERVER";
	private ILog serverLog;
	private IServerObject so;
	private SOIHelper soiHelper;

	 // Text used as watermark
	 private static final String WATERMARK_STRING = "© ESRI Inc.";
	 // Path to arcgis output directory
	 private static final String OUTPUT_DIRECTORY = "C:/arcgisserver/directories/arcgisoutput";

	 /**
	 * Default constructor.
	 *
	 * @throws Exception
	 */
	public ApplyWatermarkSOI() throws Exception {
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
		this.soiHelper = new SOIHelper(arcgisHome + "XmlSchema" + File.separator + "MapServer.wsdl");
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
		 // Log message with server
		 serverLog.addMessage(3, 200, "Request received in Watermark SOI for handleRESTRequest");

		 /*
		  * Add code to manipulate REST requests here
		  */

		 // Find the correct delegate to forward the request too
		 IRESTRequestHandler restRequestHandler = soiHelper.findRestRequestHandlerDelegate(so);
		 if (restRequestHandler != null) {
			 /*
			  * Get the response.
			  */
			 byte[] response =
					 restRequestHandler.handleRESTRequest(capabilities, resourceName, operationName, operationInput, outputFormat,
							 requestProperties, responseProperties);

			 /*
			  * Manipulate the response.
			  *
			  * Add watermark
			  */

			 if (operationName.equalsIgnoreCase("export")) {
				 try {
					 BufferedImage sourceImage = null;
					 if (outputFormat.equalsIgnoreCase("image")) {
						 sourceImage = byteArrayToBufferedImage(response);

						 byte[] watermarkedImage =
								 addTextWatermark(WATERMARK_STRING, sourceImage, new JSONObject(operationInput).getString("format"),
										 outputFormat, null);

						 // return the watermarked image
						 if (watermarkedImage != null)
							 return watermarkedImage;
					 } else if (outputFormat.equalsIgnoreCase("json")) {
						 // Generate output file location
						 File outputImageFileLocation =
								 getOutputImageFileLocation(new JSONObject(new String(response)).getString("href"));
						 sourceImage = fileToBufferedImage(outputImageFileLocation);

						 //byte[] watermarkedImage =
						 //addTextWatermark(WATERMARK_STRING, sourceImage, new JSONObject(operationInput).getString("format"),
						 //outputFormat, outputImageFileLocation);

						 // return response as is because we have modified the file its pointing to
						 return response;
					 } else if (outputFormat.equalsIgnoreCase("kmz")) {
						 // Note: Watermark can be added for the kmz format too. In this example we didn't
						 // implement it.
						 return response;
					 } else {
						 return response;
					 }
				 } catch (Exception ignore) {
					 // Note: If there is an error adding the watermark
					 // don't return anything.
					 return new JSONObject()
							 .put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
							 .getBytes();
				 }
			 }

			 return response;
		 }

		 return new JSONObject()
				 .put("error", new JSONObject().put("code", 404).put("message", "Not Found")).toString()
				 .getBytes();
	 }

	 /**
	  * Generate physical file path from virtual path
	  *
	  * @param virtualPath Path returned by the MapServer SO
	  * @return
	  */
	 private File getOutputImageFileLocation(String virtualPath) {
		 /*
		  * Sample output returned by MapServer SO
		  *
		  * example : /rest/directories/arcgisoutput/SampleWorldCities_MapServer/
		  * _ags_map26c62f8c2c0c4965b53e87e300e1912f.png
		  */
		 String[] virtualPathParts = virtualPath.split("/");
		 String imageFileLocation = OUTPUT_DIRECTORY;

		 // build the physical path to the image file
		 boolean buildPath = false;
		 for (String virtualPathPart : virtualPathParts) {
			 if (buildPath) {
				 imageFileLocation += "/" + virtualPathPart;
			 }
			 if (virtualPathPart.equalsIgnoreCase("arcgisoutput")) {
				 buildPath = true;
			 }
		 }

		 return new File(imageFileLocation);
	 }

	 /**
	  * Convert byte array to BufferedImage
	  *
	  * @param sourceImageBytes
	  * @return
	  * @throws IOException
	  */
	 private BufferedImage byteArrayToBufferedImage(byte[] sourceImageBytes) throws IOException {
		 return ImageIO.read(new ByteArrayInputStream(sourceImageBytes));
	 }

	 /**
	  * Convert BufferedImage to byte array
	  *
	  * @param sourceImage
	  * @param imageType png or jpeg
	  * @return
	  * @throws IOException
	  */
	 private byte[] bufferedImagetoByteArray(BufferedImage sourceImage, String imageType) throws IOException {
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 ImageIO.write(sourceImage, (imageType.startsWith("png")) ? "PNG" : "JPG", baos);
		 baos.flush();
		 byte[] imageInBA = baos.toByteArray();
		 baos.close();
		 return imageInBA;
	 }

	 /**
	  * Read an Image file and convert it into a BufferedImage
	  *
	  * @param sourceImageFile
	  * @return
	  * @throws IOException
	  */
	 private BufferedImage fileToBufferedImage(File sourceImageFile) throws IOException {
		 return ImageIO.read(sourceImageFile);
	 }

	 /**
	  * Write image to disk
	  *
	  * @param sourceImage
	  * @param imageType
	  * @param outputImageFile
	  * @throws IOException
	  */
	 private void writeImageToDisk(BufferedImage sourceImage, String imageType, File outputImageFile) throws IOException {
		 ImageIO.write(sourceImage, (imageType.startsWith("png")) ? "PNG" : "JPG", outputImageFile);
	 }

	 /**
	  * Add watermark to an image
	  *
	  * @param watermarkText Text to be added as watermark.
	  * @param sourceImage Image data in a byte array
	  * @param imageType Type of image (png, jpeg etc.)
	  * @param outputFormat json or image or kmz
	  * @param outputImageFile
	  * @return
	  * @throws IOException
	  */
	 private byte[] addTextWatermark(String watermarkText, BufferedImage sourceImage, String imageType,
									 String outputFormat, File outputImageFile) throws IOException {
		 // Create BufferedImage and Graphics2D objects
		 Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

		 // Initializes necessary graphic properties
		 AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		 g2d.setComposite(alphaChannel);
		 g2d.setColor(Color.BLUE);
		 g2d.setFont(new Font("Arial", Font.BOLD, 64));
		 FontMetrics fontMetrics = g2d.getFontMetrics();
		 Rectangle2D rect = fontMetrics.getStringBounds(watermarkText, g2d);

		 // Calculate the center
		 int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
		 int centerY = sourceImage.getHeight() / 2;

		 // Add watermark at the center of image
		 g2d.drawString(watermarkText, centerX, centerY);
		 g2d.dispose();

		 if (outputFormat.equalsIgnoreCase("image")) {
			 return bufferedImagetoByteArray(sourceImage, imageType);
		 } else if (outputFormat.equalsIgnoreCase("json")) {
			 // replace watermarked image with original image
			 writeImageToDisk(sourceImage, imageType, outputImageFile);
			 return null;
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
		 serverLog.addMessage(3, 200, "Request received in Sample Object Interceptor for handleStringRequest");

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
		 serverLog.addMessage(3, 200, "Request received in Sample Object Interceptor for handleBinaryRequest");

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

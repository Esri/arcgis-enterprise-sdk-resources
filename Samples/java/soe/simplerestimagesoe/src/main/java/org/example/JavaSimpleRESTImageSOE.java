package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.esri.arcgis.carto.IImageServerInit;
import com.esri.arcgis.datasourcesraster.IMosaicDataset;
import com.esri.arcgis.datasourcesraster.IRasterBand;
import com.esri.arcgis.datasourcesraster.IRasterBandCollection;
import com.esri.arcgis.datasourcesraster.IRasterStatistics;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geodatabase.IMosaicDatasetName;
import com.esri.arcgis.geodatabase.IRasterCatalogItem;
import com.esri.arcgis.geodatabase.IRasterDataset;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.json.JSONArray;
import com.esri.arcgis.server.json.JSONException;
import com.esri.arcgis.server.json.JSONObject;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.IName;
import com.esri.arcgis.system.IRESTRequestHandler;
import com.esri.arcgis.system.ServerUtilities;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Simple REST Image SOE",
		description = "Java Simple REST Image SOE.",
		defaultSOAPCapabilities = "",
		allSOAPCapabilities = "",
		properties = "",
		servicetype = "ImageService",
		supportsSharedInstances = true
)
public class JavaSimpleRESTImageSOE implements IServerObjectExtension,
		IRESTRequestHandler {
	private static final long serialVersionUID = 1L;
	private IServerObjectHelper soHelper;
	private ILog serverLog;
	private IImageServerInit imageServer;

	public JavaSimpleRESTImageSOE() throws Exception {
		super();
	}

	public void init(IServerObjectHelper soh) throws IOException,
			AutomationException {
		this.soHelper = soh;
		this.serverLog = ServerUtilities.getServerLogger();
		this.imageServer = (IImageServerInit) this.soHelper.getServerObject();

		serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName()
				+ " SOE.");
	}

	public void shutdown() throws IOException, AutomationException {
		serverLog.addMessage(3, 200, "Shutting down "
				+ this.getClass().getName() + " SOE.");
		this.soHelper = null;
		this.serverLog = null;
		this.imageServer = null;
	}

	private byte[] getRootResource() throws Exception {
		JSONObject json = new JSONObject();
		json.put("name", "Java Simple Image service REST SOE");
		json.put(
				"description",
				"Simple REST Image Service SOE with 1 sub-resource called \"layers\" and 1 operation called \"getLayerCountByType\".");
		json.put("usage",
				"The \"layers\" subresource returns all layers in the image service.");
		return json.toString().getBytes(StandardCharsets.UTF_8);
	}

	private byte[] getSubresourceLayers(
			Map<String, String> responsePropertiesMap) throws Exception {
		JSONObject json = new JSONObject();
		json.put("layers", getLayersInfoAsJSON());

		return json.toString().getBytes(StandardCharsets.UTF_8);
	}

	public JSONObject getLayersInfoAsJSON() throws Exception {
		JSONObject json = new JSONObject();

		IName imageName = this.imageServer.getImageDataSourceName();
		if (imageName instanceof IMosaicDatasetName) {
			IMosaicDataset md = (IMosaicDataset) imageName.open();
			IFeatureClass mosaicCatalogFC = md.getCatalog();
			IFeatureCursor featureCursor = mosaicCatalogFC.search(null, false);
			IFeature feature = featureCursor.nextFeature();
			JSONArray rasterCatItemsArray = new JSONArray();
			while (feature != null) {
				IRasterCatalogItem rasterCatlogItem = (IRasterCatalogItem) feature;
				if (rasterCatlogItem != null) {
					IRasterDataset rasterDataset = rasterCatlogItem
							.getRasterDataset();
					rasterCatItemsArray
							.put(getRasterDatasetJSON(rasterDataset));
				}

				feature = featureCursor.nextFeature();
			}

			json.put("mosaicDatasetName", mosaicCatalogFC.getAliasName());
			json.put("mosaicFeatureType", mosaicCatalogFC.getFeatureType());
			json.put("shapeFieldName", mosaicCatalogFC.getShapeFieldName());
		}
		/*
		 * else if(imageName instanceof IRasterDatasetName) { IRasterDataset rd
		 * = (IRasterDataset) imageName; json = getRasterDatasetJSON(rd); } else
		 * if(imageName instanceof IRasterCatalogName) { //IRasterCatalogName rc
		 * = (IRasterCatalogName) imageName; json = new
		 * JSONObject().put("message", "no info available yet"); }
		 */

		return json;
	}

	private JSONObject getRasterDatasetJSON(IRasterDataset rasterDataset)
			throws JSONException, IOException, AutomationException {
		JSONObject rasterCatJSON = new JSONObject();
		rasterCatJSON.put("completeName", rasterDataset.getCompleteName());
		rasterCatJSON.put("compressionType", rasterDataset.getCompressionType());
		rasterCatJSON.put("format", rasterDataset.getFormat());
		rasterCatJSON.put("sensorType", rasterDataset.getSensorType());

		IRasterBandCollection rasterBandsCol = (IRasterBandCollection) rasterDataset;
		JSONArray rasterBandsArray = new JSONArray();
		for (int i = 0; i < rasterBandsCol.getCount(); i++) {
			JSONObject bandStatsJSON = new JSONObject();

			IRasterBand rasterBand = rasterBandsCol.item(i);
			bandStatsJSON.put("name", rasterBand.getBandname());
			bandStatsJSON.put("representationType",
			rasterBand.getRepresentationType());

			IRasterStatistics statistics = rasterBand.getStatistics();
			bandStatsJSON.put("maxValues", statistics.getMaximum());
			bandStatsJSON.put("minValues", statistics.getMinimum());
			bandStatsJSON.put("meanValues", statistics.getMean());
			bandStatsJSON.put("stdDevvValues",
			statistics.getStandardDeviation());

			rasterBandsArray.put(bandStatsJSON);
		}
		rasterCatJSON.put("bands", rasterBandsArray);

		return rasterCatJSON;
	}

	private byte[] getResource(String resourceName,
														 Map<String, String> responsePropertiesMap) throws Exception {
		if (resourceName.isEmpty()) {
			return getRootResource();
		} else if (resourceName.equalsIgnoreCase("layers")) {
			return getSubresourceLayers(responsePropertiesMap);
		}

		return null;
	}

	@Override
	public byte[] handleRESTRequest(String capabilities, String resourceName,
																	String operationName, String operationInput, String outputFormat,
																	String requestProperties, String[] responseProperties)
			throws IOException, AutomationException {
		try {
			Map<String, String> responsePropertiesMap = new HashMap<String, String>();
			responsePropertiesMap.put("Content-Type", "application/json");

			byte[] response = null;
			if (operationName.isEmpty()) {
				response = getResource(resourceName, responsePropertiesMap);
			} else {
				response = ServerUtilities.sendError(500,
						"No operations defined.",
						new String[] { "No details specified." }).getBytes(
						StandardCharsets.UTF_8);
			}

			JSONObject responsePropertiesJSON = new JSONObject(
					responsePropertiesMap);
			responseProperties[0] = responsePropertiesJSON.toString();

			return response;
		} catch (Exception e) {
			this.serverLog.addMessage(1, 500, e.getMessage());
			return ServerUtilities.sendError(500,
					"Exception occurred: " + e.getMessage(),
					new String[] { "No details specified." }).getBytes(StandardCharsets.UTF_8);
		}
	}

	public String getSchema() throws IOException, AutomationException {
		try {
			JSONObject simpleRESTSOE = ServerUtilities.createResource(
					"JavaSimpleRESTImageSOE", "Java Simple REST Image SOE",
					false, false);
			JSONArray _subResourcesArray = new JSONArray();
			_subResourcesArray.put(ServerUtilities.createResource("layers",
					"layers in associated image service", false, false));
			simpleRESTSOE.put("resources", _subResourcesArray);

			return simpleRESTSOE.toString();
		} catch (JSONException e) {
			this.serverLog.addMessage(1, 500, e.getMessage());
			return ServerUtilities.sendError(500,
					"Exception occurred: " + e.getMessage(), null);
		}
	}
}
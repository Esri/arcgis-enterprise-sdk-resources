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
import java.net.UnknownHostException;

import com.esri.arcgis.carto.ILayerDescriptions;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerDataAccess;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.carto.LayerDescription;
import com.esri.arcgis.carto.LayerResultOptions;
import com.esri.arcgis.carto.QueryResult;
import com.esri.arcgis.carto.QueryResultOptions;
import com.esri.arcgis.carto.esriQueryResultFormat;
import com.esri.arcgis.geodatabase.FeatureClass;
import com.esri.arcgis.geodatabase.GeometryResultOptions;
import com.esri.arcgis.geodatabase.RecordSet;
import com.esri.arcgis.geodatabase.SpatialFilter;
import com.esri.arcgis.geodatabase.esriFeatureType;
import com.esri.arcgis.geodatabase.esriSpatialRelEnum;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polygon;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.SOAPRequestHandler;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.ServerUtilities;

import entsdksamples.soe.IJavaFindNearbyFeatures;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "Java Find Nearby Features SOAP SOE", description = "Java Find Nearby Features SOAP SOE")
public class JavaFindNearbyFeaturesSOAPSOE extends SOAPRequestHandler implements
	IServerObjectExtension, IJavaFindNearbyFeatures {
    private static final long serialVersionUID = 1L;
    private ILog serverLog;
    private IMapServerDataAccess mapServerDataAccess;

    public JavaFindNearbyFeaturesSOAPSOE() throws Exception {
	super();
    }

    /****************************************************************************************************************************
     * IServerObjectExtension methods: This is a mandatory interface that must
     * be supported by all SOEs. This interface is used by the Server Object to
     * manage the lifetime of the SOE and includes two methods: init() and
     * shutdown(). The Server Object cocreates the SOE and calls the init()
     * method handing it a back reference to the Server Object via the Server
     * Object Helper argument. The Server Object Helper implements a weak
     * reference on the Server Object. The extension can keep a strong reference
     * on the Server Object Helper (for example, in a member variable) but
     * should not keep a strong reference on the Server Object.
     * 
     * The log entries are merely informative and completely optional.
     ****************************************************************************************************************************/
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
	this.serverLog = ServerUtilities.getServerLogger();
	this.mapServerDataAccess = (IMapServerDataAccess) soh.getServerObject();
	this.serverLog.addMessage(3, 200, "Initialized "
		+ this.getClass().getName() + " SOE.");
    }

    /**
     * shutdown() is called once when the Server Object's context is being shut
     * down and is about to go away.
     */
    public void shutdown() throws IOException, AutomationException {
	/*
	 * The SOE should release its reference on the Server Object Helper.
	 */
	this.serverLog.addMessage(3, 200, "Shutting down "
		+ this.getClass().getName() + " SOE.");
	this.serverLog = null;
	this.mapServerDataAccess = null;
    }

    /*************************************************************************************
     * SOAP methods
     *************************************************************************************/
    public RecordSet findNearbyFeatures(int layerId, Point location,
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
			getLayerDescription(mapServerInfo, layerId),
			spatialFilter, qResultOptions);
		return (RecordSet) queryResult.getObject();
	    }
	}

	return null;
    }

    /*************************************************************************************
     * SOE Util methods
     *************************************************************************************/
    /**
     * Retrieve layer description
     */
    private LayerDescription getLayerDescription(IMapServerInfo mapServerInfo,
	    int layerID) {
	try {
	    IMapServerInfo mapServerInfo3 = (IMapServerInfo) mapServerInfo;
	    ILayerDescriptions layerDescriptions = mapServerInfo3
		    .getDefaultMapDescription().getLayerDescriptions();
	    for (int i = 0; i < mapServerInfo.getMapLayerInfos().getCount(); i++) {
		LayerDescription layerDescription = (LayerDescription) layerDescriptions
			.getElement(i);
		if (layerDescription.getID() == layerID) {
		    GeometryResultOptions geomResultOptions = new GeometryResultOptions();
		    geomResultOptions.setDensifyGeometries(true);

		    LayerResultOptions resultOptions = new LayerResultOptions();
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
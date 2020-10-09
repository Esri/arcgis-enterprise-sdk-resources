package com.esri.samples;

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

import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerDataAccess;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.interop.extn.ArcGISExtension;
import com.esri.arcgis.server.IServerObjectExtension;
import com.esri.arcgis.server.IServerObjectHelper;
import com.esri.arcgis.server.SOAPRequestHandler;
import com.esri.arcgis.system.ILog;
import com.esri.arcgis.system.ServerUtilities;
import com.esri.arcgis.interop.extn.ServerObjectExtProperties;
import com.esri.arcgis.system.IObjectActivate;
import com.esri.arcgis.system.IObjectConstruct;
import com.esri.arcgis.system.IPropertySet;

@ArcGISExtension
@ServerObjectExtProperties(displayName = "SOAP SOE", description = "SOAP SOE.", 
		 properties = "" ,
		 allSOAPCapabilities = "" ,
		 defaultSOAPCapabilities = "" ,
		supportsSharedInstances = false)

public class MySoapSoe extends SOAPRequestHandler
		implements IServerObjectExtension, IObjectActivate, IMySoapSoe
		 {
	private ILog serverLog;
	private static final long serialVersionUID = 1L;
	private IMapServerDataAccess mapServerDataAccess;
	private IMapLayerInfos layerInfos;
	
	public MySoapSoe() throws Exception {
		super();
	}

	/****************************************************************************************************************************
	 * IServerObjectExtension methods:
	 * This is a mandatory interface that must be supported by all SOEs. 
	 * This interface is used by the Server Object to manage the lifecycle of the SOE and includes 
	 * two methods: init() and shutdown(). 
	 * The Server Object cocreates the SOE and calls the init() method handing it a back reference 
	 * to the Server Object via the Server Object Helper argument. The Server Object Helper implements 
	 * a weak reference on the Server Object. The extension can keep a strong reference on the Server 
	 * Object Helper (for example, in a member variable) but should not keep a strong reference 
	 * on the Server Object. 
	 *    
	 * The log entries are merely informative and completely optional. 
	 ****************************************************************************************************************************/
	/**
	 * init() is called once, when the instance of the SOE is created. 
	 */
	public void init(IServerObjectHelper soh) throws IOException, AutomationException {
		/*
		  * An SOE should retrieve a weak reference to the Server Object from the Server Object Helper in
		  * order to make any method calls on the Server Object and release the
		  * reference after making the method calls.
		 */
		this.serverLog = ServerUtilities.getServerLogger();
		this.mapServerDataAccess = (IMapServerDataAccess) soh.getServerObject();
		this.serverLog.addMessage(3, 200, "Initialized " + this.getClass().getName() + " SOE.");
		IMapServer ms = (IMapServer) this.mapServerDataAccess;
		serverLog.addMessage(3, 200,
			"ms.getDefaultMapName(): " + ms.getDefaultMapName());
		IMapServerInfo mapServerInfo = ms.getServerInfo(ms.getDefaultMapName());
		serverLog.addMessage(3, 200, "mapServerInfo.getName(): "
			+ mapServerInfo.getName());
		this.layerInfos = mapServerInfo.getMapLayerInfos();
		serverLog.addMessage(3, 200, "Initialized "
			+ this.getClass().getName() + " SOE.");

	}

	/**
	 * shutdown() is called once when the Server Object's context is being shut down and is 
	 * about to go away.
	 */
	public void shutdown() throws IOException, AutomationException {
		/*
		 * The SOE should release its reference on the Server Object Helper.
		 */
		this.serverLog.addMessage(3, 200, "Shutting down " + this.getClass().getName() + " SOE.");
		this.serverLog = null;
		this.mapServerDataAccess = null;
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

	
    public int getLayerCountByType(String type) throws Exception {
		if (type != null && !type.isEmpty()) {
			String aoType = "";
			if (type.equalsIgnoreCase("all")) {
			return layerInfos.getCount();
			} else if (type.equalsIgnoreCase("feature")) {
			aoType = "Feature Layer";
			} else if (type.equalsIgnoreCase("raster")) {
			aoType = "Raster Layer";
			} else if (type.equalsIgnoreCase("dataset")) {
			aoType = "Network Dataset Layer";
			}

			int count = 0;
			for (int i = 0; i < layerInfos.getCount(); i++) {
			if (layerInfos.getElement(i).getType().equalsIgnoreCase(aoType)) {
				count++;
			}
			}

			return count;
		} else {
			throw new Exception(
				"Invalid layer type provided. Accessble types are: \"all\", \"feature\", \"raster\", \"dataset\".");
		}
    }
}
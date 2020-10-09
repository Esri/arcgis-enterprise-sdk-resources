package com.esri.samples;


import com.esri.arcgis.interop.extn.ArcGISExtension;
@ArcGISExtension
public interface IMySoapSoe {
	public int getLayerCountByType(String type) throws Exception;
}
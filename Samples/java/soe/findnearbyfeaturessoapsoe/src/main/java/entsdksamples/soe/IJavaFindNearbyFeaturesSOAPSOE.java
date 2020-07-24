package entsdksamples.soe;


import com.esri.arcgis.geodatabase.RecordSet;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.interop.extn.ArcGISExtension;

@ArcGISExtension
public interface IJavaFindNearbyFeaturesSOAPSOE {
	public RecordSet findNearbyFeatures(int layerId, Point location,
										double distance) throws Exception;
}
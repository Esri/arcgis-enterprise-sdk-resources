package entsdksamples.soe;


import com.esri.arcgis.interop.extn.ArcGISExtension;

@ArcGISExtension
public interface IJavaSimpleSOAPSOE {
	public int getLayerCountByType(String type) throws Exception;
}
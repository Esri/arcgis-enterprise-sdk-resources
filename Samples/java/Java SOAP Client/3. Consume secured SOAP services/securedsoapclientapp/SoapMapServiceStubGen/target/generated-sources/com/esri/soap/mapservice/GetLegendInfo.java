
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MapName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LayerIDs" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfInt"/&amp;gt;
 *         &amp;lt;element name="LegendPatch" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}MapServerLegendPatch"/&amp;gt;
 *         &amp;lt;element name="ImageType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ImageType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mapName",
    "layerIDs",
    "legendPatch",
    "imageType"
})
@XmlRootElement(name = "GetLegendInfo")
public class GetLegendInfo {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "LayerIDs", required = true)
    protected ArrayOfInt layerIDs;
    @XmlElement(name = "LegendPatch", required = true)
    protected MapServerLegendPatch legendPatch;
    @XmlElement(name = "ImageType", required = true)
    protected ImageType imageType;

    /**
     * Gets the value of the mapName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Sets the value of the mapName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapName(String value) {
        this.mapName = value;
    }

    /**
     * Gets the value of the layerIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getLayerIDs() {
        return layerIDs;
    }

    /**
     * Sets the value of the layerIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setLayerIDs(ArrayOfInt value) {
        this.layerIDs = value;
    }

    /**
     * Gets the value of the legendPatch property.
     * 
     * @return
     *     possible object is
     *     {@link MapServerLegendPatch }
     *     
     */
    public MapServerLegendPatch getLegendPatch() {
        return legendPatch;
    }

    /**
     * Sets the value of the legendPatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapServerLegendPatch }
     *     
     */
    public void setLegendPatch(MapServerLegendPatch value) {
        this.legendPatch = value;
    }

    /**
     * Gets the value of the imageType property.
     * 
     * @return
     *     possible object is
     *     {@link ImageType }
     *     
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * Sets the value of the imageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageType }
     *     
     */
    public void setImageType(ImageType value) {
        this.imageType = value;
    }

}

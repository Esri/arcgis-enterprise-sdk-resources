
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for FeatureExtent complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FeatureExtent"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}MapArea"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DefaultScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="ExpandRatio" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="FeatureIDs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LayerID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="MapName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureExtent", propOrder = {
    "defaultScale",
    "expandRatio",
    "featureIDs",
    "layerID",
    "mapName"
})
public class FeatureExtent
    extends MapArea
{

    @XmlElement(name = "DefaultScale")
    protected double defaultScale;
    @XmlElement(name = "ExpandRatio", defaultValue = "1.0")
    protected double expandRatio;
    @XmlElement(name = "FeatureIDs")
    protected ArrayOfInt featureIDs;
    @XmlElement(name = "LayerID")
    protected int layerID;
    @XmlElement(name = "MapName", required = true)
    protected String mapName;

    /**
     * Gets the value of the defaultScale property.
     * 
     */
    public double getDefaultScale() {
        return defaultScale;
    }

    /**
     * Sets the value of the defaultScale property.
     * 
     */
    public void setDefaultScale(double value) {
        this.defaultScale = value;
    }

    /**
     * Gets the value of the expandRatio property.
     * 
     */
    public double getExpandRatio() {
        return expandRatio;
    }

    /**
     * Sets the value of the expandRatio property.
     * 
     */
    public void setExpandRatio(double value) {
        this.expandRatio = value;
    }

    /**
     * Gets the value of the featureIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getFeatureIDs() {
        return featureIDs;
    }

    /**
     * Sets the value of the featureIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setFeatureIDs(ArrayOfInt value) {
        this.featureIDs = value;
    }

    /**
     * Gets the value of the layerID property.
     * 
     */
    public int getLayerID() {
        return layerID;
    }

    /**
     * Sets the value of the layerID property.
     * 
     */
    public void setLayerID(int value) {
        this.layerID = value;
    }

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

}

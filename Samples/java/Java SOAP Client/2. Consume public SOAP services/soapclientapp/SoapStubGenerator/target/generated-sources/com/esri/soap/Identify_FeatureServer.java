
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &amp;lt;element name="MapDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapDescription"/&amp;gt;
 *         &amp;lt;element name="MapImageDisplay" type="{http://www.esri.com/schemas/ArcGIS/10.7}ImageDisplay"/&amp;gt;
 *         &amp;lt;element name="SearchShape" type="{http://www.esri.com/schemas/ArcGIS/10.7}Geometry"/&amp;gt;
 *         &amp;lt;element name="Tolerance" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="IdentifyOption" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriIdentifyOption"/&amp;gt;
 *         &amp;lt;element name="LayerIDs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt"/&amp;gt;
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
    "mapDescription",
    "mapImageDisplay",
    "searchShape",
    "tolerance",
    "identifyOption",
    "layerIDs"
})
@XmlRootElement(name = "Identify")
public class Identify_FeatureServer {

    @XmlElement(name = "MapDescription", required = true)
    protected MapDescription mapDescription;
    @XmlElement(name = "MapImageDisplay", required = true)
    protected ImageDisplay mapImageDisplay;
    @XmlElement(name = "SearchShape", required = true)
    protected Geometry searchShape;
    @XmlElement(name = "Tolerance")
    protected int tolerance;
    @XmlElement(name = "IdentifyOption", required = true)
    @XmlSchemaType(name = "string")
    protected EsriIdentifyOption identifyOption;
    @XmlElement(name = "LayerIDs", required = true)
    protected ArrayOfInt layerIDs;

    /**
     * Gets the value of the mapDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MapDescription }
     *     
     */
    public MapDescription getMapDescription() {
        return mapDescription;
    }

    /**
     * Sets the value of the mapDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapDescription }
     *     
     */
    public void setMapDescription(MapDescription value) {
        this.mapDescription = value;
    }

    /**
     * Gets the value of the mapImageDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link ImageDisplay }
     *     
     */
    public ImageDisplay getMapImageDisplay() {
        return mapImageDisplay;
    }

    /**
     * Sets the value of the mapImageDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageDisplay }
     *     
     */
    public void setMapImageDisplay(ImageDisplay value) {
        this.mapImageDisplay = value;
    }

    /**
     * Gets the value of the searchShape property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getSearchShape() {
        return searchShape;
    }

    /**
     * Sets the value of the searchShape property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setSearchShape(Geometry value) {
        this.searchShape = value;
    }

    /**
     * Gets the value of the tolerance property.
     * 
     */
    public int getTolerance() {
        return tolerance;
    }

    /**
     * Sets the value of the tolerance property.
     * 
     */
    public void setTolerance(int value) {
        this.tolerance = value;
    }

    /**
     * Gets the value of the identifyOption property.
     * 
     * @return
     *     possible object is
     *     {@link EsriIdentifyOption }
     *     
     */
    public EsriIdentifyOption getIdentifyOption() {
        return identifyOption;
    }

    /**
     * Sets the value of the identifyOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriIdentifyOption }
     *     
     */
    public void setIdentifyOption(EsriIdentifyOption value) {
        this.identifyOption = value;
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

}

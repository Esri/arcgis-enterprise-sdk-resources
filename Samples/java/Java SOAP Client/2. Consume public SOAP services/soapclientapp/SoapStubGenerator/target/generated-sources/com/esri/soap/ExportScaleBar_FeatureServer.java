
package com.esri.soap;

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
 *         &amp;lt;element name="ScaleBar" type="{http://www.esri.com/schemas/ArcGIS/10.7}ScaleBar" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MapDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapDescription"/&amp;gt;
 *         &amp;lt;element name="MapDisplay" type="{http://www.esri.com/schemas/ArcGIS/10.7}ImageDisplay"/&amp;gt;
 *         &amp;lt;element name="BackGroundColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ImageDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}ImageDescription"/&amp;gt;
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
    "scaleBar",
    "mapDescription",
    "mapDisplay",
    "backGroundColor",
    "imageDescription"
})
@XmlRootElement(name = "ExportScaleBar")
public class ExportScaleBar_FeatureServer {

    @XmlElement(name = "ScaleBar")
    protected ScaleBar scaleBar;
    @XmlElement(name = "MapDescription", required = true)
    protected MapDescription mapDescription;
    @XmlElement(name = "MapDisplay", required = true)
    protected ImageDisplay mapDisplay;
    @XmlElement(name = "BackGroundColor")
    protected Color backGroundColor;
    @XmlElement(name = "ImageDescription", required = true)
    protected ImageDescription imageDescription;

    /**
     * Gets the value of the scaleBar property.
     * 
     * @return
     *     possible object is
     *     {@link ScaleBar }
     *     
     */
    public ScaleBar getScaleBar() {
        return scaleBar;
    }

    /**
     * Sets the value of the scaleBar property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScaleBar }
     *     
     */
    public void setScaleBar(ScaleBar value) {
        this.scaleBar = value;
    }

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
     * Gets the value of the mapDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link ImageDisplay }
     *     
     */
    public ImageDisplay getMapDisplay() {
        return mapDisplay;
    }

    /**
     * Sets the value of the mapDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageDisplay }
     *     
     */
    public void setMapDisplay(ImageDisplay value) {
        this.mapDisplay = value;
    }

    /**
     * Gets the value of the backGroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getBackGroundColor() {
        return backGroundColor;
    }

    /**
     * Sets the value of the backGroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setBackGroundColor(Color value) {
        this.backGroundColor = value;
    }

    /**
     * Gets the value of the imageDescription property.
     * 
     * @return
     *     possible object is
     *     {@link ImageDescription }
     *     
     */
    public ImageDescription getImageDescription() {
        return imageDescription;
    }

    /**
     * Sets the value of the imageDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageDescription }
     *     
     */
    public void setImageDescription(ImageDescription value) {
        this.imageDescription = value;
    }

}

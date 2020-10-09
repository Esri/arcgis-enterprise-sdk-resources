
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
 *         &amp;lt;element name="MapDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapDescription"/&amp;gt;
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
    "mapDescription",
    "imageDescription"
})
@XmlRootElement(name = "ExportMapImage")
public class ExportMapImage_FeatureServer {

    @XmlElement(name = "MapDescription", required = true)
    protected MapDescription mapDescription;
    @XmlElement(name = "ImageDescription", required = true)
    protected ImageDescription imageDescription;

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

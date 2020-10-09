
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerLegendPatch complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerLegendPatch"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="ImageDPI" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="LinePatch" type="{http://www.esri.com/schemas/ArcGIS/10.7}LinePatch" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AreaPatch" type="{http://www.esri.com/schemas/ArcGIS/10.7}AreaPatch" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerLegendPatch", propOrder = {
    "width",
    "height",
    "imageDPI",
    "linePatch",
    "areaPatch"
})
public class MapServerLegendPatch {

    @XmlElement(name = "Width")
    protected double width;
    @XmlElement(name = "Height")
    protected double height;
    @XmlElement(name = "ImageDPI")
    protected double imageDPI;
    @XmlElement(name = "LinePatch")
    protected LinePatch linePatch;
    @XmlElement(name = "AreaPatch")
    protected AreaPatch areaPatch;

    /**
     * Gets the value of the width property.
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     */
    public void setWidth(double value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     */
    public void setHeight(double value) {
        this.height = value;
    }

    /**
     * Gets the value of the imageDPI property.
     * 
     */
    public double getImageDPI() {
        return imageDPI;
    }

    /**
     * Sets the value of the imageDPI property.
     * 
     */
    public void setImageDPI(double value) {
        this.imageDPI = value;
    }

    /**
     * Gets the value of the linePatch property.
     * 
     * @return
     *     possible object is
     *     {@link LinePatch }
     *     
     */
    public LinePatch getLinePatch() {
        return linePatch;
    }

    /**
     * Sets the value of the linePatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinePatch }
     *     
     */
    public void setLinePatch(LinePatch value) {
        this.linePatch = value;
    }

    /**
     * Gets the value of the areaPatch property.
     * 
     * @return
     *     possible object is
     *     {@link AreaPatch }
     *     
     */
    public AreaPatch getAreaPatch() {
        return areaPatch;
    }

    /**
     * Sets the value of the areaPatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaPatch }
     *     
     */
    public void setAreaPatch(AreaPatch value) {
        this.areaPatch = value;
    }

}

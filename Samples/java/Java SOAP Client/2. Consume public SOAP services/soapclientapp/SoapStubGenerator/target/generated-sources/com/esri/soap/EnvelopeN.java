
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for EnvelopeN complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="EnvelopeN"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="XMin" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="YMin" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="XMax" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="YMax" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="ZMin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZMax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MMin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MMax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeN", propOrder = {
    "xMin",
    "yMin",
    "xMax",
    "yMax",
    "zMin",
    "zMax",
    "mMin",
    "mMax",
    "spatialReference"
})
public class EnvelopeN
    extends Envelope
{

    @XmlElement(name = "XMin")
    protected double xMin;
    @XmlElement(name = "YMin")
    protected double yMin;
    @XmlElement(name = "XMax")
    protected double xMax;
    @XmlElement(name = "YMax")
    protected double yMax;
    @XmlElement(name = "ZMin")
    protected Double zMin;
    @XmlElement(name = "ZMax")
    protected Double zMax;
    @XmlElement(name = "MMin")
    protected Double mMin;
    @XmlElement(name = "MMax")
    protected Double mMax;
    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;

    /**
     * Gets the value of the xMin property.
     * 
     */
    public double getXMin() {
        return xMin;
    }

    /**
     * Sets the value of the xMin property.
     * 
     */
    public void setXMin(double value) {
        this.xMin = value;
    }

    /**
     * Gets the value of the yMin property.
     * 
     */
    public double getYMin() {
        return yMin;
    }

    /**
     * Sets the value of the yMin property.
     * 
     */
    public void setYMin(double value) {
        this.yMin = value;
    }

    /**
     * Gets the value of the xMax property.
     * 
     */
    public double getXMax() {
        return xMax;
    }

    /**
     * Sets the value of the xMax property.
     * 
     */
    public void setXMax(double value) {
        this.xMax = value;
    }

    /**
     * Gets the value of the yMax property.
     * 
     */
    public double getYMax() {
        return yMax;
    }

    /**
     * Sets the value of the yMax property.
     * 
     */
    public void setYMax(double value) {
        this.yMax = value;
    }

    /**
     * Gets the value of the zMin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZMin() {
        return zMin;
    }

    /**
     * Sets the value of the zMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZMin(Double value) {
        this.zMin = value;
    }

    /**
     * Gets the value of the zMax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZMax() {
        return zMax;
    }

    /**
     * Sets the value of the zMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZMax(Double value) {
        this.zMax = value;
    }

    /**
     * Gets the value of the mMin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMMin() {
        return mMin;
    }

    /**
     * Sets the value of the mMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMMin(Double value) {
        this.mMin = value;
    }

    /**
     * Gets the value of the mMax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMMax() {
        return mMax;
    }

    /**
     * Sets the value of the mMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMMax(Double value) {
        this.mMax = value;
    }

    /**
     * Gets the value of the spatialReference property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialReference }
     *     
     */
    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    /**
     * Sets the value of the spatialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialReference }
     *     
     */
    public void setSpatialReference(SpatialReference value) {
        this.spatialReference = value;
    }

}

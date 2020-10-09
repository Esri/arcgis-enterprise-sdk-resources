
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PointN complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PointN"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Point"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Z" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}SpatialReference" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointN", propOrder = {
    "x",
    "y",
    "m",
    "z",
    "id",
    "spatialReference"
})
public class PointN
    extends Point
{

    @XmlElement(name = "X")
    protected double x;
    @XmlElement(name = "Y")
    protected double y;
    @XmlElement(name = "M")
    protected Double m;
    @XmlElement(name = "Z")
    protected Double z;
    @XmlElement(name = "ID")
    protected Integer id;
    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;

    /**
     * Gets the value of the x property.
     * 
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(double value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(double value) {
        this.y = value;
    }

    /**
     * Gets the value of the m property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getM() {
        return m;
    }

    /**
     * Sets the value of the m property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setM(Double value) {
        this.m = value;
    }

    /**
     * Gets the value of the z property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZ() {
        return z;
    }

    /**
     * Sets the value of the z property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZ(Double value) {
        this.z = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setID(Integer value) {
        this.id = value;
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

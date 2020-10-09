
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for CircularArc complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CircularArc"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Segment"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CenterPoint" type="{http://www.esri.com/schemas/ArcGIS/10.7}Point"/&amp;gt;
 *         &amp;lt;element name="FromAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ToAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsCounterClockwise" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="IsMinor" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="IsLine" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CircularArc", propOrder = {
    "centerPoint",
    "fromAngle",
    "toAngle",
    "isCounterClockwise",
    "isMinor",
    "isLine"
})
public class CircularArc
    extends Segment
{

    @XmlElement(name = "CenterPoint", required = true)
    protected Point centerPoint;
    @XmlElement(name = "FromAngle")
    protected Double fromAngle;
    @XmlElement(name = "ToAngle")
    protected Double toAngle;
    @XmlElement(name = "IsCounterClockwise")
    protected boolean isCounterClockwise;
    @XmlElement(name = "IsMinor")
    protected boolean isMinor;
    @XmlElement(name = "IsLine")
    protected boolean isLine;

    /**
     * Gets the value of the centerPoint property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * Sets the value of the centerPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setCenterPoint(Point value) {
        this.centerPoint = value;
    }

    /**
     * Gets the value of the fromAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFromAngle() {
        return fromAngle;
    }

    /**
     * Sets the value of the fromAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFromAngle(Double value) {
        this.fromAngle = value;
    }

    /**
     * Gets the value of the toAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getToAngle() {
        return toAngle;
    }

    /**
     * Sets the value of the toAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setToAngle(Double value) {
        this.toAngle = value;
    }

    /**
     * Gets the value of the isCounterClockwise property.
     * 
     */
    public boolean isIsCounterClockwise() {
        return isCounterClockwise;
    }

    /**
     * Sets the value of the isCounterClockwise property.
     * 
     */
    public void setIsCounterClockwise(boolean value) {
        this.isCounterClockwise = value;
    }

    /**
     * Gets the value of the isMinor property.
     * 
     */
    public boolean isIsMinor() {
        return isMinor;
    }

    /**
     * Sets the value of the isMinor property.
     * 
     */
    public void setIsMinor(boolean value) {
        this.isMinor = value;
    }

    /**
     * Gets the value of the isLine property.
     * 
     */
    public boolean isIsLine() {
        return isLine;
    }

    /**
     * Sets the value of the isLine property.
     * 
     */
    public void setIsLine(boolean value) {
        this.isLine = value;
    }

}

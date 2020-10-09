
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for EllipticArc complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="EllipticArc"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Segment"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="EllipseStd" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="CenterPoint" type="{http://www.esri.com/schemas/ArcGIS/10.7}Point"/&amp;gt;
 *         &amp;lt;element name="Rotation" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="MinorMajorRatio" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="IsCounterClockwise" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="IsMinor" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EllipticArc", propOrder = {
    "ellipseStd",
    "centerPoint",
    "rotation",
    "minorMajorRatio",
    "isCounterClockwise",
    "isMinor"
})
public class EllipticArc
    extends Segment
{

    @XmlElement(name = "EllipseStd")
    protected boolean ellipseStd;
    @XmlElement(name = "CenterPoint", required = true)
    protected Point centerPoint;
    @XmlElement(name = "Rotation")
    protected double rotation;
    @XmlElement(name = "MinorMajorRatio")
    protected double minorMajorRatio;
    @XmlElement(name = "IsCounterClockwise")
    protected boolean isCounterClockwise;
    @XmlElement(name = "IsMinor")
    protected boolean isMinor;

    /**
     * Gets the value of the ellipseStd property.
     * 
     */
    public boolean isEllipseStd() {
        return ellipseStd;
    }

    /**
     * Sets the value of the ellipseStd property.
     * 
     */
    public void setEllipseStd(boolean value) {
        this.ellipseStd = value;
    }

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
     * Gets the value of the rotation property.
     * 
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Sets the value of the rotation property.
     * 
     */
    public void setRotation(double value) {
        this.rotation = value;
    }

    /**
     * Gets the value of the minorMajorRatio property.
     * 
     */
    public double getMinorMajorRatio() {
        return minorMajorRatio;
    }

    /**
     * Sets the value of the minorMajorRatio property.
     * 
     */
    public void setMinorMajorRatio(double value) {
        this.minorMajorRatio = value;
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

}

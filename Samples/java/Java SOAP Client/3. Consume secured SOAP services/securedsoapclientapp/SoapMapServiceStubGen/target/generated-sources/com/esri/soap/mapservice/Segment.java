
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for Segment complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Segment"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Curve"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="FromPoint" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Point"/&amp;gt;
 *         &amp;lt;element name="ToPoint" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Point"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Segment", propOrder = {
    "fromPoint",
    "toPoint"
})
@XmlSeeAlso({
    Line.class,
    EllipticArc.class,
    CircularArc.class,
    BezierCurve.class
})
public abstract class Segment
    extends Curve
{

    @XmlElement(name = "FromPoint", required = true)
    protected Point fromPoint;
    @XmlElement(name = "ToPoint", required = true)
    protected Point toPoint;

    /**
     * Gets the value of the fromPoint property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getFromPoint() {
        return fromPoint;
    }

    /**
     * Sets the value of the fromPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setFromPoint(Point value) {
        this.fromPoint = value;
    }

    /**
     * Gets the value of the toPoint property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getToPoint() {
        return toPoint;
    }

    /**
     * Sets the value of the toPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setToPoint(Point value) {
        this.toPoint = value;
    }

}

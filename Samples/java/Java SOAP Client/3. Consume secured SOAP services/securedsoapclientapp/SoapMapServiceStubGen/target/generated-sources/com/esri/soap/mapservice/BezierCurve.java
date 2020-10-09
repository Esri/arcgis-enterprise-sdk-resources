
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for BezierCurve complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="BezierCurve"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Segment"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Degree" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="ControlPointArray" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfPoint"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BezierCurve", propOrder = {
    "degree",
    "controlPointArray"
})
public class BezierCurve
    extends Segment
{

    @XmlElement(name = "Degree")
    protected int degree;
    @XmlElement(name = "ControlPointArray", required = true)
    protected ArrayOfPoint controlPointArray;

    /**
     * Gets the value of the degree property.
     * 
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the value of the degree property.
     * 
     */
    public void setDegree(int value) {
        this.degree = value;
    }

    /**
     * Gets the value of the controlPointArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPoint }
     *     
     */
    public ArrayOfPoint getControlPointArray() {
        return controlPointArray;
    }

    /**
     * Sets the value of the controlPointArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPoint }
     *     
     */
    public void setControlPointArray(ArrayOfPoint value) {
        this.controlPointArray = value;
    }

}

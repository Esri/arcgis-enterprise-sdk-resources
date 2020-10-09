
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for AlgorithmicColorRamp complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AlgorithmicColorRamp"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}ColorRamp"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Algorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FromColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}HsvColor" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ToColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}HsvColor" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlgorithmicColorRamp", propOrder = {
    "algorithm",
    "fromColor",
    "toColor"
})
public class AlgorithmicColorRamp
    extends ColorRamp
{

    @XmlElement(name = "Algorithm")
    protected String algorithm;
    @XmlElement(name = "FromColor")
    protected HsvColor fromColor;
    @XmlElement(name = "ToColor")
    protected HsvColor toColor;

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    /**
     * Gets the value of the fromColor property.
     * 
     * @return
     *     possible object is
     *     {@link HsvColor }
     *     
     */
    public HsvColor getFromColor() {
        return fromColor;
    }

    /**
     * Sets the value of the fromColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link HsvColor }
     *     
     */
    public void setFromColor(HsvColor value) {
        this.fromColor = value;
    }

    /**
     * Gets the value of the toColor property.
     * 
     * @return
     *     possible object is
     *     {@link HsvColor }
     *     
     */
    public HsvColor getToColor() {
        return toColor;
    }

    /**
     * Sets the value of the toColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link HsvColor }
     *     
     */
    public void setToColor(HsvColor value) {
        this.toColor = value;
    }

}

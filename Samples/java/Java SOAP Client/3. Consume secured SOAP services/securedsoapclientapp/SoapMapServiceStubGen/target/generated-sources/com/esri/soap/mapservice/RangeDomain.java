
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri Range Domain object.
 * 
 * &lt;p&gt;Java class for RangeDomain complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RangeDomain"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Domain"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MaxValue" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 *         &amp;lt;element name="MinValue" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeDomain", propOrder = {
    "maxValue",
    "minValue"
})
public class RangeDomain
    extends Domain
{

    @XmlElement(name = "MaxValue", required = true)
    protected Object maxValue;
    @XmlElement(name = "MinValue", required = true)
    protected Object minValue;

    /**
     * Gets the value of the maxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMaxValue(Object value) {
        this.maxValue = value;
    }

    /**
     * Gets the value of the minValue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMinValue(Object value) {
        this.minValue = value;
    }

}

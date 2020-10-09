
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri Geometry export options object.
 * 
 * &lt;p&gt;Java class for GeometryResultOptions complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="GeometryResultOptions"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DensifyGeometries" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaximumSegmentLength" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaximumDeviation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GeneralizeGeometries" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaximumAllowableOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeometryResultOptions", propOrder = {
    "densifyGeometries",
    "maximumSegmentLength",
    "maximumDeviation",
    "generalizeGeometries",
    "maximumAllowableOffset"
})
public class GeometryResultOptions {

    @XmlElement(name = "DensifyGeometries", defaultValue = "false")
    protected Boolean densifyGeometries;
    @XmlElement(name = "MaximumSegmentLength", defaultValue = "-1.0")
    protected Double maximumSegmentLength;
    @XmlElement(name = "MaximumDeviation", defaultValue = "0.0")
    protected Double maximumDeviation;
    @XmlElement(name = "GeneralizeGeometries", defaultValue = "false")
    protected Boolean generalizeGeometries;
    @XmlElement(name = "MaximumAllowableOffset", defaultValue = "0.0")
    protected Double maximumAllowableOffset;

    /**
     * Gets the value of the densifyGeometries property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDensifyGeometries() {
        return densifyGeometries;
    }

    /**
     * Sets the value of the densifyGeometries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDensifyGeometries(Boolean value) {
        this.densifyGeometries = value;
    }

    /**
     * Gets the value of the maximumSegmentLength property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumSegmentLength() {
        return maximumSegmentLength;
    }

    /**
     * Sets the value of the maximumSegmentLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumSegmentLength(Double value) {
        this.maximumSegmentLength = value;
    }

    /**
     * Gets the value of the maximumDeviation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumDeviation() {
        return maximumDeviation;
    }

    /**
     * Sets the value of the maximumDeviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumDeviation(Double value) {
        this.maximumDeviation = value;
    }

    /**
     * Gets the value of the generalizeGeometries property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGeneralizeGeometries() {
        return generalizeGeometries;
    }

    /**
     * Sets the value of the generalizeGeometries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGeneralizeGeometries(Boolean value) {
        this.generalizeGeometries = value;
    }

    /**
     * Gets the value of the maximumAllowableOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumAllowableOffset() {
        return maximumAllowableOffset;
    }

    /**
     * Sets the value of the maximumAllowableOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumAllowableOffset(Double value) {
        this.maximumAllowableOffset = value;
    }

}

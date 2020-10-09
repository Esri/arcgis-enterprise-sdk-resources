
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for NumericFormat complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="NumericFormat"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RoundingOption" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriRoundingOptionEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RoundingValue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AlignmentOption" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriNumericAlignmentEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AlignmentWidth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseSeparator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZeroPad" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ShowPlus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumericFormat", propOrder = {
    "roundingOption",
    "roundingValue",
    "alignmentOption",
    "alignmentWidth",
    "useSeparator",
    "zeroPad",
    "showPlus"
})
public class NumericFormat {

    @XmlElement(name = "RoundingOption")
    @XmlSchemaType(name = "string")
    protected EsriRoundingOptionEnum roundingOption;
    @XmlElement(name = "RoundingValue")
    protected Integer roundingValue;
    @XmlElement(name = "AlignmentOption")
    @XmlSchemaType(name = "string")
    protected EsriNumericAlignmentEnum alignmentOption;
    @XmlElement(name = "AlignmentWidth")
    protected Integer alignmentWidth;
    @XmlElement(name = "UseSeparator")
    protected Boolean useSeparator;
    @XmlElement(name = "ZeroPad")
    protected Boolean zeroPad;
    @XmlElement(name = "ShowPlus")
    protected Boolean showPlus;

    /**
     * Gets the value of the roundingOption property.
     * 
     * @return
     *     possible object is
     *     {@link EsriRoundingOptionEnum }
     *     
     */
    public EsriRoundingOptionEnum getRoundingOption() {
        return roundingOption;
    }

    /**
     * Sets the value of the roundingOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriRoundingOptionEnum }
     *     
     */
    public void setRoundingOption(EsriRoundingOptionEnum value) {
        this.roundingOption = value;
    }

    /**
     * Gets the value of the roundingValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRoundingValue() {
        return roundingValue;
    }

    /**
     * Sets the value of the roundingValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRoundingValue(Integer value) {
        this.roundingValue = value;
    }

    /**
     * Gets the value of the alignmentOption property.
     * 
     * @return
     *     possible object is
     *     {@link EsriNumericAlignmentEnum }
     *     
     */
    public EsriNumericAlignmentEnum getAlignmentOption() {
        return alignmentOption;
    }

    /**
     * Sets the value of the alignmentOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriNumericAlignmentEnum }
     *     
     */
    public void setAlignmentOption(EsriNumericAlignmentEnum value) {
        this.alignmentOption = value;
    }

    /**
     * Gets the value of the alignmentWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAlignmentWidth() {
        return alignmentWidth;
    }

    /**
     * Sets the value of the alignmentWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAlignmentWidth(Integer value) {
        this.alignmentWidth = value;
    }

    /**
     * Gets the value of the useSeparator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseSeparator() {
        return useSeparator;
    }

    /**
     * Sets the value of the useSeparator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseSeparator(Boolean value) {
        this.useSeparator = value;
    }

    /**
     * Gets the value of the zeroPad property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isZeroPad() {
        return zeroPad;
    }

    /**
     * Sets the value of the zeroPad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setZeroPad(Boolean value) {
        this.zeroPad = value;
    }

    /**
     * Gets the value of the showPlus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowPlus() {
        return showPlus;
    }

    /**
     * Sets the value of the showPlus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowPlus(Boolean value) {
        this.showPlus = value;
    }

}

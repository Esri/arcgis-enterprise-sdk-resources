
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ClassBreakInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ClassBreakInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ClassMaximumValue" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="Label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol"/&amp;gt;
 *         &amp;lt;element name="ClassMinimumValue" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassBreakInfo", propOrder = {
    "classMaximumValue",
    "label",
    "description",
    "symbol",
    "classMinimumValue"
})
public class ClassBreakInfo {

    @XmlElement(name = "ClassMaximumValue")
    protected double classMaximumValue;
    @XmlElement(name = "Label")
    protected String label;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Symbol", required = true)
    protected Symbol symbol;
    @XmlElement(name = "ClassMinimumValue")
    protected double classMinimumValue;

    /**
     * Gets the value of the classMaximumValue property.
     * 
     */
    public double getClassMaximumValue() {
        return classMaximumValue;
    }

    /**
     * Sets the value of the classMaximumValue property.
     * 
     */
    public void setClassMaximumValue(double value) {
        this.classMaximumValue = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setSymbol(Symbol value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the classMinimumValue property.
     * 
     */
    public double getClassMinimumValue() {
        return classMinimumValue;
    }

    /**
     * Sets the value of the classMinimumValue property.
     * 
     */
    public void setClassMinimumValue(double value) {
        this.classMinimumValue = value;
    }

}

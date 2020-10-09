
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LegendClass complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LegendClass"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Label" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LegendClassFormat" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}LegendClassFormat" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegendClass", propOrder = {
    "symbol",
    "label",
    "description",
    "legendClassFormat"
})
public class LegendClass {

    @XmlElement(name = "Symbol")
    protected Symbol symbol;
    @XmlElement(name = "Label", required = true)
    protected String label;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "LegendClassFormat")
    protected LegendClassFormat legendClassFormat;

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
     * Gets the value of the legendClassFormat property.
     * 
     * @return
     *     possible object is
     *     {@link LegendClassFormat }
     *     
     */
    public LegendClassFormat getLegendClassFormat() {
        return legendClassFormat;
    }

    /**
     * Sets the value of the legendClassFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegendClassFormat }
     *     
     */
    public void setLegendClassFormat(LegendClassFormat value) {
        this.legendClassFormat = value;
    }

}

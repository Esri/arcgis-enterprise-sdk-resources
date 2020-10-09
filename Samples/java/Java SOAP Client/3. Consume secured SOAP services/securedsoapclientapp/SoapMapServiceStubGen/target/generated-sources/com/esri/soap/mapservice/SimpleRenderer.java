
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SimpleRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SimpleRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}FeatureRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol"/&amp;gt;
 *         &amp;lt;element name="Label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RotationField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RotationType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriRotationType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TransparencyField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleRenderer", propOrder = {
    "symbol",
    "label",
    "description",
    "rotationField",
    "rotationType",
    "transparencyField"
})
public class SimpleRenderer
    extends FeatureRenderer
{

    @XmlElement(name = "Symbol", required = true)
    protected Symbol symbol;
    @XmlElement(name = "Label")
    protected String label;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "RotationField")
    protected String rotationField;
    @XmlElement(name = "RotationType")
    @XmlSchemaType(name = "string")
    protected EsriRotationType rotationType;
    @XmlElement(name = "TransparencyField")
    protected String transparencyField;

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
     * Gets the value of the rotationField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRotationField() {
        return rotationField;
    }

    /**
     * Sets the value of the rotationField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRotationField(String value) {
        this.rotationField = value;
    }

    /**
     * Gets the value of the rotationType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriRotationType }
     *     
     */
    public EsriRotationType getRotationType() {
        return rotationType;
    }

    /**
     * Sets the value of the rotationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriRotationType }
     *     
     */
    public void setRotationType(EsriRotationType value) {
        this.rotationType = value;
    }

    /**
     * Gets the value of the transparencyField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransparencyField() {
        return transparencyField;
    }

    /**
     * Sets the value of the transparencyField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransparencyField(String value) {
        this.transparencyField = value;
    }

}

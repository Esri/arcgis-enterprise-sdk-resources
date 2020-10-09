
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for UniqueValuesDef complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="UniqueValuesDef"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}DataClassificationDef"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="BaseSymbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Fields" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfString" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FieldDelimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorRamp" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ColorRamp" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UniqueValuesDef", propOrder = {
    "baseSymbol",
    "fields",
    "fieldDelimiter",
    "colorRamp"
})
public class UniqueValuesDef
    extends DataClassificationDef
{

    @XmlElement(name = "BaseSymbol")
    protected Symbol baseSymbol;
    @XmlElement(name = "Fields")
    protected ArrayOfString fields;
    @XmlElement(name = "FieldDelimiter")
    protected String fieldDelimiter;
    @XmlElement(name = "ColorRamp")
    protected ColorRamp colorRamp;

    /**
     * Gets the value of the baseSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getBaseSymbol() {
        return baseSymbol;
    }

    /**
     * Sets the value of the baseSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setBaseSymbol(Symbol value) {
        this.baseSymbol = value;
    }

    /**
     * Gets the value of the fields property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getFields() {
        return fields;
    }

    /**
     * Sets the value of the fields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setFields(ArrayOfString value) {
        this.fields = value;
    }

    /**
     * Gets the value of the fieldDelimiter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    /**
     * Sets the value of the fieldDelimiter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldDelimiter(String value) {
        this.fieldDelimiter = value;
    }

    /**
     * Gets the value of the colorRamp property.
     * 
     * @return
     *     possible object is
     *     {@link ColorRamp }
     *     
     */
    public ColorRamp getColorRamp() {
        return colorRamp;
    }

    /**
     * Sets the value of the colorRamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorRamp }
     *     
     */
    public void setColorRamp(ColorRamp value) {
        this.colorRamp = value;
    }

}

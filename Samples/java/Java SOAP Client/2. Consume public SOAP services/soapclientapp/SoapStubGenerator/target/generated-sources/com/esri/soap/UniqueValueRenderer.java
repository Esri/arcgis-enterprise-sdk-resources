
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for UniqueValueRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="UniqueValueRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}FeatureRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Field1" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Field3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FieldDelimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UniqueValueInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfUniqueValueInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RotationField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RotationType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriRotationType" minOccurs="0"/&amp;gt;
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
@XmlType(name = "UniqueValueRenderer", propOrder = {
    "field1",
    "field2",
    "field3",
    "fieldDelimiter",
    "defaultSymbol",
    "defaultLabel",
    "uniqueValueInfos",
    "rotationField",
    "rotationType",
    "transparencyField"
})
public class UniqueValueRenderer
    extends FeatureRenderer
{

    @XmlElement(name = "Field1", required = true)
    protected String field1;
    @XmlElement(name = "Field2")
    protected String field2;
    @XmlElement(name = "Field3")
    protected String field3;
    @XmlElement(name = "FieldDelimiter")
    protected String fieldDelimiter;
    @XmlElement(name = "DefaultSymbol")
    protected Symbol defaultSymbol;
    @XmlElement(name = "DefaultLabel")
    protected String defaultLabel;
    @XmlElement(name = "UniqueValueInfos")
    protected ArrayOfUniqueValueInfo uniqueValueInfos;
    @XmlElement(name = "RotationField")
    protected String rotationField;
    @XmlElement(name = "RotationType")
    @XmlSchemaType(name = "string")
    protected EsriRotationType rotationType;
    @XmlElement(name = "TransparencyField")
    protected String transparencyField;

    /**
     * Gets the value of the field1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField1() {
        return field1;
    }

    /**
     * Sets the value of the field1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField1(String value) {
        this.field1 = value;
    }

    /**
     * Gets the value of the field2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField2() {
        return field2;
    }

    /**
     * Sets the value of the field2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField2(String value) {
        this.field2 = value;
    }

    /**
     * Gets the value of the field3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField3() {
        return field3;
    }

    /**
     * Sets the value of the field3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField3(String value) {
        this.field3 = value;
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
     * Gets the value of the defaultSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getDefaultSymbol() {
        return defaultSymbol;
    }

    /**
     * Sets the value of the defaultSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setDefaultSymbol(Symbol value) {
        this.defaultSymbol = value;
    }

    /**
     * Gets the value of the defaultLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultLabel() {
        return defaultLabel;
    }

    /**
     * Sets the value of the defaultLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultLabel(String value) {
        this.defaultLabel = value;
    }

    /**
     * Gets the value of the uniqueValueInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUniqueValueInfo }
     *     
     */
    public ArrayOfUniqueValueInfo getUniqueValueInfos() {
        return uniqueValueInfos;
    }

    /**
     * Sets the value of the uniqueValueInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUniqueValueInfo }
     *     
     */
    public void setUniqueValueInfos(ArrayOfUniqueValueInfo value) {
        this.uniqueValueInfos = value;
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

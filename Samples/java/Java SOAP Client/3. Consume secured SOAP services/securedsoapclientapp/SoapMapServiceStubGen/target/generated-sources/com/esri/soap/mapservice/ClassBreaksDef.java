
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ClassBreaksDef complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ClassBreaksDef"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}DataClassificationDef"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ClassificationField" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="ClassificationMethod" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriClassifyMethod"/&amp;gt;
 *         &amp;lt;element name="BaseSymbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorRamp" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ColorRamp" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BreakCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NormalizationField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StandardDeviationInterval" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NormalizationType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriNormalizationType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassBreaksDef", propOrder = {
    "classificationField",
    "classificationMethod",
    "baseSymbol",
    "colorRamp",
    "breakCount",
    "normalizationField",
    "standardDeviationInterval",
    "normalizationType"
})
public class ClassBreaksDef
    extends DataClassificationDef
{

    @XmlElement(name = "ClassificationField", required = true)
    protected String classificationField;
    @XmlElement(name = "ClassificationMethod", required = true)
    @XmlSchemaType(name = "string")
    protected EsriClassifyMethod classificationMethod;
    @XmlElement(name = "BaseSymbol")
    protected Symbol baseSymbol;
    @XmlElement(name = "ColorRamp")
    protected ColorRamp colorRamp;
    @XmlElement(name = "BreakCount")
    protected Long breakCount;
    @XmlElement(name = "NormalizationField")
    protected String normalizationField;
    @XmlElement(name = "StandardDeviationInterval")
    protected Double standardDeviationInterval;
    @XmlElement(name = "NormalizationType")
    @XmlSchemaType(name = "string")
    protected EsriNormalizationType normalizationType;

    /**
     * Gets the value of the classificationField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationField() {
        return classificationField;
    }

    /**
     * Sets the value of the classificationField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificationField(String value) {
        this.classificationField = value;
    }

    /**
     * Gets the value of the classificationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link EsriClassifyMethod }
     *     
     */
    public EsriClassifyMethod getClassificationMethod() {
        return classificationMethod;
    }

    /**
     * Sets the value of the classificationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriClassifyMethod }
     *     
     */
    public void setClassificationMethod(EsriClassifyMethod value) {
        this.classificationMethod = value;
    }

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

    /**
     * Gets the value of the breakCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBreakCount() {
        return breakCount;
    }

    /**
     * Sets the value of the breakCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBreakCount(Long value) {
        this.breakCount = value;
    }

    /**
     * Gets the value of the normalizationField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormalizationField() {
        return normalizationField;
    }

    /**
     * Sets the value of the normalizationField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormalizationField(String value) {
        this.normalizationField = value;
    }

    /**
     * Gets the value of the standardDeviationInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStandardDeviationInterval() {
        return standardDeviationInterval;
    }

    /**
     * Sets the value of the standardDeviationInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStandardDeviationInterval(Double value) {
        this.standardDeviationInterval = value;
    }

    /**
     * Gets the value of the normalizationType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriNormalizationType }
     *     
     */
    public EsriNormalizationType getNormalizationType() {
        return normalizationType;
    }

    /**
     * Sets the value of the normalizationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriNormalizationType }
     *     
     */
    public void setNormalizationType(EsriNormalizationType value) {
        this.normalizationType = value;
    }

}

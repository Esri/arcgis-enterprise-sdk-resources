
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ClassBreaksRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ClassBreaksRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}FeatureRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Field" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="MinimumValue" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="ClassBreakInfos" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfClassBreakInfo"/&amp;gt;
 *         &amp;lt;element name="BackgroundSymbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}FillSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NormalizationField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NormalizationType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriNormalizationType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NormalizationTotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RotationField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RotationType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriRotationType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassBreaksRenderer", propOrder = {
    "field",
    "minimumValue",
    "classBreakInfos",
    "backgroundSymbol",
    "normalizationField",
    "normalizationType",
    "normalizationTotal",
    "rotationField",
    "rotationType"
})
public class ClassBreaksRenderer
    extends FeatureRenderer
{

    @XmlElement(name = "Field", required = true)
    protected String field;
    @XmlElement(name = "MinimumValue")
    protected double minimumValue;
    @XmlElement(name = "ClassBreakInfos", required = true)
    protected ArrayOfClassBreakInfo classBreakInfos;
    @XmlElement(name = "BackgroundSymbol")
    protected FillSymbol backgroundSymbol;
    @XmlElement(name = "NormalizationField")
    protected String normalizationField;
    @XmlElement(name = "NormalizationType")
    @XmlSchemaType(name = "string")
    protected EsriNormalizationType normalizationType;
    @XmlElement(name = "NormalizationTotal")
    protected Double normalizationTotal;
    @XmlElement(name = "RotationField")
    protected String rotationField;
    @XmlElement(name = "RotationType")
    @XmlSchemaType(name = "string")
    protected EsriRotationType rotationType;

    /**
     * Gets the value of the field property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField(String value) {
        this.field = value;
    }

    /**
     * Gets the value of the minimumValue property.
     * 
     */
    public double getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets the value of the minimumValue property.
     * 
     */
    public void setMinimumValue(double value) {
        this.minimumValue = value;
    }

    /**
     * Gets the value of the classBreakInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfClassBreakInfo }
     *     
     */
    public ArrayOfClassBreakInfo getClassBreakInfos() {
        return classBreakInfos;
    }

    /**
     * Sets the value of the classBreakInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfClassBreakInfo }
     *     
     */
    public void setClassBreakInfos(ArrayOfClassBreakInfo value) {
        this.classBreakInfos = value;
    }

    /**
     * Gets the value of the backgroundSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link FillSymbol }
     *     
     */
    public FillSymbol getBackgroundSymbol() {
        return backgroundSymbol;
    }

    /**
     * Sets the value of the backgroundSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillSymbol }
     *     
     */
    public void setBackgroundSymbol(FillSymbol value) {
        this.backgroundSymbol = value;
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

    /**
     * Gets the value of the normalizationTotal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNormalizationTotal() {
        return normalizationTotal;
    }

    /**
     * Sets the value of the normalizationTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNormalizationTotal(Double value) {
        this.normalizationTotal = value;
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

}

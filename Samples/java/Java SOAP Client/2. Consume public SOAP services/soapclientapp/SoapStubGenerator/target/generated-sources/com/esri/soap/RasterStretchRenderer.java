
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterStretchRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterStretchRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}RasterRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ColorSchema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LayerIndex1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StretchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StandardDeviations" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsInvert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BlackValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorRamp" type="{http://www.esri.com/schemas/ArcGIS/10.7}ColorRamp" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BkColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LegendGroup" type="{http://www.esri.com/schemas/ArcGIS/10.7}LegendGroup" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DisplayBkValue" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InitCustomMinMax" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseCustomMinMax" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CustomMin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CustomMax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterStretchRenderer", propOrder = {
    "colorSchema",
    "layerIndex1",
    "stretchType",
    "standardDeviations",
    "isInvert",
    "blackValue",
    "colorRamp",
    "bkColor",
    "legendGroup",
    "displayBkValue",
    "initCustomMinMax",
    "useCustomMinMax",
    "customMin",
    "customMax"
})
public class RasterStretchRenderer
    extends RasterRenderer
{

    @XmlElement(name = "ColorSchema")
    protected String colorSchema;
    @XmlElement(name = "LayerIndex1")
    protected Integer layerIndex1;
    @XmlElement(name = "StretchType")
    protected String stretchType;
    @XmlElement(name = "StandardDeviations")
    protected Double standardDeviations;
    @XmlElement(name = "IsInvert")
    protected Boolean isInvert;
    @XmlElement(name = "BlackValue")
    protected Double blackValue;
    @XmlElement(name = "ColorRamp")
    protected ColorRamp colorRamp;
    @XmlElement(name = "BkColor")
    protected Color bkColor;
    @XmlElement(name = "LegendGroup")
    protected LegendGroup legendGroup;
    @XmlElement(name = "DisplayBkValue")
    protected Boolean displayBkValue;
    @XmlElement(name = "InitCustomMinMax")
    protected Boolean initCustomMinMax;
    @XmlElement(name = "UseCustomMinMax")
    protected Boolean useCustomMinMax;
    @XmlElement(name = "CustomMin")
    protected Double customMin;
    @XmlElement(name = "CustomMax")
    protected Double customMax;

    /**
     * Gets the value of the colorSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColorSchema() {
        return colorSchema;
    }

    /**
     * Sets the value of the colorSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColorSchema(String value) {
        this.colorSchema = value;
    }

    /**
     * Gets the value of the layerIndex1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLayerIndex1() {
        return layerIndex1;
    }

    /**
     * Sets the value of the layerIndex1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLayerIndex1(Integer value) {
        this.layerIndex1 = value;
    }

    /**
     * Gets the value of the stretchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStretchType() {
        return stretchType;
    }

    /**
     * Sets the value of the stretchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStretchType(String value) {
        this.stretchType = value;
    }

    /**
     * Gets the value of the standardDeviations property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStandardDeviations() {
        return standardDeviations;
    }

    /**
     * Sets the value of the standardDeviations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStandardDeviations(Double value) {
        this.standardDeviations = value;
    }

    /**
     * Gets the value of the isInvert property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsInvert() {
        return isInvert;
    }

    /**
     * Sets the value of the isInvert property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInvert(Boolean value) {
        this.isInvert = value;
    }

    /**
     * Gets the value of the blackValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBlackValue() {
        return blackValue;
    }

    /**
     * Sets the value of the blackValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBlackValue(Double value) {
        this.blackValue = value;
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
     * Gets the value of the bkColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getBkColor() {
        return bkColor;
    }

    /**
     * Sets the value of the bkColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setBkColor(Color value) {
        this.bkColor = value;
    }

    /**
     * Gets the value of the legendGroup property.
     * 
     * @return
     *     possible object is
     *     {@link LegendGroup }
     *     
     */
    public LegendGroup getLegendGroup() {
        return legendGroup;
    }

    /**
     * Sets the value of the legendGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegendGroup }
     *     
     */
    public void setLegendGroup(LegendGroup value) {
        this.legendGroup = value;
    }

    /**
     * Gets the value of the displayBkValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisplayBkValue() {
        return displayBkValue;
    }

    /**
     * Sets the value of the displayBkValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisplayBkValue(Boolean value) {
        this.displayBkValue = value;
    }

    /**
     * Gets the value of the initCustomMinMax property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInitCustomMinMax() {
        return initCustomMinMax;
    }

    /**
     * Sets the value of the initCustomMinMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInitCustomMinMax(Boolean value) {
        this.initCustomMinMax = value;
    }

    /**
     * Gets the value of the useCustomMinMax property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseCustomMinMax() {
        return useCustomMinMax;
    }

    /**
     * Sets the value of the useCustomMinMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseCustomMinMax(Boolean value) {
        this.useCustomMinMax = value;
    }

    /**
     * Gets the value of the customMin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCustomMin() {
        return customMin;
    }

    /**
     * Sets the value of the customMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCustomMin(Double value) {
        this.customMin = value;
    }

    /**
     * Gets the value of the customMax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCustomMax() {
        return customMax;
    }

    /**
     * Sets the value of the customMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCustomMax(Double value) {
        this.customMax = value;
    }

}

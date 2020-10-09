
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterRGBRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterRGBRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}RasterRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LayerIndex1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LayerIndex2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LayerIndex3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseRGBBand" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StretchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StandardDeviations" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsInvert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DisplayBkValue" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BlackValue" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfDouble" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsLegendExpand" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BkColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterRGBRenderer", propOrder = {
    "layerIndex1",
    "layerIndex2",
    "layerIndex3",
    "useRGBBand",
    "stretchType",
    "standardDeviations",
    "isInvert",
    "displayBkValue",
    "blackValue",
    "isLegendExpand",
    "bkColor"
})
public class RasterRGBRenderer
    extends RasterRenderer
{

    @XmlElement(name = "LayerIndex1")
    protected Integer layerIndex1;
    @XmlElement(name = "LayerIndex2")
    protected Integer layerIndex2;
    @XmlElement(name = "LayerIndex3")
    protected Integer layerIndex3;
    @XmlElement(name = "UseRGBBand")
    @XmlSchemaType(name = "unsignedByte")
    protected Short useRGBBand;
    @XmlElement(name = "StretchType")
    protected String stretchType;
    @XmlElement(name = "StandardDeviations")
    protected Double standardDeviations;
    @XmlElement(name = "IsInvert")
    protected Boolean isInvert;
    @XmlElement(name = "DisplayBkValue")
    protected Boolean displayBkValue;
    @XmlElement(name = "BlackValue")
    protected ArrayOfDouble blackValue;
    @XmlElement(name = "IsLegendExpand")
    protected Boolean isLegendExpand;
    @XmlElement(name = "BkColor")
    protected Color bkColor;

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
     * Gets the value of the layerIndex2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLayerIndex2() {
        return layerIndex2;
    }

    /**
     * Sets the value of the layerIndex2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLayerIndex2(Integer value) {
        this.layerIndex2 = value;
    }

    /**
     * Gets the value of the layerIndex3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLayerIndex3() {
        return layerIndex3;
    }

    /**
     * Sets the value of the layerIndex3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLayerIndex3(Integer value) {
        this.layerIndex3 = value;
    }

    /**
     * Gets the value of the useRGBBand property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getUseRGBBand() {
        return useRGBBand;
    }

    /**
     * Sets the value of the useRGBBand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setUseRGBBand(Short value) {
        this.useRGBBand = value;
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
     * Gets the value of the blackValue property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDouble }
     *     
     */
    public ArrayOfDouble getBlackValue() {
        return blackValue;
    }

    /**
     * Sets the value of the blackValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDouble }
     *     
     */
    public void setBlackValue(ArrayOfDouble value) {
        this.blackValue = value;
    }

    /**
     * Gets the value of the isLegendExpand property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsLegendExpand() {
        return isLegendExpand;
    }

    /**
     * Sets the value of the isLegendExpand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsLegendExpand(Boolean value) {
        this.isLegendExpand = value;
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

}

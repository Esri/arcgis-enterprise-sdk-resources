
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SimpleTextSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SimpleTextSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Color" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BackgroundColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OutlineColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="VerticalAlignment" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSimpleTextVerticalAlignment" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HorizontalAlignment" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSimpleTextHorizontalAlignment" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RightToLeft" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Angle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="YOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Size" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontFamilyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontStyle" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriFontStyle" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontWeight" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriFontWeight" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontDecoration" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriFontDecoration" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OutlineWidth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Kerning" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaskStyle" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriMaskStyle" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaskSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaskSymbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleTextSymbol", propOrder = {
    "color",
    "backgroundColor",
    "outlineColor",
    "verticalAlignment",
    "horizontalAlignment",
    "rightToLeft",
    "angle",
    "xOffset",
    "yOffset",
    "size",
    "fontFamilyName",
    "fontStyle",
    "fontWeight",
    "fontDecoration",
    "outlineWidth",
    "kerning",
    "maskStyle",
    "maskSize",
    "maskSymbol"
})
public class SimpleTextSymbol
    extends Symbol
{

    @XmlElement(name = "Color")
    protected Color color;
    @XmlElement(name = "BackgroundColor")
    protected Color backgroundColor;
    @XmlElement(name = "OutlineColor")
    protected Color outlineColor;
    @XmlElement(name = "VerticalAlignment")
    @XmlSchemaType(name = "string")
    protected EsriSimpleTextVerticalAlignment verticalAlignment;
    @XmlElement(name = "HorizontalAlignment")
    @XmlSchemaType(name = "string")
    protected EsriSimpleTextHorizontalAlignment horizontalAlignment;
    @XmlElement(name = "RightToLeft", defaultValue = "false")
    protected Boolean rightToLeft;
    @XmlElement(name = "Angle", defaultValue = "0.0")
    protected Double angle;
    @XmlElement(name = "XOffset", defaultValue = "0.0")
    protected Double xOffset;
    @XmlElement(name = "YOffset", defaultValue = "0.0")
    protected Double yOffset;
    @XmlElement(name = "Size")
    protected Double size;
    @XmlElement(name = "FontFamilyName")
    protected String fontFamilyName;
    @XmlElement(name = "FontStyle")
    @XmlSchemaType(name = "string")
    protected EsriFontStyle fontStyle;
    @XmlElement(name = "FontWeight")
    @XmlSchemaType(name = "string")
    protected EsriFontWeight fontWeight;
    @XmlElement(name = "FontDecoration")
    @XmlSchemaType(name = "string")
    protected EsriFontDecoration fontDecoration;
    @XmlElement(name = "OutlineWidth")
    protected Double outlineWidth;
    @XmlElement(name = "Kerning")
    protected Boolean kerning;
    @XmlElement(name = "MaskStyle")
    @XmlSchemaType(name = "string")
    protected EsriMaskStyle maskStyle;
    @XmlElement(name = "MaskSize")
    protected Integer maskSize;
    @XmlElement(name = "MaskSymbol")
    protected Symbol maskSymbol;

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setColor(Color value) {
        this.color = value;
    }

    /**
     * Gets the value of the backgroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the value of the backgroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setBackgroundColor(Color value) {
        this.backgroundColor = value;
    }

    /**
     * Gets the value of the outlineColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getOutlineColor() {
        return outlineColor;
    }

    /**
     * Sets the value of the outlineColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setOutlineColor(Color value) {
        this.outlineColor = value;
    }

    /**
     * Gets the value of the verticalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSimpleTextVerticalAlignment }
     *     
     */
    public EsriSimpleTextVerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the value of the verticalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSimpleTextVerticalAlignment }
     *     
     */
    public void setVerticalAlignment(EsriSimpleTextVerticalAlignment value) {
        this.verticalAlignment = value;
    }

    /**
     * Gets the value of the horizontalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSimpleTextHorizontalAlignment }
     *     
     */
    public EsriSimpleTextHorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the value of the horizontalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSimpleTextHorizontalAlignment }
     *     
     */
    public void setHorizontalAlignment(EsriSimpleTextHorizontalAlignment value) {
        this.horizontalAlignment = value;
    }

    /**
     * Gets the value of the rightToLeft property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRightToLeft() {
        return rightToLeft;
    }

    /**
     * Sets the value of the rightToLeft property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRightToLeft(Boolean value) {
        this.rightToLeft = value;
    }

    /**
     * Gets the value of the angle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAngle() {
        return angle;
    }

    /**
     * Sets the value of the angle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAngle(Double value) {
        this.angle = value;
    }

    /**
     * Gets the value of the xOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXOffset() {
        return xOffset;
    }

    /**
     * Sets the value of the xOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXOffset(Double value) {
        this.xOffset = value;
    }

    /**
     * Gets the value of the yOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYOffset() {
        return yOffset;
    }

    /**
     * Sets the value of the yOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYOffset(Double value) {
        this.yOffset = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSize(Double value) {
        this.size = value;
    }

    /**
     * Gets the value of the fontFamilyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontFamilyName() {
        return fontFamilyName;
    }

    /**
     * Sets the value of the fontFamilyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontFamilyName(String value) {
        this.fontFamilyName = value;
    }

    /**
     * Gets the value of the fontStyle property.
     * 
     * @return
     *     possible object is
     *     {@link EsriFontStyle }
     *     
     */
    public EsriFontStyle getFontStyle() {
        return fontStyle;
    }

    /**
     * Sets the value of the fontStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriFontStyle }
     *     
     */
    public void setFontStyle(EsriFontStyle value) {
        this.fontStyle = value;
    }

    /**
     * Gets the value of the fontWeight property.
     * 
     * @return
     *     possible object is
     *     {@link EsriFontWeight }
     *     
     */
    public EsriFontWeight getFontWeight() {
        return fontWeight;
    }

    /**
     * Sets the value of the fontWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriFontWeight }
     *     
     */
    public void setFontWeight(EsriFontWeight value) {
        this.fontWeight = value;
    }

    /**
     * Gets the value of the fontDecoration property.
     * 
     * @return
     *     possible object is
     *     {@link EsriFontDecoration }
     *     
     */
    public EsriFontDecoration getFontDecoration() {
        return fontDecoration;
    }

    /**
     * Sets the value of the fontDecoration property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriFontDecoration }
     *     
     */
    public void setFontDecoration(EsriFontDecoration value) {
        this.fontDecoration = value;
    }

    /**
     * Gets the value of the outlineWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOutlineWidth() {
        return outlineWidth;
    }

    /**
     * Sets the value of the outlineWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOutlineWidth(Double value) {
        this.outlineWidth = value;
    }

    /**
     * Gets the value of the kerning property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKerning() {
        return kerning;
    }

    /**
     * Sets the value of the kerning property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKerning(Boolean value) {
        this.kerning = value;
    }

    /**
     * Gets the value of the maskStyle property.
     * 
     * @return
     *     possible object is
     *     {@link EsriMaskStyle }
     *     
     */
    public EsriMaskStyle getMaskStyle() {
        return maskStyle;
    }

    /**
     * Sets the value of the maskStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriMaskStyle }
     *     
     */
    public void setMaskStyle(EsriMaskStyle value) {
        this.maskStyle = value;
    }

    /**
     * Gets the value of the maskSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaskSize() {
        return maskSize;
    }

    /**
     * Sets the value of the maskSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaskSize(Integer value) {
        this.maskSize = value;
    }

    /**
     * Gets the value of the maskSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getMaskSymbol() {
        return maskSymbol;
    }

    /**
     * Sets the value of the maskSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setMaskSymbol(Symbol value) {
        this.maskSymbol = value;
    }

}

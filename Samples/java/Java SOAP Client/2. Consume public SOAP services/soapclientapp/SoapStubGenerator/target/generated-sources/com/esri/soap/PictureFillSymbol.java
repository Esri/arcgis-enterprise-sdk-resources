
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PictureFillSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PictureFillSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}FillSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Picture" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PictureUri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BgColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FgColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BitmapTransColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XSeparation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="YSeparation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Swap1BitColor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Angle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="YOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="YScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PictureFillSymbol", propOrder = {
    "picture",
    "pictureUri",
    "width",
    "height",
    "bgColor",
    "fgColor",
    "bitmapTransColor",
    "xSeparation",
    "ySeparation",
    "swap1BitColor",
    "angle",
    "xOffset",
    "yOffset",
    "xScale",
    "yScale"
})
public class PictureFillSymbol
    extends FillSymbol
{

    @XmlElement(name = "Picture")
    protected byte[] picture;
    @XmlElement(name = "PictureUri")
    protected String pictureUri;
    @XmlElement(name = "Width")
    protected Double width;
    @XmlElement(name = "Height")
    protected Double height;
    @XmlElement(name = "BgColor")
    protected Color bgColor;
    @XmlElement(name = "FgColor")
    protected Color fgColor;
    @XmlElement(name = "BitmapTransColor")
    protected Color bitmapTransColor;
    @XmlElement(name = "XSeparation")
    protected Double xSeparation;
    @XmlElement(name = "YSeparation")
    protected Double ySeparation;
    @XmlElement(name = "Swap1BitColor")
    protected Boolean swap1BitColor;
    @XmlElement(name = "Angle")
    protected Double angle;
    @XmlElement(name = "XOffset")
    protected Double xOffset;
    @XmlElement(name = "YOffset")
    protected Double yOffset;
    @XmlElement(name = "XScale", defaultValue = "1.0")
    protected Double xScale;
    @XmlElement(name = "YScale", defaultValue = "1.0")
    protected Double yScale;

    /**
     * Gets the value of the picture property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPicture(byte[] value) {
        this.picture = value;
    }

    /**
     * Gets the value of the pictureUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureUri() {
        return pictureUri;
    }

    /**
     * Sets the value of the pictureUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureUri(String value) {
        this.pictureUri = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWidth(Double value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHeight(Double value) {
        this.height = value;
    }

    /**
     * Gets the value of the bgColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * Sets the value of the bgColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setBgColor(Color value) {
        this.bgColor = value;
    }

    /**
     * Gets the value of the fgColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getFgColor() {
        return fgColor;
    }

    /**
     * Sets the value of the fgColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setFgColor(Color value) {
        this.fgColor = value;
    }

    /**
     * Gets the value of the bitmapTransColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getBitmapTransColor() {
        return bitmapTransColor;
    }

    /**
     * Sets the value of the bitmapTransColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setBitmapTransColor(Color value) {
        this.bitmapTransColor = value;
    }

    /**
     * Gets the value of the xSeparation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXSeparation() {
        return xSeparation;
    }

    /**
     * Sets the value of the xSeparation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXSeparation(Double value) {
        this.xSeparation = value;
    }

    /**
     * Gets the value of the ySeparation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYSeparation() {
        return ySeparation;
    }

    /**
     * Sets the value of the ySeparation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYSeparation(Double value) {
        this.ySeparation = value;
    }

    /**
     * Gets the value of the swap1BitColor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSwap1BitColor() {
        return swap1BitColor;
    }

    /**
     * Sets the value of the swap1BitColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSwap1BitColor(Boolean value) {
        this.swap1BitColor = value;
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
     * Gets the value of the xScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXScale() {
        return xScale;
    }

    /**
     * Sets the value of the xScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXScale(Double value) {
        this.xScale = value;
    }

    /**
     * Gets the value of the yScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYScale() {
        return yScale;
    }

    /**
     * Sets the value of the yScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYScale(Double value) {
        this.yScale = value;
    }

}

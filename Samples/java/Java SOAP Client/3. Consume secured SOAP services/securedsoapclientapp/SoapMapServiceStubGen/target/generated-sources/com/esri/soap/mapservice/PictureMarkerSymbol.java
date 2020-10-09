
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PictureMarkerSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PictureMarkerSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}CartographicMarkerSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="BgColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BitmapTransColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Picture" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PictureUri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FgColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Swap1BitColor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PictureMarkerSymbol", propOrder = {
    "bgColor",
    "bitmapTransColor",
    "picture",
    "pictureUri",
    "width",
    "fgColor",
    "swap1BitColor"
})
public class PictureMarkerSymbol
    extends CartographicMarkerSymbol
{

    @XmlElement(name = "BgColor")
    protected Color bgColor;
    @XmlElement(name = "BitmapTransColor")
    protected Color bitmapTransColor;
    @XmlElement(name = "Picture")
    protected byte[] picture;
    @XmlElement(name = "PictureUri")
    protected String pictureUri;
    @XmlElement(name = "Width")
    protected Double width;
    @XmlElement(name = "FgColor")
    protected Color fgColor;
    @XmlElement(name = "Swap1BitColor")
    protected Boolean swap1BitColor;

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

}

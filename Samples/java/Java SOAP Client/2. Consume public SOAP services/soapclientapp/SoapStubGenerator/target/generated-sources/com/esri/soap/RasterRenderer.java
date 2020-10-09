
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Indexed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Brightness" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Contrast" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ResamplingType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NoDataColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NoDataValue" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfDouble" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AlphaBandIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseAlphaBand" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterRenderer", propOrder = {
    "indexed",
    "brightness",
    "contrast",
    "resamplingType",
    "noDataColor",
    "noDataValue",
    "alphaBandIndex",
    "useAlphaBand"
})
@XmlSeeAlso({
    RasterUniqueValueRenderer.class,
    RasterRGBRenderer.class,
    RasterStretchRenderer.class,
    RasterClassifyRenderer.class
})
public abstract class RasterRenderer {

    @XmlElement(name = "Indexed")
    protected Boolean indexed;
    @XmlElement(name = "Brightness")
    protected Integer brightness;
    @XmlElement(name = "Contrast")
    protected Integer contrast;
    @XmlElement(name = "ResamplingType")
    protected String resamplingType;
    @XmlElement(name = "NoDataColor")
    protected Color noDataColor;
    @XmlElement(name = "NoDataValue")
    protected ArrayOfDouble noDataValue;
    @XmlElement(name = "AlphaBandIndex")
    protected Integer alphaBandIndex;
    @XmlElement(name = "UseAlphaBand")
    protected Boolean useAlphaBand;

    /**
     * Gets the value of the indexed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIndexed() {
        return indexed;
    }

    /**
     * Sets the value of the indexed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIndexed(Boolean value) {
        this.indexed = value;
    }

    /**
     * Gets the value of the brightness property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBrightness() {
        return brightness;
    }

    /**
     * Sets the value of the brightness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBrightness(Integer value) {
        this.brightness = value;
    }

    /**
     * Gets the value of the contrast property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getContrast() {
        return contrast;
    }

    /**
     * Sets the value of the contrast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setContrast(Integer value) {
        this.contrast = value;
    }

    /**
     * Gets the value of the resamplingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResamplingType() {
        return resamplingType;
    }

    /**
     * Sets the value of the resamplingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResamplingType(String value) {
        this.resamplingType = value;
    }

    /**
     * Gets the value of the noDataColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getNoDataColor() {
        return noDataColor;
    }

    /**
     * Sets the value of the noDataColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setNoDataColor(Color value) {
        this.noDataColor = value;
    }

    /**
     * Gets the value of the noDataValue property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDouble }
     *     
     */
    public ArrayOfDouble getNoDataValue() {
        return noDataValue;
    }

    /**
     * Sets the value of the noDataValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDouble }
     *     
     */
    public void setNoDataValue(ArrayOfDouble value) {
        this.noDataValue = value;
    }

    /**
     * Gets the value of the alphaBandIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAlphaBandIndex() {
        return alphaBandIndex;
    }

    /**
     * Sets the value of the alphaBandIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAlphaBandIndex(Integer value) {
        this.alphaBandIndex = value;
    }

    /**
     * Gets the value of the useAlphaBand property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseAlphaBand() {
        return useAlphaBand;
    }

    /**
     * Sets the value of the useAlphaBand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseAlphaBand(Boolean value) {
        this.useAlphaBand = value;
    }

}

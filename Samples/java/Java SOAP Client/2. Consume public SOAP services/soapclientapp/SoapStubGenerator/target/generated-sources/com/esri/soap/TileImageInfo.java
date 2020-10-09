
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TileImageInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TileImageInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CacheTileFormat" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="CompressionQuality" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Antialiasing" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="BandCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LERCError" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileImageInfo", propOrder = {
    "cacheTileFormat",
    "compressionQuality",
    "antialiasing",
    "bandCount",
    "lercError"
})
public class TileImageInfo {

    @XmlElement(name = "CacheTileFormat", required = true)
    protected String cacheTileFormat;
    @XmlElement(name = "CompressionQuality")
    protected int compressionQuality;
    @XmlElement(name = "Antialiasing", required = true)
    protected String antialiasing;
    @XmlElement(name = "BandCount", defaultValue = "1")
    protected Integer bandCount;
    @XmlElement(name = "LERCError")
    protected Double lercError;

    /**
     * Gets the value of the cacheTileFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheTileFormat() {
        return cacheTileFormat;
    }

    /**
     * Sets the value of the cacheTileFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheTileFormat(String value) {
        this.cacheTileFormat = value;
    }

    /**
     * Gets the value of the compressionQuality property.
     * 
     */
    public int getCompressionQuality() {
        return compressionQuality;
    }

    /**
     * Sets the value of the compressionQuality property.
     * 
     */
    public void setCompressionQuality(int value) {
        this.compressionQuality = value;
    }

    /**
     * Gets the value of the antialiasing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAntialiasing() {
        return antialiasing;
    }

    /**
     * Sets the value of the antialiasing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAntialiasing(String value) {
        this.antialiasing = value;
    }

    /**
     * Gets the value of the bandCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBandCount() {
        return bandCount;
    }

    /**
     * Sets the value of the bandCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBandCount(Integer value) {
        this.bandCount = value;
    }

    /**
     * Gets the value of the lercError property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLERCError() {
        return lercError;
    }

    /**
     * Sets the value of the lercError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLERCError(Double value) {
        this.lercError = value;
    }

}

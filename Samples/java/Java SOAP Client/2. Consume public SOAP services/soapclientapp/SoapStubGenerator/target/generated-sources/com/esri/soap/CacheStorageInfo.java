
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for CacheStorageInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CacheStorageInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="StorageFormat" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriMapCacheStorageFormat"/&amp;gt;
 *         &amp;lt;element name="PacketSize" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CacheStorageInfo", propOrder = {
    "storageFormat",
    "packetSize"
})
public class CacheStorageInfo {

    @XmlElement(name = "StorageFormat", required = true)
    @XmlSchemaType(name = "string")
    protected EsriMapCacheStorageFormat storageFormat;
    @XmlElement(name = "PacketSize")
    protected int packetSize;

    /**
     * Gets the value of the storageFormat property.
     * 
     * @return
     *     possible object is
     *     {@link EsriMapCacheStorageFormat }
     *     
     */
    public EsriMapCacheStorageFormat getStorageFormat() {
        return storageFormat;
    }

    /**
     * Sets the value of the storageFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriMapCacheStorageFormat }
     *     
     */
    public void setStorageFormat(EsriMapCacheStorageFormat value) {
        this.storageFormat = value;
    }

    /**
     * Gets the value of the packetSize property.
     * 
     */
    public int getPacketSize() {
        return packetSize;
    }

    /**
     * Sets the value of the packetSize property.
     * 
     */
    public void setPacketSize(int value) {
        this.packetSize = value;
    }

}

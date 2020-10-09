
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LayerCacheInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LayerCacheInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LayerID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="HasCache" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerCacheInfo", propOrder = {
    "layerID",
    "hasCache"
})
public class LayerCacheInfo {

    @XmlElement(name = "LayerID")
    protected int layerID;
    @XmlElement(name = "HasCache")
    protected boolean hasCache;

    /**
     * Gets the value of the layerID property.
     * 
     */
    public int getLayerID() {
        return layerID;
    }

    /**
     * Sets the value of the layerID property.
     * 
     */
    public void setLayerID(int value) {
        this.layerID = value;
    }

    /**
     * Gets the value of the hasCache property.
     * 
     */
    public boolean isHasCache() {
        return hasCache;
    }

    /**
     * Sets the value of the hasCache property.
     * 
     */
    public void setHasCache(boolean value) {
        this.hasCache = value;
    }

}

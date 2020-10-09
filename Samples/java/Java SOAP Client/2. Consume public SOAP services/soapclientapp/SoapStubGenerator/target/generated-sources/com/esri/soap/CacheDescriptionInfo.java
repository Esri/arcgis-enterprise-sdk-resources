
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for CacheDescriptionInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CacheDescriptionInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="TileCacheInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}TileCacheInfo"/&amp;gt;
 *         &amp;lt;element name="TileImageInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}TileImageInfo"/&amp;gt;
 *         &amp;lt;element name="LayerCacheInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfLayerCacheInfo"/&amp;gt;
 *         &amp;lt;element name="CacheControlInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}CacheControlInfo"/&amp;gt;
 *         &amp;lt;element name="ServiceType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriCachedMapServiceType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CacheDescriptionInfo", propOrder = {
    "tileCacheInfo",
    "tileImageInfo",
    "layerCacheInfos",
    "cacheControlInfo",
    "serviceType"
})
public class CacheDescriptionInfo {

    @XmlElement(name = "TileCacheInfo", required = true)
    protected TileCacheInfo tileCacheInfo;
    @XmlElement(name = "TileImageInfo", required = true)
    protected TileImageInfo tileImageInfo;
    @XmlElement(name = "LayerCacheInfos", required = true)
    protected ArrayOfLayerCacheInfo layerCacheInfos;
    @XmlElement(name = "CacheControlInfo", required = true)
    protected CacheControlInfo cacheControlInfo;
    @XmlElement(name = "ServiceType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriCachedMapServiceType serviceType;

    /**
     * Gets the value of the tileCacheInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TileCacheInfo }
     *     
     */
    public TileCacheInfo getTileCacheInfo() {
        return tileCacheInfo;
    }

    /**
     * Sets the value of the tileCacheInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TileCacheInfo }
     *     
     */
    public void setTileCacheInfo(TileCacheInfo value) {
        this.tileCacheInfo = value;
    }

    /**
     * Gets the value of the tileImageInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TileImageInfo }
     *     
     */
    public TileImageInfo getTileImageInfo() {
        return tileImageInfo;
    }

    /**
     * Sets the value of the tileImageInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TileImageInfo }
     *     
     */
    public void setTileImageInfo(TileImageInfo value) {
        this.tileImageInfo = value;
    }

    /**
     * Gets the value of the layerCacheInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLayerCacheInfo }
     *     
     */
    public ArrayOfLayerCacheInfo getLayerCacheInfos() {
        return layerCacheInfos;
    }

    /**
     * Sets the value of the layerCacheInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLayerCacheInfo }
     *     
     */
    public void setLayerCacheInfos(ArrayOfLayerCacheInfo value) {
        this.layerCacheInfos = value;
    }

    /**
     * Gets the value of the cacheControlInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CacheControlInfo }
     *     
     */
    public CacheControlInfo getCacheControlInfo() {
        return cacheControlInfo;
    }

    /**
     * Sets the value of the cacheControlInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CacheControlInfo }
     *     
     */
    public void setCacheControlInfo(CacheControlInfo value) {
        this.cacheControlInfo = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriCachedMapServiceType }
     *     
     */
    public EsriCachedMapServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriCachedMapServiceType }
     *     
     */
    public void setServiceType(EsriCachedMapServiceType value) {
        this.serviceType = value;
    }

}

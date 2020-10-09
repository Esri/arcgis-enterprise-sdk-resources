
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ArrayOfLayerCacheInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ArrayOfLayerCacheInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LayerCacheInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}LayerCacheInfo"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLayerCacheInfo", propOrder = {
    "layerCacheInfo"
})
public class ArrayOfLayerCacheInfo {

    @XmlElement(name = "LayerCacheInfo", required = true)
    protected LayerCacheInfo layerCacheInfo;

    /**
     * Gets the value of the layerCacheInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LayerCacheInfo }
     *     
     */
    public LayerCacheInfo getLayerCacheInfo() {
        return layerCacheInfo;
    }

    /**
     * Sets the value of the layerCacheInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LayerCacheInfo }
     *     
     */
    public void setLayerCacheInfo(LayerCacheInfo value) {
        this.layerCacheInfo = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MapName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LayerDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}LayerDescription"/&amp;gt;
 *         &amp;lt;element name="QueryFilter" type="{http://www.esri.com/schemas/ArcGIS/10.7}QueryFilter"/&amp;gt;
 *         &amp;lt;element name="QueryResultOptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}QueryResultOptions"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mapName",
    "layerDescription",
    "queryFilter",
    "queryResultOptions"
})
@XmlRootElement(name = "QueryFeatureData2")
public class QueryFeatureData2_FeatureServer {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "LayerDescription", required = true)
    protected LayerDescription layerDescription;
    @XmlElement(name = "QueryFilter", required = true)
    protected QueryFilter queryFilter;
    @XmlElement(name = "QueryResultOptions", required = true)
    protected QueryResultOptions queryResultOptions;

    /**
     * Gets the value of the mapName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Sets the value of the mapName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapName(String value) {
        this.mapName = value;
    }

    /**
     * Gets the value of the layerDescription property.
     * 
     * @return
     *     possible object is
     *     {@link LayerDescription }
     *     
     */
    public LayerDescription getLayerDescription() {
        return layerDescription;
    }

    /**
     * Sets the value of the layerDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link LayerDescription }
     *     
     */
    public void setLayerDescription(LayerDescription value) {
        this.layerDescription = value;
    }

    /**
     * Gets the value of the queryFilter property.
     * 
     * @return
     *     possible object is
     *     {@link QueryFilter }
     *     
     */
    public QueryFilter getQueryFilter() {
        return queryFilter;
    }

    /**
     * Sets the value of the queryFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryFilter }
     *     
     */
    public void setQueryFilter(QueryFilter value) {
        this.queryFilter = value;
    }

    /**
     * Gets the value of the queryResultOptions property.
     * 
     * @return
     *     possible object is
     *     {@link QueryResultOptions }
     *     
     */
    public QueryResultOptions getQueryResultOptions() {
        return queryResultOptions;
    }

    /**
     * Sets the value of the queryResultOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResultOptions }
     *     
     */
    public void setQueryResultOptions(QueryResultOptions value) {
        this.queryResultOptions = value;
    }

}

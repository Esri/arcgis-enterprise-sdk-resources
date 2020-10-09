
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
 *         &amp;lt;element name="LayerOrTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="DefinitionExpression" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="QueryFilter" type="{http://www.esri.com/schemas/ArcGIS/10.7}QueryFilter"/&amp;gt;
 *         &amp;lt;element name="ServiceDataOptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ServiceDataOptions"/&amp;gt;
 *         &amp;lt;element name="GdbVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaximumAllowableOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
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
    "layerOrTableID",
    "definitionExpression",
    "queryFilter",
    "serviceDataOptions",
    "gdbVersion",
    "maximumAllowableOffset"
})
@XmlRootElement(name = "Query")
public class Query {

    @XmlElement(name = "LayerOrTableID")
    protected int layerOrTableID;
    @XmlElement(name = "DefinitionExpression", required = true)
    protected String definitionExpression;
    @XmlElement(name = "QueryFilter", required = true)
    protected QueryFilter queryFilter;
    @XmlElement(name = "ServiceDataOptions", required = true)
    protected ServiceDataOptions serviceDataOptions;
    @XmlElement(name = "GdbVersion")
    protected String gdbVersion;
    @XmlElement(name = "MaximumAllowableOffset", defaultValue = "0.0")
    protected Double maximumAllowableOffset;

    /**
     * Gets the value of the layerOrTableID property.
     * 
     */
    public int getLayerOrTableID() {
        return layerOrTableID;
    }

    /**
     * Sets the value of the layerOrTableID property.
     * 
     */
    public void setLayerOrTableID(int value) {
        this.layerOrTableID = value;
    }

    /**
     * Gets the value of the definitionExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefinitionExpression() {
        return definitionExpression;
    }

    /**
     * Sets the value of the definitionExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefinitionExpression(String value) {
        this.definitionExpression = value;
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
     * Gets the value of the serviceDataOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDataOptions }
     *     
     */
    public ServiceDataOptions getServiceDataOptions() {
        return serviceDataOptions;
    }

    /**
     * Sets the value of the serviceDataOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDataOptions }
     *     
     */
    public void setServiceDataOptions(ServiceDataOptions value) {
        this.serviceDataOptions = value;
    }

    /**
     * Gets the value of the gdbVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdbVersion() {
        return gdbVersion;
    }

    /**
     * Sets the value of the gdbVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdbVersion(String value) {
        this.gdbVersion = value;
    }

    /**
     * Gets the value of the maximumAllowableOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumAllowableOffset() {
        return maximumAllowableOffset;
    }

    /**
     * Sets the value of the maximumAllowableOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumAllowableOffset(Double value) {
        this.maximumAllowableOffset = value;
    }

}

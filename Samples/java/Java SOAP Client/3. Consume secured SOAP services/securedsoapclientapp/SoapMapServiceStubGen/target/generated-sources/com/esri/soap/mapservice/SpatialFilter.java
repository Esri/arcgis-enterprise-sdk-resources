
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri Spatial Filter Object.
 * 
 * &lt;p&gt;Java class for SpatialFilter complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SpatialFilter"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}QueryFilter"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SearchOrder" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSearchOrder"/&amp;gt;
 *         &amp;lt;element name="SpatialRel" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSpatialRelEnum"/&amp;gt;
 *         &amp;lt;element name="SpatialRelDescription" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="FilterGeometry" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Geometry"/&amp;gt;
 *         &amp;lt;element name="GeometryFieldName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="FilterOwnsGeometry" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpatialFilter", propOrder = {
    "searchOrder",
    "spatialRel",
    "spatialRelDescription",
    "filterGeometry",
    "geometryFieldName",
    "filterOwnsGeometry"
})
@XmlSeeAlso({
    TimeQueryFilter.class
})
public class SpatialFilter
    extends QueryFilter
{

    @XmlElement(name = "SearchOrder", required = true)
    @XmlSchemaType(name = "string")
    protected EsriSearchOrder searchOrder;
    @XmlElement(name = "SpatialRel", required = true)
    @XmlSchemaType(name = "string")
    protected EsriSpatialRelEnum spatialRel;
    @XmlElement(name = "SpatialRelDescription", required = true)
    protected String spatialRelDescription;
    @XmlElement(name = "FilterGeometry", required = true)
    protected Geometry filterGeometry;
    @XmlElement(name = "GeometryFieldName", required = true)
    protected String geometryFieldName;
    @XmlElement(name = "FilterOwnsGeometry")
    protected boolean filterOwnsGeometry;

    /**
     * Gets the value of the searchOrder property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSearchOrder }
     *     
     */
    public EsriSearchOrder getSearchOrder() {
        return searchOrder;
    }

    /**
     * Sets the value of the searchOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSearchOrder }
     *     
     */
    public void setSearchOrder(EsriSearchOrder value) {
        this.searchOrder = value;
    }

    /**
     * Gets the value of the spatialRel property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSpatialRelEnum }
     *     
     */
    public EsriSpatialRelEnum getSpatialRel() {
        return spatialRel;
    }

    /**
     * Sets the value of the spatialRel property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSpatialRelEnum }
     *     
     */
    public void setSpatialRel(EsriSpatialRelEnum value) {
        this.spatialRel = value;
    }

    /**
     * Gets the value of the spatialRelDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpatialRelDescription() {
        return spatialRelDescription;
    }

    /**
     * Sets the value of the spatialRelDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpatialRelDescription(String value) {
        this.spatialRelDescription = value;
    }

    /**
     * Gets the value of the filterGeometry property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getFilterGeometry() {
        return filterGeometry;
    }

    /**
     * Sets the value of the filterGeometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setFilterGeometry(Geometry value) {
        this.filterGeometry = value;
    }

    /**
     * Gets the value of the geometryFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeometryFieldName() {
        return geometryFieldName;
    }

    /**
     * Sets the value of the geometryFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeometryFieldName(String value) {
        this.geometryFieldName = value;
    }

    /**
     * Gets the value of the filterOwnsGeometry property.
     * 
     */
    public boolean isFilterOwnsGeometry() {
        return filterOwnsGeometry;
    }

    /**
     * Sets the value of the filterOwnsGeometry property.
     * 
     */
    public void setFilterOwnsGeometry(boolean value) {
        this.filterOwnsGeometry = value;
    }

}

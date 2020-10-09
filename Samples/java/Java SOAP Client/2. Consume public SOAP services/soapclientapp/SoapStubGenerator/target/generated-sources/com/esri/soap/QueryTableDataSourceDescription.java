
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for QueryTableDataSourceDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="QueryTableDataSourceDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}DataSourceDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Query" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="OIDFields" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="GeometryType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriGeometryType"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryTableDataSourceDescription", propOrder = {
    "query",
    "oidFields",
    "geometryType",
    "spatialReference"
})
public class QueryTableDataSourceDescription
    extends DataSourceDescription
{

    @XmlElement(name = "Query", required = true)
    protected String query;
    @XmlElement(name = "OIDFields", required = true)
    protected String oidFields;
    @XmlElement(name = "GeometryType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriGeometryType geometryType;
    @XmlElement(name = "SpatialReference", required = true)
    protected SpatialReference spatialReference;

    /**
     * Gets the value of the query property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the value of the query property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuery(String value) {
        this.query = value;
    }

    /**
     * Gets the value of the oidFields property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOIDFields() {
        return oidFields;
    }

    /**
     * Sets the value of the oidFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOIDFields(String value) {
        this.oidFields = value;
    }

    /**
     * Gets the value of the geometryType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriGeometryType }
     *     
     */
    public EsriGeometryType getGeometryType() {
        return geometryType;
    }

    /**
     * Sets the value of the geometryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriGeometryType }
     *     
     */
    public void setGeometryType(EsriGeometryType value) {
        this.geometryType = value;
    }

    /**
     * Gets the value of the spatialReference property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialReference }
     *     
     */
    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    /**
     * Sets the value of the spatialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialReference }
     *     
     */
    public void setSpatialReference(SpatialReference value) {
        this.spatialReference = value;
    }

}

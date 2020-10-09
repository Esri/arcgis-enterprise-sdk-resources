
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RelateDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RelateDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RelationshipID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="RelatedTableDefinitionExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RelatedTableFields" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OutputSpatialReference" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GeoTransformation" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}GeoTransformation" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IncludeGeometry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GeometryResultOptions" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}GeometryResultOptions" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ResultFormat" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriRelateResultFormat"/&amp;gt;
 *         &amp;lt;element name="OutputTimeReference" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}TimeReference" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelateDescription", propOrder = {
    "relationshipID",
    "relatedTableDefinitionExpression",
    "relatedTableFields",
    "outputSpatialReference",
    "geoTransformation",
    "includeGeometry",
    "geometryResultOptions",
    "resultFormat",
    "outputTimeReference"
})
public class RelateDescription {

    @XmlElement(name = "RelationshipID")
    protected int relationshipID;
    @XmlElement(name = "RelatedTableDefinitionExpression")
    protected String relatedTableDefinitionExpression;
    @XmlElement(name = "RelatedTableFields")
    protected String relatedTableFields;
    @XmlElement(name = "OutputSpatialReference")
    protected SpatialReference outputSpatialReference;
    @XmlElement(name = "GeoTransformation")
    protected GeoTransformation geoTransformation;
    @XmlElement(name = "IncludeGeometry", defaultValue = "true")
    protected Boolean includeGeometry;
    @XmlElement(name = "GeometryResultOptions")
    protected GeometryResultOptions geometryResultOptions;
    @XmlElement(name = "ResultFormat", required = true)
    @XmlSchemaType(name = "string")
    protected EsriRelateResultFormat resultFormat;
    @XmlElement(name = "OutputTimeReference")
    protected TimeReference outputTimeReference;

    /**
     * Gets the value of the relationshipID property.
     * 
     */
    public int getRelationshipID() {
        return relationshipID;
    }

    /**
     * Sets the value of the relationshipID property.
     * 
     */
    public void setRelationshipID(int value) {
        this.relationshipID = value;
    }

    /**
     * Gets the value of the relatedTableDefinitionExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedTableDefinitionExpression() {
        return relatedTableDefinitionExpression;
    }

    /**
     * Sets the value of the relatedTableDefinitionExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedTableDefinitionExpression(String value) {
        this.relatedTableDefinitionExpression = value;
    }

    /**
     * Gets the value of the relatedTableFields property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedTableFields() {
        return relatedTableFields;
    }

    /**
     * Sets the value of the relatedTableFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedTableFields(String value) {
        this.relatedTableFields = value;
    }

    /**
     * Gets the value of the outputSpatialReference property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialReference }
     *     
     */
    public SpatialReference getOutputSpatialReference() {
        return outputSpatialReference;
    }

    /**
     * Sets the value of the outputSpatialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialReference }
     *     
     */
    public void setOutputSpatialReference(SpatialReference value) {
        this.outputSpatialReference = value;
    }

    /**
     * Gets the value of the geoTransformation property.
     * 
     * @return
     *     possible object is
     *     {@link GeoTransformation }
     *     
     */
    public GeoTransformation getGeoTransformation() {
        return geoTransformation;
    }

    /**
     * Sets the value of the geoTransformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoTransformation }
     *     
     */
    public void setGeoTransformation(GeoTransformation value) {
        this.geoTransformation = value;
    }

    /**
     * Gets the value of the includeGeometry property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeGeometry() {
        return includeGeometry;
    }

    /**
     * Sets the value of the includeGeometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeGeometry(Boolean value) {
        this.includeGeometry = value;
    }

    /**
     * Gets the value of the geometryResultOptions property.
     * 
     * @return
     *     possible object is
     *     {@link GeometryResultOptions }
     *     
     */
    public GeometryResultOptions getGeometryResultOptions() {
        return geometryResultOptions;
    }

    /**
     * Sets the value of the geometryResultOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryResultOptions }
     *     
     */
    public void setGeometryResultOptions(GeometryResultOptions value) {
        this.geometryResultOptions = value;
    }

    /**
     * Gets the value of the resultFormat property.
     * 
     * @return
     *     possible object is
     *     {@link EsriRelateResultFormat }
     *     
     */
    public EsriRelateResultFormat getResultFormat() {
        return resultFormat;
    }

    /**
     * Sets the value of the resultFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriRelateResultFormat }
     *     
     */
    public void setResultFormat(EsriRelateResultFormat value) {
        this.resultFormat = value;
    }

    /**
     * Gets the value of the outputTimeReference property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getOutputTimeReference() {
        return outputTimeReference;
    }

    /**
     * Sets the value of the outputTimeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setOutputTimeReference(TimeReference value) {
        this.outputTimeReference = value;
    }

}

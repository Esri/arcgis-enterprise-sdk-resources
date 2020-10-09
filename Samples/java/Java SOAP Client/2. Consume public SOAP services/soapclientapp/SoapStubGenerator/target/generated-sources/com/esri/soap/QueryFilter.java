
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri Query Filter Object.
 * 
 * &lt;p&gt;Java class for QueryFilter complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="QueryFilter"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SubFields" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WhereClause" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="SpatialReferenceFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Resolution" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="OutputSpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FIDSet" type="{http://www.esri.com/schemas/ArcGIS/10.7}FIDSet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PostfixClause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FilterDefs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfFilterDef" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PrefixClause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryFilter", propOrder = {
    "subFields",
    "whereClause",
    "spatialReferenceFieldName",
    "resolution",
    "outputSpatialReference",
    "fidSet",
    "postfixClause",
    "filterDefs",
    "prefixClause"
})
@XmlSeeAlso({
    SpatialFilter.class
})
public class QueryFilter {

    @XmlElement(name = "SubFields")
    protected String subFields;
    @XmlElement(name = "WhereClause", required = true)
    protected String whereClause;
    @XmlElement(name = "SpatialReferenceFieldName")
    protected String spatialReferenceFieldName;
    @XmlElement(name = "Resolution")
    protected double resolution;
    @XmlElement(name = "OutputSpatialReference")
    protected SpatialReference outputSpatialReference;
    @XmlElement(name = "FIDSet")
    protected FIDSet fidSet;
    @XmlElement(name = "PostfixClause")
    protected String postfixClause;
    @XmlElement(name = "FilterDefs")
    protected ArrayOfFilterDef filterDefs;
    @XmlElement(name = "PrefixClause")
    protected String prefixClause;

    /**
     * Gets the value of the subFields property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubFields() {
        return subFields;
    }

    /**
     * Sets the value of the subFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubFields(String value) {
        this.subFields = value;
    }

    /**
     * Gets the value of the whereClause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClause() {
        return whereClause;
    }

    /**
     * Sets the value of the whereClause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClause(String value) {
        this.whereClause = value;
    }

    /**
     * Gets the value of the spatialReferenceFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpatialReferenceFieldName() {
        return spatialReferenceFieldName;
    }

    /**
     * Sets the value of the spatialReferenceFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpatialReferenceFieldName(String value) {
        this.spatialReferenceFieldName = value;
    }

    /**
     * Gets the value of the resolution property.
     * 
     */
    public double getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     */
    public void setResolution(double value) {
        this.resolution = value;
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
     * Gets the value of the fidSet property.
     * 
     * @return
     *     possible object is
     *     {@link FIDSet }
     *     
     */
    public FIDSet getFIDSet() {
        return fidSet;
    }

    /**
     * Sets the value of the fidSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link FIDSet }
     *     
     */
    public void setFIDSet(FIDSet value) {
        this.fidSet = value;
    }

    /**
     * Gets the value of the postfixClause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostfixClause() {
        return postfixClause;
    }

    /**
     * Sets the value of the postfixClause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostfixClause(String value) {
        this.postfixClause = value;
    }

    /**
     * Gets the value of the filterDefs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFilterDef }
     *     
     */
    public ArrayOfFilterDef getFilterDefs() {
        return filterDefs;
    }

    /**
     * Sets the value of the filterDefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFilterDef }
     *     
     */
    public void setFilterDefs(ArrayOfFilterDef value) {
        this.filterDefs = value;
    }

    /**
     * Gets the value of the prefixClause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefixClause() {
        return prefixClause;
    }

    /**
     * Sets the value of the prefixClause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefixClause(String value) {
        this.prefixClause = value;
    }

}


package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for StandaloneTableInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="StandaloneTableInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}MapTableInfo"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Fields" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Fields" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RelateInfos" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfRelateInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsTime" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StartTimeFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EndTimeFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeValueFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TrackIDFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeReference" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}TimeReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FullTimeExtent" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}TimeExtent" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeInterval" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeIntervalUnits" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriTimeUnits" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasAttachments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DisplayField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IDField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasSubtype" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsStatistics" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsAdvancedQueries" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandaloneTableInfo", propOrder = {
    "id",
    "name",
    "fields",
    "relateInfos",
    "supportsTime",
    "startTimeFieldName",
    "endTimeFieldName",
    "timeValueFormat",
    "trackIDFieldName",
    "timeReference",
    "fullTimeExtent",
    "timeInterval",
    "timeIntervalUnits",
    "hasAttachments",
    "displayField",
    "description",
    "idField",
    "hasSubtype",
    "supportsStatistics",
    "supportsAdvancedQueries"
})
public class StandaloneTableInfo
    extends MapTableInfo
{

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Fields")
    protected Fields fields;
    @XmlElement(name = "RelateInfos")
    protected ArrayOfRelateInfo relateInfos;
    @XmlElement(name = "SupportsTime")
    protected Boolean supportsTime;
    @XmlElement(name = "StartTimeFieldName")
    protected String startTimeFieldName;
    @XmlElement(name = "EndTimeFieldName")
    protected String endTimeFieldName;
    @XmlElement(name = "TimeValueFormat")
    protected String timeValueFormat;
    @XmlElement(name = "TrackIDFieldName")
    protected String trackIDFieldName;
    @XmlElement(name = "TimeReference")
    protected TimeReference timeReference;
    @XmlElement(name = "FullTimeExtent")
    protected TimeExtent fullTimeExtent;
    @XmlElement(name = "TimeInterval", defaultValue = "0.0")
    protected Double timeInterval;
    @XmlElement(name = "TimeIntervalUnits")
    @XmlSchemaType(name = "string")
    protected EsriTimeUnits timeIntervalUnits;
    @XmlElement(name = "HasAttachments")
    protected Boolean hasAttachments;
    @XmlElement(name = "DisplayField")
    protected String displayField;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "IDField")
    protected String idField;
    @XmlElement(name = "HasSubtype")
    protected Boolean hasSubtype;
    @XmlElement(name = "SupportsStatistics")
    protected Boolean supportsStatistics;
    @XmlElement(name = "SupportsAdvancedQueries")
    protected Boolean supportsAdvancedQueries;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the fields property.
     * 
     * @return
     *     possible object is
     *     {@link Fields }
     *     
     */
    public Fields getFields() {
        return fields;
    }

    /**
     * Sets the value of the fields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fields }
     *     
     */
    public void setFields(Fields value) {
        this.fields = value;
    }

    /**
     * Gets the value of the relateInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRelateInfo }
     *     
     */
    public ArrayOfRelateInfo getRelateInfos() {
        return relateInfos;
    }

    /**
     * Sets the value of the relateInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRelateInfo }
     *     
     */
    public void setRelateInfos(ArrayOfRelateInfo value) {
        this.relateInfos = value;
    }

    /**
     * Gets the value of the supportsTime property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportsTime() {
        return supportsTime;
    }

    /**
     * Sets the value of the supportsTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportsTime(Boolean value) {
        this.supportsTime = value;
    }

    /**
     * Gets the value of the startTimeFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTimeFieldName() {
        return startTimeFieldName;
    }

    /**
     * Sets the value of the startTimeFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTimeFieldName(String value) {
        this.startTimeFieldName = value;
    }

    /**
     * Gets the value of the endTimeFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTimeFieldName() {
        return endTimeFieldName;
    }

    /**
     * Sets the value of the endTimeFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTimeFieldName(String value) {
        this.endTimeFieldName = value;
    }

    /**
     * Gets the value of the timeValueFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeValueFormat() {
        return timeValueFormat;
    }

    /**
     * Sets the value of the timeValueFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeValueFormat(String value) {
        this.timeValueFormat = value;
    }

    /**
     * Gets the value of the trackIDFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackIDFieldName() {
        return trackIDFieldName;
    }

    /**
     * Sets the value of the trackIDFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackIDFieldName(String value) {
        this.trackIDFieldName = value;
    }

    /**
     * Gets the value of the timeReference property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getTimeReference() {
        return timeReference;
    }

    /**
     * Sets the value of the timeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setTimeReference(TimeReference value) {
        this.timeReference = value;
    }

    /**
     * Gets the value of the fullTimeExtent property.
     * 
     * @return
     *     possible object is
     *     {@link TimeExtent }
     *     
     */
    public TimeExtent getFullTimeExtent() {
        return fullTimeExtent;
    }

    /**
     * Sets the value of the fullTimeExtent property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeExtent }
     *     
     */
    public void setFullTimeExtent(TimeExtent value) {
        this.fullTimeExtent = value;
    }

    /**
     * Gets the value of the timeInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTimeInterval() {
        return timeInterval;
    }

    /**
     * Sets the value of the timeInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTimeInterval(Double value) {
        this.timeInterval = value;
    }

    /**
     * Gets the value of the timeIntervalUnits property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTimeUnits }
     *     
     */
    public EsriTimeUnits getTimeIntervalUnits() {
        return timeIntervalUnits;
    }

    /**
     * Sets the value of the timeIntervalUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTimeUnits }
     *     
     */
    public void setTimeIntervalUnits(EsriTimeUnits value) {
        this.timeIntervalUnits = value;
    }

    /**
     * Gets the value of the hasAttachments property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasAttachments() {
        return hasAttachments;
    }

    /**
     * Sets the value of the hasAttachments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasAttachments(Boolean value) {
        this.hasAttachments = value;
    }

    /**
     * Gets the value of the displayField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayField() {
        return displayField;
    }

    /**
     * Sets the value of the displayField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayField(String value) {
        this.displayField = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the idField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDField() {
        return idField;
    }

    /**
     * Sets the value of the idField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDField(String value) {
        this.idField = value;
    }

    /**
     * Gets the value of the hasSubtype property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasSubtype() {
        return hasSubtype;
    }

    /**
     * Sets the value of the hasSubtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasSubtype(Boolean value) {
        this.hasSubtype = value;
    }

    /**
     * Gets the value of the supportsStatistics property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportsStatistics() {
        return supportsStatistics;
    }

    /**
     * Sets the value of the supportsStatistics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportsStatistics(Boolean value) {
        this.supportsStatistics = value;
    }

    /**
     * Gets the value of the supportsAdvancedQueries property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportsAdvancedQueries() {
        return supportsAdvancedQueries;
    }

    /**
     * Sets the value of the supportsAdvancedQueries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportsAdvancedQueries(Boolean value) {
        this.supportsAdvancedQueries = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for DataObjectTable complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DataObjectTable"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CopyrightText" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="DisplayPropName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="GlobalIDPropName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="HTMLPopupType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriServerHTMLPopupType"/&amp;gt;
 *         &amp;lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="OIDPropName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="PropertyInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfPropertyInfo"/&amp;gt;
 *         &amp;lt;element name="Relations" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfRelateInfo"/&amp;gt;
 *         &amp;lt;element name="Templates" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfTemplateInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TypeIDPropName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Types" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfDataObjectType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsTime" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StartTimeFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EndTimeFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeValueFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TrackIDFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FullTimeExtent" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeExtent" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeInterval" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeIntervalUnits" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTimeUnits" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasAttachments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EditorTrackingInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}EditorTrackingInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OwnershipBasedAccessControl" type="{http://www.esri.com/schemas/ArcGIS/10.7}OwnershipBasedAccessControl" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SyncCanReturnChanges" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsDataVersioned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsRollbackOnFailureParameter" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectTable", propOrder = {
    "copyrightText",
    "description",
    "displayPropName",
    "globalIDPropName",
    "htmlPopupType",
    "id",
    "name",
    "oidPropName",
    "propertyInfos",
    "relations",
    "templates",
    "typeIDPropName",
    "types",
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
    "editorTrackingInfo",
    "ownershipBasedAccessControl",
    "syncCanReturnChanges",
    "isDataVersioned",
    "supportsRollbackOnFailureParameter"
})
@XmlSeeAlso({
    GraphicFeatureLayer.class
})
public class DataObjectTable {

    @XmlElement(name = "CopyrightText", required = true)
    protected String copyrightText;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "DisplayPropName", required = true)
    protected String displayPropName;
    @XmlElement(name = "GlobalIDPropName", required = true)
    protected String globalIDPropName;
    @XmlElement(name = "HTMLPopupType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriServerHTMLPopupType htmlPopupType;
    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "OIDPropName", required = true)
    protected String oidPropName;
    @XmlElement(name = "PropertyInfos", required = true)
    protected ArrayOfPropertyInfo propertyInfos;
    @XmlElement(name = "Relations", required = true)
    protected ArrayOfRelateInfo relations;
    @XmlElement(name = "Templates")
    protected ArrayOfTemplateInfo templates;
    @XmlElement(name = "TypeIDPropName")
    protected String typeIDPropName;
    @XmlElement(name = "Types")
    protected ArrayOfDataObjectType types;
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
    @XmlElement(name = "EditorTrackingInfo")
    protected EditorTrackingInfo editorTrackingInfo;
    @XmlElement(name = "OwnershipBasedAccessControl")
    protected OwnershipBasedAccessControl ownershipBasedAccessControl;
    @XmlElement(name = "SyncCanReturnChanges")
    protected Boolean syncCanReturnChanges;
    @XmlElement(name = "IsDataVersioned")
    protected Boolean isDataVersioned;
    @XmlElement(name = "SupportsRollbackOnFailureParameter")
    protected Boolean supportsRollbackOnFailureParameter;

    /**
     * Gets the value of the copyrightText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyrightText() {
        return copyrightText;
    }

    /**
     * Sets the value of the copyrightText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyrightText(String value) {
        this.copyrightText = value;
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
     * Gets the value of the displayPropName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayPropName() {
        return displayPropName;
    }

    /**
     * Sets the value of the displayPropName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayPropName(String value) {
        this.displayPropName = value;
    }

    /**
     * Gets the value of the globalIDPropName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalIDPropName() {
        return globalIDPropName;
    }

    /**
     * Sets the value of the globalIDPropName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalIDPropName(String value) {
        this.globalIDPropName = value;
    }

    /**
     * Gets the value of the htmlPopupType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriServerHTMLPopupType }
     *     
     */
    public EsriServerHTMLPopupType getHTMLPopupType() {
        return htmlPopupType;
    }

    /**
     * Sets the value of the htmlPopupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriServerHTMLPopupType }
     *     
     */
    public void setHTMLPopupType(EsriServerHTMLPopupType value) {
        this.htmlPopupType = value;
    }

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
     * Gets the value of the oidPropName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOIDPropName() {
        return oidPropName;
    }

    /**
     * Sets the value of the oidPropName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOIDPropName(String value) {
        this.oidPropName = value;
    }

    /**
     * Gets the value of the propertyInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPropertyInfo }
     *     
     */
    public ArrayOfPropertyInfo getPropertyInfos() {
        return propertyInfos;
    }

    /**
     * Sets the value of the propertyInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPropertyInfo }
     *     
     */
    public void setPropertyInfos(ArrayOfPropertyInfo value) {
        this.propertyInfos = value;
    }

    /**
     * Gets the value of the relations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRelateInfo }
     *     
     */
    public ArrayOfRelateInfo getRelations() {
        return relations;
    }

    /**
     * Sets the value of the relations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRelateInfo }
     *     
     */
    public void setRelations(ArrayOfRelateInfo value) {
        this.relations = value;
    }

    /**
     * Gets the value of the templates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTemplateInfo }
     *     
     */
    public ArrayOfTemplateInfo getTemplates() {
        return templates;
    }

    /**
     * Sets the value of the templates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTemplateInfo }
     *     
     */
    public void setTemplates(ArrayOfTemplateInfo value) {
        this.templates = value;
    }

    /**
     * Gets the value of the typeIDPropName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeIDPropName() {
        return typeIDPropName;
    }

    /**
     * Sets the value of the typeIDPropName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeIDPropName(String value) {
        this.typeIDPropName = value;
    }

    /**
     * Gets the value of the types property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDataObjectType }
     *     
     */
    public ArrayOfDataObjectType getTypes() {
        return types;
    }

    /**
     * Sets the value of the types property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDataObjectType }
     *     
     */
    public void setTypes(ArrayOfDataObjectType value) {
        this.types = value;
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
     * Gets the value of the editorTrackingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EditorTrackingInfo }
     *     
     */
    public EditorTrackingInfo getEditorTrackingInfo() {
        return editorTrackingInfo;
    }

    /**
     * Sets the value of the editorTrackingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EditorTrackingInfo }
     *     
     */
    public void setEditorTrackingInfo(EditorTrackingInfo value) {
        this.editorTrackingInfo = value;
    }

    /**
     * Gets the value of the ownershipBasedAccessControl property.
     * 
     * @return
     *     possible object is
     *     {@link OwnershipBasedAccessControl }
     *     
     */
    public OwnershipBasedAccessControl getOwnershipBasedAccessControl() {
        return ownershipBasedAccessControl;
    }

    /**
     * Sets the value of the ownershipBasedAccessControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnershipBasedAccessControl }
     *     
     */
    public void setOwnershipBasedAccessControl(OwnershipBasedAccessControl value) {
        this.ownershipBasedAccessControl = value;
    }

    /**
     * Gets the value of the syncCanReturnChanges property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSyncCanReturnChanges() {
        return syncCanReturnChanges;
    }

    /**
     * Sets the value of the syncCanReturnChanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSyncCanReturnChanges(Boolean value) {
        this.syncCanReturnChanges = value;
    }

    /**
     * Gets the value of the isDataVersioned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDataVersioned() {
        return isDataVersioned;
    }

    /**
     * Sets the value of the isDataVersioned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDataVersioned(Boolean value) {
        this.isDataVersioned = value;
    }

    /**
     * Gets the value of the supportsRollbackOnFailureParameter property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportsRollbackOnFailureParameter() {
        return supportsRollbackOnFailureParameter;
    }

    /**
     * Sets the value of the supportsRollbackOnFailureParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportsRollbackOnFailureParameter(Boolean value) {
        this.supportsRollbackOnFailureParameter = value;
    }

}

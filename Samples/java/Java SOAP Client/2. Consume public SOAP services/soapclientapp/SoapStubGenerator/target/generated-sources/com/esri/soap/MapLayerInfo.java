
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapLayerInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapLayerInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}MapTableInfo"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LayerID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LayerType" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="SourceDescription" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="HasLabels" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="CanSelect" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="CanScaleSymbols" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="MinScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="MaxScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="Extent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasHyperlinks" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="HasAttributes" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="CanIdentify" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="CanFind" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="IsFeatureLayer" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="Fields" type="{http://www.esri.com/schemas/ArcGIS/10.7}Fields" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DisplayField" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="IDField" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="IsComposite" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="SubLayerIDs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ParentLayerID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="FieldAliases" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfString" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CopyrightText" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="RelateInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfRelateInfo" minOccurs="0"/&amp;gt;
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
 *         &amp;lt;element name="HTMLPopupType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriServerHTMLPopupType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasLayerDrawingDescription" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasSubtype" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CanModifyDrawingDescription" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsStatistics" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsAdvancedQueries" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasLiveData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapLayerInfo", propOrder = {
    "layerID",
    "name",
    "description",
    "layerType",
    "sourceDescription",
    "hasLabels",
    "canSelect",
    "canScaleSymbols",
    "minScale",
    "maxScale",
    "extent",
    "hasHyperlinks",
    "hasAttributes",
    "canIdentify",
    "canFind",
    "isFeatureLayer",
    "fields",
    "displayField",
    "idField",
    "isComposite",
    "subLayerIDs",
    "parentLayerID",
    "fieldAliases",
    "copyrightText",
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
    "htmlPopupType",
    "hasLayerDrawingDescription",
    "hasSubtype",
    "canModifyDrawingDescription",
    "supportsStatistics",
    "supportsAdvancedQueries",
    "hasLiveData"
})
public class MapLayerInfo
    extends MapTableInfo
{

    @XmlElement(name = "LayerID")
    protected int layerID;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "LayerType", required = true)
    protected String layerType;
    @XmlElement(name = "SourceDescription", required = true)
    protected String sourceDescription;
    @XmlElement(name = "HasLabels")
    protected boolean hasLabels;
    @XmlElement(name = "CanSelect")
    protected boolean canSelect;
    @XmlElement(name = "CanScaleSymbols")
    protected boolean canScaleSymbols;
    @XmlElement(name = "MinScale")
    protected double minScale;
    @XmlElement(name = "MaxScale")
    protected double maxScale;
    @XmlElement(name = "Extent")
    protected Envelope extent;
    @XmlElement(name = "HasHyperlinks")
    protected boolean hasHyperlinks;
    @XmlElement(name = "HasAttributes")
    protected boolean hasAttributes;
    @XmlElement(name = "CanIdentify")
    protected boolean canIdentify;
    @XmlElement(name = "CanFind")
    protected boolean canFind;
    @XmlElement(name = "IsFeatureLayer")
    protected boolean isFeatureLayer;
    @XmlElement(name = "Fields")
    protected Fields fields;
    @XmlElement(name = "DisplayField", required = true)
    protected String displayField;
    @XmlElement(name = "IDField", required = true)
    protected String idField;
    @XmlElement(name = "IsComposite")
    protected boolean isComposite;
    @XmlElement(name = "SubLayerIDs")
    protected ArrayOfInt subLayerIDs;
    @XmlElement(name = "ParentLayerID")
    protected int parentLayerID;
    @XmlElement(name = "FieldAliases")
    protected ArrayOfString fieldAliases;
    @XmlElement(name = "CopyrightText", required = true)
    protected String copyrightText;
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
    @XmlElement(name = "HTMLPopupType")
    @XmlSchemaType(name = "string")
    protected EsriServerHTMLPopupType htmlPopupType;
    @XmlElement(name = "HasLayerDrawingDescription")
    protected Boolean hasLayerDrawingDescription;
    @XmlElement(name = "HasSubtype")
    protected Boolean hasSubtype;
    @XmlElement(name = "CanModifyDrawingDescription")
    protected Boolean canModifyDrawingDescription;
    @XmlElement(name = "SupportsStatistics")
    protected Boolean supportsStatistics;
    @XmlElement(name = "SupportsAdvancedQueries")
    protected Boolean supportsAdvancedQueries;
    @XmlElement(name = "HasLiveData")
    protected Boolean hasLiveData;

    /**
     * Gets the value of the layerID property.
     * 
     */
    public int getLayerID() {
        return layerID;
    }

    /**
     * Sets the value of the layerID property.
     * 
     */
    public void setLayerID(int value) {
        this.layerID = value;
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
     * Gets the value of the layerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayerType() {
        return layerType;
    }

    /**
     * Sets the value of the layerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayerType(String value) {
        this.layerType = value;
    }

    /**
     * Gets the value of the sourceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDescription() {
        return sourceDescription;
    }

    /**
     * Sets the value of the sourceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDescription(String value) {
        this.sourceDescription = value;
    }

    /**
     * Gets the value of the hasLabels property.
     * 
     */
    public boolean isHasLabels() {
        return hasLabels;
    }

    /**
     * Sets the value of the hasLabels property.
     * 
     */
    public void setHasLabels(boolean value) {
        this.hasLabels = value;
    }

    /**
     * Gets the value of the canSelect property.
     * 
     */
    public boolean isCanSelect() {
        return canSelect;
    }

    /**
     * Sets the value of the canSelect property.
     * 
     */
    public void setCanSelect(boolean value) {
        this.canSelect = value;
    }

    /**
     * Gets the value of the canScaleSymbols property.
     * 
     */
    public boolean isCanScaleSymbols() {
        return canScaleSymbols;
    }

    /**
     * Sets the value of the canScaleSymbols property.
     * 
     */
    public void setCanScaleSymbols(boolean value) {
        this.canScaleSymbols = value;
    }

    /**
     * Gets the value of the minScale property.
     * 
     */
    public double getMinScale() {
        return minScale;
    }

    /**
     * Sets the value of the minScale property.
     * 
     */
    public void setMinScale(double value) {
        this.minScale = value;
    }

    /**
     * Gets the value of the maxScale property.
     * 
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * Sets the value of the maxScale property.
     * 
     */
    public void setMaxScale(double value) {
        this.maxScale = value;
    }

    /**
     * Gets the value of the extent property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getExtent() {
        return extent;
    }

    /**
     * Sets the value of the extent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setExtent(Envelope value) {
        this.extent = value;
    }

    /**
     * Gets the value of the hasHyperlinks property.
     * 
     */
    public boolean isHasHyperlinks() {
        return hasHyperlinks;
    }

    /**
     * Sets the value of the hasHyperlinks property.
     * 
     */
    public void setHasHyperlinks(boolean value) {
        this.hasHyperlinks = value;
    }

    /**
     * Gets the value of the hasAttributes property.
     * 
     */
    public boolean isHasAttributes() {
        return hasAttributes;
    }

    /**
     * Sets the value of the hasAttributes property.
     * 
     */
    public void setHasAttributes(boolean value) {
        this.hasAttributes = value;
    }

    /**
     * Gets the value of the canIdentify property.
     * 
     */
    public boolean isCanIdentify() {
        return canIdentify;
    }

    /**
     * Sets the value of the canIdentify property.
     * 
     */
    public void setCanIdentify(boolean value) {
        this.canIdentify = value;
    }

    /**
     * Gets the value of the canFind property.
     * 
     */
    public boolean isCanFind() {
        return canFind;
    }

    /**
     * Sets the value of the canFind property.
     * 
     */
    public void setCanFind(boolean value) {
        this.canFind = value;
    }

    /**
     * Gets the value of the isFeatureLayer property.
     * 
     */
    public boolean isIsFeatureLayer() {
        return isFeatureLayer;
    }

    /**
     * Sets the value of the isFeatureLayer property.
     * 
     */
    public void setIsFeatureLayer(boolean value) {
        this.isFeatureLayer = value;
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
     * Gets the value of the isComposite property.
     * 
     */
    public boolean isIsComposite() {
        return isComposite;
    }

    /**
     * Sets the value of the isComposite property.
     * 
     */
    public void setIsComposite(boolean value) {
        this.isComposite = value;
    }

    /**
     * Gets the value of the subLayerIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getSubLayerIDs() {
        return subLayerIDs;
    }

    /**
     * Sets the value of the subLayerIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setSubLayerIDs(ArrayOfInt value) {
        this.subLayerIDs = value;
    }

    /**
     * Gets the value of the parentLayerID property.
     * 
     */
    public int getParentLayerID() {
        return parentLayerID;
    }

    /**
     * Sets the value of the parentLayerID property.
     * 
     */
    public void setParentLayerID(int value) {
        this.parentLayerID = value;
    }

    /**
     * Gets the value of the fieldAliases property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getFieldAliases() {
        return fieldAliases;
    }

    /**
     * Sets the value of the fieldAliases property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setFieldAliases(ArrayOfString value) {
        this.fieldAliases = value;
    }

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
     * Gets the value of the hasLayerDrawingDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasLayerDrawingDescription() {
        return hasLayerDrawingDescription;
    }

    /**
     * Sets the value of the hasLayerDrawingDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasLayerDrawingDescription(Boolean value) {
        this.hasLayerDrawingDescription = value;
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
     * Gets the value of the canModifyDrawingDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCanModifyDrawingDescription() {
        return canModifyDrawingDescription;
    }

    /**
     * Sets the value of the canModifyDrawingDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCanModifyDrawingDescription(Boolean value) {
        this.canModifyDrawingDescription = value;
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

    /**
     * Gets the value of the hasLiveData property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasLiveData() {
        return hasLiveData;
    }

    /**
     * Sets the value of the hasLiveData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasLiveData(Boolean value) {
        this.hasLiveData = value;
    }

}

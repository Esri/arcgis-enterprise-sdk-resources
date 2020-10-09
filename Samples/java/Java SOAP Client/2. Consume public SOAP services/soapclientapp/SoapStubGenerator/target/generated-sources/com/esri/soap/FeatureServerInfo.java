
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for FeatureServerInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FeatureServerInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CopyrightText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CurrentVersion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DocumentInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}PropertySet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FullExtent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasVersionedData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InitialExtent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LayerIdentities" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfTableIdentity" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaxRecordCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ServiceDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsDisconnectedEditing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TableIdentities" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfTableIdentity" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Units" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriUnits" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EnableZDefaults" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZDefault" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AllowGeometryUpdates" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AllowTrueCurvesUpdates" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DateFieldsTimeReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeReference" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureServerInfo", propOrder = {
    "copyrightText",
    "currentVersion",
    "description",
    "documentInfo",
    "fullExtent",
    "hasVersionedData",
    "initialExtent",
    "layerIdentities",
    "maxRecordCount",
    "spatialReference",
    "serviceDescription",
    "supportsDisconnectedEditing",
    "tableIdentities",
    "units",
    "enableZDefaults",
    "zDefault",
    "allowGeometryUpdates",
    "allowTrueCurvesUpdates",
    "dateFieldsTimeReference"
})
public class FeatureServerInfo {

    @XmlElement(name = "CopyrightText")
    protected String copyrightText;
    @XmlElement(name = "CurrentVersion")
    protected Double currentVersion;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "DocumentInfo")
    protected PropertySet documentInfo;
    @XmlElement(name = "FullExtent")
    protected Envelope fullExtent;
    @XmlElement(name = "HasVersionedData")
    protected Boolean hasVersionedData;
    @XmlElement(name = "InitialExtent")
    protected Envelope initialExtent;
    @XmlElement(name = "LayerIdentities")
    protected ArrayOfTableIdentity layerIdentities;
    @XmlElement(name = "MaxRecordCount")
    protected Long maxRecordCount;
    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;
    @XmlElement(name = "ServiceDescription")
    protected String serviceDescription;
    @XmlElement(name = "SupportsDisconnectedEditing")
    protected Boolean supportsDisconnectedEditing;
    @XmlElement(name = "TableIdentities")
    protected ArrayOfTableIdentity tableIdentities;
    @XmlElement(name = "Units")
    @XmlSchemaType(name = "string")
    protected EsriUnits units;
    @XmlElement(name = "EnableZDefaults")
    protected Boolean enableZDefaults;
    @XmlElement(name = "ZDefault")
    protected Double zDefault;
    @XmlElement(name = "AllowGeometryUpdates")
    protected Boolean allowGeometryUpdates;
    @XmlElement(name = "AllowTrueCurvesUpdates")
    protected Boolean allowTrueCurvesUpdates;
    @XmlElement(name = "DateFieldsTimeReference")
    protected TimeReference dateFieldsTimeReference;

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
     * Gets the value of the currentVersion property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Sets the value of the currentVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCurrentVersion(Double value) {
        this.currentVersion = value;
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
     * Gets the value of the documentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PropertySet }
     *     
     */
    public PropertySet getDocumentInfo() {
        return documentInfo;
    }

    /**
     * Sets the value of the documentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertySet }
     *     
     */
    public void setDocumentInfo(PropertySet value) {
        this.documentInfo = value;
    }

    /**
     * Gets the value of the fullExtent property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getFullExtent() {
        return fullExtent;
    }

    /**
     * Sets the value of the fullExtent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setFullExtent(Envelope value) {
        this.fullExtent = value;
    }

    /**
     * Gets the value of the hasVersionedData property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasVersionedData() {
        return hasVersionedData;
    }

    /**
     * Sets the value of the hasVersionedData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasVersionedData(Boolean value) {
        this.hasVersionedData = value;
    }

    /**
     * Gets the value of the initialExtent property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getInitialExtent() {
        return initialExtent;
    }

    /**
     * Sets the value of the initialExtent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setInitialExtent(Envelope value) {
        this.initialExtent = value;
    }

    /**
     * Gets the value of the layerIdentities property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTableIdentity }
     *     
     */
    public ArrayOfTableIdentity getLayerIdentities() {
        return layerIdentities;
    }

    /**
     * Sets the value of the layerIdentities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTableIdentity }
     *     
     */
    public void setLayerIdentities(ArrayOfTableIdentity value) {
        this.layerIdentities = value;
    }

    /**
     * Gets the value of the maxRecordCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxRecordCount() {
        return maxRecordCount;
    }

    /**
     * Sets the value of the maxRecordCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxRecordCount(Long value) {
        this.maxRecordCount = value;
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

    /**
     * Gets the value of the serviceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * Sets the value of the serviceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescription(String value) {
        this.serviceDescription = value;
    }

    /**
     * Gets the value of the supportsDisconnectedEditing property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportsDisconnectedEditing() {
        return supportsDisconnectedEditing;
    }

    /**
     * Sets the value of the supportsDisconnectedEditing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportsDisconnectedEditing(Boolean value) {
        this.supportsDisconnectedEditing = value;
    }

    /**
     * Gets the value of the tableIdentities property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTableIdentity }
     *     
     */
    public ArrayOfTableIdentity getTableIdentities() {
        return tableIdentities;
    }

    /**
     * Sets the value of the tableIdentities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTableIdentity }
     *     
     */
    public void setTableIdentities(ArrayOfTableIdentity value) {
        this.tableIdentities = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link EsriUnits }
     *     
     */
    public EsriUnits getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriUnits }
     *     
     */
    public void setUnits(EsriUnits value) {
        this.units = value;
    }

    /**
     * Gets the value of the enableZDefaults property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnableZDefaults() {
        return enableZDefaults;
    }

    /**
     * Sets the value of the enableZDefaults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnableZDefaults(Boolean value) {
        this.enableZDefaults = value;
    }

    /**
     * Gets the value of the zDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZDefault() {
        return zDefault;
    }

    /**
     * Sets the value of the zDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZDefault(Double value) {
        this.zDefault = value;
    }

    /**
     * Gets the value of the allowGeometryUpdates property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowGeometryUpdates() {
        return allowGeometryUpdates;
    }

    /**
     * Sets the value of the allowGeometryUpdates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowGeometryUpdates(Boolean value) {
        this.allowGeometryUpdates = value;
    }

    /**
     * Gets the value of the allowTrueCurvesUpdates property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowTrueCurvesUpdates() {
        return allowTrueCurvesUpdates;
    }

    /**
     * Sets the value of the allowTrueCurvesUpdates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowTrueCurvesUpdates(Boolean value) {
        this.allowTrueCurvesUpdates = value;
    }

    /**
     * Gets the value of the dateFieldsTimeReference property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getDateFieldsTimeReference() {
        return dateFieldsTimeReference;
    }

    /**
     * Sets the value of the dateFieldsTimeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setDateFieldsTimeReference(TimeReference value) {
        this.dateFieldsTimeReference = value;
    }

}

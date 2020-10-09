
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="FullExtent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Extent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MapLayerInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfMapLayerInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BackgroundColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Bookmarks" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfMapServerBookmark" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultMapDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapDescription" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Units" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriUnits"/&amp;gt;
 *         &amp;lt;element name="SupportedImageReturnTypes" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriImageReturnType"/&amp;gt;
 *         &amp;lt;element name="BackgroundSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}FillSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CopyrightText" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="StandaloneTableInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfStandaloneTableInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StandaloneTableDescriptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfStandaloneTableDescription" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FullTimeExtent" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeExtent" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultTimeStepInterval" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultTimeStepIntervalUnits" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTimeUnits" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultTimeWindow" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportsDynamicLayers" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasLiveData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MinScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaxScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerInfo", propOrder = {
    "name",
    "description",
    "fullExtent",
    "extent",
    "spatialReference",
    "mapLayerInfos",
    "backgroundColor",
    "bookmarks",
    "defaultMapDescription",
    "units",
    "supportedImageReturnTypes",
    "backgroundSymbol",
    "copyrightText",
    "standaloneTableInfos",
    "standaloneTableDescriptions",
    "fullTimeExtent",
    "defaultTimeStepInterval",
    "defaultTimeStepIntervalUnits",
    "defaultTimeWindow",
    "supportsDynamicLayers",
    "hasLiveData",
    "minScale",
    "maxScale"
})
public class MapServerInfo {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "FullExtent")
    protected Envelope fullExtent;
    @XmlElement(name = "Extent")
    protected Envelope extent;
    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;
    @XmlElement(name = "MapLayerInfos")
    protected ArrayOfMapLayerInfo mapLayerInfos;
    @XmlElement(name = "BackgroundColor")
    protected Color backgroundColor;
    @XmlElement(name = "Bookmarks")
    protected ArrayOfMapServerBookmark bookmarks;
    @XmlElement(name = "DefaultMapDescription")
    protected MapDescription defaultMapDescription;
    @XmlElement(name = "Units", required = true)
    @XmlSchemaType(name = "string")
    protected EsriUnits units;
    @XmlElement(name = "SupportedImageReturnTypes", required = true)
    @XmlSchemaType(name = "string")
    protected EsriImageReturnType supportedImageReturnTypes;
    @XmlElement(name = "BackgroundSymbol")
    protected FillSymbol backgroundSymbol;
    @XmlElement(name = "CopyrightText", required = true)
    protected String copyrightText;
    @XmlElement(name = "StandaloneTableInfos")
    protected ArrayOfStandaloneTableInfo standaloneTableInfos;
    @XmlElement(name = "StandaloneTableDescriptions")
    protected ArrayOfStandaloneTableDescription standaloneTableDescriptions;
    @XmlElement(name = "FullTimeExtent")
    protected TimeExtent fullTimeExtent;
    @XmlElement(name = "DefaultTimeStepInterval")
    protected Double defaultTimeStepInterval;
    @XmlElement(name = "DefaultTimeStepIntervalUnits")
    @XmlSchemaType(name = "string")
    protected EsriTimeUnits defaultTimeStepIntervalUnits;
    @XmlElement(name = "DefaultTimeWindow")
    protected Double defaultTimeWindow;
    @XmlElement(name = "SupportsDynamicLayers")
    protected Boolean supportsDynamicLayers;
    @XmlElement(name = "HasLiveData")
    protected Boolean hasLiveData;
    @XmlElement(name = "MinScale")
    protected Double minScale;
    @XmlElement(name = "MaxScale")
    protected Double maxScale;

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
     * Gets the value of the mapLayerInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMapLayerInfo }
     *     
     */
    public ArrayOfMapLayerInfo getMapLayerInfos() {
        return mapLayerInfos;
    }

    /**
     * Sets the value of the mapLayerInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMapLayerInfo }
     *     
     */
    public void setMapLayerInfos(ArrayOfMapLayerInfo value) {
        this.mapLayerInfos = value;
    }

    /**
     * Gets the value of the backgroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the value of the backgroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setBackgroundColor(Color value) {
        this.backgroundColor = value;
    }

    /**
     * Gets the value of the bookmarks property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMapServerBookmark }
     *     
     */
    public ArrayOfMapServerBookmark getBookmarks() {
        return bookmarks;
    }

    /**
     * Sets the value of the bookmarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMapServerBookmark }
     *     
     */
    public void setBookmarks(ArrayOfMapServerBookmark value) {
        this.bookmarks = value;
    }

    /**
     * Gets the value of the defaultMapDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MapDescription }
     *     
     */
    public MapDescription getDefaultMapDescription() {
        return defaultMapDescription;
    }

    /**
     * Sets the value of the defaultMapDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapDescription }
     *     
     */
    public void setDefaultMapDescription(MapDescription value) {
        this.defaultMapDescription = value;
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
     * Gets the value of the supportedImageReturnTypes property.
     * 
     * @return
     *     possible object is
     *     {@link EsriImageReturnType }
     *     
     */
    public EsriImageReturnType getSupportedImageReturnTypes() {
        return supportedImageReturnTypes;
    }

    /**
     * Sets the value of the supportedImageReturnTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriImageReturnType }
     *     
     */
    public void setSupportedImageReturnTypes(EsriImageReturnType value) {
        this.supportedImageReturnTypes = value;
    }

    /**
     * Gets the value of the backgroundSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link FillSymbol }
     *     
     */
    public FillSymbol getBackgroundSymbol() {
        return backgroundSymbol;
    }

    /**
     * Sets the value of the backgroundSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillSymbol }
     *     
     */
    public void setBackgroundSymbol(FillSymbol value) {
        this.backgroundSymbol = value;
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
     * Gets the value of the standaloneTableInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfStandaloneTableInfo }
     *     
     */
    public ArrayOfStandaloneTableInfo getStandaloneTableInfos() {
        return standaloneTableInfos;
    }

    /**
     * Sets the value of the standaloneTableInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfStandaloneTableInfo }
     *     
     */
    public void setStandaloneTableInfos(ArrayOfStandaloneTableInfo value) {
        this.standaloneTableInfos = value;
    }

    /**
     * Gets the value of the standaloneTableDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfStandaloneTableDescription }
     *     
     */
    public ArrayOfStandaloneTableDescription getStandaloneTableDescriptions() {
        return standaloneTableDescriptions;
    }

    /**
     * Sets the value of the standaloneTableDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfStandaloneTableDescription }
     *     
     */
    public void setStandaloneTableDescriptions(ArrayOfStandaloneTableDescription value) {
        this.standaloneTableDescriptions = value;
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
     * Gets the value of the defaultTimeStepInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDefaultTimeStepInterval() {
        return defaultTimeStepInterval;
    }

    /**
     * Sets the value of the defaultTimeStepInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDefaultTimeStepInterval(Double value) {
        this.defaultTimeStepInterval = value;
    }

    /**
     * Gets the value of the defaultTimeStepIntervalUnits property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTimeUnits }
     *     
     */
    public EsriTimeUnits getDefaultTimeStepIntervalUnits() {
        return defaultTimeStepIntervalUnits;
    }

    /**
     * Sets the value of the defaultTimeStepIntervalUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTimeUnits }
     *     
     */
    public void setDefaultTimeStepIntervalUnits(EsriTimeUnits value) {
        this.defaultTimeStepIntervalUnits = value;
    }

    /**
     * Gets the value of the defaultTimeWindow property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDefaultTimeWindow() {
        return defaultTimeWindow;
    }

    /**
     * Sets the value of the defaultTimeWindow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDefaultTimeWindow(Double value) {
        this.defaultTimeWindow = value;
    }

    /**
     * Gets the value of the supportsDynamicLayers property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportsDynamicLayers() {
        return supportsDynamicLayers;
    }

    /**
     * Sets the value of the supportsDynamicLayers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportsDynamicLayers(Boolean value) {
        this.supportsDynamicLayers = value;
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

    /**
     * Gets the value of the minScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinScale() {
        return minScale;
    }

    /**
     * Sets the value of the minScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinScale(Double value) {
        this.minScale = value;
    }

    /**
     * Gets the value of the maxScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxScale() {
        return maxScale;
    }

    /**
     * Sets the value of the maxScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxScale(Double value) {
        this.maxScale = value;
    }

}


package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterClassifyRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterClassifyRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}RasterRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ClassField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NormField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ClassificationComponent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Guid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorSchema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LegendGroupsCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LegendGroups" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfLegendGroup" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BreakSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ArrayOfBreak" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfDouble" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Ascending" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NumberFormat" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}NumericFormat" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ShowClassGaps" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DeviationInterval" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ExlusionValues" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ExclusionRanges" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ExclusionShowClass" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ExclusionLegendClass" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}LegendClass" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UniqueValues" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}RasterUniqueValues" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseHillShader" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterClassifyRenderer", propOrder = {
    "classField",
    "normField",
    "classificationComponent",
    "guid",
    "colorSchema",
    "legendGroupsCount",
    "legendGroups",
    "breakSize",
    "arrayOfBreak",
    "ascending",
    "numberFormat",
    "showClassGaps",
    "deviationInterval",
    "exlusionValues",
    "exclusionRanges",
    "exclusionShowClass",
    "exclusionLegendClass",
    "uniqueValues",
    "useHillShader",
    "zScale"
})
public class RasterClassifyRenderer
    extends RasterRenderer
{

    @XmlElement(name = "ClassField")
    protected String classField;
    @XmlElement(name = "NormField")
    protected String normField;
    @XmlElement(name = "ClassificationComponent")
    protected Boolean classificationComponent;
    @XmlElement(name = "Guid")
    protected String guid;
    @XmlElement(name = "ColorSchema")
    protected String colorSchema;
    @XmlElement(name = "LegendGroupsCount")
    protected Integer legendGroupsCount;
    @XmlElement(name = "LegendGroups")
    protected ArrayOfLegendGroup legendGroups;
    @XmlElement(name = "BreakSize")
    protected Integer breakSize;
    @XmlElement(name = "ArrayOfBreak")
    protected ArrayOfDouble arrayOfBreak;
    @XmlElement(name = "Ascending")
    protected Boolean ascending;
    @XmlElement(name = "NumberFormat")
    protected NumericFormat numberFormat;
    @XmlElement(name = "ShowClassGaps")
    protected Boolean showClassGaps;
    @XmlElement(name = "DeviationInterval")
    protected Double deviationInterval;
    @XmlElement(name = "ExlusionValues")
    protected Object exlusionValues;
    @XmlElement(name = "ExclusionRanges")
    protected Object exclusionRanges;
    @XmlElement(name = "ExclusionShowClass")
    protected Boolean exclusionShowClass;
    @XmlElement(name = "ExclusionLegendClass")
    protected LegendClass exclusionLegendClass;
    @XmlElement(name = "UniqueValues")
    protected RasterUniqueValues uniqueValues;
    @XmlElement(name = "UseHillShader")
    protected Boolean useHillShader;
    @XmlElement(name = "ZScale")
    protected Double zScale;

    /**
     * Gets the value of the classField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassField() {
        return classField;
    }

    /**
     * Sets the value of the classField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassField(String value) {
        this.classField = value;
    }

    /**
     * Gets the value of the normField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormField() {
        return normField;
    }

    /**
     * Sets the value of the normField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormField(String value) {
        this.normField = value;
    }

    /**
     * Gets the value of the classificationComponent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClassificationComponent() {
        return classificationComponent;
    }

    /**
     * Sets the value of the classificationComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClassificationComponent(Boolean value) {
        this.classificationComponent = value;
    }

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the colorSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColorSchema() {
        return colorSchema;
    }

    /**
     * Sets the value of the colorSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColorSchema(String value) {
        this.colorSchema = value;
    }

    /**
     * Gets the value of the legendGroupsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLegendGroupsCount() {
        return legendGroupsCount;
    }

    /**
     * Sets the value of the legendGroupsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLegendGroupsCount(Integer value) {
        this.legendGroupsCount = value;
    }

    /**
     * Gets the value of the legendGroups property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLegendGroup }
     *     
     */
    public ArrayOfLegendGroup getLegendGroups() {
        return legendGroups;
    }

    /**
     * Sets the value of the legendGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLegendGroup }
     *     
     */
    public void setLegendGroups(ArrayOfLegendGroup value) {
        this.legendGroups = value;
    }

    /**
     * Gets the value of the breakSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBreakSize() {
        return breakSize;
    }

    /**
     * Sets the value of the breakSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBreakSize(Integer value) {
        this.breakSize = value;
    }

    /**
     * Gets the value of the arrayOfBreak property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDouble }
     *     
     */
    public ArrayOfDouble getArrayOfBreak() {
        return arrayOfBreak;
    }

    /**
     * Sets the value of the arrayOfBreak property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDouble }
     *     
     */
    public void setArrayOfBreak(ArrayOfDouble value) {
        this.arrayOfBreak = value;
    }

    /**
     * Gets the value of the ascending property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAscending() {
        return ascending;
    }

    /**
     * Sets the value of the ascending property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAscending(Boolean value) {
        this.ascending = value;
    }

    /**
     * Gets the value of the numberFormat property.
     * 
     * @return
     *     possible object is
     *     {@link NumericFormat }
     *     
     */
    public NumericFormat getNumberFormat() {
        return numberFormat;
    }

    /**
     * Sets the value of the numberFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumericFormat }
     *     
     */
    public void setNumberFormat(NumericFormat value) {
        this.numberFormat = value;
    }

    /**
     * Gets the value of the showClassGaps property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowClassGaps() {
        return showClassGaps;
    }

    /**
     * Sets the value of the showClassGaps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowClassGaps(Boolean value) {
        this.showClassGaps = value;
    }

    /**
     * Gets the value of the deviationInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDeviationInterval() {
        return deviationInterval;
    }

    /**
     * Sets the value of the deviationInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDeviationInterval(Double value) {
        this.deviationInterval = value;
    }

    /**
     * Gets the value of the exlusionValues property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getExlusionValues() {
        return exlusionValues;
    }

    /**
     * Sets the value of the exlusionValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setExlusionValues(Object value) {
        this.exlusionValues = value;
    }

    /**
     * Gets the value of the exclusionRanges property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getExclusionRanges() {
        return exclusionRanges;
    }

    /**
     * Sets the value of the exclusionRanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setExclusionRanges(Object value) {
        this.exclusionRanges = value;
    }

    /**
     * Gets the value of the exclusionShowClass property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExclusionShowClass() {
        return exclusionShowClass;
    }

    /**
     * Sets the value of the exclusionShowClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExclusionShowClass(Boolean value) {
        this.exclusionShowClass = value;
    }

    /**
     * Gets the value of the exclusionLegendClass property.
     * 
     * @return
     *     possible object is
     *     {@link LegendClass }
     *     
     */
    public LegendClass getExclusionLegendClass() {
        return exclusionLegendClass;
    }

    /**
     * Sets the value of the exclusionLegendClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegendClass }
     *     
     */
    public void setExclusionLegendClass(LegendClass value) {
        this.exclusionLegendClass = value;
    }

    /**
     * Gets the value of the uniqueValues property.
     * 
     * @return
     *     possible object is
     *     {@link RasterUniqueValues }
     *     
     */
    public RasterUniqueValues getUniqueValues() {
        return uniqueValues;
    }

    /**
     * Sets the value of the uniqueValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link RasterUniqueValues }
     *     
     */
    public void setUniqueValues(RasterUniqueValues value) {
        this.uniqueValues = value;
    }

    /**
     * Gets the value of the useHillShader property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseHillShader() {
        return useHillShader;
    }

    /**
     * Sets the value of the useHillShader property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseHillShader(Boolean value) {
        this.useHillShader = value;
    }

    /**
     * Gets the value of the zScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZScale() {
        return zScale;
    }

    /**
     * Sets the value of the zScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZScale(Double value) {
        this.zScale = value;
    }

}

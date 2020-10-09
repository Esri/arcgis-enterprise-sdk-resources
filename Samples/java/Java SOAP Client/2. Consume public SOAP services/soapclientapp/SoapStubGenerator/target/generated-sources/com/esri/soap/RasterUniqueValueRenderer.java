
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterUniqueValueRenderer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterUniqueValueRenderer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}RasterRenderer"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ValueField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ClassField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorSchema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseDefaultSymbol" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DefaultLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LegendGroupsCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LegendGroups" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfLegendGroup" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ClassValuesCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ClassesInLegend" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ClassesInLegendSize" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UniqueValueVariants" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfValue" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Global" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UniqueValues" type="{http://www.esri.com/schemas/ArcGIS/10.7}RasterUniqueValues" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorRamp" type="{http://www.esri.com/schemas/ArcGIS/10.7}ColorRamp" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterUniqueValueRenderer", propOrder = {
    "valueField",
    "classField",
    "colorSchema",
    "useDefaultSymbol",
    "defaultSymbol",
    "defaultLabel",
    "legendGroupsCount",
    "legendGroups",
    "classValuesCount",
    "classesInLegend",
    "classesInLegendSize",
    "uniqueValueVariants",
    "global",
    "uniqueValues",
    "colorRamp"
})
public class RasterUniqueValueRenderer
    extends RasterRenderer
{

    @XmlElement(name = "ValueField")
    protected String valueField;
    @XmlElement(name = "ClassField")
    protected String classField;
    @XmlElement(name = "ColorSchema")
    protected String colorSchema;
    @XmlElement(name = "UseDefaultSymbol")
    protected Boolean useDefaultSymbol;
    @XmlElement(name = "DefaultSymbol")
    protected Symbol defaultSymbol;
    @XmlElement(name = "DefaultLabel")
    protected String defaultLabel;
    @XmlElement(name = "LegendGroupsCount")
    protected Integer legendGroupsCount;
    @XmlElement(name = "LegendGroups")
    protected ArrayOfLegendGroup legendGroups;
    @XmlElement(name = "ClassValuesCount")
    protected Integer classValuesCount;
    @XmlElement(name = "ClassesInLegend")
    protected ArrayOfInt classesInLegend;
    @XmlElement(name = "ClassesInLegendSize")
    protected ArrayOfInt classesInLegendSize;
    @XmlElement(name = "UniqueValueVariants")
    protected ArrayOfValue uniqueValueVariants;
    @XmlElement(name = "Global")
    protected Boolean global;
    @XmlElement(name = "UniqueValues")
    protected RasterUniqueValues uniqueValues;
    @XmlElement(name = "ColorRamp")
    protected ColorRamp colorRamp;

    /**
     * Gets the value of the valueField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueField() {
        return valueField;
    }

    /**
     * Sets the value of the valueField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueField(String value) {
        this.valueField = value;
    }

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
     * Gets the value of the useDefaultSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseDefaultSymbol() {
        return useDefaultSymbol;
    }

    /**
     * Sets the value of the useDefaultSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseDefaultSymbol(Boolean value) {
        this.useDefaultSymbol = value;
    }

    /**
     * Gets the value of the defaultSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getDefaultSymbol() {
        return defaultSymbol;
    }

    /**
     * Sets the value of the defaultSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setDefaultSymbol(Symbol value) {
        this.defaultSymbol = value;
    }

    /**
     * Gets the value of the defaultLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultLabel() {
        return defaultLabel;
    }

    /**
     * Sets the value of the defaultLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultLabel(String value) {
        this.defaultLabel = value;
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
     * Gets the value of the classValuesCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClassValuesCount() {
        return classValuesCount;
    }

    /**
     * Sets the value of the classValuesCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClassValuesCount(Integer value) {
        this.classValuesCount = value;
    }

    /**
     * Gets the value of the classesInLegend property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getClassesInLegend() {
        return classesInLegend;
    }

    /**
     * Sets the value of the classesInLegend property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setClassesInLegend(ArrayOfInt value) {
        this.classesInLegend = value;
    }

    /**
     * Gets the value of the classesInLegendSize property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getClassesInLegendSize() {
        return classesInLegendSize;
    }

    /**
     * Sets the value of the classesInLegendSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setClassesInLegendSize(ArrayOfInt value) {
        this.classesInLegendSize = value;
    }

    /**
     * Gets the value of the uniqueValueVariants property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfValue }
     *     
     */
    public ArrayOfValue getUniqueValueVariants() {
        return uniqueValueVariants;
    }

    /**
     * Sets the value of the uniqueValueVariants property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfValue }
     *     
     */
    public void setUniqueValueVariants(ArrayOfValue value) {
        this.uniqueValueVariants = value;
    }

    /**
     * Gets the value of the global property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGlobal() {
        return global;
    }

    /**
     * Sets the value of the global property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGlobal(Boolean value) {
        this.global = value;
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
     * Gets the value of the colorRamp property.
     * 
     * @return
     *     possible object is
     *     {@link ColorRamp }
     *     
     */
    public ColorRamp getColorRamp() {
        return colorRamp;
    }

    /**
     * Sets the value of the colorRamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorRamp }
     *     
     */
    public void setColorRamp(ColorRamp value) {
        this.colorRamp = value;
    }

}

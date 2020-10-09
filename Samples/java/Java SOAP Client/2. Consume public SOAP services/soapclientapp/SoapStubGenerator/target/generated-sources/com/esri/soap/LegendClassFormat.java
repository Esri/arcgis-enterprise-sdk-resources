
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LegendClassFormat complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LegendClassFormat"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LabelSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DescriptionSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LinePatch" type="{http://www.esri.com/schemas/ArcGIS/10.7}LinePatch" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AreaPatch" type="{http://www.esri.com/schemas/ArcGIS/10.7}AreaPatch" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PatchWidth" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="PatchHeight" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegendClassFormat", propOrder = {
    "labelSymbol",
    "descriptionSymbol",
    "linePatch",
    "areaPatch",
    "patchWidth",
    "patchHeight"
})
public class LegendClassFormat {

    @XmlElement(name = "LabelSymbol")
    protected Symbol labelSymbol;
    @XmlElement(name = "DescriptionSymbol")
    protected Symbol descriptionSymbol;
    @XmlElement(name = "LinePatch")
    protected LinePatch linePatch;
    @XmlElement(name = "AreaPatch")
    protected AreaPatch areaPatch;
    @XmlElement(name = "PatchWidth")
    protected double patchWidth;
    @XmlElement(name = "PatchHeight")
    protected double patchHeight;

    /**
     * Gets the value of the labelSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getLabelSymbol() {
        return labelSymbol;
    }

    /**
     * Sets the value of the labelSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setLabelSymbol(Symbol value) {
        this.labelSymbol = value;
    }

    /**
     * Gets the value of the descriptionSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getDescriptionSymbol() {
        return descriptionSymbol;
    }

    /**
     * Sets the value of the descriptionSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setDescriptionSymbol(Symbol value) {
        this.descriptionSymbol = value;
    }

    /**
     * Gets the value of the linePatch property.
     * 
     * @return
     *     possible object is
     *     {@link LinePatch }
     *     
     */
    public LinePatch getLinePatch() {
        return linePatch;
    }

    /**
     * Sets the value of the linePatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinePatch }
     *     
     */
    public void setLinePatch(LinePatch value) {
        this.linePatch = value;
    }

    /**
     * Gets the value of the areaPatch property.
     * 
     * @return
     *     possible object is
     *     {@link AreaPatch }
     *     
     */
    public AreaPatch getAreaPatch() {
        return areaPatch;
    }

    /**
     * Sets the value of the areaPatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaPatch }
     *     
     */
    public void setAreaPatch(AreaPatch value) {
        this.areaPatch = value;
    }

    /**
     * Gets the value of the patchWidth property.
     * 
     */
    public double getPatchWidth() {
        return patchWidth;
    }

    /**
     * Sets the value of the patchWidth property.
     * 
     */
    public void setPatchWidth(double value) {
        this.patchWidth = value;
    }

    /**
     * Gets the value of the patchHeight property.
     * 
     */
    public double getPatchHeight() {
        return patchHeight;
    }

    /**
     * Sets the value of the patchHeight property.
     * 
     */
    public void setPatchHeight(double value) {
        this.patchHeight = value;
    }

}

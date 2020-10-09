
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for AlternatingScaleBar complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AlternatingScaleBar"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}ScaleBar"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="FillSymbol1" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}FillSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FillSymbol2" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}FillSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DivisionMarkSymbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}LineSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SubdivisionMarkSymbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}LineSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DivisionMarkHeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SubdivisionMarkHeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MarkPosition" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriVertPosEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MarkFrequency" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriScaleBarFrequency" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlternatingScaleBar", propOrder = {
    "fillSymbol1",
    "fillSymbol2",
    "divisionMarkSymbol",
    "subdivisionMarkSymbol",
    "divisionMarkHeight",
    "subdivisionMarkHeight",
    "markPosition",
    "markFrequency"
})
public class AlternatingScaleBar
    extends ScaleBar
{

    @XmlElement(name = "FillSymbol1")
    protected FillSymbol fillSymbol1;
    @XmlElement(name = "FillSymbol2")
    protected FillSymbol fillSymbol2;
    @XmlElement(name = "DivisionMarkSymbol")
    protected LineSymbol divisionMarkSymbol;
    @XmlElement(name = "SubdivisionMarkSymbol")
    protected LineSymbol subdivisionMarkSymbol;
    @XmlElement(name = "DivisionMarkHeight")
    protected Double divisionMarkHeight;
    @XmlElement(name = "SubdivisionMarkHeight")
    protected Double subdivisionMarkHeight;
    @XmlElement(name = "MarkPosition")
    @XmlSchemaType(name = "string")
    protected EsriVertPosEnum markPosition;
    @XmlElement(name = "MarkFrequency")
    @XmlSchemaType(name = "string")
    protected EsriScaleBarFrequency markFrequency;

    /**
     * Gets the value of the fillSymbol1 property.
     * 
     * @return
     *     possible object is
     *     {@link FillSymbol }
     *     
     */
    public FillSymbol getFillSymbol1() {
        return fillSymbol1;
    }

    /**
     * Sets the value of the fillSymbol1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillSymbol }
     *     
     */
    public void setFillSymbol1(FillSymbol value) {
        this.fillSymbol1 = value;
    }

    /**
     * Gets the value of the fillSymbol2 property.
     * 
     * @return
     *     possible object is
     *     {@link FillSymbol }
     *     
     */
    public FillSymbol getFillSymbol2() {
        return fillSymbol2;
    }

    /**
     * Sets the value of the fillSymbol2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillSymbol }
     *     
     */
    public void setFillSymbol2(FillSymbol value) {
        this.fillSymbol2 = value;
    }

    /**
     * Gets the value of the divisionMarkSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link LineSymbol }
     *     
     */
    public LineSymbol getDivisionMarkSymbol() {
        return divisionMarkSymbol;
    }

    /**
     * Sets the value of the divisionMarkSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineSymbol }
     *     
     */
    public void setDivisionMarkSymbol(LineSymbol value) {
        this.divisionMarkSymbol = value;
    }

    /**
     * Gets the value of the subdivisionMarkSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link LineSymbol }
     *     
     */
    public LineSymbol getSubdivisionMarkSymbol() {
        return subdivisionMarkSymbol;
    }

    /**
     * Sets the value of the subdivisionMarkSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineSymbol }
     *     
     */
    public void setSubdivisionMarkSymbol(LineSymbol value) {
        this.subdivisionMarkSymbol = value;
    }

    /**
     * Gets the value of the divisionMarkHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDivisionMarkHeight() {
        return divisionMarkHeight;
    }

    /**
     * Sets the value of the divisionMarkHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDivisionMarkHeight(Double value) {
        this.divisionMarkHeight = value;
    }

    /**
     * Gets the value of the subdivisionMarkHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSubdivisionMarkHeight() {
        return subdivisionMarkHeight;
    }

    /**
     * Sets the value of the subdivisionMarkHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSubdivisionMarkHeight(Double value) {
        this.subdivisionMarkHeight = value;
    }

    /**
     * Gets the value of the markPosition property.
     * 
     * @return
     *     possible object is
     *     {@link EsriVertPosEnum }
     *     
     */
    public EsriVertPosEnum getMarkPosition() {
        return markPosition;
    }

    /**
     * Sets the value of the markPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriVertPosEnum }
     *     
     */
    public void setMarkPosition(EsriVertPosEnum value) {
        this.markPosition = value;
    }

    /**
     * Gets the value of the markFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link EsriScaleBarFrequency }
     *     
     */
    public EsriScaleBarFrequency getMarkFrequency() {
        return markFrequency;
    }

    /**
     * Sets the value of the markFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriScaleBarFrequency }
     *     
     */
    public void setMarkFrequency(EsriScaleBarFrequency value) {
        this.markFrequency = value;
    }

}

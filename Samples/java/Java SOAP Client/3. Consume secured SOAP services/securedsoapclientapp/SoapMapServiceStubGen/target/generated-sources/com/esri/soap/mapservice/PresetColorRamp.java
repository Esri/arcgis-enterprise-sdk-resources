
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PresetColorRamp complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PresetColorRamp"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}ColorRamp"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="NumColors" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PresetSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Colors" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfColor" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PresetColorRamp", propOrder = {
    "numColors",
    "presetSize",
    "colors"
})
public class PresetColorRamp
    extends ColorRamp
{

    @XmlElement(name = "NumColors")
    protected Integer numColors;
    @XmlElement(name = "PresetSize")
    protected Integer presetSize;
    @XmlElement(name = "Colors")
    protected ArrayOfColor colors;

    /**
     * Gets the value of the numColors property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumColors() {
        return numColors;
    }

    /**
     * Sets the value of the numColors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumColors(Integer value) {
        this.numColors = value;
    }

    /**
     * Gets the value of the presetSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPresetSize() {
        return presetSize;
    }

    /**
     * Sets the value of the presetSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPresetSize(Integer value) {
        this.presetSize = value;
    }

    /**
     * Gets the value of the colors property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfColor }
     *     
     */
    public ArrayOfColor getColors() {
        return colors;
    }

    /**
     * Sets the value of the colors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfColor }
     *     
     */
    public void setColors(ArrayOfColor value) {
        this.colors = value;
    }

}

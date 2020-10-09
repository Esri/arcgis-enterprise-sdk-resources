
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RandomColorRamp complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RandomColorRamp"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}ColorRamp"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="NumColors" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseSeed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Seed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MinValue" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaxValue" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MinSaturation" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaxSaturation" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StartHue" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EndHue" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RandomColorRamp", propOrder = {
    "numColors",
    "useSeed",
    "seed",
    "minValue",
    "maxValue",
    "minSaturation",
    "maxSaturation",
    "startHue",
    "endHue"
})
public class RandomColorRamp
    extends ColorRamp
{

    @XmlElement(name = "NumColors")
    protected Integer numColors;
    @XmlElement(name = "UseSeed")
    protected Boolean useSeed;
    @XmlElement(name = "Seed")
    protected Integer seed;
    @XmlElement(name = "MinValue")
    protected Short minValue;
    @XmlElement(name = "MaxValue")
    protected Short maxValue;
    @XmlElement(name = "MinSaturation")
    protected Short minSaturation;
    @XmlElement(name = "MaxSaturation")
    protected Short maxSaturation;
    @XmlElement(name = "StartHue")
    protected Short startHue;
    @XmlElement(name = "EndHue")
    protected Short endHue;

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
     * Gets the value of the useSeed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseSeed() {
        return useSeed;
    }

    /**
     * Sets the value of the useSeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseSeed(Boolean value) {
        this.useSeed = value;
    }

    /**
     * Gets the value of the seed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeed() {
        return seed;
    }

    /**
     * Sets the value of the seed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeed(Integer value) {
        this.seed = value;
    }

    /**
     * Gets the value of the minValue property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMinValue(Short value) {
        this.minValue = value;
    }

    /**
     * Gets the value of the maxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMaxValue(Short value) {
        this.maxValue = value;
    }

    /**
     * Gets the value of the minSaturation property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMinSaturation() {
        return minSaturation;
    }

    /**
     * Sets the value of the minSaturation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMinSaturation(Short value) {
        this.minSaturation = value;
    }

    /**
     * Gets the value of the maxSaturation property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMaxSaturation() {
        return maxSaturation;
    }

    /**
     * Sets the value of the maxSaturation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMaxSaturation(Short value) {
        this.maxSaturation = value;
    }

    /**
     * Gets the value of the startHue property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getStartHue() {
        return startHue;
    }

    /**
     * Sets the value of the startHue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setStartHue(Short value) {
        this.startHue = value;
    }

    /**
     * Gets the value of the endHue property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getEndHue() {
        return endHue;
    }

    /**
     * Sets the value of the endHue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setEndHue(Short value) {
        this.endHue = value;
    }

}

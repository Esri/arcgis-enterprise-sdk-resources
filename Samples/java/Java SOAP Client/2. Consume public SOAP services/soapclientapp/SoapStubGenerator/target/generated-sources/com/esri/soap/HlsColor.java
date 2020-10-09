
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for HlsColor complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="HlsColor"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Color"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Hue" type="{http://www.w3.org/2001/XMLSchema}short"/&amp;gt;
 *         &amp;lt;element name="Lightness" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&amp;gt;
 *         &amp;lt;element name="Saturation" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HlsColor", propOrder = {
    "hue",
    "lightness",
    "saturation"
})
public class HlsColor
    extends Color
{

    @XmlElement(name = "Hue")
    protected short hue;
    @XmlElement(name = "Lightness")
    @XmlSchemaType(name = "unsignedByte")
    protected short lightness;
    @XmlElement(name = "Saturation")
    @XmlSchemaType(name = "unsignedByte")
    protected short saturation;

    /**
     * Gets the value of the hue property.
     * 
     */
    public short getHue() {
        return hue;
    }

    /**
     * Sets the value of the hue property.
     * 
     */
    public void setHue(short value) {
        this.hue = value;
    }

    /**
     * Gets the value of the lightness property.
     * 
     */
    public short getLightness() {
        return lightness;
    }

    /**
     * Sets the value of the lightness property.
     * 
     */
    public void setLightness(short value) {
        this.lightness = value;
    }

    /**
     * Gets the value of the saturation property.
     * 
     */
    public short getSaturation() {
        return saturation;
    }

    /**
     * Sets the value of the saturation property.
     * 
     */
    public void setSaturation(short value) {
        this.saturation = value;
    }

}

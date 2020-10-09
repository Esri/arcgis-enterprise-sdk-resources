
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RgbColor complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RgbColor"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Red" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&amp;gt;
 *         &amp;lt;element name="Green" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&amp;gt;
 *         &amp;lt;element name="Blue" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RgbColor", propOrder = {
    "red",
    "green",
    "blue"
})
public class RgbColor
    extends Color
{

    @XmlElement(name = "Red")
    @XmlSchemaType(name = "unsignedByte")
    protected short red;
    @XmlElement(name = "Green")
    @XmlSchemaType(name = "unsignedByte")
    protected short green;
    @XmlElement(name = "Blue")
    @XmlSchemaType(name = "unsignedByte")
    protected short blue;

    /**
     * Gets the value of the red property.
     * 
     */
    public short getRed() {
        return red;
    }

    /**
     * Sets the value of the red property.
     * 
     */
    public void setRed(short value) {
        this.red = value;
    }

    /**
     * Gets the value of the green property.
     * 
     */
    public short getGreen() {
        return green;
    }

    /**
     * Sets the value of the green property.
     * 
     */
    public void setGreen(short value) {
        this.green = value;
    }

    /**
     * Gets the value of the blue property.
     * 
     */
    public short getBlue() {
        return blue;
    }

    /**
     * Sets the value of the blue property.
     * 
     */
    public void setBlue(short value) {
        this.blue = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for Color complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Color"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="UseWindowsDithering" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AlphaValue" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Color", propOrder = {
    "useWindowsDithering",
    "alphaValue"
})
@XmlSeeAlso({
    GrayColor.class,
    RgbColor.class,
    CmykColor.class,
    HlsColor.class,
    HsvColor.class
})
public abstract class Color {

    @XmlElement(name = "UseWindowsDithering")
    protected Boolean useWindowsDithering;
    @XmlElement(name = "AlphaValue")
    @XmlSchemaType(name = "unsignedByte")
    protected Short alphaValue;

    /**
     * Gets the value of the useWindowsDithering property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseWindowsDithering() {
        return useWindowsDithering;
    }

    /**
     * Sets the value of the useWindowsDithering property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseWindowsDithering(Boolean value) {
        this.useWindowsDithering = value;
    }

    /**
     * Gets the value of the alphaValue property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getAlphaValue() {
        return alphaValue;
    }

    /**
     * Sets the value of the alphaValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAlphaValue(Short value) {
        this.alphaValue = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Fill Sybmol Properties.
 *       
 * 
 * &lt;p&gt;Java class for FillSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FillSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Color" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Outline" type="{http://www.esri.com/schemas/ArcGIS/10.7}LineSymbol" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FillSymbol", propOrder = {
    "color",
    "outline"
})
@XmlSeeAlso({
    SimpleFillSymbol.class,
    PictureFillSymbol.class,
    XMLBinaryFillSymbol.class
})
public abstract class FillSymbol
    extends Symbol
{

    @XmlElement(name = "Color")
    protected Color color;
    @XmlElement(name = "Outline")
    protected LineSymbol outline;

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setColor(Color value) {
        this.color = value;
    }

    /**
     * Gets the value of the outline property.
     * 
     * @return
     *     possible object is
     *     {@link LineSymbol }
     *     
     */
    public LineSymbol getOutline() {
        return outline;
    }

    /**
     * Sets the value of the outline property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineSymbol }
     *     
     */
    public void setOutline(LineSymbol value) {
        this.outline = value;
    }

}

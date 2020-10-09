
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Cartographic marker symbol properties.
 *       
 * 
 * &lt;p&gt;Java class for CartographicMarkerSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CartographicMarkerSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}MarkerSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="XScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="YScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CartographicMarkerSymbol", propOrder = {
    "xScale",
    "yScale"
})
@XmlSeeAlso({
    CharacterMarkerSymbol.class,
    PictureMarkerSymbol.class
})
public abstract class CartographicMarkerSymbol
    extends MarkerSymbol
{

    @XmlElement(name = "XScale", defaultValue = "1.0")
    protected double xScale;
    @XmlElement(name = "YScale", defaultValue = "1.0")
    protected double yScale;

    /**
     * Gets the value of the xScale property.
     * 
     */
    public double getXScale() {
        return xScale;
    }

    /**
     * Sets the value of the xScale property.
     * 
     */
    public void setXScale(double value) {
        this.xScale = value;
    }

    /**
     * Gets the value of the yScale property.
     * 
     */
    public double getYScale() {
        return yScale;
    }

    /**
     * Sets the value of the yScale property.
     * 
     */
    public void setYScale(double value) {
        this.yScale = value;
    }

}

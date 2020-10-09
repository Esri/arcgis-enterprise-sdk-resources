
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SymbolShadow complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SymbolShadow"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Shadow"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}LineSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CornerRounding" type="{http://www.w3.org/2001/XMLSchema}short"/&amp;gt;
 *         &amp;lt;element name="HorizontalOffset" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="VerticalOffset" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SymbolShadow", propOrder = {
    "symbol",
    "cornerRounding",
    "horizontalOffset",
    "verticalOffset"
})
public class SymbolShadow
    extends Shadow
{

    @XmlElement(name = "Symbol")
    protected LineSymbol symbol;
    @XmlElement(name = "CornerRounding")
    protected short cornerRounding;
    @XmlElement(name = "HorizontalOffset")
    protected double horizontalOffset;
    @XmlElement(name = "VerticalOffset")
    protected double verticalOffset;

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link LineSymbol }
     *     
     */
    public LineSymbol getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineSymbol }
     *     
     */
    public void setSymbol(LineSymbol value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the cornerRounding property.
     * 
     */
    public short getCornerRounding() {
        return cornerRounding;
    }

    /**
     * Sets the value of the cornerRounding property.
     * 
     */
    public void setCornerRounding(short value) {
        this.cornerRounding = value;
    }

    /**
     * Gets the value of the horizontalOffset property.
     * 
     */
    public double getHorizontalOffset() {
        return horizontalOffset;
    }

    /**
     * Sets the value of the horizontalOffset property.
     * 
     */
    public void setHorizontalOffset(double value) {
        this.horizontalOffset = value;
    }

    /**
     * Gets the value of the verticalOffset property.
     * 
     */
    public double getVerticalOffset() {
        return verticalOffset;
    }

    /**
     * Sets the value of the verticalOffset property.
     * 
     */
    public void setVerticalOffset(double value) {
        this.verticalOffset = value;
    }

}

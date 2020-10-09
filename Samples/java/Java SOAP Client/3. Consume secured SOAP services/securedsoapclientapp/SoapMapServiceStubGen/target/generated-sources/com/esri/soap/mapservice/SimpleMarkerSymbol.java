
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SimpleMarkerSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SimpleMarkerSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}MarkerSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Outline" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="OutlineSize" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="OutlineColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color"/&amp;gt;
 *         &amp;lt;element name="Style" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSimpleMarkerStyle"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleMarkerSymbol", propOrder = {
    "outline",
    "outlineSize",
    "outlineColor",
    "style"
})
public class SimpleMarkerSymbol
    extends MarkerSymbol
{

    @XmlElement(name = "Outline")
    protected boolean outline;
    @XmlElement(name = "OutlineSize")
    protected double outlineSize;
    @XmlElement(name = "OutlineColor", required = true)
    protected Color outlineColor;
    @XmlElement(name = "Style", required = true)
    @XmlSchemaType(name = "string")
    protected EsriSimpleMarkerStyle style;

    /**
     * Gets the value of the outline property.
     * 
     */
    public boolean isOutline() {
        return outline;
    }

    /**
     * Sets the value of the outline property.
     * 
     */
    public void setOutline(boolean value) {
        this.outline = value;
    }

    /**
     * Gets the value of the outlineSize property.
     * 
     */
    public double getOutlineSize() {
        return outlineSize;
    }

    /**
     * Sets the value of the outlineSize property.
     * 
     */
    public void setOutlineSize(double value) {
        this.outlineSize = value;
    }

    /**
     * Gets the value of the outlineColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getOutlineColor() {
        return outlineColor;
    }

    /**
     * Sets the value of the outlineColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setOutlineColor(Color value) {
        this.outlineColor = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSimpleMarkerStyle }
     *     
     */
    public EsriSimpleMarkerStyle getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSimpleMarkerStyle }
     *     
     */
    public void setStyle(EsriSimpleMarkerStyle value) {
        this.style = value;
    }

}

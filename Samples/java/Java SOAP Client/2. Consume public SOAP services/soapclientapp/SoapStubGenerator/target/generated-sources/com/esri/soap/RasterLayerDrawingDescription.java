
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterLayerDrawingDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterLayerDrawingDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}LayerDrawingDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RasterRenderer" type="{http://www.esri.com/schemas/ArcGIS/10.7}RasterRenderer" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Transparency" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Brightness" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Contrast" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterLayerDrawingDescription", propOrder = {
    "rasterRenderer",
    "transparency",
    "brightness",
    "contrast"
})
public class RasterLayerDrawingDescription
    extends LayerDrawingDescription
{

    @XmlElement(name = "RasterRenderer")
    protected RasterRenderer rasterRenderer;
    @XmlElement(name = "Transparency")
    protected Short transparency;
    @XmlElement(name = "Brightness")
    protected Short brightness;
    @XmlElement(name = "Contrast")
    protected Short contrast;

    /**
     * Gets the value of the rasterRenderer property.
     * 
     * @return
     *     possible object is
     *     {@link RasterRenderer }
     *     
     */
    public RasterRenderer getRasterRenderer() {
        return rasterRenderer;
    }

    /**
     * Sets the value of the rasterRenderer property.
     * 
     * @param value
     *     allowed object is
     *     {@link RasterRenderer }
     *     
     */
    public void setRasterRenderer(RasterRenderer value) {
        this.rasterRenderer = value;
    }

    /**
     * Gets the value of the transparency property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTransparency() {
        return transparency;
    }

    /**
     * Sets the value of the transparency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTransparency(Short value) {
        this.transparency = value;
    }

    /**
     * Gets the value of the brightness property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBrightness() {
        return brightness;
    }

    /**
     * Sets the value of the brightness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBrightness(Short value) {
        this.brightness = value;
    }

    /**
     * Gets the value of the contrast property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getContrast() {
        return contrast;
    }

    /**
     * Sets the value of the contrast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setContrast(Short value) {
        this.contrast = value;
    }

}

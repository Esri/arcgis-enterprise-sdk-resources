
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerLegendClass complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerLegendClass"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Label" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="SymbolImage" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ImageResult" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TransparentColor" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Color" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerLegendClass", propOrder = {
    "label",
    "description",
    "symbolImage",
    "transparentColor"
})
public class MapServerLegendClass {

    @XmlElement(name = "Label", required = true)
    protected String label;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "SymbolImage")
    protected ImageResult symbolImage;
    @XmlElement(name = "TransparentColor")
    protected Color transparentColor;

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the symbolImage property.
     * 
     * @return
     *     possible object is
     *     {@link ImageResult }
     *     
     */
    public ImageResult getSymbolImage() {
        return symbolImage;
    }

    /**
     * Sets the value of the symbolImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageResult }
     *     
     */
    public void setSymbolImage(ImageResult value) {
        this.symbolImage = value;
    }

    /**
     * Gets the value of the transparentColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getTransparentColor() {
        return transparentColor;
    }

    /**
     * Sets the value of the transparentColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setTransparentColor(Color value) {
        this.transparentColor = value;
    }

}

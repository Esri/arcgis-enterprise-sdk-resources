
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MapName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LayerIDs" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfInt"/&amp;gt;
 *         &amp;lt;element name="SymbolOutputOptions" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ServerSymbolOutputOptions"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mapName",
    "layerIDs",
    "symbolOutputOptions"
})
@XmlRootElement(name = "GetDefaultLayerDrawingDescriptions")
public class GetDefaultLayerDrawingDescriptions {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "LayerIDs", required = true)
    protected ArrayOfInt layerIDs;
    @XmlElement(name = "SymbolOutputOptions", required = true)
    protected ServerSymbolOutputOptions symbolOutputOptions;

    /**
     * Gets the value of the mapName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Sets the value of the mapName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapName(String value) {
        this.mapName = value;
    }

    /**
     * Gets the value of the layerIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getLayerIDs() {
        return layerIDs;
    }

    /**
     * Sets the value of the layerIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setLayerIDs(ArrayOfInt value) {
        this.layerIDs = value;
    }

    /**
     * Gets the value of the symbolOutputOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ServerSymbolOutputOptions }
     *     
     */
    public ServerSymbolOutputOptions getSymbolOutputOptions() {
        return symbolOutputOptions;
    }

    /**
     * Sets the value of the symbolOutputOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerSymbolOutputOptions }
     *     
     */
    public void setSymbolOutputOptions(ServerSymbolOutputOptions value) {
        this.symbolOutputOptions = value;
    }

}

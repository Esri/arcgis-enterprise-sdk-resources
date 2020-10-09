
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerLegendGroup complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerLegendGroup"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Heading" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LegendClasses" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfMapServerLegendClass" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerLegendGroup", propOrder = {
    "heading",
    "legendClasses"
})
public class MapServerLegendGroup {

    @XmlElement(name = "Heading", required = true)
    protected String heading;
    @XmlElement(name = "LegendClasses")
    protected ArrayOfMapServerLegendClass legendClasses;

    /**
     * Gets the value of the heading property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Sets the value of the heading property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeading(String value) {
        this.heading = value;
    }

    /**
     * Gets the value of the legendClasses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMapServerLegendClass }
     *     
     */
    public ArrayOfMapServerLegendClass getLegendClasses() {
        return legendClasses;
    }

    /**
     * Sets the value of the legendClasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMapServerLegendClass }
     *     
     */
    public void setLegendClasses(ArrayOfMapServerLegendClass value) {
        this.legendClasses = value;
    }

}

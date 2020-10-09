
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &amp;lt;element name="FromPoint" type="{http://www.esri.com/schemas/ArcGIS/10.7}Point"/&amp;gt;
 *         &amp;lt;element name="ToPoint" type="{http://www.esri.com/schemas/ArcGIS/10.7}Point"/&amp;gt;
 *         &amp;lt;element name="Units" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriUnits"/&amp;gt;
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
    "fromPoint",
    "toPoint",
    "units"
})
@XmlRootElement(name = "ComputeDistance")
public class ComputeDistance_FeatureServer {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "FromPoint", required = true)
    protected Point fromPoint;
    @XmlElement(name = "ToPoint", required = true)
    protected Point toPoint;
    @XmlElement(name = "Units", required = true)
    @XmlSchemaType(name = "string")
    protected EsriUnits units;

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
     * Gets the value of the fromPoint property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getFromPoint() {
        return fromPoint;
    }

    /**
     * Sets the value of the fromPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setFromPoint(Point value) {
        this.fromPoint = value;
    }

    /**
     * Gets the value of the toPoint property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getToPoint() {
        return toPoint;
    }

    /**
     * Sets the value of the toPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setToPoint(Point value) {
        this.toPoint = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link EsriUnits }
     *     
     */
    public EsriUnits getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriUnits }
     *     
     */
    public void setUnits(EsriUnits value) {
        this.units = value;
    }

}

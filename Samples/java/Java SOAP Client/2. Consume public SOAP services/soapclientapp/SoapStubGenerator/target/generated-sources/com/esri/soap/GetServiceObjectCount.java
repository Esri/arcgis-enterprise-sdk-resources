
package com.esri.soap;

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
 *         &amp;lt;element name="LayerOrTableDescriptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfGFSTableDescription"/&amp;gt;
 *         &amp;lt;element name="Geometry" type="{http://www.esri.com/schemas/ArcGIS/10.7}Geometry" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GdbVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "layerOrTableDescriptions",
    "geometry",
    "gdbVersion"
})
@XmlRootElement(name = "GetServiceObjectCount")
public class GetServiceObjectCount {

    @XmlElement(name = "LayerOrTableDescriptions", required = true)
    protected ArrayOfGFSTableDescription layerOrTableDescriptions;
    @XmlElement(name = "Geometry")
    protected Geometry geometry;
    @XmlElement(name = "GdbVersion")
    protected String gdbVersion;

    /**
     * Gets the value of the layerOrTableDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGFSTableDescription }
     *     
     */
    public ArrayOfGFSTableDescription getLayerOrTableDescriptions() {
        return layerOrTableDescriptions;
    }

    /**
     * Sets the value of the layerOrTableDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGFSTableDescription }
     *     
     */
    public void setLayerOrTableDescriptions(ArrayOfGFSTableDescription value) {
        this.layerOrTableDescriptions = value;
    }

    /**
     * Gets the value of the geometry property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Sets the value of the geometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setGeometry(Geometry value) {
        this.geometry = value;
    }

    /**
     * Gets the value of the gdbVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdbVersion() {
        return gdbVersion;
    }

    /**
     * Sets the value of the gdbVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdbVersion(String value) {
        this.gdbVersion = value;
    }

}

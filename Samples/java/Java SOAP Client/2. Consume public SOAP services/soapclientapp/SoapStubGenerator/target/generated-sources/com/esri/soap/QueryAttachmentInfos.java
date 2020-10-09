
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
 *         &amp;lt;element name="LayerOrTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="ObjectIDs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt"/&amp;gt;
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
    "layerOrTableID",
    "objectIDs",
    "gdbVersion"
})
@XmlRootElement(name = "QueryAttachmentInfos")
public class QueryAttachmentInfos {

    @XmlElement(name = "LayerOrTableID")
    protected int layerOrTableID;
    @XmlElement(name = "ObjectIDs", required = true)
    protected ArrayOfInt objectIDs;
    @XmlElement(name = "GdbVersion")
    protected String gdbVersion;

    /**
     * Gets the value of the layerOrTableID property.
     * 
     */
    public int getLayerOrTableID() {
        return layerOrTableID;
    }

    /**
     * Sets the value of the layerOrTableID property.
     * 
     */
    public void setLayerOrTableID(int value) {
        this.layerOrTableID = value;
    }

    /**
     * Gets the value of the objectIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getObjectIDs() {
        return objectIDs;
    }

    /**
     * Sets the value of the objectIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setObjectIDs(ArrayOfInt value) {
        this.objectIDs = value;
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

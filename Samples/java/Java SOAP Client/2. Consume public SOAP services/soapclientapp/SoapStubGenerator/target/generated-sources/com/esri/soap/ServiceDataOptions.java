
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ServiceDataOptions complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ServiceDataOptions"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Etag" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Format" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Properties" type="{http://www.esri.com/schemas/ArcGIS/10.7}PropertySet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TransportType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTransportType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDataOptions", propOrder = {
    "etag",
    "format",
    "properties",
    "transportType"
})
public class ServiceDataOptions {

    @XmlElement(name = "Etag", required = true)
    protected String etag;
    @XmlElement(name = "Format", required = true)
    protected String format;
    @XmlElement(name = "Properties")
    protected PropertySet properties;
    @XmlElement(name = "TransportType")
    @XmlSchemaType(name = "string")
    protected EsriTransportType transportType;

    /**
     * Gets the value of the etag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtag() {
        return etag;
    }

    /**
     * Sets the value of the etag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtag(String value) {
        this.etag = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link PropertySet }
     *     
     */
    public PropertySet getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertySet }
     *     
     */
    public void setProperties(PropertySet value) {
        this.properties = value;
    }

    /**
     * Gets the value of the transportType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTransportType }
     *     
     */
    public EsriTransportType getTransportType() {
        return transportType;
    }

    /**
     * Sets the value of the transportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTransportType }
     *     
     */
    public void setTransportType(EsriTransportType value) {
        this.transportType = value;
    }

}

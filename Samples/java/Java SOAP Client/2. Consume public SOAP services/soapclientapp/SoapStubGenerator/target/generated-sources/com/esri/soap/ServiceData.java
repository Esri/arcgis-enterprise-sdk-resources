
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ServiceData complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ServiceData"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ServiceDataOptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ServiceDataOptions"/&amp;gt;
 *         &amp;lt;element name="EmbeddedData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NotModified" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="Object" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ResponseEtag" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="URI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceData", propOrder = {
    "serviceDataOptions",
    "embeddedData",
    "notModified",
    "object",
    "responseEtag",
    "uri"
})
public class ServiceData {

    @XmlElement(name = "ServiceDataOptions", required = true)
    protected ServiceDataOptions serviceDataOptions;
    @XmlElement(name = "EmbeddedData")
    protected byte[] embeddedData;
    @XmlElement(name = "NotModified")
    protected boolean notModified;
    @XmlElement(name = "Object")
    protected Object object;
    @XmlElement(name = "ResponseEtag", required = true)
    protected String responseEtag;
    @XmlElement(name = "URI")
    protected String uri;

    /**
     * Gets the value of the serviceDataOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDataOptions }
     *     
     */
    public ServiceDataOptions getServiceDataOptions() {
        return serviceDataOptions;
    }

    /**
     * Sets the value of the serviceDataOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDataOptions }
     *     
     */
    public void setServiceDataOptions(ServiceDataOptions value) {
        this.serviceDataOptions = value;
    }

    /**
     * Gets the value of the embeddedData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEmbeddedData() {
        return embeddedData;
    }

    /**
     * Sets the value of the embeddedData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEmbeddedData(byte[] value) {
        this.embeddedData = value;
    }

    /**
     * Gets the value of the notModified property.
     * 
     */
    public boolean isNotModified() {
        return notModified;
    }

    /**
     * Sets the value of the notModified property.
     * 
     */
    public void setNotModified(boolean value) {
        this.notModified = value;
    }

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setObject(Object value) {
        this.object = value;
    }

    /**
     * Gets the value of the responseEtag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseEtag() {
        return responseEtag;
    }

    /**
     * Sets the value of the responseEtag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseEtag(String value) {
        this.responseEtag = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

}

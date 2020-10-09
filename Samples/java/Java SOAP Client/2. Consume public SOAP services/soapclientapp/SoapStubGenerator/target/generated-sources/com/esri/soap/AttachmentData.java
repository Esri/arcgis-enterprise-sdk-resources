
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri AttachmentData Object.
 * 
 * &lt;p&gt;Java class for AttachmentData complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AttachmentData"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&amp;gt;
 *         &amp;lt;element name="AttachmentInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}AttachmentInfo"/&amp;gt;
 *         &amp;lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="TransportType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTransportType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttachmentData", propOrder = {
    "data",
    "attachmentInfo",
    "url",
    "transportType"
})
public class AttachmentData {

    @XmlElement(name = "Data", required = true)
    protected byte[] data;
    @XmlElement(name = "AttachmentInfo", required = true)
    protected AttachmentInfo attachmentInfo;
    @XmlElement(name = "URL", required = true)
    protected String url;
    @XmlElement(name = "TransportType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTransportType transportType;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setData(byte[] value) {
        this.data = value;
    }

    /**
     * Gets the value of the attachmentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AttachmentInfo }
     *     
     */
    public AttachmentInfo getAttachmentInfo() {
        return attachmentInfo;
    }

    /**
     * Sets the value of the attachmentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttachmentInfo }
     *     
     */
    public void setAttachmentInfo(AttachmentInfo value) {
        this.attachmentInfo = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
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

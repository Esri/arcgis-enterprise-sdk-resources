
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Query Result
 * 
 * &lt;p&gt;Java class for QueryResult complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="QueryResult"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MimeData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Object" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryResult", propOrder = {
    "mimeData",
    "url",
    "object"
})
public class QueryResult {

    @XmlElement(name = "MimeData")
    protected byte[] mimeData;
    @XmlElement(name = "URL", required = true)
    protected String url;
    @XmlElement(name = "Object", required = true)
    protected Object object;

    /**
     * Gets the value of the mimeData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMimeData() {
        return mimeData;
    }

    /**
     * Sets the value of the mimeData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMimeData(byte[] value) {
        this.mimeData = value;
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

}

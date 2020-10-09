
package com.esri.soap.servicecatalog;

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
 *         &amp;lt;element name="TokenServiceURL" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
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
    "tokenServiceURL"
})
@XmlRootElement(name = "GetTokenServiceURLResponse")
public class GetTokenServiceURLResponse {

    @XmlElement(name = "TokenServiceURL", required = true)
    protected String tokenServiceURL;

    /**
     * Gets the value of the tokenServiceURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenServiceURL() {
        return tokenServiceURL;
    }

    /**
     * Sets the value of the tokenServiceURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenServiceURL(String value) {
        this.tokenServiceURL = value;
    }

}

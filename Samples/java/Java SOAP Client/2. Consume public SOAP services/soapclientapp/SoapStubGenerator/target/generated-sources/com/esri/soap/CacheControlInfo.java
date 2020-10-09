
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for CacheControlInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CacheControlInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ClientCachingAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CacheControlInfo", propOrder = {
    "clientCachingAllowed"
})
public class CacheControlInfo {

    @XmlElement(name = "ClientCachingAllowed")
    protected boolean clientCachingAllowed;

    /**
     * Gets the value of the clientCachingAllowed property.
     * 
     */
    public boolean isClientCachingAllowed() {
        return clientCachingAllowed;
    }

    /**
     * Sets the value of the clientCachingAllowed property.
     * 
     */
    public void setClientCachingAllowed(boolean value) {
        this.clientCachingAllowed = value;
    }

}

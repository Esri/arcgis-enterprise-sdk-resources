
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
 *         &amp;lt;element name="ServiceDescriptions" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfServiceDescription"/&amp;gt;
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
    "serviceDescriptions"
})
@XmlRootElement(name = "GetServiceDescriptionsResponse")
public class GetServiceDescriptionsResponse {

    @XmlElement(name = "ServiceDescriptions", required = true)
    protected ArrayOfServiceDescription serviceDescriptions;

    /**
     * Gets the value of the serviceDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServiceDescription }
     *     
     */
    public ArrayOfServiceDescription getServiceDescriptions() {
        return serviceDescriptions;
    }

    /**
     * Sets the value of the serviceDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServiceDescription }
     *     
     */
    public void setServiceDescriptions(ArrayOfServiceDescription value) {
        this.serviceDescriptions = value;
    }

}


package com.esri.soap.servicecatalog;

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
 *         &amp;lt;element name="MessageVersion" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriArcGISVersion"/&amp;gt;
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
    "messageVersion"
})
@XmlRootElement(name = "GetMessageVersionResponse")
public class GetMessageVersionResponse {

    @XmlElement(name = "MessageVersion", required = true)
    @XmlSchemaType(name = "string")
    protected EsriArcGISVersion messageVersion;

    /**
     * Gets the value of the messageVersion property.
     * 
     * @return
     *     possible object is
     *     {@link EsriArcGISVersion }
     *     
     */
    public EsriArcGISVersion getMessageVersion() {
        return messageVersion;
    }

    /**
     * Sets the value of the messageVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriArcGISVersion }
     *     
     */
    public void setMessageVersion(EsriArcGISVersion value) {
        this.messageVersion = value;
    }

}

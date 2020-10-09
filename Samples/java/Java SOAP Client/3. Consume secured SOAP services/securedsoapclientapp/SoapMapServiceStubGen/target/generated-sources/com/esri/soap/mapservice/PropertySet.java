
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PropertySet complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PropertySet"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="PropertyArray" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfPropertySetProperty"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertySet", propOrder = {
    "propertyArray"
})
public class PropertySet {

    @XmlElement(name = "PropertyArray", required = true)
    protected ArrayOfPropertySetProperty propertyArray;

    /**
     * Gets the value of the propertyArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPropertySetProperty }
     *     
     */
    public ArrayOfPropertySetProperty getPropertyArray() {
        return propertyArray;
    }

    /**
     * Sets the value of the propertyArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPropertySetProperty }
     *     
     */
    public void setPropertyArray(ArrayOfPropertySetProperty value) {
        this.propertyArray = value;
    }

}

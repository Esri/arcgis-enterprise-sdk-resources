
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for DataObjectType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DataObjectType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="PropDomains" type="{http://www.esri.com/schemas/ArcGIS/10.7}PropertySet"/&amp;gt;
 *         &amp;lt;element name="Templates" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfTemplateInfo"/&amp;gt;
 *         &amp;lt;element name="TypeID" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectType", propOrder = {
    "name",
    "propDomains",
    "templates",
    "typeID"
})
public class DataObjectType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "PropDomains", required = true)
    protected PropertySet propDomains;
    @XmlElement(name = "Templates", required = true)
    protected ArrayOfTemplateInfo templates;
    @XmlElement(name = "TypeID", required = true)
    protected Object typeID;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the propDomains property.
     * 
     * @return
     *     possible object is
     *     {@link PropertySet }
     *     
     */
    public PropertySet getPropDomains() {
        return propDomains;
    }

    /**
     * Sets the value of the propDomains property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertySet }
     *     
     */
    public void setPropDomains(PropertySet value) {
        this.propDomains = value;
    }

    /**
     * Gets the value of the templates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTemplateInfo }
     *     
     */
    public ArrayOfTemplateInfo getTemplates() {
        return templates;
    }

    /**
     * Sets the value of the templates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTemplateInfo }
     *     
     */
    public void setTemplates(ArrayOfTemplateInfo value) {
        this.templates = value;
    }

    /**
     * Gets the value of the typeID property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTypeID() {
        return typeID;
    }

    /**
     * Sets the value of the typeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTypeID(Object value) {
        this.typeID = value;
    }

}

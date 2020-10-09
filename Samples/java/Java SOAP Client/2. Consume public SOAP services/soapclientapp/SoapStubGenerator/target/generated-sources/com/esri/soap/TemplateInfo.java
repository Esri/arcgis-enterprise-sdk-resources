
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TemplateInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TemplateInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Prototype" type="{http://www.esri.com/schemas/ArcGIS/10.7}DataObject"/&amp;gt;
 *         &amp;lt;element name="DefaultTool" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriFeatureEditTool"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateInfo", propOrder = {
    "description",
    "name",
    "prototype",
    "defaultTool"
})
public class TemplateInfo {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Prototype", required = true)
    protected DataObject prototype;
    @XmlElement(name = "DefaultTool", required = true)
    @XmlSchemaType(name = "string")
    protected EsriFeatureEditTool defaultTool;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

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
     * Gets the value of the prototype property.
     * 
     * @return
     *     possible object is
     *     {@link DataObject }
     *     
     */
    public DataObject getPrototype() {
        return prototype;
    }

    /**
     * Sets the value of the prototype property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObject }
     *     
     */
    public void setPrototype(DataObject value) {
        this.prototype = value;
    }

    /**
     * Gets the value of the defaultTool property.
     * 
     * @return
     *     possible object is
     *     {@link EsriFeatureEditTool }
     *     
     */
    public EsriFeatureEditTool getDefaultTool() {
        return defaultTool;
    }

    /**
     * Sets the value of the defaultTool property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriFeatureEditTool }
     *     
     */
    public void setDefaultTool(EsriFeatureEditTool value) {
        this.defaultTool = value;
    }

}

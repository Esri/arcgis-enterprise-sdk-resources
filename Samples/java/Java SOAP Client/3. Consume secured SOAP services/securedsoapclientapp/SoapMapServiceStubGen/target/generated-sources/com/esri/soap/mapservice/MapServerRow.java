
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerRow complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerRow"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Properties" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}PropertySet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Relationships" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfMapServerRelationship" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FeatureID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerRow", propOrder = {
    "name",
    "properties",
    "relationships",
    "featureID"
})
public class MapServerRow {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Properties")
    protected PropertySet properties;
    @XmlElement(name = "Relationships")
    protected ArrayOfMapServerRelationship relationships;
    @XmlElement(name = "FeatureID")
    protected Integer featureID;

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
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link PropertySet }
     *     
     */
    public PropertySet getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertySet }
     *     
     */
    public void setProperties(PropertySet value) {
        this.properties = value;
    }

    /**
     * Gets the value of the relationships property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMapServerRelationship }
     *     
     */
    public ArrayOfMapServerRelationship getRelationships() {
        return relationships;
    }

    /**
     * Sets the value of the relationships property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMapServerRelationship }
     *     
     */
    public void setRelationships(ArrayOfMapServerRelationship value) {
        this.relationships = value;
    }

    /**
     * Gets the value of the featureID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFeatureID() {
        return featureID;
    }

    /**
     * Sets the value of the featureID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFeatureID(Integer value) {
        this.featureID = value;
    }

}

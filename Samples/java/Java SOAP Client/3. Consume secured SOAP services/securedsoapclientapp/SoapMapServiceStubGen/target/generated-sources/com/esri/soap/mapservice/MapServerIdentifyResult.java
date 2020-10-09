
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerIdentifyResult complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerIdentifyResult"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LayerID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Properties" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}PropertySet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Shape" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Geometry" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Relationships" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfMapServerRelationship" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HTMLPopup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
@XmlType(name = "MapServerIdentifyResult", propOrder = {
    "layerID",
    "name",
    "properties",
    "shape",
    "relationships",
    "htmlPopup",
    "featureID"
})
public class MapServerIdentifyResult {

    @XmlElement(name = "LayerID")
    protected int layerID;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Properties")
    protected PropertySet properties;
    @XmlElement(name = "Shape")
    protected Geometry shape;
    @XmlElement(name = "Relationships")
    protected ArrayOfMapServerRelationship relationships;
    @XmlElement(name = "HTMLPopup")
    protected String htmlPopup;
    @XmlElement(name = "FeatureID")
    protected Integer featureID;

    /**
     * Gets the value of the layerID property.
     * 
     */
    public int getLayerID() {
        return layerID;
    }

    /**
     * Sets the value of the layerID property.
     * 
     */
    public void setLayerID(int value) {
        this.layerID = value;
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
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setShape(Geometry value) {
        this.shape = value;
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
     * Gets the value of the htmlPopup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHTMLPopup() {
        return htmlPopup;
    }

    /**
     * Sets the value of the htmlPopup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHTMLPopup(String value) {
        this.htmlPopup = value;
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

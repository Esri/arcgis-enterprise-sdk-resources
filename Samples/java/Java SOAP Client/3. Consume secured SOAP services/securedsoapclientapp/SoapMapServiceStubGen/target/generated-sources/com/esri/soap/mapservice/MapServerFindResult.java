
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerFindResult complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerFindResult"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="LayerID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="FeatureID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="FieldName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Shape" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Geometry" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Properties" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}PropertySet" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerFindResult", propOrder = {
    "value",
    "layerID",
    "featureID",
    "fieldName",
    "shape",
    "properties"
})
public class MapServerFindResult {

    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "LayerID")
    protected int layerID;
    @XmlElement(name = "FeatureID")
    protected int featureID;
    @XmlElement(name = "FieldName", required = true)
    protected String fieldName;
    @XmlElement(name = "Shape")
    protected Geometry shape;
    @XmlElement(name = "Properties")
    protected PropertySet properties;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

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
     * Gets the value of the featureID property.
     * 
     */
    public int getFeatureID() {
        return featureID;
    }

    /**
     * Sets the value of the featureID property.
     * 
     */
    public void setFeatureID(int value) {
        this.featureID = value;
    }

    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
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

}

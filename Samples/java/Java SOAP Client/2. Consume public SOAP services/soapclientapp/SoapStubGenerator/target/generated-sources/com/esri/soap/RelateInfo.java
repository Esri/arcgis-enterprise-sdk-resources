
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RelateInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RelateInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="RelationshipID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="RelatedTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Role" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriRelRole" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Cardinality" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriRelCardinality" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="KeyField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsComposite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RelationshipTableId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="KeyFieldInRelationshipTable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelateInfo", propOrder = {
    "name",
    "relationshipID",
    "relatedTableID",
    "role",
    "cardinality",
    "keyField",
    "isComposite",
    "relationshipTableId",
    "keyFieldInRelationshipTable"
})
public class RelateInfo {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "RelationshipID")
    protected int relationshipID;
    @XmlElement(name = "RelatedTableID")
    protected int relatedTableID;
    @XmlElement(name = "Role")
    @XmlSchemaType(name = "string")
    protected EsriRelRole role;
    @XmlElement(name = "Cardinality")
    @XmlSchemaType(name = "string")
    protected EsriRelCardinality cardinality;
    @XmlElement(name = "KeyField")
    protected String keyField;
    @XmlElement(name = "IsComposite")
    protected Boolean isComposite;
    @XmlElement(name = "RelationshipTableId")
    protected Integer relationshipTableId;
    @XmlElement(name = "KeyFieldInRelationshipTable")
    protected String keyFieldInRelationshipTable;

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
     * Gets the value of the relationshipID property.
     * 
     */
    public int getRelationshipID() {
        return relationshipID;
    }

    /**
     * Sets the value of the relationshipID property.
     * 
     */
    public void setRelationshipID(int value) {
        this.relationshipID = value;
    }

    /**
     * Gets the value of the relatedTableID property.
     * 
     */
    public int getRelatedTableID() {
        return relatedTableID;
    }

    /**
     * Sets the value of the relatedTableID property.
     * 
     */
    public void setRelatedTableID(int value) {
        this.relatedTableID = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link EsriRelRole }
     *     
     */
    public EsriRelRole getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriRelRole }
     *     
     */
    public void setRole(EsriRelRole value) {
        this.role = value;
    }

    /**
     * Gets the value of the cardinality property.
     * 
     * @return
     *     possible object is
     *     {@link EsriRelCardinality }
     *     
     */
    public EsriRelCardinality getCardinality() {
        return cardinality;
    }

    /**
     * Sets the value of the cardinality property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriRelCardinality }
     *     
     */
    public void setCardinality(EsriRelCardinality value) {
        this.cardinality = value;
    }

    /**
     * Gets the value of the keyField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyField() {
        return keyField;
    }

    /**
     * Sets the value of the keyField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyField(String value) {
        this.keyField = value;
    }

    /**
     * Gets the value of the isComposite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsComposite() {
        return isComposite;
    }

    /**
     * Sets the value of the isComposite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsComposite(Boolean value) {
        this.isComposite = value;
    }

    /**
     * Gets the value of the relationshipTableId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRelationshipTableId() {
        return relationshipTableId;
    }

    /**
     * Sets the value of the relationshipTableId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRelationshipTableId(Integer value) {
        this.relationshipTableId = value;
    }

    /**
     * Gets the value of the keyFieldInRelationshipTable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyFieldInRelationshipTable() {
        return keyFieldInRelationshipTable;
    }

    /**
     * Sets the value of the keyFieldInRelationshipTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyFieldInRelationshipTable(String value) {
        this.keyFieldInRelationshipTable = value;
    }

}

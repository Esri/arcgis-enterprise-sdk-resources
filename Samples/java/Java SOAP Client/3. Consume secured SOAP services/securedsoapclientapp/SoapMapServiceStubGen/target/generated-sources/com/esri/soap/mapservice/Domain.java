
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Domain Properties control and their merge split policies control.
 *       
 * 
 * &lt;p&gt;Java class for Domain complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Domain"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DomainName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="FieldType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriFieldType"/&amp;gt;
 *         &amp;lt;element name="MergePolicy" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriMergePolicyType"/&amp;gt;
 *         &amp;lt;element name="SplitPolicy" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSplitPolicyType"/&amp;gt;
 *         &amp;lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Domain", propOrder = {
    "domainName",
    "fieldType",
    "mergePolicy",
    "splitPolicy",
    "description",
    "owner"
})
@XmlSeeAlso({
    RangeDomain.class,
    CodedValueDomain.class
})
public abstract class Domain {

    @XmlElement(name = "DomainName", required = true)
    protected String domainName;
    @XmlElement(name = "FieldType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriFieldType fieldType;
    @XmlElement(name = "MergePolicy", required = true)
    @XmlSchemaType(name = "string")
    protected EsriMergePolicyType mergePolicy;
    @XmlElement(name = "SplitPolicy", required = true)
    @XmlSchemaType(name = "string")
    protected EsriSplitPolicyType splitPolicy;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Owner")
    protected String owner;

    /**
     * Gets the value of the domainName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets the value of the domainName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainName(String value) {
        this.domainName = value;
    }

    /**
     * Gets the value of the fieldType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriFieldType }
     *     
     */
    public EsriFieldType getFieldType() {
        return fieldType;
    }

    /**
     * Sets the value of the fieldType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriFieldType }
     *     
     */
    public void setFieldType(EsriFieldType value) {
        this.fieldType = value;
    }

    /**
     * Gets the value of the mergePolicy property.
     * 
     * @return
     *     possible object is
     *     {@link EsriMergePolicyType }
     *     
     */
    public EsriMergePolicyType getMergePolicy() {
        return mergePolicy;
    }

    /**
     * Sets the value of the mergePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriMergePolicyType }
     *     
     */
    public void setMergePolicy(EsriMergePolicyType value) {
        this.mergePolicy = value;
    }

    /**
     * Gets the value of the splitPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSplitPolicyType }
     *     
     */
    public EsriSplitPolicyType getSplitPolicy() {
        return splitPolicy;
    }

    /**
     * Sets the value of the splitPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSplitPolicyType }
     *     
     */
    public void setSplitPolicy(EsriSplitPolicyType value) {
        this.splitPolicy = value;
    }

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
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

}

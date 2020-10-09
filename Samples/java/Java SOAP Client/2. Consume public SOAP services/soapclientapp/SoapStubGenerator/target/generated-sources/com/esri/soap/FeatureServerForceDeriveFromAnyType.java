
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for FeatureServerForceDeriveFromAnyType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FeatureServerForceDeriveFromAnyType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DataObjects" type="{http://www.esri.com/schemas/ArcGIS/10.7}DataObjects"/&amp;gt;
 *         &amp;lt;element name="DataObjectGroups" type="{http://www.esri.com/schemas/ArcGIS/10.7}DataObjectGroups"/&amp;gt;
 *         &amp;lt;element name="DomainInfo" type="{http://www.esri.com/schemas/ArcGIS/10.7}DomainInfo"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureServerForceDeriveFromAnyType", propOrder = {
    "dataObjects",
    "dataObjectGroups",
    "domainInfo"
})
public class FeatureServerForceDeriveFromAnyType {

    @XmlElement(name = "DataObjects", required = true)
    protected DataObjects dataObjects;
    @XmlElement(name = "DataObjectGroups", required = true)
    protected DataObjectGroups dataObjectGroups;
    @XmlElement(name = "DomainInfo", required = true)
    protected DomainInfo domainInfo;

    /**
     * Gets the value of the dataObjects property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjects }
     *     
     */
    public DataObjects getDataObjects() {
        return dataObjects;
    }

    /**
     * Sets the value of the dataObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjects }
     *     
     */
    public void setDataObjects(DataObjects value) {
        this.dataObjects = value;
    }

    /**
     * Gets the value of the dataObjectGroups property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectGroups }
     *     
     */
    public DataObjectGroups getDataObjectGroups() {
        return dataObjectGroups;
    }

    /**
     * Sets the value of the dataObjectGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectGroups }
     *     
     */
    public void setDataObjectGroups(DataObjectGroups value) {
        this.dataObjectGroups = value;
    }

    /**
     * Gets the value of the domainInfo property.
     * 
     * @return
     *     possible object is
     *     {@link DomainInfo }
     *     
     */
    public DomainInfo getDomainInfo() {
        return domainInfo;
    }

    /**
     * Sets the value of the domainInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainInfo }
     *     
     */
    public void setDomainInfo(DomainInfo value) {
        this.domainInfo = value;
    }

}

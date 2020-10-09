
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapServerForceDeriveFromAnyType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapServerForceDeriveFromAnyType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RelatedRecordSet" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}RelatedRecordSet"/&amp;gt;
 *         &amp;lt;element name="FieldDomainInfo" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}FieldDomainInfo"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapServerForceDeriveFromAnyType", propOrder = {
    "relatedRecordSet",
    "fieldDomainInfo"
})
public class MapServerForceDeriveFromAnyType {

    @XmlElement(name = "RelatedRecordSet", required = true)
    protected RelatedRecordSet relatedRecordSet;
    @XmlElement(name = "FieldDomainInfo", required = true)
    protected FieldDomainInfo fieldDomainInfo;

    /**
     * Gets the value of the relatedRecordSet property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedRecordSet }
     *     
     */
    public RelatedRecordSet getRelatedRecordSet() {
        return relatedRecordSet;
    }

    /**
     * Sets the value of the relatedRecordSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedRecordSet }
     *     
     */
    public void setRelatedRecordSet(RelatedRecordSet value) {
        this.relatedRecordSet = value;
    }

    /**
     * Gets the value of the fieldDomainInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FieldDomainInfo }
     *     
     */
    public FieldDomainInfo getFieldDomainInfo() {
        return fieldDomainInfo;
    }

    /**
     * Sets the value of the fieldDomainInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldDomainInfo }
     *     
     */
    public void setFieldDomainInfo(FieldDomainInfo value) {
        this.fieldDomainInfo = value;
    }

}

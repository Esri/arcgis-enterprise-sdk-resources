
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RelatedRecordSet complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RelatedRecordSet"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RelatedRecordFields" type="{http://www.esri.com/schemas/ArcGIS/10.7}Fields"/&amp;gt;
 *         &amp;lt;element name="RelatedRecordGroups" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfRelatedRecordGroup"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedRecordSet", propOrder = {
    "relatedRecordFields",
    "relatedRecordGroups"
})
public class RelatedRecordSet {

    @XmlElement(name = "RelatedRecordFields", required = true)
    protected Fields relatedRecordFields;
    @XmlElement(name = "RelatedRecordGroups", required = true)
    protected ArrayOfRelatedRecordGroup relatedRecordGroups;

    /**
     * Gets the value of the relatedRecordFields property.
     * 
     * @return
     *     possible object is
     *     {@link Fields }
     *     
     */
    public Fields getRelatedRecordFields() {
        return relatedRecordFields;
    }

    /**
     * Sets the value of the relatedRecordFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fields }
     *     
     */
    public void setRelatedRecordFields(Fields value) {
        this.relatedRecordFields = value;
    }

    /**
     * Gets the value of the relatedRecordGroups property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRelatedRecordGroup }
     *     
     */
    public ArrayOfRelatedRecordGroup getRelatedRecordGroups() {
        return relatedRecordGroups;
    }

    /**
     * Sets the value of the relatedRecordGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRelatedRecordGroup }
     *     
     */
    public void setRelatedRecordGroups(ArrayOfRelatedRecordGroup value) {
        this.relatedRecordGroups = value;
    }

}

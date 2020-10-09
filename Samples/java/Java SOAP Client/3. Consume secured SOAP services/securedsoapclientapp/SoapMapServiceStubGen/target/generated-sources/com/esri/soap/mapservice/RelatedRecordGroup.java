
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RelatedRecordGroup complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RelatedRecordGroup"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SourceRowID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Records" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfRecord"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedRecordGroup", propOrder = {
    "sourceRowID",
    "records"
})
public class RelatedRecordGroup {

    @XmlElement(name = "SourceRowID")
    protected int sourceRowID;
    @XmlElement(name = "Records", required = true)
    protected ArrayOfRecord records;

    /**
     * Gets the value of the sourceRowID property.
     * 
     */
    public int getSourceRowID() {
        return sourceRowID;
    }

    /**
     * Sets the value of the sourceRowID property.
     * 
     */
    public void setSourceRowID(int value) {
        this.sourceRowID = value;
    }

    /**
     * Gets the value of the records property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecord }
     *     
     */
    public ArrayOfRecord getRecords() {
        return records;
    }

    /**
     * Sets the value of the records property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecord }
     *     
     */
    public void setRecords(ArrayOfRecord value) {
        this.records = value;
    }

}

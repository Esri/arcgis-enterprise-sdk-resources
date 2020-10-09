
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SubtypeInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SubtypeInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SubtypeCode" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="SubtypeName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="FieldDomainInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfFieldDomainInfo"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubtypeInfo", propOrder = {
    "subtypeCode",
    "subtypeName",
    "fieldDomainInfos"
})
public class SubtypeInfo {

    @XmlElement(name = "SubtypeCode")
    protected int subtypeCode;
    @XmlElement(name = "SubtypeName", required = true)
    protected String subtypeName;
    @XmlElement(name = "FieldDomainInfos", required = true)
    protected ArrayOfFieldDomainInfo fieldDomainInfos;

    /**
     * Gets the value of the subtypeCode property.
     * 
     */
    public int getSubtypeCode() {
        return subtypeCode;
    }

    /**
     * Sets the value of the subtypeCode property.
     * 
     */
    public void setSubtypeCode(int value) {
        this.subtypeCode = value;
    }

    /**
     * Gets the value of the subtypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubtypeName() {
        return subtypeName;
    }

    /**
     * Sets the value of the subtypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubtypeName(String value) {
        this.subtypeName = value;
    }

    /**
     * Gets the value of the fieldDomainInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFieldDomainInfo }
     *     
     */
    public ArrayOfFieldDomainInfo getFieldDomainInfos() {
        return fieldDomainInfos;
    }

    /**
     * Sets the value of the fieldDomainInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFieldDomainInfo }
     *     
     */
    public void setFieldDomainInfos(ArrayOfFieldDomainInfo value) {
        this.fieldDomainInfos = value;
    }

}

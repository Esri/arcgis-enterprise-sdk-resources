
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TimeReference complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TimeReference"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="TimeZoneNameID" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="RespectsDaylightSavingTime" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RespectsDynamicAdjustmentRules" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeReference", propOrder = {
    "timeZoneNameID",
    "respectsDaylightSavingTime",
    "respectsDynamicAdjustmentRules"
})
public class TimeReference {

    @XmlElement(name = "TimeZoneNameID", required = true)
    protected String timeZoneNameID;
    @XmlElement(name = "RespectsDaylightSavingTime")
    protected Boolean respectsDaylightSavingTime;
    @XmlElement(name = "RespectsDynamicAdjustmentRules")
    protected Boolean respectsDynamicAdjustmentRules;

    /**
     * Gets the value of the timeZoneNameID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneNameID() {
        return timeZoneNameID;
    }

    /**
     * Sets the value of the timeZoneNameID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneNameID(String value) {
        this.timeZoneNameID = value;
    }

    /**
     * Gets the value of the respectsDaylightSavingTime property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRespectsDaylightSavingTime() {
        return respectsDaylightSavingTime;
    }

    /**
     * Sets the value of the respectsDaylightSavingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRespectsDaylightSavingTime(Boolean value) {
        this.respectsDaylightSavingTime = value;
    }

    /**
     * Gets the value of the respectsDynamicAdjustmentRules property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRespectsDynamicAdjustmentRules() {
        return respectsDynamicAdjustmentRules;
    }

    /**
     * Sets the value of the respectsDynamicAdjustmentRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRespectsDynamicAdjustmentRules(Boolean value) {
        this.respectsDynamicAdjustmentRules = value;
    }

}

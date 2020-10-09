
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for StatisticDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="StatisticDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="StatisticFieldName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="StatisticType" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriDataStatType"/&amp;gt;
 *         &amp;lt;element name="ResultFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatisticDescription", propOrder = {
    "statisticFieldName",
    "statisticType",
    "resultFieldName"
})
public class StatisticDescription {

    @XmlElement(name = "StatisticFieldName", required = true)
    protected String statisticFieldName;
    @XmlElement(name = "StatisticType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriDataStatType statisticType;
    @XmlElement(name = "ResultFieldName")
    protected String resultFieldName;

    /**
     * Gets the value of the statisticFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatisticFieldName() {
        return statisticFieldName;
    }

    /**
     * Sets the value of the statisticFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatisticFieldName(String value) {
        this.statisticFieldName = value;
    }

    /**
     * Gets the value of the statisticType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriDataStatType }
     *     
     */
    public EsriDataStatType getStatisticType() {
        return statisticType;
    }

    /**
     * Sets the value of the statisticType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriDataStatType }
     *     
     */
    public void setStatisticType(EsriDataStatType value) {
        this.statisticType = value;
    }

    /**
     * Gets the value of the resultFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultFieldName() {
        return resultFieldName;
    }

    /**
     * Sets the value of the resultFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultFieldName(String value) {
        this.resultFieldName = value;
    }

}

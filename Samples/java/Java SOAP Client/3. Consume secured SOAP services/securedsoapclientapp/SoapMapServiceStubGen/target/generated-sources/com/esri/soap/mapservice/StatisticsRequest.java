
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for StatisticsRequest complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="StatisticsRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="StatisticDescriptions" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfStatisticDescription" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OrderByFields" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GroupByFields" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatisticsRequest", propOrder = {
    "statisticDescriptions",
    "orderByFields",
    "groupByFields"
})
public class StatisticsRequest {

    @XmlElement(name = "StatisticDescriptions")
    protected ArrayOfStatisticDescription statisticDescriptions;
    @XmlElement(name = "OrderByFields")
    protected String orderByFields;
    @XmlElement(name = "GroupByFields")
    protected String groupByFields;

    /**
     * Gets the value of the statisticDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfStatisticDescription }
     *     
     */
    public ArrayOfStatisticDescription getStatisticDescriptions() {
        return statisticDescriptions;
    }

    /**
     * Sets the value of the statisticDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfStatisticDescription }
     *     
     */
    public void setStatisticDescriptions(ArrayOfStatisticDescription value) {
        this.statisticDescriptions = value;
    }

    /**
     * Gets the value of the orderByFields property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderByFields() {
        return orderByFields;
    }

    /**
     * Sets the value of the orderByFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderByFields(String value) {
        this.orderByFields = value;
    }

    /**
     * Gets the value of the groupByFields property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupByFields() {
        return groupByFields;
    }

    /**
     * Sets the value of the groupByFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupByFields(String value) {
        this.groupByFields = value;
    }

}

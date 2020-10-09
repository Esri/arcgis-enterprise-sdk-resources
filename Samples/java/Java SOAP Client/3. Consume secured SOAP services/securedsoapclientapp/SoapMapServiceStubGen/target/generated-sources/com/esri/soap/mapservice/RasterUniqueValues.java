
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterUniqueValues complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterUniqueValues"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="UniqueValuesSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Values" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfValue" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Counts" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfInt" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterUniqueValues", propOrder = {
    "uniqueValuesSize",
    "values",
    "counts"
})
public class RasterUniqueValues {

    @XmlElement(name = "UniqueValuesSize")
    protected Integer uniqueValuesSize;
    @XmlElement(name = "Values")
    protected ArrayOfValue values;
    @XmlElement(name = "Counts")
    protected ArrayOfInt counts;

    /**
     * Gets the value of the uniqueValuesSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUniqueValuesSize() {
        return uniqueValuesSize;
    }

    /**
     * Sets the value of the uniqueValuesSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUniqueValuesSize(Integer value) {
        this.uniqueValuesSize = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfValue }
     *     
     */
    public ArrayOfValue getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfValue }
     *     
     */
    public void setValues(ArrayOfValue value) {
        this.values = value;
    }

    /**
     * Gets the value of the counts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getCounts() {
        return counts;
    }

    /**
     * Sets the value of the counts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setCounts(ArrayOfInt value) {
        this.counts = value;
    }

}

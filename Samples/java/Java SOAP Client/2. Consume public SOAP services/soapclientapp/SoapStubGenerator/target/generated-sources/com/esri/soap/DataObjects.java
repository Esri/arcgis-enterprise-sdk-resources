
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for DataObjects complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DataObjects"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DataObjectArray" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfDataObject"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeReference" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjects", propOrder = {
    "dataObjectArray",
    "spatialReference",
    "timeReference"
})
public class DataObjects {

    @XmlElement(name = "DataObjectArray", required = true)
    protected ArrayOfDataObject dataObjectArray;
    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;
    @XmlElement(name = "TimeReference")
    protected TimeReference timeReference;

    /**
     * Gets the value of the dataObjectArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDataObject }
     *     
     */
    public ArrayOfDataObject getDataObjectArray() {
        return dataObjectArray;
    }

    /**
     * Sets the value of the dataObjectArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDataObject }
     *     
     */
    public void setDataObjectArray(ArrayOfDataObject value) {
        this.dataObjectArray = value;
    }

    /**
     * Gets the value of the spatialReference property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialReference }
     *     
     */
    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    /**
     * Sets the value of the spatialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialReference }
     *     
     */
    public void setSpatialReference(SpatialReference value) {
        this.spatialReference = value;
    }

    /**
     * Gets the value of the timeReference property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getTimeReference() {
        return timeReference;
    }

    /**
     * Sets the value of the timeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setTimeReference(TimeReference value) {
        this.timeReference = value;
    }

}

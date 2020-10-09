
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for DataObjectGroup complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DataObjectGroup"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DataObjectArray" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfDataObject"/&amp;gt;
 *         &amp;lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectGroup", propOrder = {
    "dataObjectArray",
    "groupID"
})
public class DataObjectGroup {

    @XmlElement(name = "DataObjectArray", required = true)
    protected ArrayOfDataObject dataObjectArray;
    @XmlElement(name = "GroupID")
    protected int groupID;

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
     * Gets the value of the groupID property.
     * 
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     */
    public void setGroupID(int value) {
        this.groupID = value;
    }

}

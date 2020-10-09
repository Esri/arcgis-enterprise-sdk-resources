
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MapTableSubtypeInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MapTableSubtypeInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="TableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="SubtypeFieldName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="SubtypeInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfSubtypeInfo"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapTableSubtypeInfo", propOrder = {
    "tableID",
    "subtypeFieldName",
    "subtypeInfos"
})
public class MapTableSubtypeInfo {

    @XmlElement(name = "TableID")
    protected int tableID;
    @XmlElement(name = "SubtypeFieldName", required = true)
    protected String subtypeFieldName;
    @XmlElement(name = "SubtypeInfos", required = true)
    protected ArrayOfSubtypeInfo subtypeInfos;

    /**
     * Gets the value of the tableID property.
     * 
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * Sets the value of the tableID property.
     * 
     */
    public void setTableID(int value) {
        this.tableID = value;
    }

    /**
     * Gets the value of the subtypeFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubtypeFieldName() {
        return subtypeFieldName;
    }

    /**
     * Sets the value of the subtypeFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubtypeFieldName(String value) {
        this.subtypeFieldName = value;
    }

    /**
     * Gets the value of the subtypeInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSubtypeInfo }
     *     
     */
    public ArrayOfSubtypeInfo getSubtypeInfos() {
        return subtypeInfos;
    }

    /**
     * Sets the value of the subtypeInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSubtypeInfo }
     *     
     */
    public void setSubtypeInfos(ArrayOfSubtypeInfo value) {
        this.subtypeInfos = value;
    }

}

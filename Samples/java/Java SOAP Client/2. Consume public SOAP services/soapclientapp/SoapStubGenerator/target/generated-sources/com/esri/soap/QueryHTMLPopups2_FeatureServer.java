
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MapName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="MapTableDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapTableDescription"/&amp;gt;
 *         &amp;lt;element name="RowIDs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mapName",
    "mapTableDescription",
    "rowIDs"
})
@XmlRootElement(name = "QueryHTMLPopups2")
public class QueryHTMLPopups2_FeatureServer {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "MapTableDescription", required = true)
    protected MapTableDescription mapTableDescription;
    @XmlElement(name = "RowIDs", required = true)
    protected ArrayOfInt rowIDs;

    /**
     * Gets the value of the mapName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Sets the value of the mapName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapName(String value) {
        this.mapName = value;
    }

    /**
     * Gets the value of the mapTableDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MapTableDescription }
     *     
     */
    public MapTableDescription getMapTableDescription() {
        return mapTableDescription;
    }

    /**
     * Sets the value of the mapTableDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapTableDescription }
     *     
     */
    public void setMapTableDescription(MapTableDescription value) {
        this.mapTableDescription = value;
    }

    /**
     * Gets the value of the rowIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getRowIDs() {
        return rowIDs;
    }

    /**
     * Sets the value of the rowIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setRowIDs(ArrayOfInt value) {
        this.rowIDs = value;
    }

}

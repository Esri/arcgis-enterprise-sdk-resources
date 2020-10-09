
package com.esri.soap.mapservice;

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
 *         &amp;lt;element name="MapTableDescription" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}MapTableDescription"/&amp;gt;
 *         &amp;lt;element name="SourceFIDSet" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}FIDSet"/&amp;gt;
 *         &amp;lt;element name="RelateDescription" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}RelateDescription"/&amp;gt;
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
    "sourceFIDSet",
    "relateDescription"
})
@XmlRootElement(name = "QueryRelatedRecords2")
public class QueryRelatedRecords2 {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "MapTableDescription", required = true)
    protected MapTableDescription mapTableDescription;
    @XmlElement(name = "SourceFIDSet", required = true)
    protected FIDSet sourceFIDSet;
    @XmlElement(name = "RelateDescription", required = true)
    protected RelateDescription relateDescription;

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
     * Gets the value of the sourceFIDSet property.
     * 
     * @return
     *     possible object is
     *     {@link FIDSet }
     *     
     */
    public FIDSet getSourceFIDSet() {
        return sourceFIDSet;
    }

    /**
     * Sets the value of the sourceFIDSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link FIDSet }
     *     
     */
    public void setSourceFIDSet(FIDSet value) {
        this.sourceFIDSet = value;
    }

    /**
     * Gets the value of the relateDescription property.
     * 
     * @return
     *     possible object is
     *     {@link RelateDescription }
     *     
     */
    public RelateDescription getRelateDescription() {
        return relateDescription;
    }

    /**
     * Sets the value of the relateDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelateDescription }
     *     
     */
    public void setRelateDescription(RelateDescription value) {
        this.relateDescription = value;
    }

}

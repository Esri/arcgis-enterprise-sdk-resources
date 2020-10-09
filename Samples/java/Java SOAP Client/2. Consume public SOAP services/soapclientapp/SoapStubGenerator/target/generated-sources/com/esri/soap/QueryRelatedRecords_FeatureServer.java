
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
 *         &amp;lt;element name="SourceTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="SourceFIDSet" type="{http://www.esri.com/schemas/ArcGIS/10.7}FIDSet"/&amp;gt;
 *         &amp;lt;element name="RelateDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}RelateDescription"/&amp;gt;
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
    "sourceTableID",
    "sourceFIDSet",
    "relateDescription"
})
@XmlRootElement(name = "QueryRelatedRecords")
public class QueryRelatedRecords_FeatureServer {

    @XmlElement(name = "MapName", required = true)
    protected String mapName;
    @XmlElement(name = "SourceTableID")
    protected int sourceTableID;
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
     * Gets the value of the sourceTableID property.
     * 
     */
    public int getSourceTableID() {
        return sourceTableID;
    }

    /**
     * Sets the value of the sourceTableID property.
     * 
     */
    public void setSourceTableID(int value) {
        this.sourceTableID = value;
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

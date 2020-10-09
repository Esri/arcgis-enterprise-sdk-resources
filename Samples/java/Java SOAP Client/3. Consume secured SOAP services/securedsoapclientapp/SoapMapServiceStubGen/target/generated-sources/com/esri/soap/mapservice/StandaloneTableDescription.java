
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for StandaloneTableDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="StandaloneTableDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}MapTableDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="DefinitionExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseTime" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeDataCumulative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeOffsetUnits" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriTimeUnits" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Source" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}MapServerSourceDescription" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandaloneTableDescription", propOrder = {
    "id",
    "definitionExpression",
    "sourceID",
    "useTime",
    "timeDataCumulative",
    "timeOffset",
    "timeOffsetUnits",
    "source"
})
public class StandaloneTableDescription
    extends MapTableDescription
{

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "DefinitionExpression")
    protected String definitionExpression;
    @XmlElement(name = "SourceID")
    protected String sourceID;
    @XmlElement(name = "UseTime")
    protected Boolean useTime;
    @XmlElement(name = "TimeDataCumulative")
    protected Boolean timeDataCumulative;
    @XmlElement(name = "TimeOffset")
    protected Double timeOffset;
    @XmlElement(name = "TimeOffsetUnits")
    @XmlSchemaType(name = "string")
    protected EsriTimeUnits timeOffsetUnits;
    @XmlElement(name = "Source")
    protected MapServerSourceDescription source;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the definitionExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefinitionExpression() {
        return definitionExpression;
    }

    /**
     * Sets the value of the definitionExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefinitionExpression(String value) {
        this.definitionExpression = value;
    }

    /**
     * Gets the value of the sourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceID() {
        return sourceID;
    }

    /**
     * Sets the value of the sourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceID(String value) {
        this.sourceID = value;
    }

    /**
     * Gets the value of the useTime property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseTime() {
        return useTime;
    }

    /**
     * Sets the value of the useTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseTime(Boolean value) {
        this.useTime = value;
    }

    /**
     * Gets the value of the timeDataCumulative property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTimeDataCumulative() {
        return timeDataCumulative;
    }

    /**
     * Sets the value of the timeDataCumulative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTimeDataCumulative(Boolean value) {
        this.timeDataCumulative = value;
    }

    /**
     * Gets the value of the timeOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTimeOffset() {
        return timeOffset;
    }

    /**
     * Sets the value of the timeOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTimeOffset(Double value) {
        this.timeOffset = value;
    }

    /**
     * Gets the value of the timeOffsetUnits property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTimeUnits }
     *     
     */
    public EsriTimeUnits getTimeOffsetUnits() {
        return timeOffsetUnits;
    }

    /**
     * Sets the value of the timeOffsetUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTimeUnits }
     *     
     */
    public void setTimeOffsetUnits(EsriTimeUnits value) {
        this.timeOffsetUnits = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link MapServerSourceDescription }
     *     
     */
    public MapServerSourceDescription getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapServerSourceDescription }
     *     
     */
    public void setSource(MapServerSourceDescription value) {
        this.source = value;
    }

}

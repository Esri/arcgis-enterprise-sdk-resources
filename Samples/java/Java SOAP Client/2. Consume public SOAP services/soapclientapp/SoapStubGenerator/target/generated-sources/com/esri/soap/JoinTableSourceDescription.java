
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for JoinTableSourceDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="JoinTableSourceDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}MapServerSourceDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LeftSourceDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapServerSourceDescription"/&amp;gt;
 *         &amp;lt;element name="RightSourceDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapServerSourceDescription"/&amp;gt;
 *         &amp;lt;element name="LeftTableKey" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="RightTableKey" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="JoinType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriJoinType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JoinTableSourceDescription", propOrder = {
    "leftSourceDescription",
    "rightSourceDescription",
    "leftTableKey",
    "rightTableKey",
    "joinType"
})
public class JoinTableSourceDescription
    extends MapServerSourceDescription
{

    @XmlElement(name = "LeftSourceDescription", required = true)
    protected MapServerSourceDescription leftSourceDescription;
    @XmlElement(name = "RightSourceDescription", required = true)
    protected MapServerSourceDescription rightSourceDescription;
    @XmlElement(name = "LeftTableKey", required = true)
    protected String leftTableKey;
    @XmlElement(name = "RightTableKey", required = true)
    protected String rightTableKey;
    @XmlElement(name = "JoinType")
    @XmlSchemaType(name = "string")
    protected EsriJoinType joinType;

    /**
     * Gets the value of the leftSourceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MapServerSourceDescription }
     *     
     */
    public MapServerSourceDescription getLeftSourceDescription() {
        return leftSourceDescription;
    }

    /**
     * Sets the value of the leftSourceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapServerSourceDescription }
     *     
     */
    public void setLeftSourceDescription(MapServerSourceDescription value) {
        this.leftSourceDescription = value;
    }

    /**
     * Gets the value of the rightSourceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MapServerSourceDescription }
     *     
     */
    public MapServerSourceDescription getRightSourceDescription() {
        return rightSourceDescription;
    }

    /**
     * Sets the value of the rightSourceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapServerSourceDescription }
     *     
     */
    public void setRightSourceDescription(MapServerSourceDescription value) {
        this.rightSourceDescription = value;
    }

    /**
     * Gets the value of the leftTableKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftTableKey() {
        return leftTableKey;
    }

    /**
     * Sets the value of the leftTableKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftTableKey(String value) {
        this.leftTableKey = value;
    }

    /**
     * Gets the value of the rightTableKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightTableKey() {
        return rightTableKey;
    }

    /**
     * Sets the value of the rightTableKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightTableKey(String value) {
        this.rightTableKey = value;
    }

    /**
     * Gets the value of the joinType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriJoinType }
     *     
     */
    public EsriJoinType getJoinType() {
        return joinType;
    }

    /**
     * Sets the value of the joinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriJoinType }
     *     
     */
    public void setJoinType(EsriJoinType value) {
        this.joinType = value;
    }

}

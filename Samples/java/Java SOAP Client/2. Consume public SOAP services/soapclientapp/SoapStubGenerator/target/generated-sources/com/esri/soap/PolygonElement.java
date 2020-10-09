
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PolygonElement complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PolygonElement"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}GraphicElement"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AutoTransform" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ReferenceScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Polygon" type="{http://www.esri.com/schemas/ArcGIS/10.7}Geometry"/&amp;gt;
 *         &amp;lt;element name="Locked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FixedAspectRatio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonElement", propOrder = {
    "name",
    "type",
    "autoTransform",
    "referenceScale",
    "symbol",
    "polygon",
    "locked",
    "fixedAspectRatio"
})
public class PolygonElement
    extends GraphicElement
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "AutoTransform")
    protected Boolean autoTransform;
    @XmlElement(name = "ReferenceScale")
    protected Double referenceScale;
    @XmlElement(name = "Symbol")
    protected Symbol symbol;
    @XmlElement(name = "Polygon", required = true)
    protected Geometry polygon;
    @XmlElement(name = "Locked")
    protected Boolean locked;
    @XmlElement(name = "FixedAspectRatio")
    protected Boolean fixedAspectRatio;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the autoTransform property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoTransform() {
        return autoTransform;
    }

    /**
     * Sets the value of the autoTransform property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoTransform(Boolean value) {
        this.autoTransform = value;
    }

    /**
     * Gets the value of the referenceScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReferenceScale() {
        return referenceScale;
    }

    /**
     * Sets the value of the referenceScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReferenceScale(Double value) {
        this.referenceScale = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setSymbol(Symbol value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the polygon property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getPolygon() {
        return polygon;
    }

    /**
     * Sets the value of the polygon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setPolygon(Geometry value) {
        this.polygon = value;
    }

    /**
     * Gets the value of the locked property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLocked() {
        return locked;
    }

    /**
     * Sets the value of the locked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLocked(Boolean value) {
        this.locked = value;
    }

    /**
     * Gets the value of the fixedAspectRatio property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFixedAspectRatio() {
        return fixedAspectRatio;
    }

    /**
     * Sets the value of the fixedAspectRatio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFixedAspectRatio(Boolean value) {
        this.fixedAspectRatio = value;
    }

}

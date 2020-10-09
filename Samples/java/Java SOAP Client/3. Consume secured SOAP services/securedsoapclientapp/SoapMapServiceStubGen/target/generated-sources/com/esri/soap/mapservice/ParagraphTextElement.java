
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ParagraphTextElement complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ParagraphTextElement"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}GraphicElement"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AutoTransform" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ReferenceScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Scale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TextGeometry" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Geometry"/&amp;gt;
 *         &amp;lt;element name="Locked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FrameBorder" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Border" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FrameBackground" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Background" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FrameShadow" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Shadow" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColumnGap" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColumnCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Margin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParagraphTextElement", propOrder = {
    "name",
    "type",
    "autoTransform",
    "referenceScale",
    "text",
    "scale",
    "symbol",
    "textGeometry",
    "locked",
    "frameBorder",
    "frameBackground",
    "frameShadow",
    "columnGap",
    "columnCount",
    "margin"
})
public class ParagraphTextElement
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
    @XmlElement(name = "Text")
    protected String text;
    @XmlElement(name = "Scale")
    protected Double scale;
    @XmlElement(name = "Symbol")
    protected Symbol symbol;
    @XmlElement(name = "TextGeometry", required = true)
    protected Geometry textGeometry;
    @XmlElement(name = "Locked")
    protected Boolean locked;
    @XmlElement(name = "FrameBorder")
    protected Border frameBorder;
    @XmlElement(name = "FrameBackground")
    protected Background frameBackground;
    @XmlElement(name = "FrameShadow")
    protected Shadow frameShadow;
    @XmlElement(name = "ColumnGap")
    protected Double columnGap;
    @XmlElement(name = "ColumnCount")
    protected Integer columnCount;
    @XmlElement(name = "Margin")
    protected Double margin;

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
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setScale(Double value) {
        this.scale = value;
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
     * Gets the value of the textGeometry property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getTextGeometry() {
        return textGeometry;
    }

    /**
     * Sets the value of the textGeometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setTextGeometry(Geometry value) {
        this.textGeometry = value;
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
     * Gets the value of the frameBorder property.
     * 
     * @return
     *     possible object is
     *     {@link Border }
     *     
     */
    public Border getFrameBorder() {
        return frameBorder;
    }

    /**
     * Sets the value of the frameBorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Border }
     *     
     */
    public void setFrameBorder(Border value) {
        this.frameBorder = value;
    }

    /**
     * Gets the value of the frameBackground property.
     * 
     * @return
     *     possible object is
     *     {@link Background }
     *     
     */
    public Background getFrameBackground() {
        return frameBackground;
    }

    /**
     * Sets the value of the frameBackground property.
     * 
     * @param value
     *     allowed object is
     *     {@link Background }
     *     
     */
    public void setFrameBackground(Background value) {
        this.frameBackground = value;
    }

    /**
     * Gets the value of the frameShadow property.
     * 
     * @return
     *     possible object is
     *     {@link Shadow }
     *     
     */
    public Shadow getFrameShadow() {
        return frameShadow;
    }

    /**
     * Sets the value of the frameShadow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shadow }
     *     
     */
    public void setFrameShadow(Shadow value) {
        this.frameShadow = value;
    }

    /**
     * Gets the value of the columnGap property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getColumnGap() {
        return columnGap;
    }

    /**
     * Sets the value of the columnGap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setColumnGap(Double value) {
        this.columnGap = value;
    }

    /**
     * Gets the value of the columnCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getColumnCount() {
        return columnCount;
    }

    /**
     * Sets the value of the columnCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setColumnCount(Integer value) {
        this.columnCount = value;
    }

    /**
     * Gets the value of the margin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMargin() {
        return margin;
    }

    /**
     * Sets the value of the margin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMargin(Double value) {
        this.margin = value;
    }

}

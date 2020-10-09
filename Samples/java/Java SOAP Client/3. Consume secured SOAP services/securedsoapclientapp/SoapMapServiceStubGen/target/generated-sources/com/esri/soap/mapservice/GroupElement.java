
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for GroupElement complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="GroupElement"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}Element"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AutoTransform" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ReferenceScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Elements" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfGraphicElement"/&amp;gt;
 *         &amp;lt;element name="Rectangle" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Geometry" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Locked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FixedAspectRatio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Border" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Border" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Background" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Background" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DraftMode" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Shadow" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}Shadow" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupElement", propOrder = {
    "name",
    "type",
    "autoTransform",
    "referenceScale",
    "elements",
    "rectangle",
    "locked",
    "fixedAspectRatio",
    "border",
    "background",
    "draftMode",
    "shadow"
})
public class GroupElement
    extends Element
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "AutoTransform")
    protected Boolean autoTransform;
    @XmlElement(name = "ReferenceScale")
    protected Double referenceScale;
    @XmlElement(name = "Elements", required = true)
    protected ArrayOfGraphicElement elements;
    @XmlElement(name = "Rectangle")
    protected Geometry rectangle;
    @XmlElement(name = "Locked")
    protected Boolean locked;
    @XmlElement(name = "FixedAspectRatio")
    protected Boolean fixedAspectRatio;
    @XmlElement(name = "Border")
    protected Border border;
    @XmlElement(name = "Background")
    protected Background background;
    @XmlElement(name = "DraftMode")
    protected Boolean draftMode;
    @XmlElement(name = "Shadow")
    protected Shadow shadow;

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
     * Gets the value of the elements property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGraphicElement }
     *     
     */
    public ArrayOfGraphicElement getElements() {
        return elements;
    }

    /**
     * Sets the value of the elements property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGraphicElement }
     *     
     */
    public void setElements(ArrayOfGraphicElement value) {
        this.elements = value;
    }

    /**
     * Gets the value of the rectangle property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getRectangle() {
        return rectangle;
    }

    /**
     * Sets the value of the rectangle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setRectangle(Geometry value) {
        this.rectangle = value;
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

    /**
     * Gets the value of the border property.
     * 
     * @return
     *     possible object is
     *     {@link Border }
     *     
     */
    public Border getBorder() {
        return border;
    }

    /**
     * Sets the value of the border property.
     * 
     * @param value
     *     allowed object is
     *     {@link Border }
     *     
     */
    public void setBorder(Border value) {
        this.border = value;
    }

    /**
     * Gets the value of the background property.
     * 
     * @return
     *     possible object is
     *     {@link Background }
     *     
     */
    public Background getBackground() {
        return background;
    }

    /**
     * Sets the value of the background property.
     * 
     * @param value
     *     allowed object is
     *     {@link Background }
     *     
     */
    public void setBackground(Background value) {
        this.background = value;
    }

    /**
     * Gets the value of the draftMode property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDraftMode() {
        return draftMode;
    }

    /**
     * Sets the value of the draftMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDraftMode(Boolean value) {
        this.draftMode = value;
    }

    /**
     * Gets the value of the shadow property.
     * 
     * @return
     *     possible object is
     *     {@link Shadow }
     *     
     */
    public Shadow getShadow() {
        return shadow;
    }

    /**
     * Sets the value of the shadow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shadow }
     *     
     */
    public void setShadow(Shadow value) {
        this.shadow = value;
    }

}

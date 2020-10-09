
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * EditorTrackingInfo co-class.
 * 
 * &lt;p&gt;Java class for EditorTrackingInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="EditorTrackingInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CreationDateFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CreatorFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EditDateFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EditorFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Realm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IsTimeInUTC" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EditorTrackingInfo", propOrder = {
    "creationDateFieldName",
    "creatorFieldName",
    "editDateFieldName",
    "editorFieldName",
    "realm",
    "timeReference",
    "isTimeInUTC"
})
public class EditorTrackingInfo {

    @XmlElement(name = "CreationDateFieldName")
    protected String creationDateFieldName;
    @XmlElement(name = "CreatorFieldName")
    protected String creatorFieldName;
    @XmlElement(name = "EditDateFieldName")
    protected String editDateFieldName;
    @XmlElement(name = "EditorFieldName")
    protected String editorFieldName;
    @XmlElement(name = "Realm")
    protected String realm;
    @XmlElement(name = "TimeReference")
    protected TimeReference timeReference;
    @XmlElement(name = "IsTimeInUTC")
    protected Boolean isTimeInUTC;

    /**
     * Gets the value of the creationDateFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDateFieldName() {
        return creationDateFieldName;
    }

    /**
     * Sets the value of the creationDateFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDateFieldName(String value) {
        this.creationDateFieldName = value;
    }

    /**
     * Gets the value of the creatorFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorFieldName() {
        return creatorFieldName;
    }

    /**
     * Sets the value of the creatorFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorFieldName(String value) {
        this.creatorFieldName = value;
    }

    /**
     * Gets the value of the editDateFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditDateFieldName() {
        return editDateFieldName;
    }

    /**
     * Sets the value of the editDateFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditDateFieldName(String value) {
        this.editDateFieldName = value;
    }

    /**
     * Gets the value of the editorFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditorFieldName() {
        return editorFieldName;
    }

    /**
     * Sets the value of the editorFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditorFieldName(String value) {
        this.editorFieldName = value;
    }

    /**
     * Gets the value of the realm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealm() {
        return realm;
    }

    /**
     * Sets the value of the realm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealm(String value) {
        this.realm = value;
    }

    /**
     * Gets the value of the timeReference property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getTimeReference() {
        return timeReference;
    }

    /**
     * Sets the value of the timeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setTimeReference(TimeReference value) {
        this.timeReference = value;
    }

    /**
     * Gets the value of the isTimeInUTC property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsTimeInUTC() {
        return isTimeInUTC;
    }

    /**
     * Sets the value of the isTimeInUTC property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsTimeInUTC(Boolean value) {
        this.isTimeInUTC = value;
    }

}

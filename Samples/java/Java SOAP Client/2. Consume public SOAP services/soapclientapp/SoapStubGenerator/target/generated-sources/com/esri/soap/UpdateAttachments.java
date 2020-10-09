
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
 *         &amp;lt;element name="LayerOrTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="AttachmentDataArray" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfAttachmentData"/&amp;gt;
 *         &amp;lt;element name="GdbVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RollbackOnFailure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
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
    "layerOrTableID",
    "attachmentDataArray",
    "gdbVersion",
    "rollbackOnFailure"
})
@XmlRootElement(name = "UpdateAttachments")
public class UpdateAttachments {

    @XmlElement(name = "LayerOrTableID")
    protected int layerOrTableID;
    @XmlElement(name = "AttachmentDataArray", required = true)
    protected ArrayOfAttachmentData attachmentDataArray;
    @XmlElement(name = "GdbVersion")
    protected String gdbVersion;
    @XmlElement(name = "RollbackOnFailure")
    protected Boolean rollbackOnFailure;

    /**
     * Gets the value of the layerOrTableID property.
     * 
     */
    public int getLayerOrTableID() {
        return layerOrTableID;
    }

    /**
     * Sets the value of the layerOrTableID property.
     * 
     */
    public void setLayerOrTableID(int value) {
        this.layerOrTableID = value;
    }

    /**
     * Gets the value of the attachmentDataArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAttachmentData }
     *     
     */
    public ArrayOfAttachmentData getAttachmentDataArray() {
        return attachmentDataArray;
    }

    /**
     * Sets the value of the attachmentDataArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAttachmentData }
     *     
     */
    public void setAttachmentDataArray(ArrayOfAttachmentData value) {
        this.attachmentDataArray = value;
    }

    /**
     * Gets the value of the gdbVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdbVersion() {
        return gdbVersion;
    }

    /**
     * Sets the value of the gdbVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdbVersion(String value) {
        this.gdbVersion = value;
    }

    /**
     * Gets the value of the rollbackOnFailure property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRollbackOnFailure() {
        return rollbackOnFailure;
    }

    /**
     * Sets the value of the rollbackOnFailure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRollbackOnFailure(Boolean value) {
        this.rollbackOnFailure = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Ownership-based feature-level Access Control.
 * 
 * &lt;p&gt;Java class for OwnershipBasedAccessControl complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="OwnershipBasedAccessControl"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="AllowOthersToDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AllowOthersToUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OwnershipBasedAccessControl", propOrder = {
    "enabled",
    "allowOthersToDelete",
    "allowOthersToUpdate"
})
public class OwnershipBasedAccessControl {

    @XmlElement(name = "Enabled")
    protected boolean enabled;
    @XmlElement(name = "AllowOthersToDelete")
    protected Boolean allowOthersToDelete;
    @XmlElement(name = "AllowOthersToUpdate")
    protected Boolean allowOthersToUpdate;

    /**
     * Gets the value of the enabled property.
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the allowOthersToDelete property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowOthersToDelete() {
        return allowOthersToDelete;
    }

    /**
     * Sets the value of the allowOthersToDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowOthersToDelete(Boolean value) {
        this.allowOthersToDelete = value;
    }

    /**
     * Gets the value of the allowOthersToUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowOthersToUpdate() {
        return allowOthersToUpdate;
    }

    /**
     * Sets the value of the allowOthersToUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowOthersToUpdate(Boolean value) {
        this.allowOthersToUpdate = value;
    }

}

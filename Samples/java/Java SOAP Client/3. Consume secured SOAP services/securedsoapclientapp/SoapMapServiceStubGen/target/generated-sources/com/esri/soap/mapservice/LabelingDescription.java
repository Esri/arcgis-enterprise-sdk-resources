
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LabelingDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LabelingDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LabelClassDescriptions" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}ArrayOfLabelClassDescription"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelingDescription", propOrder = {
    "labelClassDescriptions"
})
public class LabelingDescription {

    @XmlElement(name = "LabelClassDescriptions", required = true)
    protected ArrayOfLabelClassDescription labelClassDescriptions;

    /**
     * Gets the value of the labelClassDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLabelClassDescription }
     *     
     */
    public ArrayOfLabelClassDescription getLabelClassDescriptions() {
        return labelClassDescriptions;
    }

    /**
     * Sets the value of the labelClassDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLabelClassDescription }
     *     
     */
    public void setLabelClassDescriptions(ArrayOfLabelClassDescription value) {
        this.labelClassDescriptions = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri Attribute set constraint object.
 * 
 * &lt;p&gt;Java class for CodedValueDomain complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CodedValueDomain"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Domain"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CodedValues" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfCodedValue"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodedValueDomain", propOrder = {
    "codedValues"
})
@XmlSeeAlso({
    BitMaskCodedValueDomain.class
})
public class CodedValueDomain
    extends Domain
{

    @XmlElement(name = "CodedValues", required = true)
    protected ArrayOfCodedValue codedValues;

    /**
     * Gets the value of the codedValues property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCodedValue }
     *     
     */
    public ArrayOfCodedValue getCodedValues() {
        return codedValues;
    }

    /**
     * Sets the value of the codedValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCodedValue }
     *     
     */
    public void setCodedValues(ArrayOfCodedValue value) {
        this.codedValues = value;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MultiPartColorRamp complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MultiPartColorRamp"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}ColorRamp"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="NumColorRamps" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ColorRamps" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfColorRamp" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiPartColorRamp", propOrder = {
    "numColorRamps",
    "colorRamps"
})
public class MultiPartColorRamp
    extends ColorRamp
{

    @XmlElement(name = "NumColorRamps")
    protected Integer numColorRamps;
    @XmlElement(name = "ColorRamps")
    protected ArrayOfColorRamp colorRamps;

    /**
     * Gets the value of the numColorRamps property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumColorRamps() {
        return numColorRamps;
    }

    /**
     * Sets the value of the numColorRamps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumColorRamps(Integer value) {
        this.numColorRamps = value;
    }

    /**
     * Gets the value of the colorRamps property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfColorRamp }
     *     
     */
    public ArrayOfColorRamp getColorRamps() {
        return colorRamps;
    }

    /**
     * Sets the value of the colorRamps property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfColorRamp }
     *     
     */
    public void setColorRamps(ArrayOfColorRamp value) {
        this.colorRamps = value;
    }

}

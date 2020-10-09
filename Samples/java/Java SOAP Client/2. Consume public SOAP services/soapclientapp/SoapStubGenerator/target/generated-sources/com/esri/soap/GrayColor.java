
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for GrayColor complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="GrayColor"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Color"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="GrayLevel" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GrayColor", propOrder = {
    "grayLevel"
})
public class GrayColor
    extends Color
{

    @XmlElement(name = "GrayLevel")
    @XmlSchemaType(name = "unsignedByte")
    protected short grayLevel;

    /**
     * Gets the value of the grayLevel property.
     * 
     */
    public short getGrayLevel() {
        return grayLevel;
    }

    /**
     * Sets the value of the grayLevel property.
     * 
     */
    public void setGrayLevel(short value) {
        this.grayLevel = value;
    }

}

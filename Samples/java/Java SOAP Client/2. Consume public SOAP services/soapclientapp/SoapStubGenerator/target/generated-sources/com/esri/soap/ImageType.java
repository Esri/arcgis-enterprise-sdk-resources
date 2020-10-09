
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ImageType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ImageType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ImageFormat" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriImageFormat"/&amp;gt;
 *         &amp;lt;element name="ImageReturnType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriImageReturnType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageType", propOrder = {
    "imageFormat",
    "imageReturnType"
})
public class ImageType {

    @XmlElement(name = "ImageFormat", required = true)
    @XmlSchemaType(name = "string")
    protected EsriImageFormat imageFormat;
    @XmlElement(name = "ImageReturnType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriImageReturnType imageReturnType;

    /**
     * Gets the value of the imageFormat property.
     * 
     * @return
     *     possible object is
     *     {@link EsriImageFormat }
     *     
     */
    public EsriImageFormat getImageFormat() {
        return imageFormat;
    }

    /**
     * Sets the value of the imageFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriImageFormat }
     *     
     */
    public void setImageFormat(EsriImageFormat value) {
        this.imageFormat = value;
    }

    /**
     * Gets the value of the imageReturnType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriImageReturnType }
     *     
     */
    public EsriImageReturnType getImageReturnType() {
        return imageReturnType;
    }

    /**
     * Sets the value of the imageReturnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriImageReturnType }
     *     
     */
    public void setImageReturnType(EsriImageReturnType value) {
        this.imageReturnType = value;
    }

}

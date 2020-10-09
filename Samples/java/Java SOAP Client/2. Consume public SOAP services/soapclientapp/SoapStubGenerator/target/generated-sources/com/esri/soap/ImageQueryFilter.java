
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Esri Image Query Filter Object.
 * 
 * &lt;p&gt;Java class for ImageQueryFilter complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ImageQueryFilter"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}TimeQueryFilter"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="PixelSize" type="{http://www.esri.com/schemas/ArcGIS/10.7}Point"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageQueryFilter", propOrder = {
    "pixelSize"
})
public class ImageQueryFilter
    extends TimeQueryFilter
{

    @XmlElement(name = "PixelSize", required = true)
    protected Point pixelSize;

    /**
     * Gets the value of the pixelSize property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getPixelSize() {
        return pixelSize;
    }

    /**
     * Sets the value of the pixelSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setPixelSize(Point value) {
        this.pixelSize = value;
    }

}

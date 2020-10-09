
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SimpleLineSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SimpleLineSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}LineSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Style" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSimpleLineStyle"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleLineSymbol", propOrder = {
    "style"
})
public class SimpleLineSymbol
    extends LineSymbol
{

    @XmlElement(name = "Style", required = true)
    @XmlSchemaType(name = "string")
    protected EsriSimpleLineStyle style;

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSimpleLineStyle }
     *     
     */
    public EsriSimpleLineStyle getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSimpleLineStyle }
     *     
     */
    public void setStyle(EsriSimpleLineStyle value) {
        this.style = value;
    }

}

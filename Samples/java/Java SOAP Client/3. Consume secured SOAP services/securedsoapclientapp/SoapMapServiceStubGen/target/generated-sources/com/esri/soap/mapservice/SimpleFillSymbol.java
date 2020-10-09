
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SimpleFillSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SimpleFillSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}FillSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Style" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriSimpleFillStyle"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleFillSymbol", propOrder = {
    "style"
})
public class SimpleFillSymbol
    extends FillSymbol
{

    @XmlElement(name = "Style", required = true)
    @XmlSchemaType(name = "string")
    protected EsriSimpleFillStyle style;

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link EsriSimpleFillStyle }
     *     
     */
    public EsriSimpleFillStyle getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriSimpleFillStyle }
     *     
     */
    public void setStyle(EsriSimpleFillStyle value) {
        this.style = value;
    }

}

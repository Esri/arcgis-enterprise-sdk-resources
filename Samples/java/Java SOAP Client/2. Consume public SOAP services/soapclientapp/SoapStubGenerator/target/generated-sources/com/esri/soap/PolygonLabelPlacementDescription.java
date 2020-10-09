
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PolygonLabelPlacementDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PolygonLabelPlacementDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}LabelPlacementDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Type" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriServerPolygonLabelPlacementType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonLabelPlacementDescription", propOrder = {
    "type"
})
public class PolygonLabelPlacementDescription
    extends LabelPlacementDescription
{

    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected EsriServerPolygonLabelPlacementType type;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EsriServerPolygonLabelPlacementType }
     *     
     */
    public EsriServerPolygonLabelPlacementType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriServerPolygonLabelPlacementType }
     *     
     */
    public void setType(EsriServerPolygonLabelPlacementType value) {
        this.type = value;
    }

}

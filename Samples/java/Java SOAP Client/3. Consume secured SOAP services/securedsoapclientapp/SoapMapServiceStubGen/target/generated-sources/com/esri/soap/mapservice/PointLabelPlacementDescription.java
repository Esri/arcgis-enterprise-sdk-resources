
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PointLabelPlacementDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PointLabelPlacementDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}LabelPlacementDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Type" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriServerPointLabelPlacementType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointLabelPlacementDescription", propOrder = {
    "type"
})
public class PointLabelPlacementDescription
    extends LabelPlacementDescription
{

    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected EsriServerPointLabelPlacementType type;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EsriServerPointLabelPlacementType }
     *     
     */
    public EsriServerPointLabelPlacementType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriServerPointLabelPlacementType }
     *     
     */
    public void setType(EsriServerPointLabelPlacementType value) {
        this.type = value;
    }

}

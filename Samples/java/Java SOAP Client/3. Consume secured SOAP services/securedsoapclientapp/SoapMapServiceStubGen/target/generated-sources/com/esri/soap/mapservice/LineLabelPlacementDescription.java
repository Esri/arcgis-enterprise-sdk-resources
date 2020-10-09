
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LineLabelPlacementDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LineLabelPlacementDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}LabelPlacementDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Type" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}esriServerLineLabelPlacementType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineLabelPlacementDescription", propOrder = {
    "type"
})
public class LineLabelPlacementDescription
    extends LabelPlacementDescription
{

    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected EsriServerLineLabelPlacementType type;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EsriServerLineLabelPlacementType }
     *     
     */
    public EsriServerLineLabelPlacementType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriServerLineLabelPlacementType }
     *     
     */
    public void setType(EsriServerLineLabelPlacementType value) {
        this.type = value;
    }

}

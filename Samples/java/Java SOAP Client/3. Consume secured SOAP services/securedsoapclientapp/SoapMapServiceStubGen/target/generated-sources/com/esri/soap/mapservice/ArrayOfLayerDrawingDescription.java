
package com.esri.soap.mapservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ArrayOfLayerDrawingDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ArrayOfLayerDrawingDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LayerDrawingDescription" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}LayerDrawingDescription" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLayerDrawingDescription", propOrder = {
    "layerDrawingDescription"
})
public class ArrayOfLayerDrawingDescription {

    @XmlElement(name = "LayerDrawingDescription")
    protected List<LayerDrawingDescription> layerDrawingDescription;

    /**
     * Gets the value of the layerDrawingDescription property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the layerDrawingDescription property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getLayerDrawingDescription().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link LayerDrawingDescription }
     * 
     * 
     */
    public List<LayerDrawingDescription> getLayerDrawingDescription() {
        if (layerDrawingDescription == null) {
            layerDrawingDescription = new ArrayList<LayerDrawingDescription>();
        }
        return this.layerDrawingDescription;
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TableEdit complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TableEdit"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Adds" type="{http://www.esri.com/schemas/ArcGIS/10.7}DataObjects"/&amp;gt;
 *         &amp;lt;element name="Deletes" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt"/&amp;gt;
 *         &amp;lt;element name="LayerOrTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Updates" type="{http://www.esri.com/schemas/ArcGIS/10.7}DataObjects"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableEdit", propOrder = {
    "adds",
    "deletes",
    "layerOrTableID",
    "updates"
})
public class TableEdit {

    @XmlElement(name = "Adds", required = true)
    protected DataObjects adds;
    @XmlElement(name = "Deletes", required = true)
    protected ArrayOfInt deletes;
    @XmlElement(name = "LayerOrTableID")
    protected int layerOrTableID;
    @XmlElement(name = "Updates", required = true)
    protected DataObjects updates;

    /**
     * Gets the value of the adds property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjects }
     *     
     */
    public DataObjects getAdds() {
        return adds;
    }

    /**
     * Sets the value of the adds property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjects }
     *     
     */
    public void setAdds(DataObjects value) {
        this.adds = value;
    }

    /**
     * Gets the value of the deletes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getDeletes() {
        return deletes;
    }

    /**
     * Sets the value of the deletes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setDeletes(ArrayOfInt value) {
        this.deletes = value;
    }

    /**
     * Gets the value of the layerOrTableID property.
     * 
     */
    public int getLayerOrTableID() {
        return layerOrTableID;
    }

    /**
     * Sets the value of the layerOrTableID property.
     * 
     */
    public void setLayerOrTableID(int value) {
        this.layerOrTableID = value;
    }

    /**
     * Gets the value of the updates property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjects }
     *     
     */
    public DataObjects getUpdates() {
        return updates;
    }

    /**
     * Sets the value of the updates property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjects }
     *     
     */
    public void setUpdates(DataObjects value) {
        this.updates = value;
    }

}

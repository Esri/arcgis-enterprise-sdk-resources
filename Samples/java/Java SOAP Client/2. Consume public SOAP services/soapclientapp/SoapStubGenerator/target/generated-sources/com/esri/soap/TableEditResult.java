
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TableEditResult complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TableEditResult"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="AddResults" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfEditResult"/&amp;gt;
 *         &amp;lt;element name="DeleteResults" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfEditResult"/&amp;gt;
 *         &amp;lt;element name="LayerOrTableID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="UpdateResults" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfEditResult"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableEditResult", propOrder = {
    "addResults",
    "deleteResults",
    "layerOrTableID",
    "updateResults"
})
public class TableEditResult {

    @XmlElement(name = "AddResults", required = true)
    protected ArrayOfEditResult addResults;
    @XmlElement(name = "DeleteResults", required = true)
    protected ArrayOfEditResult deleteResults;
    @XmlElement(name = "LayerOrTableID")
    protected int layerOrTableID;
    @XmlElement(name = "UpdateResults", required = true)
    protected ArrayOfEditResult updateResults;

    /**
     * Gets the value of the addResults property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEditResult }
     *     
     */
    public ArrayOfEditResult getAddResults() {
        return addResults;
    }

    /**
     * Sets the value of the addResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEditResult }
     *     
     */
    public void setAddResults(ArrayOfEditResult value) {
        this.addResults = value;
    }

    /**
     * Gets the value of the deleteResults property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEditResult }
     *     
     */
    public ArrayOfEditResult getDeleteResults() {
        return deleteResults;
    }

    /**
     * Sets the value of the deleteResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEditResult }
     *     
     */
    public void setDeleteResults(ArrayOfEditResult value) {
        this.deleteResults = value;
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
     * Gets the value of the updateResults property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEditResult }
     *     
     */
    public ArrayOfEditResult getUpdateResults() {
        return updateResults;
    }

    /**
     * Sets the value of the updateResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEditResult }
     *     
     */
    public void setUpdateResults(ArrayOfEditResult value) {
        this.updateResults = value;
    }

}

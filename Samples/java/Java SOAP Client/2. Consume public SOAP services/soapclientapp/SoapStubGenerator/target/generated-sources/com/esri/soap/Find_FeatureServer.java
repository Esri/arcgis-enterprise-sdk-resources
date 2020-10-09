
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="MapDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}MapDescription"/&amp;gt;
 *         &amp;lt;element name="MapImageDisplay" type="{http://www.esri.com/schemas/ArcGIS/10.7}ImageDisplay"/&amp;gt;
 *         &amp;lt;element name="SearchString" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Contains" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="SearchFields" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="FindOption" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriFindOption"/&amp;gt;
 *         &amp;lt;element name="LayerIDs" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfInt"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mapDescription",
    "mapImageDisplay",
    "searchString",
    "contains",
    "searchFields",
    "findOption",
    "layerIDs"
})
@XmlRootElement(name = "Find")
public class Find_FeatureServer {

    @XmlElement(name = "MapDescription", required = true)
    protected MapDescription mapDescription;
    @XmlElement(name = "MapImageDisplay", required = true)
    protected ImageDisplay mapImageDisplay;
    @XmlElement(name = "SearchString", required = true)
    protected String searchString;
    @XmlElement(name = "Contains")
    protected boolean contains;
    @XmlElement(name = "SearchFields", required = true)
    protected String searchFields;
    @XmlElement(name = "FindOption", required = true)
    @XmlSchemaType(name = "string")
    protected EsriFindOption findOption;
    @XmlElement(name = "LayerIDs", required = true)
    protected ArrayOfInt layerIDs;

    /**
     * Gets the value of the mapDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MapDescription }
     *     
     */
    public MapDescription getMapDescription() {
        return mapDescription;
    }

    /**
     * Sets the value of the mapDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapDescription }
     *     
     */
    public void setMapDescription(MapDescription value) {
        this.mapDescription = value;
    }

    /**
     * Gets the value of the mapImageDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link ImageDisplay }
     *     
     */
    public ImageDisplay getMapImageDisplay() {
        return mapImageDisplay;
    }

    /**
     * Sets the value of the mapImageDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageDisplay }
     *     
     */
    public void setMapImageDisplay(ImageDisplay value) {
        this.mapImageDisplay = value;
    }

    /**
     * Gets the value of the searchString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * Sets the value of the searchString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchString(String value) {
        this.searchString = value;
    }

    /**
     * Gets the value of the contains property.
     * 
     */
    public boolean isContains() {
        return contains;
    }

    /**
     * Sets the value of the contains property.
     * 
     */
    public void setContains(boolean value) {
        this.contains = value;
    }

    /**
     * Gets the value of the searchFields property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchFields() {
        return searchFields;
    }

    /**
     * Sets the value of the searchFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchFields(String value) {
        this.searchFields = value;
    }

    /**
     * Gets the value of the findOption property.
     * 
     * @return
     *     possible object is
     *     {@link EsriFindOption }
     *     
     */
    public EsriFindOption getFindOption() {
        return findOption;
    }

    /**
     * Sets the value of the findOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriFindOption }
     *     
     */
    public void setFindOption(EsriFindOption value) {
        this.findOption = value;
    }

    /**
     * Gets the value of the layerIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getLayerIDs() {
        return layerIDs;
    }

    /**
     * Sets the value of the layerIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setLayerIDs(ArrayOfInt value) {
        this.layerIDs = value;
    }

}

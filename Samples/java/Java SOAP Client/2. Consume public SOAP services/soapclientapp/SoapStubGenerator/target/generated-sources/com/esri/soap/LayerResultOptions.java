
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LayerResultOptions complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LayerResultOptions"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="IncludeGeometry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GeometryResultOptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}GeometryResultOptions" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ReturnFieldNamesInResults" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FormatValuesInResults" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerResultOptions", propOrder = {
    "includeGeometry",
    "geometryResultOptions",
    "returnFieldNamesInResults",
    "formatValuesInResults"
})
public class LayerResultOptions {

    @XmlElement(name = "IncludeGeometry", defaultValue = "true")
    protected Boolean includeGeometry;
    @XmlElement(name = "GeometryResultOptions")
    protected GeometryResultOptions geometryResultOptions;
    @XmlElement(name = "ReturnFieldNamesInResults", defaultValue = "false")
    protected Boolean returnFieldNamesInResults;
    @XmlElement(name = "FormatValuesInResults", defaultValue = "true")
    protected Boolean formatValuesInResults;

    /**
     * Gets the value of the includeGeometry property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeGeometry() {
        return includeGeometry;
    }

    /**
     * Sets the value of the includeGeometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeGeometry(Boolean value) {
        this.includeGeometry = value;
    }

    /**
     * Gets the value of the geometryResultOptions property.
     * 
     * @return
     *     possible object is
     *     {@link GeometryResultOptions }
     *     
     */
    public GeometryResultOptions getGeometryResultOptions() {
        return geometryResultOptions;
    }

    /**
     * Sets the value of the geometryResultOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryResultOptions }
     *     
     */
    public void setGeometryResultOptions(GeometryResultOptions value) {
        this.geometryResultOptions = value;
    }

    /**
     * Gets the value of the returnFieldNamesInResults property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnFieldNamesInResults() {
        return returnFieldNamesInResults;
    }

    /**
     * Sets the value of the returnFieldNamesInResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnFieldNamesInResults(Boolean value) {
        this.returnFieldNamesInResults = value;
    }

    /**
     * Gets the value of the formatValuesInResults property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFormatValuesInResults() {
        return formatValuesInResults;
    }

    /**
     * Sets the value of the formatValuesInResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFormatValuesInResults(Boolean value) {
        this.formatValuesInResults = value;
    }

}

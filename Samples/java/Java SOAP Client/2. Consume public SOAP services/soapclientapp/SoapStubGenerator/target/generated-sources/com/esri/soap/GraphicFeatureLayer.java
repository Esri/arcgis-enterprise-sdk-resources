
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for GraphicFeatureLayer complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="GraphicFeatureLayer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}DataObjectTable"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="GeometryFieldName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="GeometryType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriGeometryType"/&amp;gt;
 *         &amp;lt;element name="LayerDrawingDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}FeatureLayerDrawingDescription"/&amp;gt;
 *         &amp;lt;element name="MaxScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="MinScale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="SpatialExtent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference"/&amp;gt;
 *         &amp;lt;element name="HasM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HasZ" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GraphicFeatureLayer", propOrder = {
    "geometryFieldName",
    "geometryType",
    "layerDrawingDescription",
    "maxScale",
    "minScale",
    "spatialExtent",
    "spatialReference",
    "hasM",
    "hasZ",
    "visible"
})
public class GraphicFeatureLayer
    extends DataObjectTable
{

    @XmlElement(name = "GeometryFieldName", required = true)
    protected String geometryFieldName;
    @XmlElement(name = "GeometryType", required = true)
    @XmlSchemaType(name = "string")
    protected EsriGeometryType geometryType;
    @XmlElement(name = "LayerDrawingDescription", required = true)
    protected FeatureLayerDrawingDescription layerDrawingDescription;
    @XmlElement(name = "MaxScale")
    protected double maxScale;
    @XmlElement(name = "MinScale")
    protected double minScale;
    @XmlElement(name = "SpatialExtent", required = true)
    protected Envelope spatialExtent;
    @XmlElement(name = "SpatialReference", required = true)
    protected SpatialReference spatialReference;
    @XmlElement(name = "HasM")
    protected Boolean hasM;
    @XmlElement(name = "HasZ")
    protected Boolean hasZ;
    @XmlElement(name = "Visible")
    protected Boolean visible;

    /**
     * Gets the value of the geometryFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeometryFieldName() {
        return geometryFieldName;
    }

    /**
     * Sets the value of the geometryFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeometryFieldName(String value) {
        this.geometryFieldName = value;
    }

    /**
     * Gets the value of the geometryType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriGeometryType }
     *     
     */
    public EsriGeometryType getGeometryType() {
        return geometryType;
    }

    /**
     * Sets the value of the geometryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriGeometryType }
     *     
     */
    public void setGeometryType(EsriGeometryType value) {
        this.geometryType = value;
    }

    /**
     * Gets the value of the layerDrawingDescription property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureLayerDrawingDescription }
     *     
     */
    public FeatureLayerDrawingDescription getLayerDrawingDescription() {
        return layerDrawingDescription;
    }

    /**
     * Sets the value of the layerDrawingDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureLayerDrawingDescription }
     *     
     */
    public void setLayerDrawingDescription(FeatureLayerDrawingDescription value) {
        this.layerDrawingDescription = value;
    }

    /**
     * Gets the value of the maxScale property.
     * 
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * Sets the value of the maxScale property.
     * 
     */
    public void setMaxScale(double value) {
        this.maxScale = value;
    }

    /**
     * Gets the value of the minScale property.
     * 
     */
    public double getMinScale() {
        return minScale;
    }

    /**
     * Sets the value of the minScale property.
     * 
     */
    public void setMinScale(double value) {
        this.minScale = value;
    }

    /**
     * Gets the value of the spatialExtent property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getSpatialExtent() {
        return spatialExtent;
    }

    /**
     * Sets the value of the spatialExtent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setSpatialExtent(Envelope value) {
        this.spatialExtent = value;
    }

    /**
     * Gets the value of the spatialReference property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialReference }
     *     
     */
    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    /**
     * Sets the value of the spatialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialReference }
     *     
     */
    public void setSpatialReference(SpatialReference value) {
        this.spatialReference = value;
    }

    /**
     * Gets the value of the hasM property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasM() {
        return hasM;
    }

    /**
     * Sets the value of the hasM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasM(Boolean value) {
        this.hasM = value;
    }

    /**
     * Gets the value of the hasZ property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasZ() {
        return hasZ;
    }

    /**
     * Sets the value of the hasZ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasZ(Boolean value) {
        this.hasZ = value;
    }

    /**
     * Gets the value of the visible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisible(Boolean value) {
        this.visible = value;
    }

}

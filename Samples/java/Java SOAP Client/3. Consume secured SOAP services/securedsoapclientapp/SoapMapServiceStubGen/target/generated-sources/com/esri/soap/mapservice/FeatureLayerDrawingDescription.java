
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for FeatureLayerDrawingDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FeatureLayerDrawingDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}LayerDrawingDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="FeatureRenderer" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}FeatureRenderer" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ScaleSymbols" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Transparency" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Brightness" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Contrast" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LabelingDescription" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}LabelingDescription" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SourceLayerID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureLayerDrawingDescription", propOrder = {
    "featureRenderer",
    "scaleSymbols",
    "transparency",
    "brightness",
    "contrast",
    "labelingDescription",
    "sourceLayerID"
})
public class FeatureLayerDrawingDescription
    extends LayerDrawingDescription
{

    @XmlElement(name = "FeatureRenderer")
    protected FeatureRenderer featureRenderer;
    @XmlElement(name = "ScaleSymbols")
    protected Boolean scaleSymbols;
    @XmlElement(name = "Transparency")
    protected Short transparency;
    @XmlElement(name = "Brightness")
    protected Short brightness;
    @XmlElement(name = "Contrast")
    protected Short contrast;
    @XmlElement(name = "LabelingDescription")
    protected LabelingDescription labelingDescription;
    @XmlElement(name = "SourceLayerID")
    protected Integer sourceLayerID;

    /**
     * Gets the value of the featureRenderer property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureRenderer }
     *     
     */
    public FeatureRenderer getFeatureRenderer() {
        return featureRenderer;
    }

    /**
     * Sets the value of the featureRenderer property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureRenderer }
     *     
     */
    public void setFeatureRenderer(FeatureRenderer value) {
        this.featureRenderer = value;
    }

    /**
     * Gets the value of the scaleSymbols property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isScaleSymbols() {
        return scaleSymbols;
    }

    /**
     * Sets the value of the scaleSymbols property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setScaleSymbols(Boolean value) {
        this.scaleSymbols = value;
    }

    /**
     * Gets the value of the transparency property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTransparency() {
        return transparency;
    }

    /**
     * Sets the value of the transparency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTransparency(Short value) {
        this.transparency = value;
    }

    /**
     * Gets the value of the brightness property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBrightness() {
        return brightness;
    }

    /**
     * Sets the value of the brightness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBrightness(Short value) {
        this.brightness = value;
    }

    /**
     * Gets the value of the contrast property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getContrast() {
        return contrast;
    }

    /**
     * Sets the value of the contrast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setContrast(Short value) {
        this.contrast = value;
    }

    /**
     * Gets the value of the labelingDescription property.
     * 
     * @return
     *     possible object is
     *     {@link LabelingDescription }
     *     
     */
    public LabelingDescription getLabelingDescription() {
        return labelingDescription;
    }

    /**
     * Sets the value of the labelingDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelingDescription }
     *     
     */
    public void setLabelingDescription(LabelingDescription value) {
        this.labelingDescription = value;
    }

    /**
     * Gets the value of the sourceLayerID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSourceLayerID() {
        return sourceLayerID;
    }

    /**
     * Sets the value of the sourceLayerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSourceLayerID(Integer value) {
        this.sourceLayerID = value;
    }

}

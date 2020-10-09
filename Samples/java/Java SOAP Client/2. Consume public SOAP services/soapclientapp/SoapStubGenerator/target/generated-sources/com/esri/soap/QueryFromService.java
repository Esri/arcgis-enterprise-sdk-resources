
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &amp;lt;element name="LayerOrTableDescriptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfGFSTableDescription"/&amp;gt;
 *         &amp;lt;element name="Geometry" type="{http://www.esri.com/schemas/ArcGIS/10.7}Geometry" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OutSR" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OutTR" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ServiceDataOptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ServiceDataOptions"/&amp;gt;
 *         &amp;lt;element name="GdbVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaximumAllowableOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
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
    "layerOrTableDescriptions",
    "geometry",
    "outSR",
    "outTR",
    "serviceDataOptions",
    "gdbVersion",
    "maximumAllowableOffset"
})
@XmlRootElement(name = "QueryFromService")
public class QueryFromService {

    @XmlElement(name = "LayerOrTableDescriptions", required = true)
    protected ArrayOfGFSTableDescription layerOrTableDescriptions;
    @XmlElement(name = "Geometry")
    protected Geometry geometry;
    @XmlElement(name = "OutSR")
    protected SpatialReference outSR;
    @XmlElement(name = "OutTR")
    protected TimeReference outTR;
    @XmlElement(name = "ServiceDataOptions", required = true)
    protected ServiceDataOptions serviceDataOptions;
    @XmlElement(name = "GdbVersion")
    protected String gdbVersion;
    @XmlElement(name = "MaximumAllowableOffset", defaultValue = "0.0")
    protected Double maximumAllowableOffset;

    /**
     * Gets the value of the layerOrTableDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGFSTableDescription }
     *     
     */
    public ArrayOfGFSTableDescription getLayerOrTableDescriptions() {
        return layerOrTableDescriptions;
    }

    /**
     * Sets the value of the layerOrTableDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGFSTableDescription }
     *     
     */
    public void setLayerOrTableDescriptions(ArrayOfGFSTableDescription value) {
        this.layerOrTableDescriptions = value;
    }

    /**
     * Gets the value of the geometry property.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Sets the value of the geometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setGeometry(Geometry value) {
        this.geometry = value;
    }

    /**
     * Gets the value of the outSR property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialReference }
     *     
     */
    public SpatialReference getOutSR() {
        return outSR;
    }

    /**
     * Sets the value of the outSR property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialReference }
     *     
     */
    public void setOutSR(SpatialReference value) {
        this.outSR = value;
    }

    /**
     * Gets the value of the outTR property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getOutTR() {
        return outTR;
    }

    /**
     * Sets the value of the outTR property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setOutTR(TimeReference value) {
        this.outTR = value;
    }

    /**
     * Gets the value of the serviceDataOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDataOptions }
     *     
     */
    public ServiceDataOptions getServiceDataOptions() {
        return serviceDataOptions;
    }

    /**
     * Sets the value of the serviceDataOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDataOptions }
     *     
     */
    public void setServiceDataOptions(ServiceDataOptions value) {
        this.serviceDataOptions = value;
    }

    /**
     * Gets the value of the gdbVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdbVersion() {
        return gdbVersion;
    }

    /**
     * Sets the value of the gdbVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdbVersion(String value) {
        this.gdbVersion = value;
    }

    /**
     * Gets the value of the maximumAllowableOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumAllowableOffset() {
        return maximumAllowableOffset;
    }

    /**
     * Sets the value of the maximumAllowableOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumAllowableOffset(Double value) {
        this.maximumAllowableOffset = value;
    }

}

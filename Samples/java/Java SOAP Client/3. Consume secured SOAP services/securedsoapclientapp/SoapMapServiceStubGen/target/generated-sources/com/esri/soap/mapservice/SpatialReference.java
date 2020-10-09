
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SpatialReference complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SpatialReference"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="WKT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XOrigin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="YOrigin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XYScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZOrigin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MOrigin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="XYTolerance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ZTolerance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MTolerance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="HighPrecision" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LeftLongitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WKID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LatestWKID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="VCSWKID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LatestVCSWKID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpatialReference", propOrder = {
    "wkt",
    "xOrigin",
    "yOrigin",
    "xyScale",
    "zOrigin",
    "zScale",
    "mOrigin",
    "mScale",
    "xyTolerance",
    "zTolerance",
    "mTolerance",
    "highPrecision",
    "leftLongitude",
    "wkid",
    "latestWKID",
    "vcswkid",
    "latestVCSWKID"
})
@XmlSeeAlso({
    ProjectedCoordinateSystem.class,
    GeographicCoordinateSystem.class,
    UnknownCoordinateSystem.class
})
public abstract class SpatialReference {

    @XmlElement(name = "WKT")
    protected String wkt;
    @XmlElement(name = "XOrigin")
    protected Double xOrigin;
    @XmlElement(name = "YOrigin")
    protected Double yOrigin;
    @XmlElement(name = "XYScale")
    protected Double xyScale;
    @XmlElement(name = "ZOrigin")
    protected Double zOrigin;
    @XmlElement(name = "ZScale")
    protected Double zScale;
    @XmlElement(name = "MOrigin")
    protected Double mOrigin;
    @XmlElement(name = "MScale")
    protected Double mScale;
    @XmlElement(name = "XYTolerance")
    protected Double xyTolerance;
    @XmlElement(name = "ZTolerance")
    protected Double zTolerance;
    @XmlElement(name = "MTolerance")
    protected Double mTolerance;
    @XmlElement(name = "HighPrecision")
    protected Boolean highPrecision;
    @XmlElement(name = "LeftLongitude")
    protected Double leftLongitude;
    @XmlElement(name = "WKID")
    protected Integer wkid;
    @XmlElement(name = "LatestWKID")
    protected Integer latestWKID;
    @XmlElement(name = "VCSWKID")
    protected Integer vcswkid;
    @XmlElement(name = "LatestVCSWKID")
    protected Integer latestVCSWKID;

    /**
     * Gets the value of the wkt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWKT() {
        return wkt;
    }

    /**
     * Sets the value of the wkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWKT(String value) {
        this.wkt = value;
    }

    /**
     * Gets the value of the xOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXOrigin() {
        return xOrigin;
    }

    /**
     * Sets the value of the xOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXOrigin(Double value) {
        this.xOrigin = value;
    }

    /**
     * Gets the value of the yOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYOrigin() {
        return yOrigin;
    }

    /**
     * Sets the value of the yOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYOrigin(Double value) {
        this.yOrigin = value;
    }

    /**
     * Gets the value of the xyScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXYScale() {
        return xyScale;
    }

    /**
     * Sets the value of the xyScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXYScale(Double value) {
        this.xyScale = value;
    }

    /**
     * Gets the value of the zOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZOrigin() {
        return zOrigin;
    }

    /**
     * Sets the value of the zOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZOrigin(Double value) {
        this.zOrigin = value;
    }

    /**
     * Gets the value of the zScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZScale() {
        return zScale;
    }

    /**
     * Sets the value of the zScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZScale(Double value) {
        this.zScale = value;
    }

    /**
     * Gets the value of the mOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMOrigin() {
        return mOrigin;
    }

    /**
     * Sets the value of the mOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMOrigin(Double value) {
        this.mOrigin = value;
    }

    /**
     * Gets the value of the mScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMScale() {
        return mScale;
    }

    /**
     * Sets the value of the mScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMScale(Double value) {
        this.mScale = value;
    }

    /**
     * Gets the value of the xyTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXYTolerance() {
        return xyTolerance;
    }

    /**
     * Sets the value of the xyTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXYTolerance(Double value) {
        this.xyTolerance = value;
    }

    /**
     * Gets the value of the zTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getZTolerance() {
        return zTolerance;
    }

    /**
     * Sets the value of the zTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setZTolerance(Double value) {
        this.zTolerance = value;
    }

    /**
     * Gets the value of the mTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMTolerance() {
        return mTolerance;
    }

    /**
     * Sets the value of the mTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMTolerance(Double value) {
        this.mTolerance = value;
    }

    /**
     * Gets the value of the highPrecision property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHighPrecision() {
        return highPrecision;
    }

    /**
     * Sets the value of the highPrecision property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHighPrecision(Boolean value) {
        this.highPrecision = value;
    }

    /**
     * Gets the value of the leftLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLeftLongitude() {
        return leftLongitude;
    }

    /**
     * Sets the value of the leftLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLeftLongitude(Double value) {
        this.leftLongitude = value;
    }

    /**
     * Gets the value of the wkid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWKID() {
        return wkid;
    }

    /**
     * Sets the value of the wkid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWKID(Integer value) {
        this.wkid = value;
    }

    /**
     * Gets the value of the latestWKID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatestWKID() {
        return latestWKID;
    }

    /**
     * Sets the value of the latestWKID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatestWKID(Integer value) {
        this.latestWKID = value;
    }

    /**
     * Gets the value of the vcswkid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVCSWKID() {
        return vcswkid;
    }

    /**
     * Sets the value of the vcswkid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVCSWKID(Integer value) {
        this.vcswkid = value;
    }

    /**
     * Gets the value of the latestVCSWKID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatestVCSWKID() {
        return latestVCSWKID;
    }

    /**
     * Sets the value of the latestVCSWKID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatestVCSWKID(Integer value) {
        this.latestVCSWKID = value;
    }

}

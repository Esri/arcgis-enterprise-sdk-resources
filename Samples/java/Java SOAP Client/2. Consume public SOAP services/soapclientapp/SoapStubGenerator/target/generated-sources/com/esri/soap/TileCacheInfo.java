
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TileCacheInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TileCacheInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialReference" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TileOrigin" type="{http://www.esri.com/schemas/ArcGIS/10.7}Point" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TileCols" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="TileRows" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="DPI" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="LODInfos" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfLODInfo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PreciseDPI" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileCacheInfo", propOrder = {
    "spatialReference",
    "tileOrigin",
    "tileCols",
    "tileRows",
    "dpi",
    "lodInfos",
    "preciseDPI"
})
public class TileCacheInfo {

    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;
    @XmlElement(name = "TileOrigin")
    protected Point tileOrigin;
    @XmlElement(name = "TileCols")
    protected int tileCols;
    @XmlElement(name = "TileRows")
    protected int tileRows;
    @XmlElement(name = "DPI")
    protected int dpi;
    @XmlElement(name = "LODInfos")
    protected ArrayOfLODInfo lodInfos;
    @XmlElement(name = "PreciseDPI")
    protected Double preciseDPI;

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
     * Gets the value of the tileOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getTileOrigin() {
        return tileOrigin;
    }

    /**
     * Sets the value of the tileOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setTileOrigin(Point value) {
        this.tileOrigin = value;
    }

    /**
     * Gets the value of the tileCols property.
     * 
     */
    public int getTileCols() {
        return tileCols;
    }

    /**
     * Sets the value of the tileCols property.
     * 
     */
    public void setTileCols(int value) {
        this.tileCols = value;
    }

    /**
     * Gets the value of the tileRows property.
     * 
     */
    public int getTileRows() {
        return tileRows;
    }

    /**
     * Sets the value of the tileRows property.
     * 
     */
    public void setTileRows(int value) {
        this.tileRows = value;
    }

    /**
     * Gets the value of the dpi property.
     * 
     */
    public int getDPI() {
        return dpi;
    }

    /**
     * Sets the value of the dpi property.
     * 
     */
    public void setDPI(int value) {
        this.dpi = value;
    }

    /**
     * Gets the value of the lodInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLODInfo }
     *     
     */
    public ArrayOfLODInfo getLODInfos() {
        return lodInfos;
    }

    /**
     * Sets the value of the lodInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLODInfo }
     *     
     */
    public void setLODInfos(ArrayOfLODInfo value) {
        this.lodInfos = value;
    }

    /**
     * Gets the value of the preciseDPI property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPreciseDPI() {
        return preciseDPI;
    }

    /**
     * Sets the value of the preciseDPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPreciseDPI(Double value) {
        this.preciseDPI = value;
    }

}

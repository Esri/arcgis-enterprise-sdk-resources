
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LODInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LODInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LevelID" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Scale" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="Resolution" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LODInfo", propOrder = {
    "levelID",
    "scale",
    "resolution"
})
public class LODInfo {

    @XmlElement(name = "LevelID")
    protected int levelID;
    @XmlElement(name = "Scale")
    protected double scale;
    @XmlElement(name = "Resolution")
    protected double resolution;

    /**
     * Gets the value of the levelID property.
     * 
     */
    public int getLevelID() {
        return levelID;
    }

    /**
     * Sets the value of the levelID property.
     * 
     */
    public void setLevelID(int value) {
        this.levelID = value;
    }

    /**
     * Gets the value of the scale property.
     * 
     */
    public double getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     */
    public void setScale(double value) {
        this.scale = value;
    }

    /**
     * Gets the value of the resolution property.
     * 
     */
    public double getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     */
    public void setResolution(double value) {
        this.resolution = value;
    }

}

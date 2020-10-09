
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MultiPatchN complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MultiPatchN"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}MultiPatch"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="HasID" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="HasZ" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="HasM" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="Extent" type="{http://www.esri.com/schemas/ArcGIS/10.7}Envelope"/&amp;gt;
 *         &amp;lt;element name="SurfacePatchArray" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfSurfacePatch"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiPatchN", propOrder = {
    "hasID",
    "hasZ",
    "hasM",
    "extent",
    "surfacePatchArray"
})
public class MultiPatchN
    extends MultiPatch
{

    @XmlElement(name = "HasID")
    protected boolean hasID;
    @XmlElement(name = "HasZ")
    protected boolean hasZ;
    @XmlElement(name = "HasM")
    protected boolean hasM;
    @XmlElement(name = "Extent", required = true)
    protected Envelope extent;
    @XmlElement(name = "SurfacePatchArray", required = true)
    protected ArrayOfSurfacePatch surfacePatchArray;

    /**
     * Gets the value of the hasID property.
     * 
     */
    public boolean isHasID() {
        return hasID;
    }

    /**
     * Sets the value of the hasID property.
     * 
     */
    public void setHasID(boolean value) {
        this.hasID = value;
    }

    /**
     * Gets the value of the hasZ property.
     * 
     */
    public boolean isHasZ() {
        return hasZ;
    }

    /**
     * Sets the value of the hasZ property.
     * 
     */
    public void setHasZ(boolean value) {
        this.hasZ = value;
    }

    /**
     * Gets the value of the hasM property.
     * 
     */
    public boolean isHasM() {
        return hasM;
    }

    /**
     * Sets the value of the hasM property.
     * 
     */
    public void setHasM(boolean value) {
        this.hasM = value;
    }

    /**
     * Gets the value of the extent property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getExtent() {
        return extent;
    }

    /**
     * Sets the value of the extent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setExtent(Envelope value) {
        this.extent = value;
    }

    /**
     * Gets the value of the surfacePatchArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSurfacePatch }
     *     
     */
    public ArrayOfSurfacePatch getSurfacePatchArray() {
        return surfacePatchArray;
    }

    /**
     * Sets the value of the surfacePatchArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSurfacePatch }
     *     
     */
    public void setSurfacePatchArray(ArrayOfSurfacePatch value) {
        this.surfacePatchArray = value;
    }

}

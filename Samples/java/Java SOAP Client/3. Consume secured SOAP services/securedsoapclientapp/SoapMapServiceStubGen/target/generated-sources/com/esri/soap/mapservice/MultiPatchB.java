
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for MultiPatchB complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MultiPatchB"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}MultiPatch"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Bytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&amp;gt;
 *         &amp;lt;element name="SpatialReference" type="{http://www.esri.com/schemas/ArcGIS/2.6.0}SpatialReference" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiPatchB", propOrder = {
    "bytes",
    "spatialReference"
})
public class MultiPatchB
    extends MultiPatch
{

    @XmlElement(name = "Bytes", required = true)
    protected byte[] bytes;
    @XmlElement(name = "SpatialReference")
    protected SpatialReference spatialReference;

    /**
     * Gets the value of the bytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Sets the value of the bytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBytes(byte[] value) {
        this.bytes = value;
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

}

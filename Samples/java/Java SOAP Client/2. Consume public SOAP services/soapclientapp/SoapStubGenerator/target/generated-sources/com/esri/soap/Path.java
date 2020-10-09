
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for Path complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Path"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Curve"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="PointArray" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfPoint" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SegmentArray" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfSegment" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Path", propOrder = {
    "pointArray",
    "segmentArray"
})
@XmlSeeAlso({
    Ring.class
})
public class Path
    extends Curve
{

    @XmlElement(name = "PointArray")
    protected ArrayOfPoint pointArray;
    @XmlElement(name = "SegmentArray")
    protected ArrayOfSegment segmentArray;

    /**
     * Gets the value of the pointArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPoint }
     *     
     */
    public ArrayOfPoint getPointArray() {
        return pointArray;
    }

    /**
     * Sets the value of the pointArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPoint }
     *     
     */
    public void setPointArray(ArrayOfPoint value) {
        this.pointArray = value;
    }

    /**
     * Gets the value of the segmentArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSegment }
     *     
     */
    public ArrayOfSegment getSegmentArray() {
        return segmentArray;
    }

    /**
     * Sets the value of the segmentArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSegment }
     *     
     */
    public void setSegmentArray(ArrayOfSegment value) {
        this.segmentArray = value;
    }

}

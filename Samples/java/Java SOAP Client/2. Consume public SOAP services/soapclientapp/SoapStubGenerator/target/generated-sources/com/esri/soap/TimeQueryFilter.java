
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TimeQueryFilter complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TimeQueryFilter"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}SpatialFilter"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="TimeValue" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeValue"/&amp;gt;
 *         &amp;lt;element name="OutputTimeReference" type="{http://www.esri.com/schemas/ArcGIS/10.7}TimeReference"/&amp;gt;
 *         &amp;lt;element name="TimeRelation" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTimeRelation"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeQueryFilter", propOrder = {
    "timeValue",
    "outputTimeReference",
    "timeRelation"
})
@XmlSeeAlso({
    ImageQueryFilter.class
})
public class TimeQueryFilter
    extends SpatialFilter
{

    @XmlElement(name = "TimeValue", required = true)
    protected TimeValue timeValue;
    @XmlElement(name = "OutputTimeReference", required = true)
    protected TimeReference outputTimeReference;
    @XmlElement(name = "TimeRelation", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTimeRelation timeRelation;

    /**
     * Gets the value of the timeValue property.
     * 
     * @return
     *     possible object is
     *     {@link TimeValue }
     *     
     */
    public TimeValue getTimeValue() {
        return timeValue;
    }

    /**
     * Sets the value of the timeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeValue }
     *     
     */
    public void setTimeValue(TimeValue value) {
        this.timeValue = value;
    }

    /**
     * Gets the value of the outputTimeReference property.
     * 
     * @return
     *     possible object is
     *     {@link TimeReference }
     *     
     */
    public TimeReference getOutputTimeReference() {
        return outputTimeReference;
    }

    /**
     * Sets the value of the outputTimeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeReference }
     *     
     */
    public void setOutputTimeReference(TimeReference value) {
        this.outputTimeReference = value;
    }

    /**
     * Gets the value of the timeRelation property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTimeRelation }
     *     
     */
    public EsriTimeRelation getTimeRelation() {
        return timeRelation;
    }

    /**
     * Sets the value of the timeRelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTimeRelation }
     *     
     */
    public void setTimeRelation(EsriTimeRelation value) {
        this.timeRelation = value;
    }

}

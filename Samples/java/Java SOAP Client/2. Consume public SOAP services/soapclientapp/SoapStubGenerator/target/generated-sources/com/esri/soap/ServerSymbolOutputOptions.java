
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ServerSymbolOutputOptions complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ServerSymbolOutputOptions"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="PictureOutputType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriServerPictureOutputType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ConvertLabelExpressions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerSymbolOutputOptions", propOrder = {
    "pictureOutputType",
    "convertLabelExpressions"
})
public class ServerSymbolOutputOptions {

    @XmlElement(name = "PictureOutputType")
    @XmlSchemaType(name = "string")
    protected EsriServerPictureOutputType pictureOutputType;
    @XmlElement(name = "ConvertLabelExpressions", defaultValue = "true")
    protected Boolean convertLabelExpressions;

    /**
     * Gets the value of the pictureOutputType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriServerPictureOutputType }
     *     
     */
    public EsriServerPictureOutputType getPictureOutputType() {
        return pictureOutputType;
    }

    /**
     * Sets the value of the pictureOutputType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriServerPictureOutputType }
     *     
     */
    public void setPictureOutputType(EsriServerPictureOutputType value) {
        this.pictureOutputType = value;
    }

    /**
     * Gets the value of the convertLabelExpressions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConvertLabelExpressions() {
        return convertLabelExpressions;
    }

    /**
     * Sets the value of the convertLabelExpressions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConvertLabelExpressions(Boolean value) {
        this.convertLabelExpressions = value;
    }

}

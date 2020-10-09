
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RasterDataSourceDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RasterDataSourceDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}DataSourceDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RasterName" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RasterDataSourceDescription", propOrder = {
    "rasterName"
})
public class RasterDataSourceDescription
    extends DataSourceDescription
{

    @XmlElement(name = "RasterName", required = true)
    protected String rasterName;

    /**
     * Gets the value of the rasterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRasterName() {
        return rasterName;
    }

    /**
     * Sets the value of the rasterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRasterName(String value) {
        this.rasterName = value;
    }

}

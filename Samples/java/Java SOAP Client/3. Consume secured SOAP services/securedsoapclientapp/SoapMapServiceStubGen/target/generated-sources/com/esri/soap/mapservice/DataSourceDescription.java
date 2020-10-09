
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for DataSourceDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DataSourceDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/2.6.0}MapServerSourceDescription"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="WorkspaceID" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceDescription", propOrder = {
    "workspaceID"
})
@XmlSeeAlso({
    TableDataSourceDescription.class,
    QueryTableDataSourceDescription.class,
    RasterDataSourceDescription.class
})
public class DataSourceDescription
    extends MapServerSourceDescription
{

    @XmlElement(name = "WorkspaceID", required = true)
    protected String workspaceID;

    /**
     * Gets the value of the workspaceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkspaceID() {
        return workspaceID;
    }

    /**
     * Sets the value of the workspaceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkspaceID(String value) {
        this.workspaceID = value;
    }

}

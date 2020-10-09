
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SymbolOutputOptions" type="{http://www.esri.com/schemas/ArcGIS/10.7}ServerSymbolOutputOptions" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IgnoreLayers" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "symbolOutputOptions",
    "ignoreLayers"
})
@XmlRootElement(name = "GetTables")
public class GetTables {

    @XmlElement(name = "SymbolOutputOptions")
    protected ServerSymbolOutputOptions symbolOutputOptions;
    @XmlElement(name = "IgnoreLayers")
    protected boolean ignoreLayers;

    /**
     * Gets the value of the symbolOutputOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ServerSymbolOutputOptions }
     *     
     */
    public ServerSymbolOutputOptions getSymbolOutputOptions() {
        return symbolOutputOptions;
    }

    /**
     * Sets the value of the symbolOutputOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerSymbolOutputOptions }
     *     
     */
    public void setSymbolOutputOptions(ServerSymbolOutputOptions value) {
        this.symbolOutputOptions = value;
    }

    /**
     * Gets the value of the ignoreLayers property.
     * 
     */
    public boolean isIgnoreLayers() {
        return ignoreLayers;
    }

    /**
     * Sets the value of the ignoreLayers property.
     * 
     */
    public void setIgnoreLayers(boolean value) {
        this.ignoreLayers = value;
    }

}

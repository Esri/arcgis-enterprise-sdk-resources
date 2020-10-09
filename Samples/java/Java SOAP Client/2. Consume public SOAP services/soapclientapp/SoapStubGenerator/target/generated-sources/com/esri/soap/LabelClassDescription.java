
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for LabelClassDescription complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LabelClassDescription"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="LabelPlacementDescription" type="{http://www.esri.com/schemas/ArcGIS/10.7}LabelPlacementDescription" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LabelExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Symbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}SimpleTextSymbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UseCodedValue" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MaximumScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MinimumScale" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LabelExpressionType" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriLabelExpressionType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="WhereClause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelClassDescription", propOrder = {
    "labelPlacementDescription",
    "labelExpression",
    "symbol",
    "useCodedValue",
    "maximumScale",
    "minimumScale",
    "labelExpressionType",
    "whereClause"
})
public class LabelClassDescription {

    @XmlElement(name = "LabelPlacementDescription")
    protected LabelPlacementDescription labelPlacementDescription;
    @XmlElement(name = "LabelExpression")
    protected String labelExpression;
    @XmlElement(name = "Symbol")
    protected SimpleTextSymbol symbol;
    @XmlElement(name = "UseCodedValue")
    protected Boolean useCodedValue;
    @XmlElement(name = "MaximumScale")
    protected Double maximumScale;
    @XmlElement(name = "MinimumScale")
    protected Double minimumScale;
    @XmlElement(name = "LabelExpressionType")
    @XmlSchemaType(name = "string")
    protected EsriLabelExpressionType labelExpressionType;
    @XmlElement(name = "WhereClause")
    protected String whereClause;

    /**
     * Gets the value of the labelPlacementDescription property.
     * 
     * @return
     *     possible object is
     *     {@link LabelPlacementDescription }
     *     
     */
    public LabelPlacementDescription getLabelPlacementDescription() {
        return labelPlacementDescription;
    }

    /**
     * Sets the value of the labelPlacementDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelPlacementDescription }
     *     
     */
    public void setLabelPlacementDescription(LabelPlacementDescription value) {
        this.labelPlacementDescription = value;
    }

    /**
     * Gets the value of the labelExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * Sets the value of the labelExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelExpression(String value) {
        this.labelExpression = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleTextSymbol }
     *     
     */
    public SimpleTextSymbol getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleTextSymbol }
     *     
     */
    public void setSymbol(SimpleTextSymbol value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the useCodedValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseCodedValue() {
        return useCodedValue;
    }

    /**
     * Sets the value of the useCodedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseCodedValue(Boolean value) {
        this.useCodedValue = value;
    }

    /**
     * Gets the value of the maximumScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumScale() {
        return maximumScale;
    }

    /**
     * Sets the value of the maximumScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumScale(Double value) {
        this.maximumScale = value;
    }

    /**
     * Gets the value of the minimumScale property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimumScale() {
        return minimumScale;
    }

    /**
     * Sets the value of the minimumScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinimumScale(Double value) {
        this.minimumScale = value;
    }

    /**
     * Gets the value of the labelExpressionType property.
     * 
     * @return
     *     possible object is
     *     {@link EsriLabelExpressionType }
     *     
     */
    public EsriLabelExpressionType getLabelExpressionType() {
        return labelExpressionType;
    }

    /**
     * Sets the value of the labelExpressionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriLabelExpressionType }
     *     
     */
    public void setLabelExpressionType(EsriLabelExpressionType value) {
        this.labelExpressionType = value;
    }

    /**
     * Gets the value of the whereClause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClause() {
        return whereClause;
    }

    /**
     * Sets the value of the whereClause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClause(String value) {
        this.whereClause = value;
    }

}

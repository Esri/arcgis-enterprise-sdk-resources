
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SQLSyntaxInfo complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SQLSyntaxInfo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="FunctionNames" type="{http://www.esri.com/schemas/ArcGIS/10.7}PropertySet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SpecialCharacters" type="{http://www.esri.com/schemas/ArcGIS/10.7}PropertySet" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportedPredicates" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfString" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SupportedClauses" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfString" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IdentifierCase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="DelimitedIdentifierCase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="StringComparisonCase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="Keywords" type="{http://www.esri.com/schemas/ArcGIS/10.7}ArrayOfString" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InvalidCharacters" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="InvalidStartingCharacters" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQLSyntaxInfo", propOrder = {
    "functionNames",
    "specialCharacters",
    "supportedPredicates",
    "supportedClauses",
    "identifierCase",
    "delimitedIdentifierCase",
    "stringComparisonCase",
    "keywords",
    "invalidCharacters",
    "invalidStartingCharacters"
})
public class SQLSyntaxInfo {

    @XmlElement(name = "FunctionNames")
    protected PropertySet functionNames;
    @XmlElement(name = "SpecialCharacters")
    protected PropertySet specialCharacters;
    @XmlElement(name = "SupportedPredicates")
    protected ArrayOfString supportedPredicates;
    @XmlElement(name = "SupportedClauses")
    protected ArrayOfString supportedClauses;
    @XmlElement(name = "IdentifierCase")
    protected boolean identifierCase;
    @XmlElement(name = "DelimitedIdentifierCase")
    protected boolean delimitedIdentifierCase;
    @XmlElement(name = "StringComparisonCase")
    protected boolean stringComparisonCase;
    @XmlElement(name = "Keywords")
    protected ArrayOfString keywords;
    @XmlElement(name = "InvalidCharacters", required = true)
    protected String invalidCharacters;
    @XmlElement(name = "InvalidStartingCharacters", required = true)
    protected String invalidStartingCharacters;

    /**
     * Gets the value of the functionNames property.
     * 
     * @return
     *     possible object is
     *     {@link PropertySet }
     *     
     */
    public PropertySet getFunctionNames() {
        return functionNames;
    }

    /**
     * Sets the value of the functionNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertySet }
     *     
     */
    public void setFunctionNames(PropertySet value) {
        this.functionNames = value;
    }

    /**
     * Gets the value of the specialCharacters property.
     * 
     * @return
     *     possible object is
     *     {@link PropertySet }
     *     
     */
    public PropertySet getSpecialCharacters() {
        return specialCharacters;
    }

    /**
     * Sets the value of the specialCharacters property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertySet }
     *     
     */
    public void setSpecialCharacters(PropertySet value) {
        this.specialCharacters = value;
    }

    /**
     * Gets the value of the supportedPredicates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSupportedPredicates() {
        return supportedPredicates;
    }

    /**
     * Sets the value of the supportedPredicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSupportedPredicates(ArrayOfString value) {
        this.supportedPredicates = value;
    }

    /**
     * Gets the value of the supportedClauses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSupportedClauses() {
        return supportedClauses;
    }

    /**
     * Sets the value of the supportedClauses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSupportedClauses(ArrayOfString value) {
        this.supportedClauses = value;
    }

    /**
     * Gets the value of the identifierCase property.
     * 
     */
    public boolean isIdentifierCase() {
        return identifierCase;
    }

    /**
     * Sets the value of the identifierCase property.
     * 
     */
    public void setIdentifierCase(boolean value) {
        this.identifierCase = value;
    }

    /**
     * Gets the value of the delimitedIdentifierCase property.
     * 
     */
    public boolean isDelimitedIdentifierCase() {
        return delimitedIdentifierCase;
    }

    /**
     * Sets the value of the delimitedIdentifierCase property.
     * 
     */
    public void setDelimitedIdentifierCase(boolean value) {
        this.delimitedIdentifierCase = value;
    }

    /**
     * Gets the value of the stringComparisonCase property.
     * 
     */
    public boolean isStringComparisonCase() {
        return stringComparisonCase;
    }

    /**
     * Sets the value of the stringComparisonCase property.
     * 
     */
    public void setStringComparisonCase(boolean value) {
        this.stringComparisonCase = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setKeywords(ArrayOfString value) {
        this.keywords = value;
    }

    /**
     * Gets the value of the invalidCharacters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvalidCharacters() {
        return invalidCharacters;
    }

    /**
     * Sets the value of the invalidCharacters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvalidCharacters(String value) {
        this.invalidCharacters = value;
    }

    /**
     * Gets the value of the invalidStartingCharacters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvalidStartingCharacters() {
        return invalidStartingCharacters;
    }

    /**
     * Sets the value of the invalidStartingCharacters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvalidStartingCharacters(String value) {
        this.invalidStartingCharacters = value;
    }

}

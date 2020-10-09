
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for CharacterMarkerSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CharacterMarkerSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}CartographicMarkerSymbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CharacterIndex" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="FontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontItalic" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontUnderline" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontStrikethrough" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontWeight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontCharset" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontSizeHi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontSizeLo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacterMarkerSymbol", propOrder = {
    "characterIndex",
    "fontName",
    "fontItalic",
    "fontUnderline",
    "fontStrikethrough",
    "fontWeight",
    "fontCharset",
    "fontSizeHi",
    "fontSizeLo"
})
public class CharacterMarkerSymbol
    extends CartographicMarkerSymbol
{

    @XmlElement(name = "CharacterIndex")
    protected int characterIndex;
    @XmlElement(name = "FontName")
    protected String fontName;
    @XmlElement(name = "FontItalic")
    protected Boolean fontItalic;
    @XmlElement(name = "FontUnderline")
    protected Boolean fontUnderline;
    @XmlElement(name = "FontStrikethrough")
    protected Boolean fontStrikethrough;
    @XmlElement(name = "FontWeight")
    protected Integer fontWeight;
    @XmlElement(name = "FontCharset")
    protected Integer fontCharset;
    @XmlElement(name = "FontSizeHi")
    protected Integer fontSizeHi;
    @XmlElement(name = "FontSizeLo")
    protected Integer fontSizeLo;

    /**
     * Gets the value of the characterIndex property.
     * 
     */
    public int getCharacterIndex() {
        return characterIndex;
    }

    /**
     * Sets the value of the characterIndex property.
     * 
     */
    public void setCharacterIndex(int value) {
        this.characterIndex = value;
    }

    /**
     * Gets the value of the fontName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * Sets the value of the fontName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontName(String value) {
        this.fontName = value;
    }

    /**
     * Gets the value of the fontItalic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFontItalic() {
        return fontItalic;
    }

    /**
     * Sets the value of the fontItalic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFontItalic(Boolean value) {
        this.fontItalic = value;
    }

    /**
     * Gets the value of the fontUnderline property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFontUnderline() {
        return fontUnderline;
    }

    /**
     * Sets the value of the fontUnderline property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFontUnderline(Boolean value) {
        this.fontUnderline = value;
    }

    /**
     * Gets the value of the fontStrikethrough property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFontStrikethrough() {
        return fontStrikethrough;
    }

    /**
     * Sets the value of the fontStrikethrough property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFontStrikethrough(Boolean value) {
        this.fontStrikethrough = value;
    }

    /**
     * Gets the value of the fontWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFontWeight() {
        return fontWeight;
    }

    /**
     * Sets the value of the fontWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFontWeight(Integer value) {
        this.fontWeight = value;
    }

    /**
     * Gets the value of the fontCharset property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFontCharset() {
        return fontCharset;
    }

    /**
     * Sets the value of the fontCharset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFontCharset(Integer value) {
        this.fontCharset = value;
    }

    /**
     * Gets the value of the fontSizeHi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFontSizeHi() {
        return fontSizeHi;
    }

    /**
     * Sets the value of the fontSizeHi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFontSizeHi(Integer value) {
        this.fontSizeHi = value;
    }

    /**
     * Gets the value of the fontSizeLo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFontSizeLo() {
        return fontSizeLo;
    }

    /**
     * Sets the value of the fontSizeLo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFontSizeLo(Integer value) {
        this.fontSizeLo = value;
    }

}

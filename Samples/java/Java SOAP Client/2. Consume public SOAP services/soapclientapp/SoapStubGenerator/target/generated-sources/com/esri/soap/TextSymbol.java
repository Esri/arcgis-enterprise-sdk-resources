
package com.esri.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for TextSymbol complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TextSymbol"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Color" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BreakCharIndex" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="VerticalAlignment" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTextVerticalAlignment"/&amp;gt;
 *         &amp;lt;element name="HorizontalAlignment" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTextHorizontalAlignment"/&amp;gt;
 *         &amp;lt;element name="Clip" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="RightToLeft" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="Angle" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="XOffset" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="YOffset" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="ShadowColor" type="{http://www.esri.com/schemas/ArcGIS/10.7}Color" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ShadowXOffset" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="ShadowYOffset" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="TextPosition" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTextPosition"/&amp;gt;
 *         &amp;lt;element name="TextCase" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTextCase"/&amp;gt;
 *         &amp;lt;element name="CharacterSpacing" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="CharacterWidth" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="WordSpacing" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="Kerning" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="Leading" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="TextDirection" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriTextDirection"/&amp;gt;
 *         &amp;lt;element name="FlipAngle" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="TypeSetting" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="TextPathClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FillSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="Size" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="MaskStyle" type="{http://www.esri.com/schemas/ArcGIS/10.7}esriMaskStyle"/&amp;gt;
 *         &amp;lt;element name="MaskSize" type="{http://www.w3.org/2001/XMLSchema}double"/&amp;gt;
 *         &amp;lt;element name="MaskSymbol" type="{http://www.esri.com/schemas/ArcGIS/10.7}Symbol" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontItalic" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontUnderline" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontStrikethrough" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontWeight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontCharset" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontSizeHi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FontSizeLo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TextParserClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextSymbol", propOrder = {
    "color",
    "breakCharIndex",
    "verticalAlignment",
    "horizontalAlignment",
    "clip",
    "rightToLeft",
    "angle",
    "xOffset",
    "yOffset",
    "shadowColor",
    "shadowXOffset",
    "shadowYOffset",
    "textPosition",
    "textCase",
    "characterSpacing",
    "characterWidth",
    "wordSpacing",
    "kerning",
    "leading",
    "textDirection",
    "flipAngle",
    "typeSetting",
    "textPathClass",
    "fillSymbol",
    "text",
    "size",
    "maskStyle",
    "maskSize",
    "maskSymbol",
    "fontName",
    "fontItalic",
    "fontUnderline",
    "fontStrikethrough",
    "fontWeight",
    "fontCharset",
    "fontSizeHi",
    "fontSizeLo",
    "textParserClass"
})
public class TextSymbol
    extends Symbol
{

    @XmlElement(name = "Color")
    protected Color color;
    @XmlElement(name = "BreakCharIndex")
    protected int breakCharIndex;
    @XmlElement(name = "VerticalAlignment", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTextVerticalAlignment verticalAlignment;
    @XmlElement(name = "HorizontalAlignment", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTextHorizontalAlignment horizontalAlignment;
    @XmlElement(name = "Clip")
    protected boolean clip;
    @XmlElement(name = "RightToLeft")
    protected boolean rightToLeft;
    @XmlElement(name = "Angle")
    protected double angle;
    @XmlElement(name = "XOffset")
    protected double xOffset;
    @XmlElement(name = "YOffset")
    protected double yOffset;
    @XmlElement(name = "ShadowColor")
    protected Color shadowColor;
    @XmlElement(name = "ShadowXOffset")
    protected double shadowXOffset;
    @XmlElement(name = "ShadowYOffset")
    protected double shadowYOffset;
    @XmlElement(name = "TextPosition", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTextPosition textPosition;
    @XmlElement(name = "TextCase", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTextCase textCase;
    @XmlElement(name = "CharacterSpacing")
    protected double characterSpacing;
    @XmlElement(name = "CharacterWidth", defaultValue = "100.0")
    protected double characterWidth;
    @XmlElement(name = "WordSpacing", defaultValue = "100.0")
    protected double wordSpacing;
    @XmlElement(name = "Kerning")
    protected boolean kerning;
    @XmlElement(name = "Leading")
    protected double leading;
    @XmlElement(name = "TextDirection", required = true)
    @XmlSchemaType(name = "string")
    protected EsriTextDirection textDirection;
    @XmlElement(name = "FlipAngle")
    protected double flipAngle;
    @XmlElement(name = "TypeSetting")
    protected boolean typeSetting;
    @XmlElement(name = "TextPathClass")
    protected String textPathClass;
    @XmlElement(name = "FillSymbol")
    protected Symbol fillSymbol;
    @XmlElement(name = "Text", required = true)
    protected String text;
    @XmlElement(name = "Size")
    protected double size;
    @XmlElement(name = "MaskStyle", required = true)
    @XmlSchemaType(name = "string")
    protected EsriMaskStyle maskStyle;
    @XmlElement(name = "MaskSize")
    protected double maskSize;
    @XmlElement(name = "MaskSymbol")
    protected Symbol maskSymbol;
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
    @XmlElement(name = "TextParserClass")
    protected String textParserClass;

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setColor(Color value) {
        this.color = value;
    }

    /**
     * Gets the value of the breakCharIndex property.
     * 
     */
    public int getBreakCharIndex() {
        return breakCharIndex;
    }

    /**
     * Sets the value of the breakCharIndex property.
     * 
     */
    public void setBreakCharIndex(int value) {
        this.breakCharIndex = value;
    }

    /**
     * Gets the value of the verticalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTextVerticalAlignment }
     *     
     */
    public EsriTextVerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the value of the verticalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTextVerticalAlignment }
     *     
     */
    public void setVerticalAlignment(EsriTextVerticalAlignment value) {
        this.verticalAlignment = value;
    }

    /**
     * Gets the value of the horizontalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTextHorizontalAlignment }
     *     
     */
    public EsriTextHorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the value of the horizontalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTextHorizontalAlignment }
     *     
     */
    public void setHorizontalAlignment(EsriTextHorizontalAlignment value) {
        this.horizontalAlignment = value;
    }

    /**
     * Gets the value of the clip property.
     * 
     */
    public boolean isClip() {
        return clip;
    }

    /**
     * Sets the value of the clip property.
     * 
     */
    public void setClip(boolean value) {
        this.clip = value;
    }

    /**
     * Gets the value of the rightToLeft property.
     * 
     */
    public boolean isRightToLeft() {
        return rightToLeft;
    }

    /**
     * Sets the value of the rightToLeft property.
     * 
     */
    public void setRightToLeft(boolean value) {
        this.rightToLeft = value;
    }

    /**
     * Gets the value of the angle property.
     * 
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets the value of the angle property.
     * 
     */
    public void setAngle(double value) {
        this.angle = value;
    }

    /**
     * Gets the value of the xOffset property.
     * 
     */
    public double getXOffset() {
        return xOffset;
    }

    /**
     * Sets the value of the xOffset property.
     * 
     */
    public void setXOffset(double value) {
        this.xOffset = value;
    }

    /**
     * Gets the value of the yOffset property.
     * 
     */
    public double getYOffset() {
        return yOffset;
    }

    /**
     * Sets the value of the yOffset property.
     * 
     */
    public void setYOffset(double value) {
        this.yOffset = value;
    }

    /**
     * Gets the value of the shadowColor property.
     * 
     * @return
     *     possible object is
     *     {@link Color }
     *     
     */
    public Color getShadowColor() {
        return shadowColor;
    }

    /**
     * Sets the value of the shadowColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Color }
     *     
     */
    public void setShadowColor(Color value) {
        this.shadowColor = value;
    }

    /**
     * Gets the value of the shadowXOffset property.
     * 
     */
    public double getShadowXOffset() {
        return shadowXOffset;
    }

    /**
     * Sets the value of the shadowXOffset property.
     * 
     */
    public void setShadowXOffset(double value) {
        this.shadowXOffset = value;
    }

    /**
     * Gets the value of the shadowYOffset property.
     * 
     */
    public double getShadowYOffset() {
        return shadowYOffset;
    }

    /**
     * Sets the value of the shadowYOffset property.
     * 
     */
    public void setShadowYOffset(double value) {
        this.shadowYOffset = value;
    }

    /**
     * Gets the value of the textPosition property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTextPosition }
     *     
     */
    public EsriTextPosition getTextPosition() {
        return textPosition;
    }

    /**
     * Sets the value of the textPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTextPosition }
     *     
     */
    public void setTextPosition(EsriTextPosition value) {
        this.textPosition = value;
    }

    /**
     * Gets the value of the textCase property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTextCase }
     *     
     */
    public EsriTextCase getTextCase() {
        return textCase;
    }

    /**
     * Sets the value of the textCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTextCase }
     *     
     */
    public void setTextCase(EsriTextCase value) {
        this.textCase = value;
    }

    /**
     * Gets the value of the characterSpacing property.
     * 
     */
    public double getCharacterSpacing() {
        return characterSpacing;
    }

    /**
     * Sets the value of the characterSpacing property.
     * 
     */
    public void setCharacterSpacing(double value) {
        this.characterSpacing = value;
    }

    /**
     * Gets the value of the characterWidth property.
     * 
     */
    public double getCharacterWidth() {
        return characterWidth;
    }

    /**
     * Sets the value of the characterWidth property.
     * 
     */
    public void setCharacterWidth(double value) {
        this.characterWidth = value;
    }

    /**
     * Gets the value of the wordSpacing property.
     * 
     */
    public double getWordSpacing() {
        return wordSpacing;
    }

    /**
     * Sets the value of the wordSpacing property.
     * 
     */
    public void setWordSpacing(double value) {
        this.wordSpacing = value;
    }

    /**
     * Gets the value of the kerning property.
     * 
     */
    public boolean isKerning() {
        return kerning;
    }

    /**
     * Sets the value of the kerning property.
     * 
     */
    public void setKerning(boolean value) {
        this.kerning = value;
    }

    /**
     * Gets the value of the leading property.
     * 
     */
    public double getLeading() {
        return leading;
    }

    /**
     * Sets the value of the leading property.
     * 
     */
    public void setLeading(double value) {
        this.leading = value;
    }

    /**
     * Gets the value of the textDirection property.
     * 
     * @return
     *     possible object is
     *     {@link EsriTextDirection }
     *     
     */
    public EsriTextDirection getTextDirection() {
        return textDirection;
    }

    /**
     * Sets the value of the textDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriTextDirection }
     *     
     */
    public void setTextDirection(EsriTextDirection value) {
        this.textDirection = value;
    }

    /**
     * Gets the value of the flipAngle property.
     * 
     */
    public double getFlipAngle() {
        return flipAngle;
    }

    /**
     * Sets the value of the flipAngle property.
     * 
     */
    public void setFlipAngle(double value) {
        this.flipAngle = value;
    }

    /**
     * Gets the value of the typeSetting property.
     * 
     */
    public boolean isTypeSetting() {
        return typeSetting;
    }

    /**
     * Sets the value of the typeSetting property.
     * 
     */
    public void setTypeSetting(boolean value) {
        this.typeSetting = value;
    }

    /**
     * Gets the value of the textPathClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextPathClass() {
        return textPathClass;
    }

    /**
     * Sets the value of the textPathClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextPathClass(String value) {
        this.textPathClass = value;
    }

    /**
     * Gets the value of the fillSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getFillSymbol() {
        return fillSymbol;
    }

    /**
     * Sets the value of the fillSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setFillSymbol(Symbol value) {
        this.fillSymbol = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the size property.
     * 
     */
    public double getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     */
    public void setSize(double value) {
        this.size = value;
    }

    /**
     * Gets the value of the maskStyle property.
     * 
     * @return
     *     possible object is
     *     {@link EsriMaskStyle }
     *     
     */
    public EsriMaskStyle getMaskStyle() {
        return maskStyle;
    }

    /**
     * Sets the value of the maskStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsriMaskStyle }
     *     
     */
    public void setMaskStyle(EsriMaskStyle value) {
        this.maskStyle = value;
    }

    /**
     * Gets the value of the maskSize property.
     * 
     */
    public double getMaskSize() {
        return maskSize;
    }

    /**
     * Sets the value of the maskSize property.
     * 
     */
    public void setMaskSize(double value) {
        this.maskSize = value;
    }

    /**
     * Gets the value of the maskSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link Symbol }
     *     
     */
    public Symbol getMaskSymbol() {
        return maskSymbol;
    }

    /**
     * Sets the value of the maskSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symbol }
     *     
     */
    public void setMaskSymbol(Symbol value) {
        this.maskSymbol = value;
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

    /**
     * Gets the value of the textParserClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextParserClass() {
        return textParserClass;
    }

    /**
     * Sets the value of the textParserClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextParserClass(String value) {
        this.textParserClass = value;
    }

}

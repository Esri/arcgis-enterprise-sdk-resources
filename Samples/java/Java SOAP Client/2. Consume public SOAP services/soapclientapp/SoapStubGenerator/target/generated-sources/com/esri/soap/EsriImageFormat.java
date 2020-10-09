
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriImageFormat.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriImageFormat"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriImageNone"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageBMP"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageJPG"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageDIB"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageTIFF"/&amp;gt;
 *     &amp;lt;enumeration value="esriImagePNG"/&amp;gt;
 *     &amp;lt;enumeration value="esriImagePNG24"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageEMF"/&amp;gt;
 *     &amp;lt;enumeration value="esriImagePS"/&amp;gt;
 *     &amp;lt;enumeration value="esriImagePDF"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageAI"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageGIF"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageSVG"/&amp;gt;
 *     &amp;lt;enumeration value="esriImagePNG32"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageJPGPNG"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriImageFormat")
@XmlEnum
public enum EsriImageFormat {

    @XmlEnumValue("esriImageNone")
    ESRI_IMAGE_NONE("esriImageNone"),
    @XmlEnumValue("esriImageBMP")
    ESRI_IMAGE_BMP("esriImageBMP"),
    @XmlEnumValue("esriImageJPG")
    ESRI_IMAGE_JPG("esriImageJPG"),
    @XmlEnumValue("esriImageDIB")
    ESRI_IMAGE_DIB("esriImageDIB"),
    @XmlEnumValue("esriImageTIFF")
    ESRI_IMAGE_TIFF("esriImageTIFF"),
    @XmlEnumValue("esriImagePNG")
    ESRI_IMAGE_PNG("esriImagePNG"),
    @XmlEnumValue("esriImagePNG24")
    ESRI_IMAGE_PNG_24("esriImagePNG24"),
    @XmlEnumValue("esriImageEMF")
    ESRI_IMAGE_EMF("esriImageEMF"),
    @XmlEnumValue("esriImagePS")
    ESRI_IMAGE_PS("esriImagePS"),
    @XmlEnumValue("esriImagePDF")
    ESRI_IMAGE_PDF("esriImagePDF"),
    @XmlEnumValue("esriImageAI")
    ESRI_IMAGE_AI("esriImageAI"),
    @XmlEnumValue("esriImageGIF")
    ESRI_IMAGE_GIF("esriImageGIF"),
    @XmlEnumValue("esriImageSVG")
    ESRI_IMAGE_SVG("esriImageSVG"),
    @XmlEnumValue("esriImagePNG32")
    ESRI_IMAGE_PNG_32("esriImagePNG32"),
    @XmlEnumValue("esriImageJPGPNG")
    ESRI_IMAGE_JPGPNG("esriImageJPGPNG");
    private final String value;

    EsriImageFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriImageFormat fromValue(String v) {
        for (EsriImageFormat c: EsriImageFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

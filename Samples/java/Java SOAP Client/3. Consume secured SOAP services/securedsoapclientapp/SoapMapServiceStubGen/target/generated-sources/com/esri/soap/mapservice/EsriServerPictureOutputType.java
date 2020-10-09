
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriServerPictureOutputType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriServerPictureOutputType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriServerPictureOutputAsPNG"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPictureOutputAsPNGInMime"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPictureOutputAsIPicture"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriServerPictureOutputType")
@XmlEnum
public enum EsriServerPictureOutputType {

    @XmlEnumValue("esriServerPictureOutputAsPNG")
    ESRI_SERVER_PICTURE_OUTPUT_AS_PNG("esriServerPictureOutputAsPNG"),
    @XmlEnumValue("esriServerPictureOutputAsPNGInMime")
    ESRI_SERVER_PICTURE_OUTPUT_AS_PNG_IN_MIME("esriServerPictureOutputAsPNGInMime"),
    @XmlEnumValue("esriServerPictureOutputAsIPicture")
    ESRI_SERVER_PICTURE_OUTPUT_AS_I_PICTURE("esriServerPictureOutputAsIPicture");
    private final String value;

    EsriServerPictureOutputType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriServerPictureOutputType fromValue(String v) {
        for (EsriServerPictureOutputType c: EsriServerPictureOutputType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

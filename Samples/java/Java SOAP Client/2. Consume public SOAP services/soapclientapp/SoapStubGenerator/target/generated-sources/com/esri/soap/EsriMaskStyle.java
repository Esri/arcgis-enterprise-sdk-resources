
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriMaskStyle.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriMaskStyle"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriMSNone"/&amp;gt;
 *     &amp;lt;enumeration value="esriMSHalo"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriMaskStyle")
@XmlEnum
public enum EsriMaskStyle {

    @XmlEnumValue("esriMSNone")
    ESRI_MS_NONE("esriMSNone"),
    @XmlEnumValue("esriMSHalo")
    ESRI_MS_HALO("esriMSHalo");
    private final String value;

    EsriMaskStyle(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriMaskStyle fromValue(String v) {
        for (EsriMaskStyle c: EsriMaskStyle.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

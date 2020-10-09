
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTextCase.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTextCase"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTCNormal"/&amp;gt;
 *     &amp;lt;enumeration value="esriTCLowercase"/&amp;gt;
 *     &amp;lt;enumeration value="esriTCAllCaps"/&amp;gt;
 *     &amp;lt;enumeration value="esriTCSmallCaps"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTextCase")
@XmlEnum
public enum EsriTextCase {

    @XmlEnumValue("esriTCNormal")
    ESRI_TC_NORMAL("esriTCNormal"),
    @XmlEnumValue("esriTCLowercase")
    ESRI_TC_LOWERCASE("esriTCLowercase"),
    @XmlEnumValue("esriTCAllCaps")
    ESRI_TC_ALL_CAPS("esriTCAllCaps"),
    @XmlEnumValue("esriTCSmallCaps")
    ESRI_TC_SMALL_CAPS("esriTCSmallCaps");
    private final String value;

    EsriTextCase(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTextCase fromValue(String v) {
        for (EsriTextCase c: EsriTextCase.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

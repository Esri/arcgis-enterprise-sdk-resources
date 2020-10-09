
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTextHorizontalAlignment.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTextHorizontalAlignment"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTHALeft"/&amp;gt;
 *     &amp;lt;enumeration value="esriTHACenter"/&amp;gt;
 *     &amp;lt;enumeration value="esriTHARight"/&amp;gt;
 *     &amp;lt;enumeration value="esriTHAFull"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTextHorizontalAlignment")
@XmlEnum
public enum EsriTextHorizontalAlignment {

    @XmlEnumValue("esriTHALeft")
    ESRI_THA_LEFT("esriTHALeft"),
    @XmlEnumValue("esriTHACenter")
    ESRI_THA_CENTER("esriTHACenter"),
    @XmlEnumValue("esriTHARight")
    ESRI_THA_RIGHT("esriTHARight"),
    @XmlEnumValue("esriTHAFull")
    ESRI_THA_FULL("esriTHAFull");
    private final String value;

    EsriTextHorizontalAlignment(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTextHorizontalAlignment fromValue(String v) {
        for (EsriTextHorizontalAlignment c: EsriTextHorizontalAlignment.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

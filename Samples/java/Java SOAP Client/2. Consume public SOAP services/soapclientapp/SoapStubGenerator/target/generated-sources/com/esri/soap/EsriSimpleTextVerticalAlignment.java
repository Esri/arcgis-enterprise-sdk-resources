
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSimpleTextVerticalAlignment.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSimpleTextVerticalAlignment"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="top"/&amp;gt;
 *     &amp;lt;enumeration value="middle"/&amp;gt;
 *     &amp;lt;enumeration value="baseline"/&amp;gt;
 *     &amp;lt;enumeration value="bottom"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSimpleTextVerticalAlignment")
@XmlEnum
public enum EsriSimpleTextVerticalAlignment {

    @XmlEnumValue("top")
    TOP("top"),
    @XmlEnumValue("middle")
    MIDDLE("middle"),
    @XmlEnumValue("baseline")
    BASELINE("baseline"),
    @XmlEnumValue("bottom")
    BOTTOM("bottom");
    private final String value;

    EsriSimpleTextVerticalAlignment(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSimpleTextVerticalAlignment fromValue(String v) {
        for (EsriSimpleTextVerticalAlignment c: EsriSimpleTextVerticalAlignment.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

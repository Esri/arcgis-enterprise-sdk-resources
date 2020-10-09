
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSimpleTextHorizontalAlignment.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSimpleTextHorizontalAlignment"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="left"/&amp;gt;
 *     &amp;lt;enumeration value="center"/&amp;gt;
 *     &amp;lt;enumeration value="right"/&amp;gt;
 *     &amp;lt;enumeration value="justified"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSimpleTextHorizontalAlignment")
@XmlEnum
public enum EsriSimpleTextHorizontalAlignment {

    @XmlEnumValue("left")
    LEFT("left"),
    @XmlEnumValue("center")
    CENTER("center"),
    @XmlEnumValue("right")
    RIGHT("right"),
    @XmlEnumValue("justified")
    JUSTIFIED("justified");
    private final String value;

    EsriSimpleTextHorizontalAlignment(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSimpleTextHorizontalAlignment fromValue(String v) {
        for (EsriSimpleTextHorizontalAlignment c: EsriSimpleTextHorizontalAlignment.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

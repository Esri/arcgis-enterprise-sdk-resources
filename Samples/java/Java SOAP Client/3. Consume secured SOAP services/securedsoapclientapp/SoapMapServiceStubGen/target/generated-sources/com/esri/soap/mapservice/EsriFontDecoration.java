
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriFontDecoration.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriFontDecoration"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="none"/&amp;gt;
 *     &amp;lt;enumeration value="underline"/&amp;gt;
 *     &amp;lt;enumeration value="line-through"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriFontDecoration")
@XmlEnum
public enum EsriFontDecoration {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("underline")
    UNDERLINE("underline"),
    @XmlEnumValue("line-through")
    LINE_THROUGH("line-through");
    private final String value;

    EsriFontDecoration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriFontDecoration fromValue(String v) {
        for (EsriFontDecoration c: EsriFontDecoration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

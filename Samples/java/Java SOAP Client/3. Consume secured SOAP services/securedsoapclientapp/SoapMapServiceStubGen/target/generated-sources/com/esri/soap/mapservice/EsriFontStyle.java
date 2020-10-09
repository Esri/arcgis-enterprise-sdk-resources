
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriFontStyle.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriFontStyle"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="normal"/&amp;gt;
 *     &amp;lt;enumeration value="italic"/&amp;gt;
 *     &amp;lt;enumeration value="oblique"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriFontStyle")
@XmlEnum
public enum EsriFontStyle {

    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("italic")
    ITALIC("italic"),
    @XmlEnumValue("oblique")
    OBLIQUE("oblique");
    private final String value;

    EsriFontStyle(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriFontStyle fromValue(String v) {
        for (EsriFontStyle c: EsriFontStyle.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriFontWeight.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriFontWeight"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="normal"/&amp;gt;
 *     &amp;lt;enumeration value="bold"/&amp;gt;
 *     &amp;lt;enumeration value="bolder"/&amp;gt;
 *     &amp;lt;enumeration value="lighter"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriFontWeight")
@XmlEnum
public enum EsriFontWeight {

    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("bold")
    BOLD("bold"),
    @XmlEnumValue("bolder")
    BOLDER("bolder"),
    @XmlEnumValue("lighter")
    LIGHTER("lighter");
    private final String value;

    EsriFontWeight(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriFontWeight fromValue(String v) {
        for (EsriFontWeight c: EsriFontWeight.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

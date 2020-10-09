
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriRotationType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriRotationType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriRotateSymbolGeographic"/&amp;gt;
 *     &amp;lt;enumeration value="esriRotateSymbolArithmetic"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriRotationType")
@XmlEnum
public enum EsriRotationType {


    /**
     * Clockwise rotation with 0 at the positive y-axis.
     * 
     */
    @XmlEnumValue("esriRotateSymbolGeographic")
    ESRI_ROTATE_SYMBOL_GEOGRAPHIC("esriRotateSymbolGeographic"),

    /**
     * Counter clockwise rotation with 0 at the positive x-axis.
     * 
     */
    @XmlEnumValue("esriRotateSymbolArithmetic")
    ESRI_ROTATE_SYMBOL_ARITHMETIC("esriRotateSymbolArithmetic");
    private final String value;

    EsriRotationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriRotationType fromValue(String v) {
        for (EsriRotationType c: EsriRotationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

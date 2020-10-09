
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSearchOrder.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSearchOrder"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriSearchOrderSpatial"/&amp;gt;
 *     &amp;lt;enumeration value="esriSearchOrderAttribute"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSearchOrder")
@XmlEnum
public enum EsriSearchOrder {


    /**
     * Spatial query is applied first.
     * 
     */
    @XmlEnumValue("esriSearchOrderSpatial")
    ESRI_SEARCH_ORDER_SPATIAL("esriSearchOrderSpatial"),

    /**
     * Attribute query is applied first.
     * 
     */
    @XmlEnumValue("esriSearchOrderAttribute")
    ESRI_SEARCH_ORDER_ATTRIBUTE("esriSearchOrderAttribute");
    private final String value;

    EsriSearchOrder(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSearchOrder fromValue(String v) {
        for (EsriSearchOrder c: EsriSearchOrder.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

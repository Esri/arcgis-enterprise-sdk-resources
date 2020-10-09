
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriJoinType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriJoinType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriLeftOuterJoin"/&amp;gt;
 *     &amp;lt;enumeration value="esriLeftInnerJoin"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriJoinType")
@XmlEnum
public enum EsriJoinType {

    @XmlEnumValue("esriLeftOuterJoin")
    ESRI_LEFT_OUTER_JOIN("esriLeftOuterJoin"),
    @XmlEnumValue("esriLeftInnerJoin")
    ESRI_LEFT_INNER_JOIN("esriLeftInnerJoin");
    private final String value;

    EsriJoinType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriJoinType fromValue(String v) {
        for (EsriJoinType c: EsriJoinType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

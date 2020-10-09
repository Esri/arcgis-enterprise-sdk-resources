
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTimeRelation.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTimeRelation"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTimeRelationOverlaps"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeRelationOverlapsStartWithinEnd"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeRelationAfterStartOverlapsEnd"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTimeRelation")
@XmlEnum
public enum EsriTimeRelation {

    @XmlEnumValue("esriTimeRelationOverlaps")
    ESRI_TIME_RELATION_OVERLAPS("esriTimeRelationOverlaps"),
    @XmlEnumValue("esriTimeRelationOverlapsStartWithinEnd")
    ESRI_TIME_RELATION_OVERLAPS_START_WITHIN_END("esriTimeRelationOverlapsStartWithinEnd"),
    @XmlEnumValue("esriTimeRelationAfterStartOverlapsEnd")
    ESRI_TIME_RELATION_AFTER_START_OVERLAPS_END("esriTimeRelationAfterStartOverlapsEnd");
    private final String value;

    EsriTimeRelation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTimeRelation fromValue(String v) {
        for (EsriTimeRelation c: EsriTimeRelation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

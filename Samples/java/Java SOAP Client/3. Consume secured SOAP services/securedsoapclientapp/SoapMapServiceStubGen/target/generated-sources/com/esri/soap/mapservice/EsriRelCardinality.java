
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriRelCardinality.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriRelCardinality"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriRelCardinalityOneToOne"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelCardinalityOneToMany"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelCardinalityManyToMany"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriRelCardinality")
@XmlEnum
public enum EsriRelCardinality {


    /**
     * One To One.
     * 
     */
    @XmlEnumValue("esriRelCardinalityOneToOne")
    ESRI_REL_CARDINALITY_ONE_TO_ONE("esriRelCardinalityOneToOne"),

    /**
     * One To Many.
     * 
     */
    @XmlEnumValue("esriRelCardinalityOneToMany")
    ESRI_REL_CARDINALITY_ONE_TO_MANY("esriRelCardinalityOneToMany"),

    /**
     * Many To Many.
     * 
     */
    @XmlEnumValue("esriRelCardinalityManyToMany")
    ESRI_REL_CARDINALITY_MANY_TO_MANY("esriRelCardinalityManyToMany");
    private final String value;

    EsriRelCardinality(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriRelCardinality fromValue(String v) {
        for (EsriRelCardinality c: EsriRelCardinality.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

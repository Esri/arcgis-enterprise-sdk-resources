
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSplitPolicyType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSplitPolicyType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriSPTGeometryRatio"/&amp;gt;
 *     &amp;lt;enumeration value="esriSPTDuplicate"/&amp;gt;
 *     &amp;lt;enumeration value="esriSPTDefaultValue"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSplitPolicyType")
@XmlEnum
public enum EsriSplitPolicyType {


    /**
     * Geometry-ratioed split policy.
     * 
     */
    @XmlEnumValue("esriSPTGeometryRatio")
    ESRI_SPT_GEOMETRY_RATIO("esriSPTGeometryRatio"),

    /**
     * Duplicate split policy.
     * 
     */
    @XmlEnumValue("esriSPTDuplicate")
    ESRI_SPT_DUPLICATE("esriSPTDuplicate"),

    /**
     * Default value split policy.
     * 
     */
    @XmlEnumValue("esriSPTDefaultValue")
    ESRI_SPT_DEFAULT_VALUE("esriSPTDefaultValue");
    private final String value;

    EsriSplitPolicyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSplitPolicyType fromValue(String v) {
        for (EsriSplitPolicyType c: EsriSplitPolicyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriMergePolicyType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriMergePolicyType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriMPTSumValues"/&amp;gt;
 *     &amp;lt;enumeration value="esriMPTAreaWeighted"/&amp;gt;
 *     &amp;lt;enumeration value="esriMPTDefaultValue"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriMergePolicyType")
@XmlEnum
public enum EsriMergePolicyType {


    /**
     * Sum the values merge policy.
     * 
     */
    @XmlEnumValue("esriMPTSumValues")
    ESRI_MPT_SUM_VALUES("esriMPTSumValues"),

    /**
     * Area weighted merge policy.
     * 
     */
    @XmlEnumValue("esriMPTAreaWeighted")
    ESRI_MPT_AREA_WEIGHTED("esriMPTAreaWeighted"),

    /**
     * Default value merge policy.
     * 
     */
    @XmlEnumValue("esriMPTDefaultValue")
    ESRI_MPT_DEFAULT_VALUE("esriMPTDefaultValue");
    private final String value;

    EsriMergePolicyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriMergePolicyType fromValue(String v) {
        for (EsriMergePolicyType c: EsriMergePolicyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

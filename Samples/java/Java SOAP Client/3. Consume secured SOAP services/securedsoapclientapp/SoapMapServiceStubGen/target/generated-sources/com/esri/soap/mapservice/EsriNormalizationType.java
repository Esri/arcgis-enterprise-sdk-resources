
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriNormalizationType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriNormalizationType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriNormalizeByField"/&amp;gt;
 *     &amp;lt;enumeration value="esriNormalizeByLog"/&amp;gt;
 *     &amp;lt;enumeration value="esriNormalizeByPercentOfTotal"/&amp;gt;
 *     &amp;lt;enumeration value="esriNormalizeByArea"/&amp;gt;
 *     &amp;lt;enumeration value="esriNormalizeByNothing"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriNormalizationType")
@XmlEnum
public enum EsriNormalizationType {


    /**
     * Normalize by field.
     * 
     */
    @XmlEnumValue("esriNormalizeByField")
    ESRI_NORMALIZE_BY_FIELD("esriNormalizeByField"),

    /**
     * Normalize by Log.
     * 
     */
    @XmlEnumValue("esriNormalizeByLog")
    ESRI_NORMALIZE_BY_LOG("esriNormalizeByLog"),

    /**
     * Normalize by percent of total.
     * 
     */
    @XmlEnumValue("esriNormalizeByPercentOfTotal")
    ESRI_NORMALIZE_BY_PERCENT_OF_TOTAL("esriNormalizeByPercentOfTotal"),

    /**
     * Normalize by area.
     * 
     */
    @XmlEnumValue("esriNormalizeByArea")
    ESRI_NORMALIZE_BY_AREA("esriNormalizeByArea"),

    /**
     * Do not Normalize.
     * 
     */
    @XmlEnumValue("esriNormalizeByNothing")
    ESRI_NORMALIZE_BY_NOTHING("esriNormalizeByNothing");
    private final String value;

    EsriNormalizationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriNormalizationType fromValue(String v) {
        for (EsriNormalizationType c: EsriNormalizationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriClassifyMethod.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriClassifyMethod"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriClassifyNaturalBreaks"/&amp;gt;
 *     &amp;lt;enumeration value="esriClassifyEqualInterval"/&amp;gt;
 *     &amp;lt;enumeration value="esriClassifyQuantile"/&amp;gt;
 *     &amp;lt;enumeration value="esriClassifyStandardDeviation"/&amp;gt;
 *     &amp;lt;enumeration value="esriClassifyGeometricalInterval"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriClassifyMethod")
@XmlEnum
public enum EsriClassifyMethod {


    /**
     * Classify using Natural Breaks.
     * 
     */
    @XmlEnumValue("esriClassifyNaturalBreaks")
    ESRI_CLASSIFY_NATURAL_BREAKS("esriClassifyNaturalBreaks"),

    /**
     * Classify using Equal Interval.
     * 
     */
    @XmlEnumValue("esriClassifyEqualInterval")
    ESRI_CLASSIFY_EQUAL_INTERVAL("esriClassifyEqualInterval"),

    /**
     * Classify using Quantile.
     * 
     */
    @XmlEnumValue("esriClassifyQuantile")
    ESRI_CLASSIFY_QUANTILE("esriClassifyQuantile"),

    /**
     * Classify using Standard Deviation.
     * 
     */
    @XmlEnumValue("esriClassifyStandardDeviation")
    ESRI_CLASSIFY_STANDARD_DEVIATION("esriClassifyStandardDeviation"),

    /**
     * Classify using Geometrical Interval.
     * 
     */
    @XmlEnumValue("esriClassifyGeometricalInterval")
    ESRI_CLASSIFY_GEOMETRICAL_INTERVAL("esriClassifyGeometricalInterval");
    private final String value;

    EsriClassifyMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriClassifyMethod fromValue(String v) {
        for (EsriClassifyMethod c: EsriClassifyMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

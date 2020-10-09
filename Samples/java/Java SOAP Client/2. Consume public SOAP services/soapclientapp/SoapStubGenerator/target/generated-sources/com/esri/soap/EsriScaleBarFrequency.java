
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriScaleBarFrequency.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriScaleBarFrequency"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarNone"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarOne"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarMajorDivisions"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarDivisions"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarDivisionsAndFirstMidpoint"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarDivisionsAndFirstSubdivisions"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarDivisionsAndSubdivisions"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriScaleBarFrequency")
@XmlEnum
public enum EsriScaleBarFrequency {

    @XmlEnumValue("esriScaleBarNone")
    ESRI_SCALE_BAR_NONE("esriScaleBarNone"),
    @XmlEnumValue("esriScaleBarOne")
    ESRI_SCALE_BAR_ONE("esriScaleBarOne"),
    @XmlEnumValue("esriScaleBarMajorDivisions")
    ESRI_SCALE_BAR_MAJOR_DIVISIONS("esriScaleBarMajorDivisions"),
    @XmlEnumValue("esriScaleBarDivisions")
    ESRI_SCALE_BAR_DIVISIONS("esriScaleBarDivisions"),
    @XmlEnumValue("esriScaleBarDivisionsAndFirstMidpoint")
    ESRI_SCALE_BAR_DIVISIONS_AND_FIRST_MIDPOINT("esriScaleBarDivisionsAndFirstMidpoint"),
    @XmlEnumValue("esriScaleBarDivisionsAndFirstSubdivisions")
    ESRI_SCALE_BAR_DIVISIONS_AND_FIRST_SUBDIVISIONS("esriScaleBarDivisionsAndFirstSubdivisions"),
    @XmlEnumValue("esriScaleBarDivisionsAndSubdivisions")
    ESRI_SCALE_BAR_DIVISIONS_AND_SUBDIVISIONS("esriScaleBarDivisionsAndSubdivisions");
    private final String value;

    EsriScaleBarFrequency(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriScaleBarFrequency fromValue(String v) {
        for (EsriScaleBarFrequency c: EsriScaleBarFrequency.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

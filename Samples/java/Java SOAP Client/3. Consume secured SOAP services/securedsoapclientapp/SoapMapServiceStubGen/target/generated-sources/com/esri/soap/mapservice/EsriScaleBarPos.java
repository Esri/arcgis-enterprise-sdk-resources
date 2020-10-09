
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriScaleBarPos.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriScaleBarPos"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarAbove"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarBeforeLabels"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarAfterLabels"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarBeforeBar"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarAfterBar"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarBelow"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriScaleBarPos")
@XmlEnum
public enum EsriScaleBarPos {

    @XmlEnumValue("esriScaleBarAbove")
    ESRI_SCALE_BAR_ABOVE("esriScaleBarAbove"),
    @XmlEnumValue("esriScaleBarBeforeLabels")
    ESRI_SCALE_BAR_BEFORE_LABELS("esriScaleBarBeforeLabels"),
    @XmlEnumValue("esriScaleBarAfterLabels")
    ESRI_SCALE_BAR_AFTER_LABELS("esriScaleBarAfterLabels"),
    @XmlEnumValue("esriScaleBarBeforeBar")
    ESRI_SCALE_BAR_BEFORE_BAR("esriScaleBarBeforeBar"),
    @XmlEnumValue("esriScaleBarAfterBar")
    ESRI_SCALE_BAR_AFTER_BAR("esriScaleBarAfterBar"),
    @XmlEnumValue("esriScaleBarBelow")
    ESRI_SCALE_BAR_BELOW("esriScaleBarBelow");
    private final String value;

    EsriScaleBarPos(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriScaleBarPos fromValue(String v) {
        for (EsriScaleBarPos c: EsriScaleBarPos.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

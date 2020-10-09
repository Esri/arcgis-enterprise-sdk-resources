
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriScaleBarResizeHint.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriScaleBarResizeHint"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarFixed"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarAutoDivision"/&amp;gt;
 *     &amp;lt;enumeration value="esriScaleBarAutoDivisions"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriScaleBarResizeHint")
@XmlEnum
public enum EsriScaleBarResizeHint {

    @XmlEnumValue("esriScaleBarFixed")
    ESRI_SCALE_BAR_FIXED("esriScaleBarFixed"),
    @XmlEnumValue("esriScaleBarAutoDivision")
    ESRI_SCALE_BAR_AUTO_DIVISION("esriScaleBarAutoDivision"),
    @XmlEnumValue("esriScaleBarAutoDivisions")
    ESRI_SCALE_BAR_AUTO_DIVISIONS("esriScaleBarAutoDivisions");
    private final String value;

    EsriScaleBarResizeHint(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriScaleBarResizeHint fromValue(String v) {
        for (EsriScaleBarResizeHint c: EsriScaleBarResizeHint.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

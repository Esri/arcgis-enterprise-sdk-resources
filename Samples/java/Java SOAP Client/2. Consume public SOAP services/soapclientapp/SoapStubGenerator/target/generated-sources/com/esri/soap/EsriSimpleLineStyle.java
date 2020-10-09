
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSimpleLineStyle.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSimpleLineStyle"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriSLSSolid"/&amp;gt;
 *     &amp;lt;enumeration value="esriSLSDash"/&amp;gt;
 *     &amp;lt;enumeration value="esriSLSDot"/&amp;gt;
 *     &amp;lt;enumeration value="esriSLSDashDot"/&amp;gt;
 *     &amp;lt;enumeration value="esriSLSDashDotDot"/&amp;gt;
 *     &amp;lt;enumeration value="esriSLSNull"/&amp;gt;
 *     &amp;lt;enumeration value="esriSLSInsideFrame"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSimpleLineStyle")
@XmlEnum
public enum EsriSimpleLineStyle {

    @XmlEnumValue("esriSLSSolid")
    ESRI_SLS_SOLID("esriSLSSolid"),
    @XmlEnumValue("esriSLSDash")
    ESRI_SLS_DASH("esriSLSDash"),
    @XmlEnumValue("esriSLSDot")
    ESRI_SLS_DOT("esriSLSDot"),
    @XmlEnumValue("esriSLSDashDot")
    ESRI_SLS_DASH_DOT("esriSLSDashDot"),
    @XmlEnumValue("esriSLSDashDotDot")
    ESRI_SLS_DASH_DOT_DOT("esriSLSDashDotDot"),
    @XmlEnumValue("esriSLSNull")
    ESRI_SLS_NULL("esriSLSNull"),
    @XmlEnumValue("esriSLSInsideFrame")
    ESRI_SLS_INSIDE_FRAME("esriSLSInsideFrame");
    private final String value;

    EsriSimpleLineStyle(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSimpleLineStyle fromValue(String v) {
        for (EsriSimpleLineStyle c: EsriSimpleLineStyle.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

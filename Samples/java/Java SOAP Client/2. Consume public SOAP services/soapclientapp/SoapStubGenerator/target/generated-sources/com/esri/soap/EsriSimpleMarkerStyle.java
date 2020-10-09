
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSimpleMarkerStyle.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSimpleMarkerStyle"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriSMSCircle"/&amp;gt;
 *     &amp;lt;enumeration value="esriSMSSquare"/&amp;gt;
 *     &amp;lt;enumeration value="esriSMSCross"/&amp;gt;
 *     &amp;lt;enumeration value="esriSMSX"/&amp;gt;
 *     &amp;lt;enumeration value="esriSMSDiamond"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSimpleMarkerStyle")
@XmlEnum
public enum EsriSimpleMarkerStyle {

    @XmlEnumValue("esriSMSCircle")
    ESRI_SMS_CIRCLE("esriSMSCircle"),
    @XmlEnumValue("esriSMSSquare")
    ESRI_SMS_SQUARE("esriSMSSquare"),
    @XmlEnumValue("esriSMSCross")
    ESRI_SMS_CROSS("esriSMSCross"),
    @XmlEnumValue("esriSMSX")
    ESRI_SMSX("esriSMSX"),
    @XmlEnumValue("esriSMSDiamond")
    ESRI_SMS_DIAMOND("esriSMSDiamond");
    private final String value;

    EsriSimpleMarkerStyle(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSimpleMarkerStyle fromValue(String v) {
        for (EsriSimpleMarkerStyle c: EsriSimpleMarkerStyle.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

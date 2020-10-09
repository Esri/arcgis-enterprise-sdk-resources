
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTextDirection.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTextDirection"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTDHorizontal"/&amp;gt;
 *     &amp;lt;enumeration value="esriTDAngle"/&amp;gt;
 *     &amp;lt;enumeration value="esriTDVertical"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTextDirection")
@XmlEnum
public enum EsriTextDirection {

    @XmlEnumValue("esriTDHorizontal")
    ESRI_TD_HORIZONTAL("esriTDHorizontal"),
    @XmlEnumValue("esriTDAngle")
    ESRI_TD_ANGLE("esriTDAngle"),
    @XmlEnumValue("esriTDVertical")
    ESRI_TD_VERTICAL("esriTDVertical");
    private final String value;

    EsriTextDirection(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTextDirection fromValue(String v) {
        for (EsriTextDirection c: EsriTextDirection.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

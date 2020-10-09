
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTextPosition.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTextPosition"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTPNormal"/&amp;gt;
 *     &amp;lt;enumeration value="esriTPSuperscript"/&amp;gt;
 *     &amp;lt;enumeration value="esriTPSubscript"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTextPosition")
@XmlEnum
public enum EsriTextPosition {

    @XmlEnumValue("esriTPNormal")
    ESRI_TP_NORMAL("esriTPNormal"),
    @XmlEnumValue("esriTPSuperscript")
    ESRI_TP_SUPERSCRIPT("esriTPSuperscript"),
    @XmlEnumValue("esriTPSubscript")
    ESRI_TP_SUBSCRIPT("esriTPSubscript");
    private final String value;

    EsriTextPosition(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTextPosition fromValue(String v) {
        for (EsriTextPosition c: EsriTextPosition.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

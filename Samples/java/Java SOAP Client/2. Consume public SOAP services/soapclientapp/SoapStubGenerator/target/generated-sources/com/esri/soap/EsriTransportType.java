
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTransportType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTransportType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTransportTypeEmbedded"/&amp;gt;
 *     &amp;lt;enumeration value="esriTransportTypeUrl"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTransportType")
@XmlEnum
public enum EsriTransportType {

    @XmlEnumValue("esriTransportTypeEmbedded")
    ESRI_TRANSPORT_TYPE_EMBEDDED("esriTransportTypeEmbedded"),
    @XmlEnumValue("esriTransportTypeUrl")
    ESRI_TRANSPORT_TYPE_URL("esriTransportTypeUrl");
    private final String value;

    EsriTransportType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTransportType fromValue(String v) {
        for (EsriTransportType c: EsriTransportType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

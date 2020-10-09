
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriCachedMapServiceType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriCachedMapServiceType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriSingleFusedMapCache"/&amp;gt;
 *     &amp;lt;enumeration value="esriIndividualLayerCaches"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriCachedMapServiceType")
@XmlEnum
public enum EsriCachedMapServiceType {

    @XmlEnumValue("esriSingleFusedMapCache")
    ESRI_SINGLE_FUSED_MAP_CACHE("esriSingleFusedMapCache"),
    @XmlEnumValue("esriIndividualLayerCaches")
    ESRI_INDIVIDUAL_LAYER_CACHES("esriIndividualLayerCaches");
    private final String value;

    EsriCachedMapServiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriCachedMapServiceType fromValue(String v) {
        for (EsriCachedMapServiceType c: EsriCachedMapServiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriServerPolygonLabelPlacementType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriServerPolygonLabelPlacementType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriServerPolygonPlacementAlwaysHorizontal"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriServerPolygonLabelPlacementType")
@XmlEnum
public enum EsriServerPolygonLabelPlacementType {

    @XmlEnumValue("esriServerPolygonPlacementAlwaysHorizontal")
    ESRI_SERVER_POLYGON_PLACEMENT_ALWAYS_HORIZONTAL("esriServerPolygonPlacementAlwaysHorizontal");
    private final String value;

    EsriServerPolygonLabelPlacementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriServerPolygonLabelPlacementType fromValue(String v) {
        for (EsriServerPolygonLabelPlacementType c: EsriServerPolygonLabelPlacementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

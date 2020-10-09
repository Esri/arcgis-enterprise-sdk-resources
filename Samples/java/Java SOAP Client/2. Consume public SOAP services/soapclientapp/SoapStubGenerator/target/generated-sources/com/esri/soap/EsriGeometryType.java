
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriGeometryType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriGeometryType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriGeometryPoint"/&amp;gt;
 *     &amp;lt;enumeration value="esriGeometryMultipoint"/&amp;gt;
 *     &amp;lt;enumeration value="esriGeometryPolyline"/&amp;gt;
 *     &amp;lt;enumeration value="esriGeometryPolygon"/&amp;gt;
 *     &amp;lt;enumeration value="esriGeometryMultiPatch"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriGeometryType")
@XmlEnum
public enum EsriGeometryType {

    @XmlEnumValue("esriGeometryPoint")
    ESRI_GEOMETRY_POINT("esriGeometryPoint"),
    @XmlEnumValue("esriGeometryMultipoint")
    ESRI_GEOMETRY_MULTIPOINT("esriGeometryMultipoint"),
    @XmlEnumValue("esriGeometryPolyline")
    ESRI_GEOMETRY_POLYLINE("esriGeometryPolyline"),
    @XmlEnumValue("esriGeometryPolygon")
    ESRI_GEOMETRY_POLYGON("esriGeometryPolygon"),
    @XmlEnumValue("esriGeometryMultiPatch")
    ESRI_GEOMETRY_MULTI_PATCH("esriGeometryMultiPatch");
    private final String value;

    EsriGeometryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriGeometryType fromValue(String v) {
        for (EsriGeometryType c: EsriGeometryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

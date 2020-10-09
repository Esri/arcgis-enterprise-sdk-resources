
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriFieldType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriFieldType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeInteger"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeSmallInteger"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeDouble"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeSingle"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeString"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeDate"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeGeometry"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeOID"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeBlob"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeGlobalID"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeRaster"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeGUID"/&amp;gt;
 *     &amp;lt;enumeration value="esriFieldTypeXML"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriFieldType")
@XmlEnum
public enum EsriFieldType {


    /**
     * Long Integer.
     * 
     */
    @XmlEnumValue("esriFieldTypeInteger")
    ESRI_FIELD_TYPE_INTEGER("esriFieldTypeInteger"),

    /**
     * Integer.
     * 
     */
    @XmlEnumValue("esriFieldTypeSmallInteger")
    ESRI_FIELD_TYPE_SMALL_INTEGER("esriFieldTypeSmallInteger"),

    /**
     * Double-precision floating-point number.
     * 
     */
    @XmlEnumValue("esriFieldTypeDouble")
    ESRI_FIELD_TYPE_DOUBLE("esriFieldTypeDouble"),

    /**
     * Single-precision floating-point number.
     * 
     */
    @XmlEnumValue("esriFieldTypeSingle")
    ESRI_FIELD_TYPE_SINGLE("esriFieldTypeSingle"),

    /**
     * Character string.
     * 
     */
    @XmlEnumValue("esriFieldTypeString")
    ESRI_FIELD_TYPE_STRING("esriFieldTypeString"),

    /**
     * Date.
     * 
     */
    @XmlEnumValue("esriFieldTypeDate")
    ESRI_FIELD_TYPE_DATE("esriFieldTypeDate"),

    /**
     * Geometry.
     * 
     */
    @XmlEnumValue("esriFieldTypeGeometry")
    ESRI_FIELD_TYPE_GEOMETRY("esriFieldTypeGeometry"),

    /**
     * Long Integer representing an object identifier.
     * 
     */
    @XmlEnumValue("esriFieldTypeOID")
    ESRI_FIELD_TYPE_OID("esriFieldTypeOID"),

    /**
     * Binary Large Object.
     * 
     */
    @XmlEnumValue("esriFieldTypeBlob")
    ESRI_FIELD_TYPE_BLOB("esriFieldTypeBlob"),

    /**
     * Esri Global ID.
     * 
     */
    @XmlEnumValue("esriFieldTypeGlobalID")
    ESRI_FIELD_TYPE_GLOBAL_ID("esriFieldTypeGlobalID"),

    /**
     * Raster.
     * 
     */
    @XmlEnumValue("esriFieldTypeRaster")
    ESRI_FIELD_TYPE_RASTER("esriFieldTypeRaster"),

    /**
     * Globally Unique Identifier.
     * 
     */
    @XmlEnumValue("esriFieldTypeGUID")
    ESRI_FIELD_TYPE_GUID("esriFieldTypeGUID"),

    /**
     * XML Document
     * 
     */
    @XmlEnumValue("esriFieldTypeXML")
    ESRI_FIELD_TYPE_XML("esriFieldTypeXML");
    private final String value;

    EsriFieldType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriFieldType fromValue(String v) {
        for (EsriFieldType c: EsriFieldType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

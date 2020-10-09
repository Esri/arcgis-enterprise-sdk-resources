
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriQueryResultFormat.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriQueryResultFormat"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultRecordSetAsObject"/&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultJsonAsMime"/&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultJsonAsURL"/&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultAmfAsMime"/&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultAmfAsURL"/&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultKMLAsMime"/&amp;gt;
 *     &amp;lt;enumeration value="esriQueryResultKMLAsURL"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriQueryResultFormat")
@XmlEnum
public enum EsriQueryResultFormat {

    @XmlEnumValue("esriQueryResultRecordSetAsObject")
    ESRI_QUERY_RESULT_RECORD_SET_AS_OBJECT("esriQueryResultRecordSetAsObject"),
    @XmlEnumValue("esriQueryResultJsonAsMime")
    ESRI_QUERY_RESULT_JSON_AS_MIME("esriQueryResultJsonAsMime"),
    @XmlEnumValue("esriQueryResultJsonAsURL")
    ESRI_QUERY_RESULT_JSON_AS_URL("esriQueryResultJsonAsURL"),
    @XmlEnumValue("esriQueryResultAmfAsMime")
    ESRI_QUERY_RESULT_AMF_AS_MIME("esriQueryResultAmfAsMime"),
    @XmlEnumValue("esriQueryResultAmfAsURL")
    ESRI_QUERY_RESULT_AMF_AS_URL("esriQueryResultAmfAsURL"),
    @XmlEnumValue("esriQueryResultKMLAsMime")
    ESRI_QUERY_RESULT_KML_AS_MIME("esriQueryResultKMLAsMime"),
    @XmlEnumValue("esriQueryResultKMLAsURL")
    ESRI_QUERY_RESULT_KML_AS_URL("esriQueryResultKMLAsURL");
    private final String value;

    EsriQueryResultFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriQueryResultFormat fromValue(String v) {
        for (EsriQueryResultFormat c: EsriQueryResultFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

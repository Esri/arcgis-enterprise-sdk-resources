
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriRelateResultFormat.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriRelateResultFormat"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriRelateResultRelatedRecordSetAsObject"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelateResultJsonAsMime"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelateResultJsonAsURL"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelateResultAmfAsMime"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelateResultAmfAsURL"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriRelateResultFormat")
@XmlEnum
public enum EsriRelateResultFormat {

    @XmlEnumValue("esriRelateResultRelatedRecordSetAsObject")
    ESRI_RELATE_RESULT_RELATED_RECORD_SET_AS_OBJECT("esriRelateResultRelatedRecordSetAsObject"),
    @XmlEnumValue("esriRelateResultJsonAsMime")
    ESRI_RELATE_RESULT_JSON_AS_MIME("esriRelateResultJsonAsMime"),
    @XmlEnumValue("esriRelateResultJsonAsURL")
    ESRI_RELATE_RESULT_JSON_AS_URL("esriRelateResultJsonAsURL"),
    @XmlEnumValue("esriRelateResultAmfAsMime")
    ESRI_RELATE_RESULT_AMF_AS_MIME("esriRelateResultAmfAsMime"),
    @XmlEnumValue("esriRelateResultAmfAsURL")
    ESRI_RELATE_RESULT_AMF_AS_URL("esriRelateResultAmfAsURL");
    private final String value;

    EsriRelateResultFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriRelateResultFormat fromValue(String v) {
        for (EsriRelateResultFormat c: EsriRelateResultFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

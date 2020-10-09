
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriDataStatType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriDataStatType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeCount"/&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeSum"/&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeMin"/&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeMax"/&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeAverage"/&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeStdDev"/&amp;gt;
 *     &amp;lt;enumeration value="esriDataStatTypeVariance"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriDataStatType")
@XmlEnum
public enum EsriDataStatType {

    @XmlEnumValue("esriDataStatTypeCount")
    ESRI_DATA_STAT_TYPE_COUNT("esriDataStatTypeCount"),
    @XmlEnumValue("esriDataStatTypeSum")
    ESRI_DATA_STAT_TYPE_SUM("esriDataStatTypeSum"),
    @XmlEnumValue("esriDataStatTypeMin")
    ESRI_DATA_STAT_TYPE_MIN("esriDataStatTypeMin"),
    @XmlEnumValue("esriDataStatTypeMax")
    ESRI_DATA_STAT_TYPE_MAX("esriDataStatTypeMax"),
    @XmlEnumValue("esriDataStatTypeAverage")
    ESRI_DATA_STAT_TYPE_AVERAGE("esriDataStatTypeAverage"),
    @XmlEnumValue("esriDataStatTypeStdDev")
    ESRI_DATA_STAT_TYPE_STD_DEV("esriDataStatTypeStdDev"),
    @XmlEnumValue("esriDataStatTypeVariance")
    ESRI_DATA_STAT_TYPE_VARIANCE("esriDataStatTypeVariance");
    private final String value;

    EsriDataStatType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriDataStatType fromValue(String v) {
        for (EsriDataStatType c: EsriDataStatType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

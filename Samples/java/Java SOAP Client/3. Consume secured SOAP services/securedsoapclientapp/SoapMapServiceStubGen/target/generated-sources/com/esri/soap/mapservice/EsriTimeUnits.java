
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTimeUnits.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTimeUnits"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsUnknown"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsMilliseconds"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsSeconds"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsMinutes"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsHours"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsDays"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsWeeks"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsMonths"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsYears"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsDecades"/&amp;gt;
 *     &amp;lt;enumeration value="esriTimeUnitsCenturies"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTimeUnits")
@XmlEnum
public enum EsriTimeUnits {

    @XmlEnumValue("esriTimeUnitsUnknown")
    ESRI_TIME_UNITS_UNKNOWN("esriTimeUnitsUnknown"),
    @XmlEnumValue("esriTimeUnitsMilliseconds")
    ESRI_TIME_UNITS_MILLISECONDS("esriTimeUnitsMilliseconds"),
    @XmlEnumValue("esriTimeUnitsSeconds")
    ESRI_TIME_UNITS_SECONDS("esriTimeUnitsSeconds"),
    @XmlEnumValue("esriTimeUnitsMinutes")
    ESRI_TIME_UNITS_MINUTES("esriTimeUnitsMinutes"),
    @XmlEnumValue("esriTimeUnitsHours")
    ESRI_TIME_UNITS_HOURS("esriTimeUnitsHours"),
    @XmlEnumValue("esriTimeUnitsDays")
    ESRI_TIME_UNITS_DAYS("esriTimeUnitsDays"),
    @XmlEnumValue("esriTimeUnitsWeeks")
    ESRI_TIME_UNITS_WEEKS("esriTimeUnitsWeeks"),
    @XmlEnumValue("esriTimeUnitsMonths")
    ESRI_TIME_UNITS_MONTHS("esriTimeUnitsMonths"),
    @XmlEnumValue("esriTimeUnitsYears")
    ESRI_TIME_UNITS_YEARS("esriTimeUnitsYears"),
    @XmlEnumValue("esriTimeUnitsDecades")
    ESRI_TIME_UNITS_DECADES("esriTimeUnitsDecades"),
    @XmlEnumValue("esriTimeUnitsCenturies")
    ESRI_TIME_UNITS_CENTURIES("esriTimeUnitsCenturies");
    private final String value;

    EsriTimeUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTimeUnits fromValue(String v) {
        for (EsriTimeUnits c: EsriTimeUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

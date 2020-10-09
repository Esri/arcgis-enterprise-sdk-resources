
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriUnits.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriUnits"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriUnknownUnits"/&amp;gt;
 *     &amp;lt;enumeration value="esriInches"/&amp;gt;
 *     &amp;lt;enumeration value="esriPoints"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeet"/&amp;gt;
 *     &amp;lt;enumeration value="esriYards"/&amp;gt;
 *     &amp;lt;enumeration value="esriMiles"/&amp;gt;
 *     &amp;lt;enumeration value="esriNauticalMiles"/&amp;gt;
 *     &amp;lt;enumeration value="esriMillimeters"/&amp;gt;
 *     &amp;lt;enumeration value="esriCentimeters"/&amp;gt;
 *     &amp;lt;enumeration value="esriMeters"/&amp;gt;
 *     &amp;lt;enumeration value="esriKilometers"/&amp;gt;
 *     &amp;lt;enumeration value="esriDecimalDegrees"/&amp;gt;
 *     &amp;lt;enumeration value="esriDecimeters"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriUnits")
@XmlEnum
public enum EsriUnits {

    @XmlEnumValue("esriUnknownUnits")
    ESRI_UNKNOWN_UNITS("esriUnknownUnits"),
    @XmlEnumValue("esriInches")
    ESRI_INCHES("esriInches"),
    @XmlEnumValue("esriPoints")
    ESRI_POINTS("esriPoints"),
    @XmlEnumValue("esriFeet")
    ESRI_FEET("esriFeet"),
    @XmlEnumValue("esriYards")
    ESRI_YARDS("esriYards"),
    @XmlEnumValue("esriMiles")
    ESRI_MILES("esriMiles"),
    @XmlEnumValue("esriNauticalMiles")
    ESRI_NAUTICAL_MILES("esriNauticalMiles"),
    @XmlEnumValue("esriMillimeters")
    ESRI_MILLIMETERS("esriMillimeters"),
    @XmlEnumValue("esriCentimeters")
    ESRI_CENTIMETERS("esriCentimeters"),
    @XmlEnumValue("esriMeters")
    ESRI_METERS("esriMeters"),
    @XmlEnumValue("esriKilometers")
    ESRI_KILOMETERS("esriKilometers"),
    @XmlEnumValue("esriDecimalDegrees")
    ESRI_DECIMAL_DEGREES("esriDecimalDegrees"),
    @XmlEnumValue("esriDecimeters")
    ESRI_DECIMETERS("esriDecimeters");
    private final String value;

    EsriUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriUnits fromValue(String v) {
        for (EsriUnits c: EsriUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

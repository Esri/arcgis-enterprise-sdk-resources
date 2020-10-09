
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriRoundingOptionEnum.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriRoundingOptionEnum"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriRoundNumberOfDecimals"/&amp;gt;
 *     &amp;lt;enumeration value="esriRoundNumberOfSignificantDigits"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriRoundingOptionEnum")
@XmlEnum
public enum EsriRoundingOptionEnum {

    @XmlEnumValue("esriRoundNumberOfDecimals")
    ESRI_ROUND_NUMBER_OF_DECIMALS("esriRoundNumberOfDecimals"),
    @XmlEnumValue("esriRoundNumberOfSignificantDigits")
    ESRI_ROUND_NUMBER_OF_SIGNIFICANT_DIGITS("esriRoundNumberOfSignificantDigits");
    private final String value;

    EsriRoundingOptionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriRoundingOptionEnum fromValue(String v) {
        for (EsriRoundingOptionEnum c: EsriRoundingOptionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

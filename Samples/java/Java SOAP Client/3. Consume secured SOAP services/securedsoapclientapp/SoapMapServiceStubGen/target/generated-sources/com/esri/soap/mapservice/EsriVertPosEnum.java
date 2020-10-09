
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriVertPosEnum.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriVertPosEnum"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriAbove"/&amp;gt;
 *     &amp;lt;enumeration value="esriTop"/&amp;gt;
 *     &amp;lt;enumeration value="esriOn"/&amp;gt;
 *     &amp;lt;enumeration value="esriBottom"/&amp;gt;
 *     &amp;lt;enumeration value="esriBelow"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriVertPosEnum")
@XmlEnum
public enum EsriVertPosEnum {

    @XmlEnumValue("esriAbove")
    ESRI_ABOVE("esriAbove"),
    @XmlEnumValue("esriTop")
    ESRI_TOP("esriTop"),
    @XmlEnumValue("esriOn")
    ESRI_ON("esriOn"),
    @XmlEnumValue("esriBottom")
    ESRI_BOTTOM("esriBottom"),
    @XmlEnumValue("esriBelow")
    ESRI_BELOW("esriBelow");
    private final String value;

    EsriVertPosEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriVertPosEnum fromValue(String v) {
        for (EsriVertPosEnum c: EsriVertPosEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

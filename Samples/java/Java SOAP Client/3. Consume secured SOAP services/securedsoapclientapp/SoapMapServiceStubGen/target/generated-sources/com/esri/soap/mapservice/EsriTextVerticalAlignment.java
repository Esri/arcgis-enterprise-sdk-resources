
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriTextVerticalAlignment.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriTextVerticalAlignment"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriTVATop"/&amp;gt;
 *     &amp;lt;enumeration value="esriTVACenter"/&amp;gt;
 *     &amp;lt;enumeration value="esriTVABaseline"/&amp;gt;
 *     &amp;lt;enumeration value="esriTVABottom"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriTextVerticalAlignment")
@XmlEnum
public enum EsriTextVerticalAlignment {

    @XmlEnumValue("esriTVATop")
    ESRI_TVA_TOP("esriTVATop"),
    @XmlEnumValue("esriTVACenter")
    ESRI_TVA_CENTER("esriTVACenter"),
    @XmlEnumValue("esriTVABaseline")
    ESRI_TVA_BASELINE("esriTVABaseline"),
    @XmlEnumValue("esriTVABottom")
    ESRI_TVA_BOTTOM("esriTVABottom");
    private final String value;

    EsriTextVerticalAlignment(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriTextVerticalAlignment fromValue(String v) {
        for (EsriTextVerticalAlignment c: EsriTextVerticalAlignment.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

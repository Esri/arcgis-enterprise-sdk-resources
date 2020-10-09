
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriNumericAlignmentEnum.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriNumericAlignmentEnum"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriAlignRight"/&amp;gt;
 *     &amp;lt;enumeration value="esriAlignLeft"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriNumericAlignmentEnum")
@XmlEnum
public enum EsriNumericAlignmentEnum {

    @XmlEnumValue("esriAlignRight")
    ESRI_ALIGN_RIGHT("esriAlignRight"),
    @XmlEnumValue("esriAlignLeft")
    ESRI_ALIGN_LEFT("esriAlignLeft");
    private final String value;

    EsriNumericAlignmentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriNumericAlignmentEnum fromValue(String v) {
        for (EsriNumericAlignmentEnum c: EsriNumericAlignmentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

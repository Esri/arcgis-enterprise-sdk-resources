
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriFindOption.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriFindOption"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriFindVisibleLayers"/&amp;gt;
 *     &amp;lt;enumeration value="esriFindAllLayers"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriFindOption")
@XmlEnum
public enum EsriFindOption {

    @XmlEnumValue("esriFindVisibleLayers")
    ESRI_FIND_VISIBLE_LAYERS("esriFindVisibleLayers"),
    @XmlEnumValue("esriFindAllLayers")
    ESRI_FIND_ALL_LAYERS("esriFindAllLayers");
    private final String value;

    EsriFindOption(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriFindOption fromValue(String v) {
        for (EsriFindOption c: EsriFindOption.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

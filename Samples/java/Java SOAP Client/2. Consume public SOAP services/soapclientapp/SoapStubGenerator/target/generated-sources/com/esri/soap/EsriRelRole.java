
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriRelRole.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriRelRole"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriRelRoleAny"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelRoleOrigin"/&amp;gt;
 *     &amp;lt;enumeration value="esriRelRoleDestination"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriRelRole")
@XmlEnum
public enum EsriRelRole {


    /**
     * Any.
     * 
     */
    @XmlEnumValue("esriRelRoleAny")
    ESRI_REL_ROLE_ANY("esriRelRoleAny"),

    /**
     * Origin.
     * 
     */
    @XmlEnumValue("esriRelRoleOrigin")
    ESRI_REL_ROLE_ORIGIN("esriRelRoleOrigin"),

    /**
     * Destination.
     * 
     */
    @XmlEnumValue("esriRelRoleDestination")
    ESRI_REL_ROLE_DESTINATION("esriRelRoleDestination");
    private final String value;

    EsriRelRole(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriRelRole fromValue(String v) {
        for (EsriRelRole c: EsriRelRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

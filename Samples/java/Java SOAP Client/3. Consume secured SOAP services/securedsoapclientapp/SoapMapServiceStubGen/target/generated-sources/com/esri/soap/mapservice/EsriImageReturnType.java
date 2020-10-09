
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriImageReturnType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriImageReturnType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriImageReturnURL"/&amp;gt;
 *     &amp;lt;enumeration value="esriImageReturnMimeData"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriImageReturnType")
@XmlEnum
public enum EsriImageReturnType {

    @XmlEnumValue("esriImageReturnURL")
    ESRI_IMAGE_RETURN_URL("esriImageReturnURL"),
    @XmlEnumValue("esriImageReturnMimeData")
    ESRI_IMAGE_RETURN_MIME_DATA("esriImageReturnMimeData");
    private final String value;

    EsriImageReturnType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriImageReturnType fromValue(String v) {
        for (EsriImageReturnType c: EsriImageReturnType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

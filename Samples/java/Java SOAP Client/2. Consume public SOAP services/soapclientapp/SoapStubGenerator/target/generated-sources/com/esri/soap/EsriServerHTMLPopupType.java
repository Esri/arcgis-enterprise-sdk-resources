
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriServerHTMLPopupType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriServerHTMLPopupType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriServerHTMLPopupTypeNone"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerHTMLPopupTypeAsURL"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerHTMLPopupTypeAsHTMLText"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriServerHTMLPopupType")
@XmlEnum
public enum EsriServerHTMLPopupType {

    @XmlEnumValue("esriServerHTMLPopupTypeNone")
    ESRI_SERVER_HTML_POPUP_TYPE_NONE("esriServerHTMLPopupTypeNone"),
    @XmlEnumValue("esriServerHTMLPopupTypeAsURL")
    ESRI_SERVER_HTML_POPUP_TYPE_AS_URL("esriServerHTMLPopupTypeAsURL"),
    @XmlEnumValue("esriServerHTMLPopupTypeAsHTMLText")
    ESRI_SERVER_HTML_POPUP_TYPE_AS_HTML_TEXT("esriServerHTMLPopupTypeAsHTMLText");
    private final String value;

    EsriServerHTMLPopupType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriServerHTMLPopupType fromValue(String v) {
        for (EsriServerHTMLPopupType c: EsriServerHTMLPopupType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

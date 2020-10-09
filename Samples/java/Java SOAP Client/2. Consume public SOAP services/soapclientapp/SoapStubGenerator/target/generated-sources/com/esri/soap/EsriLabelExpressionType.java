
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriLabelExpressionType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriLabelExpressionType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriLabelExpressionSimple"/&amp;gt;
 *     &amp;lt;enumeration value="esriLabelExpressionPython"/&amp;gt;
 *     &amp;lt;enumeration value="esriLabelExpressionVBScript"/&amp;gt;
 *     &amp;lt;enumeration value="esriLabelExpressionJScript"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriLabelExpressionType")
@XmlEnum
public enum EsriLabelExpressionType {

    @XmlEnumValue("esriLabelExpressionSimple")
    ESRI_LABEL_EXPRESSION_SIMPLE("esriLabelExpressionSimple"),
    @XmlEnumValue("esriLabelExpressionPython")
    ESRI_LABEL_EXPRESSION_PYTHON("esriLabelExpressionPython"),
    @XmlEnumValue("esriLabelExpressionVBScript")
    ESRI_LABEL_EXPRESSION_VB_SCRIPT("esriLabelExpressionVBScript"),
    @XmlEnumValue("esriLabelExpressionJScript")
    ESRI_LABEL_EXPRESSION_J_SCRIPT("esriLabelExpressionJScript");
    private final String value;

    EsriLabelExpressionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriLabelExpressionType fromValue(String v) {
        for (EsriLabelExpressionType c: EsriLabelExpressionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

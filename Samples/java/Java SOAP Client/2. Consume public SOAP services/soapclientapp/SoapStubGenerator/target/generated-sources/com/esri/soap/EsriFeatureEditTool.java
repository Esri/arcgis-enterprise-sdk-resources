
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriFeatureEditTool.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriFeatureEditTool"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolNone"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolPoint"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolLine"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolPolygon"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolAutoCompletePolygon"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolCircle"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolEllipse"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolRectangle"/&amp;gt;
 *     &amp;lt;enumeration value="esriFeatureEditToolFreehand"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriFeatureEditTool")
@XmlEnum
public enum EsriFeatureEditTool {

    @XmlEnumValue("esriFeatureEditToolNone")
    ESRI_FEATURE_EDIT_TOOL_NONE("esriFeatureEditToolNone"),
    @XmlEnumValue("esriFeatureEditToolPoint")
    ESRI_FEATURE_EDIT_TOOL_POINT("esriFeatureEditToolPoint"),
    @XmlEnumValue("esriFeatureEditToolLine")
    ESRI_FEATURE_EDIT_TOOL_LINE("esriFeatureEditToolLine"),
    @XmlEnumValue("esriFeatureEditToolPolygon")
    ESRI_FEATURE_EDIT_TOOL_POLYGON("esriFeatureEditToolPolygon"),
    @XmlEnumValue("esriFeatureEditToolAutoCompletePolygon")
    ESRI_FEATURE_EDIT_TOOL_AUTO_COMPLETE_POLYGON("esriFeatureEditToolAutoCompletePolygon"),
    @XmlEnumValue("esriFeatureEditToolCircle")
    ESRI_FEATURE_EDIT_TOOL_CIRCLE("esriFeatureEditToolCircle"),
    @XmlEnumValue("esriFeatureEditToolEllipse")
    ESRI_FEATURE_EDIT_TOOL_ELLIPSE("esriFeatureEditToolEllipse"),
    @XmlEnumValue("esriFeatureEditToolRectangle")
    ESRI_FEATURE_EDIT_TOOL_RECTANGLE("esriFeatureEditToolRectangle"),
    @XmlEnumValue("esriFeatureEditToolFreehand")
    ESRI_FEATURE_EDIT_TOOL_FREEHAND("esriFeatureEditToolFreehand");
    private final String value;

    EsriFeatureEditTool(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriFeatureEditTool fromValue(String v) {
        for (EsriFeatureEditTool c: EsriFeatureEditTool.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

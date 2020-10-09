
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriIdentifyOption.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriIdentifyOption"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriIdentifyTopmost"/&amp;gt;
 *     &amp;lt;enumeration value="esriIdentifyAllLayers"/&amp;gt;
 *     &amp;lt;enumeration value="esriIdentifyVisibleLayers"/&amp;gt;
 *     &amp;lt;enumeration value="esriIdentifyTopOneWithHTMLPopup"/&amp;gt;
 *     &amp;lt;enumeration value="esriIdentifyVisibleWithHTMLPopup"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriIdentifyOption")
@XmlEnum
public enum EsriIdentifyOption {

    @XmlEnumValue("esriIdentifyTopmost")
    ESRI_IDENTIFY_TOPMOST("esriIdentifyTopmost"),
    @XmlEnumValue("esriIdentifyAllLayers")
    ESRI_IDENTIFY_ALL_LAYERS("esriIdentifyAllLayers"),
    @XmlEnumValue("esriIdentifyVisibleLayers")
    ESRI_IDENTIFY_VISIBLE_LAYERS("esriIdentifyVisibleLayers"),
    @XmlEnumValue("esriIdentifyTopOneWithHTMLPopup")
    ESRI_IDENTIFY_TOP_ONE_WITH_HTML_POPUP("esriIdentifyTopOneWithHTMLPopup"),
    @XmlEnumValue("esriIdentifyVisibleWithHTMLPopup")
    ESRI_IDENTIFY_VISIBLE_WITH_HTML_POPUP("esriIdentifyVisibleWithHTMLPopup");
    private final String value;

    EsriIdentifyOption(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriIdentifyOption fromValue(String v) {
        for (EsriIdentifyOption c: EsriIdentifyOption.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriServerLineLabelPlacementType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriServerLineLabelPlacementType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementAboveAfter"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementAboveAlong"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementAboveBefore"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementAboveStart"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementAboveEnd"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementBelowAfter"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementBelowAlong"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementBelowBefore"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementBelowStart"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementBelowEnd"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementCenterAfter"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementCenterAlong"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementCenterBefore"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementCenterStart"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerLinePlacementCenterEnd"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriServerLineLabelPlacementType")
@XmlEnum
public enum EsriServerLineLabelPlacementType {

    @XmlEnumValue("esriServerLinePlacementAboveAfter")
    ESRI_SERVER_LINE_PLACEMENT_ABOVE_AFTER("esriServerLinePlacementAboveAfter"),
    @XmlEnumValue("esriServerLinePlacementAboveAlong")
    ESRI_SERVER_LINE_PLACEMENT_ABOVE_ALONG("esriServerLinePlacementAboveAlong"),
    @XmlEnumValue("esriServerLinePlacementAboveBefore")
    ESRI_SERVER_LINE_PLACEMENT_ABOVE_BEFORE("esriServerLinePlacementAboveBefore"),
    @XmlEnumValue("esriServerLinePlacementAboveStart")
    ESRI_SERVER_LINE_PLACEMENT_ABOVE_START("esriServerLinePlacementAboveStart"),
    @XmlEnumValue("esriServerLinePlacementAboveEnd")
    ESRI_SERVER_LINE_PLACEMENT_ABOVE_END("esriServerLinePlacementAboveEnd"),
    @XmlEnumValue("esriServerLinePlacementBelowAfter")
    ESRI_SERVER_LINE_PLACEMENT_BELOW_AFTER("esriServerLinePlacementBelowAfter"),
    @XmlEnumValue("esriServerLinePlacementBelowAlong")
    ESRI_SERVER_LINE_PLACEMENT_BELOW_ALONG("esriServerLinePlacementBelowAlong"),
    @XmlEnumValue("esriServerLinePlacementBelowBefore")
    ESRI_SERVER_LINE_PLACEMENT_BELOW_BEFORE("esriServerLinePlacementBelowBefore"),
    @XmlEnumValue("esriServerLinePlacementBelowStart")
    ESRI_SERVER_LINE_PLACEMENT_BELOW_START("esriServerLinePlacementBelowStart"),
    @XmlEnumValue("esriServerLinePlacementBelowEnd")
    ESRI_SERVER_LINE_PLACEMENT_BELOW_END("esriServerLinePlacementBelowEnd"),
    @XmlEnumValue("esriServerLinePlacementCenterAfter")
    ESRI_SERVER_LINE_PLACEMENT_CENTER_AFTER("esriServerLinePlacementCenterAfter"),
    @XmlEnumValue("esriServerLinePlacementCenterAlong")
    ESRI_SERVER_LINE_PLACEMENT_CENTER_ALONG("esriServerLinePlacementCenterAlong"),
    @XmlEnumValue("esriServerLinePlacementCenterBefore")
    ESRI_SERVER_LINE_PLACEMENT_CENTER_BEFORE("esriServerLinePlacementCenterBefore"),
    @XmlEnumValue("esriServerLinePlacementCenterStart")
    ESRI_SERVER_LINE_PLACEMENT_CENTER_START("esriServerLinePlacementCenterStart"),
    @XmlEnumValue("esriServerLinePlacementCenterEnd")
    ESRI_SERVER_LINE_PLACEMENT_CENTER_END("esriServerLinePlacementCenterEnd");
    private final String value;

    EsriServerLineLabelPlacementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriServerLineLabelPlacementType fromValue(String v) {
        for (EsriServerLineLabelPlacementType c: EsriServerLineLabelPlacementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

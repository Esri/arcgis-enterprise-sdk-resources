
package com.esri.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriServerPointLabelPlacementType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriServerPointLabelPlacementType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementAboveCenter"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementAboveLeft"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementAboveRight"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementBelowCenter"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementBelowLeft"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementBelowRight"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementCenterCenter"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementCenterLeft"/&amp;gt;
 *     &amp;lt;enumeration value="esriServerPointLabelPlacementCenterRight"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriServerPointLabelPlacementType")
@XmlEnum
public enum EsriServerPointLabelPlacementType {

    @XmlEnumValue("esriServerPointLabelPlacementAboveCenter")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_ABOVE_CENTER("esriServerPointLabelPlacementAboveCenter"),
    @XmlEnumValue("esriServerPointLabelPlacementAboveLeft")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_ABOVE_LEFT("esriServerPointLabelPlacementAboveLeft"),
    @XmlEnumValue("esriServerPointLabelPlacementAboveRight")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_ABOVE_RIGHT("esriServerPointLabelPlacementAboveRight"),
    @XmlEnumValue("esriServerPointLabelPlacementBelowCenter")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_BELOW_CENTER("esriServerPointLabelPlacementBelowCenter"),
    @XmlEnumValue("esriServerPointLabelPlacementBelowLeft")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_BELOW_LEFT("esriServerPointLabelPlacementBelowLeft"),
    @XmlEnumValue("esriServerPointLabelPlacementBelowRight")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_BELOW_RIGHT("esriServerPointLabelPlacementBelowRight"),
    @XmlEnumValue("esriServerPointLabelPlacementCenterCenter")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_CENTER_CENTER("esriServerPointLabelPlacementCenterCenter"),
    @XmlEnumValue("esriServerPointLabelPlacementCenterLeft")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_CENTER_LEFT("esriServerPointLabelPlacementCenterLeft"),
    @XmlEnumValue("esriServerPointLabelPlacementCenterRight")
    ESRI_SERVER_POINT_LABEL_PLACEMENT_CENTER_RIGHT("esriServerPointLabelPlacementCenterRight");
    private final String value;

    EsriServerPointLabelPlacementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriServerPointLabelPlacementType fromValue(String v) {
        for (EsriServerPointLabelPlacementType c: EsriServerPointLabelPlacementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

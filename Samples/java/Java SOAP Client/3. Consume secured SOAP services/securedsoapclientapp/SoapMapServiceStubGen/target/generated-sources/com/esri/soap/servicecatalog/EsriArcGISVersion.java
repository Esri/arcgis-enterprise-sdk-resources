
package com.esri.soap.servicecatalog;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriArcGISVersion.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriArcGISVersion"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion83"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion90"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion92"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion93"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion10"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion101"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion103"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion104"/&amp;gt;
 *     &amp;lt;enumeration value="esriArcGISVersion105"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriArcGISVersion")
@XmlEnum
public enum EsriArcGISVersion {

    @XmlEnumValue("esriArcGISVersion83")
    ESRI_ARC_GIS_VERSION_83("esriArcGISVersion83"),
    @XmlEnumValue("esriArcGISVersion90")
    ESRI_ARC_GIS_VERSION_90("esriArcGISVersion90"),
    @XmlEnumValue("esriArcGISVersion92")
    ESRI_ARC_GIS_VERSION_92("esriArcGISVersion92"),
    @XmlEnumValue("esriArcGISVersion93")
    ESRI_ARC_GIS_VERSION_93("esriArcGISVersion93"),
    @XmlEnumValue("esriArcGISVersion10")
    ESRI_ARC_GIS_VERSION_10("esriArcGISVersion10"),
    @XmlEnumValue("esriArcGISVersion101")
    ESRI_ARC_GIS_VERSION_101("esriArcGISVersion101"),
    @XmlEnumValue("esriArcGISVersion103")
    ESRI_ARC_GIS_VERSION_103("esriArcGISVersion103"),
    @XmlEnumValue("esriArcGISVersion104")
    ESRI_ARC_GIS_VERSION_104("esriArcGISVersion104"),
    @XmlEnumValue("esriArcGISVersion105")
    ESRI_ARC_GIS_VERSION_105("esriArcGISVersion105");
    private final String value;

    EsriArcGISVersion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriArcGISVersion fromValue(String v) {
        for (EsriArcGISVersion c: EsriArcGISVersion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

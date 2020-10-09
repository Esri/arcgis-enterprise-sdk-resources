
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriSimpleFillStyle.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriSimpleFillStyle"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriSFSSolid"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSNull"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSHorizontal"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSVertical"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSForwardDiagonal"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSBackwardDiagonal"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSCross"/&amp;gt;
 *     &amp;lt;enumeration value="esriSFSDiagonalCross"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriSimpleFillStyle")
@XmlEnum
public enum EsriSimpleFillStyle {

    @XmlEnumValue("esriSFSSolid")
    ESRI_SFS_SOLID("esriSFSSolid"),
    @XmlEnumValue("esriSFSNull")
    ESRI_SFS_NULL("esriSFSNull"),
    @XmlEnumValue("esriSFSHorizontal")
    ESRI_SFS_HORIZONTAL("esriSFSHorizontal"),
    @XmlEnumValue("esriSFSVertical")
    ESRI_SFS_VERTICAL("esriSFSVertical"),
    @XmlEnumValue("esriSFSForwardDiagonal")
    ESRI_SFS_FORWARD_DIAGONAL("esriSFSForwardDiagonal"),
    @XmlEnumValue("esriSFSBackwardDiagonal")
    ESRI_SFS_BACKWARD_DIAGONAL("esriSFSBackwardDiagonal"),
    @XmlEnumValue("esriSFSCross")
    ESRI_SFS_CROSS("esriSFSCross"),
    @XmlEnumValue("esriSFSDiagonalCross")
    ESRI_SFS_DIAGONAL_CROSS("esriSFSDiagonalCross");
    private final String value;

    EsriSimpleFillStyle(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriSimpleFillStyle fromValue(String v) {
        for (EsriSimpleFillStyle c: EsriSimpleFillStyle.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

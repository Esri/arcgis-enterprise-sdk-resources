
package com.esri.soap.servicecatalog;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriServiceCatalogMessageFormat.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriServiceCatalogMessageFormat"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriServiceCatalogMessageFormatSoap"/&amp;gt;
 *     &amp;lt;enumeration value="esriServiceCatalogMessageFormatBin"/&amp;gt;
 *     &amp;lt;enumeration value="esriServiceCatalogMessageFormatSoapOrBin"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriServiceCatalogMessageFormat")
@XmlEnum
public enum EsriServiceCatalogMessageFormat {

    @XmlEnumValue("esriServiceCatalogMessageFormatSoap")
    ESRI_SERVICE_CATALOG_MESSAGE_FORMAT_SOAP("esriServiceCatalogMessageFormatSoap"),
    @XmlEnumValue("esriServiceCatalogMessageFormatBin")
    ESRI_SERVICE_CATALOG_MESSAGE_FORMAT_BIN("esriServiceCatalogMessageFormatBin"),
    @XmlEnumValue("esriServiceCatalogMessageFormatSoapOrBin")
    ESRI_SERVICE_CATALOG_MESSAGE_FORMAT_SOAP_OR_BIN("esriServiceCatalogMessageFormatSoapOrBin");
    private final String value;

    EsriServiceCatalogMessageFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriServiceCatalogMessageFormat fromValue(String v) {
        for (EsriServiceCatalogMessageFormat c: EsriServiceCatalogMessageFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

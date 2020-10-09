
package com.esri.soap.mapservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for esriMapCacheStorageFormat.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="esriMapCacheStorageFormat"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="esriMapCacheStorageModeCompact"/&amp;gt;
 *     &amp;lt;enumeration value="esriMapCacheStorageModeExploded"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "esriMapCacheStorageFormat")
@XmlEnum
public enum EsriMapCacheStorageFormat {

    @XmlEnumValue("esriMapCacheStorageModeCompact")
    ESRI_MAP_CACHE_STORAGE_MODE_COMPACT("esriMapCacheStorageModeCompact"),
    @XmlEnumValue("esriMapCacheStorageModeExploded")
    ESRI_MAP_CACHE_STORAGE_MODE_EXPLODED("esriMapCacheStorageModeExploded");
    private final String value;

    EsriMapCacheStorageFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EsriMapCacheStorageFormat fromValue(String v) {
        for (EsriMapCacheStorageFormat c: EsriMapCacheStorageFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

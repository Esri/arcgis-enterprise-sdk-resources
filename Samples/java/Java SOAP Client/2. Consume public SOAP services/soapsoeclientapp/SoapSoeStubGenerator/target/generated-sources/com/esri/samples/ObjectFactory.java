
package com.esri.samples;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.esri.samples package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.esri.samples
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLayerCountByType }
     * 
     */
    public GetLayerCountByType createGetLayerCountByType() {
        return new GetLayerCountByType();
    }

    /**
     * Create an instance of {@link GetLayerCountByTypeResponse }
     * 
     */
    public GetLayerCountByTypeResponse createGetLayerCountByTypeResponse() {
        return new GetLayerCountByTypeResponse();
    }

}

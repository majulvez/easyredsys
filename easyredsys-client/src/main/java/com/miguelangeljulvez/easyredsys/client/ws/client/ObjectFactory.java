
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.miguelangeljulvez.easyredsys.ws package.
 * <p>An ObjectFactory allows you to programatically 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.miguelangeljulvez.easyredsys.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaDCCResponse }
     * 
     */
    public ConsultaDCCResponse createConsultaDCCResponse() {
        return new ConsultaDCCResponse();
    }

    /**
     * Create an instance of {@link ConsultaDCC }
     * 
     */
    public ConsultaDCC createConsultaDCC() {
        return new ConsultaDCC();
    }

    /**
     * Create an instance of {@link TrataPeticion }
     * 
     */
    public TrataPeticion createTrataPeticion() {
        return new TrataPeticion();
    }

    /**
     * Create an instance of {@link TrataPeticionResponse }
     * 
     */
    public TrataPeticionResponse createTrataPeticionResponse() {
        return new TrataPeticionResponse();
    }

}

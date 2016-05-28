
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public ConsultaDCCResponse createConsultaDCCResponse() {
        return new ConsultaDCCResponse();
    }

    public ConsultaDCC createConsultaDCC() {
        return new ConsultaDCC();
    }

    public TrataPeticion createTrataPeticion() {
        return new TrataPeticion();
    }

    public TrataPeticionResponse createTrataPeticionResponse() {
        return new TrataPeticionResponse();
    }

}

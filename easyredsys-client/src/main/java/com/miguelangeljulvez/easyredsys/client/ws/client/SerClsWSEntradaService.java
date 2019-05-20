
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


@WebServiceClient(name = "SerClsWSEntradaService", targetNamespace = "http://webservice.sis.sermepa.es")
public class SerClsWSEntradaService extends Service {
    private final static QName SERCLSWSENTRADASERVICE_QNAME = new QName("http://webservice.sis.sermepa.es", "SerClsWSEntradaService");

    public SerClsWSEntradaService(URL wsdlLocation) {
        super(wsdlLocation, SERCLSWSENTRADASERVICE_QNAME);
    }

    @WebEndpoint(name = "SerClsWSEntrada")
    public SerClsWSEntrada getSerClsWSEntrada() {
        return super.getPort(new QName("http://webservice.sis.sermepa.es", "SerClsWSEntrada"), SerClsWSEntrada.class);
    }

    @WebEndpoint(name = "SerClsWSEntrada")
    public SerClsWSEntrada getSerClsWSEntrada(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.sis.sermepa.es", "SerClsWSEntrada"), SerClsWSEntrada.class, features);
    }

}

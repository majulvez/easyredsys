package com.miguelangeljulvez.easyredsys.server.ws.literal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "InotificacionSIS", targetNamespace = "http://notificador.webservice.sis.redsys.es")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface InotificacionSIS {

    @WebMethod(action = "notificacion")
    @WebResult(name = "notificacionReturn", targetNamespace = "http://notificador.webservice.sis.redsys.es")
    String notificacion(@WebParam(name = "datoEntrada", targetNamespace = "http://notificador.webservice.sis.redsys.es") String datoEntrada);

}
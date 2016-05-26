package com.miguelangeljulvez.easyredsys.server.ws.rest;


import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderCESResponse;
import com.miguelangeljulvez.easyredsys.server.util.SecurityUtil;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/InotificacionSIS")
public class InotificacionSISRest {

    @Inject
    private AppConfig appConfig;

    @POST
    public Response notificar(
            @FormParam("Ds_SignatureVersion") String ds_SignatureVersion,
            @FormParam("Ds_MerchantParameters") String ds_MerchantParameters,
            @FormParam("Ds_Signature") String ds_Signature,
            @Context HttpServletRequest request) {

        _log.log(Level.INFO, "Notificación del banco recibida");

        _log.log(Level.FINEST, "ds_SignatureVersion: " + ds_SignatureVersion);
        _log.log(Level.FINEST, "ds_MerchantParameters: " + ds_MerchantParameters);
        _log.log(Level.FINEST, "ds_Signature: " + ds_Signature);

        if (!SecurityUtil.isValidIp(request.getRemoteAddr())) {
            _log.log(Level.WARNING, "La notificación se ha recibido desde una IP no permitida");

            return Response.status(400).build();
        }

        String password;
        if (appConfig == null) {
            _log.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            _log.log(Level.WARNING, "Usando password por defecto de la pasarela de test: 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'");
            password = "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
        } else {
            password = AppConfig.getSecretKey();
        }

        MessageOrderCESResponse messageOrderCESResponse = new MessageOrderCESResponse(ds_SignatureVersion, ds_Signature, ds_MerchantParameters, password);

        _log.log(Level.FINEST, "Notificación recibida: " + messageOrderCESResponse.getOperationCES());

        if (!messageOrderCESResponse.isValid()) {
            _log.log(Level.WARNING, "Notificación para el pedido " + messageOrderCESResponse.getOperationCES().getDs_Order() + " recibida erróneamente. La firma no es válida");

            return Response.status(400).build();
        }

        _log.info("Notificación válida recibida para la order: " + messageOrderCESResponse.getOperationCES().getDs_Order());

        if (appConfig == null) {
            _log.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            _log.log(Level.WARNING, "No hay nada que hacer con la notificación recibida");
        } else {
            appConfig.saveNotification(messageOrderCESResponse.getOperationCES());
        }

        return Response.status(200).build();
    }

    private static final Logger _log = Logger.getLogger(InotificacionSISRest.class.getName());

}

package com.miguelangeljulvez.easyredsys.server.ws.rest;


import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderCESResponse;
import com.miguelangeljulvez.easyredsys.client.util.ResponseCodes;
import com.miguelangeljulvez.easyredsys.server.util.SecurityUtil;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/InotificacionSIS")
public class InotificacionSISRest {

    AppConfig appConfig;

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
        if (getAppConfig() == null) {
            _log.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            _log.log(Level.WARNING, "Usando password por defecto de la pasarela de test: 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'");
            password = "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
        } else {
            password = getAppConfig().getSecretKey();
        }

        MessageOrderCESResponse messageOrderCESResponse = new MessageOrderCESResponse(ds_SignatureVersion, ds_Signature, ds_MerchantParameters, password);

        _log.log(Level.FINEST, "Notificación recibida: " + messageOrderCESResponse.getOperationCES());

        if (!messageOrderCESResponse.isValid()) {
            _log.log(Level.WARNING, "Notificación para el pedido " + messageOrderCESResponse.getOperationCES().getDs_Order() + " recibida erróneamente. La firma no es válida");

            return Response.status(400).build();
        }

        _log.info("Notificación válida recibida para la order: " + messageOrderCESResponse.getOperationCES().getDs_Order());

        if (!ResponseCodes.isSuccessResponse(messageOrderCESResponse.getOperationCES().getDs_Response())) {
            _log.log(Level.WARNING, "OperationException: Response code de error: " + messageOrderCESResponse.getOperationCES().getDs_Response());

            throw new SecurityException(ResponseCodes.getErrorResponseMessage(messageOrderCESResponse.getOperationCES().getDs_Response()));
        }

        if (getAppConfig() == null) {
            _log.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            _log.log(Level.WARNING, "No hay nada que hacer con la notificación recibida");
        } else {
            getAppConfig().saveNotification(messageOrderCESResponse.getOperationCES());
        }

        return Response.status(200).build();
    }

    private AppConfig getAppConfig() {

        if (appConfig == null) {
            Package[] packages = Package.getPackages();
            Reflections reflections;
            Set<Class<? extends AppConfig>> classes = new HashSet<>();
            for (Package packageP : packages) { //¿No hay algo más efectivo para hacer esto?
                reflections = new Reflections(packageP.getName());
                classes.addAll(reflections.getSubTypesOf(AppConfig.class));
            }

            if (classes.size() == 0) {
                _log.log(Level.SEVERE, "Ninguna clase en el classpath implementa AppConfig");
            } else if (classes.size() > 1) {
                _log.log(Level.SEVERE, "Mas de una clase en el classpath implementa AppConfig");
            } else {
                Class<? extends AppConfig> aClass = classes.iterator().next();
                try {
                    appConfig = aClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    _log.log(Level.SEVERE, "No se ha podido instanciar la clase que implementa AppConfig");
                }
            }
        }

        return appConfig;
    }

    private static final Logger _log = Logger.getLogger(InotificacionSISRest.class.getName());

}

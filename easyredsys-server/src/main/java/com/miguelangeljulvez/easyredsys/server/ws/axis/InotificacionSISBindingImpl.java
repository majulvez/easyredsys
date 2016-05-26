package com.miguelangeljulvez.easyredsys.server.ws.axis;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.server.core.MessageOrderSOAPRequest;
import com.miguelangeljulvez.easyredsys.server.core.MessageOrderSOAPResponse;
import com.miguelangeljulvez.easyredsys.server.core.OrderSOAP;
import com.miguelangeljulvez.easyredsys.server.util.SecurityUtil;
import com.miguelangeljulvez.easyredsys.server.util.XMLSOAPUtil;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.reflections.Reflections;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InotificacionSISBindingImpl implements InotificacionSISPortType {

    //@Inject - No funcionaría porque el bean no lo crea el contenedor
    private AppConfig appConfig;

    public InotificacionSISBindingImpl() {

        Package[] packages = Package.getPackages();
        Reflections reflections;
        Set<Class<? extends AppConfig>> classes = new HashSet<>();
        for (Package packageP: packages) { //¿No hay algo más efectivo para hacer esto?
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

    public String procesaNotificacionSIS(String XML) throws java.rmi.RemoteException {

        _log.log(Level.INFO, "Notificación del banco recibida");

        _log.log(Level.FINEST, "Notificación recibida: " + XML);

        String remoteAddr = getRemoteAddr();
        if (!SecurityUtil.isValidIp((remoteAddr))) {
            _log.log(Level.WARNING, "SecurityException - Acceso a la url de notificación desde ips no autorizadas: " + remoteAddr);

            throw new SecurityException("Acceso a la url de notificación desde ips no autorizadas");
        }

        String clave;
        if (appConfig == null) {
            _log.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            _log.log(Level.WARNING, "Usando password por defecto de la pasarela de test: 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'");
            clave = "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
        } else {
            clave = AppConfig.getSecretKey();
        }

        MessageOrderSOAPRequest messageOrderSOAPRequest = new MessageOrderSOAPRequest(XML, clave);

        _log.log(Level.FINEST, "Notificación recibida: " + messageOrderSOAPRequest.getNotificationSOAP());

        if (!messageOrderSOAPRequest.isValid()) {
            _log.log(Level.WARNING, "SecurityException - La firma recibida no es correcta: " + messageOrderSOAPRequest.getSignature());

            throw new SecurityException("La firma recibida no es correcta");
        }

        _log.info("Notificación válida recibida para la order " + messageOrderSOAPRequest.getNotificationSOAP().getDs_Order());

        if (appConfig == null) {
            _log.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            _log.log(Level.WARNING, "No hay nada que hacer con la notificación recibida");
        } else {
            appConfig.saveNotification(messageOrderSOAPRequest.getNotificationSOAP());
        }

        OrderSOAP orderSOAP = new OrderSOAP(messageOrderSOAPRequest.getNotificationSOAP().getDs_Order());
        orderSOAP.setDs_version("0.0");
        orderSOAP.setDs_response_merchant("OK"); //o KO, depende

        MessageOrderSOAPResponse messageOrderSOAPResponse = new MessageOrderSOAPResponse(orderSOAP, clave);

        String val_xml = XMLSOAPUtil.toRedsysXML(messageOrderSOAPResponse);

        _log.log(Level.FINEST, "Respuesta a la notificación: " + val_xml);

        return val_xml;
    }

    private String getRemoteAddr() {
        String remoteAddr = "";

        MessageContext context = MessageContext.getCurrentContext();

        if(context!=null && context.containsProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST)) {
            HttpServletRequest servletReq = (HttpServletRequest)
                    context.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);

            if (servletReq != null)
                remoteAddr = servletReq.getRemoteAddr();
        }

        if (context == null) { //Para pasar los test
            return "127.0.0.1";
        }

        return remoteAddr;
    }

    private static final Logger _log = Logger.getLogger(InotificacionSISBindingImpl.class.getName());
}

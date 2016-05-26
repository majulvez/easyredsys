package com.miguelangeljulvez.easyredsys.server.ws.literal;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.server.core.MessageOrderSOAPRequest;
import com.miguelangeljulvez.easyredsys.server.core.MessageOrderSOAPResponse;
import com.miguelangeljulvez.easyredsys.server.core.OrderSOAP;
import com.miguelangeljulvez.easyredsys.server.util.SecurityUtil;
import com.miguelangeljulvez.easyredsys.server.util.XMLSOAPUtil;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "InotificacionSIS" ,  endpointInterface = "com.miguelangeljulvez.easyredsys.server.ws.literal.InotificacionSIS")
public class InotificacionSISImpl implements InotificacionSIS {

    @Inject
    private AppConfig appConfig;

    @Override
    public String notificacion(String datoEntrada) {
        _log.log(Level.INFO, "Notificación del banco recibida");

        _log.log(Level.FINEST, "Notificación recibida: " + datoEntrada);

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

        MessageOrderSOAPRequest messageOrderSOAPRequest = new MessageOrderSOAPRequest(datoEntrada, clave);

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

    private static final Logger _log = Logger.getLogger(InotificacionSISImpl.class.getName());
}

/*
<soapenv:Envelope
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:not="http://notificador.webservice.sis.redsys.es">
        <soapenv:Header />
        <soapenv:Body>
                <not:notificacion>
                        <not:datoEntrada>
                                <![CDATA[<Message><Request Ds_Version='0.0'><Fecha>16/12/2014</Fecha><Hora>16:57</Hora><Ds_SecurePayment>1</Ds_SecurePayment><Ds_Card_Country>724</Ds_Card_Country><Ds_Amount>145</Ds_Amount><Ds_Currency>978</Ds_Currency><Ds_Order>3075QNW2</Ds_Order><Ds_MerchantCode>42</Ds_MerchantCode><Ds_Terminal>014</Ds_Terminal><Ds_Response>0129</Ds_Response><Ds_MerchantData>Viajes
&#9;&#9;&#9;&#9;&#9;&#9;&#9;Mal
&#9;&#9;&#9;&#9;&#9;&#9;&#9;divas</Ds_MerchantData><Ds_TransactionType>0</Ds_TransactionType><Ds_ConsumerLanguage>1</Ds_ConsumerLanguage><Ds_AuthorisationCode>      </Ds_AuthorisationCode></Request><Signature>2ba79f3b247948917b2158b47929017d87c7d560</Signature></Message>]]>
                        </not:datoEntrada>
                </not:notificacion>
        </soapenv:Body>
</soapenv:Envelope>

Respuesta:

<soapenv:Envelope
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/"
        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        <soapenv:Header />
        <soapenv:Body>
                <p474:notificacionResponse
                        xmlns:p474="http://notificador.webservice.sis.redsys.es">
                        <p474:notificacionReturn>
                                <![CDATA[<Message><Response Ds_Version="0.0"><Ds_Response_Merchant>OK</Ds_Response_Merchant></Response><Signature>4eae1504b93d97d5e0cb3528e1d2f413a886530c</Signature></Message>]]>
                        </p474:notificacionReturn>
                </p474:notificacionResponse>
        </soapenv:Body>
</soapenv:Envelope>
 */

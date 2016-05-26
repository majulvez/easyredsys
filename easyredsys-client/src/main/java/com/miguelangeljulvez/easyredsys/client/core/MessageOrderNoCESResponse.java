package com.miguelangeljulvez.easyredsys.client.core;


import com.miguelangeljulvez.easyredsys.client.util.XMLUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.logging.Logger;

@XmlRootElement(name = "RETORNOXML")
public final class MessageOrderNoCESResponse {

    private volatile String claveSecreta;

    private String codigo;
    private NotificationNoCES operationNoCES;

    public MessageOrderNoCESResponse(String xml, String claveSecreta) {
        MessageOrderNoCESResponse messageOrderNoCESResponse = XMLUtil.fromRedsysXMLOrderNoCES(xml);

        this.codigo = messageOrderNoCESResponse.getCodigo();
        this.operationNoCES = messageOrderNoCESResponse.getOperationNoCES();
        this.claveSecreta = claveSecreta;
    }

    public MessageOrderNoCESResponse() {}

    @XmlElement(name="CODIGO")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlElement(name="OPERACION")
    public NotificationNoCES getOperationNoCES() {
        return operationNoCES;
    }

    public void setOperationNoCES(NotificationNoCES operationNoCES) {
        this.operationNoCES = operationNoCES;
    }

    public boolean isValid() {
        return operationNoCES.isValid(claveSecreta, operationNoCES.getDs_Signature());
    }

    private static final Logger _log = Logger.getLogger(MessageOrderNoCESResponse.class.getName());

}

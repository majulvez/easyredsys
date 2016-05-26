
package com.miguelangeljulvez.easyredsys.server.ws.literal.jaxws;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "notificacionResponse", namespace = "http://notificador.webservice.sis.redsys.es")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificacionResponse", namespace = "http://notificador.webservice.sis.redsys.es")
public class NotificacionResponse {

    @XmlElement(name = "notificacionReturn", namespace = "http://notificador.webservice.sis.redsys.es")
    private String notificacionReturn;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNotificacionReturn() {
        return this.notificacionReturn;
    }

    /**
     * 
     * @param notificacionReturn
     *     the value for the notificacionReturn property
     */
    public void setNotificacionReturn(String notificacionReturn) {
        this.notificacionReturn = notificacionReturn;
    }

}

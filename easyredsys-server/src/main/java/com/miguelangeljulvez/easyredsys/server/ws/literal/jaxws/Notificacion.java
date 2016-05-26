
package com.miguelangeljulvez.easyredsys.server.ws.literal.jaxws;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "notificacion", namespace = "http://notificador.webservice.sis.redsys.es")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificacion", namespace = "http://notificador.webservice.sis.redsys.es")
public class Notificacion {

    @XmlElement(name = "datoEntrada", namespace = "http://notificador.webservice.sis.redsys.es")
    private String datoEntrada;

    /**
     * 
     * @return
     *     returns String
     */
    public String getDatoEntrada() {
        return this.datoEntrada;
    }

    /**
     * 
     * @param datoEntrada
     *     the value for the datoEntrada property
     */
    public void setDatoEntrada(String datoEntrada) {
        this.datoEntrada = datoEntrada;
    }

}

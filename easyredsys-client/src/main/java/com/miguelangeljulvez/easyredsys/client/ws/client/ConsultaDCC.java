
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datoEntrada"
})
@XmlRootElement(name = "consultaDCC")
public class ConsultaDCC {

    @XmlElement(required = true, nillable = true)
    protected String datoEntrada;

    /**
     * Obtiene el valor de la propiedad datoEntrada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatoEntrada() {
        return datoEntrada;
    }

    /**
     * Define el valor de la propiedad datoEntrada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatoEntrada(String value) {
        this.datoEntrada = value;
    }

}

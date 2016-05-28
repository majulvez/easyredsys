
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datoEntrada"
})
@XmlRootElement(name = "trataPeticion")
public class TrataPeticion {

    @XmlElement(required = true, nillable = true)
    protected String datoEntrada;

    public String getDatoEntrada() {
        return datoEntrada;
    }

    public void setDatoEntrada(String value) {
        this.datoEntrada = value;
    }

}

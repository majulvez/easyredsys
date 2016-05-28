
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "consultaDCCReturn"
})
@XmlRootElement(name = "consultaDCCResponse")
public class ConsultaDCCResponse {

    @XmlElement(required = true, nillable = true)
    protected String consultaDCCReturn;

    /**
     * Obtiene el valor de la propiedad consultaDCCReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsultaDCCReturn() {
        return consultaDCCReturn;
    }

    /**
     * Define el valor de la propiedad consultaDCCReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsultaDCCReturn(String value) {
        this.consultaDCCReturn = value;
    }

}

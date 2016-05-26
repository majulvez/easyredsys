
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.*;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultaDCCReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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


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
 *         &lt;element name="datoEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "datoEntrada"
})
@XmlRootElement(name = "trataPeticion")
public class TrataPeticion {

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

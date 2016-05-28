
package com.miguelangeljulvez.easyredsys.client.ws.client;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "trataPeticionReturn"
})
@XmlRootElement(name = "trataPeticionResponse")
public class TrataPeticionResponse {

    @XmlElement(required = true, nillable = true)
    protected String trataPeticionReturn;

    public String getTrataPeticionReturn() {
        return trataPeticionReturn;
    }

    public void setTrataPeticionReturn(String value) {
        this.trataPeticionReturn = value;
    }

}

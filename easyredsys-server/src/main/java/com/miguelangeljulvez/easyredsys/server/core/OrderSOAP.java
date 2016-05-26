package com.miguelangeljulvez.easyredsys.server.core;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Response")
public class OrderSOAP {

    protected String ds_version;
    protected String ds_response_merchant;
    protected String ds_order;

    public OrderSOAP(String ds_order) {
        this.ds_order = ds_order;
    }

    public OrderSOAP() {}

    @XmlAttribute(name = "Ds_version")
    public String getDs_version() {
        return ds_version;
    }

    public void setDs_version(String ds_version) {
        this.ds_version = ds_version;
    }

    @XmlElement(name = "Ds_Response_Merchant")
    public String getDs_response_merchant() {
        return ds_response_merchant;
    }

    public void setDs_response_merchant(String ds_response_merchant) {
        this.ds_response_merchant = ds_response_merchant;
    }

    @XmlTransient
    public String getDs_order() {
        return ds_order;
    }

    public void setDs_order(String ds_order) {
        this.ds_order = ds_order;
    }
}

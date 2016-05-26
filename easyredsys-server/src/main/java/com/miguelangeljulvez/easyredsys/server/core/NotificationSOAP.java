package com.miguelangeljulvez.easyredsys.server.core;

import com.miguelangeljulvez.easyredsys.client.core.Notification;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Request")
public class NotificationSOAP extends Notification {

    @XmlAttribute(name = "Ds_Version")
    public String getDs_version() {
        return ds_version;
    }

    public void setDs_version(String ds_version) {
        this.ds_version = ds_version;
    }

    @Override
    @XmlElement(name = "Fecha")
    public String getDs_Date() {
        return super.getDs_Date();
    }

    @Override
    public void setDs_Date(String ds_date) {
        super.setDs_Date(ds_date);
    }

    @Override
    @XmlElement(name = "Hora")
    public String getDs_Hour() {
        return super.getDs_Hour();
    }

    @Override
    public void setDs_Hour(String ds_hour) {
        super.setDs_Hour(ds_hour);
    }

    @XmlElement(name = "Ds_MerchantData")
    public String getDs_merchantData() {
        return ds_merchantData;
    }

    public void setDs_merchantData(String ds_merchantData) {
        this.ds_merchantData = ds_merchantData;
    }

    @XmlElement(name = "Ds_Card_Country")
    public String getDs_card_country() {
        return ds_card_country;
    }

    public void setDs_card_country(String ds_card_country) {
        this.ds_card_country = ds_card_country;
    }

    @XmlElement(name = "Ds_Card_Type")
    public String getDs_card_type() {
        return ds_card_type;
    }

    public void setDs_card_type(String ds_card_type) {
        this.ds_card_type = ds_card_type;
    }

    protected String ds_version;
    protected String ds_merchantData;
    protected String ds_card_country;
    protected String ds_card_type;

    @Override
    public boolean isValid(String claveSecreta, String expectedSignature) {
        return true;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Fecha:");
        sb.append(getDs_Date());
        sb.append(System.lineSeparator());
        sb.append("Hora:");
        sb.append(getDs_Hour());
        sb.append(System.lineSeparator());
        sb.append("Ds_MerchantData:");
        sb.append(getDs_merchantData());
        sb.append(System.lineSeparator());
        sb.append("Ds_Card_Country:");
        sb.append(getDs_card_country());
        sb.append(System.lineSeparator());
        sb.append("Ds_Card_Type:");
        sb.append(getDs_card_type());
        sb.append(System.lineSeparator());
        sb.append(super.toString());

        return sb.toString();
    }
}

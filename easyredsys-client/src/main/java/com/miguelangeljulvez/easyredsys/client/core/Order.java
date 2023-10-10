package com.miguelangeljulvez.easyredsys.client.core;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import sis.redsys.api.ApiMacSha256;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public abstract class Order {

    protected final static String ds_SignatureVersion = "HMAC_SHA256_V1";

    protected final ApiMacSha256 apiMacSha256 = new ApiMacSha256();

    protected AppConfig appConfig;

    public Order() {
        //Inicializamos por no tocar las librerías de redsys
        apiMacSha256.setParameter("DS_MERCHANT_AMOUNT", "");
        apiMacSha256.setParameter("DS_MERCHANT_ORDER", "");
        apiMacSha256.setParameter("DS_MERCHANT_MERCHANTCODE", "");
        apiMacSha256.setParameter("DS_MERCHANT_TERMINAL", "");
        apiMacSha256.setParameter("DS_MERCHANT_CURRENCY", "");
        apiMacSha256.setParameter("DS_MERCHANT_TRANSACTIONTYPE", "");
        apiMacSha256.setParameter("DS_MERCHANT_PRODUCTDESCRIPTION", "");
        apiMacSha256.setParameter("DS_MERCHANT_TITULAR", "");
        apiMacSha256.setParameter("DS_MERCHANT_MERCHANTDATA", "");
        apiMacSha256.setParameter("DS_MERCHANT_IDENTIFIER", "");
    }

    public String getDs_SignatureVersion() {
        return ds_SignatureVersion;
    }

    @XmlElement(name = "DS_MERCHANT_AMOUNT")
    public long getDs_merchant_amount() {
        try {
            return (Long.valueOf(apiMacSha256.getParameter("DS_MERCHANT_AMOUNT")));
        } catch (NumberFormatException e) {
        }

        return 0;
    }

    public void setDs_merchant_amount(long ds_merchant_amount) {
        apiMacSha256.setParameter("DS_MERCHANT_AMOUNT", String.valueOf(ds_merchant_amount));
    }

    @XmlElement(name = "DS_MERCHANT_ORDER")
    public String getDs_merchant_order() {
        return apiMacSha256.getParameter("DS_MERCHANT_ORDER");
    }

    public void setDs_merchant_order(String ds_merchant_order) {
        apiMacSha256.setParameter("DS_MERCHANT_ORDER", String.valueOf(ds_merchant_order));
    }

    @XmlElement(name = "DS_MERCHANT_MERCHANTCODE")
    public long getDs_merchant_merchantcode() {
        try {
            return Long.valueOf(apiMacSha256.getParameter("DS_MERCHANT_MERCHANTCODE"));
        } catch (NumberFormatException e) {
        }

        return -1;
    }

    public void setDs_merchant_merchantcode(long ds_merchant_merchantcode) {
        apiMacSha256.setParameter("DS_MERCHANT_MERCHANTCODE", String.valueOf(ds_merchant_merchantcode));
    }

    @XmlElement(name = "DS_MERCHANT_TERMINAL")
    public long getDs_merchant_terminal() {
        try {
            return Long.valueOf(apiMacSha256.getParameter("DS_MERCHANT_TERMINAL"));
        } catch (NumberFormatException e) {
        }

        return 0;
    }

    public void setDs_merchant_terminal(long ds_merchant_terminal) {
        apiMacSha256.setParameter("DS_MERCHANT_TERMINAL", String.valueOf(ds_merchant_terminal));
    }

    @XmlElement(name = "DS_MERCHANT_CURRENCY")
    public long getDs_merchant_currency() {
        try {
            return Long.valueOf(apiMacSha256.getParameter("DS_MERCHANT_CURRENCY"));
        } catch (NumberFormatException e) {
        }

        return 0;
    }

    public void setDs_merchant_currency(long ds_merchant_currency) {
        apiMacSha256.setParameter("DS_MERCHANT_CURRENCY", String.valueOf(ds_merchant_currency));
    }

    @XmlElement(name = "DS_MERCHANT_IDENTIFIER")
    public String getDs_merchant_identifier() {
        return apiMacSha256.getParameter("DS_MERCHANT_IDENTIFIER");
    }

    public void setDs_merchant_identifier(String ds_merchant_identifier) {
        apiMacSha256.setParameter("DS_MERCHANT_IDENTIFIER", ds_merchant_identifier);
    }

    @XmlElement(name = "DS_MERCHANT_TRANSACTIONTYPE")
    public String getDs_merchant_transactiontype() {
        return apiMacSha256.getParameter("DS_MERCHANT_TRANSACTIONTYPE");
    }

    public void setDs_merchant_transactiontype(String ds_merchant_transactiontype) {
        apiMacSha256.setParameter("DS_MERCHANT_TRANSACTIONTYPE", ds_merchant_transactiontype);
    }

    @XmlElement(name = "DS_MERCHANT_PRODUCTDESCRIPTION")
    public String getDs_merchant_productdescription() {
        return apiMacSha256.getParameter("DS_MERCHANT_PRODUCTDESCRIPTION");
    }

    public void setDs_merchant_productdescription(String ds_merchant_productdescription) {
        apiMacSha256.setParameter("DS_MERCHANT_PRODUCTDESCRIPTION", ds_merchant_productdescription);
    }

    @XmlElement(name = "DS_MERCHANT_TITULAR")
    public String getDs_merchant_titular() {
        return apiMacSha256.getParameter("DS_MERCHANT_TITULAR");
    }

    public void setDs_merchant_titular(String ds_merchant_titular) {
        apiMacSha256.setParameter("DS_MERCHANT_TITULAR", ds_merchant_titular);
    }

    @XmlElement(name = "DS_MERCHANT_MERCHANTDATA")
    public String getDs_merchant_merchantdata() {
        return apiMacSha256.getParameter("DS_MERCHANT_MERCHANTDATA");
    }

    public void setDs_merchant_merchantdata(String ds_merchant_merchantdata) {
        apiMacSha256.setParameter("DS_MERCHANT_MERCHANTDATA", ds_merchant_merchantdata);
    }

    @XmlTransient
    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("DS_MERCHANT_AMOUNT:");
        sb.append(getDs_merchant_amount());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_ORDER:");
        sb.append(getDs_merchant_order());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_MERCHANTCODE:");
        sb.append(getDs_merchant_merchantcode());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_TERMINAL:");
        sb.append(getDs_merchant_terminal());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_CURRENCY:");
        sb.append(getDs_merchant_currency());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_TRANSACTIONTYPE:");
        sb.append(getDs_merchant_transactiontype());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_IDENTIFIER:");
        sb.append(getDs_merchant_identifier());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_PRODUCTDESCRIPTION:");
        sb.append(getDs_merchant_productdescription());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_TITULAR:");
        sb.append(getDs_merchant_titular());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_MERCHANTDATA:");
        sb.append(getDs_merchant_merchantdata());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object instanceof Order) {
            Order order = (Order)object;

            if (order.getDs_merchant_order() !=null && order.getDs_merchant_order().equals(this.getDs_merchant_order())) {
                isEquals = true;
            }
        }
        return isEquals;
    }

}

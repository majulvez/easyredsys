package com.miguelangeljulvez.easyredsys.client.core;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@XmlRootElement(name = "DATOSENTRADA")
public class OrderNoCES extends Order {

    protected OrderNoCES() {
        //Inicializamos por no tocar las librerías de redsys
        apiMacSha256.setParameter("DS_MERCHANT_PAN", "");
        apiMacSha256.setParameter("DS_MERCHANT_CVV2", "");
        apiMacSha256.setParameter("DS_MERCHANT_EXPIRYDATE", "");
    }

    @XmlElement(name = "DS_MERCHANT_PAN")
    public String getDs_merchant_pan() {
        return apiMacSha256.getParameter("DS_MERCHANT_PAN");
    }

    public void setDs_merchant_pan(String ds_merchant_pan) {
        apiMacSha256.setParameter("DS_MERCHANT_PAN", ds_merchant_pan);
    }

    @XmlElement(name = "DS_MERCHANT_CVV2")
    public String getDs_merchant_cvv2() {
        return apiMacSha256.getParameter("DS_MERCHANT_CVV2");
    }

    public void setDs_merchant_cvv2(String ds_merchant_cvv2) {
        apiMacSha256.setParameter("DS_MERCHANT_CVV2", ds_merchant_cvv2);
    }

    @XmlElement(name = "DS_MERCHANT_EXPIRYDATE")
    public String getDs_merchant_expirydate() {
        return apiMacSha256.getParameter("DS_MERCHANT_EXPIRYDATE");
    }

    public void setDs_merchant_expirydate(String ds_merchant_expirydate) {
        apiMacSha256.setParameter("DS_MERCHANT_EXPIRYDATE", ds_merchant_expirydate);
    }

    @XmlElement(name = "DS_MERCHANT_TITULAR")
    public String getDs_merchant_titular() {
        return null;
    }

    @XmlElement(name = "DS_MERCHANT_MERCHANTDATA")
    public String getDs_merchant_merchantdata() {
        return null;
    }

    @XmlElement(name = "DS_MERCHANT_PRODUCTDESCRIPTION")
    public String getDs_merchant_productdescription() {
        return null;
    }



    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();

        sb.append(super.toString());

        sb.append("DS_MERCHANT_PAN:");
        sb.append(getDs_merchant_pan());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_CVV2:");
        sb.append(getDs_merchant_cvv2());
        sb.append(System.lineSeparator());
        sb.append("DS_MERCHANT_EXPIRYDATE:");
        sb.append(getDs_merchant_expirydate());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public static class Builder {

        private String transactionType = "";
        private long currency;
        private String order = "";
        private long amount;
        private String cardNumber = "";
        private String cvv2 = "";
        private String expiryDate = "";

        private AppConfig appConfig;

        public Builder(Class<? extends AppConfig> userActionClass) {
            try {
                this.appConfig = userActionClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public Builder transactionType(final TransactionType transactionType) {
            this.transactionType = transactionType.getCode();
            return this;
        }

        public Builder currency(final Currency currency) {
            this.currency = currency.getISOCodeNumeric();
            return this;
        }

        public Builder order(final String order) {
            this.order = order;
            return this;
        }

        public Builder amount(final long amount) {
            this.amount = amount;
            return this;
        }

        public Builder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder cvv2(final String cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        public Builder expiryDate(final String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public OrderNoCES build() {
            OrderNoCES orderNoCES =  new OrderNoCES();
            orderNoCES.setDs_merchant_merchantcode(Long.valueOf(appConfig.getMerchantCode()));
            orderNoCES.setDs_merchant_terminal(Long.valueOf(appConfig.getTerminal()));
            orderNoCES.setDs_merchant_transactiontype(transactionType);
            orderNoCES.setDs_merchant_currency(currency);
            orderNoCES.setDs_merchant_order(order);
            orderNoCES.setDs_merchant_amount(amount);
            orderNoCES.setDs_merchant_pan(cardNumber);
            orderNoCES.setDs_merchant_cvv2(cvv2);
            orderNoCES.setDs_merchant_expirydate(expiryDate);
            orderNoCES.setAppConfig(appConfig);

            //TODO - Lanzar error de validación

            return orderNoCES;
        }
    }
}

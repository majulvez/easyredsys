package com.miguelangeljulvez.easyredsys.client.core;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@XmlRootElement(name = "DATOSENTRADA")
public class OrderNoCESConfirmation extends OrderNoCES {

    protected OrderNoCESConfirmation() { //Inicializamos por no tocar las librerías de redsys
        apiMacSha256.setParameter("DS_MERCHANT_AUTHORISATIONCODE", "");
    }

    @XmlElement(name = "DS_MERCHANT_AUTHORISATIONCODE")
    public String getDs_merchant_authorisationcode() {
        return apiMacSha256.getParameter("DS_MERCHANT_AUTHORISATIONCODE");
    }

    public void setDs_merchant_authorisationcode(String ds_merchant_authorisationcode) {
        apiMacSha256.setParameter("DS_MERCHANT_AUTHORISATIONCODE", ds_merchant_authorisationcode);
    }

    public static class Builder {

        private long merchantCode;
        private long terminal;
        private String transactionType;
        private long currency;
        private String order;
        private long amount;
        private String ds_merchant_authorisationcode = "";
        private String cardNumber = "";
        private String cvv2 = "";
        private String expiryDate = "";

        private AppConfig appConfig;

        public Builder(Class<? extends AppConfig> userActionClass) {
            try {
                this.appConfig = userActionClass.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        public Builder(NotificationNoCES notificationNoCES) {
            this.merchantCode = Long.valueOf(notificationNoCES.getDs_MerchantCode());
            this.terminal = Long.valueOf(notificationNoCES.getDs_Terminal());
            this.currency = Long.valueOf(notificationNoCES.getDs_Currency());
            this.ds_merchant_authorisationcode = notificationNoCES.getDs_AuthorisationCode();
        }


        public Builder merchantCode(final String merchantCode) {
            this.merchantCode = Long.valueOf(merchantCode);
            return this;
        }

        public Builder terminal(final String terminal) {
            this.terminal = Long.valueOf(terminal);
            return this;
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

        public OrderNoCESConfirmation build() {
            OrderNoCESConfirmation orderNoCESConfirmation =  new OrderNoCESConfirmation();
            orderNoCESConfirmation.setDs_merchant_merchantcode(merchantCode);
            orderNoCESConfirmation.setDs_merchant_terminal(terminal);
            orderNoCESConfirmation.setDs_merchant_transactiontype(transactionType);
            orderNoCESConfirmation.setDs_merchant_currency(currency);
            orderNoCESConfirmation.setDs_merchant_order(order);
            orderNoCESConfirmation.setDs_merchant_amount(amount);
            orderNoCESConfirmation.setDs_merchant_authorisationcode(ds_merchant_authorisationcode);
            orderNoCESConfirmation.setDs_merchant_pan(cardNumber);
            orderNoCESConfirmation.setDs_merchant_cvv2(cvv2);
            orderNoCESConfirmation.setDs_merchant_expirydate(expiryDate);
            orderNoCESConfirmation.setAppConfig(appConfig);

            return orderNoCESConfirmation;
        }
    }
}

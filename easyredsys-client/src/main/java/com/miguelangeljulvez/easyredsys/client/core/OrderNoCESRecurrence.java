package com.miguelangeljulvez.easyredsys.client.core;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@XmlRootElement(name = "DATOSENTRADA")
public final class OrderNoCESRecurrence extends OrderNoCESConfirmation {

    @XmlElement(name = "DS_MERCHANT_SUMTOTAL")
    public String getDs_merchant_sumtotal() {
        return apiMacSha256.getParameter("DS_MERCHANT_SUMTOTAL");
    }

    public void setDs_merchant_sumtotal(long ds_merchant_sumtotal) {
        apiMacSha256.setParameter("DS_MERCHANT_SUMTOTAL", String.valueOf(ds_merchant_sumtotal));
    }

    @XmlElement(name = "DS_MERCHANT_DATEFRECUENCY")
    public String getDs_merchant_datefrecuency() {
        return apiMacSha256.getParameter("DS_MERCHANT_DATEFRECUENCY");
    }

    public void setDs_merchant_datefrecuency(String ds_merchant_datefrecuency) {
        apiMacSha256.setParameter("DS_MERCHANT_DATEFRECUENCY", ds_merchant_datefrecuency);
    }

    @XmlElement(name = "DS_MERCHANT_CHARGEEXPIRYDATE")
    public String getDs_merchant_chargeexpirydate() {
        return apiMacSha256.getParameter("DS_MERCHANT_CHARGEEXPIRYDATE");
    }

    public void setDs_merchant_chargeexpirydate(String ds_merchant_chargeexpirydate) {
        apiMacSha256.setParameter("DS_MERCHANT_CHARGEEXPIRYDATE", ds_merchant_chargeexpirydate);
    }

    @XmlElement(name = "DS_MERCHANT_TRANSACTIONDATE")
    public String getDs_merchant_transactiondate() {
        return apiMacSha256.getParameter("DS_MERCHANT_TRANSACTIONDATE");
    }

    public void setDs_merchant_transactiondate(String ds_merchant_transactiondate) {
        apiMacSha256.setParameter("DS_MERCHANT_TRANSACTIONDATE", ds_merchant_transactiondate);
    }

    public static class Builder {

        private long merchantCode;
        private long terminal;
        private String transactionType;
        private long currency;
        private String order;
        private long amount;
        private String authorisationcode = "";
        private String cardNumber = "";
        private String cvv2 = "";
        private String expiryDate = "";
        private long sumtotal;
        private String datefrecuency = "";
        private String chargeexpirydate = "";
        private String transactiondate = "";

        private AppConfig appConfig;

        public Builder(Class<? extends AppConfig> userActionClass) {
            try {
                this.appConfig = userActionClass.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        public Builder(AppConfig userActionClass) {
            this.appConfig = userActionClass;
        }

        public Builder(NotificationNoCES notificationNoCES) {
            this.merchantCode = Long.parseLong(notificationNoCES.getDs_MerchantCode());
            this.terminal = Long.parseLong(notificationNoCES.getDs_Terminal());
            this.currency = Long.parseLong(notificationNoCES.getDs_Currency());
            this.authorisationcode = notificationNoCES.getDs_AuthorisationCode();
        }


        public Builder merchantCode(final String merchantCode) {
            this.merchantCode = Long.parseLong(merchantCode);
            return this;
        }

        public Builder terminal(final String terminal) {
            this.terminal = Long.parseLong(terminal);
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

        public Builder sumtotal(final long sumtotal) {
            this.sumtotal = sumtotal;
            return this;
        }

        public Builder datefrecuency(final String datefrecuency) {
            this.datefrecuency = datefrecuency;
            return this;
        }

        public Builder chargeexpirydate(final String chargeexpirydate) {
            this.chargeexpirydate = chargeexpirydate;
            return this;
        }

        public Builder transactiondate(final String transactiondate) {
            this.transactiondate = transactiondate;
            return this;
        }

        public OrderNoCESRecurrence build() {
            OrderNoCESRecurrence orderNoCESRecurrence =  new OrderNoCESRecurrence();
            orderNoCESRecurrence.setDs_merchant_merchantcode(merchantCode);
            orderNoCESRecurrence.setDs_merchant_terminal(terminal);
            orderNoCESRecurrence.setDs_merchant_transactiontype(transactionType);
            orderNoCESRecurrence.setDs_merchant_currency(currency);
            orderNoCESRecurrence.setDs_merchant_order(order);
            orderNoCESRecurrence.setDs_merchant_amount(amount);
            orderNoCESRecurrence.setDs_merchant_pan(cardNumber);
            orderNoCESRecurrence.setDs_merchant_cvv2(cvv2);
            orderNoCESRecurrence.setDs_merchant_expirydate(expiryDate);
            orderNoCESRecurrence.setDs_merchant_authorisationcode(authorisationcode);
            orderNoCESRecurrence.setDs_merchant_sumtotal(sumtotal);
            orderNoCESRecurrence.setDs_merchant_datefrecuency(datefrecuency);
            orderNoCESRecurrence.setDs_merchant_chargeexpirydate(chargeexpirydate);
            orderNoCESRecurrence.setDs_merchant_transactiondate(transactiondate);
            orderNoCESRecurrence.setAppConfig(appConfig);

            return orderNoCESRecurrence;
        }
    }
}

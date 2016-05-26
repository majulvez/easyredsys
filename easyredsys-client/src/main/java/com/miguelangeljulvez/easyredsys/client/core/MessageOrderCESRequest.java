package com.miguelangeljulvez.easyredsys.client.core;


import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.util.RedsysAddresses;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageOrderCESRequest {

    private volatile String claveSecreta;
    private volatile boolean testMode = true;

    private OrderCES orderCES;

    public MessageOrderCESRequest(OrderCES orderCES, String claveSecreta) {
        this.orderCES = orderCES;
        this.claveSecreta = claveSecreta;
    }

    public MessageOrderCESRequest() {}

    public String getDs_MerchantParameters() {

        String merchanParameters = "";

        try {
            merchanParameters = orderCES.apiMacSha256.createMerchantParameters();
        } catch (UnsupportedEncodingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return merchanParameters;
    }

    public String getDs_Signature() {

        String dsSignature = "";

        try {
            dsSignature = orderCES.apiMacSha256.createMerchantSignature(claveSecreta);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return dsSignature;
    }

    public String getDs_SignatureVersion() {
        return orderCES.getDs_SignatureVersion();
    }

    public OrderCES getOrderCES() {
        return orderCES;
    }

    public void setOrderCES(OrderCES orderCES) {
        this.orderCES = orderCES;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public String getRedsysUrl() { //TODO - Al fichero de propieades
        if (testMode) {
            return RedsysAddresses.getRedirectURL("test");
        } else {
            return RedsysAddresses.getRedirectURL("pro");
        }
    }

    public String getClaveSecreta() {
        return claveSecreta;
    }

    public void setClaveSecreta(String claveSecreta) {
        this.claveSecreta = claveSecreta;
    }

    public static class Builder {
        private OrderCES orderCES;
        private String claveSecreta;
        private boolean testMode;

        public Builder() {}

        public Builder(Class<? extends AppConfig> userActionClass) {
            try {
                Method isTestMode = userActionClass.getDeclaredMethod("isTestMode");
                isTestMode.setAccessible(true);
                Method getSecretKey = userActionClass.getDeclaredMethod("getSecretKey");
                getSecretKey.setAccessible(true);

                this.testMode = (boolean)isTestMode.invoke(null);
                if (!testMode)
                    this.claveSecreta = (String) getSecretKey.invoke(null);
                else
                    this.claveSecreta = "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public Builder withOrder(OrderCES orderCES) {
            this.orderCES = orderCES;
            return this;
        }

        public Builder secretKey(String claveSecreta) {
            this.claveSecreta = claveSecreta;
            return this;
        }

        public Builder testMode(boolean testMode) {
            this.testMode = testMode;
            return this;
        }

        public MessageOrderCESRequest build() {
            MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest();
            messageOrderCESRequest.setClaveSecreta(claveSecreta);
            messageOrderCESRequest.setOrderCES(orderCES);
            messageOrderCESRequest.setTestMode(testMode);

            return messageOrderCESRequest;
        }
    }

    private static final Logger _log = Logger.getLogger(MessageOrderCESRequest.class.getName());
}

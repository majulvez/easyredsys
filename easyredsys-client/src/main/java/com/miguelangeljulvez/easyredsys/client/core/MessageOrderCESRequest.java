package com.miguelangeljulvez.easyredsys.client.core;


import com.miguelangeljulvez.easyredsys.client.util.EasyredsysUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageOrderCESRequest {

    private OrderCES orderCES;

    private MessageOrderCESRequest() {}

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
            dsSignature = orderCES.apiMacSha256.createMerchantSignature(EasyredsysUtil.getSecretyKey(orderCES));
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

    public String getRedsysUrl() { //TODO - Al fichero de propiedades
        if (orderCES.getAppConfig().isTestMode()) {
            return EasyredsysUtil.getRedirectURL("test");
        } else {
            return EasyredsysUtil.getRedirectURL("pro");
        }
    }

    public static class Builder {
        private OrderCES orderCES;

        public Builder(OrderCES orderCES) {
            this.orderCES = orderCES;
        }

        public MessageOrderCESRequest build() {
            MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest();
            messageOrderCESRequest.setOrderCES(orderCES);

            return messageOrderCESRequest;
        }
    }

    private static final Logger _log = Logger.getLogger(MessageOrderCESRequest.class.getName());
}

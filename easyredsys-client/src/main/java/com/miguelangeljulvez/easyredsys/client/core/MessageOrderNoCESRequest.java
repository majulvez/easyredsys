package com.miguelangeljulvez.easyredsys.client.core;


import com.miguelangeljulvez.easyredsys.client.util.EasyredsysUtil;
import com.miguelangeljulvez.easyredsys.client.util.XMLUtil;
import sis.redsys.api.ApiWsMacSha256;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@XmlRootElement(name = "REQUEST")
public class MessageOrderNoCESRequest {

    private final ApiWsMacSha256 apiWsMacSha256 = new ApiWsMacSha256();

    private OrderNoCES orderNoCES;

    private MessageOrderNoCESRequest() {}

    @XmlElement(name = "DS_SIGNATUREVERSION")
    public String getDs_SignatureVersion() {
        return orderNoCES.getDs_SignatureVersion();
    }

    @XmlElement(name = "DS_SIGNATURE")
    public String getDs_Signature() {

        System.out.println("La clave es: " + EasyredsysUtil.getSecretyKey(orderNoCES));
        System.out.println("Lo que se cifra: " + XMLUtil.toRedsysXML(orderNoCES));

        String ds_signature = "";
        try {
            ds_signature = apiWsMacSha256.createMerchantSignatureHostToHost(EasyredsysUtil.getSecretyKey(orderNoCES), XMLUtil.toRedsysXML(orderNoCES));

            System.out.println("Genero: " + ds_signature);
        } catch (UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | NoSuchPaddingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return ds_signature;
    }

    @XmlAnyElement(lax=true)
    public OrderNoCES getOrderNoCES() {
        return orderNoCES;
    }

    public void setOrderNoCES(OrderNoCES orderNoCES) {
        this.orderNoCES = orderNoCES;
    }


    @XmlTransient
    public String getRedsysUrl() {
        if (orderNoCES.getAppConfig().isTestMode()) {
            return EasyredsysUtil.getWebserviceURL("test");
        } else {
            return EasyredsysUtil.getWebserviceURL("pro");
        }
    }

    public static class Builder {
        private OrderNoCES orderNoCES;

        public Builder(OrderNoCES orderNoCES) {
            this.orderNoCES = orderNoCES;
        }

        public MessageOrderNoCESRequest build() {
            MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest();
            messageOrderNoCESRequest.setOrderNoCES(orderNoCES);

            return messageOrderNoCESRequest;
        }
    }

    private static final Logger _log = Logger.getLogger(MessageOrderNoCESRequest.class.getName());
}

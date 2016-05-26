package com.miguelangeljulvez.easyredsys.client.core;


import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.util.RedsysAddresses;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@XmlRootElement(name = "REQUEST")
public class MessageOrderNoCESRequest {

    private final ApiWsMacSha256 apiWsMacSha256 = new ApiWsMacSha256();

    private volatile String claveSecreta;

    private volatile boolean testMode = true;

    private OrderNoCES orderNoCES;

    public MessageOrderNoCESRequest(OrderNoCES orderNoCES, String claveSecreta) {
        this.orderNoCES = orderNoCES;
        this.claveSecreta = claveSecreta;
    }

    public MessageOrderNoCESRequest() {}

    @XmlElement(name = "DS_SIGNATUREVERSION")
    public String getDs_SignatureVersion() {
        return orderNoCES.getDs_SignatureVersion();
    }

    @XmlElement(name = "DS_SIGNATURE")
    public String getDs_Signature() {

        String ds_signature = "";
        try {
            ds_signature = apiWsMacSha256.createMerchantSignatureHostToHost(claveSecreta, XMLUtil.toRedsysXML(orderNoCES));
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
    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    @XmlTransient
    public String getRedsysUrl() {
        if (testMode) {
            return RedsysAddresses.getWebserviceURL("test");
        } else {
            return RedsysAddresses.getWebserviceURL("pro");
        }
    }

    @XmlTransient
    public String getClaveSecreta() {
        return claveSecreta;
    }

    public void setClaveSecreta(String claveSecreta) {
        this.claveSecreta = claveSecreta;
    }

    public static class Builder {
        private OrderNoCES orderNoCES;
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

        public Builder withOrder(OrderNoCES orderNoCES) {
            this.orderNoCES = orderNoCES;
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

        public MessageOrderNoCESRequest build() {
            MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest();
            messageOrderNoCESRequest.setClaveSecreta(claveSecreta);
            messageOrderNoCESRequest.setOrderNoCES(orderNoCES);
            messageOrderNoCESRequest.setTestMode(testMode);

            return messageOrderNoCESRequest;
        }
    }

    private static final Logger _log = Logger.getLogger(MessageOrderNoCESRequest.class.getName());
}

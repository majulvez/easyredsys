package com.miguelangeljulvez.easyredsys.client.core;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MessageOrderCESResponse {

    private volatile String claveSecreta;

    private String ds_SignatureVersion;
    private String ds_Signature;
    private String ds_MerchantParameters;

    private NotificationCES operationCES;

    public MessageOrderCESResponse(String ds_SignatureVersion, String ds_Signature, String ds_MerchantParameters, String claveSecreta) {

        this.ds_SignatureVersion = ds_SignatureVersion;
        this.ds_Signature = ds_Signature;
        this.ds_MerchantParameters = ds_MerchantParameters;
        this.claveSecreta = claveSecreta;

        operationCES = new NotificationCES();

        try {
            operationCES.getApiMacSha256().decodeMerchantParameters(ds_MerchantParameters);
            operationCES.setDs_MerchantParameters(ds_MerchantParameters);
        } catch (UnsupportedEncodingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public MessageOrderCESResponse() {}

    public NotificationCES getOperationCES() {
        return operationCES;
    }

    public void setOperationCES(NotificationCES operationCES) {
        this.operationCES = operationCES;
    }

    public boolean isValid() {
        return operationCES.isValid(claveSecreta, ds_Signature);
    }

    public String getDs_SignatureVersion() {
        return ds_SignatureVersion;
    }

    public void setDs_SignatureVersion(String ds_SignatureVersion) {
        this.ds_SignatureVersion = ds_SignatureVersion;
    }

    public String getDs_Signature() {
        return ds_Signature;
    }

    public void setDs_Signature(String ds_Signature) {
        this.ds_Signature = ds_Signature;
    }

    public String getDs_MerchantParameters() {
        return ds_MerchantParameters;
    }

    public void setDs_MerchantParameters(String ds_MerchantParameters) {
        this.ds_MerchantParameters = ds_MerchantParameters;
    }

    public String getClaveSecreta() {
        return claveSecreta;
    }

    public void setClaveSecreta(String claveSecreta) {
        this.claveSecreta = claveSecreta;
    }


    private static final Logger _log = Logger.getLogger(MessageOrderCESResponse.class.getName());
}

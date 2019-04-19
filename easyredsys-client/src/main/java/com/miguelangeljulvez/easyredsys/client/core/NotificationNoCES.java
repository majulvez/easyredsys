package com.miguelangeljulvez.easyredsys.client.core;

import org.json.JSONException;
import sis.redsys.api.ApiWsMacSha256;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.annotation.XmlElement;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NotificationNoCES extends Notification {

    @Override
    public boolean isValid(String claveSecreta, String expectedSignature) {

        String signature = "";
        try {
            ApiWsMacSha256 apiWsMacSha256 = new ApiWsMacSha256();

            signature = apiWsMacSha256.createSignatureResponseHostToHost(claveSecreta, getCadenaConcatenada(), getDs_Order());
        } catch (UnsupportedEncodingException | InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return !signature.isEmpty() && signature.equals(expectedSignature);
    }

    private String getCadenaConcatenada() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDs_Amount());
        sb.append(getDs_Order());
        sb.append(getDs_MerchantCode());
        sb.append(getDs_Currency());
        sb.append(getDs_Response());
        sb.append(getDs_CardNumber());
        sb.append(getDs_TransactionType());
        sb.append(getDs_SecurePayment());

        return sb.toString();
    }

    @XmlElement(name = "Ds_CardNumber")
    public String getDs_CardNumber() {
        String ds_cardNumber = "";
        try {
            ds_cardNumber = apiMacSha256.getParameter("Ds_CardNumber");
        } catch (JSONException e) {
        }
        return ds_cardNumber;
    }

    public void setDs_CardNumber(String ds_cardNumber) {
        apiMacSha256.setParameter("Ds_CardNumber", ds_cardNumber);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("Ds_CardNumber:");
        sb.append(getDs_CardNumber());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private static final Logger _log = Logger.getLogger(NotificationNoCES.class.getName());

}

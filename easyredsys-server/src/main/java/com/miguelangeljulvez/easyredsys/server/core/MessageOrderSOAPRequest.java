package com.miguelangeljulvez.easyredsys.server.core;

import com.miguelangeljulvez.easyredsys.server.util.XMLSOAPUtil;
import sis.redsys.api.ApiMacSha256;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@XmlRootElement(name = "Message")
public class MessageOrderSOAPRequest {

    private final ApiMacSha256 apiMacSha256 = new ApiMacSha256();

    private String originalXml;
    private NotificationSOAP notificationSOAP;
    private String signature;
    private String claveSecreta;

    public MessageOrderSOAPRequest(String xml, String claveSecreta) {

        MessageOrderSOAPRequest messageOrderSOAPRequest = XMLSOAPUtil.fromRedsysXMLNotificationSOAP(xml);

        this.originalXml = xml;
        this.claveSecreta = claveSecreta;
        this.signature = messageOrderSOAPRequest.getSignature();
        this.notificationSOAP = messageOrderSOAPRequest.getNotificationSOAP();
    }

    public MessageOrderSOAPRequest() {}

    @XmlElement(name = "Request")
    public NotificationSOAP getNotificationSOAP() {
        return notificationSOAP;
    }

    public void setNotificationSOAP(NotificationSOAP notificationSOAP) {
        this.notificationSOAP = notificationSOAP;
    }

    @XmlElement(name = "Signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @XmlTransient
    public String getClaveSecreta() {
        return claveSecreta;
    }

    public void setClaveSecreta(String claveSecreta) {
        this.claveSecreta = claveSecreta;
    }

    @XmlTransient
    public String getOriginalXml() {
        return originalXml;
    }

    public void setOriginalXml(String originalXml) {
        this.originalXml = originalXml;
    }

    public boolean isValid() {

        String signature = "";
        try {
            signature = apiMacSha256.createMerchantSignatureNotifSOAPRequest(claveSecreta, originalXml);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidAlgorithmParameterException | BadPaddingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return  !signature.isEmpty() && signature.equals(getSignature());
    }

    @Override
    public String toString() {
       StringBuffer sb = new StringBuffer();

        sb.append("- Request");
        sb.append(System.lineSeparator());
        sb.append(getNotificationSOAP().toString());
        sb.append("- Fin request");
        sb.append(System.lineSeparator());
        sb.append("Signature:");
        sb.append(getSignature());

        return sb.toString();
    }

    private static final Logger _log = Logger.getLogger(MessageOrderSOAPRequest.class.getName());
}

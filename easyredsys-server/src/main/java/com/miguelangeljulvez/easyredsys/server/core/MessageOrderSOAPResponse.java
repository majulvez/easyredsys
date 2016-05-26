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
public class MessageOrderSOAPResponse {

    private ApiMacSha256 apiMacSha256 = new ApiMacSha256();

    protected OrderSOAP orderSOAP;

    protected String signature;

    protected String claveSecreta;

    public MessageOrderSOAPResponse(OrderSOAP orderSOAP, String claveSecreta) {
        this.orderSOAP = orderSOAP;
        this.claveSecreta = claveSecreta;
    }

    public MessageOrderSOAPResponse() {}

    @XmlElement(name = "Response")
    public OrderSOAP getOrderSOAP() {
        return orderSOAP;
    }

    public void setOrderSOAP(OrderSOAP orderSOAP) {
        this.orderSOAP = orderSOAP;
    }

    @XmlElement(name = "Signature")
    public String getSignature() {

        String signature = "";
        try {
            signature = apiMacSha256.createMerchantSignatureNotifSOAPResponse(claveSecreta,  XMLSOAPUtil.toRedsysXML(orderSOAP), orderSOAP.getDs_order());
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidAlgorithmParameterException | BadPaddingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

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


    private static final Logger _log = Logger.getLogger(MessageOrderSOAPResponse.class.getName());
}

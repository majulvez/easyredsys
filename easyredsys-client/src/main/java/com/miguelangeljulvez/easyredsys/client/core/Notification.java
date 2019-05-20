package com.miguelangeljulvez.easyredsys.client.core;


import com.miguelangeljulvez.easyredsys.client.util.Country;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import org.json.JSONException;
import sis.redsys.api.ApiMacSha256;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@XmlRootElement
public abstract class Notification {

    protected final ApiMacSha256 apiMacSha256 = new ApiMacSha256();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @XmlElement(name = "Ds_Date")
    public String getDs_Date() {
        String ds_Date = "";
        try {
            ds_Date = apiMacSha256.getParameter("Ds_Date");
        } catch (JSONException e) {
        }
        return ds_Date;
    }

    public void setDs_Date(String ds_Date) {
        apiMacSha256.setParameter("Ds_Date", ds_Date);
    }

    @XmlElement(name = "Ds_Hour")
    public String getDs_Hour() {
        String ds_Hour = "";
        try {
            ds_Hour = apiMacSha256.getParameter("Ds_Hour");
        } catch (JSONException e) {
        }
        return ds_Hour;
    }

    public void setDs_Hour(String ds_Hour) {
        apiMacSha256.setParameter("Ds_Hour", ds_Hour);
    }

    @XmlElement(name = "Ds_Amount")
    public String getDs_Amount() {
        String ds_Amount = "";
        try {
            ds_Amount = apiMacSha256.getParameter("Ds_Amount");
        } catch (JSONException e) {
        }
        return ds_Amount;
    }

    public void setDs_Amount(String ds_Amount) {
        apiMacSha256.setParameter("Ds_Amount", ds_Amount);
    }

    @XmlElement(name = "Ds_Currency")
    public String getDs_Currency() {
        String ds_Currency = "";
        try {
            ds_Currency = apiMacSha256.getParameter("Ds_Currency");
        } catch (JSONException e) {
        }
        return ds_Currency;
    }

    public void setDs_Currency(String ds_Currency) {
        apiMacSha256.setParameter("Ds_Currency", ds_Currency);
    }

    @XmlElement(name = "Ds_Order")
    public String getDs_Order() {
        String ds_Order = "";
        try {
            ds_Order = apiMacSha256.getParameter("Ds_Order");
        } catch (JSONException e) {
        }
        return ds_Order;
    }

    public void setDs_Order(String ds_Order) {
        apiMacSha256.setParameter("Ds_Order", ds_Order);
    }

    @XmlElement(name = "Ds_MerchantCode")
    public String getDs_MerchantCode() {
        String ds_MerchantCode = "";
        try {
            ds_MerchantCode = apiMacSha256.getParameter("Ds_MerchantCode");
        } catch (JSONException e) {
        }
        return ds_MerchantCode;
    }

    public void setDs_MerchantCode(String ds_MerchantCode) {
        apiMacSha256.setParameter("Ds_MerchantCode", ds_MerchantCode);
    }

    @XmlElement(name = "Ds_MerchantData")
    public String getDs_MerchantData() {
        String ds_MerchantData = "";
        try {
            ds_MerchantData = apiMacSha256.getParameter("Ds_MerchantData");
        } catch (JSONException e) {
        }
        return ds_MerchantData;
    }

    public void setDs_MerchantData(String ds_MerchantData) {
        apiMacSha256.setParameter("Ds_MerchantData", ds_MerchantData);
    }

    @XmlElement(name = "Ds_Terminal")
    public String getDs_Terminal() {
        String ds_Terminal = "";
        try {
            ds_Terminal = apiMacSha256.getParameter("Ds_Terminal");
        } catch (JSONException e) {
        }
        return ds_Terminal;
    }

    public void setDs_Terminal(String ds_Terminal) {
        apiMacSha256.setParameter("Ds_Terminal", ds_Terminal);
    }

    @XmlElement(name = "Ds_Response")
    public String getDs_Response() {
        String ds_Response = "";
        try {
            ds_Response = apiMacSha256.getParameter("Ds_Response");
        } catch (JSONException e) {
        }
        return ds_Response;
    }

    public void setDs_Response(String ds_Response) {
        apiMacSha256.setParameter("Ds_Response", ds_Response);
    }

    @XmlElement(name = "Ds_AuthorisationCode")
    public String getDs_AuthorisationCode() {
        String ds_AuthorisationCode = "";
        try {
            ds_AuthorisationCode = apiMacSha256.getParameter("Ds_AuthorisationCode");
        } catch (JSONException e) {
        }
        return ds_AuthorisationCode;
    }

    public void setDs_AuthorisationCode(String ds_AuthorisationCode) {
        apiMacSha256.setParameter("Ds_AuthorisationCode", ds_AuthorisationCode);
    }

    @XmlElement(name = "Ds_TransactionType")
    public String getDs_TransactionType() {
        String ds_TransactionType = "";
        try {
            ds_TransactionType = apiMacSha256.getParameter("Ds_TransactionType");
        } catch (JSONException e) {
        }
        return ds_TransactionType;
    }

    public void setDs_TransactionType(String ds_TransactionType) {
        apiMacSha256.setParameter("Ds_TransactionType", ds_TransactionType);
    }

    @XmlElement(name = "Ds_SecurePayment")
    public String getDs_SecurePayment() {
        String ds_SecurePayment = "";
        try {
            ds_SecurePayment = apiMacSha256.getParameter("Ds_SecurePayment");
        } catch (JSONException e) {
        }
        return ds_SecurePayment;
    }

    public void setDs_SecurePayment(String ds_SecurePayment) {
        apiMacSha256.setParameter("Ds_SecurePayment", ds_SecurePayment);
    }

    @XmlElement(name = "Ds_ConsumerLanguage")
    public String getDs_ConsumerLanguage() {
        String ds_ConsumerLanguage = "";
        try {
            ds_ConsumerLanguage = apiMacSha256.getParameter("Ds_ConsumerLanguage");
        } catch (JSONException e) {
        }
        return ds_ConsumerLanguage;
    }

    public void setDs_ConsumerLanguage(String ds_Language) {
        apiMacSha256.setParameter("Ds_ConsumerLanguage", ds_Language);
    }

    @XmlElement(name="Ds_Signature")
    public String getDs_Signature() {
        String ds_Signature = "";
        try {
            ds_Signature = apiMacSha256.getParameter("Ds_Signature");
        } catch (JSONException e) {}
        return ds_Signature;
    }

    public void setDs_Signature(String ds_Signature) {
        apiMacSha256.setParameter("Ds_Signature", ds_Signature);
    }

    /*
    @XmlElement(name="Ds_Language")
    public String getDs_Language() {
        String ds_Language = "";
        try {
            ds_Language = apiMacSha256.getParameter("Ds_Language");
        } catch (JSONException e) {}
        return ds_Language;
    }

    public void setDs_Language(String ds_Language) {
        apiMacSha256.setParameter("Ds_Language", ds_Language);
    }
    */

    @XmlElement(name="Ds_Card_Country")
    public String getDs_Card_Country() {
        String ds_Card_Country = "";
        try {
            ds_Card_Country = apiMacSha256.getParameter("Ds_Card_Country");
        } catch (JSONException e) {}
        return ds_Card_Country;
    }

    public void setDs_Card_Country(String ds_Card_Country) {
        apiMacSha256.setParameter("Ds_Card_Country", ds_Card_Country);
    }

    @XmlElement(name="Ds_Card_Type")
    public String getDs_Card_Type() {
        String ds_Card_Type = "";
        try {
            ds_Card_Type = apiMacSha256.getParameter("Ds_Card_Type");
        } catch (JSONException e) {}
        return ds_Card_Type;
    }

    public void setDs_Card_Type(String ds_Card_Type) {
        apiMacSha256.setParameter("Ds_Card_Type", ds_Card_Type);
    }

    @XmlElement(name = "epochTime")
    public long getEpochTime() {

        long epochTime = 0;
        try {
            String dateString = URLDecoder.decode(getDs_Date() + " " + getDs_Hour(), "UTF-8"); //Hora de la peticion en madrid

            if (dateString.trim().isEmpty()) {
                return 0;
            }

            Date date = simpleDateFormat.parse(dateString);

            epochTime = date.getTime();
        } catch (ParseException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return epochTime;
    }

    protected ApiMacSha256 getApiMacSha256() {
        return apiMacSha256;
    }

    public abstract boolean isValid(String claveSecreta, String expectedSignature);

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Ds_Date:");
        sb.append(getDs_Date());
        sb.append(System.lineSeparator());
        sb.append("Ds_Hour:");
        sb.append(getDs_Hour());
        sb.append(System.lineSeparator());
        sb.append("Ds_Amount:");
        sb.append(getDs_Amount());
        sb.append(System.lineSeparator());
        sb.append("Ds_Currency:");
        sb.append(getDs_Currency());
        if (getDs_Currency() != null && !getDs_Currency().isEmpty())
            sb.append(" (" + Currency.findByNumeric(Integer.valueOf(getDs_Currency())) + ")");
        sb.append(System.lineSeparator());
        sb.append("Ds_Order:");
        sb.append(getDs_Order());
        sb.append(System.lineSeparator());
        sb.append("Ds_MerchantCode:");
        sb.append(getDs_MerchantCode());
        sb.append(System.lineSeparator());
        sb.append("Ds_MerchantData:");
        sb.append(getDs_MerchantData());
        sb.append(System.lineSeparator());
        sb.append("Ds_Terminal:");
        sb.append(getDs_Terminal());
        sb.append(System.lineSeparator());
        sb.append("Ds_Response:");
        sb.append(getDs_Response());
        sb.append(System.lineSeparator());
        sb.append("Ds_AuthorisationCode:");
        sb.append(getDs_AuthorisationCode());
        sb.append(System.lineSeparator());
        sb.append("Ds_TransactionType:");
        sb.append(getDs_TransactionType());
        sb.append(System.lineSeparator());
        sb.append("Ds_SecurePayment:");
        sb.append(getDs_SecurePayment());
        sb.append(System.lineSeparator());
        sb.append("Ds_ConsumerLanguage:");
        sb.append(getDs_ConsumerLanguage());
        sb.append(System.lineSeparator());
        sb.append("Ds_Signature:");
        sb.append(getDs_Signature());
        sb.append(System.lineSeparator());
        sb.append("Ds_Language:");
        //sb.append(getDs_Language());
        //sb.append(System.lineSeparator());
        sb.append("Ds_Card_Country:");
        sb.append(getDs_Card_Country());
        if (getDs_Card_Country() != null && !getDs_Card_Country().isEmpty())
            sb.append(" (" + Country.getByCode(Integer.valueOf(getDs_Card_Country())) +")");
        sb.append(System.lineSeparator());
        sb.append("Ds_Card_Type:");
        sb.append(getDs_Card_Type());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object instanceof Notification) {
            Notification notification = (Notification) object;

            if (notification.getDs_Order() != null && notification.getDs_Order().equals(this.getDs_Order())) {
                isEquals = true;
            }
        }
        return isEquals;
    }

    private static final Logger _log = Logger.getLogger(Notification.class.getName());
}

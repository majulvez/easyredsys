package com.miguelangeljulvez.easyredsys;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.Notification;

public class AppConfigImpl implements AppConfig {

    public String getMerchantCode() {
        return "061978060";
    }

    public String getTerminal() {
        return "001";
    }

    public String getSecretKey() {
        return "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
    }

    public boolean isTestMode() { return true;}

    @Override
    public void saveNotification(Notification notification) {
        System.out.println("NOTIFICACIÓN RECIBIDA: ");
        System.out.println(notification);
    }
}

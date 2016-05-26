package com.miguelangeljulvez.easyredsys;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.Notification;

public class AppConfigImpl implements AppConfig {

    static String getMerchantCode() {
        return "061978060";
    }

    static String getTerminal() {
        return "001";
    }

    static String getSecretKey() {
        return "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
    }

    static boolean isTestMode() { return true;}

    @Override
    public void saveNotification(Notification notification) {
        System.out.println("NOTIFICACIÓN RECIBIDA: ");
        System.out.println(notification);
    }
}

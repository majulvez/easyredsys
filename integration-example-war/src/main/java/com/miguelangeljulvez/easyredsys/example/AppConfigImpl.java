package com.miguelangeljulvez.easyredsys.example;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.Notification;

import java.util.logging.Logger;

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
        _log.info("OLEEEEEÉ - Si puedes leer esto es que todo ha ido bien");

        // Pon aquí lo que quieras hacer con la notificación recibida
    }


    private final static Logger _log = Logger.getLogger(AppConfigImpl.class.getName());
}

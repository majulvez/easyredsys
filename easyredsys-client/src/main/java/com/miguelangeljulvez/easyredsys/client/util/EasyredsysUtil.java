package com.miguelangeljulvez.easyredsys.client.util;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.Order;

public class EasyredsysUtil {

    private static final String proWebservice = "https://sis.redsys.es/sis/services/SerClsWSEntrada";
    private static final String proRedirect = "https://sis.redsys.es/sis/realizarPago";

    private static final String testWebservice = "https://sis-t.redsys.es:25443/sis/services/SerClsWSEntrada";
    private static final String testRedirect = "https://sis-t.redsys.es:25443/sis/realizarPago";

    private EasyredsysUtil(){}

    public static String getWebserviceURL(boolean testMode) {
        if (testMode) {
            return testWebservice;
        } else {
            return proWebservice;
        }
    }

    public static String getRedirectURL(boolean testMode) {
        if (testMode) {
            return testRedirect;
        } else {
            return proRedirect;
        }
    }

    public static String getSecretyKey(AppConfig appConfig) {

        String clave = "sq7HjrUOBfKmC576ILgskD5srU870gJ7";

        if (appConfig != null && !appConfig.isTestMode()) {
            clave = appConfig.getSecretKey(); //Devuelve la clave configurada por el usuario
        }

        return clave;
    }

    public static String getSecretyKey(Order order) {

        return getSecretyKey(order.getAppConfig());
    }
}

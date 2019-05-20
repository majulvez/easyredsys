package com.miguelangeljulvez.easyredsys.client.util;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.Order;

public class EasyredsysUtil {

    private static final String proWebservice = "https://sis.redsys.es/sis/services/SerClsWSEntrada";
    private static final String proRedirect = "https://sis.redsys.es/sis/realizarPago";

    private static final String testWebservice = "https://sis-t.redsys.es:25443/sis/services/SerClsWSEntrada";
    private static final String testRedirect = "https://sis-t.redsys.es:25443/sis/realizarPago";

    private static final String intWebservice = "https://sis-t.redsys.es:25443/sis/services/SerClsWSEntrada";
    private static final String intRedirect = "https://sis-i.redsys.es:25443/sis/realizarPago";

    private EasyredsysUtil(){}

    public static String getWebserviceURL(String environment) {
        if ("pro".equals(environment)) {
            return proWebservice;
        } else if ("int".equals(environment)) {
            return intWebservice;
        } else {
            return testWebservice;
        }
    }

    public static String getRedirectURL(String environment) {
        if ("pro".equals(environment)) {
            return proRedirect;
        } else if ("int".equals(environment)) {
            return intRedirect;
        } else {
            return testRedirect;
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

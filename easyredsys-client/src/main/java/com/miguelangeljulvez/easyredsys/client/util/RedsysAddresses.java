package com.miguelangeljulvez.easyredsys.client.util;

public class RedsysAddresses {

    public static final String proWebservice = "https://sis.redsys.es/sis/services/SerClsWSEntrada";
    public static final String proRedirect = "https://sis.redsys.es/sis/realizarPago";

    public static final String testWebservice = "https://sis-t.redsys.es:25443/sis/services/SerClsWSEntrada";
    public static final String testRedirect = "https://sis-t.redsys.es:25443/sis/realizarPago";

    public static final String intWebservice = "https://sis-t.redsys.es:25443/sis/services/SerClsWSEntrada";
    public static final String intRedirect = "https://sis-i.redsys.es:25443/sis/realizarPago";

    private RedsysAddresses(){}

    public static final String getWebserviceURL(String environment) {
        if ("pro".equals(environment)) {
            return proWebservice;
        } else if ("int".equals(environment)) {
            return intWebservice;
        } else {
            return testWebservice;
        }
    }

    public static final String getRedirectURL(String environment) {
        if ("pro".equals(environment)) {
            return proRedirect;
        } else if ("int".equals(environment)) {
            return intRedirect;
        } else {
            return testRedirect;
        }
    }
}

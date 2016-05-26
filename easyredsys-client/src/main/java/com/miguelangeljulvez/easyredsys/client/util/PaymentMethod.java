package com.miguelangeljulvez.easyredsys.client.util;


import java.util.HashMap;

public enum PaymentMethod {
    TARJETA("C"),
    TRANSFERENCIA("R"),
    DOMICILIACION("D"),
    TARJETAYIUPAY("T"),
    IUPAY("O");

    private String code;
    private static HashMap<String, PaymentMethod> codes = new HashMap<>();

    static {
        for (PaymentMethod cc : values()) {
            codes.put(cc.getCode(), cc);
        }
    }

    private PaymentMethod(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static String findByCode(String code) {
        return codes.get(code).name();
    }
}

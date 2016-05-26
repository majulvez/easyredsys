package com.miguelangeljulvez.easyredsys.client.util;


public enum Language {
    SPANISH("1"),
    ENGLISH("2"),
    CATALAN("3"),
    FRANCES("4"),
    ALEMAN("5"),
    HOLANDES("6"),
    ITALIANO("7"),
    SUECO("8"),
    PORTUGUES("9"),
    VALENCIANO("10"),
    POLACO("11"),
    GALLEGO("12"),
    EUSKERA("13"),
    ;

    private String code;

    private Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

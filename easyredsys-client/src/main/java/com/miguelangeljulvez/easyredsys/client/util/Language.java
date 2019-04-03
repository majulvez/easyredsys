package com.miguelangeljulvez.easyredsys.client.util;


public enum Language {
    SPANISH("001"),
    ENGLISH("002"),
    CATALAN("003"),
    FRENCH("004"),
    GERMAN("005"),
    DUTCH("006"),
    ITALIAN("007"),
    SWEDISH("008"),
    PORTUGUESE("009"),
    VALENCIAN("010"),
    POLISH("011"),
    GALICIAN("012"),
    BASQUE("013"),
    BULGARIAN("100"),
    CHINESE("156"),
    CROATIAN("191"),
    CZECH("203"),
    DANISH("208"),
    ESTONIAN("233"),
    FINNISH("246"),
    GREEK("300"),
    HUNGARIAN("348"),
    JAPANESE("392"),
    LATVIAN("428"),
    LITHUANIAN("440"),
    MALTESE("470"),
    ROMANIAN("642"),
    RUSSIAN("643"),
    SLOVENIAN("705"),
    SLOVAK("703"),
    TURKISH("792");

    private String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

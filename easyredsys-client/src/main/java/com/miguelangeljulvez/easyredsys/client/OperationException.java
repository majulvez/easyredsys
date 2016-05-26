package com.miguelangeljulvez.easyredsys.client;


public class OperationException extends Exception {
    private String code;
    private String message;

    public OperationException(String code, String message) {
        super(code);

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OperationException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

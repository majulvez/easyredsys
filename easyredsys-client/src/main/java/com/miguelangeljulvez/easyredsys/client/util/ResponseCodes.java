package com.miguelangeljulvez.easyredsys.client.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResponseCodes {

    private static final HashMap<String, String> errorResponseCodes = new HashMap<>();
    private static final HashMap<String, String> successResponseCodes = new HashMap<>();

    static {

        for (int i=0; i<100; i++) { // 0000 a 0099
            String number = String.valueOf(i);
            while (number.length()<4) {
                number = "0" + number;
            }
            successResponseCodes.put(number, number);
        }
        successResponseCodes.put("0900", "0900");
        successResponseCodes.put("400", "400");


        errorResponseCodes.put("101", "101");
        errorResponseCodes.put("102", "102");
        errorResponseCodes.put("106", "106");
        errorResponseCodes.put("125", "125");
        errorResponseCodes.put("129", "129");
        errorResponseCodes.put("180", "180");
        errorResponseCodes.put("184", "184");
        errorResponseCodes.put("190", "190");
        errorResponseCodes.put("191", "191");
        errorResponseCodes.put("202", "202");
        errorResponseCodes.put("904", "904");
        errorResponseCodes.put("909", "909");
        errorResponseCodes.put("912", "912");
        errorResponseCodes.put("913", "913");
        errorResponseCodes.put("944", "944");
        errorResponseCodes.put("950", "950");
        errorResponseCodes.put("0101", "101");
        errorResponseCodes.put("0102", "102");
        errorResponseCodes.put("0104", "104");
        errorResponseCodes.put("0106", "106");
        errorResponseCodes.put("0125", "125");
        errorResponseCodes.put("0129", "129");
        errorResponseCodes.put("0180", "180");
        errorResponseCodes.put("0184", "184");
        errorResponseCodes.put("0190", "190");
        errorResponseCodes.put("0191", "191");
        errorResponseCodes.put("0202", "202");
        errorResponseCodes.put("0904", "904");
        errorResponseCodes.put("0909", "909");
        errorResponseCodes.put("0913", "913");
        errorResponseCodes.put("0944", "944");
        errorResponseCodes.put("0950", "950");
        errorResponseCodes.put("0912", "912");
        errorResponseCodes.put("9912", "9912");
        errorResponseCodes.put("9064", "9064");
        errorResponseCodes.put("9078", "9078");
        errorResponseCodes.put("9093", "9093");
        errorResponseCodes.put("9094", "9094");
        errorResponseCodes.put("9104", "9104");
        errorResponseCodes.put("9218", "9218");
        errorResponseCodes.put("9253", "9253");
        errorResponseCodes.put("9256", "9256");
        errorResponseCodes.put("9257", "9257");
        errorResponseCodes.put("9261", "9261");
        errorResponseCodes.put("9913", "9913");
        errorResponseCodes.put("9914", "9914");
        errorResponseCodes.put("9915", "9915");
        errorResponseCodes.put("9928", "9928");
        errorResponseCodes.put("9929", "9929");
        errorResponseCodes.put("9997", "9997");
        errorResponseCodes.put("9998", "9998");
        errorResponseCodes.put("9999", "9999");
    }

    private ResponseCodes() {}

    public static String getErrorResponseMessage(String responseCode) {
        return getErrorResponseMessage(responseCode, Locale.getDefault());
    }

    public static String getErrorResponseMessage(String responseCode, Locale locale) {

        String message = errorResponseCodes.get(responseCode);

        if (message == null) {
            message = "not-response-code-found";
        }

        return ResourceBundle.getBundle("Language", locale).getString(message);
    }

    public static String getSuccessResponseMessage(String responseCode) {
        return getSuccessResponseMessage(responseCode, Locale.getDefault());
    }

    public static String getSuccessResponseMessage(String responseCode, Locale locale) {

        String message = successResponseCodes.get(responseCode);

        if (message == null) {
            message = "not-response-code-found";
        }

        return ResourceBundle.getBundle("Language", locale).getString(message);
    }

    public static boolean isErrorResponse(String responseCode) {
        return errorResponseCodes.containsKey(responseCode);
    }

    public static boolean isSuccessResponse(String responseCode) {
        return successResponseCodes.containsKey(responseCode);
    }
}

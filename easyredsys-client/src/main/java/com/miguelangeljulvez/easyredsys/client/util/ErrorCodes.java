package com.miguelangeljulvez.easyredsys.client.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorCodes  {

    private static final HashMap<String, String> errorCodes = new HashMap<>();

    static {
        errorCodes.put("SIS0007", "SIS0007");
        errorCodes.put("SIS0008", "SIS0008");
        errorCodes.put("SIS0009", "SIS0009");
        errorCodes.put("SIS0010", "SIS0010");
        errorCodes.put("SIS0011", "SIS0011");
        errorCodes.put("SIS0014", "SIS0014");
        errorCodes.put("SIS0015", "SIS0015");
        errorCodes.put("SIS0016", "SIS0016");
        errorCodes.put("SIS0017", "SIS0017");
        errorCodes.put("SIS0018", "SIS0018");
        errorCodes.put("SIS0019", "SIS0019");
        errorCodes.put("SIS0020", "SIS0020");
        errorCodes.put("SIS0021", "SIS0021");
        errorCodes.put("SIS0022", "SIS0022");
        errorCodes.put("SIS0023", "SIS0023");
        errorCodes.put("SIS0024", "SIS0024");
        errorCodes.put("SIS0025", "SIS0025");
        errorCodes.put("SIS0026", "SIS0026");
        errorCodes.put("SIS0027", "SIS0027");
        errorCodes.put("SIS0028", "SIS0028");
        errorCodes.put("SIS0030", "SIS0030");
        errorCodes.put("SIS0031", "SIS0031");
        errorCodes.put("SIS0033", "SIS0033");
        errorCodes.put("SIS0034", "SIS0034");
        errorCodes.put("SIS0037", "SIS0037");
        errorCodes.put("SIS0038", "SIS0038");
        errorCodes.put("SIS0040", "SIS0040");
        errorCodes.put("SIS0041", "SIS0041");
        errorCodes.put("SIS0042", "SIS0042");
        errorCodes.put("SIS0043", "SIS0043");
        errorCodes.put("SIS0046", "SIS0046");
        errorCodes.put("SIS0051", "SIS0051");
        errorCodes.put("SIS0054", "SIS0054");
        errorCodes.put("SIS0055", "SIS0055");
        errorCodes.put("SIS0056", "SIS0056");
        errorCodes.put("SIS0057", "SIS0057");
        errorCodes.put("SIS0058", "SIS0058");
        errorCodes.put("SIS0059", "SIS0059");
        errorCodes.put("SIS0060", "SIS0060");
        errorCodes.put("SIS0061", "SIS0061");
        errorCodes.put("SIS0062", "SIS0062");
        errorCodes.put("SIS0063", "SIS0063");
        errorCodes.put("SIS0064", "SIS0064");
        errorCodes.put("SIS0065", "SIS0065");
        errorCodes.put("SIS0066", "SIS0066");
        errorCodes.put("SIS0067", "SIS0067");
        errorCodes.put("SIS0068", "SIS0068");
        errorCodes.put("SIS0069", "SIS0069");
        errorCodes.put("SIS0070", "SIS0070");
        errorCodes.put("SIS0071", "SIS0071");
        errorCodes.put("SIS0072", "SIS0072");
        errorCodes.put("SIS0074", "SIS0074");
        errorCodes.put("SIS0075", "SIS0075");
        errorCodes.put("SIS0076", "SIS0076");
        errorCodes.put("SIS0077", "SIS0077");
        errorCodes.put("SIS0078", "SIS0078");
        errorCodes.put("SIS0079", "SIS0079");
        errorCodes.put("SIS0081", "SIS0081");
        errorCodes.put("SIS0084", "SIS0084");
        errorCodes.put("SIS0085", "SIS0085");
        errorCodes.put("SIS0086", "SIS0086");
        errorCodes.put("SIS0089", "SIS0089");
        errorCodes.put("SIS0092", "SIS0092");
        errorCodes.put("SIS0093", "SIS0093");
        errorCodes.put("SIS0094", "SIS0094");
        errorCodes.put("SIS0097", "SIS0097");
        errorCodes.put("SIS0098", "SIS0098");
        errorCodes.put("SIS0112", "SIS0112");
        errorCodes.put("SIS0113", "SIS0113");
        errorCodes.put("SIS0114", "SIS0114");
        errorCodes.put("SIS0115", "SIS0115");
        errorCodes.put("SIS0116", "SIS0116");
        errorCodes.put("SIS0117", "SIS0117");
        errorCodes.put("SIS0118", "SIS0118");
        errorCodes.put("SIS0119", "SIS0119");
        errorCodes.put("SIS0120", "SIS0120");
        errorCodes.put("SIS0121", "SIS0121");
        errorCodes.put("SIS0122", "SIS0122");
        errorCodes.put("SIS0123", "SIS0123");
        errorCodes.put("SIS0124", "SIS0124");
        errorCodes.put("SIS0132", "SIS0132");
        errorCodes.put("SIS0133", "SIS0133");
        errorCodes.put("SIS0139", "SIS0139");
        errorCodes.put("SIS0142", "SIS0142");
        errorCodes.put("SIS0197", "SIS0197");
        errorCodes.put("SIS0198", "SIS0198");
        errorCodes.put("SIS0199", "SIS0199");
        errorCodes.put("SIS0200", "SIS0200");
        errorCodes.put("SIS0214", "SIS0214");
        errorCodes.put("SIS0216", "SIS0216");
        errorCodes.put("SIS0217", "SIS0217");
        errorCodes.put("SIS0218", "SIS0218");
        errorCodes.put("SIS0219", "SIS0219");
        errorCodes.put("SIS0220", "SIS0220");
        errorCodes.put("SIS0221", "SIS0221");
        errorCodes.put("SIS0222", "SIS0222");
        errorCodes.put("SIS0223", "SIS0223");
        errorCodes.put("SIS0224", "SIS0224");
        errorCodes.put("SIS0225", "SIS0225");
        errorCodes.put("SIS0226", "SIS0226");
        errorCodes.put("SIS0227", "SIS0227");
        errorCodes.put("SIS0229", "SIS0229");
        errorCodes.put("SIS0252", "SIS0252");
        errorCodes.put("SIS0253", "SIS0253");
        errorCodes.put("SIS0254", "SIS0254");
        errorCodes.put("SIS0255", "SIS0255");
        errorCodes.put("SIS0256", "SIS0256");
        errorCodes.put("SIS0257", "SIS0257");
        errorCodes.put("SIS0258", "SIS0258");
        errorCodes.put("SIS0261", "SIS0261");
        errorCodes.put("SIS0270", "SIS0270");
        errorCodes.put("SIS0274", "SIS0274");
        errorCodes.put("SIS0429", "SIS0429");
        errorCodes.put("SIS0432", "SIS0432");
        errorCodes.put("SIS0433", "SIS0433");
        errorCodes.put("SIS0434", "SIS0434");
        errorCodes.put("SIS0435", "SIS0435");
        errorCodes.put("SIS0436", "SIS0436");
        errorCodes.put("SIS0437", "SIS0437");
        errorCodes.put("SIS0438", "SIS0438");
        errorCodes.put("SIS0439", "SIS0439");
    }

    private ErrorCodes() {}

    public static String getErrorMessage(String errorCode) {
        return getErrorMessage(errorCode, Locale.getDefault());
    }

    public static String getErrorMessage(String errorCode, Locale locale) {

        String message = errorCodes.get(errorCode);

        if (message == null) {
            message = "not-error-code-found";
        }

        return ResourceBundle.getBundle("Language", locale, new UTF8Control()).getString(message);
    }
}

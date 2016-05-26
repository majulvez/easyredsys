package com.miguelangeljulvez.easyredsys.server.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityUtil {

    private static List<String> ips = new ArrayList<>();

    static {
        String[] ipsStr = new String[3];
            ipsStr[0] = "127.0.0.1";
            ipsStr[1] = "195.76.9.187";
            ipsStr[2] = "195.76.9.222";

        ips = Arrays.asList(ipsStr);
    }

    public static boolean isValidIp(String ip) {

        return ips.contains(ip);
    }
}

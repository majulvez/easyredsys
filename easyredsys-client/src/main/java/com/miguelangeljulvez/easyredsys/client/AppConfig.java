package com.miguelangeljulvez.easyredsys.client;

import com.miguelangeljulvez.easyredsys.client.core.Notification;

public interface AppConfig {

    String getMerchantCode();
    String getTerminal();
    String getSecretKey();
    boolean isTestMode();

    void saveNotification(Notification notification);
}

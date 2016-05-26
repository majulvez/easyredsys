package com.miguelangeljulvez.easyredsys;


import com.miguelangeljulvez.easyredsys.client.OperationException;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESRequest;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESResponse;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCES;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCESRecurrence;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;
import com.miguelangeljulvez.easyredsys.client.ws.EasyRedsysService;
import org.junit.*;

import java.util.Random;

public class OrderNoCESSuscripcionTest {

    @Test
    @Ignore
    public void testCuotaInicial () throws OperationException {

        OrderNoCESRecurrence orderNoCESRecurrence = new OrderNoCESRecurrence.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.CUOTA_INICIAL)
                .order(String.valueOf(getRandomOrder()))
                .currency(Currency.EUR)
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .datefrecuency("30")
                .chargeexpirydate("2017-12-31")
                .transactiondate("2016-07-01")
                .sumtotal(10000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCESRecurrence)
                .build();

        String codigo;
        try {
            MessageOrderNoCESResponse response = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
            codigo = response.getCodigo();
        } catch (OperationException e) {
            e.printStackTrace();
            codigo = e.getCode();
        }
        Assert.assertEquals("0", codigo);
    }

    @Test
    @Ignore
    public void testCuotaSucesiva () throws OperationException {

        MessageOrderNoCESResponse responseTransaccionRecurrente = createOperation();

        OrderNoCESRecurrence orderNoCESRecurrence = new OrderNoCESRecurrence.Builder(responseTransaccionRecurrente.getOperationNoCES())
                .transactionType(TransactionType.CUOTA_SUCESIVA)
                .order(responseTransaccionRecurrente.getOperationNoCES().getDs_Order())
                .amount(1000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCESRecurrence)
                .build();

        String codigo;
        try {
            MessageOrderNoCESResponse response = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
            codigo = response.getCodigo();
        } catch (OperationException e) {
            e.printStackTrace();
            codigo = e.getCode();
        }
        Assert.assertEquals("0", codigo);
    }

    @Test
    @Ignore
    public void testCuotaInicialDiferido () throws OperationException {

        OrderNoCESRecurrence orderNoCESRecurrence = new OrderNoCESRecurrence.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.CUOTA_INICIAL_DIFERIDO)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .datefrecuency("30")
                .chargeexpirydate("2017-12-31")
                .transactiondate("2016-07-01")
                .sumtotal(10000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCESRecurrence)
                .build();

        String codigo;
        try {
            MessageOrderNoCESResponse response = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
            codigo = response.getCodigo();
        } catch (OperationException e) {
            e.printStackTrace();
            codigo = e.getCode();
        }
        Assert.assertEquals("0", codigo);
    }

    @Test
    @Ignore
    public void cuotaSucesivaDiferido () throws OperationException {

        MessageOrderNoCESResponse responseTransaccionRecurrente = createOperationDiferido();

        OrderNoCESRecurrence orderNoCESRecurrence = new OrderNoCESRecurrence.Builder(responseTransaccionRecurrente.getOperationNoCES())
                .transactionType(TransactionType.CUOTA_SUCESIVA)
                .order(responseTransaccionRecurrente.getOperationNoCES().getDs_Order())
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .datefrecuency("30")
                .chargeexpirydate("2017-12-31")
                .transactiondate("2016-07-01")
                .sumtotal(10000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCESRecurrence)
                .build();

        String codigo;
        try {
            MessageOrderNoCESResponse response = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
            codigo = response.getCodigo();
        } catch (OperationException e) {
            e.printStackTrace();
            codigo = e.getCode();
        }
        Assert.assertEquals("0", codigo);
    }

    private MessageOrderNoCESResponse createOperation() throws OperationException {
        OrderNoCESRecurrence orderNoCES = new OrderNoCESRecurrence.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.CUOTA_INICIAL)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .datefrecuency("1")
                .chargeexpirydate("2017-12-31")
                .transactiondate("2016-05-23")
                .sumtotal(10000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCES)
                .build();

        return EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
    }

    private MessageOrderNoCESResponse createOperationDiferido() throws OperationException {
        OrderNoCESRecurrence orderNoCES = new OrderNoCESRecurrence.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.CUOTA_INICIAL_DIFERIDO)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .datefrecuency("30")
                .chargeexpirydate("2017-12-31")
                .transactiondate("2016-07-01")
                .sumtotal(10000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCES)
                .build();

        return EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
    }

    private String getRandomOrder() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}

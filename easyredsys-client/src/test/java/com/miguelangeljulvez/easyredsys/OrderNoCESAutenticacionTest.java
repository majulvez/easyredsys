package com.miguelangeljulvez.easyredsys;


import com.miguelangeljulvez.easyredsys.client.OperationException;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESRequest;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESResponse;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCES;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCESConfirmation;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;
import com.miguelangeljulvez.easyredsys.client.ws.EasyRedsysService;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Random;

public class OrderNoCESAutenticacionTest {


    @Test
    @Ignore
    public void testAutenticacion () {

        OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.PRE_AUTENTICACION)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCES)
                .build();

        String codigo = null;
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
    public void testConfirmacionAutenticacion () throws OperationException {

        MessageOrderNoCESResponse responseAutorizacion = createOperation();

        OrderNoCESConfirmation orderNoCESConfirmation = new OrderNoCESConfirmation.Builder(responseAutorizacion.getOperationNoCES())
                .transactionType(TransactionType.CONFIRMACION_PRE_AUTENTICACION)
                .order(responseAutorizacion.getOperationNoCES().getDs_Order())
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCESConfirmation)
                .build();

        MessageOrderNoCESResponse response = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);

        Assert.assertEquals("0", response.getCodigo());
    }

    private MessageOrderNoCESResponse createOperation() throws OperationException {

        OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.PRE_AUTENTICACION)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
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

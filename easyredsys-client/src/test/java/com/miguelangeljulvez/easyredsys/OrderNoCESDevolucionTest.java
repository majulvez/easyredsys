package com.miguelangeljulvez.easyredsys;

import com.miguelangeljulvez.easyredsys.client.OperationException;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESRequest;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESResponse;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCES;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCESConfirmation;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;
import com.miguelangeljulvez.easyredsys.client.ws.EasyRedsysService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

public class OrderNoCESDevolucionTest {

    @Test
    @Ignore
    public void testDevolucion() throws OperationException {

        MessageOrderNoCESResponse responseAutorizacion = createOperation();

        OrderNoCESConfirmation orderNoCESConfirmation = new OrderNoCESConfirmation.Builder(responseAutorizacion.getOperationNoCES())
                .transactionType(TransactionType.DEVOLUCION_AUTOMATICA)
                .order(responseAutorizacion.getOperationNoCES().getDs_Order())
                .amount(1000)
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(orderNoCESConfirmation).build();

        String codigo;
        try {
            MessageOrderNoCESResponse responseDevolucion = EasyRedsysService.request(messageOrderNoCESRequest);
            codigo = responseDevolucion.getCodigo();
        } catch (OperationException e) {
            e.printStackTrace();
            codigo = e.getCode();
        }
        Assert.assertEquals("0", codigo);
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

    private MessageOrderNoCESResponse createOperation() throws OperationException {
        OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("4548812049400004")
                .cvv2("123")
                .expiryDate("2012")
                .build();

        MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(orderNoCES).build();

        return EasyRedsysService.request(messageOrderNoCESRequest);

    }
}

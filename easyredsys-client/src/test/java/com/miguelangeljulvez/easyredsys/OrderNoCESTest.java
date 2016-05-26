package com.miguelangeljulvez.easyredsys;


import com.miguelangeljulvez.easyredsys.client.OperationException;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESRequest;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESResponse;
import com.miguelangeljulvez.easyredsys.client.core.OrderNoCES;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;
import com.miguelangeljulvez.easyredsys.client.ws.EasyRedsysService;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Random;

public class OrderNoCESTest {

    @Before
    public void setUp() {

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    @Ignore
    public void testTarjetaValida () throws OperationException {

        OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
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
            MessageOrderNoCESResponse messageOrderNoCESResponse = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);

            codigo = messageOrderNoCESResponse.getCodigo();
        } catch (OperationException e) {
            e.printStackTrace();
            codigo = e.getCode();
        }

        Assert.assertEquals("0", codigo);
    }

    @Test
    @Ignore
    public void testTarjetaInvalida () {

        OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
                .currency(Currency.EUR)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .cardNumber("1111111111111117")
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

        Assert.assertEquals("SIS0093", codigo);
    }

    @Test
    @Ignore
    public void llamadaFaltanTodosParametros() {

        OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
                .order(getRandomOrder())
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

        Assert.assertEquals("SIS0019", codigo);
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

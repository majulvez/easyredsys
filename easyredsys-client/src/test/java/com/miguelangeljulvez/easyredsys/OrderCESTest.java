package com.miguelangeljulvez.easyredsys;

import com.miguelangeljulvez.easyredsys.client.core.MessageOrderCESRequest;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderCESResponse;
import com.miguelangeljulvez.easyredsys.client.core.OrderCES;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.Language;
import com.miguelangeljulvez.easyredsys.client.util.PaymentMethod;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

public class OrderCESTest {

    @Test
    public void validLocalOrderCES() {

        OrderCES orderCES = new OrderCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
                .currency(Currency.EUR)
                .consumerLanguage(Language.SPANISH)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .productDescription("Product description")
                .payMethods(PaymentMethod.TARJETA)
                .urlOk("http://localhost:8080/redsys/index.jsp?isSuccessful=TRUE")
                .urlKo("http://localhost:8080/redsys/index.jsp?isSuccessful=FALSE")
                .urlNotification("http://localhost:8080/redsys")
                .build();

        MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderCES)
                .build();

        String signatureVersion = "HMAC_SHA256_V1";
        String merchantParameter = "eyJEc19EYXRlIjoiMTYlMkYwNSUyRjIwMTYiLCJEc19Ib3VyIjoiMTElM0E0NSIsIkRzX1NlY3VyZVBheW1lbnQiOiIxIiwiRHNfQ2FyZF9Db3VudHJ5IjoiNzI0IiwiRHNfQW1vdW50IjoiMTAwMCIsIkRzX0N1cnJlbmN5IjoiOTc4IiwiRHNfT3JkZXIiOiJrbG9rbnp4bXpidnUiLCJEc19NZXJjaGFudENvZGUiOiI2MTk3ODA2MCIsIkRzX1Rlcm1pbmFsIjoiMDAxIiwiRHNfUmVzcG9uc2UiOiIwMDAwIiwiRHNfTWVyY2hhbnREYXRhIjoiIiwiRHNfVHJhbnNhY3Rpb25UeXBlIjoiMCIsIkRzX0NvbnN1bWVyTGFuZ3VhZ2UiOiIxIiwiRHNfQXV0aG9yaXNhdGlvbkNvZGUiOiIzNTcwMTQifQ==";
        String signature = "CfRWIlpdnuAYkWEaxk8OmUw3_8g0GUxFg-LpX2Zs57g=";

        MessageOrderCESResponse messageOrderCESResponse = new MessageOrderCESResponse(signatureVersion, signature, merchantParameter, AppConfigImpl.getSecretKey());

        Assert.assertTrue(messageOrderCESResponse.isValid());
    }

    @Test
    public void validOrderCES() {

        OrderCES orderCES = new OrderCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
                .currency(Currency.EUR)
                .consumerLanguage(Language.SPANISH)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .productDescription("Product description")
                .payMethods(PaymentMethod.TARJETA)
                .urlOk("http://localhost:8080/redsys/index.jsp?isSuccessful=TRUE")
                .urlKo("http://localhost:8080/redsys/index.jsp?isSuccessful=FALSE")
                .urlNotification("http://localhost:8080/redsys")
                .build();

        MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderCES)
                .build();

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(messageOrderCESRequest.getRedsysUrl());

        Form form = new Form();
        form.param("Ds_SignatureVersion", messageOrderCESRequest.getDs_SignatureVersion());
        form.param("Ds_MerchantParameters", messageOrderCESRequest.getDs_MerchantParameters());
        form.param("Ds_Signature", messageOrderCESRequest.getDs_Signature());

        Invocation.Builder request = target.request();

        Response response = request.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

        String entity = response.readEntity(String.class);

        Assert.assertThat(entity, CoreMatchers.not(CoreMatchers.containsString("SIS0")));
    }

    @Test
    public void notValidOrderCES() {

        OrderCES orderCES = new OrderCES.Builder(AppConfigImpl.class)
                .transactionType(TransactionType.AUTORIZACION)
                .currency(Currency.EUR)
                .consumerLanguage(Language.SPANISH)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000)
                .productDescription("Product description")
                .payMethods(PaymentMethod.TARJETA)
                .urlOk("http://localhost:8080/redsys/index.jsp?isSuccessful=TRUE")
                .urlKo("http://localhost:8080/redsys/index.jsp?isSuccessful=FALSE")
                .urlNotification("http://localhost:8080/redsys")
                .build();

        MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderCES)
                .build();

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(messageOrderCESRequest.getRedsysUrl());

        Form form = new Form();
        form.param("Ds_SignatureVersion", messageOrderCESRequest.getDs_SignatureVersion());
        form.param("Ds_MerchantParameters", messageOrderCESRequest.getDs_MerchantParameters());
        form.param("Ds_Signature", "invalidSignature");

        Invocation.Builder request = target.request();

        Response response = request.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

        String entity = response.readEntity(String.class);

        Assert.assertThat(entity, CoreMatchers.containsString("SIS0042"));
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

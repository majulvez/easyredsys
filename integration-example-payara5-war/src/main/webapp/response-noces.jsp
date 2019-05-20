<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.miguelangeljulvez.easyredsys.client.OperationException" %>
<%@ page import="com.miguelangeljulvez.easyredsys.client.core.MessageOrderNoCESRequest" %>
<%@ page import="com.miguelangeljulvez.easyredsys.client.core.OrderNoCES" %>
<%@ page import="com.miguelangeljulvez.easyredsys.client.util.Currency" %>
<%@ page import="com.miguelangeljulvez.easyredsys.client.util.TransactionType" %>
<%@ page import="com.miguelangeljulvez.easyredsys.client.ws.EasyRedsysService" %>
<%@ page import="com.miguelangeljulvez.easyredsys.example.AppConfigImpl" %>
<%@ page import="java.util.Random" %>

<!DOCTYPE html>
<html>
<head>
    <title>Easyredsys</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<%
    //Recogemos los parámetros del formulario
    String card = request.getParameter("card");
    String month = request.getParameter("expiration-month");
    String year = request.getParameter("expiration-year");
    String cvv = request.getParameter("cvv");

    OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
            .transactionType(TransactionType.AUTORIZACION)
            .currency(Currency.EUR)
            .order(String.valueOf(getRandomOrder()))
            .amount(1000)
            .cardNumber(card)
            .cvv2(cvv)
            .expiryDate(year+month)
            .build();

    MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(orderNoCES).build();
%>

<body class="text-center">
<h1>Easyredsys TEST</h1>
<p>&nbsp;</p>
<p>
<%
    try {
        EasyRedsysService.request(messageOrderNoCESRequest);
        out.write("Congratulations! Everything worked!");
    } catch (OperationException e) {
        out.write("Ups... something was wrong. Error code: " + e.getCode() + "-" + e.getMessage());
        e.printStackTrace();
    }
%>
</p>
</body>

</html>

<%!
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
%>
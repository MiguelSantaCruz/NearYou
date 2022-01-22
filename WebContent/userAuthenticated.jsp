<%@ page import="Model.Utilizador" %>
<%@ page import="Model.ISessao" %><%--
  Created by IntelliJ IDEA.
  User: miguel
  Date: 22/01/22
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ISessao sessao = (ISessao) request.getAttribute("Sessao");
    Utilizador utilizador = (Utilizador) request.getAttribute("Utilizador");
%>
<h1 style="color: #5e9ca0;">Bem vindo <span style="color: #2b2301;"><% out.println(utilizador.getUserName()); %></span> !</h1>
<h2 style="color: #2e6c80;">Near You</h2>
<input type="button" value="Voltar" onclick="history.back()">
</body>
</html>

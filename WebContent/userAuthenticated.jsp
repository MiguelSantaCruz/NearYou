<%@ page import="Model.Utilizador" %>
<%@ page import="Model.ISessao" %>
<%@ page import="DataLayer.IBDHandler" %>
<%@ page import="java.util.Map" %>
<%@ page import="Model.PontoDeInteresse" %><%--
  Created by IntelliJ IDEA.
  User: miguel
  Date: 22/01/22
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Página inicial</title>
</head>
<body>
<%
    ISessao sessao = (ISessao) request.getAttribute("Sessao");
    Utilizador utilizador = (Utilizador) request.getAttribute("Utilizador");
    IBDHandler ibdHandler = (IBDHandler) request.getAttribute("BD");
%>
<!DOCTYPE html>
<html lang="en">
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif;}
    .w3-sidebar {
        z-index: 3;
        width: 250px;
        top: 43px;
        bottom: 0;
        height: inherit;
    }
    .poi {
        float: left;
        padding: 20px;
        background: yellow;
        border-style: solid
    }

    .imgPOI{
        float: left;
        padding: 20px;
    }
</style>
<body style="background-color:orange;">
    <div class=" w3-container">

<div class="w3-row w3-padding-64">
    <div class="w3-twothird w3-container">
        <a href="welcome">
            <img src="nearyou_logo.png" alt="Avatar" class="avatar" width="100">
        </a>

        <h1 class="w3-theme-black">NearYou</h1>
    </div>
    <div class="w3-third w3-container">
        <p class="w3-padding-large w3-padding-32 w3-center">Olá <% out.print(utilizador.getUserName());%><img src="user-circle.png" alt="Avatar" class="avatar" width="100">
        </p>
    </div>
</div>
<div class="w3-row w3-padding-64">

    <form action="search" method="post">
        <div class="container">
            <label for="search"><p>Procurar</p></label>
            <input id="search" type="text" placeholder="Insira o termo de procura" name="search" required>
            <button type="submit">Procurar</button>
        </div>
        <div class="container" style="background-color:#f1f1f1">
        </div>
    </form>
    <div class="w3-twothird w3-container">
        <h1 class="w3-theme-black">Pontos de interesse próximos</h1>
    </div>


        <% for (Map.Entry<String, PontoDeInteresse> entry : ibdHandler.getPontosDeInteresse().entrySet()) {
            out.print("<div class=\"poi\" style=\"width: 100%\">");
            if(entry.getValue().getPathFoto() == null){
                out.print(" <p><img class=\"imgPOI\" src=\"nophoto.png\"");
                out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                out.print(entry.getValue().getIdPontoInteresse() + "\" >");
            }else{
                out.print(" <p><img class=\"imgPOI\" src=\"");
                out.print(entry.getValue().getPathFoto() + "\"");
                out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                out.print(entry.getValue().getIdPontoInteresse() + "\" >");
            }
             out.write("</h1><h1>" + entry.getValue().getName() + "</a></h1><p>" +
             entry.getValue().getEndereco()+ "</p><p>" + entry.getValue().getDescricao()+ "</p><p><b> Classificação</b>: " + entry.getValue().getClassificacaoMedia() + "</p>");
            out.print("</div>");
        }%>

</div>
    </div>
</body>
</html>

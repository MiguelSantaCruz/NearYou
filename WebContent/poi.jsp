<%@ page import="Model.Utilizador" %>
<%@ page import="Model.ISessao" %>
<%@ page import="DataLayer.IBDHandler" %>
<%@ page import="java.util.Map" %>
<%@ page import="Model.PontoDeInteresse" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Review" %><%--
  Created by IntelliJ IDEA.
  User: miguel
  Date: 22/01/22
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ponto de Interesse</title>
</head>
<body>
<%
    ISessao sessao = (ISessao) request.getAttribute("Sessao");
    Utilizador utilizador = (Utilizador) request.getAttribute("Utilizador");
    IBDHandler ibdHandler = (IBDHandler) request.getAttribute("BD");
    List<Review> reviewList = (List<Review>) request.getAttribute("reviews");

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

    <div class="poi" style="width: 100%">
        <%
            PontoDeInteresse pontoDeInteresse = (PontoDeInteresse) request.getAttribute("poi");

             if(pontoDeInteresse.getPathFoto() == null){
                 out.print("<p>Missing photo</p>");
             }else{
                 out.print(" <p><img class=\"imgPOI\" src=\"");
                 out.print(pontoDeInteresse.getPathFoto() + "\"");
                 out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                 out.print(pontoDeInteresse.getIdPontoInteresse() + "\" >");
             }

             out.print(pontoDeInteresse.getName() + "</a></h1><p>");
             out.print(pontoDeInteresse.getEndereco()+ "</p><p>" );
             out.print(pontoDeInteresse.getDescricao()+ "</p><p><b> Classificação</b>: " + pontoDeInteresse.getClassificacaoMedia() + "</p>");
        %>
    </div>
    <h1>Reviews:</h1>
    <%
        out.print("<a href=\"/NearYou/Review?" + pontoDeInteresse.getIdPontoInteresse() + "\">Efetuar review</a>");
    %>


        <%
            if(reviewList != null && reviewList.size() != 0){


                for (Review review: reviewList) {
                    out.print("<div class=\"poi\" style=\"width:100%\" href=\"#\">");
                    out.print("<h1><b>" + ibdHandler.getUtilizador(review.getUserID()).getUserName() + "</b></h1>");
                    out.print("<p>" + review.getReviewEscrita() + "</p>");
                    out.print("<b>Gostos:</b>");
                    out.print(review.getNrGostos());
                    out.print("<p>" + review.getDate().getDayOfMonth() + "/" + review.getDate().getMonthValue() + "/" + review.getDate().getYear() + " às " + review.getDate().getHour() +":"+ review.getDate().getMinute() + "</p>");
                    out.print("<form action=\"like?" + review.getReviewID()+"?" + pontoDeInteresse.getIdPontoInteresse() + "?\" method=\"post\">\n" +
                            "                <div class=\"container\">\n" +
                            "                    <button type=\"summit\">Gostar</button>\n" +
                            "                </div>\n" +
                            "            </form>");
                    out.print("</div>");
                }
            } else{
                out.print("<h><b>Sem reviews</b></h>");
            }
        %>
    </div>
</div>
    </div>
</body>
</html>

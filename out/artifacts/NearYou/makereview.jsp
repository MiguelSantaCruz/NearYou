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
    <title>Ponto de Interesse</title>
</head>
<body>
<%
    ISessao sessao = (ISessao) request.getAttribute("Sessao");
    Utilizador utilizador = (Utilizador) request.getAttribute("Utilizador");
    IBDHandler ibdHandler = (IBDHandler) request.getAttribute("BD");
    int avaliacao = 0;
%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="mystylesheets.css">
<title>Efetuar Review</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<body style="background-color:orange;">
<div class=" w3-container" style="background-color: darkorange">
<div class="topnav" style="background-color: darkorange">
    <a class="active" href="loginAction" style="background-color: darkorange">
            <img src="nearyou_logo.png" alt="NearYou" width="100 height= 100">

            <p style="color: black;background-color: darkorange;font-size: 24px">Near You</p>
        </a>
        <h2 style="color: black;float:right">
            <a style="color: black;float: inside;font-size: 30px" href="loginAction">
                Olá
                <% out.print(utilizador.getUserName()); %>!</a>
            <img src="user-circle.png" alt="Avatar" class="avatar" width="100">
        </h2>
    </div>
    <div>
        <a style="color: black;float:right;font-size: 15px" href="endSession">Terminar sessão</a>
    </div>
</div>
<div class="w3-row w3-padding-64">

    <form action="search" method="post">
        <div class="container">
            <label for="search"><p>Procurar</p></label>
            <input id="search" type="text" size="100" placeholder="Insira o termo de procura" name="search" required>
            <button type="submit">Procurar</button>
        </div>
        <div class="container" style="background-color:#f1f1f1">
        </div>
    </form>

    <div class="poi" style="width: 100%">
        <%
            PontoDeInteresse pontoDeInteresse = (PontoDeInteresse) request.getAttribute("poi");
            if(pontoDeInteresse == null){
                out.print("<h1> Não encontrado: 404</h1>");
            }else{
                if(pontoDeInteresse.getPathFoto() == null){
                    out.print(" <p><img class=\"imgPOI\" src=\"nophoto.png\"");
                    out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                    out.print(pontoDeInteresse.getIdPontoInteresse() + "\" >");
                }else{
                    out.print(" <p><img class=\"imgPOI\" src=\"");
                    out.print(pontoDeInteresse.getPathFoto() + "\"");
                    out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                    out.print(pontoDeInteresse.getIdPontoInteresse() + "\" >");
                }

                out.print(pontoDeInteresse.getName() + "</a></h1><p>");
                out.print(pontoDeInteresse.getEndereco()+ "</p><p>" );
                if(pontoDeInteresse.getClassificacaoMedia() == 0){
                    out.print("<p><b> Sem classificações</b></p>");
                }else{
                    out.print("<p><b> Classificação</b>: " + pontoDeInteresse.getClassificacaoMedia() + "</p>");
                }
            }


        %>
    </div>
    <div class="poi" style="width:100%" href="#">

        <%
            out.print("<form action=\"publish?" + pontoDeInteresse.getIdPontoInteresse() + "\" method=\"post\">");
        %>
            <form class="container">
                <p>
                <textarea id="textReview" cols="40" rows="5" placeholder="Insira a sua review" name="review" required style="height:200px;width:100%;font-size:14pt;"></textarea>
                </p>
                <div class="stars">
                        <input class="star star-5" id="star-5" type="radio" name="star" value="5"/>
                        <label class="star star-5" for="star-5"></label>
                        <input class="star star-4" id="star-4" type="radio" name="star" value="4"/>
                        <label class="star star-4" for="star-4"></label>
                        <input class="star star-3" id="star-3" type="radio" name="star" value="3"/>
                        <label class="star star-3" for="star-3"></label>
                        <input class="star star-2" id="star-2" type="radio" name="star" value="2"/>
                        <label class="star star-2" for="star-2"></label>
                        <input class="star star-1" id="star-1" type="radio" name="star" value="1" required/>
                        <label class="star star-1" for="star-1"></label>
                </div>
                <button type="summit">Publicar</button>
        </form>
</div>
</div>
    </div>
</body>
</html>

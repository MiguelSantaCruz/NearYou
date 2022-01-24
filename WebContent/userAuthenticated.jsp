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
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"/>

<head>
    <title>Página inicial</title>
</head>
<body>
<%
    ISessao sessao = (ISessao) request.getAttribute("Sessao");
    Utilizador utilizador = (Utilizador) request.getAttribute("Utilizador");
    IBDHandler ibdHandler = (IBDHandler) request.getAttribute("BD");
    String searchKey = (String) request.getAttribute("searchKey");
    Map<String,PontoDeInteresse> pois = (Map<String, PontoDeInteresse>) request.getAttribute("pois");
%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="mystylesheets.css">
<title>NearYou</title>
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
                <a style="color: black;float: inside;font-size: 30px" href="profile">
                    Olá
                    <% out.print(utilizador.getUserName()); %>!</a>
                <img src="user-circle.png" alt="Avatar" class="avatar" width="100">
            </h2>
        </div>
        <div>
            <a style="color: black;float:right;font-size: 15px" href="endSession">Terminar sessão</a>
        </div>
        </div>
    </div>


    <form action="search" method="post">
        <div class="container">
            <label for="search"><p style="font-size: 20px" >Procurar</p></label>
            <input id="search" size="100" type="text" placeholder="Insira o termo de procura" name="search" required>
            <button type="submit">Procurar</button>
        </div>
        <% if(searchKey != null) {
            System.out.println("termo de procura: " + searchKey);
            out.print("<h1> Termo de procura: " + searchKey + "</h1>");
        }
        %>
    </form>

    <div class="w3-row w3-border">
            <div class="w3-twothird w3-container" style="color: black">
                <%
                    if(pois == null){
                        out.print("<div class=\"poi\" style=\"width: 100%\">");
                        out.print("<h1>Nenhum ponto de interesse correspondente</h1>");
                        out.print("</div>");
                    } else {
                        for (Map.Entry<String, PontoDeInteresse> entry : pois.entrySet()) {
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
                                    entry.getValue().getEndereco()+ "</p><p><b> Classificação</b>: " + entry.getValue().getClassificacaoMedia() + "</p>");
                            out.print("<form action=\"save?"+ entry.getValue().getIdPontoInteresse() + "?" + utilizador.getUserID() + "?\" method=\"post\" style=\"float: left\">\n" +
                                    "<button type=\"summit\">Guardar</button>\n" +
                                    " </form>");
                            out.print("</div>");
                        }
                    }
                    %>
            </div>
            <div class="w3-third w3-container">
                <h2>Tags</h2>
                <p><a href="search?cafe">Cafés</a></p>
                <p><a href="search?restaurante">Restaurantes</a></p>
                <p><a href="search?museus">Museus</a></p>
                <p><a href="search?parque">Parque</a></p>
                <p><a href="search?monumentos">Monumentos</a></p>
                <p><a href="search?piscina">Piscinas</a></p>
                <p><a href="search?hospital">Hospital</a></p>
                <p><a href="search?igrejas">Igrejas</a></p>
                <p><a href="search?praia">Praias</a></p>
            </div>
    </div>
</body>
</html>

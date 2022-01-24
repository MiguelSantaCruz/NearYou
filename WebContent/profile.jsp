<%@ page import="DataLayer.IBDHandler" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.*" %>
<%@ page import="DataBase.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="mystylesheets.css">
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
    int userID = -1;
    try{
        userID = Integer.valueOf((String) request.getAttribute("userID"));
    }catch (Exception e){
    }

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

<body style="background-color:orange;">
<div class=" w3-container" style="background-color: darkorange">
    <div class="topnav" style="background-color: darkorange">
        <a class="active" href="loginAction" style="background-color: darkorange">
            <img src="nearyou_logo.png" alt="NearYou" width="100 height= 100">

                <p style="color: black;background-color: darkorange;font-size: 24px">Near You</p>
            </a>
            <h2 style="color: black;float:right">
                <a style="color: black;float: inside;font-size: 30px" href="profile?<%out.print(sessao.getIdUser());%>">
                    Olá
                <% out.print(utilizador.getUserName()); %>!</a>
                <img src="user-circle.png" alt="Avatar" class="avatar" width="100">
            </h2>
        </div>
        <div>
            <a style="color: black;float:right;font-size: 15px" href="endSession">Terminar sessão</a>
        </div>
    </div>

    <%
        if(userID == Integer.valueOf(sessao.getIdUser())){
            out.print("<div class=\"container\">\n" +
                    "        <h1><b>Localização: </b>");
            APIHandler apiHandler = new APIHandler();
            out.print("<h>" + apiHandler.localizautilizador() + "</h></h1></div>");
        }


    %>

    <form action="search" method="post">
        <div class="container">
            <label for="search"><p style="font-size: 20px" >Procurar</p></label>
            <input id="search" type="text" size="100" placeholder="Insira o termo de procura" name="search" required>
            <button type="submit">Procurar</button>
        </div>
        <% if(searchKey != null) {
            System.out.println("termo de procura: " + searchKey);
            out.print("<h1> Termo de procura: " + searchKey + "</h1>");
        }
        %>
    </form>

<div>
    <%
        if(userID != Integer.valueOf(sessao.getIdUser())){
            if(UtilizadorDAO.exists_by_id(userID)){
                out.print("<h1><b>Utilizador: </b>" + UtilizadorDAO.get_by_id(userID).getUserName() + "</h1>");
                if(utilizador.getPermissao() != 1){
                    out.print("<form style=\"float: left\" action=\"blockUser?"+ userID + "\" method=\"post\">\n" +
                            "                <div class=\"container\">\n" +
                            "                    <button type=\"summit\">Bloquear Utilizador</button>\n" +
                            "                </div>\n" +
                            "            </form>");
                }
            }
        }
    %>

</div>

    <div class="w3-row w3-border">
        <h1> Reviews: </h1>
            <div class="w3-twothird w3-container" style="color: black">
                <%
                    if(UtilizadorDAO.exists_by_id(userID)) {
                        List<Review> reviewList = ReviewDAO.get_all_reviews_user(userID);
                        if (reviewList == null || reviewList.isEmpty()) {
                            out.print("<div class=\"poi\" style=\"width: 100%\">");
                            out.print("<h1>Sem reviews efetuadas</h1>");
                            out.print("</div>");
                        } else {
                            for (Review review : reviewList) {
                                out.print("<div class=\"poi\" style=\"width:100%\" href=\"#\">");
                                PontoDeInteresse pontoDeInteresse = ibdHandler.getPontoInteresse(Integer.valueOf(review.getPiID()));
                                if (pontoDeInteresse.getPathFoto() == null) {
                                    out.print(" <p><img class=\"imgPOI\" src=\"nophoto.png\"");
                                    out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                                    out.print(pontoDeInteresse.getIdPontoInteresse() + "\" >");
                                } else {
                                    out.print(" <p><img class=\"imgPOI\" src=\"");
                                    out.print(pontoDeInteresse.getPathFoto() + "\"");
                                    out.print("alt=\"Photo\" style=\"width:170px;height:170px;\"></p><h1><a href=\"openPOI?");
                                    out.print(pontoDeInteresse.getIdPontoInteresse() + "\" >");
                                }
                                out.print(pontoDeInteresse.getName() + "</a></h1><p>");
                                out.print(pontoDeInteresse.getEndereco() + "</p>");
                                out.print("<p><b>Review:</b></p>");
                                out.print("<p>" + review.getReviewEscrita() + "</p>");
                                out.print("<b>Gostos: </b>");
                                out.print(LikedReviewDAO.getLikesNumber(Integer.valueOf(review.getReviewID())));
                                out.print("<p> <b> Avaliação: </b>");
                                for (int i = 0; i < review.getClassificacao(); i++) {
                                    out.print(" ⭐ ");
                                }
                                out.print("</p>");
                                if(Integer.valueOf(review.getUserID()) == utilizador.getUserID()){
                                    out.print("<form style=\"float: right\" action=\"deleteReview?" + review.getReviewID()+"?" + pontoDeInteresse.getIdPontoInteresse() + "?\" method=\"post\">\n" +
                                            "                <div class=\"container\">\n" +
                                            "                    <button type=\"summit\">Apagar</button>\n" +
                                            "                </div>\n" +
                                            "            </form>");
                                } else {
                                    out.print("<form style=\"float: right\" action=\"report?" + review.getReviewID()+"?" + utilizador.getUserID() + "\" method=\"post\">\n" +
                                            "                <div class=\"container\">\n" +
                                            "                    <button type=\"summit\">Reportar</button>\n" +
                                            "                </div>\n" +
                                            "            </form>");
                                }
                                out.print("</div>");
                            }
                        }
                    } else {
                        out.print("<div class=\"poi\" style=\"width: 100%\">");
                        out.print("<h1>Utilizador inexistente</h1>");
                        out.print("</div>");
                    }
                    %>
            </div>
            <div class="w3-third w3-container">
                <h2>Locais de interesse guardados:</h2>
                <%
                    if(UtilizadorDAO.exists_by_id(userID)) {
                        List<PontoDeInteresse> pontoDeInteresseList = UtilizadorPiDAO.get_All_Pis_Users(userID);
                        if (pontoDeInteresseList == null || pontoDeInteresseList.isEmpty()) {
                            out.print("<div class=\"poi\" style=\"width: 100%;\">");
                            out.print("<p>Sem pontos de interesse guardados</p>");
                            out.print("</div>");
                        }
                        for (PontoDeInteresse pontoDeInteresse : pontoDeInteresseList) {
                            out.print("<div class=\"poi\" style=\"width: 100%;\">");
                            out.write("<p><a href=\"openPOI?" + pontoDeInteresse.getIdPontoInteresse() + "\">" + pontoDeInteresse.getName() + "</a>\n");
                            if(userID == Integer.valueOf(sessao.getIdUser())){
                                out.print("<form style=\"display: inline-block;\" action=\"remove?" + pontoDeInteresse.getIdPontoInteresse() + "?" + utilizador.getUserID() + "?\" method=\"post\" style=\"float: left\">\n" +
                                        "<button action=\"remove?\"+ pontoDeInteresse.getIdPontoInteresse() + \"?\" + utilizador.getUserID() + \"?\" method=\"post\" type=\"summit\">Remover</button>\n" +
                                        " </form></p>");
                                out.print("</div>");
                            }


                        }
                    } else{
                        out.print("<div class=\"poi\" style=\"width: 100%\">");
                        out.print("<h1>Sem locais</h1>");
                        out.print("</div>");
                    }
                %>
                <input type="hidden" name="viewid" value="profile.jsp">
            </div>
    </div>
</body>
</html>

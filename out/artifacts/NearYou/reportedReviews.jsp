<%@ page import="DataLayer.IBDHandler" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBase.ReviewDAO" %>
<%@ page import="DataBase.LikedReviewDAO" %>
<%@ page import="Model.*" %>
<%@ page import="DataBase.ReportedReviewDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="mystylesheets.css">

<head>
    <title>Ponto de Interesse</title>
</head>
<body>
<%
    ISessao sessao = (ISessao) request.getAttribute("Sessao");
    Utilizador utilizador = (Utilizador) request.getAttribute("Utilizador");
    IBDHandler ibdHandler = (IBDHandler) request.getAttribute("BD");
    List<ReportedReview> reviewList = (List<ReportedReview>) request.getAttribute("reviews");

%>
<!DOCTYPE html>
<html lang="en">
<title>Ver Detalhes</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body style="background-color:orange;">
<input type="hidden" name="viewid" value="poi.jsp">
<div class=" w3-container" style="background-color: darkorange">
    <div class="topnav" style="background-color: darkorange">
    <a class="active" href="loginAction" style="background-color: darkorange">
            <img src="nearyou_logo.png" alt="NearYou" width="100 height= 100">

            <p style="color: black;background-color: darkorange;font-size: 24px">Near You</p>
        </a>
        <h2 style="color: black;float:right">
            <a style="color: black;float: inside;font-size: 30px" href="profile?<% out.print(utilizador.getUserID()); %>">
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
            <input id="search" size="100" type="text" placeholder="Insira o termo de procura" name="search" required>
            <button type="submit">Procurar</button>
        </div>
    </form>
    <h1>Reviews reportadas:</h1>
        <%
            if(reviewList != null && reviewList.size() != 0){
                for (ReportedReview reportedReview: reviewList) {
                    Review review = ReviewDAO.get_by_id(reportedReview.getReviewID());
                    if(review != null) {
                        out.print("<div class=\"poi\" style=\"width:100%\" href=\"#\">");
                        out.print("<h1><b><a href=\"profile?" + review.getUserID() + "\">" + ibdHandler.getUtilizador(Integer.valueOf(review.getUserID())).getUserName() + "</a></b></h1>");
                        out.print("<p><b><a href=\"openPOI?" + review.getPiID() + "\"> Ver Ponto de Interesse </a></b></p>");
                        out.print("<p>" + review.getReviewEscrita() + "</p>");
                        out.print("<b>Gostos: </b>");
                        out.print(LikedReviewDAO.getLikesNumber(Integer.valueOf(review.getReviewID())));
                        out.print("<p>" + review.getDate().getDayOfMonth() + "/" + review.getDate().getMonthValue() + "/" + review.getDate().getYear() + " às " + review.getDate().getHour() + ":" + review.getDate().getMinute() + "</p>");
                        out.print("<p> <b> Avaliação: </b>");
                        for (int i = 0; i < review.getClassificacao(); i++) {
                            out.print(" ⭐ ");
                        }
                        out.print("</p>");
                        System.out.println("Review: " + review);
                        out.print("<form style=\"float: right\" action=\"deleteReview?" + review.getReviewID() + "?" + review.getPiID() + "?\" method=\"post\">\n" +
                                "                <div class=\"container\">\n" +
                                "                    <button type=\"summit\">Apagar</button>\n" +
                                "                </div>\n" +
                                "            </form>");
                        out.print("</div>");
                    }
                }
            } else{
                out.print("<p><h><b>Sem reviews</b></h></p>");
            }
        %>
</div>
</div>
    </div>
</body>
</html>

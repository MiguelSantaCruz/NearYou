package UI;

import Model.PontoDeInteresse;
import Model.Review;
import Model.Utilizador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(name = "like", value = "/like")
public class LikeReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Sessao", NearYouMain.modelo.getSessaoAtual());
        Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(), NearYouMain.ibdHandler);
        request.setAttribute("Utilizador",utilizador);
        request.setAttribute("BD", NearYouMain.ibdHandler);
        RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
        view.forward(request, response);
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryString = request.getQueryString();
        System.out.println("query; " + queryString);
        StringTokenizer st = new StringTokenizer(queryString,"?");
        String reviewID = st.nextToken();
        System.out.println("id review stk: " + reviewID);
        String idPOIrequest = st.nextToken();
        int idPOI = Integer.valueOf(idPOIrequest);
        System.out.println("id poi stk:" + idPOI);
        PontoDeInteresse pontoDeInteresse = NearYouMain.ibdHandler.getPontoInteresse(idPOI);
        List<Review> reviewsList= NearYouMain.ibdHandler.getPontoInteresseReviews(idPOI);
        if(NearYouMain.modelo.getSessaoAtual() == null){
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }else{
                NearYouMain.ibdHandler.alteraGosto(Integer.valueOf(NearYouMain.modelo.getSessaoAtual().getIdUser()),Integer.valueOf(reviewID));
                request.setAttribute("poi", pontoDeInteresse);
                request.setAttribute("reviews",reviewsList);
                request.setAttribute("Sessao", NearYouMain.modelo.getSessaoAtual());
                Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(), NearYouMain.ibdHandler);
                request.setAttribute("Utilizador",utilizador);
                request.setAttribute("BD", NearYouMain.ibdHandler);
            RequestDispatcher view = request.getRequestDispatcher("poi.jsp");
            view.forward(request, response);
        }
    }
}

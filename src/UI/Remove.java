package UI;

import DataBase.UtilizadorPiDAO;
import Model.APIHandler;
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
import java.util.Map;
import java.util.StringTokenizer;

@WebServlet(name = "remove", value = "/remove")
public class Remove extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        StringTokenizer st = new StringTokenizer(query,"?");
        String piID = st.nextToken();
        String userID = st.nextToken();
        UtilizadorPiDAO.delete(Integer.parseInt(piID));
        response.setContentType("text/html");
        if(NearYouMain.modelo.getSessaoAtual() != null){
            request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            request.setAttribute("BD",NearYouMain.ibdHandler);
            APIHandler apiHandler = new APIHandler();
            Map<String, PontoDeInteresse> pois = apiHandler.getPontosDeInteresse("cafe");
            if(pois != null) {
                for (Map.Entry<String, PontoDeInteresse> entry : pois.entrySet()) {
                    int id = NearYouMain.ibdHandler.addPoi(entry.getValue());
                    entry.getValue().setIdPontoInteresse(String.valueOf(id));
                }
            }
            request.setAttribute("pois",pois);
            RequestDispatcher view = request.getRequestDispatcher("profile.jsp");
            view.forward(request, response);
        }else{
            RequestDispatcher view = request.getRequestDispatcher("login");
            view.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPOIrequest = request.getQueryString();
        int idPOI = Integer.valueOf(idPOIrequest);
        PontoDeInteresse pontoDeInteresse = NearYouMain.ibdHandler.getPontoInteresse(idPOI);
        List<Review> reviewsList= NearYouMain.ibdHandler.getPontoInteresseReviews(idPOI);
        if(NearYouMain.modelo.getSessaoAtual() == null){
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }else{
            request.setAttribute("poi", pontoDeInteresse);
            request.setAttribute("reviews",reviewsList);
            request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            request.setAttribute("BD",NearYouMain.ibdHandler);
            RequestDispatcher view = request.getRequestDispatcher("userAutheticated.jsp");
            view.forward(request, response);
        }
    }
}

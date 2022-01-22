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

@WebServlet(name = "reviewPOI", value = "/Review")
public class ReviewPOI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPOI = request.getQueryString();
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
            RequestDispatcher view = request.getRequestDispatcher("makereview.jsp");
            view.forward(request, response);
        }


    }
}

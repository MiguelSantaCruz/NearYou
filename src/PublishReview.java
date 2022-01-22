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

@WebServlet(name = "publish", value = "/publish")
public class PublishReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
        Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
        request.setAttribute("Utilizador",utilizador);
        request.setAttribute("BD",NearYouMain.ibdHandler);
        RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
        view.forward(request, response);
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPOI = request.getQueryString();
        System.out.println("Identificador do ponto: " + idPOI);
        String review = request.getParameter("review");
        PontoDeInteresse pontoDeInteresse = NearYouMain.ibdHandler.getPontoInteresse(idPOI);
        List<Review> reviewsList= NearYouMain.ibdHandler.getPontoInteresseReviews(idPOI);
        if(NearYouMain.modelo.getSessaoAtual() == null){
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }else{
            if(NearYouMain.ibdHandler.verificaAvaliou(idPOI,NearYouMain.modelo.getSessaoAtual().getIdUser())){
                request.setAttribute("poi", pontoDeInteresse);
                request.setAttribute("reviews",reviewsList);
                request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
                Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
                request.setAttribute("Utilizador",utilizador);
                request.setAttribute("BD",NearYouMain.ibdHandler);
                RequestDispatcher view = request.getRequestDispatcher("poi.jsp");
                view.forward(request, response);
            }else{
                NearYouMain.ibdHandler.addReview(review,"0",idPOI,NearYouMain.modelo.getSessaoAtual().getIdUser());
                reviewsList= NearYouMain.ibdHandler.getPontoInteresseReviews(idPOI);
                request.setAttribute("poi", pontoDeInteresse);
                request.setAttribute("reviews",reviewsList);
                request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
                Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
                request.setAttribute("Utilizador",utilizador);
                request.setAttribute("BD",NearYouMain.ibdHandler);
                RequestDispatcher view = request.getRequestDispatcher("poi.jsp");
                view.forward(request, response);
            }
        }
    }
}

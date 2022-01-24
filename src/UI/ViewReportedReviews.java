package UI;

import DataBase.ReportedReviewDAO;
import Model.ReportedReview;
import Model.Utilizador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "reportedReviews", value = "/reportedReviews")
public class ViewReportedReviews extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ReportedReview> reviewsList= ReportedReviewDAO.get_all();
        if(NearYouMain.modelo.getSessaoAtual() == null){
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }else{
            request.setAttribute("reviews",reviewsList);
            request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            request.setAttribute("BD",NearYouMain.ibdHandler);
            RequestDispatcher view = request.getRequestDispatcher("reportedReviews.jsp");
            view.forward(request, response);
        }


    }
}

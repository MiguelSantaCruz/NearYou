import DataBase.ReportedReviewDAO;
import Model.*;

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

@WebServlet(name = "report", value = "/report")
public class Report extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        StringTokenizer st = new StringTokenizer(query,"?");
        int reviewID = Integer.valueOf(st.nextToken());
        int userID = Integer.valueOf(st.nextToken());
        if(NearYouMain.modelo.getSessaoAtual() == null){
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }else{

            request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            request.setAttribute("BD",NearYouMain.ibdHandler);
            ReportedReview review = new ReportedReview(userID,reviewID,0);
            ReportedReviewDAO.add_rep(review);
            APIHandler apiHandler = new APIHandler();
            Map<String, PontoDeInteresse> pois = apiHandler.getPontosDeInteresse("cafe");
            if(pois != null) {
                for (Map.Entry<String, PontoDeInteresse> entry : pois.entrySet()) {
                    int id = NearYouMain.ibdHandler.addPoi(entry.getValue());
                    entry.getValue().setIdPontoInteresse(String.valueOf(id));
                }
            }
            request.setAttribute("pois",pois);
            RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
            view.forward(request, response);
        }
    }
}

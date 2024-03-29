package UI;

import Model.APIHandler;
import Model.PontoDeInteresse;
import Model.Utilizador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "searchuser", value = "/searchuser")
public class SearchUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = (String) request.getParameter("searchUser");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
        Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
        request.setAttribute("searchKeyUser",search);
        request.setAttribute("Utilizador",utilizador);
        APIHandler apiHandler = new APIHandler();
        Map<String,PontoDeInteresse> pois = apiHandler.getPontosDeInteresse(search);
        if(pois != null){
            for (Map.Entry<String,PontoDeInteresse> entry : pois.entrySet()) {
                int id = NearYouMain.ibdHandler.addPoi(entry.getValue());
                entry.getValue().setIdPontoInteresse(String.valueOf(id));
            }
        }
        request.setAttribute("pois",pois);
        request.setAttribute("BD",NearYouMain.ibdHandler);
        RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
        view.forward(request, response);
    }
}

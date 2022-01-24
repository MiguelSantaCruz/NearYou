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

@WebServlet(name = "blockUser", value = "/blockUser")
public class BloqUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if(NearYouMain.modelo.getSessaoAtual() != null){
            String idUser = request.getQueryString();
            request.setAttribute("Sessao", NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(), NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            request.setAttribute("BD", NearYouMain.ibdHandler);
            APIHandler apiHandler = new APIHandler();
            Map<String, PontoDeInteresse> pois = apiHandler.getPontosDeInteresse("cafe");
            if(pois != null) {
                for (Map.Entry<String, PontoDeInteresse> entry : pois.entrySet()) {
                    int id = NearYouMain.ibdHandler.addPoi(entry.getValue());
                    entry.getValue().setIdPontoInteresse(String.valueOf(id));
                }
            }
            request.setAttribute("pois",pois);
            NearYouMain.ibdHandler.bloqUser(Integer.parseInt(idUser));
            RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
            view.forward(request, response);
        }else{
            RequestDispatcher view = request.getRequestDispatcher("login");
            view.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

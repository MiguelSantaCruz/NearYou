import Model.APIHandler;
import Model.PontoDeInteresse;
import Model.Utilizador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "LoginAction", value = "/loginAction")
public class LoginAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if(NearYouMain.modelo.getSessaoAtual() != null){
            String ordenacao = request.getQueryString();
            System.out.println("Ordenação: " + ordenacao);
            request.setAttribute("ordenacao",ordenacao);
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
            RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
            view.forward(request, response);
        }else{
            RequestDispatcher view = request.getRequestDispatcher("login");
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String email = request.getParameter("uname");
        String password = request.getParameter("psw");
        int sessaoCriada = NearYouMain.modelo.login(email,password,NearYouMain.ibdHandler);

        if(sessaoCriada == -1) {
            RequestDispatcher view = request.getRequestDispatcher("loginError.jsp");
            view.forward(request, response);
        } else {
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
            RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
            view.forward(request, response);
        }



    }
}

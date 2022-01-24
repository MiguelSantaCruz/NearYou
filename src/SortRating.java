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
import java.util.StringTokenizer;

@WebServlet(name = "sort", value = "/sort")
public class SortRating extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if(NearYouMain.modelo.getSessaoAtual() != null){
            String query = request.getQueryString();
            StringTokenizer st = new StringTokenizer(query,"?");
            String ordenacao = st.nextToken();
            String searchKey = st.nextToken();
            System.out.println("Ordenação: " + ordenacao);
            if(!ordenacao.equals("rating")) ordenacao = null;
            request.setAttribute("ordenacao",ordenacao);
            request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            if(searchKey == null) searchKey = "cafe";
            request.setAttribute("searchKey", searchKey);
            request.setAttribute("BD",NearYouMain.ibdHandler);
            APIHandler apiHandler = new APIHandler();
            Map<String, PontoDeInteresse> pois;
            if(searchKey == null) pois = apiHandler.getPontosDeInteresse("cafe");
            else pois =  apiHandler.getPontosDeInteresse(searchKey);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

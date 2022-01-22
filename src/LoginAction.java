import Model.Utilizador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginAction", value = "/loginAction")
public class LoginAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Teste");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("uname");
        String password = request.getParameter("psw");
        int sessaoCriada = NearYouMain.modelo.login(email,password,NearYouMain.ibdHandler);

        if(sessaoCriada == -1) {
            out.println("<h1>Login inválido</h1>");
            out.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
        } else {
            request.setAttribute("Sessao",NearYouMain.modelo.getSessaoAtual());
            Utilizador utilizador = NearYouMain.modelo.getUtilizador(NearYouMain.modelo.getSessaoAtual().getIdUser(),NearYouMain.ibdHandler);
            request.setAttribute("Utilizador",utilizador);
            request.setAttribute("BD",NearYouMain.ibdHandler);
            RequestDispatcher view = request.getRequestDispatcher("userAuthenticated.jsp");
            view.forward(request, response);
        }



    }
}

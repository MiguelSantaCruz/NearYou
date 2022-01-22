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
        out.println("<p>");
        out.println("My name: "+email);
        out.println("</p>");
        String password = request.getParameter("psw");
        out.println("<p>");
        out.println("My password: "+ password);
        out.println("</p>");
        if(NearYouMain.modelo.login(email,password,NearYouMain.ibdHandler) == -1) out.println("Login inválido");
        else out.println("Sucesso");


    }
}

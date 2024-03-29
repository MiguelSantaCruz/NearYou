package UI;

import DataBase.UtilizadorDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UI.RegisterAction", value = "/registerAction")
public class RegisterAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("psw");
        String repeatPassword = request.getParameter("psw-repeat");
        out.println("<p> My email: " + email+"</p>");
        out.println("<p> My username: " + username+"</p>");
        out.println("<p> My password: " + password+"</p>");
        out.println("<p> My repeated password: " + repeatPassword+"</p>");
        if(UtilizadorDAO.exists_by_email(email)){
                RequestDispatcher view = request.getRequestDispatcher("registerError.jsp");
                view.forward(request, response);
        }else if(!password.equals(repeatPassword)){
                RequestDispatcher view = request.getRequestDispatcher("registerErrorPassword.jsp");
                view.forward(request, response);
        } else{
            int userID = NearYouMain.ibdHandler.addUtilizador(username,email,password,1,false);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
    }
}

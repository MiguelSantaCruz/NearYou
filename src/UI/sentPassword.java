package UI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UI.sentPassword", value = "/UI.sentPassword")
public class sentPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        request.setAttribute("email",email);
        RequestDispatcher view = request.getRequestDispatcher("UI.sentPassword.jsp");
        view.forward(request, response);
    }
}

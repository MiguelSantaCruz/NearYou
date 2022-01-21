import DataLayer.BDHandler;
import DataLayer.IBDHandler;
import Model.IModelo;
import Model.Modelo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Login", value = "/login")
public class NearYouMain extends HttpServlet {

    public static IModelo modelo;
    public static IBDHandler ibdHandler;

    public void init() throws ServletException{
        modelo = (IModelo) new Modelo();
        ibdHandler = (IBDHandler) new BDHandler();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }
}

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


@WebServlet(name = "Welcome", value = "/welcome")
public class NearYouMain extends HttpServlet {

    public static IModelo modelo = (IModelo) new Modelo();
    public static IBDHandler ibdHandler = ibdHandler = (IBDHandler) new BDHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("welcome.html");
        view.forward(request, response);
    }
}

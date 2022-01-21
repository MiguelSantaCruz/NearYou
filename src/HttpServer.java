import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer implements Runnable{
    public void run() {
        /* Porta para receber pedidos do servidor HTTP */
        int port = 8080;
        /* Informação a mostrar no browser */
        String serverString = "FFSync HTTP server";
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[TCP] HTTP server listening on port " + port + " ...");
            System.out.println("Pode aceder em http://localhost:" + port + "/");
            /* Aceitar pedidos e criar nova thread */
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread t1 = new Thread(new HttpHandler(clientSocket, serverString));
                t1.start();           
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}

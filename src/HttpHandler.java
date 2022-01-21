import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class HttpHandler implements Runnable {

    private static final String HtmlFiles = "WebContent/";
    private Socket socket;
    private String serverString;

    /* Construtor */
    public HttpHandler(Socket socket,String serverString){
        this.socket = socket;
        this.serverString = serverString;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Client " + socket.getInetAddress() + " connected on port " + socket.getPort());
            String line = reader.readLine();
            System.out.println(line);
            String httpFunction;
            String httpRequestedFile;
            if(line != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line," ");
                httpFunction = stringTokenizer.nextToken();
                httpRequestedFile = stringTokenizer.nextToken();
                switch (httpFunction){
                    case "GET":
                        switch (httpRequestedFile) {
                            case "/":
                                sendResponse(200, HtmlFiles + "welcome.html", true, true, outputStream);
                                break;
                            case "/login":
                                sendResponse(200, HtmlFiles + "login.html", true, true, outputStream);
                                break;
                            default:
                                String filename = HtmlFiles + httpRequestedFile.substring(1);
                                File file = new File(filename);
                                System.out.println("File sent: " + filename);
                                if (file.exists())
                                    sendResponse(200, filename, true, false, outputStream);
                                else
                                    sendResponse(404, "<html><p>Recurso não encontrado</p></html>", false, true, outputStream);
                                break;
                        }
                        break;
                    case "POST":
                        case "/action_page.php?uname=a&psw=a&remember=on":
                            sendResponse(200,"<html><p>User autenticado com sucesso!!</p></html>",false,true,outputStream);
                        break;
                    default:
                        String filename = HtmlFiles + httpRequestedFile.substring(1);
                        File file = new File(filename);
                        System.out.println("File sent: " + filename);
                        if (file.exists())
                            sendResponse(200, filename, true, false, outputStream);
                        else
                            sendResponse(404, "<html><p>Recurso não encontrado</p></html>", false, true, outputStream);
                        break;

                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }     
    }

    /* Enviar a resposta para o browser */
    public void sendResponse(int code, String response, Boolean isFile,Boolean isHTML,OutputStream out) throws IOException{
        out.write(("HTTP/1.1 " + code +"\n").getBytes());
        out.write(("Server: " + this.serverString +"\n").getBytes());
        out.write(("Date: " + LocalDateTime.now() +"\n").getBytes());
        if(isFile){
            if(isHTML) out.write(("Content-type: text/html; charset=utf-8\n").getBytes());
            else out.write(("Content-type: multipart/form-data;\n").getBytes());
            File file = new File(response);

            out.write(("Content-length: " + file.length() +"\n").getBytes());
            out.write(("\r\n").getBytes());
            FileInputStream fin = new FileInputStream(file);
            int bytesRead;
            while ((bytesRead = fin.read()) != -1) {
                out.write(bytesRead);
            }
            fin.close();
        } else {
            if(isHTML) out.write(("Content-type: text/html; charset=utf-8\n").getBytes());
            out.write(("Content-length: " + response.length() +"\n").getBytes());
            out.write(("\r\n").getBytes());
            out.write(response.getBytes());
        }
        System.out.println("Done");
        
        out.close();
    }
    
}

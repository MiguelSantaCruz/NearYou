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
                                checkFileAndSendResponse(outputStream, httpRequestedFile, filename, file);
                                break;
                            case "/check":
                                
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
                        checkFileAndSendResponse(outputStream, httpRequestedFile, filename, file);
                        break;

                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }     
    }

    private void checkFileAndSendResponse(OutputStream outputStream, String httpRequestedFile, String filename, File file) throws IOException {
        if (file.exists())
            sendResponse(200, filename, true, false, outputStream);
        else {
            String htmlFilename = HtmlFiles + httpRequestedFile.substring(1) + ".html";
            File htmlFile = new File(htmlFilename);
            if (htmlFile.exists()) sendResponse(200, htmlFilename, true, true, outputStream);
            else {
                String jspFilename = HtmlFiles + httpRequestedFile.substring(1) + ".jsp";
                File jspFile = new File(jspFilename);
                if (jspFile.exists()) sendResponse(200, jspFilename, true, false, outputStream);
                else {
                    sendResponse(404, HtmlFiles + "error404.html", true, true, outputStream);
                }
            }
        }
    }

    /* Enviar a resposta para o browser */
    public void sendResponse(int code, String response, Boolean isFile,Boolean isHTML,OutputStream out) throws IOException{
        out.write(("HTTP/1.1 " + code +"\n").getBytes());
        out.write(("Server: " + this.serverString +"\n").getBytes());
        out.write(("Date: " + LocalDateTime.now() +"\n").getBytes());
        if(isFile){
            if(response.endsWith(".html")){
                out.write(("Content-type: text/html; charset=utf-8\n").getBytes());
            } else if(response.endsWith(".jsp")){
                out.write(("Content-type: text/html; charset=utf-8\n").getBytes());
            }  else if(response.endsWith(".png")){
                out.write(("Content-type: image/png;\n").getBytes());
            } else if(response.endsWith(".jpeg")){
                out.write(("Content-type: image/jpeg;\n").getBytes());
            } else if(response.endsWith(".gif")){
                out.write(("Content-type: image/gif;\n").getBytes());
            } else out.write(("Content-type: multipart/form-data;\n").getBytes());
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

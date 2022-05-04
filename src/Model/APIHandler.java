package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class APIHandler {
    String MAXHEIGHT = "170";
    String MAXWIDTH = "170";
    String APIKEY = ""; /* Insert API Key Here */

    public APIHandler() {
    }

    public String getPhoto(String referencia) {
            String urlFoto;
            String urlString = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + referencia + "&sensor=false&maxheight=" + MAXHEIGHT + "&maxwidth=" + MAXWIDTH + "&key=" + APIKEY;
            return  urlString;
    }

    public Map<String,PontoDeInteresse> getPontosDeInteresse(String input) {
        try {
            String newUrlString = input.replaceAll(" ", "%20");
            URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+ newUrlString + "&key=" + APIKEY);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int connectionOk = conn.getResponseCode();

            if (connectionOk != 200) {
                throw new RuntimeException("httpResponseCode" + connectionOk);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                System.out.println("Informação" + informationString);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(String.valueOf(informationString));
                JSONArray array = new JSONArray();
                array.add(obj);


                //Get the first JSON object in the JSON array
                System.out.println(array.get(0));

                JSONObject placesData = (JSONObject) array.get(0);
                System.out.println("\nplacesdata----" + placesData);
                JSONArray results = (JSONArray) placesData.get("results");
                System.out.println("\nResults---" + results);
                Map<String,PontoDeInteresse> pontosDeInteresse = new HashMap<>();
                if(results.isEmpty()) pontosDeInteresse = null;
                for (int i = 0; i < results.size(); i++) {
                    JSONObject resultsdata = (JSONObject) results.get(i);
                    String nome = (String) resultsdata.get("name");
                    String endereco = (String) resultsdata.get("formatted_address");
                    JSONArray fotos = (JSONArray) resultsdata.get("photos");
                    String referenciaFoto;
                    if(fotos != null) {
                        JSONObject fotosRef = (JSONObject) fotos.get(0);
                        referenciaFoto = (String) fotosRef.get("photo_reference");
                        System.out.println("Referencia antes da funcao: " + referenciaFoto);
                        referenciaFoto = getPhoto(referenciaFoto);
                    } else {
                        referenciaFoto = null;
                    }
                    System.out.println("Referencia foto:" + referenciaFoto);
                    String placeID = (String) resultsdata.get("place_id");
                    String rating = String.valueOf(resultsdata.get("rating"));
                    if(rating == null) rating = "0";
                    JSONArray types = (JSONArray) resultsdata.get("types");
                    List<String> tags = new ArrayList<>();
                    for (int j = 0; j < types.size(); j++) {
                        tags.add((String) types.get(j));
                    }
                    PontoDeInteresse ponto = new PontoDeInteresse(placeID, nome, "NaN", tags, referenciaFoto, endereco, null, Float.valueOf(rating));
                    pontosDeInteresse.put(ponto.getIdPontoInteresse(),ponto);
                    //System.out.println(ponto.toString());
                }
                return pontosDeInteresse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPublicIP() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String
        System.out.println(ip);
        return ip;
    }

    public String localizautilizador() {
        String city = "Braga";
        String country = "Portugal";
        try {
            URL url = new URL("http://ip-api.com/json/" + getPublicIP());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int connectionOk = conn.getResponseCode();

            if (connectionOk != 200) {
                throw new RuntimeException("httpResponseCode" + connectionOk);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                System.out.println("Informação" + informationString);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(String.valueOf(informationString));
                JSONObject jsonObject = (JSONObject) obj;
                 city = (String) jsonObject.get("city");
                 country = (String) jsonObject.get("country");
                System.out.println("Cidade: " + city);
                System.out.println("Pais: " + country);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return city+","+country;
    }
}

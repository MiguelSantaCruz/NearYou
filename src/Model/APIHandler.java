package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class APIHandler {
    String APIKEY = "AIzaSyB2KiMnJmJBQMW4KjlW_23xCDbu4nPg3kE";

    public APIHandler() {
    }

    public List<PontoDeInteresse> getPontosDeInteresse(String input) {
        try {

            URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + input+"&key=" + APIKEY);

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
                List<PontoDeInteresse> pontosDeInteresse = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    JSONObject resultsdata = (JSONObject) results.get(i);
                    String nome = (String) resultsdata.get("name");
                    String endereco = (String) resultsdata.get("formatted_address");
                    JSONArray fotos = (JSONArray) resultsdata.get("photos");
                    String referenciaFoto;
                    if(fotos != null) {
                        JSONObject fotosRef = (JSONObject) fotos.get(0);
                        referenciaFoto = (String) fotosRef.get(0);
                    } else {
                        referenciaFoto = null;
                    }
                    String placeID = (String) resultsdata.get("place_id");
                    String rating = String.valueOf(resultsdata.get("rating"));
                    JSONArray types = (JSONArray) resultsdata.get("types");
                    List<String> tags = new ArrayList<>();
                    for (int j = 0; j < types.size(); j++) {
                        tags.add((String) types.get(j));
                    }
                    PontoDeInteresse ponto = new PontoDeInteresse(placeID, nome, "NaN", tags, referenciaFoto, endereco, null, Float.valueOf(rating));
                    pontosDeInteresse.add(ponto);
                    //System.out.println(ponto.toString());
                }
                return pontosDeInteresse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
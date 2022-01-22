package Model;

import java.util.List;

public class APIHandler {
    public String localizaUser(){

        return "NaN";
    }

    public List<PontoDeInteresse> localizaPIsLocal(String localizacao, Float raioDistancia, List<String> tags){
        return null;
    }

    public List<PontoDeInteresse> localizaPIsNome(String nomePi, Float raioDistancia, List<String> tags){
        return null;
    }

    public Float getDistancia(String idPI, String localizacao){
        return 0.0f;
    }

    public List<String> getTags(){
        return null;
    }

    public PontoDeInteresse loadPI(String idPI){
        return null;
    }
}

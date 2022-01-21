package Model;

import DataLayer.IBDHandler;

import java.util.List;

public class SessaoUtilizador extends Sessao{
    public void refreshLocalizacao() {

    }

    public List<PontoDeInteresse> localizaPisPorNome(String nome, Float raioDistancia, int classificacao, List<String> tags, IBDHandler ibdHandler) {

        return null;
    }

    public List<PontoDeInteresse> localizaPisLocalizacao(String localizacao, Float raioDistancia, int classificacao, List<String> tags) {

        return null;
    }

    public void alteraGosto(String reviewID) {
    }

    public boolean verificaGosto(String reviewID) {

        return false;
    }

    public void alteraReport(String reviewID) {
    }

    public boolean verificaReport(String reviewID) {
        return false;
    }

    public List<Utilizador> searchUser(String Username) {
        return null;
    }

    public int alteraDados(String username, String email, String password) {
        return 0;
    }

    public void alteraReview(String reviewID, String comentario, int classificacao) {

    }

    public boolean addReview(int classificacao, String comentario, String idPI) {
        return false;

    }

    public void alteraPiguardado(String idPI) {
    }

    public boolean removePI(String idPI) {
        return false;
    }

    public boolean removeReview(String reviewID) {
        return false;

    }

    public void removeConta() {
    }

    public boolean verificaPtguardado(String idPI) {
        return false;
    }

    public boolean guardarPI(String idPI) {
        return false;
    }

    public String getIdUser() {
        return null;

    }

    public void setIdUser(String idUser) {
    }

    public String getLocalizacao() {
        return null;

    }

    public void setLocalizacao(String localizacao) {
    }
}

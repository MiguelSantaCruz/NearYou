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

    public void alteraGosto(String reviewID, IBDHandler ibdHandler) {
    }

    public boolean verificaGosto(String reviewID,IBDHandler ibdHandler) {

        return false;
    }

    public void alteraReport(String reviewID,IBDHandler ibdHandler) {
    }

    public boolean verificaReport(String reviewID,IBDHandler ibdHandler) {
        return false;
    }

    public List<Utilizador> searchUser(String Username,IBDHandler ibdHandler) {
        return null;
    }

    public int alteraDados(String username, String email, String password,IBDHandler ibdHandler) {
        return 0;
    }

    public void alteraReview(String reviewID, String comentario, int classificacao, IBDHandler ibdHandler) {

    }

    public boolean addReview(int classificacao, String comentario, String idPI, IBDHandler ibdHandler) {
        return false;

    }

    public void alteraPiguardado(String idPI, IBDHandler ibdHandler) {
    }

    public boolean removePI(String idPI, IBDHandler ibdHandler) {
        return false;
    }

    public boolean removeReview(String reviewID, IBDHandler ibdHandler) {
        return false;

    }

    public void removeConta(IBDHandler ibdHandler) {
    }

    public boolean verificaPIguardado(String idPI, IBDHandler ibdHandler) {
        return false;
    }

    public boolean guardarPI(String idPI, IBDHandler ibdHandler) {
        return false;
    }

    public String getIdUser(IBDHandler ibdHandler) {
        return null;

    }

    public void setIdUser(String idUser,IBDHandler ibdHandler) {
    }

    public String getLocalizacao(IBDHandler ibdHandler) {
        return null;

    }

    public void setLocalizacao(String localizacao) {
    }
}

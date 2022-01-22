package Model;

import DataLayer.BDHandler;
import DataLayer.IBDHandler;

import java.util.List;

public class SessaoUtilizador extends Sessao{

    public SessaoUtilizador(String idUser){
        super(idUser);
    }


    public void alteraGosto(String reviewID, IBDHandler ibdHandler) {
        ibdHandler.alteraGosto(super.getIdUser(),reviewID);
    }

    public boolean verificaGosto(String reviewID,IBDHandler ibdHandler) {
        ibdHandler.verificaGosto(reviewID,super.getIdUser());
        return false;
    }

    public void alteraReport(String reviewID,IBDHandler ibdHandler) {
        ibdHandler.alteraReport(super.getIdUser(),reviewID);
    }

    public boolean verificaReport(String reviewID,IBDHandler ibdHandler) {
        return ibdHandler.verificaReport(reviewID,super.getIdUser());
    }

    public List<Utilizador> searchUser(String username,IBDHandler ibdHandler) {
        return ibdHandler.getUtilizadores(username);
    }

    public int alteraDados(String username, String email, String password,IBDHandler ibdHandler) {
        ibdHandler.atualizaUtilizador(super.getIdUser(),username,email,password);
        return 1;
    }

    public void alteraReview(String reviewID, String comentario, int classificacao, IBDHandler ibdHandler) {
        ibdHandler.alteraReview(comentario,classificacao,reviewID);
    }

    public boolean addReview(int classificacao, String comentario, String idPI, IBDHandler ibdHandler) {
        return ibdHandler.addReview(comentario,classificacao+"",idPI,super.getIdUser());

    }

    public void alteraPiguardado(String idPI, IBDHandler ibdHandler) {
        ibdHandler.alteraPontoInteresseGuardado(idPI,super.getIdUser());
    }

    public boolean removeReview(String reviewID, IBDHandler ibdHandler) {
        return ibdHandler.removeReview(reviewID);

    }

    public void removeConta(IBDHandler ibdHandler) {
        ibdHandler.removeUtilizador(super.getIdUser());
    }

    public boolean verificaPIguardado(String idPI, IBDHandler ibdHandler) {
        return ibdHandler.verificaPontoInteresseGuardado(super.getIdUser(),idPI);
    }

    public boolean guardarPI(String idPI, IBDHandler ibdHandler) {
        return ibdHandler.guardarPontoInteresse(super.getIdUser(),idPI);
    }

}

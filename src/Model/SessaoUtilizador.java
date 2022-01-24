package Model;

import DataLayer.BDHandler;
import DataLayer.IBDHandler;

import java.util.List;

public class SessaoUtilizador extends Sessao{

    public SessaoUtilizador(String idUser){
        super(idUser);
    }


    public void alteraGosto(String reviewID, IBDHandler ibdHandler) {
        ibdHandler.alteraGosto(Integer.valueOf(super.getIdUser()),Integer.valueOf(reviewID));
    }

    public boolean verificaGosto(String reviewID,IBDHandler ibdHandler) {
        ibdHandler.verificaGosto(Integer.valueOf(reviewID),Integer.valueOf(super.getIdUser()));
        return false;
    }

    public void alteraReport(String reviewID,IBDHandler ibdHandler) {
        ibdHandler.alteraReport(Integer.valueOf(super.getIdUser()),Integer.valueOf(reviewID));
    }

    public boolean verificaReport(String reviewID,IBDHandler ibdHandler) {
        return ibdHandler.verificaReport(Integer.valueOf(reviewID),Integer.valueOf(super.getIdUser()));
    }

    public List<Utilizador> searchUser(String username,IBDHandler ibdHandler) {
        return ibdHandler.getUtilizadores(username);
    }

    public int alteraDados(String username, String email, String password,IBDHandler ibdHandler,int permissao) {
        ibdHandler.atualizaUtilizador(Integer.valueOf(super.getIdUser()),username,email,password,permissao);
        return 1;
    }


    public void alteraPiguardado(String idPI, IBDHandler ibdHandler) {
        ibdHandler.alteraPontoInteresseGuardado(Integer.valueOf(idPI),Integer.valueOf(super.getIdUser()));
    }

    public boolean removeReview(String reviewID, IBDHandler ibdHandler) {
        return ibdHandler.removeReview(Integer.valueOf(reviewID));

    }

    public void removeConta(IBDHandler ibdHandler) {
        ibdHandler.removeUtilizador(Integer.valueOf(super.getIdUser()));
    }

    public boolean verificaPIguardado(String idPI, IBDHandler ibdHandler) {
        return ibdHandler.verificaPontoInteresseGuardado(Integer.valueOf(super.getIdUser()),Integer.valueOf(idPI));
    }

    public boolean guardarPI(String idPI, IBDHandler ibdHandler) {
        return ibdHandler.guardarPontoInteresse(Integer.valueOf(super.getIdUser()),Integer.valueOf(idPI));
    }

}

package Model;

import DataLayer.IBDHandler;

public class SessaoModerador extends Sessao {

    public SessaoModerador(String idUser){
        super(idUser);
    }

    public void atualizaReports(){

    }

    public void bloqUser(String userID, IBDHandler ibdHandler){
        ibdHandler.bloqUser(userID);
    }

    public void modRemoveReview(String reviewID, IBDHandler ibdHandler){
        ibdHandler.removeReview(reviewID);
    }

    public void resertarReports(String reviewID, IBDHandler ibdHandler){
        ibdHandler.removeReports(reviewID);
    }
}

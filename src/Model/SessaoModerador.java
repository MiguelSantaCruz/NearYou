package Model;

import DataLayer.IBDHandler;

public class SessaoModerador extends Sessao {

    public SessaoModerador(String idUser){
        super(idUser);
    }

    public void atualizaReports(){

    }

    public void bloqUser(String userID, IBDHandler ibdHandler){
        ibdHandler.bloqUser(Integer.valueOf(userID));
    }

    public void modRemoveReview(String reviewID, IBDHandler ibdHandler){
        ibdHandler.removeReview(Integer.valueOf(reviewID));
    }

    public void resertarReports(String reviewID, IBDHandler ibdHandler){
        ibdHandler.removeReports(Integer.valueOf(reviewID));
    }
}

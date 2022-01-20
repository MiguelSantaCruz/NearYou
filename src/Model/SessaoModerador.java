package Model;

import DataLayer.IBDHandler;

public class SessaoModerador extends Sessao {
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

package Model;

import DataLayer.IBDHandler;

public class SessaoAdministrador extends Sessao{

    public int addModerador(String email, String username, String password, IBDHandler ibdHandler){
        return ibdHandler.addUtilizador(email,username,password,2);
    }

    public boolean removeModerador(String userID,IBDHandler ibdHandler){
        return ibdHandler.removeUtilizador(userID);
    }



}

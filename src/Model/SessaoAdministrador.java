package Model;

import DataLayer.IBDHandler;

public class SessaoAdministrador extends Sessao{

    public SessaoAdministrador(String idUser){
        super(idUser);
    }

    public int addModerador(String email, String username, String password, IBDHandler ibdHandler){
        return ibdHandler.addUtilizador(email,username,password,2,false);
    }

    public boolean removeModerador(String userID,IBDHandler ibdHandler){
        return ibdHandler.removeUtilizador(Integer.valueOf(userID));
    }



}

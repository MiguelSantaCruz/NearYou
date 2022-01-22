package Model;

public class Sessao implements ISessao{

    private String idUser;

    public Sessao(String idUser){
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}

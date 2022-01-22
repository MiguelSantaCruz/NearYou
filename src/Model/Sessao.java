package Model;

public class Sessao implements ISessao{

    private String idUser;
    private String localizacao;

    public Sessao(String idUser,String localizacao){
        this.idUser = idUser;
        this.localizacao = localizacao;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}

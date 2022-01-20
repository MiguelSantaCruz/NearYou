package Model;

import java.util.List;

public interface IModelo {
    int registarUser(String email, String username, String password);
    int nivelPermissao();
    int login(String email, String password);
    boolean recuperacaoPassword(String email);
    String getLocalizacao();
    void logOut();
    List<PontoDeInteresse> getPIsPorNome(String nome, Float raioDistancia, int classificacao, List<String> tags);
    List<PontoDeInteresse> getPIsPorLocalizacao(String localizacao, Float raioDistancia, int classificacao, List<String> tags);
    void alteraGosto( String reviewID);
    public boolean verificaGosto(String  reviewID);
    public void alteraReport(String  reviewID);
    public boolean verificaReport(String  reviewID);
    public void searchUser(String  searchinput);
    public List<Utilizador> urlDirecoes(String localizacaoUtilizador, PontoDeInteresse pi);
    public int alteraDados(String  username, String  email, String  password);
    public void alteraReview(String  reviewID, String comentario, int  classificacao);
    public void removeConta();
    public boolean verificaPiguardado(String  idPI);
    public boolean alteraPIguardado(String  idPI);
    public Utilizador getUtilizado(String  userID);
    public PontoDeInteresse getPI(String  idPI);
    public List<PontoDeInteresse> ordenaPIs(List <PontoDeInteresse> pis, int ordenacao);
    public boolean addReview(String  comentario, int  classificacao);
    public boolean removeReview(String  reviewID);
    public void atualizaReports();
    public void bloquser(String  userID);
    public void modRemoveReview(String  reviewID);
    public void resetarReports(String  reviewID);
    public boolean addModerador(String  email, String ssername, String password);
    public void removeModerador(String  username);
}

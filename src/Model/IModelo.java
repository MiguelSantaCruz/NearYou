package Model;

import DataLayer.IBDHandler;

import java.util.List;

public interface IModelo {
    int registarUser(String email, String username, String password, IBDHandler ibdHandler);
    int nivelPermissao(String userID);
    int login(String email, String password,IBDHandler ibdHandler);
    boolean recuperacaoPassword(String email);
    String getLocalizacao();
    void logOut();
    List<PontoDeInteresse> getPIsPorNome(String nome, Float raioDistancia, int classificacao, List<String> tags,IBDHandler ibdHandler);
    List<PontoDeInteresse> getPIsPorLocalizacao(String localizacao, Float raioDistancia, int classificacao, List<String> tags,IBDHandler ibdHandler);
    void alteraGosto( String reviewID,IBDHandler ibdHandler);
    public boolean verificaGosto(String  reviewID,IBDHandler ibdHandler);
    public void alteraReport(String  reviewID,IBDHandler ibdHandler);
    public boolean verificaReport(String  reviewID,IBDHandler ibdHandler);
    public List<Utilizador> searchUser(String  searchinput,IBDHandler ibdHandler);
    public List<Utilizador> urlDirecoes(String localizacaoUtilizador, PontoDeInteresse pi,IBDHandler ibdHandler);
    public int alteraDados(String  username, String  email, String  password,IBDHandler ibdHandler);
    public void alteraReview(String  reviewID, String comentario, int  classificacao,IBDHandler ibdHandler);
    public void removeConta(String username, IBDHandler ibdHandler);
    public boolean verificaPiguardado(String  idPI,IBDHandler ibdHandler);
    public boolean alteraPIguardado(String  idPI,IBDHandler ibdHandler);
    public Utilizador getUtilizador(String  userID,IBDHandler ibdHandler);
    public PontoDeInteresse getPI(String  idPI,IBDHandler ibdHandler);
    public List<PontoDeInteresse> ordenaPIs(List <PontoDeInteresse> pis, int ordenacao,IBDHandler ibdHandler);
    public boolean addReview(String  comentario, int  classificacao,IBDHandler ibdHandler);
    public boolean removeReview(String  reviewID,IBDHandler ibdHandler);
    public void atualizaReports();
    public void bloquser(String  userID,IBDHandler ibdHandler);
    public void modRemoveReview(String  review,IBDHandler ibdHandler);
    public void resetarReports(String  reviewID,IBDHandler ibdHandler);
    public boolean addModerador(String  email, String ssername, String password,IBDHandler ibdHandler);
    public void removeModerador(String  username,IBDHandler ibdHandler);
}

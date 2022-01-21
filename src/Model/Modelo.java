package Model;

import DataLayer.IBDHandler;

import java.util.List;

public class Modelo /*implements IModelo*/{

    private ISessao sessaoAtual;

    public Modelo(){
        this.sessaoAtual = null;
    }

    public int registarUser(String email, String username, String password, IBDHandler ibdHandler) {
        int userID = ibdHandler.addUtilizador(username,email,password,1);
        return userID;
    }

    public int nivelPermissao(IBDHandler ibdHandler) {
        int nivelPermissao = -1;
        if(this.sessaoAtual != null){
            nivelPermissao = ibdHandler.nivelDePermissao(sessaoAtual.getIdUser());
        }
        return nivelPermissao;
    }

    /**
     * Efetua o login de um utilizador e cria uma sessão
     * @param email O email do utilizador
     * @param password A password do utilizador
     * @param ibdHandler A interface da base de dados
     * @return 1 se foi criada uma sessão de utilizador, 2 se sessão de monitor, 3 se sessão de administrador, -1 caso contrário
     */
    public int login(String email, String password, IBDHandler ibdHandler) {
        int sessaoCriada = -1;
        String idUser = ibdHandler.verificaLogin(email, password);
        if (idUser != null && !ibdHandler.verificaBloqueado(idUser)) {
            int nivelPermissao = ibdHandler.nivelDePermissao(idUser);
            switch (nivelPermissao) {
                case 1:
                    this.sessaoAtual = new SessaoUtilizador();
                    sessaoCriada = 1;
                    break;
                case 2:
                    this.sessaoAtual = new SessaoModerador();
                    sessaoCriada = 2;
                    break;
                case 3:
                    this.sessaoAtual = new SessaoAdministrador();
                    sessaoCriada = 3;
                    break;
                default:
                    break;
            }
        }
        return sessaoCriada;
    }

    /**
     * Envia um email ao utilizador com sessão iniciada com a sua password
     * @param email Email do utilizador
     * @return true se foi enviado email com sucesso, false caso contrário
     */
    public boolean recuperacaoPassword(String email,IBDHandler ibdHandler) {
        boolean passwordEnviada = false;
        EmailHandler emailHandler = new EmailHandler();
        String password = ibdHandler.recuperaPassword(email);
        if(password != null) {
            emailHandler.enviaPassword(email, password);
            passwordEnviada = true;
        }
        return passwordEnviada;
    }

    /**
     * Devolve a localizacao do utilizador
     *
     */
    public String getLocalizacao() {
        String localizacao = "NaN";
        if(this.sessaoAtual.getLocalizacao() != null){
            localizacao = this.sessaoAtual.getLocalizacao();
        }
        return localizacao;
    }

    /**
     * Fecha a sessão atual
     */
    public void logOut() {
        this.sessaoAtual = null;
    }

    /***
     *
     *
     */
    public List<PontoDeInteresse> getPIsPorNome(String nome, Float raioDistancia, int classificacao, List<String> tags, IBDHandler ibdHandler) {
        
        return null;
    }

    
    public List<PontoDeInteresse> getPIsPorLocalizacao(String localizacao, Float raioDistancia, int classificacao, List<String> tags, IBDHandler ibdHandler) {
        return null;
    }

    
    public void alteraGosto(String reviewID, IBDHandler ibdHandler) {

    }

    
    public boolean verificaGosto(String reviewID, IBDHandler ibdHandler) {
        return false;
    }

    
    public void alteraReport(String reviewID, IBDHandler ibdHandler) {

    }

    
    public boolean verificaReport(String reviewID, IBDHandler ibdHandler) {
        return false;
    }

    
    public void searchUser(String searchinput, IBDHandler ibdHandler) {

    }

    
    public List<Utilizador> urlDirecoes(String localizacaoUtilizador, PontoDeInteresse pi, IBDHandler ibdHandler) {
        return null;
    }

    
    public int alteraDados(String username, String email, String password, IBDHandler ibdHandler) {
        return 0;
    }

    
    public void alteraReview(String reviewID, String comentario, int classificacao, IBDHandler ibdHandler) {

    }

    
    public void removeConta(String username, IBDHandler ibdHandler) {

    }

    
    public boolean verificaPiguardado(String idPI, IBDHandler ibdHandler) {
        return false;
    }

    
    public boolean alteraPIguardado(String idPI, IBDHandler ibdHandler) {
        return false;
    }

    
    public Utilizador getUtilizador(String userID, IBDHandler ibdHandler) {
        return null;
    }

    
    public PontoDeInteresse getPI(String idPI, IBDHandler ibdHandler) {
        return null;
    }

    
    public List<PontoDeInteresse> ordenaPIs(List<PontoDeInteresse> pis, int ordenacao, IBDHandler ibdHandler) {
        return null;
    }

    
    public boolean addReview(String comentario, int classificacao, IBDHandler ibdHandler) {
        return false;
    }

    
    public boolean removeReview(String reviewID, IBDHandler ibdHandler) {
        return false;
    }

    
    public void atualizaReports() {

    }

    
    public void bloquser(String userID, IBDHandler ibdHandler) {

    }

    
    public void modRemoveReview(String review, IBDHandler ibdHandler) {

    }

    
    public void resetarReports(String reviewID, IBDHandler ibdHandler) {

    }

    
    public boolean addModerador(String email, String ssername, String password, IBDHandler ibdHandler) {
        return false;
    }

    
    public void removeModerador(String username, IBDHandler ibdHandler) {

    }
}

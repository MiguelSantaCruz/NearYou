package Model;

import DataLayer.IBDHandler;


import java.util.List;

public class Modelo implements IModelo{

    private ISessao sessaoAtual;

    public Modelo(){
        this.sessaoAtual = null;
    }


    public ISessao getSessaoAtual() {
        return sessaoAtual;
    }

    public void setSessaoAtual(ISessao sessaoAtual) {
        this.sessaoAtual = sessaoAtual;
    }

    public int registarUser(String email, String username, String password, IBDHandler ibdHandler) {
        int userID = ibdHandler.addUtilizador(username,email,password,1);
        return userID;
    }


    @Override
    public int nivelPermissao(String userID) {
        return 0;
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
        System.out.println(idUser+"");
        if (idUser != null && !ibdHandler.verificaBloqueado(idUser)) {
            int nivelPermissao = ibdHandler.nivelDePermissao(idUser);
            System.out.println("nivelPermissao: " + nivelPermissao);
            switch (nivelPermissao) {
                case 1:
                    this.sessaoAtual = new SessaoUtilizador(idUser);
                    sessaoCriada = 1;
                    break;
                case 2:
                    this.sessaoAtual = new SessaoModerador(idUser);
                    sessaoCriada = 2;
                    break;
                case 3:
                    this.sessaoAtual = new SessaoAdministrador(idUser);
                    sessaoCriada = 3;
                    break;
                default:
                    this.sessaoAtual = new SessaoUtilizador(idUser);
                    sessaoCriada = 1;
                    break;
            }
        }
        return sessaoCriada;
    }

    @Override
    public boolean recuperacaoPassword(String email) {
        return false;
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

    /**
     * Alterar um gosto numa review
     */
    public void alteraGosto(String reviewID, IBDHandler ibdHandler) {
        if(sessaoAtual != null){
            String userId = sessaoAtual.getIdUser();
            ibdHandler.alteraGosto(reviewID,userId);
        }
    }

    /**
     *
     *Verifica um gosto numa review
     *
     */
    public boolean verificaGosto(String reviewID, IBDHandler ibdHandler) {
        if(sessaoAtual != null){
            return ibdHandler.verificaGosto(reviewID,sessaoAtual.getIdUser());

        }
        return false;
    }

    
    public void alteraReport(String reviewID, IBDHandler ibdHandler) {
        if(sessaoAtual != null){
            //ibdHandler.alteraReport()
            //TO DO
        }
    }

    /**
     * Verifica se uma determinada review foi reportada
     */
    public boolean verificaReport(String reviewID, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            String userID = sessaoAtual.getIdUser();
            return ibdHandler.verificaReport(reviewID,userID);
        }
        return false;
    }

    /**
     * Requisita à interface da base de dados utiliza-
     * dores cujos dados se aproximem do input de procura
     */
    public List<Utilizador> searchUser(String searchinput, IBDHandler ibdHandler) {
        return ibdHandler.getUtilizadores(searchinput);
    }

    
    public List<Utilizador> urlDirecoes(String localizacaoUtilizador, PontoDeInteresse pi, IBDHandler ibdHandler) {
        return null;
    }

    /**
     *
     *
     * @return 1 se correr tudo bem e -1 se falhar
     */
    public int alteraDados(String username, String email, String password, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            su.alteraDados(username,email,password,ibdHandler);
            return 1;
        }
        return -1;
    }

    /**
     *
     *  Recorre ao método ’alteraReview’ da classe Sessao, para alterar o comen-
     *  tário e a classificação de uma review, caso esta pertença ao utilizador
     *
     */
    public void alteraReview(String reviewID, String comentario, int classificacao, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            su.alteraReview(reviewID,comentario,classificacao,ibdHandler);
        }
    }

    
    public void removeConta(String username, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            su.removeConta(ibdHandler);

        }
    }

    
    public boolean verificaPiguardado(String idPI, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            return su.verificaPIguardado(idPI,ibdHandler);

        }
        return false;
    }

    
    public boolean alteraPIguardado(String idPI, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            su.alteraPiguardado(idPI,ibdHandler);
            return true;

        }
        return false;
    }

    
    public Utilizador getUtilizador(String userID, IBDHandler ibdHandler) {
        return ibdHandler.getUtilizador(userID);
    }

    
    public PontoDeInteresse getPI(String idPI, IBDHandler ibdHandler) {
        return ibdHandler.getPontoInteresse(idPI);
    }

    
    public List<PontoDeInteresse> ordenaPIs(List<PontoDeInteresse> pis, int ordenacao, IBDHandler ibdHandler) {
        if(ordenacao == 1){
            // TO DO
        }
        else if(ordenacao == 2){
            // TO DO
        }
        else return null;
        return null;
    }

    @Override
    public boolean addReview(String comentario, int classificacao, IBDHandler ibdHandler) {
        return false;
    }

    public boolean addReview(String IDpi,String comentario, int classificacao, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            return su.addReview(classificacao,comentario,IDpi,ibdHandler);

        }
        return false;

    }

    
    public boolean removeReview(String reviewID, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoUtilizador su = (SessaoUtilizador) sessaoAtual;
            return su.removeReview(reviewID,ibdHandler);

        }
        return false;
    }

    
    public void atualizaReports() {
        if(this.sessaoAtual != null){
            SessaoModerador sm = (SessaoModerador) sessaoAtual;
            sm.atualizaReports();
        }
    }

    
    public void bloquser(String userID, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoModerador sm = (SessaoModerador) sessaoAtual;
            sm.bloqUser(userID,ibdHandler);
        }
    }

    
    public void modRemoveReview(String review, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoModerador sm = (SessaoModerador) sessaoAtual;
            sm.modRemoveReview(review,ibdHandler);
        }
    }

    
    public void resetarReports(String reviewID, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoModerador sm = (SessaoModerador) sessaoAtual;
            sm.resertarReports(reviewID,ibdHandler);
        }
    }

    
    public boolean addModerador(String email, String username, String password, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoAdministrador sa = (SessaoAdministrador) sessaoAtual;
            sa.addModerador(email, username, password, ibdHandler);
            return true;
        }
        return false;
    }

    
    public void removeModerador(String username, IBDHandler ibdHandler) {
        if(this.sessaoAtual != null){
            SessaoAdministrador sa = (SessaoAdministrador) sessaoAtual;
            sa.removeModerador(username, ibdHandler);
        }
    }
}

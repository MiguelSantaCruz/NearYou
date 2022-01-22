package DataLayer;

import Model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DBTest implements IBDHandler{
    Map<String, Utilizador> utilizadores;
    Map<String, PontoDeInteresse> pois;
    Map<String, Review> reviews;

    public DBTest(){
        this.utilizadores = new HashMap<>();
        this.pois = new HashMap<>();
        this.reviews = new HashMap<>();
        this.addUtilizador("miguel","m@gmail.com","12345",1);
    }

    @Override
    public int addUtilizador(String username, String email, String password, int permissao) {
        int userID = -1;
        int id = geraIdentificadorUnico(this.utilizadores);
        Utilizador user = new Utilizador(id,username,email,password,permissao);
        utilizadores.put(String.valueOf(id),user);
        userID = id;
        return userID;
    }

    @Override
    public int nivelDePermissao(String userID) {
        int permissao = -1;
        if(utilizadores.containsKey(userID)){
            permissao = utilizadores.get(userID).getPermissao();
        }
        return permissao;
    }

    @Override
    public boolean verificaBloqueado(String userID) {
        boolean bloqueado = false;
        if(utilizadores.containsKey(Integer.valueOf(userID))){
            bloqueado = utilizadores.get(Integer.valueOf(userID)).isBloqueado();
        }
        return bloqueado;
    }

    @Override
    public boolean bloqUser(String userID) {
        if(utilizadores.containsKey(Integer.valueOf(userID))){
            utilizadores.get(Integer.valueOf(userID)).setBloqueado(false);
            return true;
        }
        return false;
    }

    @Override
    public String verificaLogin(String email, String password) {
        Utilizador user = getutilizadorByEmail(email);
        if(user != null && user.getPassword().equals(password)) return String.valueOf(user.getUserID());
        else return null;
    }

    @Override
    public String recuperaPassword(String email) {
        EmailHandler emailHandler = new EmailHandler();
        Utilizador user = getutilizadorByEmail(email);
        if(user != null){
            emailHandler.enviaPassword(email,user.getPassword());
            return "200";
        }
        return "404";
    }

    @Override
    public boolean removeUtilizador(String userID) {
        boolean userExistia = false;
        if(this.utilizadores.containsKey(userID)){
            this.utilizadores.remove(userID);
            userExistia = true;
        }
        return userExistia;
    }

    @Override
    public boolean alteraGosto(String userID, String reviewID) {
        if(this.reviews.containsKey(reviewID)){
            Review review = this.reviews.get(reviewID);
            if(this.utilizadores.containsKey(userID)) {
                Utilizador u = this.utilizadores.get(userID);
                if (u.getReviewsGostadas().contains(reviewID)) {
                    review.decrementaGosto();
                    u.removeReviewGostada(reviewID);
                } else {
                    review.incrementaGosto();
                    u.addReviewGostada(reviewID);
                }
                return true;
            } return false;
        }
        return false;
    }

    @Override
    public boolean verificaGosto(String reviewID, String userID) {
        if(this.reviews.containsKey(reviewID)){
            Review review = this.reviews.get(reviewID);
            if(this.utilizadores.containsKey(userID)) {
                Utilizador u = this.utilizadores.get(userID);
                if (u.getReviewsGostadas().contains(reviewID)) {
                    return true;
                } else {
                    return false;
                }
            } return false;
        }
        return false;
    }

    @Override
    public boolean alteraReport(String UserID, String ReviewID) {

        return false;
    }

    @Override
    public boolean verificaReport(String ReviewID, String UserID) {
        return false;
    }

    @Override
    public List<Utilizador> getUtilizadores(String searchInput) {
        return null;
    }

    @Override
    public boolean atualizaUtilizador(String userID, String username, String email, String password) {
        return false;
    }

    @Override
    public String getAutorReview(String reviewID) {
        return null;
    }

    @Override
    public boolean addReview(String comentario, String classificacao, String idPontoInteresse, String userID) {
        return false;
    }

    @Override
    public boolean alteraReview(String comentario, int classificacao, String reviewID) {
        return false;
    }

    @Override
    public boolean alteraPontoInteresseGuardado(String idPI, String userID) {
        return false;
    }

    @Override
    public boolean removeUserReviews(String userID) {
        return false;
    }

    @Override
    public boolean removeReports(String reviewID) {
        return false;
    }

    @Override
    public boolean removeReview(String reviewID) {
        return false;
    }

    @Override
    public Utilizador loadUtilizador(String UserID) {
        return null;
    }

    @Override
    public List<ReportedReview> retrieveReports() {
        return null;
    }

    @Override
    public List<Review> getPontoInteresseReviews(String idPontoInteresse) {
        return null;
    }

    @Override
    public Review loadReview(String reviewID) {
        return null;
    }

    @Override
    public boolean verificaPontoInteresseGuardado(String userID, String idPI) {
        return false;
    }

    @Override
    public boolean guardarPontoInteresse(String UserID, String idPI) {
        return false;
    }

    @Override
    public Review getReview(String reviewID) {
        return null;
    }

    @Override
    public PontoDeInteresse getPontoInteresse(String idPontoInteresse) {
        return null;
    }

    @Override
    public Utilizador getUtilizador(String userID) {
        System.out.println("userID get: " + userID);
        return this.utilizadores.get(userID);
    }

    public Utilizador getutilizadorByEmail(String email) {
        for (Map.Entry<String,Utilizador> entry: this.utilizadores.entrySet()) {
            if(entry.getValue().getEmail().equals(email)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int getClassificacaoReview(String ReviewID) {
        return 0;
    }

    /**
     * Gera um identificador de 8 caracteres único
     * @param m - Map onde se pretende criar um id unico
     * @return id gerado
     */
    public int geraIdentificadorUnico(Map m){
        //Gerar um identificador aleatório
        int max = 999999;
        int min = 0;
        int id;
        do {
            id = (int)Math.floor(Math.random()*(max-min+1)+min);
        } while (m.containsKey(id));
        return id;
    }

    @Override
    public String toString() {
        return "DBTest{" +
                "utilizadores=" + utilizadores +
                ", pois=" + pois +
                ", reviews=" + reviews +
                '}';
    }
}

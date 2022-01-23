package DataLayer;

import Model.*;

import java.util.*;

public class DBTest implements IBDHandler{
    Map<String, Utilizador> utilizadores;
    Map<String, PontoDeInteresse> pois;
    Map<String, Review> reviews;

    public DBTest(){
        this.utilizadores = new HashMap<>();
        this.pois = new HashMap<>();
        this.reviews = new HashMap<>();
        this.addUtilizador("miguel","m@gmail.com","12345678",1);
        //PontoDeInteresse poi = new PontoDeInteresse("1","Bom Jesus",
                //"O Santuário do Bom Jesus do Monte, também referido como Santuário do Bom Jesus de Braga, localiza-se na freguesia de Tenões, na cidade, concelho e distrito de Braga, em Portugal. Fica situado nas proximidades do Santuário de Nossa Senhora do Sameiro.\n" +
                  //      "\n" +
                    //    "Este santuário católico dedicado ao Senhor Bom Jesus constitui-se num conjunto arquitetónico-paisagístico integrado por uma igreja, um escadório onde se desenvolve a Via Sacra do Bom Jesus, uma área de mata (Parque do Bom Jesus), alguns hotéis e um funicular (Elevador do Bom Jesus).","https://tastebraga.com/wp-content/uploads/2017/11/sugest%C3%B5es-6-2.png","Braga",3.5f);
        //PontoDeInteresse poi2 = new PontoDeInteresse("2","Theatro Circo","O Theatro Circo é um teatro que se localiza na Avenida da Liberdade n.º 697, na freguesia de São Lázaro, cidade e município de Braga, do distrito homónimo, em Portugal.","https://www.cm-braga.pt/archive/cache/img/sz800x600/CMB16012014SERGIOFREITAS0000034.JPG"," Avenida da Liberdade n.º 697",4.5f);
        //this.pois.put("1",poi);
        //this.pois.put("2",poi2);
        APIHandler apiHandler = new APIHandler();
        apiHandler.localizautilizador();
        List<PontoDeInteresse> pontoDeInteresseList = apiHandler.getPontosDeInteresse("café");
        for (PontoDeInteresse pontoInteresse : pontoDeInteresseList) {
            this.pois.put(pontoInteresse.getIdPontoInteresse(),pontoInteresse);
        }
    }

    public Map<String,PontoDeInteresse> getPontosDeInteresse(){
        return this.pois;
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
    public boolean verificaAvaliou(String piID, String userID) {
        boolean jaAvaliou = false;
        for (Map.Entry<String,Review> entry: this.reviews.entrySet()) {
            if(entry.getValue().getPiID().equals(piID) && entry.getValue().getUserID().equals(userID)){
                jaAvaliou = true;
            }
        }
        return jaAvaliou;
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
        Review review = new Review(String.valueOf(geraIdentificadorUnico(this.reviews)),userID,idPontoInteresse,comentario,Float.valueOf(classificacao),0);
        this.reviews.put(review.getReviewID(),review);
        System.out.println("Adicionada review á base de dados: " + this.reviews.toString());
        return true;
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
        List<Review> reviewList = new ArrayList<>();
        for (Map.Entry<String,Review> entry: this.reviews.entrySet()){
            if(entry.getValue().getPiID().equals(idPontoInteresse)){
                reviewList.add(entry.getValue());
            }
        }
        return reviewList;
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
        return this.pois.get(idPontoInteresse);
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

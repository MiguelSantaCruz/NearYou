package DataLayer;

import DataBase.*;
import Model.PontoDeInteresse;
import Model.ReportedReview;
import Model.Review;
import Model.Utilizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BDHandler implements IBDHandler{
    @Override
    public int addUtilizador(String username, String email, String password, int permissao, boolean bloqueado) {
        Utilizador utilizador = new Utilizador(email.hashCode(),username,email,password,permissao,bloqueado);
        int id = UtilizadorDAO.add_user(utilizador);
        Utilizador u = UtilizadorDAO.get_by_email(email);
        u.setUserID(id);
        UtilizadorDAO.update(u);
        return id;
    }

    @Override
    public boolean verificaBloqueado(int userID) {
        if (UtilizadorDAO.exists_by_id(userID)) {
            Utilizador u = UtilizadorDAO.get_by_id(userID);
            return u.isBloqueado();
        }
        else return false;
    }

    @Override
    public boolean bloqUser(int userID) {
        if (UtilizadorDAO.exists_by_id(userID)) {
            ReviewDAO.delete_all_reviews_user(userID);
            Utilizador u = UtilizadorDAO.get_by_id(userID);
            u.setBloqueado(true);
            UtilizadorDAO.bloqUser(u);
            return true;
        }else return false;
    }

    @Override
    public String verificaLogin(String email, String password) {
        if (UtilizadorDAO.exists_by_email(email)) {
            Utilizador u = UtilizadorDAO.get_by_email(email);
            System.out.println("Utilizador recebido login: " + u.toString());
            if (u.getPassword().equals(password))
                return String.valueOf(u.getUserID());
        }
        return null;
    }

    @Override
    public String recuperaPassword(String email) {
        if (UtilizadorDAO.exists_by_email(email)) {
            Utilizador u = UtilizadorDAO.get_by_email(email);
            return u.getPassword();
        } else return null;
    }



    @Override
    public boolean removeUtilizador(int userID) {
        if (UtilizadorDAO.exists_by_id(userID)) {
            UtilizadorDAO.delete(userID);
            return true;
        }
        else return false;
    }

    @Override
    public boolean alteraGosto(int userID, int reviewID) {
        boolean jaAvaliou = false;
        if(UtilizadorDAO.exists_by_id(userID)) {
            System.out.println("User existia");
            Utilizador utilizador = UtilizadorDAO.get_by_id(userID);
            if (ReviewDAO.exists_by_id(reviewID)) {
                System.out.println("review existia");
                Review review = ReviewDAO.get_by_id(reviewID);
                System.out.println("review: " + review.toString());
                int like = LikedReviewDAO.get_like_by_user(utilizador.getUserID(), Integer.valueOf(review.getReviewID()));
                if (like == -1) {
                    LikedReviewDAO.add_like(reviewID, userID);
                } else {
                    LikedReviewDAO.delete(like);
                }
            }
        }
        return false;
    }

    @Override
    public boolean verificaGosto(int reviewID, int userID) {
        boolean reviewGostada = false;
        if(UtilizadorDAO.exists_by_id(userID)) {
            Utilizador utilizador = UtilizadorDAO.get_by_id(userID);
            if (ReviewDAO.exists_by_id(reviewID)) {
                Review review = ReviewDAO.get_by_id(reviewID);
                int like = LikedReviewDAO.get_like_by_user(utilizador.getUserID(), Integer.valueOf(review.getReviewID()));
                if (like != -1) {
                    reviewGostada = true;
                }
            }
        }
        return reviewGostada;
    }

    @Override
    public boolean verificaAvaliou(int piID, int userID) {
        boolean avaliou = false;
        if(UtilizadorDAO.exists_by_id(userID)){
            Utilizador utilizador = UtilizadorDAO.get_by_id(userID);
            List<String> reviewList = utilizador.getReviewsGostadas();
            for (String reviewID : reviewList) {
                Review review = ReviewDAO.get_by_id(Integer.valueOf(reviewID));
                if(review != null){
                    if(Integer.valueOf(review.getPiID()) == piID) return true;
                }
            }

        }
        return avaliou;
    }

    @Override
    public List<Utilizador> getUtilizadores(String searchInput) {
        List<Utilizador> users = UtilizadorDAO.get_all();
        List<Utilizador> userWithUserName = new ArrayList<>();
        for (Utilizador user: users) {
            if(user.getUserName().contains(searchInput)){
                userWithUserName.add(user);
            }
        }
        return userWithUserName;
    }

    @Override
    public boolean atualizaUtilizador(int userID, String username, String email, String password,int permissao) {
        Utilizador u = new Utilizador(userID, username,email,password,permissao,false);
        if (UtilizadorDAO.update(u) == 1) return true;
        else return false;
    }

    @Override
    public int getAutorReview(int reviewID) {
        if (ReviewDAO.exists_by_id(reviewID)) {
            Review r = ReviewDAO.get_by_id(reviewID);
            return Integer.valueOf(r.getUserID());
        }
        else return -1;
    }

    @Override
    public boolean addReview(String comentario, int nrgostos, int idPontoInteresse, int userID,int classificacao) {
        Review rev = new Review(String.valueOf(idPontoInteresse+userID),String.valueOf(userID),String.valueOf(idPontoInteresse),comentario,nrgostos,classificacao);
        boolean r = ReviewDAO.add_review(rev);
        return r;
    }

    @Override
    public  boolean alteraReview(String comentario, int nrgostos, int reviewID, int user_id,int classificacao) {
        if (ReviewDAO.exists_by_id(reviewID)) {
            Review r = ReviewDAO.get_by_id(reviewID);
            int u_id = Integer.valueOf(r.getUserID());
            if (u_id == user_id) {
                Review rev = new Review(String.valueOf(reviewID),r.getPiID(),r.getUserID(),comentario,nrgostos,classificacao);
                ReviewDAO.update(rev);
                return true;
            }else return false;
        }return false;

    }

    @Override
    public int addPoi(PontoDeInteresse pontoDeInteresse){
        int id = PontoDeInteresseDAO.addPontInt(pontoDeInteresse);
        return id;
    }

    //falta saber
    @Override
    public boolean alteraPontoInteresseGuardado(int idPI, int userID) {
        if (UtilizadorDAO.exists_by_id(userID) && PontoDeInteresseDAO.exists_by_id(idPI)){
            if(UtilizadorPiDAO.exists_by_pi_user(idPI,userID)){
                UtilizadorPiDAO.delete(idPI);
            }
            else{
                UtilizadorPiDAO.addPI(idPI,userID);
            }
            return true;
        }
        else return false;
    }
    @Override
    public boolean guardarPontoInteresse(int userID, int idPI) {
        if (UtilizadorDAO.exists_by_id(userID) && PontoDeInteresseDAO.exists_by_id(idPI)){
            if(UtilizadorPiDAO.exists_by_pi_user(idPI,userID))
                return true;
            else {
                UtilizadorPiDAO.addPI(idPI, userID);
                return true;
            }
        }
        else return false;
    }

    @Override
    public Review getReview(int reviewID) {
        return null;
    }

    @Override
    public boolean alteraReport(int userID, int reviewID) {
        if (ReviewDAO.exists_by_id(reviewID) && UtilizadorDAO.exists_by_id(userID)){
            if (ReportedReviewDAO.exists_by_user(userID)) {
                int id_rep = ReportedReviewDAO.get_repID_by_user(userID,reviewID);
                ReportedReviewDAO.delete(id_rep);
            }
            else {
                ReportedReview rep = new ReportedReview(userID, reviewID,0);
                ReportedReviewDAO.add_rep(rep);
            }
        }
        return false;
    }

    @Override
    public boolean verificaReport(int reviewID, int userID) {
        int rep = ReportedReviewDAO.get_repID_by_user(userID,reviewID);
        if (rep != -1) return true;
        else return false;
    }

    @Override
    public List<Utilizador> getUtilizadores(int searchInput) {
        return null;
    }

    @Override
    public List<ReportedReview> retrieveReports() {
        return ReportedReviewDAO.get_all();
    }

    @Override
    public boolean removeReports(int reviewID) {
        if (ReviewDAO.exists_by_id(reviewID)){
            ReportedReviewDAO.delete_all_report_review(reviewID);
            return true;
        }
        else return false;
    }

    @Override
    public  boolean removeUserReviews(int userID) {
        if (ReviewDAO.exists_by_id_user(userID)) {
            ReviewDAO.delete_all_reviews_user(userID);
            return true;
        }return  false;
    }


    @Override
    public  boolean removeReview(int reviewID) {
        if (ReviewDAO.exists_by_id(reviewID)) {
            ReviewDAO.delete(reviewID);
            return true;
        }else return  false;
    }

    @Override
    public  Utilizador loadUtilizador(int userID) {
        if (UtilizadorDAO.exists_by_id(userID)) {
            return UtilizadorDAO.get_by_id(userID);
        }return null;
    }


    @Override
    public List<Review> getPontoInteresseReviews(int idPontoInteresse) {
        //se existe reviews com idPontoIn
        if (ReviewDAO.exists_by_id_pi(idPontoInteresse)) {
            return ReviewDAO.get_all_reviewsDoPI(idPontoInteresse);
        }else return null;
    }
    @Override
    public  Review loadReview(int reviewID) {
        if (ReviewDAO.exists_by_id(reviewID)) {
            return ReviewDAO.get_by_id(reviewID);
        }else return null;
    }

    @Override
    public  boolean verificaPontoInteresseGuardado(int userID, int idPI) {
        if (UtilizadorDAO.exists_by_id(userID)) {
            Utilizador u = UtilizadorDAO.get_by_id(userID);
            List<String> list = u.getpiGuardados();
            if (list.contains(String.valueOf(idPI))) {
                return true;
            } else return false;
        }
        else return false;
    }


    @Override
    public PontoDeInteresse getPontoInteresse(int idPontoInteresse) {
        if (PontoDeInteresseDAO.exists_by_id(idPontoInteresse)) {
            return PontoDeInteresseDAO.get(idPontoInteresse);
        }else return null;
    }

    @Override
    public Utilizador getUtilizador(int userID) {
        return UtilizadorDAO.get_by_id(userID);
    }

    @Override
    public int getClassificacaoReview(int reviewID) {
        Review review = ReviewDAO.get_by_id(reviewID);
        if(review != null){
            return (int) review.getNrGostos();
        } else return 0;
    }

    @Override
    public Map<String, PontoDeInteresse> getPontosDeInteresse() {
        List<PontoDeInteresse> list = PontoDeInteresseDAO.get_all();
        Map<String,PontoDeInteresse> map = new HashMap<>();
        for (PontoDeInteresse i : list) map.put(i.getIdPontoInteresse(),i);
        return map;
    }

    @Override
    public int nivelDePermissao(int userID) {
        if (UtilizadorDAO.exists_by_id(userID)) {
            Utilizador u = UtilizadorDAO.get_by_id(userID);
            return u.getPermissao();
        }
        else return -1;
    }

}

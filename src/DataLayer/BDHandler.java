package DataLayer;

import Model.PontoDeInteresse;
import Model.ReportedReview;
import Model.Review;
import Model.Utilizador;

import java.util.List;

public class BDHandler implements IBDHandler {
    @Override
    public int addUtilizador(String username, String email, String password, int permissao) {
        return 0;
    }

    @Override
    public int nivelDePermissao(String UserID) {
        return 0;
    }

    @Override
    public boolean verificaBloqueado(String userID) {
        return false;
    }

    @Override
    public boolean bloqUser(String userID) {
        return false;
    }

    @Override
    public String verificaLogin(String email, String password) {
        return null;
    }

    @Override
    public String recuperaPassword(String email) {
        return null;
    }

    @Override
    public boolean removeUtilizador(String userID) {
        return false;
    }

    @Override
    public boolean alteraGosto(String userID, String reviewID) {
        return false;
    }

    @Override
    public boolean verificaGosto(String reviewID, String userID) {
        return false;
    }

    @Override
    public boolean alteraReport(String UserID, String ReviewilD) {
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
    public Utilizador getUtilizador(String UserID) {
        return null;
    }

    @Override
    public int getClassificacaoReview(String ReviewID) {
        return 0;
    }
}

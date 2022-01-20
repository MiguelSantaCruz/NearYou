package DataLayer;
import Model.*;

import java.util.List;

public interface IBDHandler {

    /***
     * Adicionar um utilizador
     * @param username O nome de utilizador
     * @param email O email do utilizador
     * @param password A password do utilizador
     * @param permissao 1 caso utilizador normal, 2 se moderador, 3 se administrador
     * @return O id do utilizador
     */
    int addUtilizador(String username, String email, String password, int permissao);

    int nivelDePermissao(String UserID);

    boolean verificaBloqueado(String userID);

    boolean bloqUser(String userID);

    String verificaLogin(String email, String password);

    String recuperaPassword(String email);

    /***
     * Remove um utilizador da base de da   dos
     * @param userID O nome de utilizador
     * @return {@code true} se existia e foi removido, {@code false} caso não existisse
     */
    boolean removeUtilizador(String userID);

    boolean alteraGosto(String userID, String reviewID);

    boolean verificaGosto(String reviewID, String userID);

    boolean alteraReport(String UserID, String ReviewilD);

    boolean verificaReport(String ReviewID, String UserID);

    List<Utilizador> getUtilizadores(String searchInput);

    boolean atualizaUtilizador(String userID, String username, String email, String password);

    String getAutorReview(String reviewID);

    boolean addReview(String comentario, String classificacao, String idPontoInteresse, String userID);

    boolean alteraReview(String comentario, int classificacao, String reviewID);

    boolean alteraPontoInteresseGuardado(String idPI, String userID);

    boolean removeUserReviews(String userID);

    boolean removeReports(String reviewID);

    boolean removeReview(String reviewID);

    Utilizador loadUtilizador(String UserID);

    List<ReportedReview> retrieveReports();

    List<Review> getPontoInteresseReviews(String idPontoInteresse);

    Review loadReview(String reviewID);

    boolean verificaPontoInteresseGuardado(String userID, String idPI);

    boolean guardarPontoInteresse(String UserID, String idPI);

    Review getReview(String reviewID);

    PontoDeInteresse getPontoInteresse(String idPontoInteresse);

    Utilizador getutilizador(String UserID);

    int getClassificacaoReview(String ReviewID);
}

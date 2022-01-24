package DataLayer;
import Model.*;

import java.util.List;
import java.util.Map;

public interface IBDHandler {

    /***
     * Adicionar um utilizador
     * @param username O nome de utilizador
     * @param email O email do utilizador
     * @param password A password do utilizador
     * @param permissao 1 caso utilizador normal, 2 se moderador, 3 se administrador
     * @return O id do utilizador, -1 se erro
     */
    int addUtilizador(String username, String email, String password, int permissao, boolean bloqueado);

    /**
     * Verifica um nivel de permissao de um determindado utilizador
     *
     * @return 1 caso utilizador normal, 2 se moderador, 3 se administrador, -1 caso contrário
     */
    int nivelDePermissao(int UserID);

    /**
     * Verifica se um determinado utilizador está bloqueado
     *
     * @return true caso o utilizador esteja bloqueado, false caso contrário
     */
    boolean verificaBloqueado(int userID);

    boolean bloqUser(int userID);

    /**
     * Procura um entrada de utilizador na base de dados cujo email e a palavra-
     * passe correspondam aos fornecidos.
     *
     * @returns Em caso de sucesso, retorna o id de utilizador. Caso contrário, retorna ’null’
     */
    String verificaLogin(String email, String password);

    String recuperaPassword(String email);

    /***
     * UI.Remove um utilizador da base de da   dos
     * @param userID O nome de utilizador
     * @return {@code true} se existia e foi removido, {@code false} caso não existisse
     */
    boolean removeUtilizador(int userID);

    boolean alteraGosto(int userID, int reviewID);

    boolean verificaGosto(int reviewID, int userID);

    boolean verificaAvaliou(int piID, int userID);

    boolean alteraReport(int UserID, int ReviewID);

    boolean verificaReport(int ReviewID, int UserID);

    List<Utilizador> getUtilizadores(int searchInput);

    public boolean atualizaUtilizador(int userID, String username, String email, String password, int permissao);

    int getAutorReview(int reviewID);

    public boolean addReview(String comentario, int nrgostos, int idPontoInteresse, int userID,int classificacao);

    boolean alteraReview(String comentario, int nrgostos, int reviewID, int user_id,int classificacao);

    int addPoi(PontoDeInteresse pontoDeInteresse);

    boolean alteraPontoInteresseGuardado(int idPI, int userID);

    boolean removeUserReviews(int userID);

    boolean removeReports(int reviewID);

    boolean removeReview(int reviewID);

    Utilizador loadUtilizador(int UserID);

    List<ReportedReview> retrieveReports();

    List<Review> getPontoInteresseReviews(int idPontoInteresse);

    Review loadReview(int reviewID);

    boolean verificaPontoInteresseGuardado(int userID, int idPI);

    boolean guardarPontoInteresse(int UserID, int idPI);

    Review getReview(int reviewID);

    PontoDeInteresse getPontoInteresse(int idPontoInteresse);

    Utilizador getUtilizador(int UserID);

    int getClassificacaoReview(int ReviewID);

    public Map<String, PontoDeInteresse> getPontosDeInteresse();

    public List<Utilizador> getUtilizadores(String searchInput);
}
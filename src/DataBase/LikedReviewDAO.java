package DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikedReviewDAO {
    private static final String DELETE = "DELETE FROM LikedReviews WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM LikedReviews WHERE review_id=?";
    private static final String FIND_ALL = "SELECT * FROM LikedReviews";
    private static final String REP_NUMBER = "SELECT * FROM LikedReviews WHERE review_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM LikedReviews WHERE id=?";
    private static final String FIND_BY_USER_REV= "SELECT * FROM LikedReviews WHERE utilizador_id=? and review_id=?";
    private static final String INSERT = "INSERT INTO LikedReviews(utilizador_id,review_id) VALUES(?, ?)";


    //Este método devolve o número de entradas na tabela com id_review
    public static int getLikesNumber(int id) {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement stm = conn.prepareStatement(REP_NUMBER)){
             stm.setInt(1,id);
             ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                     i++;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }



    public static boolean exists_by_user(int u_id) {
        boolean r = false;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_USER_REV);
            stm.setInt(1, u_id);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Dado um userID e uma reviewID verifica se um ultilizador deu like e devolve o id do like
     * @param userID
     * @param reviewID
     * @return
     */
    public static int get_like_by_user(int userID,int reviewID) {
        int rep=-1;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_USER_REV);
            stm.setInt(1, userID);
            stm.setInt(2, reviewID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                rep = rs.getInt("id");
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
        }

        return rep;
    }


    // //Adiconar entrada à tabela dos Utilizadores na base de dados
    // //É lançada exceção caso haja algum problema relativo á database
    public static boolean add_like(int reviewID,int userID) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, userID);
            stm.setInt(2, reviewID);
            stm.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException s) {
            // erro ao inserir user reptido
            r = false;
        } catch (SQLException e) {
            r = false;
            // Database error!
            e.printStackTrace();
        }
        return r ;
    }

    public static void delete(int id) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

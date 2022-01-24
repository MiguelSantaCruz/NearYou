package DataBase;
import Model.PontoDeInteresse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorPiDAO {
    private static final String DELETE = "DELETE FROM Utilizador_PontoDeInteresse WHERE ponto_de_interesse_id=?";
    private static final String DELETE_ALL = "DELETE * FROM Utilizador_PontoDeInteresse WHERE ponto_de_interesse_id=?";
    private static final String FIND_ALL = "SELECT * FROM Utilizador_PontoDeInteresse";
    private static final String REP_NUMBER = "SELECT * FROM Utilizador_PontoDeInteresse WHERE ponto_de_interesse_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Utilizador_PontoDeInteresse WHERE id=?";
    private static final String FIND_BY_USER_REV= "SELECT * FROM Utilizador_PontoDeInteresse WHERE utilizador_id=? and ponto_de_interesse_id=?";
    private static final String GET_ALL_BY_USER = "SELECT * FROM Utilizador_PontoDeInteresse WHERE utilizador_id=?";
    private static final String INSERT = "INSERT INTO Utilizador_PontoDeInteresse(utilizador_id,ponto_de_interesse_id) VALUES(?, ?)";


    //Este método devolve o número de entradas na tabela com id_review
    public static int getPiNumber(int id) {
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

    public static boolean exists_by_pi_user(int pont,int user_id) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_USER_REV);
            stm.setInt(1, user_id);
            stm.setInt(2,pont);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
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

    public static int get_id_by_user(int userID,int piID) {
        int rep=-1;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_USER_REV);
            stm.setInt(1, userID);
            stm.setInt(2, piID);
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
    public static boolean addPI(int piID,int userID) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, userID);
            stm.setInt(2, piID);
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

    public static List<PontoDeInteresse> get_All_Pis_Users(int userID) {
        List<PontoDeInteresse> pois = new ArrayList<>();
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(GET_ALL_BY_USER);
            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {  // A chave existe na tabela
                PontoDeInteresse pontoDeInteresse = PontoDeInteresseDAO.get(rs.getInt(3));
                if(pontoDeInteresse != null) pois.add(pontoDeInteresse);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
        }
        return pois;
    }

    public static void delete(int idPOI) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, idPOI);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

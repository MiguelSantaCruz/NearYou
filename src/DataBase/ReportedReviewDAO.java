package DataBase;

import Model.ReportedReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportedReviewDAO {
    private static final String DELETE = "DELETE FROM ReportedReview WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Review WHERE review_id=?";
    private static final String FIND_ALL = "SELECT * FROM ReportedReview";
    private static final String REP_NUMBER = "SELECT count(*) FROM ReportedReview WHERE review_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM ReportedReview WHERE id=?";
    private static final String FIND_BY_USER_REV= "SELECT * FROM ReportedReview WHERE utilizador_id=?, review_id=?";
    private static final String INSERT = "INSERT INTO ReportedReview(utilizador_id,review_id) VALUES(?, ?)";


    //Este método devolve o número de entradas na tabela com id_review
    public static int getRepNumber(int id) {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement stm = conn.prepareStatement(REP_NUMBER)){
             stm.setInt(1,id);
             try(ResultSet rs = stm.executeQuery(REP_NUMBER)) {
                 if (rs.next()) {
                     i = rs.getInt(1);
                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }


/*
    //Método que devolve se o código de um dado Utilizador se encontra registado na base de dados
    //Lança exceção caso ocorra algum problema na procura.
    public static boolean exists_by_id(int id) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }*/

    public static boolean exists_by_user(int u_id) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_USER_REV);
            stm.setInt(1, u_id);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public static int get_repID_by_user(int u_id,int rev_id) {
        int rep=-1;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_USER_REV);
            stm.setInt(1, u_id);
            stm.setInt(2, rev_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                rep = rs.getInt("id");
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return rep;
    }

    //Método que devolve a ReportedReview cujo id é o passado como argumento
    //Lança exceção caso haja algum problema na procura na base de dados
    public static ReportedReview get_by_id(int id) {
        ReportedReview a = null;
        try {Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                //numero de reports da review
                int rev_id = rs.getInt("review_id");
                int numReports = getRepNumber(rev_id);
                a = new ReportedReview(rs.getInt("id"),
                        rs.getInt("utilizador_id"),
                       rev_id,
                        numReports);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }

    // //Adiconar entrada à tabela dos Utilizadores na base de dados
    // //É lançada exceção caso haja algum problema relativo á database
    public static boolean add_rep(ReportedReview rep) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, rep.getUserID());
            stm.setInt(2, rep.getReviewID());
            stm.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException s) {
            // erro ao inserir user reptido
            r = false;
        } catch (SQLException e) {
            r = false;
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r ;
    }




    public static List<ReportedReview> get_all() {
        List<ReportedReview> list = new ArrayList<ReportedReview>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                int rev_id = rs.getInt("review_id");
                int numReports = getRepNumber(rev_id);
                list.add(new ReportedReview(
                        rs.getInt("id"),
                        rs.getInt("utilizador_id"),
                        rev_id,
                        numReports
                ));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return list;
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
//delete todos os reports de uma review
    public static void delete_all_report_review(int id) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE_ALL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

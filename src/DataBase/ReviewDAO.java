package DataBase;

import Model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO{
    private static final String DELETE = "DELETE FROM Review WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Review WHERE utilizador_id=?";
    private static final String FIND_ALL = "SELECT * FROM Review";
    private static final String FIND_ALL_PI = "SELECT * FROM Review WHERE ponto_de_interesse=?";
    private static final String FIND_ALL_USER = "SELECT * FROM Review WHERE utilizador_id=?";
    private static final String REVIEWS_NUMBER = "SELECT count(*) FROM Review";
    private static final String FIND_BY_ID = "SELECT * FROM Review WHERE id=?";
    private static final String FIND_BY_ID_USER = "SELECT * FROM Review WHERE utilizador_id=?";
    private static final String FIND_BY_ID_PI = "SELECT * FROM Review WHERE ponto_de_interesse=?";
    private static final String INSERT = "INSERT INTO Review(ponto_de_interesse, utilizador_id, comentario, classificacao, nrgostos) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Review SET ponto_de_interesse=?, utilizador_id=?, comentario=?, classificacao=?, nrgostos=? WHERE id=?";


    //Este método devolve o número de entradas na tabela na base de dados
    /*public int getReviewsNumber() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(REVIEWS_NUMBER)) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }


    //Método que devolve se o código de um dado Utilizador se encontra registado na base de dados
    //Lança exceção caso ocorra algum problema na procura.
    public boolean exists(Review re) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, re.getReviewID());

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }*/

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
    }

    //Método que devolve se o código de um dado Utilizador se encontra registado na base de dados
    //Lança exceção caso ocorra algum problema na procura.
    public static boolean exists_by_id_user(int u_id) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID_USER);
            stm.setInt(1, u_id);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    //Método que devolve se o código de um dado Utilizador se encontra registado na base de dados
    //Lança exceção caso ocorra algum problema na procura.
    public static boolean exists_by_id_pi(int pi_id) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID_PI);
            stm.setInt(1, pi_id);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    //Método que devolve a Utilizador cujo email é o passado como argumento
    //Lança exceção caso haja algum problema na procura na base de dados
    public static Review get_by_id(int id) {
        Review a = null;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                a = new Review(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("utilizador_id")),
                        String.valueOf(rs.getInt("ponto_de_interesse")),
                        rs.getString("comentario"),
                        rs.getInt("nrgostos"),
                        rs.getInt("classificacao"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }



    // //Adiconar entrada à tabela dos Utilizadores na base de dados
    // //É lançada exceção caso haja algum problema relativo á database
    public static boolean add_review(Review re) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, Integer.valueOf(re.getPiID()));
            stm.setInt(2, Integer.valueOf(re.getUserID()));
            stm.setString(3, re.getReviewEscrita());
            stm.setInt(4, re.getClassificacao());
            stm.setInt(5, re.getNrGostos());

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
        return r;
    }
    public static List<Review> get_all() {
        List<Review> list = new ArrayList<Review>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                list.add(new Review(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("utilizador_id")),
                        String.valueOf(rs.getInt("ponto_de_interesse")),
                        rs.getString("comentario"),
                        rs.getInt("nrgostos"),
                        rs.getInt("classificacao")));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return list;
    }

    public static List<Review> get_all_reviewsDoPI(int id) {
        List<Review> list = new ArrayList<Review>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL_PI);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Review review = new Review(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("utilizador_id")),
                        String.valueOf(rs.getInt("ponto_de_interesse")),
                        rs.getString("comentario"),
                        rs.getInt("nrgostos"),
                        rs.getInt("classificacao"));
                list.add(review);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return list;
    }

    public static List<Review> get_all_reviews_user(int id) {
        List<Review> list = new ArrayList<Review>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL_USER);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Review review = new Review(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("utilizador_id")),
                        String.valueOf(rs.getInt("ponto_de_interesse")),
                        rs.getString("comentario"),
                        rs.getInt("nrgostos"),
                        rs.getInt("classificacao"));
                list.add(review);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return list;
    }

    public static void delete_all_reviews_user(int id) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE_ALL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void update(Review rev) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(3, rev.getReviewEscrita());
            stmt.setInt(4,(int) rev.getNrGostos());
            stmt.setInt(5,rev.getNrGostos());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
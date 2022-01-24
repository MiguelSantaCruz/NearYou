package DataBase;

import Model.Utilizador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorDAO {
    private static final String DELETE = "DELETE FROM Utilizador WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Utilizador";
    //private static final String USERS_NUMBER = "SELECT count(*) FROM Utilizador";
    private static final String FIND_BY_ID = "SELECT * FROM Utilizador WHERE id=?";
    private static final String FIND_BY_EMAIL= "SELECT * FROM Utilizador WHERE email=?";
    private static final String INSERT = "INSERT INTO Utilizador(email, username, password, permissao, bloqueado) VALUES(?, ?, ?, ?,?)";
    private static final String UPDATE = "UPDATE Utilizador SET username=?, password=?, permissao=?, bloqueado=? WHERE id=?";
    private static final String UPDATE_BLOQ = "UPDATE Utilizador SET bloqueado=? WHERE id=?";

    //Este método devolve o número de entradas na tabela na base de dados
    /*public int getUsersNumber() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(USERS_NUMBER)) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
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

    public static boolean exists_by_email(String email) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_EMAIL);
            stm.setString(1, email);

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
    public static Utilizador get_by_email(String email) {
        Utilizador a = null;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement stm = conn.prepareStatement(FIND_BY_EMAIL);
             stm.setString(1, email);
             ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                a = new Utilizador(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("permissao"),
                        rs.getBoolean("bloqueado"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }
    //Método que devolve a Utilizador cujo email é o passado como argumento
    //Lança exceção caso haja algum problema na procura na base de dados
    public static Utilizador get_by_id(int id) {
        Utilizador a = null;
        try {Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                a = new Utilizador(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")    ,
                        rs.getInt("permissao"),
                        rs.getBoolean("bloqueado"));
                pontosGuardados(a);
                reviewsFeitas(a);
            }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new NullPointerException(e.getMessage());
    }
        return a;
    }

    // //Adiconar entrada à tabela dos Utilizadores na base de dados
    // //É lançada exceção caso haja algum problema relativo á database
    public static int add_user(Utilizador utilizador) {
       boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, utilizador.getEmail());
            stm.setString(2, utilizador.getUserName());
            stm.setString(3, utilizador.getPassword());
            stm.setInt(4, utilizador.getPermissao());
            stm.setBoolean(5, utilizador.isBloqueado());

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
        return get_by_email(utilizador.getEmail()).getUserID();
    }


    public static int update(Utilizador user) {
        int r=0;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getPermissao());
            stmt.setBoolean(4, user.isBloqueado());
            stmt.setInt(5,user.getUserID());

            r = stmt.executeUpdate();

        } catch (SQLException e) {
             e.printStackTrace();
        }
        return r;
    }

    public static void bloqUser (Utilizador user) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE_BLOQ);
            stmt.setBoolean(1, user.isBloqueado());
            stmt.setInt(2,user.getUserID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Utilizador> get_all() {
        List<Utilizador> list = new ArrayList<Utilizador>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Utilizador u = new Utilizador(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("permissao"),
                        rs.getBoolean("bloqueado"));
                pontosGuardados(u);
                reviewsFeitas(u);
                list.add(u);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return list;
    }



    public static void pontosGuardados(Utilizador u){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String query = "select * from Utilizador\n" +
                    "join Utilizador_PontoDeInteresse on Utilizador.id = utilizador_id\n" +
                    "join PontoDeInteresse on PontoDeInteresse.id = ponto_de_interesse_id;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                u.getpiGuardados().add(String.valueOf(id));
                System.out.println(id);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }

    public static void reviewsFeitas(Utilizador u){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String query ="select * from Utilizador\n" +
                        "join Review on Utilizador.id = utilizador_id;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                u.getpiGuardados().add(String.valueOf(id));
                System.out.println(id);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }


    //delete user com o id
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




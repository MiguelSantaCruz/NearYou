package DataBase;


import Model.PontoDeInteresse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PontoDeInteresseDAO{
    private static final String DELETE = "DELETE FROM PontoDeInteresse WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM PontoDeInteresse";
    private static final String USERS_NUMBER = "SELECT count(*) FROM PontoDeInteresse";
    private static final String FIND_BY_ID = "SELECT * FROM PontoDeInteresse WHERE id=?";
    private static final String FIND_BY_EMAIL= "SELECT * FROM PontoDeInteresse WHERE email=?";
    private static final String FIND_BY_ADDRESS= "SELECT * FROM PontoDeInteresse  WHERE endereco=?";
    private static final String INSERT = "INSERT INTO PontoDeInteresse(nome, descricao, foto, endereco, classificacao_media) VALUES(?, ?, ?, ?,?)";
    private static final String UPDATE = "UPDATE PontoDeInteresse SET nome=?, descricao=?, foto=?, endereco=?, classificacao_media=? WHERE id=?";


    //Este método devolve o número de entradas na tabela na base de dados
    public int getPontIntNumber() {
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
    }


    //Método que devolve se o código de uma dada PontoDeInteresse se encontra registada na base de dados
    //Lança exceção caso ocorra algum problema na procura.
    public boolean exists(PontoDeInteresse pont) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, Integer.valueOf(pont.getIdPontoInteresse()));

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    //Método que devolve se o código de uma dada PontoDeInteresse se encontra registada na base de dados
    //Lança exceção caso ocorra algum problema na procura.
    public static boolean exists_by_id(int pont) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, pont);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public static boolean exists_by_address(String address) {
        boolean r;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ADDRESS);
            stm.setString(1, address);

            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public static PontoDeInteresse get_by_address(String address) {
        PontoDeInteresse a = null;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ADDRESS);
            stm.setString(1, address);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                a = new PontoDeInteresse(String.valueOf(rs.getInt("id")),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("foto"),
                        rs.getString("endereco"),
                        rs.getFloat("classificacao_media"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
        }
        return a;
    }


    //Método que devolve a PontoDeInteresse cujo código é o passado como argumento
    //Lança exceção caso haja algum problema na procura na base de dados
    public static PontoDeInteresse get(int id) {
        PontoDeInteresse a = null;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                a = new PontoDeInteresse(String.valueOf(rs.getInt("id")),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("foto"),
                        rs.getString("endereco"),
                        rs.getFloat("classificacao_media"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }


    //Adiconar entrada á tabela dasPontoDeInteresses na base de dados. O código é a identificação do objeto na tabela
    //É lançada exceção caso haja algum problema relativo á database
    public static int addPontInt( PontoDeInteresse pont) {
        if(exists_by_address(pont.getEndereco())){
            return Integer.valueOf(get_by_address(pont.getEndereco()).getIdPontoInteresse());
        }
        PontoDeInteresse res = null;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            if(pont.getName() != null) stm.setString(1, pont.getName());
            else stm.setString(1, null);
            if(pont.getDescricao() != null) stm.setString(2, pont.getDescricao());
            else stm.setString(2, null);
            if(pont.getPathFoto() != null) stm.setString(3, pont.getPathFoto());
            else stm.setString(3, null);
            if(pont.getEndereco() != null) stm.setString(4, pont.getEndereco());
            else stm.setString(4, null);
            if(pont.getClassificacaoMedia() != null) stm.setFloat(5, pont.getClassificacaoMedia());
            else stm.setFloat(5, 0);
            stm.executeUpdate();
            ResultSet rs= stm.getGeneratedKeys();
            int id = -1;
            if (rs.next())
            {
                id = rs.getInt(1);
            }
            return id;

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            return -1;
        }

    }

    public static List<PontoDeInteresse> get_all() {
        List<PontoDeInteresse> list = new ArrayList<PontoDeInteresse>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                list.add(new PontoDeInteresse(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("endereco"),
                        rs.getString("foto"),
                        rs.getFloat("classificacao_media")
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

}



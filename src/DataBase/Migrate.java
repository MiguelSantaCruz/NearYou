package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrate {
    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Utilizador (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "username varchar(32) DEFAULT 'n/d'," +
                    "email varchar(320) DEFAULT 'n/d' unique," +
                    "password varchar(32) DEFAULT 'n/d'," +
                    "permissao int(5) NOT NULL DEFAULT 0," +
                    "bloqueado boolean NOT NULL DEFAULT FALSE)";

            stm.executeUpdate(sql);

            String sqlP = "CREATE TABLE IF NOT EXISTS PontoDeInteresse (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "nome varchar(64) DEFAULT 'n/d', " +
                    "descricao varchar(512) DEFAULT 'n/d', " +
                    "foto varchar(512) DEFAULT 'n/d', " +
                    "endereco varchar(512) DEFAULT 'n/d', " +
                    "classificacao_media int(10) DEFAULT 0)";
            //"Reviews varchar(32) DEFAULT 'n/d', foreign key(Reviews) references reviews(ID))";
            stm.executeUpdate(sqlP);

            String sqlR = "CREATE TABLE IF NOT EXISTS Review (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "ponto_de_interesse varchar(64) DEFAULT 'n/d'," +
                    "utilizador_id int(32) not null," +
                    "comentario varchar(300) DEFAULT 'n/d'," +
                    "classificacao int(1) DEFAULT 0," +
                    "nrgostos int(32) DEFAULT 0," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id))";
            stm.executeUpdate(sqlR);

            String sqlPU = "CREATE TABLE IF NOT EXISTS Utilizador_PontoDeInteresse (" +
                    "id int(64) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "utilizador_id int NOT NULL," +
                    "ponto_de_interesse_id int NOT NULL," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY (ponto_de_interesse_id) REFERENCES PontoDeInteresse(id) ON UPDATE CASCADE)";
            stm.executeUpdate(sqlPU);

            String sqlRepRev = "CREATE TABLE IF NOT EXISTS ReportedReview (" +
                    "id int(64) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "utilizador_id int NOT NULL," +
                    "review_id int NOT NULL," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY (review_id) REFERENCES Review(id) ON UPDATE CASCADE)";
            stm.executeUpdate(sqlRepRev);

            String sqlLikeRev = "CREATE TABLE IF NOT EXISTS LikedReviews (" +
                    "id int(64) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "utilizador_id int NOT NULL," +
                    "review_id int NOT NULL," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY (review_id) REFERENCES Review(id) ON UPDATE CASCADE)";
            stm.executeUpdate(sqlLikeRev);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}

package database;

import java.sql.*;
import others.Printes;

public class ConnectDB {

    private Connection connection;

    public ConnectDB() {

        String url = "jdbc:sqlite:testeProjetoProgramacao.db"; // Specify your database URL

        try {

            connection = DriverManager.getConnection(url);

            //System.out.println("--> CONEXÃO COM O BANCO DE DADOS FEITA COM SUCESSO.");

        } catch (SQLException e) {

            Printes.espaco();
            System.out.println("--> ERRO AO SE CONECTAR COM O BANCO DE DADOS.");
            e.printStackTrace();

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {

        try {
            if (connection != null) {

                //System.out.println("--> CONEXÃO COM O BANCO DE DADOS FECHADA.");

                connection.close();
            }
        } catch (SQLException e) {

            Printes.espaco();
            System.out.println("--> ERRO AO SE CONECTAR COM O BANCO DE DADOS.");
            e.printStackTrace();

        }

    }
}
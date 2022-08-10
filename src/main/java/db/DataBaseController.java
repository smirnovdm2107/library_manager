package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
    private final static String path = "jdbc:postgresql://localhost:5050";
    private final static String password = "";
    private final static String user = "postgres";

    private Connection conn;

    public DataBaseController() {
        try {
            conn = DriverManager.getConnection(
                    path,
                    password,
                    user
            );
            Statement statement = conn.createStatement();
            statement.executeUpdate("IF " +
                    "" +
                    "CREATE DATABASE LIBRARY")
        } catch (SQLException e) {
        }
    }
}

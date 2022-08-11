package com.example.db;

import com.example.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
    private final static String path = "jdbc:postgresql://localhost:5432/";
    private final static String password = "";
    private final static String user = "postgres";

    private Connection conn;

    public DataBaseController() {

        try {
            Class.forName("org.postgresql.Driver");
            try {
                conn = DriverManager.getConnection(
                        path,
                        user,
                        password

                );
            } catch(SQLException e) {
                System.out.print(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {

    }
    public void checkOrCreateTable(String name) {
        try(Statement statement = conn.createStatement()) {
            statement.executeUpdate(String.format("IF OBJECT_ID('%s') IS NOT NULL\n" +
                    "CREATE TABLE '%s'", name));
        }catch (SQLException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

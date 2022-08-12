package com.example.db;

import com.example.User;

import java.sql.*;

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
                try(Statement statement = conn.createStatement()) {
                    ResultSet resultSet = statement.executeQuery("SELECT datname FROM pg_database");
                    String dbName = resultSet.getString("datname");

                    while(resultSet.next()) {
                        if (dbName.equals("library"))
                    }
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {

    }
    private void checkOrCreateTable(String tableName, String[] columns) {
        try(Statement statement = conn.createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("CREATE TABLE IF NOT EXISTS %S(\n", tableName));
        }catch (SQLException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

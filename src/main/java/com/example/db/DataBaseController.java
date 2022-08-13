package com.example.db;

import com.example.User;

import java.sql.*;
import java.util.Objects;

public class DataBaseController {
    private final static String path = "jdbc:postgresql://localhost:5432/library";
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
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean addUser(User user) {
        Objects.requireNonNull(user);
        try(Statement statement = conn.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(String.format("SELECT user_id FROM users " +
                    "WHERE users.login='%s';", user.getLogin()));) {
                if (resultSet.next()) {
                    return false;
                }
            } catch (SQLException e) {
                System.out.print(e.getMessage());
                throw new RuntimeException(e);
            }
            statement.executeUpdate(String.format(
                    "INSERT INTO users (name, surname, login, password) " +
                            "VALUES ('%s', '%s', '%s', '%s');",
                    user.getName(), user.getSurname(), user.getLogin(), user.getPassword()
            ));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

    public User findUser(String login, String password) {
        User resultUser = null;
        try(Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM users " +
                            "WHERE users.login='%s' AND users.password='%s';", login, password));
            if (resultSet.next()) {
                resultUser = new User(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return resultUser;
    }
    public static void checkDataBase() {

        try {
            Class.forName("org.postgresql.Driver");
            try {
                Connection connection = DriverManager.getConnection(
                        path,
                        user,
                        password

                );
                try(Statement statement = connection.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                            "user_id serial PRIMARY KEY, " +
                            "name varchar(64) NOT NULL, " +
                            "surname varchar(64) NOT NULL, " +
                            "login varchar(64) UNIQUE NOT NULL, " +
                            "password varchar(64) NOT NULL" +
                            ");");
                } catch (SQLException e) {
                    System.out.print(e.getMessage());
                    throw new RuntimeException(e);
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
    private void checkOrCreateTable(String tableName, String[] columns) {
        try(Statement statement = conn.createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("CREATE TABLE IF NOT EXISTS %S(\n", tableName));
        }catch (SQLException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}

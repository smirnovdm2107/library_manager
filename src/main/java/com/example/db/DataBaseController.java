package com.example.db;

import com.example.User;
import javafx.scene.control.PasswordField;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DataBaseController {
    private final static String path = "jdbc:postgresql://localhost:5432/library";
    private final static String password = "";
    private final static String user = "postgres";

    private final Connection conn;

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

    public List<String> getBooksAndAuthors() {
        List<String> results = new ArrayList<>();
        try(Statement statement = conn.createStatement()) {
            try(ResultSet resultBooks = statement.executeQuery(
                    "SELECT title FROM books;");
                ) {
                while (resultBooks.next()) {
                results.add(String.format(resultBooks.getString("title")));
                }
            } catch (SQLException e) {
                System.out.println("problem with fetching books: " + e.getMessage());
                throw new RuntimeException(e);
            }
            try(ResultSet resultAuthors = statement.executeQuery(
                    "SELECT name, surname FROM authors;"
            )) {
                while (resultAuthors.next()) {
                    results.add(String.format("%s %s",
                            resultAuthors.getString("name"), resultAuthors.getString("surname")));
                }
            } catch (SQLException e) {
                System.out.println("problem with fetching authors: " + e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return results;
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
                            "user_id SERIAL PRIMARY KEY, " +
                            "name varchar(64) NOT NULL, " +
                            "surname varchar(64) NOT NULL, " +
                            "login varchar(64) UNIQUE NOT NULL, " +
                            "password varchar(64) NOT NULL" +
                            ");");
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS authors (" +
                            "author_id SERIAL PRIMARY KEY, " +
                            "name varchar(64)," +
                            "surname varchar(64), " +
                            "description text "+
                            ");");
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                            "book_id SERIAL PRIMARY KEY, " +
                            "title varchar(128) NOT NULL, " +
                            "fk_author_id INT, " +
                            "annotation text," +
                            "FOREIGN KEY(fk_author_id) REFERENCES authors(author_id)" +
                            ");");
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders(" +
                            "order_id SERIAL PRIMARY KEY, " +
                            "book_id INT, " +
                            "order_time TIMESTAMP, " +
                            "order_type BOOLEAN " +
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

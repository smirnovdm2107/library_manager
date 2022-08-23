package com.example.db;

import com.example.user.User;
import com.example.user.UserOrder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBController {
    private final static String LIBRARY_DB_PATH = "jdbc:postgresql://localhost:5432/library";
    private final static String USER = "postgres";
    private final static String PASSWORD = "";

    private final static String DRIVER_NAME = "org.postgresql.Driver";
    private final Connection conn;

    private final static String USER_TABLE_NAME = "users";
    private final static String ORDER_TABLE_NAME = "orders";
    private final static String AUTHOR_TABLE_NAME = "authors";
    private final static String BOOK_TABLE_NAME = "books";

    public DBController() {

        try {
            Class.forName(DRIVER_NAME);
            try {
                conn = DriverManager.getConnection(
                        LIBRARY_DB_PATH,
                        USER,
                        PASSWORD
                );
            } catch (SQLException e) {
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
        try (Statement statement = conn.createStatement()) {
            try (ResultSet resultBooks = statement.executeQuery(
                    "SELECT title FROM books;");
            ) {
                while (resultBooks.next()) {
                    results.add(String.format(resultBooks.getString("title")));
                }
            } catch (SQLException e) {
                System.out.println("problem with fetching books: " + e.getMessage());
                throw new RuntimeException(e);
            }
            try (ResultSet resultAuthors = statement.executeQuery(
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
        try (Statement statement = conn.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(String.format("SELECT user_id FROM users " +
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

    public Order[] getUserOrders(User user) {
        Objects.requireNonNull(user);
        try (Statement statement = conn.createStatement()) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Order[0];
    }

    public void addOrder(Order order) {
        Objects.requireNonNull(order);
        executeUpdate("INSERT INTO " + ORDER_TABLE_NAME +
                " ( fk_user_id, fk_book_id, order_time, order_type) " +
                "VALUES ( " );
    }

    public User findUser(String login, String password) {
        User resultUser = null;
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM users " +
                            "WHERE users.login='%s' AND users.password='%s';", login, password));
            if (resultSet.next()) {
                resultUser = new User(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("surname")
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
                        LIBRARY_DB_PATH,
                        USER,
                        PASSWORD

                );
                executeUpdate(connection,
                        "CREATE TABLE IF NOT EXISTS users (" +
                                "user_id SERIAL PRIMARY KEY, " +
                                "name varchar(64) NOT NULL, " +
                                "surname varchar(64) NOT NULL, " +
                                "login varchar(64) UNIQUE NOT NULL, " +
                                "password varchar(64) NOT NULL" +
                                ");",

                        "CREATE TABLE IF NOT EXISTS authors (" +
                                "author_id SERIAL PRIMARY KEY, " +
                                "name varchar(64)," +
                                "surname varchar(64), " +
                                "description text " +
                                ");",

                        "CREATE TABLE IF NOT EXISTS books (" +
                                "book_id SERIAL PRIMARY KEY, " +
                                "title varchar(128) NOT NULL, " +
                                "fk_author_id INT, " +
                                "annotation text," +
                                "FOREIGN KEY(fk_author_id) REFERENCES authors(author_id)" +
                                ");",

                        "CREATE TABLE IF NOT EXISTS orders(" +
                                "order_id SERIAL PRIMARY KEY, " +
                                "fk_user_id INT, " +
                                "fk_book_id INT, " +
                                "order_time TIMESTAMP, " +
                                "order_type BOOLEAN, " +
                                "FOREIGN KEY(fk_user_id) REFERENCES users(user_id), " +
                                "FOREIGN KEY(fk_book_id) REFERENCES books(book_id)" +
                                ");");
            } catch (SQLException e) {
                System.out.print(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
    }

    private void executeUpdate(String... updates) {
        executeUpdate(this.conn, updates);
    }

    private static void executeUpdate(Connection conn, String... updates) {
        try (Statement statement = conn.createStatement()) {
            for (String update : updates) {
                statement.executeUpdate(update);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private static void executeQuery

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}

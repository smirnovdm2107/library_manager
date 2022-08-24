package com.example.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    int userId = 0;
    String login;
    String password;
    String name;
    String surname;

    public User(int userId, String login, String password, String name, String surname) {
        this(login, password, name, surname);
        this.userId = userId;
    }

    public User(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isDefined()  {
        return userId != 0;
    }
}

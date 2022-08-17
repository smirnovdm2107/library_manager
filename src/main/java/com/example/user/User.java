package com.example.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private Map<String, String> userDates = new HashMap<>();
    public User(String login, String password, String name, String surname) {
        userDates.put("login", login);
        userDates.put("password", password);
        userDates.put("name", name);
        userDates.put("surname", surname);
    }

    public String getName() {
        return userDates.get("name");
    }

    public String getSurname() {
        return userDates.get("surname");
    }

    public String getLogin() {
        return userDates.get("login");
    }

    public String getPassword() {
        return userDates.get("password");
    }
}

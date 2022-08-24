package com.example.db;

public class Author {
    int authorId;
    String name;
    String surname;
    String description;
    public Author(String name, String surname, String description) {
        this.name = name;
        this.surname = surname;
        this.description = description;
    }

    public Author(int authorId, String name, String surname, String description) {
        this(name, surname, description);
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

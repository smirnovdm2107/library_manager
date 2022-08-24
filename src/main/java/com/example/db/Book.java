package com.example.db;

public class Book {
    private int bookId = 0;
    private String title;
    private int authorId;
    private String annotation;
    private int amount = 1;

    public Book(String title, int authorId, String annotation, int amount) {
        this.title = title;
        this.authorId = authorId;
        this.annotation = annotation;
        this.amount = amount;
    }

    public Book(int bookId, String title, int authorId, String annotation, int amount) {
        this(title, authorId, annotation, amount);
        this.bookId = bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAnnotation() {
        return annotation;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isDefined() {
        return bookId != 0;
    }
}

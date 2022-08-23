package com.example.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
    int orderId;
    int userId;
    int bookId;
    Timestamp orderTime;
    boolean orderType;

    public Order(int id, int userId, int bookId, Timestamp orderTime, boolean orderType) {
        this.orderId = id;
        this.userId = userId;
        this.bookId = bookId;
        this.orderTime = orderTime;
        this.orderType = orderType;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderType(boolean orderType) {
        this.orderType = orderType;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public boolean isOrderType() {
        return orderType;
    }

}

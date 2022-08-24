package com.example.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    int orderId = 0;
    int userId;
    int bookId;
    Timestamp orderTime;
    boolean orderType;

    public Order(int userId, int bookId, Timestamp orderTime, boolean orderType) {
        this.userId = userId;
        this.bookId = bookId;
        this.orderTime = orderTime;
        this.orderType = orderType;
    }

    public Order(int orderId, int userId, int bookId, Timestamp orderTime, boolean orderType) {
        this(userId, bookId, orderTime, orderType);
        this.orderId = orderId;
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
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        List<Object> fields = List.of(
                this.userId,
                this.bookId,
                this.orderTime,
                this.orderTime
        );
        StringBuilder sb = new StringBuilder();
        fields.forEach((element) -> {sb.append(String.valueOf(element)).append(", ");});
        return sb.toString();
    }

    public boolean isDefined() {
        return orderId != 0;
    }
}

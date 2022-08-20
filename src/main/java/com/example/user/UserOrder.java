package com.example.user;

public class UserOrder {

    private final String bookTitle;
    private final String orderData;

    public UserOrder(String bookTitle, String orderData, int orderID, boolean orderType) {
        this.bookTitle = bookTitle;
        this.orderData = orderData;
        this.orderID = orderID;
        this.orderType = orderType;
    }

    private final int orderID;
    private final boolean orderType;

    public String getBookTitle() {
        return bookTitle;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderData() {
        return orderData;
    }

    public boolean isOrderType() {
        return orderType;
    }
}

package com.example.controllers;

import com.example.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountMenuController {

    @FXML
    Label nameLabel;
    public void setUser(User user) {
        nameLabel.setText("This is your account, " + user.getName());
    }




}

package com.example.controllers;

import com.example.user.User;
import com.example.user.UserOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountMenuController {

    @FXML
    Label nameLabel;
    public void setUser(User user) {
        nameLabel.setText("This is your account, " + user.getName());
    }

    public void takeBook(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchScene("/MainMenu.fxml", e);
    }
    public void logOut(ActionEvent e) throws IOException {
       SceneSwitcher.getInstance().switchScene("/MainMenu.fxml", e);
    }

    public void returnBook(ActionEvent e) {

    }
}

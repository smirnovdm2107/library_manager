package com.example.controllers;

import com.example.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class AccountMenuController implements SceneController {

    @FXML
    Label nameLabel;
    public void setUser(User user) {
        nameLabel.setText("This is your account, " + user.getName());
    }

    public void takeBook(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchScene("/MainMenu.fxml", e);
    }
    public void logOut(ActionEvent e) throws IOException {
       SceneSwitcher.getInstance().switchScene("/LoginMenu.fxml", e);
    }
    public void returnBook(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchScene("/ReturnBookPage", e);
    }
}

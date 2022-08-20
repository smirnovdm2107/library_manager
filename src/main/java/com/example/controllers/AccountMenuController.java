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

    public void setOrderList(User user) {
        UserOrder[] userOrders =
    }

    public void logOut(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/LoginMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

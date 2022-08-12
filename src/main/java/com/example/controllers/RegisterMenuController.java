package com.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterMenuController {

    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    ImageView backwardArrowImage;

    Stage stage;
    Scene scene;
    Parent root;

    public void signIn(ActionEvent e) {

    }
    public void switchToLoginMenu(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginMenu.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

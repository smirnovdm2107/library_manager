package com.example.controllers;

import com.example.user.User;
import com.example.db.DataBaseController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    CheckBox logInCheckBox;

    Stage stage;
    Scene scene;
    Parent root;

    public void signIn(ActionEvent e) throws IOException {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String login = loginField.getText();
        String password = passwordField.getText();
        boolean emptyFlag = false;
        emptyFlag = checkEmpty(nameField, "Please enter name");
        emptyFlag = checkEmpty(surnameField, "Please enter surname");
        emptyFlag = checkEmpty(loginField, "Please enter login");
        emptyFlag = checkEmpty(passwordField, "Please enter surname");
        if (emptyFlag) {
            return;
        }
        User user = new User(login, password, name, surname);
        DataBaseController dataBaseController = DataBaseController.getInstance();
        dataBaseController.addUser(user);
        if (logInCheckBox.isSelected()) {
            switchScene("/MainMenu.fxml", e);
        }
        switchScene("/LoginMenu.fxml", e);
    }

    private boolean checkEmpty(TextField textField, String text) {
        if (textField.getText().isEmpty()) {
            textField.setPromptText(text);
            return true;
        }
        return false;
    }

    public void switchToLoginMenu(MouseEvent e) throws IOException {
        switchScene("/LoginMenu.fxml", e);
    }
    private void switchScene(String path, Event e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

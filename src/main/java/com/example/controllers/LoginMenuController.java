package com.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class LoginMenuController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private Button logInButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void login(ActionEvent e) throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login.isEmpty() || password.isEmpty()) {
            if (login.isEmpty()) {
                loginField.setPromptText("Please enter login");
                loginField.setOnMouseClicked(event -> loginField.setPromptText("Login"));
            }
            if (password.isEmpty()) {
                passwordField.setPromptText("Please enter password");
                passwordField.setOnMouseClicked(event -> passwordField.setPromptText("Password"));
            }
            return;
        }
        setScene("/MainMenu.fxml", e);
    }

    public void register(ActionEvent e) throws IOException {

    }

    private boolean checkEmptyFields(TextField... fields) {
        boolean isValid = true;
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                isValid = false;
                String promptText = field.getPromptText();
                field.setPromptText("please enter " + promptText);
                field.setOnMouseClicked(event -> {
                    field.setPromptText(promptText);
                });


            }
        }
        return isValid;
    }

    private void setScene(String path, ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

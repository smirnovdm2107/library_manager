package com.example.controllers;

import com.example.user.User;
import com.example.db.DBController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LoginMenuController implements SceneController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private Button logInButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label wrongDataLabel;

    public void login(ActionEvent e) throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login.isEmpty() || password.isEmpty()) {
            setPromptTextIfEmpty(loginField, "Please enter login", "Login");
            setPromptTextIfEmpty(passwordField, "Please enter password", "Password");
            return;
        }
        DBController dataBaseController = new DBController();
        User user = dataBaseController.findUser(login, password);
        if (user == null) {
            loginField.setText("");
            passwordField.setText("");
            wrongDataLabel.setVisible(true);
            /*
            EventHandler<MouseEvent> eventHandler = event -> wrongDataLabel.setVisible(false);
            loginField.setOnMouseClicked(eventHandler);
            wrongDataLabel.setOnMouseClicked(eventHandler);

            I don't know how to remove handler after the event:(
            */
            return;
        }
        writeUser(user);
        SceneSwitcher.getInstance().switchScene("/MainMenu.fxml", e);
    }
    private void setPromptTextIfEmpty(TextField textField, String promptText, String returnText) {
        if (textField.getText().isEmpty()) {
            textField.setPromptText("Please enter login");
            textField.setOnMouseClicked(event -> textField.setPromptText(returnText));
        }
    }

    private void writeUser(User user) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/main/resources/user_info.txt"),
                StandardCharsets.UTF_8
        ))) {
            writeUserField(writer, "login", user.getLogin());
            writeUserField(writer,"password", user.getPassword());
            writeUserField(writer, "name", user.getName());
            writeUserField(writer, "surname", user.getSurname());
        } catch (IOException e) {
            System.out.println("problem with opening file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void writeUserField(BufferedWriter writer, String fieldName, String fieldValue) throws IOException{
        writer.write(fieldName + " : " + fieldValue);
        writer.newLine();
    }
    public void register(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchScene("/RegisterMenu.fxml", e);
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

}

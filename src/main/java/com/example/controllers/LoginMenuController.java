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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LoginMenuController {
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
        DataBaseController dataBaseController = new DataBaseController();
        User user = dataBaseController.findUser(login, password);
        writeUser(user);
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
        setScene("/MainMenu.fxml", e);
    }

    private void writeUser(User user) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/user_info.txt"),
                StandardCharsets.UTF_8
        ))) {
            String[] userDates = new String[]{
                    user.getLogin(), user.getPassword(), user.getName(), user.getSurname()
            };
            for (String userDate : userDates) {
                writer.write(userDate);
            }
        } catch (IOException e) {
            System.out.println("problem with opening file: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public void register(ActionEvent e) throws IOException {
        setScene("/RegisterMenu.fxml", e);
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
    private void setScene(String path, Event e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

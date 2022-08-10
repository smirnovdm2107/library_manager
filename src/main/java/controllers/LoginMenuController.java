package controllers;

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

import java.io.IOException;

public class LoginMenuController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private Button logInButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void login(ActionEvent e) throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

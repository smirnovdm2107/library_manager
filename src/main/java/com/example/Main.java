package com.example;

import com.example.db.DBController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DBController.checkDataBase();
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            showCloseAlert(stage);
        });
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/LoginMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showCloseAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Already leaving?");
        alert.setContentText("Are you sure about that?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

}

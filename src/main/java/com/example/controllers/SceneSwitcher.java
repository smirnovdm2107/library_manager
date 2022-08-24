package com.example.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class SceneSwitcher {
    private static SceneSwitcher sceneSwitcher;

    private SceneSwitcher(){
    }

    public void switchScene(String sceneFXMLFilePath, Event e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneFXMLFilePath));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static SceneSwitcher getInstance() {
        if (sceneSwitcher == null) {
            sceneSwitcher = new SceneSwitcher();
        }
        return sceneSwitcher;
    }
}

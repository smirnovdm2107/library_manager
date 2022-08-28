package com.example.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ReturnBookPageController {

    @FXML
    ListView<String> returnBookListView;

    private static class ButtonListCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("EMPTY");
        Pane pane = new Pane();
        Button button = new Button("return");

        public ButtonListCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    returnBook(event);
                }
            });
        }

        public static void returnBook(ActionEvent e) {
        }
    }




}

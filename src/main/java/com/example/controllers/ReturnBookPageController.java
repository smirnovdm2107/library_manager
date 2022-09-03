package com.example.controllers;

import com.example.db.Book;
import com.example.db.DBController;
import com.example.user.User;
import com.example.user.UserLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;

public class ReturnBookPageController implements SceneController {

    @FXML
    private ListView<String> returnBookListView;

    private static class ButtonListCell extends ListCell<String> {
        private final HBox hbox = new HBox();

        private static class BookLabel extends Label {
            private final Book book;
            public BookLabel(Book book) {
                super();
                this.book = book;
            }

            public Book getBook() {
                return book;
            }
        }
        private final Label label = new Label("EMPTY");
        private final Pane pane = new Pane();
        private final Button button = new Button("return");

        public ButtonListCell() {
            super();
            button.setOnAction(event -> {
                User user = null;
                try {
                    user = new UserLoader("/resources/user_info.txt").getUser();
                } catch (IOException e) {
                    System.out.println("can't open user file");
                    throw new RuntimeException(e);
                }
                ObservableList<String> list = this.getListView().getItems();
                list.remove(((Button) event.getSource()).getParent());
            });

            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }


        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                label.setText(null);
                return;
            }
            label.setText(item);

            setGraphic(hbox);
        }

    }





}

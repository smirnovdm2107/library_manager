package com.example.controllers;

import com.example.db.DataBaseController;
import com.example.user.UserLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainMenuController {
    @FXML
    TextField bookSearchField;
    @FXML
    ListView<String> mainListView;

    public void searchBookOrAuthor(ActionEvent e) {
        String requestString = bookSearchField.getText();
        DataBaseController dataBaseController = new DataBaseController();
        List<String> results = dataBaseController.getBooksAndAuthors();
        results.sort((s1, s2) -> {
            int res1 = findLevenshteinDistance(requestString, s1);
            int res2 = findLevenshteinDistance(requestString, s2);
            if (res1 > res2) {
                return -1;
            } else if (res1 < res2) {
                return 1;
            }
            return 0;
        });
        mainListView.setItems(FXCollections.observableList(results));
    }

    public void switchToAccountScene(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        AccountMenuController controller = loader.getController();
        controller.setUser((new UserLoader("/user_info.txt").getUser()));
        stage.show();
    }

    public void logOut(ActionEvent e) {

    }

    private int findLevenshteinDistance(String a, String b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][i - 1]);
                    dp[i][j]++;
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}

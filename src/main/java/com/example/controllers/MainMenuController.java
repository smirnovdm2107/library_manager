package com.example.controllers;

import com.example.db.DataBaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Objects;

public class MainMenuController {
    @FXML
    TextField bookSearchField;
    @FXML
    ListView<String> mainListView;

    public void searchBookOrAuthor(ActionEvent e) {
        String requestString = bookSearchField.getText();
        DataBaseController dataBaseController = new DataBaseController();
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

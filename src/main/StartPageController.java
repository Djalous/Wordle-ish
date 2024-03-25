package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.AdminController;
import main.GameController;

import java.io.IOException;

public class StartPageController {
    @FXML
    private AnchorPane startPage;
    @FXML
    private ChoiceBox<String> userSelection;
    @FXML
    private void startGame(ActionEvent event) throws IOException {
        String userType = userSelection.getValue();
        switch (userType) {
            case "Admin" -> AdminController.loadAdminDashboard((Stage) startPage.getScene().getWindow());
            case "User" -> GameController.loadGameView((Stage) startPage.getScene().getWindow());
        }

    }
}

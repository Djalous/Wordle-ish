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
        Stage stage = (Stage) startPage.getScene().getWindow();
        String userType = userSelection.getValue();
        switch (userType) {
            case "Admin" -> {
                stage.close();
                new AdminController().loadAdminDashboard();
            }
            case "User" -> {
                stage.close();
                GameController.loadGameView();
            }
        }
    }


}

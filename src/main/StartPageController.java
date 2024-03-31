package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static main.AdminController.STAGE;

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
                loadAdminDashboard();
            }
            case "User" -> {
                stage.close();
                loadGameView();
            }
        }
    }

    public static void loadGameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(StartPageController.class.getResource("/resources/view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads the admin dashboard
     *
     * @throws IOException
     */
    public void loadAdminDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(StartPageController.class.getResource("/resources/admin-dashboard.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        STAGE.setScene(scene);
        STAGE.show();
    }
}

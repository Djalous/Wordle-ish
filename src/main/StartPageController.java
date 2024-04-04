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


import static main.AdminController.ADMIN_STAGE;
import static main.LogInController.LOGIN_STAGE;

public class StartPageController {
    @FXML
    private AnchorPane startPage;
    @FXML
    private void startGame(ActionEvent event) throws IOException {
        loadGameView();
    }

    @FXML
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
        FXMLLoader loader = new FXMLLoader(AdminController.class.getResource("/resources/admin-dashboard.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ADMIN_STAGE.setScene(scene);
        ADMIN_STAGE.show();
    }

    @FXML
    private void loadLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(LogInController.class.getResource("/resources/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        LOGIN_STAGE.setScene(scene);
        LOGIN_STAGE.show();
    }
}

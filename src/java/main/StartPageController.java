package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {
    @FXML
    Button logInBtn;
    @FXML
    Button playBtn;

    private static Stage stage;

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loadGameView();
    }

    @FXML
    public static void loadGameView() throws IOException {
        Parent root = ResourceManager.loadFXML("view.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads the admin dashboard
     *
     * @throws IOException
     */
    public void loadAdminDashboard() throws IOException {
        Parent root = ResourceManager.loadFXML("admin-dashboard.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadLoginPage(ActionEvent event) throws IOException {
        Parent root = ResourceManager.loadFXML("login.fxml");
        Scene scene = new Scene(root);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

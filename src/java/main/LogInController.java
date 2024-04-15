package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    Button loginBtn;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    private static String enteredUsername;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText(enteredUsername);
        usernameField.setEditable(false);
        loginBtn.disableProperty().bind(passwordField.textProperty().isEmpty());
    }

    public static void setEnteredUsername(String username) {
        enteredUsername = username;
    }

    @FXML
    public void toPreviousView(ActionEvent actionEvent) {
        Stage stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        try {
            Parent root = ResourceManager.loadFXML("login.fxml");
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

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
import java.util.HashMap;
import java.util.ResourceBundle;

public class UserProfileController {
    @FXML
    Button continueBtn;
    @FXML
    TextField usernameField;

    final static Stage LOGIN_STAGE = new Stage();

    @FXML
    private void continuePressed() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        String username = usernameField.getText();
        if (UserBase.containsUser(username)) {
            LogInController.setEnteredUsername(username);
            try {
                Parent root = ResourceManager.loadFXML("login-1.fxml");
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            CreateAccController.setEnteredUsername(username);
            try {
                Parent root = ResourceManager.loadFXML("login-2.fxml");
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

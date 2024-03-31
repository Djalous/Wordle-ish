package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    Button loginBtn;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;

    /**
     * @param url The location used to resolve relative paths for the root object,
     *            or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object,
     *                       or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.disableProperty().bind(
                usernameField.textProperty().isEmpty().and(passwordField.textProperty().isEmpty())
        );
    }

    @FXML
    private void login() {
        
    }
}

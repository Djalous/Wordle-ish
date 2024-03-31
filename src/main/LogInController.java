package main;

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

public class LogInController implements Initializable {
    @FXML
    Button loginBtn;
    @FXML
    TextField usernameField;
    @FXML
    TextField usernameField1;
    @FXML
    TextField usernameField2;
    @FXML
    TextField passwordField;

    HashMap<String, String> users = new HashMap<>();
    final static Stage LOGIN_STAGE = new Stage();

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
    private void continuePressed() throws IOException {
        String username = usernameField.getText();
        if (users.containsKey(usernameField.getText())) {
            usernameField1.setText(username);
            usernameField1.setEditable(false);
            try {
                FXMLLoader knownUser = new FXMLLoader(getClass().getResource("/resources/login-1.fxml"));
                Parent root = knownUser.load();
                Scene scene = new Scene(root);
                LOGIN_STAGE.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            usernameField2.setText(username);
            usernameField2.setEditable(false);
            try {
                FXMLLoader knownUser = new FXMLLoader(getClass().getResource("/resources/login-2.fxml"));
                Parent root = knownUser.load();
                Scene scene = new Scene(root);
                LOGIN_STAGE.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void toPreviousView() {
        try {
            FXMLLoader knownUser = new FXMLLoader(getClass().getResource("/resources/login.fxml"));
            Parent root = knownUser.load();
            Scene scene = new Scene(root);
            LOGIN_STAGE.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

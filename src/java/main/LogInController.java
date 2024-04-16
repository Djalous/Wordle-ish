/**
 *  Copyright 2024 SWE 2710 111 Team B (Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
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

    @FXML
    public void verifyLogin(ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (UserBase.checkPassword(username, password)) {
            StartPageController.setStage((Stage)((Button) actionEvent.getSource()).getScene().getWindow());
            StartPageController.loadGameView();
        }
//        else {
//            Dialog<Error> dialog = new Dialog<>();
//            dialog.setContentText("Invalid password");
//            dialog.setOnCloseRequest(e -> dialog.close());
//            dialog.showAndWait();
//        }
    }
}

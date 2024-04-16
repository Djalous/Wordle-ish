/**
 *  Copyright 2024 SWE 2710 111 Team B (Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
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

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccController implements Initializable {
    @FXML
    TextField passwordField;
    @FXML
    TextField usernameField;
    @FXML
    Button createBtn;
    @FXML
    ChoiceBox<String> userType;
    private static String enteredUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText(enteredUsername);
        usernameField.setEditable(false);
        userType.getItems().addAll(UserType.ADMIN.toString(),
                                   UserType.MIDDLE_SCHOOL_STUDENT.toString(),
                                   UserType.COLLEGE_STUDENT.toString());
        createBtn.disableProperty().bind(passwordField.textProperty().isEmpty());
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
    public void createAccount(ActionEvent actionEvent) throws IOException {
        UserType type;
        if (userType.getValue().equals(UserType.MIDDLE_SCHOOL_STUDENT.toString())) {
            type = UserType.MIDDLE_SCHOOL_STUDENT;
        } else if (userType.getValue().equals(UserType.COLLEGE_STUDENT.toString())){
            type = UserType.COLLEGE_STUDENT;
        } else {
            type = UserType.USER;
        }

        UserBase.addUser(enteredUsername, passwordField.getText(),type);
        StartPageController.setStage((Stage)((Button) actionEvent.getSource()).getScene().getWindow());
        StartPageController.loadGameView();
    }
}

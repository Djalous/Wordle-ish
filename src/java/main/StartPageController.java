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

    public static void setStage(Stage stage) {
        StartPageController.stage = stage;
    }
}

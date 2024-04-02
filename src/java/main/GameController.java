package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    private static final String FXML_PATH = "view.fxml";

    public static void loadGameView() throws IOException {
        Parent root = ResourceManager.loadFXML(FXML_PATH);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

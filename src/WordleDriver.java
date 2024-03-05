import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Drives the overall word program with its given main()
 * @author Lazar Jovanovic
 * @version created on 9/8/24
 */
public class WordleDriver extends Application {

    public WordleDriver() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("start_page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}

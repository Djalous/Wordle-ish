import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WordleDriver extends Application {

    public WordleDriver() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //stage.setTitle("Hello World!");
        //Button btn = new Button();
        //btn.setText("Say 'Hello World'");
        /*btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/

        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        //stage.setScene(new Scene(root, 300, 250));
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}

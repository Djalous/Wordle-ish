import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Window;

import java.util.List;

public class UserGUI {
    Window window;
    Pane mainPane;
    GridPane keyboardContainer;
    List<Button> screenKeyboardButtons;
    GridPane guessGrid;
    List<Text> guessGridCharacters;
    Button hintButton;
    Button getStatsButton;
    Text stats;
}

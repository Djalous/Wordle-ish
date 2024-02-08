import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GUI {
    private GridPane keyboard;
    private GridPane letterBoxes;
    private static final Color GREEN = Color.color(0.1, 0.7, 0.1);
    private static final Color GREY = Color.color(0.6, 0.6, 0.6);
    private static final Color YELLOW = Color.color(.96, 0.85, 0.21);

    private List<Button> changedButtons = new ArrayList<>();

    public GUI() {

    }

    public void onKeyPressed(ActionEvent event) {
        //TODO: Implement me!
    }

    public void setKeyColor(Button button) {
        //TODO: Implement me!
    }

    public void setCharBoxColor(Text text) {
        //TODO: Implement me!
    }

    public void setAvgGuesses(Button button) {
        //TODO: Implement me!
    }

    public void displayCommonWords() {
        //TODO: Implement me!
    }

    public void displayHint() {
        //TODO: Implement me!
    }

    public void setNewTargetBank() {
        //TODO: Implement me!
    }

    public void setNewVocabBank() {
        //TODO: Implement me!
    }

    public void testGuessFile() {
        //TODO: Implement me!
    }

    public void viewUserGuessFrequency(ActionEvent event) {
        //TODO: Implement me!
    }

    public void viewUserLetterFrequency(ActionEvent event) {
        //TODO: Implement me!
    }

    public void viewRecommendedTargetWords(ActionEvent event) {
        //TODO: Implement me!
    }

    public void getWordSize(ActionEvent event) {
        //TODO: Implement me!
    }
}

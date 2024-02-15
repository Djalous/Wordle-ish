import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class Controller implements Initializable {

    @FXML
    private TextField firstLetterTyped;
    @FXML
    private TextField secondLetterTyped;
    @FXML
    private TextField thirdLetterTyped;
    @FXML
    private TextField fourthLetterTyped;
    @FXML
    private TextField fifthLetterTyped;


    private GameState state;
    private WordBank bank;
    private UserType user;

    private GridPane keyboard;
    private GridPane letterBoxes;

    private List<Button> changedButtons = new ArrayList<>();
    private int wordLength = 5;

    private static final Color GREEN = Color.color(0.1, 0.7, 0.1);
    private static final Color GREY = Color.color(0.6, 0.6, 0.6);
    private static final Color YELLOW = Color.color(.96, 0.85, 0.21);


    public Controller() {

    }

    public boolean enterPressed() {
        return false; //TODO: Implement me!
    }

    public void keyboardPressed(KeyEvent key) {
        //TODO: Implement me!
    }

    public void updateGuess(String letter) {
        //TODO: Implement me!
    }

    public void updateGuesses(Word guess) {
        //TODO: Implement me!
    }

    public boolean isCorrect() {
        return false; //TODO: Implement me!
    }

    public void updateVocab() {
        //TODO: Implement me!
    }

    public void startGame() {
        //TODO: Implement me!
    }

    public void endGame() {
        //TODO: Implement me!
    }

    public HashMap<Character, Integer> getAllLetterFrequencies() {
        return null; //TODO: Implement me!
    }

    public float getAvgGuesses() {
        return -1; //TODO: Implement me!
    }

    //TODO: Introduce word frequencies data type
    public HashMap<String, Integer> getWordFrequencies() {
        return null; //TODO: Implement me!
    }

    public void setNewTargetBank(File file) {
        //TODO: Implement me!
    }

    public void setNewVocabBank(File file) {
        //TODO: Implement me!
    }

    public void testGuessFile(File file) {
        //TODO: Implement me!
    }

    public void writeOutputTestFile() {
        //TODO: Implement me!
    }

    public void updateWordLength(int length) {
        wordLength = length;
    }

    public void selectRandomGuesses() {
        //TODO: Implement me!
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstLetterTyped.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
    }
}

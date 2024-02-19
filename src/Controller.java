import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
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
    private final List<GameState> history = new ArrayList<>();

    private WordBank bank;
    private UserType user;

    private GridPane keyboard;
    private GridPane letterBoxes;

    private List<Button> changedButtons = new ArrayList<>();
    private int wordLength = 5;

    private static final Color GREEN = Color.color(0.1, 0.7, 0.1);
    private static final Color GREY = Color.color(0.6, 0.6, 0.6);
    private static final Color YELLOW = Color.color(.96, 0.85, 0.21);

    private boolean gameIsActive;

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

    /**
     * The following method was generated using ChatGPT on 2/15 https://chat.openai.com/
     * with the prompt: "how to make fxml textfield only accept one character in FXML"
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstLetterTyped.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        secondLetterTyped.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        thirdLetterTyped.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        fourthLetterTyped.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        fifthLetterTyped.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        filterTextField(firstLetterTyped, secondLetterTyped);
        filterTextField(secondLetterTyped, thirdLetterTyped);
        filterTextField(thirdLetterTyped, fourthLetterTyped);
        filterTextField(fourthLetterTyped, fifthLetterTyped);
        filterLastTextField(fifthLetterTyped);
    }

    /**
     * The following method was generated using ChatGPT on 2/17 https://chat.openai.com/
     * with the prompt: "how to make textfield in fxml only allow letters, be case-insensitive,
     * move cursor to next textfield automatically, and keep a cursor present in a textfield
     * after deleting its value"
     * @param textField the current textField
     * @param nextField the next textField
     */
    private void filterTextField(TextField textField, TextField nextField) {
        textField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z]") || newText.isEmpty()) {
                change.setText(newText.toUpperCase());
                change.setRange(0, change.getControlText().length());
                return change;
            } else {
                return null;
            }
        }));

        textField.setOnKeyTyped(event -> {
            if (textField.getText().length() == 1) {
                nextField.requestFocus();
            }
        });

        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE && textField.getText().isEmpty()) {
                event.consume();
                textField.positionCaret(0);
            }
        });
    }

    /**
     * The following method was generated using ChatGPT on 2/17 https://chat.openai.com/ with
     * the prompt: "how to make textfield in fxml only allow letters, be case-insensitive,
     * move cursor to next textfield automatically, and keep a cursor present in a textfield
     * after deleting its value"
     * @param lastTextField the last (fifth) textField
     */
    private void filterLastTextField(TextField lastTextField) {
        lastTextField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z]") || newText.isEmpty()) {
                change.setText(newText.toUpperCase());
                change.setRange(0, change.getControlText().length());
                return change;
            } else {
                return null;
            }
        }));

        lastTextField.setOnKeyTyped(event -> {
            if (lastTextField.getText().length() == 1) {
                lastTextField.getParent().requestFocus();
            }
        });

        lastTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE && lastTextField.getText().isEmpty()) {
                event.consume();
                lastTextField.positionCaret(0);
            }
        });
    }
}

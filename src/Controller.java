import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label errMsgLabel;
    private GameState state;
    private final List<GameState> history = new ArrayList<>();

    private WordBank bank;
    private UserType user;

    private GridPane keyboard;
    private GridPane letterBoxes;

    private final List<Button> changedButtons = new ArrayList<>();
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
        String str = key.getCharacter();

        if (str == null || str.isEmpty()) {
            return;
        }

        char c = str.charAt(0);

        state.getCurrentGuess().pushChar(c);
    }

    public void updateGuess(String letter) {
        //TODO: Implement me!
    }

    public void updateGuesses(Word guess) {
        //TODO: Implement me!
    }

    public boolean isCorrect() {
        return state.getCurrentGuess().equals(state.getTargetWord());
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
     * validateGuess() method which checks for any invalidity in the user's guess when enter
     * is pressed; displays error messages accordingly
     * @return a boolean depending on if the user's guess word is valid or not
     */
    public boolean validateGuess() {
        // will be tied to enterPressed() method which calls this every time enter is pressed
        // through some sort of event listener maybe similar to filterTextField() methods below

        // Additionally method will be used to check whether currentGuess should be stored based
        // truth value returned from this method

        // Create for loop to check if textfields are empty
        if (firstLetterTyped.getText().isEmpty() || secondLetterTyped.getText().isEmpty() ||
                thirdLetterTyped.getText().isEmpty() || fourthLetterTyped.getText().isEmpty() ||
                fifthLetterTyped.getText().isEmpty()) {
            errMsgLabel.setText("Please enter a " + wordLength + " letter word.");
            return false;
        } else {
            String tempGuess = firstLetterTyped.getText() + secondLetterTyped.getText() +
                    thirdLetterTyped.getText() + fourthLetterTyped.getText() +
                    fifthLetterTyped.getText();

            Word tempWord = new Word(tempGuess.length());
            for (int i = 0; i < tempGuess.length(); i++) {
                tempWord.pushChar(tempGuess.charAt(i));
            }

            if (!bank.isValid(tempWord)) {
                errMsgLabel.setText("Please enter a valid word.");
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * The following method was generated using ChatGPT on 2/15 https://chat.openai.com/
     * with the prompt: "how to make fxml textfield only accept one character in FXML"
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstLetterTyped.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        secondLetterTyped.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        thirdLetterTyped.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        fourthLetterTyped.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.isContentChange() && change.getControlNewText().length() > 1) {
                return null;
            }
            return change;
        }));
        fifthLetterTyped.setTextFormatter(new TextFormatter<String>(change -> {
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
        textField.setTextFormatter(new TextFormatter<String>(change -> {
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
        lastTextField.setTextFormatter(new TextFormatter<String>(change -> {
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

    /**
     * setFirstTextField() method that sets text of the first text field for testing purposes
     * @param text string being passed to the text field
     */
    public void setFirstTextField(String text) {
        firstLetterTyped.setText(text);
    }

    /**
     * setSecondTextField() method that sets text of the second text field for testing purposes
     * @param text string being passed to the text field
     */
    public void setSecondTextField(String text) {
        secondLetterTyped.setText(text);
    }

    /**
     * setThirdTextField() method that sets text of the third text field for testing purposes
     * @param text string being passed to the text field
     */
    public void setThirdTextField(String text) {
        thirdLetterTyped.setText(text);
    }

    /**
     * setFourthTextField() method that sets text of the fourth text field for testing purposes
     * @param text string being passed to the text field
     */
    public void setFourthTextField(String text) {
        fourthLetterTyped.setText(text);
    }

    /**
     * setFifthTextField() method that sets text of the fifth text field for testing purposes
     * @param text string being passed to the text field
     */
    public void setFifthTextField(String text) {
        fifthLetterTyped.setText(text);
    }

    /**
     * getLabelText() method which returns current val of the error message label for testing
     * purposes
     * @return String label text
     */
    public String getLabelText() {
        return errMsgLabel.getText();
    }

    /**
     * getWordLength() method which returns the currently set guess word length for testing
     * purposes
     * @return int word length currently set
     */
    public int getWordLength() {
        return wordLength;
    }
}

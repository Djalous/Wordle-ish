import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private GridPane letterGrid;

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
        Word targetWord = bank.generateTargetWord();

        GameState state = new GameState(targetWord);
        gameIsActive = true;

        boolean isValidWord = bank.isValid(targetWord);
        if (!isValidWord) {
            //TODO: Indicate to user the word is invalid
        }

        CharValidity[] letterStatus = state.getCurrentGuess().getCorrect(targetWord);

        int i = 0;
        for (CharValidity status: letterStatus) {
//            switch (status) {
//                case INCORRECT -> textFields[i].;
//                case PRESENT_CHAR -> letterColors.add(YELLOW);
//                case CORRECT_POSITION -> letterColors.add(GREEN);
//            }

        }
        return true;
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
     * The following method was generated using ChatGPT on 2/15 https://chat.openai.com/
     * with the prompt: "how to make fxml textfield only accept one character in FXML"
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Node> letterFields = letterGrid.getChildren();
        for  (int i = 0; i < letterFields.size(); ++i) {
            TextField letterField =  ((TextField) letterFields.get(i));
            letterField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
                if (change.isContentChange() && change.getControlNewText().length() > 1) {
                    return null;
                }
                return change;
            }));

            if ((i+1) % 5 == 0) {
                filterLastTextField(letterField);
            } else {
                filterTextField(letterField, (TextField) letterFields.get(i+1));
            }
        }
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

        textField.setOnKeyPressed(event -> {
            textField.setText(event.getText());
            if (textField.getText().length() == 1) {
                nextField.requestFocus();
            }

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

        lastTextField.setOnKeyPressed(event -> {
            lastTextField.setText(event.getText());
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

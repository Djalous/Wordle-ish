import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
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

    private final List<Button> changedButtons = new ArrayList<>();
    private List<Node> letterFields;
    private int wordLength = 5;

    private static final Color GREEN = Color.color(0.1, 0.7, 0.1);
    private static final Color GREY = Color.color(0.6, 0.6, 0.6);
    private static final Color YELLOW = Color.color(.96, 0.85, 0.21);

    private boolean gameIsActive;
    private int currentGuessRow = 0;

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
        List<TextField> currentFields = new ArrayList<>();

        for (Node letterField: letterFields) {
            if (GridPane.getRowIndex(letterField) == currentGuessRow) {
                currentFields.add((TextField) letterField);
                letterField.setDisable(true);
            }
        }

        String cssColor = null;
        for (int i = 0 ; i < letterStatus.length; ++i) {
            switch (letterStatus[i]) {
                case INCORRECT -> {
                    cssColor = String.format("#%02X%02X%02X",
                            (int) (GREY.getRed() * 255),
                            (int) (GREY.getGreen() * 255),
                            (int) (GREY.getBlue() * 255));
                }
                case PRESENT_CHAR -> {
                    cssColor = String.format("#%02X%02X%02X",
                            (int) (YELLOW.getRed() * 255),
                            (int) (YELLOW.getGreen() * 255),
                            (int) (YELLOW.getBlue() * 255));
                }
                case CORRECT_POSITION -> {
                    cssColor = String.format("#%02X%02X%02X",
                            (int) (GREEN.getRed() * 255),
                            (int) (GREEN.getGreen() * 255),
                            (int) (GREEN.getBlue() * 255));
                }
            }

            currentFields.get(i).setStyle("-fx-control-inner-background: " + cssColor + ";");
        }

        ++currentGuessRow;
        for (Node letterField: letterFields) {
            if (GridPane.getRowIndex(letterField) == currentGuessRow &&
                GridPane.getColumnIndex(letterField) == 0) {
                letterField.requestFocus();
                break;
            }
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
        letterFields = letterGrid.getChildren();
        for  (int i = 0; i < letterFields.size(); ++i) {
            TextField letterField =  ((TextField) letterFields.get(i));
            letterField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
                if (change.isContentChange() && change.getControlNewText().length() > 1) {
                    return null;
                }
                return change;
            }));

            int count = (i+1) % wordLength;

            TextField nextField = null;
            TextField prevField = null;
            if (count != 1) {
                prevField = (TextField) letterFields.get(i-1);
            }
            if (count != 0) {
                nextField = (TextField) letterFields.get(i+1);
            }

            filterTextField(prevField, letterField, nextField);
        }
    }

    /**
     * Part of the following method was generated using ChatGPT on 2/17 https://chat.openai.com/
     * with the prompt: "how to make textfield in fxml only allow letters, be case-insensitive,
     * move cursor to next textfield automatically, and keep a cursor present in a textfield
     * after deleting its value"
     * @param textField the current textField
     * @param nextField the next textField
     */
    private void filterTextField(TextField prevField, TextField textField, TextField nextField) {
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
            if (event.getCode() == KeyCode.BACK_SPACE) {
                if (textField.getText().isEmpty() && prevField != null) {
                    prevField.requestFocus();
                } else {
                    textField.clear();
                }
                //event.consume();
                //textField.positionCaret(0);
            } else {
                textField.setText(event.getText());
                if (textField.getText().length() == 1 && nextField != null) {
                    nextField.requestFocus();
                }
            }
        });
    }
}

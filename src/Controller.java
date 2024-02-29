import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private GridPane letterGrid;
    @FXML
    private Label errMsgLabel;
    @FXML
    private StackPane stackPane;
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

    private WordBank bank = new WordBank();
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
    
    public void keyboardPressed(KeyEvent key) {
        String str = key.getCharacter();

        if (str == null || str.isEmpty()) {
            return;
        }

        char c = str.charAt(0);

        state.getCurrentGuess().pushChar(c);
    }

    /** Update the current guess with a new letter
     * @param letter Letter to add
     */
    public void updateGuess(String letter) {
        //TODO: Implement me!
    }

    /** Add a new guess to our guesses
     * @param guess Guess to add
     */
    public void updateGuesses(Word guess) {
        //TODO: Implement me!
    }

    /** Checks our guess to our target word
     * @return Does our guess match our target word
     */
    public boolean isCorrect() {
        return state.getCurrentGuess().equals(state.getTargetWord());
    }

    /**
     * Initiates the vocab file selection dialogue and loading process
     */
    public void updateVocab() {
        //TODO: Implement me!
    }

    /**
     * Starts a new game
     */
    public void startGame() {
        //TODO: Implement me!
        Word targetWord = bank.generateTargetWord(); // this logic should ideally be here
        state = new GameState(targetWord);
        gameIsActive = true;
    }

    /**
     * Ends the current game
     */
    public void endGame() {
        //TODO: Implement me!
    }

    /** Returns the letter frequency data of all the past games
     * @return Frequency data
     */
    public HashMap<Character, Integer> getAllLetterFrequencies() {
        return null; //TODO: Implement me!
    }

    /** Returns the average number of guesses used over all games completed
     * @return Average number of guesses
     */
    public float getAvgGuesses() {
        return -1; //TODO: Implement me!
    }

    /** Gets the frequency of all words guessed over all games completed
     * @return The frequency data of guessed words
     */
    //TODO: Introduce word frequencies data type
    public HashMap<String, Integer> getWordFrequencies() {
        return null; //TODO: Implement me!
    }

    /** Sets a new target bank word file and loads it
     * @param file File to use for target bank
     */
    public void setNewTargetBank(File file) {
        //TODO: Implement me!
    }

    /** Sets a new vocab bank word file and loads it
     * @param file File to use for vocab bank
     */
    public void setNewVocabBank(File file) {
        //TODO: Implement me!
    }

    /** Takes a file and tests it against our default word bank with a random word
     * @param file File to test guesses against
     */
    public void testGuessFile(File file) {
        //TODO: Implement me!
    }

    /**
     * Writes the results of a test file to another output file
     */
    public void writeOutputTestFile() {
        //TODO: Implement me!
    }

    /** Change the length of the guessed words
     * @param length New word length
     */
    public void updateWordLength(int length) {
        wordLength = length;
    }

    /**
     *
     */
    public void selectRandomGuesses() {
        //TODO: Implement me!
    }

    /**
     * validateGuess() method which checks for any invalidity in the user's guess when enter
     * is pressed; displays error messages accordingly
     * @return a boolean depending on if the user's guess word is valid or not
     */
    public boolean validateGuess() {
        ArrayList<String> temp = new ArrayList<>();
        List<TextField> textFieldList = letterGrid.getChildren().stream()
                .filter(node -> node instanceof TextField).map(node -> (TextField) node)
                .collect(Collectors.toList());
        int startIndex = currentGuessRow * wordLength;
        int endIndex = startIndex + wordLength;
        for (int i = startIndex; i < endIndex; i++) {
            if (textFieldList.get(i).getText().isEmpty()) {
                errMsgLabel.setText("Please enter a " + wordLength + " letter word.");
                errMsgLabel.setVisible(true);
                return false;
            } else {
                temp.add(textFieldList.get(i).getText());
            }
        }
        String guessStr = String.join("", temp);
        Word guess = new Word(guessStr);
        if (!bank.isValid(guess)) { // need initialized bank to properly run
            errMsgLabel.setText("Please enter a valid word.");
            errMsgLabel.setVisible(true);
            return false;
        } else {
            state.updateCurrentGuess(guess);
            return true;
        }
    }

    private void colorizeFields() {
        CharValidity[] letterStatus = state.getCurrentGuess().getCorrect(state.getTargetWord());
        List<TextField> currentFields = new ArrayList<>();

        for (Node letterField : letterGrid.getChildren()) {
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

        for (Node letterField: letterFields) {
            if (GridPane.getRowIndex(letterField) == currentGuessRow &&
                    GridPane.getColumnIndex(letterField) == 0) {
                letterField.requestFocus();
                break;
            }
        }
    }

    /**
     * Helper method that moves cursor to next row once enter is pressed after a valid guess, additionally d
     */
    private void moveCursorToNextRow() {
        int nextRow = currentGuessRow + 1;
        for (Node node : letterGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == nextRow) {
                if (GridPane.getColumnIndex(node) == 0) {
                    node.requestFocus();
                }

                node.setDisable(false);
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

        Word targetWord = bank.generateTargetWord(); // this logic should ideally be here
        state = new GameState(targetWord);
        gameIsActive = true;

        letterFields = letterGrid.getChildren();
        int numCols = letterGrid.getColumnCount();
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

            int rowIndex = i / numCols;
            GridPane.setRowIndex(letterField, rowIndex);

            int colIndex = i % numCols;
            GridPane.setColumnIndex(letterField, colIndex);
        }

        stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (errMsgLabel.isVisible()) {
                errMsgLabel.setVisible(false);
            }
        });

        stackPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (validateGuess()) {
                    colorizeFields();
                    moveCursorToNextRow();
                    currentGuessRow++;
                }
            }
        });
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
                event.consume();
                //textField.positionCaret(0);
            } else {
                textField.setText(event.getText());
                if (textField.getText().length() == 1 && nextField != null) {
                    nextField.requestFocus();
                }
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

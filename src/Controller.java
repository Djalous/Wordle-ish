import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.Key;
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
    private Label msgLabel;
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
    private int wordLength = 5; // optimized for 4-7

    private static final Color GREEN = Color.color(0.1, 0.7, 0.1);
    private static final Color GREY = Color.color(0.6, 0.6, 0.6);
    private static final Color YELLOW = Color.color(.96, 0.85, 0.21);

    private boolean gameIsActive;
    private int currentGuessRow = 0;

    public Controller() throws FileNotFoundException {

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
                msgLabel.setText("Please enter a " + wordLength + " letter word.");
                msgLabel.setVisible(true);
                return false;
            } else {
                temp.add(textFieldList.get(i).getText());
            }
        }
        String guessStr = String.join("", temp);
        Word guess = new Word(guessStr);
        if (!bank.isValid(guess)) { // need initialized bank to properly run
            msgLabel.setText("Please enter a valid word.");
            msgLabel.setVisible(true);
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

        initLetterFields();

        letterFields = letterGrid.getChildren();
        int numCols = letterGrid.getColumnCount();
        for  (int i = 0; i < letterFields.size(); ++i) {
            TextField letterField =  ((TextField) letterFields.get(i));
            TextField prevField = null;
            TextField nextField = null;
            // letter fields being assigned was originally here
            letterField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
                if (change.isContentChange() && change.getControlNewText().length() > 1) {
                    return null;
                }
                return change;
            }));
            if (i != 0 && i != letterFields.size() - 1) {
                prevField = ((TextField) letterFields.get(i - 1));
                nextField = ((TextField) letterFields.get(i + 1));
            } else if (i == 0) {
                nextField = ((TextField) letterFields.get(i + 1));
            } else if (i == letterFields.size() - 1) {
                prevField = ((TextField) letterFields.get(i - 1));
            }
            filterTextField(prevField, letterField, nextField);

            int rowIndex = i / numCols;
            GridPane.setRowIndex(letterField, rowIndex);

            int colIndex = i % numCols;
            GridPane.setColumnIndex(letterField, colIndex);
        }

        stackPane.addEventFilter(MouseEvent.ANY, event -> {
            if (msgLabel.isVisible()) {
                msgLabel.setVisible(false);
            }
        });

        stackPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (msgLabel.isVisible()) {
                msgLabel.setVisible(false);
            }
        });

        stackPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && currentGuessRow != 6) {
                if (validateGuess()) {
                    colorizeFields();
                    if (currentGuessRow == 0 && state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText("Alright that was pretty cool");
                        msgLabel.setVisible(true);
                        endGame();
                    } else if (currentGuessRow == 1 && state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText("Not as cool as getting it in 1 but well done");
                        msgLabel.setVisible(true);
                        endGame();
                    } else if (currentGuessRow == 2 && state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText("Good job buster");
                        msgLabel.setVisible(true);
                        endGame();
                    } else if (currentGuessRow == 3 && state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText("Woo hoo");
                        msgLabel.setVisible(true);
                        endGame();
                    } else if (currentGuessRow == 4 && state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText("Congrats on the win");
                        msgLabel.setVisible(true);
                        endGame();
                    } else if (currentGuessRow == 5 && state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText("Should we even clap for that");
                        msgLabel.setVisible(true);
                        endGame();
                    } else if (currentGuessRow == 5 && !state.getCurrentGuess().equals(state.getTargetWord())) {
                        msgLabel.setText(state.getTargetWord().toString());
                        msgLabel.setVisible(true);
                        endGame();
                    } else {
                        moveCursorToNextRow();
                        currentGuessRow++;
                    }

                }
            }
        });
    }

    private void initLetterFields() {
        letterGrid.getChildren().clear();
        int numRows = 6;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < wordLength; col++) {
                TextField textField = new TextField();
                //textField.setPrefWidth(80); // original field width for reference
                textField.setPrefWidth(100 - (wordLength*4));
                textField.setPrefHeight(70);
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-display-caret: false");
                GridPane.setRowIndex(textField, row);
                GridPane.setColumnIndex(textField, col);
                letterGrid.getChildren().add(textField);
            }
        }
        letterGrid.setHgap((double) 250 / wordLength);
        for (int i = 0; i < (long) letterGrid.getChildren().size(); i++) {
            TextField field = (TextField) letterGrid.getChildren().get(i);
            field.setFont(Font.font("Century", field.getWidth() + 27));
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
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]")) {
                textField.setText("");
            } else {
                textField.setText(newValue.toUpperCase());
            }
        });

        textField.setOnKeyTyped(event -> {
            if (nextField != null) {
                int curRow = GridPane.getRowIndex(textField);
                int nextRow = GridPane.getRowIndex(nextField);
                if (textField.getText().length() == 1 && curRow == nextRow) {
                    nextField.requestFocus();
                } else if (curRow != nextRow) {
                    textField.positionCaret(0);
                }
            }
        });
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE && prevField != null) {
                int prevRow = GridPane.getRowIndex(prevField);
                int curRow = GridPane.getRowIndex(textField);
                if (textField.getText().isEmpty() && curRow == prevRow) {
                    prevField.requestFocus();
                    prevField.setText("");
                } else {
                    textField.setText("");
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
        return msgLabel.getText();
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

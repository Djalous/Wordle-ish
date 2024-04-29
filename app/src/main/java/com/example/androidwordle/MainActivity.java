package com.example.androidwordle;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidwordle.databinding.ActivityMainBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GameState game;
    private int currentGuess = 0;
    private boolean gameIsActive = false;

    ViewGroup[] rows = new ViewGroup[6];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(v -> {onSubmit();});

        Button adminButton = findViewById(R.id.admin_button);
        adminButton.setOnClickListener(v -> {switchToAdminMenu();});

        rows[0] = findViewById(R.id.row1_container);
        rows[1] = findViewById(R.id.row2_container);
        rows[2] = findViewById(R.id.row3_container);
        rows[3] = findViewById(R.id.row4_container);
        rows[4] = findViewById(R.id.row5_container);
        rows[5] = findViewById(R.id.row6_container);

        disableRow(rows[1]);
        disableRow(rows[2]);
        disableRow(rows[3]);
        disableRow(rows[4]);
        disableRow(rows[5]);

        try {
            startGame();
        } catch (IOException e) {
            System.out.println("Error loading word bank files for new game");
            throw new RuntimeException(e);
        }
    }

    private void startGame(WordBank bank) throws FileNotFoundException {
        game = new GameState(bank.generateTargetWord());

        gameIsActive = true;
    }

    private void startGame() throws IOException {
        startGame(getWordBank());
    }

    private void disableRow(ViewGroup row) {
        for (int i = 0; i < row.getChildCount(); i++) {
            EditText child = (EditText)row.getChildAt(i);
            child.setEnabled(false);
        }
    }

    private void enableRow(ViewGroup row) {
        for (int i = 0; i < row.getChildCount(); i++) {
            EditText child = (EditText)row.getChildAt(i);
            child.setEnabled(true);
        }
    }

    private void onSubmit() {
        if (!gameIsActive) {
            return;
        }

        switch (validateGuess()) {
            case CORRECT:
                colorizeCorrect(currentGuess);
                break;
            case INCORRECT:
                colorizeFields(currentGuess, game.getCurrentGuess().getCorrect(game.getTargetWord()));
                break;
            case INVALID:
                return;
        }

        if (nextGuess()) {
            displayMessage("Too bad, your word was: '" + game.getTargetWord() + "'");
        }
    }

    private ValidGuess validateGuess() {
        Word currentGuess = getGuessWord();
        game.updateCurrentGuess(currentGuess);

        if (currentGuess == null) {
            displayMessage("Please input a 5 letter word");
            return ValidGuess.INCORRECT;
        }

        if (!getWordBank().isValid(currentGuess)) {
            displayMessage("Please input a valid word");
            return ValidGuess.INVALID;
        }

        if (game.getCurrentGuess().equals(game.getTargetWord())) {
            displayMessage("Great Job! You got the word correct");
            return ValidGuess.CORRECT;
        }

        return ValidGuess.INCORRECT;
    }

    private boolean nextGuess() {
        if (currentGuess >= 5) {
            disableRow(rows[rows.length - 1]);
            return true;
        }

        disableRow(rows[currentGuess]);
        currentGuess++;
        enableRow(rows[currentGuess]);

        return false;
    }

    private Word getGuessWord() {
        ViewGroup row = rows[currentGuess];
        int wordLength = row.getChildCount();
        Word guess = new Word(wordLength);

        for (int i = 0; i < wordLength; i++) {
            EditText child = (EditText)row.getChildAt(i);
            Editable edit = child.getText();

            if (edit.length() == 0) {
                return null;
            }

            char letter = edit.charAt(0);
            guess.pushChar(letter);
        }

        return guess;
    }

    private void displayMessage(String msg) {
        TextView text = findViewById(R.id.appMessage);
        text.setText(msg);
    }

    private void colorizeFields(int rowInd, CharValidity[] validityData) {
        ViewGroup row = rows[rowInd];

        for (int i = 0; i < row.getChildCount(); i++) {
            EditText child = (EditText)row.getChildAt(i);
            CharValidity valid = validityData[i];

            switch (valid) {
                case CORRECT_POSITION:
                    child.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                    break;
                case PRESENT_CHAR:
                    child.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                    break;
                case INCORRECT:
                    child.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                    break;
            }
        }
    }

    private void colorizeCorrect(int rowInd) {
        ViewGroup row = rows[rowInd];

        for (int i = 0; i < row.getChildCount(); i++) {
            EditText child = (EditText)row.getChildAt(i);
            child.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void switchToAdminMenu() {
        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    private WordBank getWordBank() {
        WordleApp app = (WordleApp)getApplicationContext();
        return app.getCurrentWordBank();
    }

    private enum ValidGuess {
        CORRECT,
        INCORRECT,
        INVALID
    }
}
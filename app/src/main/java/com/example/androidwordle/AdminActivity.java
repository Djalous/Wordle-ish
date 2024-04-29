package com.example.androidwordle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button wordleButton = findViewById(R.id.wordle_button);
        wordleButton.setOnClickListener(v -> {switchToWordleMenu();});

        Button targetWordsButton = findViewById(R.id.targetWordsButton);
        targetWordsButton.setOnClickListener(v -> {readInTargetWords();});

        Button validWordsButton = findViewById(R.id.validWordsButton);
        validWordsButton.setOnClickListener(v -> {readInValidWords();});

        updateTargetWordsField();
        updateValidWordsField();
    }

    private void updateTargetWordsField() {
        EditText targetWords = findViewById(R.id.targetWordsInput);
        WordleApp app = (WordleApp)getApplicationContext();

        WordBank bank = app.getCurrentWordBank();

        targetWords.setText(bank.getTargetWordsText());
    }

    private void updateValidWordsField() {
        EditText validWords = findViewById(R.id.validWordsInput);
        WordleApp app = (WordleApp)getApplicationContext();

        WordBank bank = app.getCurrentWordBank();

        validWords.setText(bank.getValidWordsText());
    }

    private void readInTargetWords() {
        EditText targetWords = findViewById(R.id.targetWordsInput);
        String words = targetWords.getText().toString();
        try {
            WordBank.updateTargetBank(new ByteArrayInputStream(words.getBytes(StandardCharsets.UTF_8)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //This shouldn't actually be possible as the input stream is not a file
        }
    }

    private void readInValidWords() {
        EditText validWords = findViewById(R.id.validWordsInput);
        String words = validWords.getText().toString();
        try {
            WordBank.updateValidBank(new ByteArrayInputStream(words.getBytes(StandardCharsets.UTF_8)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //This shouldn't actually be possible as the input stream is not a file
        }
    }

    private void switchToWordleMenu() {
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
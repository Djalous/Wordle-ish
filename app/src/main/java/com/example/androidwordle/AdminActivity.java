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

    private void switchToWordleMenu() {
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
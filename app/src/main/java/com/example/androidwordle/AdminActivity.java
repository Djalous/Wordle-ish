/**
 *  Copyright 2024 SWE 2710 111 Team B (Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
import java.nio.charset.StandardCharsets;

/**
 * Binding class for the admin activity xml view
 * @author Jack Rosenbecker
 * @version created on 4/26/23
 */
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
        WordBank.updateTargetBank(new ByteArrayInputStream(words.getBytes(StandardCharsets.UTF_8)));
    }

    private void readInValidWords() {
        EditText validWords = findViewById(R.id.validWordsInput);
        String words = validWords.getText().toString();
        words = words.replace("," , "");
        words = words.replace(" " , "");
        WordBank.updateValidBank(new ByteArrayInputStream(words.getBytes(StandardCharsets.UTF_8)));
    }

    private void switchToWordleMenu() {
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
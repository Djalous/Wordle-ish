package com.example.androidwordle;

import android.app.Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class WordleApp extends Application {
    private WordBank currentWords;

    @Override
    public void onCreate() {
        super.onCreate();

        ResourceManager resource = new ResourceManager(getResources());
        try {
            InputStream validFile = resource.loadResource("wordle-full.txt");
            InputStream targetFile = resource.loadResource("wordle-official.txt");

            setCurrentWordBank(targetFile, validFile);
        } catch (IOException e) {
            System.out.println("Error reading in default wordle files");
            throw new RuntimeException(e);
        }
    }

    public void setCurrentWordBank(InputStream targetFile, InputStream validFile) throws FileNotFoundException {
        currentWords = new WordBank(targetFile, validFile);
    }

    public WordBank getCurrentWordBank() {
        return currentWords;
    }
}

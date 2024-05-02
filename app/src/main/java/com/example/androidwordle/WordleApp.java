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

import android.app.Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Manages the overall state of the entire wordle application. This can be used to persist
 * data between activities.
 * @author Jack Rosenbecker
 * @version created on 4/26/23
 */
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


    /**
     * Sets the current, persisting word bank, to a new word bank with the given files.
     * @param targetFile Target words input stream.
     * @param validFile Valid words input stream.
     */
    public void setCurrentWordBank(InputStream targetFile, InputStream validFile) {
        currentWords = new WordBank(targetFile, validFile);
    }

    /**
     * Returns the current, persisting word bank.
     * @return Persistent word bank.
     */
    public WordBank getCurrentWordBank() {
        return currentWords;
    }
}

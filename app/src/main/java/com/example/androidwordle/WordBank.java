package com.example.androidwordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Manages the bank of words a Wordle game will have access to for the
 * guessable words and the valid words.
 * @author Duaa Aljalous
 * @version created on 2/5/23
 */
public class WordBank {
    private static final int MAX_PREV_STORE = 10;

    private static final RecentHistory<Word[]> prevTargetWords = new RecentHistory<>(MAX_PREV_STORE);
    private static final RecentHistory<Word[]> prevValidWords = new RecentHistory<>(MAX_PREV_STORE);
    private static List<Word> targetWords = new ArrayList<>();
    private static List<Word> validWords = new ArrayList<>();
    public static int WORD_LENGTH = 5;

    private static final String TARGET_FILE_PATH = "wordle-official.txt";
    private static final String VALID_FILE_PATH = "wordle-full.txt";

    public WordBank() throws FileNotFoundException {
        updateTargetBank(ResourceManager.loadResource(TARGET_FILE_PATH));
        updateValidBank(ResourceManager.loadResource(VALID_FILE_PATH));

    }

    /** Create a main.WordBank from the given target word file and the given valid word file
     * @param targetFile File containing the guessable words
     * @param validFile File containing words considered valid
     */
    public WordBank(File targetFile, File validFile) throws FileNotFoundException {
        updateTargetBank(targetFile);
        updateValidBank(validFile);
    }

    public static void setWordLength(int parseInt) {
        WORD_LENGTH = parseInt;
    }

    /** Updates the file used for guessable words
     * @param targetFile File to used for guessable words
     * @throws InvalidPathException Thrown if the passed in file cannot be found
     */
    public static void updateTargetBank(File targetFile) throws InvalidPathException, FileNotFoundException {
        targetWords = updateBank(targetFile, targetWords, prevTargetWords);
    }

    /** Updates the file used for valid words
     * @param validFile File used for valid words
     * @throws InvalidPathException Thrown if the passed in file cannot be found
     */
    public static void updateValidBank(File validFile) throws InvalidPathException, FileNotFoundException {
        validWords = updateBank(validFile, validWords, prevValidWords);
    }

    private static List<Word> updateBank(File validFile, List<Word> storage, RecentHistory<Word[]> history) throws InvalidPathException, FileNotFoundException {
        try (Scanner in = new Scanner(validFile)) {
            checkFileExtension(validFile, in);

            if (storage != null) {
                history.add(storage.toArray(new Word[0]));
            }

            List<Word> newStorage = new ArrayList<>();
            addToWordList(newStorage, in);
            return newStorage;
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
            throw new FileNotFoundException("File cannot be found or does not exist.");
        }
    }

    /** Does the given word appear in our valid words list?
     * @param word main.Word to check
     * @return True if the word is present. False otherwise
     */
    public boolean isValid(Word word) {
        if (word.toString().matches("[^A-Za-z]+")) return false;
        return validWords.contains(word);
    }

    /** Function that takes in the scanner to a word file and adds it to a
     * given list
     * @param list List to add the words to
     * @param scanner Scanner of the word file
     */
    private static void addToWordList(List<Word> list, Scanner scanner) {
        while (scanner.hasNext()) {
            String wordStr = scanner.nextLine().toLowerCase();
            if (wordStr.length() == WORD_LENGTH) {
                Word wordObj = new Word(wordStr.length());

                for (int i = 0; i < wordStr.length(); ++i) {
                    wordObj.pushChar(wordStr.charAt(i));
                }
                list.add(wordObj);
            }
        }
    }

    /** Verifies the file has a supported file extension and configures
     * the scanner appropriately
     * @param file File to verify
     * @param scanner Scanner of file to verify and configure
     */
    public static void checkFileExtension(File file, Scanner scanner) {
        String filePath = file.getPath();
        //admin menu dependent
        if (filePath.endsWith(".csv")) {
            scanner.useDelimiter(",");
        } else if (filePath.endsWith(".txt")) {
            scanner.useDelimiter(System.lineSeparator());
        } else {
            System.out.println("The current file " + filePath + " is an unsupported file type.");
            throw new InvalidPathException(filePath, "Unsupported file type.");
        }
    }

    public Word generateTargetWord() {
        Random rand = new Random();
        return targetWords.get(rand.nextInt(targetWords.size()));
    }

    public Word[][] getValidWordHistory() {
        return prevValidWords.toArray(new Word[0][0]);
    }

    public Word[][] getTargetWordHistory() {
        return prevTargetWords.toArray(new Word[0][0]);
    }
}

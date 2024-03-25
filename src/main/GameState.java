package main;

import java.util.HashMap;
import java.util.List;

/**
 * Contains all the necessary information for a game of Wordle
 * @author Jack Rosenbecker
 * @version created on 2/5/24
 */
public class GameState {
    private Word targetWord;
    private HashMap<String, Integer> guesses;
    private Word currentGuess;

    private int guessesMade = 0;

    private static final int MAX_GUESSES = 6;

    /** Constructs a new game with the given word being the correct target word
     * @param targetWord Target word the user is trying to guess for
     */
    public GameState(Word targetWord) {
        this.guesses = new HashMap<>();
        this.targetWord = targetWord;
    }

    /** Gets the target guess word
     * @return The word the user is trying to guess
     */
    public Word getTargetWord() {
        return targetWord; //TODO: Implement me!
    }

    /** Returns a copy of the current guesses
     * @return The list of guesses that have been made
     */
    public HashMap<String, Integer> getGuessList() {
        return (HashMap<String, Integer>)guesses.clone();
    }

    /**
     * @return The current guess
     */
    public Word getCurrentGuess() {
        return currentGuess;
    }

    public HashMap<Character, Integer> getLetterAndFreq() {
        return null; //TODO: Implement me!
    }

    public HashMap<String, Integer> getWordAndFreq() {
        return null; //TODO: Implement me!
    }

    /** Gets the current number of guesses made
     * @return The current number of guesses the user has made
     */
    public int getGuessCount() {
        return guessesMade;
    }

    public void updateCurrentGuess(Word guess) {
        if (guessesMade >= MAX_GUESSES) {
            throw new ArrayIndexOutOfBoundsException("Game States may only have up to 6 guesses stored within it");
        }

        guessesMade++;

        this.currentGuess = guess;
        String guessStr = guess.toString();
        if (guesses.containsKey(guessStr)) {
            int count = guesses.get(guessStr);
            guesses.put(guessStr, count + 1);
        } else {
            guesses.put(guessStr, 1);
        }
    }
}

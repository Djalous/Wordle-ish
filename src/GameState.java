import java.util.HashMap;
import java.util.List;

public class GameState {
    private Word targetWord;
    private HashMap<String, Integer> guesses;
    private Word currentGuess;

    private int guessesMade = 0;

    private static final int MAX_GUESSES = 6;

    public GameState(Word targetWord) {
        this.guesses = new HashMap<>();
        this.targetWord = targetWord;
    }

    public Word getTargetWord() {
        return targetWord; //TODO: Implement me!
    }

    public List<Word> getGuessList() {
        return null; //TODO: Implement me!
    }

    public Word getCurrentGuess() {
        return currentGuess;
    }

    public HashMap<Character, Integer> getLetterAndFreq() {
        return null; //TODO: Implement me!
    }

    public HashMap<String, Integer> getWordAndFreq() {
        return null; //TODO: Implement me!
    }

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

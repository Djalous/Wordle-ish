import java.util.HashMap;
import java.util.List;

public class GameState {
    private Word targetWord;
    private HashMap<String, Integer> guesses;
    private Word currentGuess;


    public GameState(Word targetWord) {

    }

    public Word getTargetWord() {
        return null; //TODO: Implement me!!
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
        return guesses.size();
    }
}

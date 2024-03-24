import main.Word;
import main.WordBank;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

public class WordBankTests {
    static WordBank wordBank;
    static final int LENGTH = 5;

    @BeforeClass
    public static void setUp() throws FileNotFoundException {
        wordBank = new WordBank();
    }
    @Test
    public void checkGuessWords() {
        String[] invalidGuesses = {"abcde, biubl, FBIGJ, vivuw, hFkbS"};
        for (String guess: invalidGuesses) {
            Word word = new Word(LENGTH);
            for (int i = 0; i < LENGTH; ++i)
                word.pushChar(guess.charAt(i));
            Assert.assertFalse(wordBank.isValid(word));
        }

        String[] validGuesses = {"years", "NEVER", "fAlSe", "walks", "fuzzy"};
        for (String guess: validGuesses) {
            Word word = new Word(LENGTH);
            for (int i = 0; i < LENGTH; ++i)
                word.pushChar(guess.charAt(i));
            Assert.assertTrue(wordBank.isValid(word));
        }
    }
}

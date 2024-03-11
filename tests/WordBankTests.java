import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;

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

    @Test
    public void testHistory() throws FileNotFoundException {
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));

        Word[][] targetWordHistory = wordBank.getTargetWordHistory();
        Word[][] validWordHistory = wordBank.getValidWordHistory();

        Assert.assertEquals(targetWordHistory.length, 2);
        Assert.assertEquals(validWordHistory.length, 2);
    }

    @Test
    public void testMin10History() throws FileNotFoundException {
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));

        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateValidBank(new File("./wordle-full.txt"));

        Word[][] targetWordHistory = wordBank.getTargetWordHistory();
        Word[][] validWordHistory = wordBank.getValidWordHistory();

        Assert.assertEquals(targetWordHistory.length, 10);
        Assert.assertEquals(validWordHistory.length, 10);

        wordBank.updateValidBank(new File("./wordle-full.txt"));
        wordBank.updateTargetBank(new File("./wordle-official.txt"));

        Assert.assertTrue(targetWordHistory.length >= 10);
        Assert.assertTrue(validWordHistory.length >= 10);
    }
}

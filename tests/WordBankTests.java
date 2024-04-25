import main.Word;
import main.WordBank;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
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
        for (String guess : invalidGuesses) {
            Word word = new Word(LENGTH);
            for (int i = 0; i < LENGTH; ++i)
                word.pushChar(guess.charAt(i));
            Assert.assertFalse(wordBank.isValid(word));
        }

        String[] validGuesses = {"years", "NEVER", "fAlSe", "walks", "fuzzy"};
        for (String guess : validGuesses) {
            Word word = new Word(LENGTH);
            for (int i = 0; i < LENGTH; ++i)
                word.pushChar(guess.charAt(i));
            Assert.assertTrue(wordBank.isValid(word));
        }
    }

    //TODO Expecting 10 and getting 2 ???
    @Test
    public void testHistory() throws FileNotFoundException {
        try (InputStream targetFileStream = new FileInputStream(new File("./src/resources/wordle-official.txt"));
             InputStream validFileStream = new FileInputStream(new File("./src/resources/wordle-full.txt"))) {
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);

            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
        } catch (IOException e) {
            System.out.println("Issue loading file(s).");
        }

        Word[][] targetWordHistory = wordBank.getTargetWordHistory();
        Word[][] validWordHistory = wordBank.getValidWordHistory();

        Assert.assertEquals(targetWordHistory.length, 2);
        Assert.assertEquals(validWordHistory.length, 2);
    }

    @Test
    public void testMin10History() throws FileNotFoundException {
        try (InputStream targetFileStream = new FileInputStream(new File("./src/resources/wordle-official.txt"));
             InputStream validFileStream = new FileInputStream(new File("./src/resources/wordle-full.txt"))) {

            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);
            WordBank.updateTargetBank(targetFileStream);

            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);
            WordBank.updateValidBank(validFileStream);

            Word[][] targetWordHistory = wordBank.getTargetWordHistory();
            Word[][] validWordHistory = wordBank.getValidWordHistory();

            Assert.assertEquals(targetWordHistory.length, 10);
            Assert.assertEquals(validWordHistory.length, 10);

            WordBank.updateValidBank(validFileStream);
            WordBank.updateTargetBank(targetFileStream);

            Assert.assertTrue(targetWordHistory.length >= 10);
            Assert.assertTrue(validWordHistory.length >= 10);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}

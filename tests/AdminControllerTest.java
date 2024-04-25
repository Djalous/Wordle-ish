import main.Word;
import main.WordBank;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdminControllerTest {
    static WordBank wordBank;

    /**
     * This test checks if the updateWordBank method works correctly after upload of new files.
     * @throws FileNotFoundException if the file does not exist
     */
    @Test
    public void testUpdateWordBank() throws FileNotFoundException {
        // Create an instance of GameController
        //GameController controller = new GameController();

        wordBank = new WordBank();
        Word word = new Word("satin");

        // Create valid File instances
        File validTargetBankFile = new File("./src/resources/wordle-full.txt");
        File validVocabBankFile = new File("./src/resources/wordle-official.txt");

        wordBank = new WordBank(new FileInputStream(validTargetBankFile), new FileInputStream(validVocabBankFile));
        Assert.assertTrue(wordBank.isValid(word));

        // we expect false here because abcde is not a valid word
        Word invalidWord = new Word("abcde");
        Assert.assertFalse(wordBank.isValid(invalidWord));


    }
}

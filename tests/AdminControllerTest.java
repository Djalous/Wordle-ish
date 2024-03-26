import main.Word;
import main.WordBank;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminControllerTest {
    static WordBank wordBank;

    /**
     * This test checks if the updateWordBank method works correctly after upload of new files.
     * @throws FileNotFoundException if the file does not exist
     */
    @Test
    public void testUpdateWordBank() throws FileNotFoundException {
        // Create an instance of Controller
        //Controller controller = new Controller();

        wordBank = new WordBank();
        Word word = new Word("satin");

        // Create valid File instances
        File validTargetBankFile = new File("./wordle-full.txt");
        File validVocabBankFile = new File("./wordle-official.txt");

        wordBank = new WordBank(validTargetBankFile, validVocabBankFile);
        assertTrue(wordBank.isValid(word));

        // we expect false here because abcde is not a valid word
        Word invalidWord = new Word("abcde");
        assertFalse(wordBank.isValid(invalidWord));


    }
}

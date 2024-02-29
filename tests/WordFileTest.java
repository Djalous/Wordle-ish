import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class WordFileTest {

    @Test
    public void testLoadTXT() throws IOException {
        File testFile = new File("./wordle-test.txt");
        File validFile = new File("./wordle-full.txt");
        WordBank wordbank = new WordBank(testFile,validFile);
        wordbank.updateTargetBank(testFile);
      //  wordbank.
      //  Assert.assertTrue(wordbank.updateTargetBank(););

    }
}

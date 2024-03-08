import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class WordFileTests {
    private WordBank wordBank;

    @Before
    public void setUp() {
        wordBank = new WordBank();
    }

    @Test
    public void checkFileExtensionHandlesCsvFilesCorrectly() throws FileNotFoundException {
        File csvFile = new File("./tests/testFile.csv");
        Scanner scanner = new Scanner(csvFile);
        wordBank.checkFileExtension(csvFile, scanner);
        Assert.assertEquals(",", scanner.delimiter().toString());
    }

    @Test
    public void checkFileExtensionHandlesTxtFilesCorrectly() throws FileNotFoundException {
        File txtFile = new File("./wordle-full.txt");
        Scanner scanner = new Scanner(txtFile);
        wordBank.checkFileExtension(txtFile, scanner);
        Assert.assertEquals(System.lineSeparator(), scanner.delimiter().toString());
    }

    @Test(expected = InvalidPathException.class)
    public void checkFileExtensionThrowsExceptionForUnsupportedFileType() throws FileNotFoundException {
        File unsupportedFile = new File("./readme.md");
        Scanner scanner = new Scanner(unsupportedFile);
        wordBank.checkFileExtension(unsupportedFile, scanner);
    }
}

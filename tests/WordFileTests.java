import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class WordFileTests {
    static WordBank wordBank;

    @Before
    public void setUp() throws FileNotFoundException {
        wordBank = new WordBank();
    }

    @Test
    public void updateValidBankWithValidFile() {
        File validFile = new File("./wordle-full.txt");
        try {
            wordBank.updateValidBank(validFile);
            Assert.assertTrue(wordBank.isValid(new Word("years")));
        } catch (InvalidPathException e) {
            Assert.fail("Exception should not be thrown with valid file");
        }
    }

/*    @Test
    public void updateValidBankWithInvalidFile() {
        File invalidFile = new File("./invalid.txt");
        try {
            wordBank.updateValidBank(invalidFile);
            Assert.fail("Exception should be thrown with invalid file");
        } catch (InvalidPathException e) {
            Assert.assertTrue(true);
        }
    }*/

/*    @Test
    public void updateValidBankWithUnsupportedFileType() {
        File unsupportedFile = new File("./unsupported.docx");
        try {
            wordBank.updateValidBank(unsupportedFile);
            Assert.fail("Exception should be thrown with unsupported file type");
        } catch (InvalidPathException e) {
            Assert.assertTrue(true);
        }
    }*/
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

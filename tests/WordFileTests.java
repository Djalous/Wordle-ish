import main.Word;
import main.WordBank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

/**
 * This class contains tests for the main.WordBank class.
 */
public class WordFileTests {
    static WordBank wordBank;
    /**
     * This method sets up the main.WordBank object before each test.
     * @throws FileNotFoundException if the file does not exist
     */

    @Before
    public void setUp() throws FileNotFoundException {
        wordBank = new WordBank();
    }


    /**
     * This test checks if the updateValidBank method works correctly with a valid file.
     */
    @Test
    public void updateValidBankWithValidFile() {
        File validFile = new File("./src/resources/wordle-full.txt");
        try {
            WordBank.updateValidBank(validFile);
            Assert.assertTrue(wordBank.isValid(new Word("years")));
        } catch (InvalidPathException e) {
            Assert.fail("Exception should not be thrown with valid file");
        } catch (FileNotFoundException e) {
            Assert.fail("Exception should not be thrown with valid file. File was not found.");
        }
    }    /**
     * This test checks if the updateValidBank method throws an exception with an invalid file.
     */
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
    /**
     * This test checks if the updateValidBank method throws an exception with an unsupported file type.
     */
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
    /**
     * This test checks if the checkFileExtension method correctly handles txt files.
     * @throws FileNotFoundException if the file does not exist
     */
    @Test
    public void checkFileExtensionHandlesCsvFilesCorrectly() throws FileNotFoundException {
        File csvFile = new File("./tests/testFile.csv");
        Scanner scanner = new Scanner(csvFile);
        WordBank.checkFileExtension(csvFile, scanner);
        Assert.assertEquals(",", scanner.delimiter().toString());
    }
    /**
     * This test checks if the checkFileExtension method correctly handles txt files.
     * @throws FileNotFoundException if the file does not exist
     */
    @Test
    public void checkFileExtensionHandlesTxtFilesCorrectly() throws FileNotFoundException {
        File txtFile = new File("./src/resources/wordle-full.txt");
        Scanner scanner = new Scanner(txtFile);
        WordBank.checkFileExtension(txtFile, scanner);
        Assert.assertEquals(System.lineSeparator(), scanner.delimiter().toString());
    }
    /**
     * This test checks if the checkFileExtension method throws an exception for unsupported file types.
     * @throws FileNotFoundException if the file does not exist
     */

    @Test(expected = InvalidPathException.class)
    public void checkFileExtensionThrowsExceptionForUnsupportedFileType() throws FileNotFoundException {
        File unsupportedFile = new File("./readme.md");
        Scanner scanner = new Scanner(unsupportedFile);
        WordBank.checkFileExtension(unsupportedFile, scanner);
    }
}

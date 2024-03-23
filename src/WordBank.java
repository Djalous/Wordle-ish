import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Manages the bank of words a Wordle game will have access to for the
 * guessable words and the valid words.
 * @author Duaa Aljalous
 * @version created on 2/5/23
 */
public class WordBank {
    private static final List<Word> targetWords = new ArrayList<>();
    private static final List<Word> validWords = new ArrayList<>();
    public static int WORD_LENGTH = 5;

    public WordBank() throws FileNotFoundException {
        updateTargetBank(new File("./wordle-official.txt"));
        updateValidBank(new File("./wordle-full.txt"));

    }

    /** Create a WordBank from the given target word file and the given valid word file
     * @param targetFile File containing the guessable words
     * @param validFile File containing words considered valid
     */
    public WordBank(File targetFile, File validFile) throws FileNotFoundException {
        updateTargetBank(targetFile);
        updateValidBank(validFile);
    }

    public static void setWordLength(int parseInt) {
        WORD_LENGTH = parseInt;
    }

    /** Updates the file used for guessable words
     * @param targetFile File to used for guessable words
     * @throws InvalidPathException Thrown if the passed in file cannot be found
     */
    public static void updateTargetBank(File targetFile) throws InvalidPathException, FileNotFoundException {
        try (Scanner in = new Scanner(targetFile)) {
            checkFileExtension(targetFile, in);
            targetWords.clear();
            addToWordList(targetWords, in);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
            throw new FileNotFoundException("File cannot be found or does not exist.");

        }
        System.out.println("Target words: " + targetWords.size() + " have been added.");
    }

    /** Updates the file used for valid words
     * @param validFile File used for valid words
     * @throws InvalidPathException Thrown if the passed in file cannot be found
     */
    public static void updateValidBank(File validFile) throws InvalidPathException {
        try (Scanner in = new Scanner(validFile)) {
            checkFileExtension(validFile, in);
            validWords.clear();
            addToWordList(validWords, in);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
        System.out.println("Valid words: " + validWords.size() + " have been added.");
    }

    /** Does the given word appear in our valid words list?
     * @param word Word to check
     * @return True if the word is present. False otherwise
     */
    public boolean isValid(Word word) {
        if (word.toString().matches("[^A-Za-z]+")) return false;
        return validWords.contains(word);
    }

    /** Function that takes in the scanner to a word file and adds it to a
     * given list
     * @param list List to add the words to
     * @param scanner Scanner of the word file
     */
    private static void addToWordList(List<Word> list, Scanner scanner) {
        while (scanner.hasNext()) {
            String wordStr = scanner.nextLine().toLowerCase();
            Word wordObj = new Word(wordStr.length());

            for (int i = 0; i < wordStr.length(); ++i) {
                wordObj.pushChar(wordStr.charAt(i));
            }
            list.add(wordObj);
        }
    }

    /** Verifies the file has a supported file extension and configures
     * the scanner appropriately
     * @param file File to verify
     * @param scanner Scanner of file to verify and configure
     */
    private static void checkFileExtension(File file, Scanner scanner) {
        String filePath = file.getPath();
        //admin menu dependent
        if (filePath.endsWith(".csv")) {
            scanner.useDelimiter(",");
        } else if (filePath.endsWith(".txt")) {
            scanner.useDelimiter(System.lineSeparator());
        } else {
            System.out.println("The current file " + filePath + " is an unsupported file type.");
            throw new InvalidPathException(filePath, "Unsupported file type.");
        }
    }

    public Word generateTargetWord() {
        Random rand = new Random();
        return targetWords.get(rand.nextInt(targetWords.size()));
    }
}
